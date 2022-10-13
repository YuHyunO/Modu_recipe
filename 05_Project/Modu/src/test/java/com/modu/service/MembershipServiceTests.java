package com.modu.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.member.Member;
import com.modu.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MembershipServiceTests {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void selectMemberRankS() {
		 memberMapper.selectMemberRank();
	}
}
