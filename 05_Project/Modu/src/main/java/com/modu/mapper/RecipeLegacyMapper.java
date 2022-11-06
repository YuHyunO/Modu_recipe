package com.modu.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.Rating;
import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.domain.recipe.RecipeReplyPhoto;


public interface RecipeLegacyMapper {
	void insertReply(RecipeReply recipeReply);
	long insertReplyPhoto(RecipeReplyPhoto replyPhoto);
	void insertNestedReply(RecipeNestedReply nestedReply);
	void insertRating(@Param("email")String email, @Param("rId")long rId, @Param("star")int star);
	void insertScrap(@Param("email")String email, @Param("rId")long rId);		
	void updateRating(@Param("id")long id, @Param("star")int star);	
	void deleteReply(long id);
	void deleteNestedReply(long id);
	void deleteRating(long id);
	void deleteScrap(@Param("rId")long rid, @Param("email")String email);
	
	
	long selectReplyId(String email);
	List<RecipeReplyList> selectReply(long rId); 
	List<RecipeReplyList> selectReplyBy(@Param("rId")long rId, @Param("beginRow")long beginRow, @Param("endRow")long endRow); //대댓글 유무 추가해야함 (수정필요)
	List<RecipeReplyList> selectReplyOfMember(String email);
	List<RecipeReplyList> selectReplyOfMemberBy(@Param("email")String email, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<RecipeNestedReply> selectNestedReply(long rrId);
	List<RecipeNestedReply> selectNestedReplyBy(@Param("rrId")long rrId, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	List<RecipeNestedReply> selectNestedReplyOfMember(String email);
	List<RecipeNestedReply> selectNestedReplyOfMemberBy(@Param("email")String email, @Param("beginRow")long beginRow, @Param("endRow")long endRow);
	long selectReplyCount(long rId);
	long selectNestedReplyCount(long rrId);
	long selectReplyCountByMember(String email);
	
	Scrap selectScrapByRecipeId(@Param("rId")long rId, @Param("mEmail")String mEmail);
	long recipeViewCount(long id);
	long viewReply(long id);
	RecipeReply selectRecipeReplyByRecipeIdAndEmail(RecipeReply recipeReply);
}
