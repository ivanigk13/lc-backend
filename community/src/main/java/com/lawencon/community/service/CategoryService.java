package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
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
public class CategoryService extends BaseService {

	private final CategoryDao categoryDao;

	public InsertCategoryDtoRes insert(InsertCategoryDtoReq data) throws Exception {
		Category category = new Category();
		category.setCategoryName(data.getCategoryName());
		category.setCategoryCode(data.getCategoryCode());

		begin();
		Category categoryInsert = categoryDao.save(category);
		InsertCategoryDtoDataRes categoryId = new InsertCategoryDtoDataRes();
		categoryId.setId(categoryInsert.getId());

		InsertCategoryDtoRes result = new InsertCategoryDtoRes();
		result.setData(categoryId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception {
		Category category = categoryDao.getById(data.getId());
		category.setCategoryName(data.getCategoryName());
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

	public GetAllCategoryDtoRes getAll() throws Exception {
		List<Category> categories = categoryDao.getAll();
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

		return result;
	}

	public GetByIdCategoryDtoRes getById(String id) throws Exception {
		Category category = categoryDao.getById(id);

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
	
	public DeleteCategoryDtoRes deleteById(String id) throws Exception {
		begin();
		categoryDao.deleteById(id);
		commit();
		
		DeleteCategoryDtoRes categoryRes = new DeleteCategoryDtoRes();
		categoryRes.setMsg("Delete Successfully");
		
		return categoryRes;
	}
}
