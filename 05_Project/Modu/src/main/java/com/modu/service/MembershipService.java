package com.modu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;

public interface MembershipService {
	
	List<Member> selectMemberRankS();	
	FollowListVo getFollowList(HttpServletRequest request, HttpSession session);	
	void unfollowFriend(Long id); //HttpServletRequest request
	
}
