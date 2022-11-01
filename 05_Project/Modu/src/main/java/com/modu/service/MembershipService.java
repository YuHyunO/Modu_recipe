
package com.modu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;

public interface MembershipService {
    /* ���������� ��1~��5 �κ� ����� �������̽� �ۼ� */

    String scrapService(long rId, String email, int mode);
    String followService(String targetEmail, String email, int mode);

    List<Member> selectMemberRankS();
    FollowListVo getFollowList(HttpServletRequest request, HttpSession session);
    void removeMyFollow(int id);
}

















