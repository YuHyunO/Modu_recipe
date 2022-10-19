package com.modu.domain.board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardReplyList {
		List<BoardReply> list;
		long beginRow;
		long endRow;
}
