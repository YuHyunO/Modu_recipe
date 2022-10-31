package com.modu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;

public interface MembershipService {
    /* �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕1~�뜝�룞�삕5 �뜝�떥釉앹삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�떛�룞�삕 �뜝�뙗�눦�삕 */
    List<Member> selectMemberRankS();
    FollowListVo getFollowList(HttpServletRequest request, HttpSession session);
    String scrapService(long rId, String email, int mode);
    String followService(String targetEmail, String email, int mode);

    void removeMyFollow(int id);
}
