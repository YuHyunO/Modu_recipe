package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardDetailNextPrev;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardNestedReply;
import com.modu.domain.board.BoardReply;
import com.modu.domain.recipe.RecipeNestedReply;

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
	
	//�����Խ��� ����¡�� ���� select �߰�
	List<Board> selectFreePostsByType(@Param("type") int type, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
    long selectBoardCountByPeriod(int period);
    long selectBoardCountByIngredient(String keyword, int period);
    long selectBoardCountByKeyword(String nameOption, String keyword, int period);
    List<Board> selectBoardListBy(long beginRow, long endRow);
    List<Board> selectBoardListByPeriod(int period, long beginRow, long endRow);
    List<Board> selectBoardListByKeyword(@Param("nameOption")String nameOption, @Param("keyword")String keyword,
            @Param("period")int period, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	BoardDetailNextPrev selectBoardNextPrev(@Param("id")long id);


	
	
}
