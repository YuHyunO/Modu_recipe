package com.modu.service;

import java.util.List;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardList;

public interface BoardFindingService {
	BoardList listingPosts (long pgSize, long curPage, int type);
	BoardDetail getPost(long id);
	
	
}
