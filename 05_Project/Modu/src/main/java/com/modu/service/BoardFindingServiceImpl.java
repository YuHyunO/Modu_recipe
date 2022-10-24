package com.modu.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardList;
import com.modu.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardFindingServiceImpl implements BoardFindingService {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public BoardList listingPosts(HttpServletRequest request, HttpSession session) {
        String cpStr = request.getParameter("curPage");
        String psStr = request.getParameter("pgSize");
        int type = 1;
        long curPage = 1;
        long pgSize = 10;
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
		BoardList boardList = new BoardList(listPosts, totalPosts, listSize, pgSize, curPage, type,startPage,endPage,prev,next);
		return boardList;
	}
	public BoardDetail getPost(long id) {
		BoardDetail boardDetail = new BoardDetail();
		Board board = boardMapper.selectPost(id);
		List<BoardFile> boardFile = boardMapper.selectFile(id);
		boardDetail.setBoard(board);
		boardDetail.setBoardFile(boardFile);
		return boardDetail;
	}	

}
