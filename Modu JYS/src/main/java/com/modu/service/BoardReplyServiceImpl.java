package com.modu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardList;
import com.modu.domain.board.BoardReply;
import com.modu.domain.board.BoardReplyList;
import com.modu.mapper.BoardLegacyMapper;
import com.modu.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardReplyServiceImpl implements BoardReplyService {
	@Autowired
	BoardLegacyMapper boardLegacyMapper;
	@Autowired
	BoardMapper boardMapper;
	@Override
	public long countReplys(long bId) {
		return boardLegacyMapper.selectReplyCount(bId);
	}
	@Override
	public BoardReplyList getReply(long id,long beginRow,long endRow) {
		long bId = id;
		List<BoardReply> boardReply = boardLegacyMapper.selectReplyBy(bId, beginRow, endRow);
		BoardReplyList boardReplyList = new BoardReplyList(boardReply,beginRow, endRow);
		return boardReplyList;
	}
	@Override
	public void addReply(BoardReply boardReply) {
		boardLegacyMapper.insertReply(boardReply);	
	}
    @Override
    public void removeReply(long id) {
        boardLegacyMapper.deleteReply(id);
    }
}
