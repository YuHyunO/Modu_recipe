package com.modu.domain.board;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDetail {
	private Board board;
	private List<BoardFile> boardFile;
	private List<BoardReply> boardReply;
	private List<BoardNestedReply> boardNestedReply;
	private BoardDetailNextPrev boardDetailNextPrev;
}
