package com.modu.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.domain.recipe.Recipe;
import com.modu.domain.recipe.RecipeList;
import com.modu.domain.recipe.RecipeListVo;
import com.modu.mapper.RecipeMapper;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private RecipeMapper recipeMapper;

	@Override
	public RecipeListVo searchRecipe(HttpServletRequest request, HttpSession session) {
		int currentPage = 1;
		int pageSize = 8;
		int totalPage;
		int totalPost = recipeMapper.selectRecipeCount();
		try {
			switch(request.getParameter("value")) {
			case "0": int period = Integer.parseInt(request.getParameter("period"));
					  if(period == 0) {
						  break;  
					  }else{
						  totalPost = recipeMapper.selectRecipeCountByPeriod(period);
						  break;						  
					  }
			case "1": if (request.getParameter("category").equals("랜덤추천★")) {
						  totalPost = Integer.parseInt(request.getParameter("pageSize"));  
					  }else {
						  String category = request.getParameter("category");
						  period = Integer.parseInt(request.getParameter("period"));
						  totalPost = recipeMapper.selectRecipeCountByCategory(category, period);
					  }
					  break;
			case "2": String nameOption = request.getParameter("nameOption");
			  		  String keyword = request.getParameter("keyword");
			  		  period = Integer.parseInt(request.getParameter("period"));
			  		  if (nameOption.equals("ingredient")) {
			  			  totalPost = recipeMapper.selectRecipeCountByIngredient(keyword, period);
					  }else {
						  if(nameOption.equals("recipe")) {
							  nameOption = "TITLE";
						  }else if(nameOption.equals("chef")) {
							  nameOption = "M_NICKNAME";
						  }
						  totalPost = recipeMapper.selectRecipeCountByKeyword(nameOption, keyword, period);
					  }
			}
		}catch(NullPointerException ne) {}
		
		
		if(request.getParameter("currentPage") != null) {
			if(session.getAttribute("currentPage") != null) {
				currentPage = (int)session.getAttribute("currentPage");
			}
			String param = request.getParameter("currentPage");
			try {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}catch(NumberFormatException nfe) {
				switch(param) {
				case "＜": currentPage = currentPage - 1; break;
				case "＞": currentPage = currentPage + 1;						 
				}
			}
		}else if(session.getAttribute("currentPage") != null) {
			currentPage = (int)session.getAttribute("currentPage");
		}
		
		if(request.getParameter("pageSize") != null) {			
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}else if(session.getAttribute("pageSize") != null) {
			pageSize = (int)session.getAttribute("pageSize");			
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
		
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("pageSize", pageSize);
		
		int endRow = currentPage*pageSize;
		int beginRow = endRow-pageSize+1;
		
		List<RecipeList> recipeList = recipeMapper.selectRecipeListBy(beginRow, endRow);
		if(request.getParameter("value")!=null) {
			recipeList = getSearchedRecipes(request, beginRow, endRow);
		}
		
		RecipeListVo data = new RecipeListVo(recipeList, currentPage, pageSize, totalPage);
		
		return data;
	}
	
	private List<RecipeList> getSearchedRecipes(HttpServletRequest request, int beginRow, int endRow){
		String value = request.getParameter("value");
		int period = Integer.parseInt(request.getParameter("period"));
		String category;
		String nameOption;
		String keyword;

		List<RecipeList> recipeList = new ArrayList<RecipeList>();
				
		switch(value) {
		case "0": if (period == 0) {
					  recipeList = recipeMapper.selectRecipeListBy(beginRow, endRow);
					  break;
				  }else {
					  recipeList = recipeMapper.selectRecipeListByPeriod(period, beginRow, endRow);
					  break;
				  }				  
		case "1": category = request.getParameter("category");
				  if (category.equals("랜덤추천★")) {
					  recipeList = recipeMapper.selectRecipeListByRandom(beginRow, endRow);
					  break;
				  }		
				  recipeList = recipeMapper.selectRecipeListByCategory(category, period, beginRow, endRow);
				  break;
		case "2": nameOption = request.getParameter("nameOption");
				  keyword = request.getParameter("keyword");
				  if (nameOption.equals("recipe")) {
					  nameOption = "TITLE";
				  }else if(nameOption.equals("chef")) {
					  nameOption = "M_NICKNAME";
				  }else if(nameOption.equals("ingredient")) {
					  recipeList = recipeMapper.selectRecipeListByIngredient(keyword, period, beginRow, endRow);
					  break;
				  }
				  recipeList = recipeMapper.selectRecipeListByKeyword(nameOption, keyword, period, beginRow, endRow);
				  break;
		}
		
		return recipeList;
	}
	
	@Override
	public RecipeListVo searchRecipeByIngredient(HttpServletRequest request, HttpSession session) {
		String[] ingredients = request.getParameterValues("data");
		if(ingredients == null) {
			System.out.println("Data is null\n");
		}else {
			for(int i=0; i<ingredients.length; i++) {
				String item = ingredients[i];
				System.out.println("item : "+item);
			}
		}
		System.out.println();
		int currentPage = 1;
		int pageSize = 8;
		int totalPage;
		int totalPost = recipeMapper.selectRecipeCount(); //수정할 것
		
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
		
		session.setAttribute("myCurrentPage", currentPage);				
		
		RecipeListVo data = new RecipeListVo();
		
		return data;
	}
}
