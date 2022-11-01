
package com.modu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;

public interface MembershipService {
    /* 마이페이지 탭1~탭5 부분 기능의 인터페이스 작성 */

    String scrapService(long rId, String email, int mode);
    String followService(String targetEmail, String email, int mode);

    List<Member> selectMemberRankS();
    FollowListVo getFollowList(HttpServletRequest request, HttpSession session);
    void removeMyFollow(int id);
}

















