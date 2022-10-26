package com.modu.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modu.domain.member.Scrap;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.mapper.RecipeLegacyMapper;
import com.modu.service.MembershipService;
import com.modu.service.RecipeFindingService;
import com.modu.service.RecipeRegisterService;
import com.modu.service.RecipeSearchService;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("recipe")
public class RecipeController {
    @Autowired
    private RecipeFindingService recipeFindingService;
    @Autowired
    private RecipeRegisterService recipeRegisterService;
    @Autowired
    private RecipeSearchService searchService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private RecipeLegacyMapper recipeLegacyMapper;


    @GetMapping("/list")
    public ModelAndView recipeList(HttpServletRequest request, HttpSession session) {
        RecipeListVo data = searchService.searchRecipe(request, session);
        ModelAndView mv = new ModelAndView("recipe/list", "data", data);
        return mv;
    }
    
    @GetMapping("/list.do")
    public @ResponseBody RecipeListVo updateRecipeList(HttpServletRequest request, HttpSession session){
        RecipeListVo data = searchService.searchRecipe(request, session);
        return data;
    }

    @GetMapping("/write")
    public String recipeWrite() {
        return "recipe/write";
    }

    @ResponseBody
    @PostMapping("/write")
    public String submit(HttpServletRequest request, HttpSession session) {
        log.info("########write: " + request.getParameter("food"));
        //recipeRegisterService.registerRecipe(request, session);
        return "redirect:/";
    }
    
