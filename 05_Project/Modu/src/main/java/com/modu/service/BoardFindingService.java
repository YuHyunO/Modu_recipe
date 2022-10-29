package com.modu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardList;
import com.modu.domain.board.BoardListVo;

public interface BoardFindingService {
	BoardListVo listingPosts(HttpServletRequest request, HttpSession session);
	BoardDetail getPost(long id,HttpServletRequest request,HttpServletResponse response);
	
	
}
