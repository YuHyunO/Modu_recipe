package com.modu.domain.member;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowListVo {
    private List<FollowList> followList;
    private int currentPage;
    private int pageSize;
    private int totalPage;
}
