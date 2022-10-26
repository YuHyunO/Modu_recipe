package com.modu.service;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardDetailNextPrev;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardList;
import com.modu.domain.board.BoardListVo;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardFindingServiceImpl implements BoardFindingService {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public BoardListVo listingPosts(HttpServletRequest request, HttpSession session) {
       // String cpStr = request.getParameter("curPage");
       // String psStr = request.getParameter("pgSize");
        int type = 1;
        long curPage = 1;
        long pgSize = 10;
        long totalPage;
        long totalPost = boardMapper.selectPostCountByType(1);
        
        try {
log.info("#3331 value : " + request.getParameter("value") );
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
log.info("#3332# " + period);
                      //if (nameOption.equals("ingredient")) {
                      //    totalPost = boardMapper.selectBoardCountByIngredient(keyword, period);
                      //}else {
                          if(nameOption.equals("title")) {
                              nameOption = "TITLE";
                          }else if(nameOption.equals("mNickname")) {
                              nameOption = "M_NICKNAME";
                          }
                       //   totalPost = boardMapper.selectBoardCountByKeyword(nameOption, keyword, period);
                      //}
log.info("##3333 " + totalPost);
            }
        }catch(NullPointerException ne) {}
log.info("##334 중간");
        
        if(request.getParameter("curPage") != null) {
            if(session.getAttribute("curPage") != null) {
                curPage = (long)session.getAttribute("curPage");
            }
            String param = request.getParameter("curPage");
log.info("##3341 curP : " + param);
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
log.info("##3342  pgSize: " + pgSize);
        }else if(session.getAttribute("pgSize") != null) {
            pgSize = (long)session.getAttribute("pgSize");   
log.info("##3343  pgSize: " + pgSize);
        }
        
        totalPage = (long) (totalPost/pgSize);
log.info("##3343  totalPage : " + totalPage);
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
log.info("##3343  endRow , beginRow : " + endRow + "  " + beginRow);
        List<Board> boardList = boardMapper.selectFreePostsByType(type, beginRow, endRow);
log.info("##3344  pgSize: " + boardList);
        if(request.getParameter("value")!=null) {
            boardList = getSearchedBoards(request, beginRow, endRow);
        }
        
        BoardListVo data = new BoardListVo(boardList, curPage, pgSize, totalPage);
        log.info("#331 :  " + data);
        return data;
        
 /*       
        String nameOption = request.getParameter("nameOption");
        String keyword = request.getParameter("keyword");
        //int period = Integer.parseInt(request.getParameter("period"));
        
        // (1) cp
        
        if (cpStr == null) {
            Object cpObj = session.getAttribute("curPage");
            if (cpObj != null) {
                curPage = (Long) cpObj;
            }
        } else {
            cpStr = cpStr.trim();
            curPage = Long.parseLong(cpStr);
        }
        session.setAttribute("curPage", curPage);

        // (2) ps

        if (psStr == null) {
            Object psObj = session.getAttribute("pgSize");
            if (psObj != null) {
                pgSize = (Long) psObj;
            }
        } else {
            psStr = psStr.trim();
            long psParam = Long.parseLong(psStr);

            Object psObj = session.getAttribute("pgSize");
            if (psObj != null) {
                long psSession = (Long) psObj;
                if (psSession != psParam) {
                    curPage = 1;
                    session.setAttribute("curPage", curPage);
                }
            } else {
                if (pgSize != psParam) {
                    curPage = 1;
                    session.setAttribute("curPage", curPage);
                }
            }
            pgSize = psParam;
        }
        session.setAttribute("pgSize", pgSize);		
	    
		long endRow = pgSize*curPage; 
		long beginRow = endRow-pgSize+1;
		
		long startPage, endPage;
		boolean prev, next;
		long totalPosts = boardMapper.selectPostCountByType(1);
		endPage = (long) (Math.ceil(curPage/5.0)) * 5;
		startPage = endPage - 4;
		prev = startPage > 1;
		long realEnd = (long)(Math.ceil(totalPosts * 1.0) / pgSize);
		endPage = realEnd <= endPage? realEnd : endPage;
		next = endPage < realEnd;
		List<Board> listPosts = boardMapper.selectFreePostsByType(type, beginRow, endRow);
		 
		long listSize = totalPosts/pgSize; 
		if (totalPosts%pgSize != 0) listSize++;
		
		BoardList boardList = new BoardList(listPosts, totalPost, totalPage, pgSize, curPage, type,startPage,endPage,prev,next);
		return boardList;
		*/
        
	}
	private List<Board> getSearchedBoards(HttpServletRequest request, long beginRow, long endRow){
	    log.info("##335 시작");
        String value = request.getParameter("value");
        int period = Integer.parseInt(request.getParameter("period"));
        String nameOption;
        String keyword;
        log.info("#333 :  " + value + "  " + period);
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
log.info("#333nameOption  :  " + nameOption);
                  keyword = request.getParameter("keyword");
  log.info("#333keyword  :  " + keyword);       
                  if (nameOption.equals("title")) {
                      nameOption = "TITLE";
  log.info("#333nameOption 11  :  " + nameOption);  
                  }else if(nameOption.equals("mNickname")) {
                      nameOption = "M_NICKNAME";
  log.info("#333nameOption 22 :  " + nameOption);  
                  }
                  /*else if(nameOption.equals("ingredient")) {
                      recipeList = boardMapper.selectBoardListByIngredient(keyword, period, beginRow, endRow);
                      break;
                  }*/
  log.info("333끌" +"nameOption,"+nameOption +"keyword,"+keyword+"period,"+period+ "beginRow," +beginRow+"endRow: "+endRow);
  
                  boardList = boardMapper.selectBoardListByKeyword(nameOption, keyword, period, beginRow, endRow);
  log.info("333 브레이크 전 boardList  : " + boardList);
                  break;
        }
        
        return boardList;
    }
	
	private long endRow(long beginRow, long endRow) {
        // TODO Auto-generated method stub
        return 0;
    }
    public BoardDetail getPost(long id) {
		BoardDetail boardDetail = new BoardDetail();
		Board board = boardMapper.selectPost(id);
		BoardDetailNextPrev nextPrev = boardMapper.selectBoardNextPrev(id);
		log.info("#4444 " + nextPrev);
		log.info("##44445  " + nextPrev.getTitle());
		List<BoardFile> boardFile = boardMapper.selectFile(id);
		log.info("##44446  " + nextPrev.getPrevId());
		log.info("##44447  " + nextPrev.getNextId());
		
		boardDetail.setBoard(board);
		boardDetail.setBoardFile(boardFile);
		boardDetail.setBoardDetailNextPrev(nextPrev);
		return boardDetail;
	}	

}
