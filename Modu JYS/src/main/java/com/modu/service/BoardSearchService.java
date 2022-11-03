package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.board.BoardList;

public interface BoardSearchService {
    BoardList searchPosts(HttpServletRequest request, HttpSession session);
}
