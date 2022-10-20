package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.Member;
import com.modu.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class MembershipServiceImpl implements MembershipService {
	
	/* 마이페이지 탭1~5 관련 기능 중심으로 인터페이스 작성 */
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<Member> selectMemberRankS() {
		log.info("###########MembershipServiceImpl: " + memberMapper.selectMemberRank());
		return memberMapper.selectMemberRank();
	}
	
	@Override
	public List<FollowList> getFollowList(HttpServletRequest request, HttpSession session){
		String email = (String)session.getAttribute("email");
		int beginRow = 1;
		int endRow = 8;
		List<FollowList> followList = new ArrayList<FollowList>();
		
		List<FollowList> following = memberMapper.selectFollowee(email, beginRow, endRow);
		List<FollowList> follower = memberMapper.selectFollower(email, beginRow, endRow);
		
		for(FollowList item: following) {
			followList.add(item);
		}
		for(FollowList item: follower) {
			followList.add(item);
		}
		return followList;
	}
}
