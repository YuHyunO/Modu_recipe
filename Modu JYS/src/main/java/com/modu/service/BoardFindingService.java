package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardListVo;

public interface BoardFindingService {
    BoardListVo listingPosts(HttpServletRequest request, HttpSession session, int type);
    
    
    BoardDetail getPost(long id);
    
    //공지사항 게시글 상세페이지
    Board getBoard(long id);

}
