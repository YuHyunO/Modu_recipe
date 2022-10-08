package com.modu.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardReplyPicture {
	private long id;
	private long brId;
	private String originalFile;
	private String saveFile;
}
