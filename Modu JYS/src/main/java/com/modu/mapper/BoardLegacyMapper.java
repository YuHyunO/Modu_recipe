package com.modu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.board.BoardNestedReply;
import com.modu.domain.board.BoardReply;
import com.modu.domain.board.BoardReplyPicture;
import com.modu.domain.recipe.RecipeNestedReply;

public interface BoardLegacyMapper {
	void insertReply(BoardReply boardReply);
	void insertReplyPicture(BoardReplyPicture replyPicture);
	void insertNestedReply(BoardNestedReply nestedReply);
	
	void deleteReply(long id);
	void deleteNestedReply(long id);
	
	List<BoardReply> selectReply(long bId);
	List<BoardReply> selectReplyBy(@Param("bId")long bId, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<BoardReply> selectReplyOfMember(String email);
	List<BoardReply> selectReplyOfMemberBy(@Param("email")String email, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<BoardNestedReply> selectNestedReply(long brId);
	List<BoardNestedReply> selectNestedReplyBy(@Param("brId")long brId, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<BoardNestedReply> selectNestedReplyOfMember(String email);
	List<BoardNestedReply> selectNestedReplyOfMemberBy(@Param("email")String email, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	long selectReplyCount(long bId);
	long selectNestedReplyCount(long brId);
	long selectReplyCountByMember(String email);
}


