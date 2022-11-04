package com.modu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardFile;
import com.modu.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class BoardRegisterServiceImpl implements BoardRegisterService {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void addPost(Board board) {
		boardMapper.insertPost(board);
	}
	@Override
	public void modifyPost(Board board,BoardFile boardFile) {
		long fileSize = boardFile.getFileSize();
		boardMapper.updatePost(board);
		if(fileSize != 0) {
			long id = board.getId();
			List<BoardFile> files = boardMapper.selectFile(id);
			int filesSize = files.size();
				boardFile.setBId(id);
				if(filesSize>0) {
					boardMapper.updateFile(boardFile);
				}else {
					boardMapper.insertFile(boardFile);
				}
				boardMapper.updateFile(boardFile);
		}
	}
	@Override
	public void removePost(long id,BoardFile boardFile) {
		long fileSize = boardFile.getFileSize();
		boardMapper.deletePost(id);
		if(fileSize != 0) {
			boardMapper.deleteFile(id);
		}	
	}

	@Override
	public void addFile(BoardFile boardFile) {
		boardMapper.insertFile(boardFile);	
	}

	@Override
	public long findPostId(String email) {
		long bId = boardMapper.selectPostId(email);
		return bId; 
	}
    @Override
    public void removeFile(long id) {
        boardMapper.deleteFile(id);
        
    }
    @Override
    public void beforeRemoveFile(long fId) {
        long id = fId;
        boardMapper.deleteFile(id);
        
    }
	
	



}
