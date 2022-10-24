package com.modu.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.member.FollowList;
import com.modu.domain.member.FollowListVo;
import com.modu.domain.member.Member;
import com.modu.mapper.MemberMapper;
import com.modu.mapper.RecipeLegacyMapper;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class MembershipServiceImpl implements MembershipService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
    private RecipeLegacyMapper recipeLegacyMapper;
	@Autowired
    private RecipeFindingService recipeFindingService;
	
	@Override
	public List<Member> selectMemberRankS() {
		log.info("###########MembershipServiceImpl selectMemberRankS(): " + memberMapper.selectMemberRank());
		return memberMapper.selectMemberRank();
	}
	
	@Override
	public FollowListVo getFollowList(HttpServletRequest request, HttpSession session) {
	    String email = (String)session.getAttribute("email");
	    int currentPage = 1;
	    int pageSize = 8;
        int totalPage;
        int totalPost = 10;
        
        if(request.getParameter("currentPage") != null) {
            if(session.getAttribute("followingCP") != null) {
                currentPage = (int)session.getAttribute("followingCP");
            }
            String param = request.getParameter("currentPage");
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch(NumberFormatException nfe) {
                switch(param) {
                    case "pre": currentPage = currentPage - 1; break;
                    case "next": currentPage = currentPage + 1;                         
                }
            }
        }else if(session.getAttribute("followingCP") != null) {
            currentPage = (int)session.getAttribute("followingCP");
        }       
        
        totalPage = totalPost/pageSize;
        if(totalPost % pageSize > 0) {
            totalPage = totalPage + 1;
        }       
        
        if(currentPage<1) { 
            currentPage = 1;
        }else if(currentPage>totalPage) { 
            currentPage = totalPage;
        }
        
        int endRow = currentPage*pageSize;
        int beginRow = endRow-pageSize+1;
        
        session.setAttribute("followingCP", currentPage);     	    
	    
	    
	    
	    
	    
	    
	    FollowListVo data = new FollowListVo();
	    
	    
	    return null;
	}
	   
	public void unfollowFriend(Long id) {
	    memberMapper.deleteFollow(id);
	}

    @Override
    public String scrapService(long rId, String email, int mode) {
        String msg;
        if (mode == 1) { // 스크랩 추가
            if (recipeFindingService.getScrap(rId, email) == null) {
                recipeLegacyMapper.insertScrap(email, rId);
                msg = "스크랩 되었습니다.";
            } else {
                msg = "이미 스크랩 중 입니다.";
            }
        } else { // 스크랩 해제
            if (recipeFindingService.getScrap(rId, email) == null) {
                msg = "스크랩 중이 아닙니다.";
            } else {
                recipeLegacyMapper.deleteScrap(rId, email);
                msg = "스크랩이 해제 되었습니다.";
            }
        }
        return msg;
    }
}
