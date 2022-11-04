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
import com.modu.domain.board.BoardListVo;
import com.modu.domain.board.BoardSearchVO;
import com.modu.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardFindingServiceImpl implements BoardFindingService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public BoardListVo listingPosts(HttpServletRequest request, HttpSession session, int type) {
        long curPage = 1;
        long pgSize = 10;
        long totalPage;
        long totalPost = boardMapper.selectPostCountByType(1);

        try {
            switch (request.getParameter("value")) {
                case "0":
                    int period = Integer.parseInt(request.getParameter("period"));
                    if (period == 0) {
                        break;
                    } else {
                        totalPost = boardMapper.selectBoardCountByPeriod(period);
                        break;
                    }
                case "2":
                    String nameOption = request.getParameter("nameOption");
                    String keyword = request.getParameter("keyword");
                    period = Integer.parseInt(request.getParameter("period"));
                    if (nameOption.equals("title")) {
                        nameOption = "TITLE";
                    } else if (nameOption.equals("mNickname")) {
                        nameOption = "M_NICKNAME";
                    }
                    totalPost = boardMapper.selectBoardCountByKeyword(nameOption, keyword, period);
            }
        } catch (NullPointerException ne) {
        }

        if (request.getParameter("curPage") != null) {
            if (session.getAttribute("curPage") != null) {
                curPage = (long) session.getAttribute("curPage");
            }
            String param = request.getParameter("curPage");
            try {
                curPage = Integer.parseInt(request.getParameter("curPage"));
            } catch (NumberFormatException nfe) {
                switch (param) {
                    case "pre":
                        curPage = curPage - 1;
                        break; // previous page
                    case "next":
                        curPage = curPage + 1; // next page
                }
            }
        } else if (session.getAttribute("curPage") != null) {
            curPage = (long) session.getAttribute("curPage");
        }

        if (request.getParameter("pgSize") != null) {
            pgSize = Integer.parseInt(request.getParameter("pgSize"));
        } else if (session.getAttribute("pgSize") != null) {

            pgSize = (long) session.getAttribute("pgSize");
        }

        totalPage = totalPost / pgSize;
        if (totalPost % pgSize > 0) {
            totalPage = totalPage + 1;
        }

        if (curPage < 1) {
            curPage = 1;
        } else if (curPage > totalPage) {
            curPage = totalPage;
        }

        session.setAttribute("curPage", curPage);
        session.setAttribute("pgSize", pgSize);

        long endRow = curPage * pgSize;
        long beginRow = endRow - pgSize + 1;

        List<Board> boardList = boardMapper.selectFreePostsByType(type, beginRow, endRow);

        if (request.getParameter("value") != null && request.getParameter("sch_type") != null) {
            boardList = getSearchedBoards(request, beginRow, endRow, type);
        }

        BoardListVo data = new BoardListVo(boardList, curPage, pgSize, totalPage);
        return data;
    }

    private List<Board> getSearchedBoards(HttpServletRequest request, long beginRow, long endRow, int type) {
        String value = request.getParameter("value");
        String sch_type = request.getParameter("sch_type");

        int period = Integer.parseInt((request.getParameter("period") != null ? request.getParameter("period") : "0"));
        String nameOption;
        String keyword;
        List<Board> boardList = new ArrayList<Board>();

        if (value != null && sch_type != null) {
            BoardSearchVO bsvo = new BoardSearchVO(type, beginRow, endRow, sch_type, value);            
            
            boardList = boardMapper.selectBoardListSearch(bsvo);
            
        } else {
            nameOption = request.getParameter("nameOption");
            keyword = request.getParameter("keyword");
            if (nameOption.equals("title")) {
                nameOption = "TITLE";
            } else if (nameOption.equals("mNickname")) {
                nameOption = "M_NICKNAME";
            }

            boardList = boardMapper.selectBoardListByKeyword(nameOption, keyword, period, beginRow, endRow);
        }
        return boardList;
//        switch (value) {
//            case "0":
//                if (period == 0) {
//                    boardList = boardMapper.selectFreePostsByType(1, beginRow, endRow);
//                    break;
//                } else {
//                    boardList = boardMapper.selectBoardListByPeriod(period, beginRow, endRow);
//                    break;
//                }
//            case "2":
//                nameOption = request.getParameter("nameOption");
//                keyword = request.getParameter("keyword");
//                if (nameOption.equals("title")) {
//                    nameOption = "TITLE";
//                } else if (nameOption.equals("mNickname")) {
//                    nameOption = "M_NICKNAME";
//                }
//
//                boardList = boardMapper.selectBoardListByKeyword(nameOption, keyword, period, beginRow, endRow);
//                break;
//        }
    }

    @Override
    public BoardDetail getPost(long id) {
        BoardDetail boardDetail = new BoardDetail();
        Board board = boardMapper.selectPost(id);
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
