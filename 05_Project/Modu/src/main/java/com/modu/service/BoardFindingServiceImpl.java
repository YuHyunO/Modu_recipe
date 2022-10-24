package com.modu.service;


import java.util.List;

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
	public BoardList listingPosts(long pgSize, long curPage, int type) {
		
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
		List<Board> listPosts = boardMapper.selectPostsByType(type, beginRow, endRow);
		 
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
