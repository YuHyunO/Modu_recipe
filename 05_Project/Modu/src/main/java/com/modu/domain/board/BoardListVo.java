package com.modu.domain.board;

import java.util.List;

import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeListVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardListVo {
    private List<Board> boardList;
    private long curPage;
    private long pgSize;
    private long totalPage;
}
