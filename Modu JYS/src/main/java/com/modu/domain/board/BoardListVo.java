package com.modu.domain.board;

import java.util.List;

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
