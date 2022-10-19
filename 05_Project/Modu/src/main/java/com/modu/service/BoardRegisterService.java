package com.modu.service;

import java.util.List;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardNestedReply;

public interface BoardRegisterService {
	void addPost(Board board);
	void modifyPost(Board board,BoardFile boardFile);
	void removePost(long id,BoardFile boardFile);
	void addFile(BoardFile boardFile);
	long findPostId(String email);
	void removeFile(long id);
	void beforeRemoveFile(long fId);

}
