package com.modu.service;

import java.util.List;

import com.modu.domain.recipe.RecipeList;

public interface RecipeFindingService {
	/* ������ ��ȸ,�˻� ���� ��� �߽����� �������̽� �ۼ� */
	List<RecipeList> selectRecipeListByBestHits(long beginRow, long endRow);
}
