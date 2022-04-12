package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dto.category.DeleteCategoryDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByIdCategoryDtoRes;
import com.lawencon.community.dto.category.GetCategoryDtoDataRes;
import com.lawencon.community.dto.category.InsertCategoryDtoDataRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoDataRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;
import com.lawencon.community.model.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseCommunityService {

	private final CategoryDao categoryDao;

	public GetAllCategoryDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Category> categories;	
		if(start == null) categories = categoryDao.getAll();
		else categories = categoryDao.getAll(start, max);
		
		List<GetCategoryDtoDataRes> data = new ArrayList<>();

		categories.forEach(list -> {
			GetCategoryDtoDataRes category = new GetCategoryDtoDataRes();
			category.setId(list.getId());
			category.setCategoryName(list.getCategoryName());
			category.setCategoryCode(list.getCategoryCode());
			category.setVersion(list.getVersion());
			category.setIsActive(list.getIsActive());
			data.add(category);
		});

		GetAllCategoryDtoRes result = new GetAllCategoryDtoRes();
		result.setData(data);
		result.setRows(categoryDao.countAll());

		return result;
	}

	public GetByIdCategoryDtoRes getById(String id) throws Exception {
		Category category = categoryDao.getById(id);
		if(category != null) {
			GetCategoryDtoDataRes categoryData = new GetCategoryDtoDataRes();
			categoryData.setId(category.getId());
			categoryData.setCategoryName(category.getCategoryName());
			categoryData.setCategoryCode(category.getCategoryCode());
			categoryData.setVersion(category.getVersion());
			categoryData.setIsActive(category.getIsActive());
			
			GetByIdCategoryDtoRes result = new GetByIdCategoryDtoRes();
			result.setData(categoryData);
			
			return result;
		}
		
		throw new RuntimeException("Category Id doesn't exist");
	}
	
	public InsertCategoryDtoRes insert(InsertCategoryDtoReq data) throws Exception {
		Category category = new Category();
		valBkNotExist(data.getCategoryCode(), data.getCategoryName());
		category.setCategoryName(data.getCategoryName());
		category.setCategoryCode(data.getCategoryCode());
		category.setCreatedBy(getId());

		begin();
		Category categoryInsert = categoryDao.save(category);
		commit();
		
		InsertCategoryDtoDataRes categoryId = new InsertCategoryDtoDataRes();
		categoryId.setId(categoryInsert.getId());

		InsertCategoryDtoRes result = new InsertCategoryDtoRes();
		result.setData(categoryId);
		result.setMsg("Insert Successfully");
		
		return result;
	}

	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception {
		Category category = categoryDao.getById(data.getId());
		valBkNotExist(data.getCategoryName());
		category.setCategoryName(data.getCategoryName());
		category.setUpdatedBy(getId());
		category.setVersion(data.getVersion());
		category.setIsActive(data.getIsActive());

		begin();
		Category categoryUpdate = categoryDao.save(category);
		commit();

		UpdateCategoryDtoDataRes categoryVersion = new UpdateCategoryDtoDataRes();
		categoryVersion.setVersion(categoryUpdate.getVersion());

		UpdateCategoryDtoRes result = new UpdateCategoryDtoRes();
		result.setData(categoryVersion);
		result.setMsg("Update Successfully");
		return result;
	}
	
	public DeleteCategoryDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = categoryDao.deleteById(id);
			commit();
			
			DeleteCategoryDtoRes categoryRes = new DeleteCategoryDtoRes();
			if(isDeleted) {
				categoryRes.setMsg("Delete Successfully");
			} else {
				categoryRes.setMsg("Delete Unsuccessfully");
			}
			
			return categoryRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	private void valBkNotExist(String code, String name) {
		Integer flagCode = categoryDao.isCategoryCodeExist(code);
		Integer flagName = categoryDao.isCategoryNameExist(name);
		if(flagCode == 1 || flagName == 1) {
			throw new RuntimeException("Category Code or Category Name has existed");
		}
	}
	
	private void valBkNotExist(String name) {
		Integer flagName = categoryDao.isCategoryNameExist(name);
		if(flagName == 1) {
			throw new RuntimeException("Category Name has existed");
		}
	}
}
