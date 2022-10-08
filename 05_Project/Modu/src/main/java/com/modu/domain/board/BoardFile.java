package com.modu.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardFile {
	private long id;
	private long bId;
	private String originalFile;
	private String saveFile;
	private String extension;
	private long fileSize;
}
