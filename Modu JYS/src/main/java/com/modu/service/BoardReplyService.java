package com.modu.service;

import java.util.List;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardReply;
import com.modu.domain.board.BoardReplyList;

public interface BoardReplyService {
	BoardReplyList getReply(long id, long endRow, long beginRow);
	long countReplys(long bId);
	void addReply(BoardReply boardReply);
	void removeReply(long id);
}
