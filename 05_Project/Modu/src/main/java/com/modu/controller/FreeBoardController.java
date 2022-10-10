package com.modu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("freeboard")
public class FreeBoardController {
	@GetMapping("/list")
	public String boardList() {	
		return "freeboard/list";
	}
	
	@GetMapping("/detail")
	public String boardDetail() {	
		return "freeboard/detail";
	}
	
	@GetMapping("/write")
	public String boardWrite() {	
		return "freeboard/write";
	}
}
