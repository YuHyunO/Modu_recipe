package com.modu.service;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardDetailNextPrev;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardListVo;
import com.modu.mapper.BoardLegacyMapper;
import com.modu.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardFindingServiceImpl implements BoardFindingService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardLegacyMapper boardLegacyMapper;

	@Override
	public BoardListVo listingPosts(HttpServletRequest request, HttpSession session, int type) {
        long curPage = 1;
        long pgSize = 10;
        long totalPage;
        long totalPost = boardMapper.selectPostCountByType(1);
        try {
            switch(request.getParameter("value")) {
            case "0": int period = Integer.parseInt(request.getParameter("period"));
                      if(period == 0) {
                          break;  
                      }else{
                          totalPost = boardMapper.selectBoardCountByPeriod(period);
                          break;                          
                      }
            case "2": String nameOption = request.getParameter("nameOption");
                      String keyword = request.getParameter("keyword");
                      period = Integer.parseInt(request.getParameter("period"));
                          if(nameOption.equals("title")) {
                              nameOption = "TITLE";
                          }else if(nameOption.equals("mNickname")) {
                              nameOption = "M_NICKNAME";
                          }
                      totalPost = boardMapper.selectBoardCountByKeyword(nameOption, keyword, period);
            }
        }catch(NullPointerException ne) {}
        
        if(request.getParameter("curPage") != null) {
            if(session.getAttribute("curPage") != null) {
                curPage = (long)session.getAttribute("curPage");
            }
            String param = request.getParameter("curPage");
            try {
                curPage = Integer.parseInt(request.getParameter("curPage"));
            }catch(NumberFormatException nfe) {
                switch(param) {
                case "pre": curPage = curPage - 1; break; //previous page
                case "next": curPage = curPage + 1;     //next page              
                }
            }
        }else if(session.getAttribute("curPage") != null) {
            curPage = (long)session.getAttribute("curPage");
        }
        
        if(request.getParameter("pgSize") != null) {          
            pgSize = Integer.parseInt(request.getParameter("pgSize"));
        }else if(session.getAttribute("pgSize") != null) {
            pgSize = (long)session.getAttribute("pgSize");   
        }
        
        totalPage = totalPost/pgSize;
        if(totalPost % pgSize > 0) {
            totalPage = totalPage + 1;
        }       
        
        if(curPage<1) { 
            curPage = 1;
        }else if(curPage>totalPage) { 
            curPage = totalPage;
        }
        
        session.setAttribute("curPage", curPage);
        session.setAttribute("pgSize", pgSize);
        
        long endRow = curPage*pgSize;
        long beginRow = endRow-pgSize+1;
        List<Board> boardList = boardMapper.selectFreePostsByType(type, beginRow, endRow);
        if(request.getParameter("value")!=null) {
            boardList = getSearchedBoards(request, beginRow, endRow);
        }
       // long bId = boardList.set(index, element)
       // boardLegacyMapper.selectReplyCount(bId);
        //log.info("1029 2 data : " + boardList);
        
        BoardListVo data = new BoardListVo(boardList, curPage, pgSize, totalPage);
        return data;
	}
	private List<Board> getSearchedBoards(HttpServletRequest request, long beginRow, long endRow){
        String value = request.getParameter("value");
        int period = Integer.parseInt(request.getParameter("period"));
        String nameOption;
        String keyword;
        List<Board> boardList = new ArrayList<Board>();
           
        switch(value) {
        case "0": if (period == 0) {
                    boardList = boardMapper.selectFreePostsByType(1,beginRow, endRow);
                      break;
                  }else {
                      boardList = boardMapper.selectBoardListByPeriod(period, beginRow, endRow);
                      break;
                  }        
        case "2": nameOption = request.getParameter("nameOption");
                  keyword = request.getParameter("keyword");
                  if (nameOption.equals("title")) {
                      nameOption = "TITLE";
                  }else if(nameOption.equals("mNickname")) {
                      nameOption = "M_NICKNAME";
                  }
  
                  boardList = boardMapper.selectBoardListByKeyword(nameOption, keyword, period, beginRow, endRow);
                  break;
        }
        
        return boardList;
    }
    @Override
    public BoardDetail getPost(long id,HttpServletRequest request,HttpServletResponse response) {
        //long bId = id;
       // long replyCount = boardLegacyMapper.selectReplyCount(bId);
       // log.info("#1029 956 : " + replyCount);
        
        String stringId = String.valueOf(id);
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + stringId + "]")) {//이프 올드쿠키에 문자열 [아이디번호] 포함되어있으면
                //  []로 감싸는 이유는  101 과 10 이 똑같이 10 부분이 중복되서 조회수가 카운트 되지 않을까바 [101],[10]처럼 감싸고 감싼걸 찾는것
                boardLegacyMapper.viewCount(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24); //초단위로 설정된다. 24*60*60-> 24번*60번*60초=24시간
                response.addCookie(oldCookie);
            }
        } else {
            boardLegacyMapper.viewCount(id);
            Cookie newCookie = new Cookie("postView","[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
        
        
        
		BoardDetail boardDetail = new BoardDetail();
		Board board = boardMapper.selectPost(id);
		long rrCount = boardLegacyMapper.selectReplyCount(id);
		long rCount = board.getReply();
		log.info("1029 rcount : " + rCount  + " rrcount2 : " + rrCount);
		if(rCount != rrCount) {
		    boardLegacyMapper.replyCountUpdate(id, rrCount);
		    log.info("#1029  4   성공");
		}else {
		    log.info("#1029 5 다름");
		}
		
		BoardDetailNextPrev nextPrev = boardMapper.selectBoardNextPrev(id);
		List<BoardFile> boardFile = boardMapper.selectFile(id);
		boardDetail.setBoard(board);
		boardDetail.setBoardFile(boardFile);
		boardDetail.setBoardDetailNextPrev(nextPrev);
		
		
		return boardDetail;
	}
    @Override
    public Board getBoard(long id) {
        Board board = boardMapper.selectPost(id);
        return board;
    }

}
