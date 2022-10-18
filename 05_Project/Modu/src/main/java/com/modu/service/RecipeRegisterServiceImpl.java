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
		
	    // ������ Ȯ��
	    log.info("#registerRecipe email: " + (String)session.getAttribute("email"));
        log.info("#registerRecipe nickname: " + (String)session.getAttribute("nickname"));
        log.info("#registerRecipe profileImg: " + (String)session.getAttribute("profileImg"));
        log.info("#registerRecipe title: " + request.getParameter("title"));
        log.info("#registerRecipe info: " + request.getParameter("info"));
        log.info("#registerRecipe food: " + request.getParameter("food"));
        log.info("#registerRecipe sort: " + request.getParameter("sort"));
        log.info("#registerRecipe ingredient: " + request.getParameter("ingredient"));
        log.info("#registerRecipe serving: " + request.getParameter("serving"));
        log.info("#registerRecipe cooktime: " + request.getParameter("cooktime"));
        log.info("#registerRecipe difficultyLevel: " + request.getParameter("difficultyLevel"));
        log.info("#registerRecipe accessibility: " + Integer.parseInt(request.getParameter("accessibility")));
        
        // ������ ���(*������ ���̵� ����)
        recipe.setMEmail((String)session.getAttribute("email"));
        recipe.setMNickname((String)session.getAttribute("nickname"));
        recipe.setProfileImg((String)session.getAttribute("profileImg"));
        recipe.setTitle(request.getParameter("title"));
        recipe.setInfo(request.getParameter("info"));
        recipe.setFood(request.getParameter("food"));
        recipe.setSort(request.getParameter("sort"));
        recipe.setIngredient(request.getParameter("ingredient"));
        recipe.setServing(request.getParameter("serving"));
        recipe.setCookTime(request.getParameter("cooktime"));
        recipe.setDifficultyLevel(request.getParameter("difficultyLevel"));
        recipe.setAccessibility(Integer.parseInt(request.getParameter("accessibility")));
        
        int result = recipeMapper.insertRecipe(recipe);
        Long id = recipe.getId();
        log.info("#registerRecipe insert result: " + result);
        log.info("#registerRecipe ID: " + id);
        
        // ������� ���
        String mainItem;
        for (int i=0; i<mainItems.size(); i++) {
            mainItem = mainItems.get(i);
            ingredient.setRId(id);
            ingredient.setIngredientType(0);
            ingredient.setIngredient(mainItem.split("-")[0]);
            ingredient.setQuantity(mainItem.split("-")[1]);
            recipeMapper.insertIngredient(ingredient);
        }
        
        // ������ ���
        String subItem;
        for (int i=0; i<subItems.size(); i++) {
            subItem = subItems.get(i);
            ingredient.setRId(id);
            ingredient.setIngredientType(1);
            ingredient.setIngredient(subItem.split("-")[0]);
            ingredient.setQuantity(subItem.split("-")[1]);
            recipeMapper.insertIngredient(ingredient);
        }
        
        // �丮���� ��� �� ���� ���ε�
        String text = "STEP-";
        String content;
        int num = 0;
        int step = 1;
        for (MultipartFile file : files) {
            String ofname = file.getOriginalFilename();
            log.info("#���� �̸�: " + ofname);
            fileInfoList.clear();
            fileInfoList.add(text + Integer.toString(num));
            if (ofname != null) ofname = ofname.trim();
            if (ofname.length() != 0) {
                if (num == 0) {
                    String[] urlAndName = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\" + id + "\\", fileInfoList);
                    log.info("#url: " + urlAndName[0]);
                    recipe.setFoodPhoto(urlAndName[1]);
                    recipeMapper.updateRecipePhoto(recipe);
                } else if(num >= directions.size()) { // �������� ������ -1������ GET �ؾ� �ε��� ������ �߻����� ���� 
                } else {
                    String[] urlAndName = fileUploadService.saveImgFile(file, Path.RECIPE_PATH + "\\" + id + "\\", fileInfoList);                
                    log.info("#url: " + urlAndName[0]);
                    content = directions.get(num);
                    direction.setRId(id);
                    direction.setDirection(content);
                    direction.setStep(step + num);
                    direction.setOriginalFile(ofname);
                    direction.setSaveFile(urlAndName[1]);
                    recipeMapper.insertDirection(direction);
                    log.info("#registerRecipe direction: " + direction);
                }
            }
            num += 1;
        }
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
			result = "����";
		} catch(Exception e) {
			System.out.println("#registerReply: " + e);
			result = "����";
		}
		System.out.println("#registerReply: " + result);
		return result;
	}

	
	@Override	
	public String registerNestedReply(RecipeNestedReply recipeNestedReply) {
		String result;
		try {
			recipeLegacyMapper.insertNestedReply(recipeNestedReply);
			result = "����";
		} catch(Exception e) {
			System.out.println("#registerNestedReply: " + e);
			result = "����";
		}
		System.out.println("#registerNestedReply: " + result);
		return result;
	}
}
