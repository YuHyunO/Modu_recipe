package com.modu.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.board.Board;
import com.modu.domain.board.BoardDetail;
import com.modu.domain.board.BoardFile;
import com.modu.domain.board.BoardList;
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
@RequestMapping("freeboard")
@AllArgsConstructor
public class FreeBoardController {
    @Autowired
    private BoardFindingService boardService;
    private BoardRegisterService boardRegisterService;
    private BoardReplyService boardReplyService;
    private FileUploadService service;

    @GetMapping("/list")
    public ModelAndView boardList(HttpServletRequest request, HttpSession session) {
        String cpStr = request.getParameter("curPage");
        String psStr = request.getParameter("pgSize");
        int type = 1;
        long curPage = 1;
        long pgSize = 10;
        // (1) cp
        
        if (cpStr == null) {
            Object cpObj = session.getAttribute("curPage");
            if (cpObj != null) {
                curPage = (Long) cpObj;
            }
        } else {
            cpStr = cpStr.trim();
            curPage = Long.parseLong(cpStr);
        }
        session.setAttribute("curPage", curPage);

        // (2) ps

        if (psStr == null) {
            Object psObj = session.getAttribute("pgSize");
            if (psObj != null) {
                pgSize = (Long) psObj;
            }
        } else {
            psStr = psStr.trim();
            long psParam = Long.parseLong(psStr);

            Object psObj = session.getAttribute("pgSize");
            if (psObj != null) {
                long psSession = (Long) psObj;
                if (psSession != psParam) {
                    curPage = 1;
                    session.setAttribute("curPage", curPage);
                }
            } else {
                if (pgSize != psParam) {
                    curPage = 1;
                    session.setAttribute("curPage", curPage);
                }
            }
            pgSize = psParam;
        }
        session.setAttribute("pgSize", pgSize);
        BoardList list = boardService.listingPosts(pgSize, curPage, type);
        ModelAndView mv = new ModelAndView("freeboard/list", "list", list);
        return mv;
    }
    

    @GetMapping("/detail")
    public ModelAndView boardDetail(long id) {
        BoardDetail board = boardService.getPost(id);
        long beginRow = 1;
        long endRow = 6;
        log.info("#3211 " + board.getBoard().getPostDate());
        Date gPD = board.getBoard().getPostDate();
    
        log.info("#3212 " + gPD);
        //SimpleDateFormat board.getBoard().getPostDate() = new SimpleDateFormat("MM-dd hh:mm"); 
        //gPD.format(new Date());
        //board.getBoard().setPostDate();
        BoardReplyList list = boardReplyService.getReply(id,beginRow,endRow);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("freeboard/detail");
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
    @GetMapping("removeReply")
    public @ResponseBody String removeReply(long id) {
        boardReplyService.removeReply(id);
        return "redirect:list";
    }
    @GetMapping("/write")
    public String boardWrite() {
        return "freeboard/write";
    }
    @PostMapping("write.do")
     public String write(Board board,BoardFile boardFile, MultipartFile file,HttpServletRequest req) {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String nickname = (String) session.getAttribute("nickname");
        String profileImg = (String) session.getAttribute("profileImg");
        board.setMEmail(email);
        board.setMNickname(nickname);
        board.setPostType(1);
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
     public ModelAndView update(long id) {
         BoardDetail board = boardService.getPost(id); 
         ModelAndView mv = new ModelAndView("freeboard/update", "board", board);
         return mv;
     }
     @PostMapping("update.do")
     public String update(Board board,BoardFile boardFile, MultipartFile file) {
         long id = board.getId();
         long fId = boardFile.getId();
         boardService.getPost(id);
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
         log.info("#del con 1   " + id );
        boardRegisterService.removePost(id,boardFile);
        log.info("#del con 2");
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
        log.info("#101 :" + file + "  " + id + "  " + saveFile );
        boardRegisterService.removeFile(id);
    }
    
}
