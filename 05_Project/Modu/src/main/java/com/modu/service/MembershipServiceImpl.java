package com.modu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.member.Member;
import com.modu.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class MembershipServiceImpl implements MembershipService {
	/* ȸ�� ��ȸ,���������� ���� ��� �߽����� �������̽� �ۼ� */
	@Autowired
	private MemberMapper memberMapper;
	
	public List<Member> selectMemberRankS() {
		log.info("###########MembershipServiceImpl: " + memberMapper.selectMemberRank());
		return memberMapper.selectMemberRank();
	}
}
