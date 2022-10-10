package com.modu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("notice")
public class NoticeController {
	@GetMapping("/list")
	public String noticeList() {	
		return "notice/list";
	}
	
	@GetMapping("/detail")
	public String noticeDetail() {	
		return "notice/detail";
	}
	
	@GetMapping("/write")
	public String noticeWrite() {	
		return "notice/write";
	}
}
