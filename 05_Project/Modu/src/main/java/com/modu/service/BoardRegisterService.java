package com.modu.service;

import com.modu.domain.board.Board;

public interface BoardRegisterService {
	/* 게시판 등록,수정,삭제 관련 기능 중심으로 인터페이스 작성 */
	void addPost();
	void modifyPost();
	void deletePost();
}
