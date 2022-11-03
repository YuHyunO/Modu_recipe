package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetailNextPrev;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardSearchVO;

public interface BoardMapper {
	void insertPost(Board board);
	void insertFile(BoardFile boardFile);
	
	void updatePost(Board board);
	void updateFile(BoardFile boardFile);
	void updatePostHits(long id);
	void updateReplyCount(@Param("id")long id, @Param("count")long count);
	void updateFileEmpty(long id);
	
	void deletePost(long id);
	void deleteFile(long id);	
	
	long selectPostId(String email);
	Board selectPost(long id);
	List<BoardFile> selectFile(long id);
	List<Board> selectPostsByType(@Param("type") int type, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<Board> selectPostsByMember(@Param("email") String email, @Param("beginRow") long beginRow, @Param("endRow") long endRow);
	long selectPostCount();
	long selectPostCountByType(int type);
	long selectPostCountByMember(String email);
	
	//자유게시판 페이징을 위한 select 추가
	List<Board> selectFreePostsByType(@Param("type") int type, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	
	List<Board> selectBoardListSearch(BoardSearchVO bsvo);
	
	
    long selectBoardCountByPeriod(@Param("period")int period);
    long selectBoardCountByKeyword(@Param("nameOption")String nameOption,@Param("keyword")String keyword,@Param("period")int period);
    List<Board> selectBoardListBy(@Param("beginRow")long beginRow,@Param("endRow")long endRow);
    List<Board> selectBoardListByPeriod(@Param("period")int period,@Param("beginRow")long beginRow,@Param("endRow")long endRow);
    List<Board> selectBoardListByKeyword(@Param("nameOption")String nameOption, @Param("keyword")String keyword,
            @Param("period")int period, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	BoardDetailNextPrev selectBoardNextPrev(@Param("id")long id);


	
	
}
