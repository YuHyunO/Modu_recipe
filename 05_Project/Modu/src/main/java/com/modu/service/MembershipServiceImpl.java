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
import com.modu.mapper.RecipeLegacyMapper;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class MembershipServiceImpl implements MembershipService {
	
	/* ���������� ��1~5 ���� ��� �߽����� �������̽� �ۼ� */
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
    private RecipeLegacyMapper recipeLegacyMapper;
	@Autowired
    private RecipeFindingService recipeFindingService;
	
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

    @Override
    public String scrapService(long rId, String email, int mode) {
        String msg;
        if (mode == 1) { // ��ũ�� �߰�
            if (recipeFindingService.getScrap(rId, email) == null) {
                recipeLegacyMapper.insertScrap(email, rId);
                msg = "��ũ�� �Ǿ����ϴ�.";
            } else {
                msg = "�̹� ��ũ�� �� �Դϴ�.";
            }
        } else { // ��ũ�� ����
            if (recipeFindingService.getScrap(rId, email) == null) {
                msg = "��ũ�� ���� �ƴմϴ�.";
            } else {
                recipeLegacyMapper.deleteScrap(rId, email);
                msg = "��ũ���� ���� �Ǿ����ϴ�.";
            }
        }
        return msg;
    }

    @Override
    public String followService(String targetEmail, String email, int mode) {
        String msg;
        if (mode == 1) {
            FollowList result = recipeFindingService.getFollower(targetEmail, email);
            log.info("#followServiceImpl: " + result);
        }
        return null;
    }
}
