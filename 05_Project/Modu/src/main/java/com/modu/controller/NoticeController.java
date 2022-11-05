package com.modu.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardListVo;
import com.modu.domain.board.BoardReply;
import com.modu.domain.board.BoardReplyList;
import com.modu.fileset.Path;
import com.modu.service.BoardFindingService;
import com.modu.service.BoardRegisterService;
import com.modu.service.BoardReplyService;
import com.modu.service.FileUploadService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("notice")
@AllArgsConstructor
public class NoticeController {
    @Autowired
    private BoardFindingService boardService;
    private BoardRegisterService boardRegisterService;
    private BoardReplyService boardReplyService;
    private FileUploadService service;
    
	@GetMapping("/list")
	public ModelAndView noticeList(HttpServletRequest request, HttpSession session) {
	    BoardListVo data = boardService.listingPosts(request, session, 0);
	    ModelAndView mv = new ModelAndView("notice/list", "data", data);	    
		return mv;
	}
	
	@GetMapping("/detail")
	public ModelAndView noticeDetail(long id,HttpServletRequest request, HttpServletResponse response) {
	    BoardDetail board = boardService.getPost(id, request, response);
        long beginRow = 1;
        long endRow = 6;
        Date gPD = board.getBoard().getPostDate();
        BoardReplyList list = boardReplyService.getReply(id,beginRow,endRow);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("notice/detail");
        mv.addObject("board", board);
        mv.addObject("list", list);
        return mv;
	}
	
	@PostMapping("addReply")
    public @ResponseBody void addReply(BoardReply boardReply, HttpServletRequest req){
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String nickname = (String) session.getAttribute("nickname");
        String profileImg = (String) session.getAttribute("profileImg");
        boardReply.setMEmail(email);
        boardReply.setMNickname(nickname);
        boardReply.setProfileImg(profileImg);
        boardReplyService.addReply(boardReply);
	}
	
	@PostMapping("moreViewReply")
    public @ResponseBody BoardReplyList getReply(long id,long endRow){
       long beginRow = endRow-5;
       BoardReplyList list = boardReplyService.getReply(id,beginRow, endRow);
       return list;
	}
	
	@PostMapping("removeReply")
    public @ResponseBody void removeReply(long id) {
        boardReplyService.removeReply(id);
    }
	
	@GetMapping("/write")
    public String noticeWrite(HttpSession session) {
        String email = (String)session.getAttribute("email");
        if(!email.equals("admin@admin.com")){
            return "redirect:list";
        }else {
            return "notice/write"; 
        }
    }
	
	@PostMapping("/write.do")
	public String write(Board board,BoardFile boardFile, MultipartFile file,HttpServletRequest req) {
	HttpSession session = req.getSession();
	String email = (String) session.getAttribute("email");
    String nickname = (String) session.getAttribute("nickname");
    String profileImg = (String) session.getAttribute("profileImg");
    board.setMEmail(email);
    board.setMNickname(nickname);
    board.setPostType(0);
    board.setProfileImg(profileImg);
    String ofname = file.getOriginalFilename();
    if(ofname != null) ofname = ofname.trim();
    if(ofname.length() != 0) {
        boardFile.setOriginalFile(ofname); 
        int idx = ofname.lastIndexOf(".");
        String ofheader = ofname.substring(0, idx);
        String ext = ofname.substring(idx);
        long ms = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(ofheader);
        sb.append("_");
        sb.append(ms);
        sb.append(ext);
        String saveFileName = sb.toString(); 
        boardFile.setSaveFile(saveFileName);
        long fsize = file.getSize();
        boardFile.setFileSize(fsize);
        int index = ofname.lastIndexOf(".");
        String fExtension = ofname.substring(index + 1);
        boardFile.setExtension(fExtension);
        service.saveStore(file);
        boardRegisterService.addPost(board);
        long bId = boardRegisterService.findPostId(board.getMEmail());
        boardFile.setBId(bId);
        boardRegisterService.addFile(boardFile);
    }else {
        boardRegisterService.addPost(board);
    }
	return "redirect:list";
	}
	
	@GetMapping("update.do")
    public ModelAndView update(long id,HttpServletRequest request, HttpServletResponse response) {
        BoardDetail board = boardService.getPost(id, request, response); 
        ModelAndView mv = new ModelAndView("freeboard/update", "board", board);
        return mv;
	    
	}
	@PostMapping("update.do")
    public String update(Board board,BoardFile boardFile, MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        long id = board.getId();
        long fId = boardFile.getId();
        boardService.getPost(id, request, response);
        String ofname = file.getOriginalFilename();
        if(ofname != null) ofname = ofname.trim();
           if(ofname.length() != 0) {
               boardRegisterService.beforeRemoveFile(fId);
               boardFile.setOriginalFile(ofname); 
               int idx = ofname.lastIndexOf(".");
               String ofheader = ofname.substring(0, idx);
               String ext = ofname.substring(idx);
               long ms = System.currentTimeMillis();
               StringBuilder sb = new StringBuilder();
               sb.append(ofheader);
               sb.append("_");
               sb.append(ms);
               sb.append(ext);
               String saveFileName = sb.toString(); 
               boardFile.setSaveFile(saveFileName);
               long fsize = file.getSize();
               boardFile.setFileSize(fsize);
               int index = ofname.lastIndexOf(".");
               String fExtension = ofname.substring(index + 1);
               boardFile.setExtension(fExtension);
               service.saveStore(file);
               boardRegisterService.modifyPost(board,boardFile);
           }else {
               boardRegisterService.modifyPost(board,boardFile);
           }
        return "redirect:list";
    }
	
	@GetMapping("delete.do")
    public String delete(long id,BoardFile boardFile) {
       boardRegisterService.removePost(id,boardFile);
       return "redirect:list";
    }
	
    @GetMapping("download.do")
    public ModelAndView download(String saveFile) {
       File file = new File(Path.FILE_STORE, saveFile);
       if(file.exists()) {
           return new ModelAndView("fileDownloadView", "downloadFile", file);
       }else {
           return new ModelAndView("redirect:list");
       }
    }
    
    @PostMapping("delFile")
    public @ResponseBody void del(String saveFile, long id) {
       File file = new File(Path.FILE_STORE, saveFile);
       if(file.exists()) file.delete();
       boardRegisterService.removeFile(id);
    }
}
