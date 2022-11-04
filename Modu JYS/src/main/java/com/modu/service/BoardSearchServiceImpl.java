package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.board.BoardList;
import com.modu.mapper.BoardMapper;

@Service
public class BoardSearchServiceImpl implements BoardSearchService {
    @Autowired
    private BoardMapper boardMapper;
    
    @Override
    public BoardList searchPosts(HttpServletRequest request, HttpSession session) {
         
        return null;
    }

}
