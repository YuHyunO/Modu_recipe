package com.modu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.modu.domain.recipe.Direction;
import com.modu.domain.recipe.Ingredient;
import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeDetail;
import com.modu.domain.recipe.RecipeTag;
import com.modu.fileset.Path;
import com.modu.mapper.MemberMapper;
import com.modu.mapper.RecipeMapper;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.modu.domain.recipe.RecipeNestedReply;
import com.modu.domain.recipe.RecipeReply;
import com.modu.domain.recipe.RecipeReplyList;
import com.modu.mapper.RecipeLegacyMapper;




@Log4j
@Controller
@Service
public class RecipeRegisterServiceImpl implements RecipeRegisterService {
    
    private Recipe recipe = new Recipe();
    private Ingredient ingredient = new Ingredient();
    private Direction direction = new Direction();
    private RecipeTag Tag = new RecipeTag();
    
    private ArrayList<String> fileInfoList = new ArrayList<String>();
	
	@Autowired
	private RecipeLegacyMapper recipeLegacyMapper;
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired FileUploadService fileUploadService;

	@Override
	public void registerRecipe(
	        HttpServletRequest request,
            HttpSession session,
            ArrayList<MultipartFile> files, 
            ArrayList<String> mainItems,
            ArrayList<String> subItems,
            ArrayList<String> directions,
            ArrayList<String> tags) {
	    
//	    // 데이터 확인
//	    log.info("#registerRecipe email: " + (String)session.getAttribute("email"));
//        log.info("#registerRecipe nickname: " + (String)session.getAttribute("nickname"));
//        log.info("#registerRecipe profileImg: " + (String)session.getAttribute("profileImg"));
//        log.info("#registerRecipe title: " + request.getParameter("title"));
//        log.info("#registerRecipe info: " + request.getParameter("info"));
//        log.info("#registerRecipe food: " + request.getParameter("food"));
//        log.info("#registerRecipe sort: " + request.getParameter("sort"));
//        log.info("#registerRecipe ingredient: " + request.getParameter("ingredient"));
//        log.info("#registerRecipe serving: " + request.getParameter("serving"));
//        log.info("#registerRecipe cooktime: " + request.getParameter("cooktime"));
//        log.info("#registerRecipe difficultyLevel: " + request.getParameter("difficultyLevel"));
//        log.info("#registerRecipe accessibility: " + Integer.parseInt(request.getParameter("accessibility")));
//        
//        // 레시피 등록(*레시피 아이디 리턴)
//        recipe.setMEmail((String)session.getAttribute("email"));
//        recipe.setMNickname((String)session.getAttribute("nickname"));
//        recipe.setProfileImg((String)session.getAttribute("profileImg"));
//        recipe.setTitle(request.getParameter("title"));
//        recipe.setInfo(request.getParameter("info"));
//        recipe.setFood(request.getParameter("food"));
//        recipe.setSort(request.getParameter("sort"));
//        recipe.setIngredient(request.getParameter("ingredient"));
//        recipe.setServing(request.getParameter("serving"));
//        recipe.setCookTime(request.getParameter("cooktime"));
//        recipe.setDifficultyLevel(request.getParameter("difficultyLevel"));
//        recipe.setAccessibility(Integer.parseInt(request.getParameter("accessibility")));
//        
//        int result = recipeMapper.insertRecipe(recipe);
//        Long id = recipe.getId();
//        log.info("#registerRecipe insert result: " + result);
//        log.info("#registerRecipe ID: " + id);
//        
//        // 메인재료 등록
//        String mainItem;
//        for (int i=0; i<mainItems.size(); i++) {
//            mainItem = mainItems.get(i);
//            ingredient.setRId(id);
//            ingredient.setIngredientType(0);
//            ingredient.setIngredient(mainItem.split("-")[0]);
//            ingredient.setQuantity(mainItem.split("-")[1]);
//            recipeMapper.insertIngredient(ingredient);
//        }
//        
//        // 양념재료 등록
//        String subItem;
//        for (int i=0; i<subItems.size(); i++) {
//            subItem = subItems.get(i);
//            ingredient.setRId(id);
//            ingredient.setIngredientType(1);
//            ingredient.setIngredient(subItem.split("-")[0]);
//            ingredient.setQuantity(subItem.split("-")[1]);
//            recipeMapper.insertIngredient(ingredient);
//        }
//        
//        // 요리순서 등록 및 파일 업로드
//        String text = "STEP-";
//        String content;
//        int num = 0;
//        int step = 0;
//        for (MultipartFile file : files) {
//            String ofname = file.getOriginalFilename();
//            log.info("#파일 이름: " + ofname);
//            fileInfoList.clear();
//            fileInfoList.add(text + Integer.toString(num));
//            if (ofname != null) ofname = ofname.trim();
//            if (ofname.length() != 0) {
//                if (num == 0) {
//                    String[] urlAndName = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\" + id + "\\", fileInfoList);
//                    log.info("#url: " + urlAndName[0]);
//                    recipe.setFoodPhoto(urlAndName[1]);
//                    recipeMapper.updateRecipePhoto(recipe); 
//                } else {
//                    if(num > directions.size()) {
//                    } else {
//                        String[] urlAndName = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\" + id + "\\", fileInfoList);                
//                        log.info("#url: " + urlAndName[0]);
//                        content = directions.get(step - 1 + num);
//                        direction.setRId(id);
//                        direction.setDirection(content);
//                        direction.setStep(step + num);
//                        direction.setOriginalFile(ofname);
//                        direction.setSaveFile(urlAndName[1]);
//                        recipeMapper.insertDirection(direction);
//                        log.info("#registerRecipe direction: " + direction);
//                    }
//                }
//            }
//            num += 1;
//        }
//        String tag;
//        for(int i=0; i<tags.size(); i++) {
//            tag = tags.get(i);
//            Tag.setRId(id);
//            Tag.setTag(tag);
//            recipeMapper.insertTag(Tag);
//            
//        }
	}

