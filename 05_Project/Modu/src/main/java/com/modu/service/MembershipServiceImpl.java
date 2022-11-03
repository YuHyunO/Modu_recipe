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
	
	/* 마이페이지 탭1~5 관련 기능 중심으로 인터페이스 작성 */
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
    private RecipeLegacyMapper recipeLegacyMapper;
	@Autowired
    private RecipeFindingService recipeFindingService;
	
	@Override
	public List<Member> selectMemberRankS() {
		return memberMapper.selectMemberRank();
	}
	
	@Override
	public FollowListVo getFollowList(HttpServletRequest request, HttpSession session) {
	    String email = (String)session.getAttribute("email");	   
	    int currentPage = 1;
	    int pageSize = 8;
	    int totalPost;	
	    int totalPage;
	    int state = 1;
	    try {
	        state = Integer.parseInt(request.getParameter("state"));
	    }catch(NumberFormatException nfe) {}
	    
	    if (state == 1) {
	        totalPost = memberMapper.selectFollowerCount(email);	        
	    }else if (state == 2) {
	        totalPost = memberMapper.selectFollowerCount(email);	       
	    }else {
	        totalPost = memberMapper.selectFollowerCount(email);
	    }
	    
	    if(request.getParameter("currentPage") != null) {
            String param = request.getParameter("currentPage");
           
            try {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }catch(NumberFormatException nfe) {
                switch(param) {
                    case "pre": currentPage = currentPage - 1; break;
                    case "next": currentPage = currentPage + 1;                         
                }
            }
        }else {
            if(session.getAttribute("followCurpage") != null) {        
                currentPage = (int)session.getAttribute("followCurpage");
            }
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
        
        List<FollowList> followList = new ArrayList<FollowList>();
        
        if(state == 1) {
            followList = memberMapper.selectFollowing(email, beginRow, endRow);
        }else if(state == 2) {
            followList = memberMapper.selectFollower(email, beginRow, endRow);
        }else {
            followList = memberMapper.selectFollowing(email, beginRow, endRow);
        }
        
        session.setAttribute("followCurpage", currentPage);  	    
	    
        FollowListVo data = new FollowListVo(followList, currentPage, totalPage);
        
	    return data;
	}

    @Override
    public String scrapService(long rId, String email, int mode) {
        String msg;
        if (mode == 1) { // 북마크 추가
            if (recipeFindingService.getScrap(rId, email) == null) {
                recipeLegacyMapper.insertScrap(email, rId);
                msg = "북마크 되었습니다.";
            } else {
                msg = "이미 북마크 중 입니다.";
            }
        } else { // 북마크 해제
            if (recipeFindingService.getScrap(rId, email) == null) {
                msg = "북마크 중이 아닙니다.";
            } else {
                recipeLegacyMapper.deleteScrap(rId, email);
                msg = "북마크가 해제 되었습니다.";
            }
        }
        return msg;
    }

    @Override
    public String followService(String targetEmail, String email, int mode) {
        String msg;
        if (mode == 1) {
            FollowList result = recipeFindingService.getFollower(email, targetEmail);
            log.info(targetEmail + " " +email);
            log.info("#followServiceImpl: " + result);
            if (result == null) { // 현재 해당 대상을 팔로우하고 있지 않을 경우(데이터가 없을 경우)
                log.info("#followServiceImpl 인서트 작동");
                memberMapper.insertFollow(email, targetEmail);
                msg = "친구 추가에 성공 하였습니다.";
            } else {
                msg = "이미 친구목록에 있습니다.";
            }
        } else if (mode == -1) {
            memberMapper.deleteFollow(email, targetEmail);
            msg = "친구 목록에서 삭제 하였습니다.";
        } else {
            FollowList result = recipeFindingService.getFollower(email, targetEmail);
            if (result == null) { // 현재 해당 대상을 팔로우하고 있지 않을 경우(데이터가 없을 경우)
                msg = "false";
            } else { // 현대 해당 대상을 팔로우하고 있을 경우
                msg = "true";
            }
        }
        return msg;
    }
    
    @Override
    public void removeMyFollow(int id) {
        memberMapper.deleteMyFollow(id); 
    }
}