    @ResponseBody
    @PostMapping("/register")
    public HashMap<String, Object> register(
            HttpServletRequest request,
            HttpSession session,
            @RequestParam ArrayList<MultipartFile> files, 
            @RequestParam ArrayList<String> mainItems,
            @RequestParam ArrayList<String> subItems,
            @RequestParam ArrayList<String> directions,
            @RequestParam ArrayList<String> tags) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        log.info("#RecipeController Upload");
        if((String)session.getAttribute("email") == null) {
            map.put("msg", "�α��� �� �̿� ���ּ���");
        } else {
            recipeRegisterService.registerRecipe(request, session, files, mainItems, subItems, directions, tags);
            log.info("#####1" + tags);
            map.put("msg", "���񽺷� ������ ���� ����");
        } 
        return map;
    }
    
    @GetMapping("/update")
    public ModelAndView RecipeRead(long id) {
        RecipeDetail recipeDetail = recipeFindingService.RecipeRead(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipe/update");
        mv.addObject("rs", recipeDetail);
        mv.addObject("id", id);
        return mv;
    }
    /* Mypage ���� �ۼ��� ������ ��ϵǸ� ������ų session�� �̿��� read
    @GetMapping("/update")
    public ModelAndView RecipeRead(HttpSession session) {//
        String ids = (String)session.getAttribute("id");
        long id = Integer.parseInt(ids);
        long rId = id;
        RecipeDetail recipeDetail = recipeFindingService.RecipeRead(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipe/update");
        mv.addObject("rs", recipeDetail);
        mv.addObject("id", id);
        return mv;
    }
    */
  
//  @PostMapping("/update")
//  public String  updateRecipe(RecipeDetail recipeDetail,
//          HttpServletRequest request,
//            HttpSession session,
//            ArrayList<MultipartFile> files,
//            ArrayList<String> mainItems,
//            ArrayList<String> subItems,
//            ArrayList<String> directions,
//            ArrayList<String> tags) {
//      recipeRegisterService.updateRecipe(recipeDetail, request, 
//              session, files, mainItems,subItems,directions, tags);
//      return "redirect:detail";
//  }
//    
//    @ResponseBody
//    @PostMapping("/update")
//    public String updateRecipe(
//            HttpServletRequest request,
//            HttpSession session,
//            @RequestParam ArrayList<MultipartFile> files, 
//            @RequestParam ArrayList<String> mainItems,
//            @RequestParam ArrayList<String> subItems,
//            @RequestParam ArrayList<String> directions,
//            @RequestParam ArrayList<String> tags) {
//        Recipe recipe = new Recipe();
//        Ingredient ingredient = new Ingredient();
//        Direction direction = new Direction();
//        RecipeTag recipeTag = new RecipeTag();
//        recipeRegisterService.updateRecipe(id,request,session,files,mainItems,subItems,directions,tags);
//       return "redirect:detail";
//    }
    
    @GetMapping("/delete")
    public String deleteRecipe(long id, HttpServletRequest request) { //
        HttpSession session = request.getSession();
        String getId = request.getParameter("id");
        id = Integer.parseInt(getId);
        log.info("#####99: " + id);
        recipeRegisterService.recipeDelete(id);
        return "redirect:/";
    }
    
    @GetMapping("/detail")
    public ModelAndView recipeDetail(HttpSession session) {
        long id = 150;
        String email = (String)session.getAttribute("email");
        RecipeDetail recipeDetail = recipeFindingService.findRecipedetails(id);
        String starPoint = recipeFindingService.getStarPoint(recipeDetail);
        List<RecipeReplyList> selectReply = recipeRegisterService.findRecipeReply(id);
        boolean scrapState = false;
        if(email != null) {
            if (recipeFindingService.getScrap(id, email) == null) {
                //��ũ�� �� �ƴ�
            } else {
                scrapState = true;
            }
        }
        long replyCount = recipeLegacyMapper.selectReplyCount(id);
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("recipe/detail");
        mv.addObject("rec", recipeDetail);
        mv.addObject("rep", selectReply);
        mv.addObject("id", id);
        mv.addObject("starPoint", starPoint);
        mv.addObject("scrapState", scrapState);
        mv.addObject("replyCount", replyCount);
        return mv;
    }

    @PostMapping("/insert.do")
    public @ResponseBody String insertReply(RecipeReply recipeReply) {
        String result = recipeRegisterService.registerReply(recipeReply);
        log.info("#recipeReply" + recipeReply);
        return result;
    }

    @GetMapping("/del.do")
    public String deleteReply(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // System.out.println("session"+session);
        String paramId = request.getParameter("id");
        long id = Long.parseLong(paramId);
        // System.out.println("id"+id);
        recipeRegisterService.delete(id);
        return "redirect:detail";
    }
    /*
     * ->����/�̿�
     * 
     * @PostMapping("/insertRreply.do") public @ResponseBody String
     * insertNestedReply (RecipeNestedReply recipeNestedReply){ String result =
     * recipeRegisterService.registerNestedReply(recipeNestedReply);
     * log.info("#recipeNestedReply" +recipeNestedReply); return result; }
     */
    
    @ResponseBody
    @PostMapping("/scrap/insert")
    public HashMap<String, Object> insertScrap(HttpServletRequest request, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String id = (String)request.getParameter("id");
        String email = (String)session.getAttribute("email");
        String msg;
        long rId = Long.parseLong(id);
        
        if (email == null) {
            map.put("error", "��ũ�� ����� �α��� �� �̿��Ͻ� �� �ֽ��ϴ�.");
            return map;
        } else {
            msg = membershipService.scrapService(rId, email, 1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }
    
    @ResponseBody
    @PostMapping("/scrap/delete")
    public HashMap<String, Object> deleteScrap(HttpServletRequest request, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String id = (String)request.getParameter("id");
        String email = (String)session.getAttribute("email");
        String msg;
        long rId = Long.parseLong(id);
        
        if (email == null) {
            map.put("error", "��ũ�� ����� �α��� �� �̿��Ͻ� �� �ֽ��ϴ�.");
            return map;
        } else {
            msg = membershipService.scrapService(rId, email, -1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }
    
    @ResponseBody
    @PostMapping("/follow/insert")
    public HashMap<String, Object> insertFollow(HttpServletRequest request, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String targetEmail = request.getParameter("targetEmail");
        String email = (String)session.getAttribute("email");
        String msg;
        String result;
        
        if (email == null) {
            map.put("error", "ģ���߰��� �α��� �� �̿��Ͻ� �� �ֽ��ϴ�.");
            return map;
        } else {
            msg = membershipService.followService(targetEmail, email, 1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }
    
    @ResponseBody
    @PostMapping("/follow/delete")
    public HashMap<String, Object> deleteFollow(HttpServletRequest request, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String targetEmail = request.getParameter("targetEmail");
        String email = (String)session.getAttribute("email");
        String msg;
        String result;
        
        if (email == null) {
            map.put("error", "ģ���߰��� �α��� �� �̿��Ͻ� �� �ֽ��ϴ�.");
            return map;
        } else {
            msg = membershipService.followService(targetEmail, email, -1);
            map.put("user", email);
            map.put("msg", msg);
            return map;
        }
    }
    
    @ResponseBody
    @PostMapping("/follow/check")
    public HashMap<String, Object> checkFollow(HttpServletRequest request, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String targetEmail = request.getParameter("targetEmail");
        String email = (String)session.getAttribute("email");
        String msg;
        String result;
        
        if (email != null) {
            msg = membershipService.followService(targetEmail, email, 0);
            map.put("user", email);
            map.put("state", msg); //�ȷο� ���񽺿��� ģ���̸� true �ƴϸ� false ����
        } else {
            msg = "����ڰ� �α��� ������ ����";
            map.put("state", "false");
        }
        return map;
    }
}