	@Override
	public List<RecipeReplyList> findRecipeReply(long id) {
		return recipeLegacyMapper.selectReply(id);		
	}
	
	@Override
	public void delete(long id) {
		recipeLegacyMapper.deleteReply(id);
	}
	
	
	@Override
	public String registerReply(RecipeReply recipeReply) {
		String result;
		try {
			recipeLegacyMapper.insertReply(recipeReply);
			result = "성공";
		} catch(Exception e) {
			System.out.println("#registerReply: " + e);
			result = "실패";
		}
		System.out.println("#registerReply: " + result);
		return result;
	}

	
	@Override	
	public String registerNestedReply(RecipeNestedReply recipeNestedReply) {
		String result;
		try {
			recipeLegacyMapper.insertNestedReply(recipeNestedReply);
			result = "성공";
		} catch(Exception e) {
			System.out.println("#registerNestedReply: " + e);
			result = "실패";
		}
		System.out.println("#registerNestedReply: " + result);
		return result;
	}

    @Override
    public void updateRecipe(long id) {
        recipeMapper.updateRecipe(recipe);
        recipeMapper.updateIngredient(ingredient);
        recipeMapper.updateDirection(direction);
        recipeMapper.updateTag(Tag);
        recipeMapper.updateRecipePhoto(recipe);
    }

//    @Override
//    public void updateRecipe(long id,
//            HttpServletRequest request,
//            HttpSession session,
//            ArrayList<MultipartFile> files,
//            ArrayList<String> mainItems,
//            ArrayList<String> subItems,
//            ArrayList<String> directions,
//            ArrayList<String> tags) {
//        //Read로 호출된 값을 불러온다.
//        String ids = (String)session.getAttribute("id");
//        id = Integer.parseInt(ids);
//        long rId = id;
//        session.getAttribute("email");
//        log.info("#####se1");
//        session.getAttribute("nickname");
//        log.info("#####se2");
//        session.getAttribute("profileImg");
//        log.info("#####se3");
//        request.getParameter("title");
//        log.info("#####se4");
//        request.getParameter("info");
//        log.info("#####se5");
//        request.getParameter("food");
//        log.info("#####se6");
//        request.getParameter("sort");
//        log.info("#####se7");
//        request.getParameter("ingredient");
//        log.info("#####se8");
//        request.getParameter("serving");
//        log.info("#####se9");
//        request.getParameter("cooktime");
//        log.info("#####se10");
//        request.getParameter("difficultyLevel");
//        log.info("#####se11");
//        
//        Integer.parseInt(request.getParameter("accessibility"));
//        Recipe recipes = recipeMapper.updateRecipe(recipe);
//        session.setAttribute("email", recipes.getMEmail());
//        session.setAttribute("nickname", recipes.getMNickname());
//        session.setAttribute("profileImg", recipes.getProfileImg());
//        session.setAttribute("recipe", recipes);
//        
//        
//        String mainItem;
//        for (int i=0; i<mainItems.size(); i++) {
//            mainItem = mainItems.get(i);
//            ingredient.setRId(id);
//            ingredient.setIngredientType(0);
//            ingredient.setIngredient(mainItem.split("-")[0]);
//            ingredient.setQuantity(mainItem.split("-")[1]);
//            recipeMapper.updateIngredient(ingredient);
//        }
//      
//        // 양념재료 등록
//        String subItem;
//        for (int i=0; i<subItems.size(); i++) {
//            subItem = subItems.get(i);
//            ingredient.setRId(id);
//            ingredient.setIngredientType(1);
//            ingredient.setIngredient(subItem.split("-")[0]);
//            ingredient.setQuantity(subItem.split("-")[1]);
//            recipeMapper.updateIngredient(ingredient);
//        }
//        
//        String text = "STEP-";
//        String content;
//        int num = 0;
//        int step = 0;
//        for (MultipartFile file : files) {
//        String ofname = file.getOriginalFilename();
//        log.info("#파일 이름: " + ofname);
//        fileInfoList.clear();
//        fileInfoList.add(text + Integer.toString(num));
//        if (ofname != null) ofname = ofname.trim();
//        if (ofname.length() != 0) {
//            if (num == 0) {
//                String[] urlAndName = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\" + id + "\\", fileInfoList);
//                log.info("#url: " + urlAndName[0]);
//                recipe.setFoodPhoto(urlAndName[1]);
//                recipeMapper.updateRecipePhoto(recipe); 
//            } else {
//                if(num > directions.size()) {
//                } else {
//                    String[] urlAndName = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\" + id + "\\", fileInfoList);                
//                    log.info("#url: " + urlAndName[0]);
//                    content = directions.get(step - 1 + num);
//                    direction.setRId(id);
//                    direction.setDirection(content);
//                    direction.setStep(step + num);
//                    direction.setOriginalFile(ofname);
//                    direction.setSaveFile(urlAndName[1]);
//                    recipeMapper.updateDirection(direction);
//                    log.info("#registerRecipe direction: " + direction);
//                }
//            }
//        }
//        num += 1;
//    }
//      String tag;
//      for(int i=0; i<tags.size(); i++) {
//          tag = tags.get(i);
//          Tag.setRId(id);
//          Tag.setTag(tag);
//          recipeMapper.updateTag(Tag);
//
//      }
//    }
	
}
