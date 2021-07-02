package com.kh.Final_cccc.recipe.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.Final_cccc.recipe.model.vo.ReMaterial;
import com.kh.Final_cccc.recipe.model.vo.Recipe;
import com.kh.Final_cccc.recipe.model.vo.RecipeProcess;

@Repository("rDAO")
public class recipeDAO {

	public ArrayList<Recipe> selectsubList(int sort_no, SqlSessionTemplate sqlSession) {
		// 주제별 검색
		return (ArrayList)sqlSession.selectList("recipeMapper.selectsubList", sort_no);
	}

	public ArrayList<Recipe> selectmateList(int sort_no, SqlSessionTemplate sqlSession) {
		// 재료별 검색
		return (ArrayList)sqlSession.selectList("recipeMapper.selectmateList", sort_no);
	}

	public ArrayList<Recipe> selectspecList(int sort_no, SqlSessionTemplate sqlSession) {
		// 특산물 검색
		return (ArrayList)sqlSession.selectList("recipeMapper.selectspecList", sort_no);
	}

	public Recipe selectRecipe(int recipeNo, SqlSessionTemplate sqlSession) {
		// 레시피 선택
		return sqlSession.selectOne("recipeMapper.selectRecipe", recipeNo);
	}

	public ArrayList<ReMaterial> selectrMate(int recipeNo, SqlSessionTemplate sqlSession) {
		// 레시피 재료 불러오기
		return (ArrayList)sqlSession.selectList("recipeMapper.selectrMate", recipeNo);
	}

	public ArrayList<RecipeProcess> selectProcess(int recipeNo, SqlSessionTemplate sqlSession) {
		// 조리과정 불러오기
		return (ArrayList)sqlSession.selectList("recipeMapper.selectProcess", recipeNo);
	}

}