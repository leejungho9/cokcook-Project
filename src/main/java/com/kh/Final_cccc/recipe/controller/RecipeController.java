package com.kh.Final_cccc.recipe.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.Final_cccc.Event.exception.EventException;
import com.kh.Final_cccc.Files.service.FilesService;
import com.kh.Final_cccc.Files.vo.Files;
import com.kh.Final_cccc.material.model.vo.Material;
import com.kh.Final_cccc.recipe.model.service.RecipeService;
import com.kh.Final_cccc.recipe.model.vo.ReMaterial;
import com.kh.Final_cccc.recipe.model.vo.Recipe;
import com.kh.Final_cccc.recipe.model.vo.RecipeProcess;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeService rService;
	
	@Autowired
	private FilesService fService;
	
	@RequestMapping("RList.rp")
	public ModelAndView RecipeList(@RequestParam("sort_no") int sort_no, @RequestParam("type") int type,
			ModelAndView mv) {

		
		ArrayList<Recipe> rList = null;
		switch(type) {
		case 1: rList = rService.selectsubList(sort_no); break;
		case 2: rList = rService.selectmateList(sort_no); break;
		case 3: rList = rService.selectspecList(sort_no); break;
		}
		
		System.out.println(rList);
		
		if(rList != null) {
			mv.addObject("rList", rList).setViewName("/searchRecipe/searchRecipe");
		}
		return mv;
	}
	
	@RequestMapping("rDetail.rp")
	public String RecipeList(@RequestParam("recipeNo") int recipeNo, Model model) {
		

		
		//레시피 개요
		Recipe r_info = rService.selectRecipe(recipeNo);
		//재료정보(영양정보& 재료목록)
		ArrayList<ReMaterial> r_mate = rService.selectrMate(recipeNo);
		//조리과정
		ArrayList<RecipeProcess> r_process = rService.selectProcess(recipeNo);
		
		//회원정보 고민해볼것
		
		//영양정보  계산하기
		int kcal = 0, fat = 0, protein = 0, carbo = 0, sugar = 0;
		
		if(r_mate != null) {
			for(int i = 0; i < r_mate.size(); i++) {
				//칼로리
				kcal += r_mate.get(i).getKcal();
				//단백질
				protein += r_mate.get(i).getProtein();
				//지방
				fat += r_mate.get(i).getFat();
				//탄수화물
				carbo += r_mate.get(i).getCarbo();
				//당
				sugar += r_mate.get(i).getSugar();
			}
		}
		int nutritionArr[] = {kcal, protein, fat, carbo, sugar};
		
		ArrayList<Integer> nutlist = new ArrayList<>();
		for(int i = 0; i < nutritionArr.length; i++) {
			nutlist.add(nutritionArr[i]);
		}
		
		
		model.addAttribute("r_info", r_info).addAttribute("r_mate",r_mate).addAttribute("r_process", r_process).addAttribute("nutArr", nutlist);
		return "/RecipeDetail/RecipeDetail";
	}
	
	@RequestMapping("insertRecipeForm.rp")
	public String insertRpform() {
		
		return "/insertRecipe/InsertRecipe";
	}
	
	@RequestMapping("MList.rp")
	@ResponseBody
	public void MateList(@RequestParam("mNo") int mNo, HttpServletResponse response) throws JsonIOException, IOException {
		System.out.println("m : "+mNo);
		response.setContentType("application/json; charset=UTF-8");
		ArrayList<Material> mList = rService.selectmaterialList(mNo);
		
		System.out.println(mList);
		
		new GsonBuilder().create().toJson(mList, response.getWriter());
	}
	
	@RequestMapping("insertRecipe.rp")
	public void insertRecipe(HttpServletRequest request, @ModelAttribute Recipe recipe,
			MultipartHttpServletRequest request2, @RequestParam("RecipeImg") MultipartFile[] recipeImg) {
		
		//파일 기본조건
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "/uploadFiles";
		//디렉토리가 없다면 디렉토리 생성
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		//1. 레시피(메인) 추가
		System.out.println(recipe);
		int result_1 = rService.insertRecipe(recipe);
		
		
		//날짜를 원하는 형태로 출력 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMHHssSSS");
		ArrayList<String> originFileName = new ArrayList<String>(); 
		ArrayList<String> renameFileName = new ArrayList<String>();
		ArrayList<String > renamePath = new ArrayList<String>();
		
		//추가된 재료 배열과 재료에 맞는 양
		String[] mNoArr = request.getParameterValues("rp_mate");
		String[] mAmArr = request.getParameterValues("mate_amount");
		for(int i = 0; i < mNoArr.length; i++) {
			System.out.println("번호 : "+mNoArr[i] + "/ 재료양 : " + mAmArr[i]);
			int material_no = Integer.parseInt(mNoArr[i]);
			int per_qnt = Integer.parseInt(mAmArr[i]);
			ReMaterial mate = new ReMaterial(material_no, per_qnt);
			int result_2 = rService.insertRecipemate(mate);
			//한번씩 메소드를 호출해서 보내줘야
		}
		

		//조리과정		
		for(int i = 0; i < recipeImg.length; i++) {
			 originFileName.add(recipeImg[i].getOriginalFilename());
			 renameFileName.add(sdf.format(new Date(System.currentTimeMillis()))+ originFileName.get(i).substring(originFileName.lastIndexOf(".") + 1));
			 renamePath.add(folder +"/"+ renameFileName.get(i));
			 
			 try {
				 // MultipartFile로 받은 파일을 transferTo( ) 함수를 통해 renamePath()경로에  저장
				recipeImg[i].transferTo(new File(renamePath.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] pr_Content = request.getParameterValues("rp_content");
		for(int i = 0; i < pr_Content.length; i++) {
			//i번째에 한번씩 메소드를 불러와 DB에 저장해야함
			System.out.println("텍스트 에어리어 : " + pr_Content[i]);
			System.out.println("원본 이미지 : " + originFileName.get(i));
			System.out.println("변형 이미지 : " + renameFileName.get(i));
			
			
		}
		
		ArrayList<Files> fileList = new ArrayList<Files>();
		
		for(int i = 0; i <= originFileName.size()-1; i++) {
			Files f = new Files();
			f.setFilePath(savePath);
			
			f.setFileName(originFileName.get(i));
			f.setChangeName(renameFileName.get(i));

			if( i == originFileName.size() - 1) {
						f.setFileYn("N");
					} else {
						f.setFileYn("Y");
					}
			
			
				System.out.println("f" + f);
				int fresult = fService.insertRFiles(f);
				if(fresult == 0) {
					System.out.println("이벤트이미지 에러남");
					throw new EventException("이벤트 상세 조회에 실패하였습니다.");
				}
			
			}
		
		
		
	}
}

