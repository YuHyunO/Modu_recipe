package com.modu.domain.board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardList {
	List<Board> list;
	long totalPosts;
	long listSize;
	long pgSize;
	long curPage;
	int type;
	long startPage;
	long endPage;
	boolean prev;
	boolean next;
}
