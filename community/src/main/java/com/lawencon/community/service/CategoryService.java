package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseService;
import com.lawencon.community.dao.CategoryDao;
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
		Category Category = new Category();
		Category.setCategoryName(data.getCategoryName());
		Category.setCategoryCode(data.getCategoryCode());

		begin();
		Category CategoryInsert = categoryDao.save(Category);
		InsertCategoryDtoDataRes CategoryId = new InsertCategoryDtoDataRes();
		CategoryId.setId(CategoryInsert.getId());

		InsertCategoryDtoRes result = new InsertCategoryDtoRes();
		result.setData(CategoryId);
		result.setMsg("Insert Successfully");
		commit();
		return result;
	}

	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception {
		Category Category = categoryDao.getById(data.getId());
		Category.setCategoryName(data.getCategoryName());
		Category.setVersion(data.getVersion());
		Category.setIsActive(data.getIsActive());

		begin();
		Category CategoryUpdate = categoryDao.save(Category);

		UpdateCategoryDtoDataRes CategoryVersion = new UpdateCategoryDtoDataRes();
		CategoryVersion.setVersion(CategoryUpdate.getVersion());

		UpdateCategoryDtoRes result = new UpdateCategoryDtoRes();
		result.setData(CategoryVersion);
		result.setMsg("Update Successfully");
		return result;
	}

	public GetAllCategoryDtoRes getAll() throws Exception {
		List<Category> Categorys = categoryDao.getAll();
		List<GetCategoryDtoDataRes> data = new ArrayList<>();

		Categorys.forEach(list -> {
			GetCategoryDtoDataRes Category = new GetCategoryDtoDataRes();
			Category.setId(list.getId());
			Category.setCategoryName(list.getCategoryName());
			Category.setCategoryCode(list.getCategoryCode());
			Category.setVersion(list.getVersion());
			Category.setIsActive(list.getIsActive());
			data.add(Category);
		});

		GetAllCategoryDtoRes result = new GetAllCategoryDtoRes();
		result.setData(data);

		return result;
	}

	public GetByIdCategoryDtoRes getById(String id) throws Exception {
		Category Category = categoryDao.getById(id);

		GetCategoryDtoDataRes CategoryData = new GetCategoryDtoDataRes();
		CategoryData.setId(Category.getId());
		CategoryData.setCategoryName(Category.getCategoryName());
		CategoryData.setCategoryCode(Category.getCategoryCode());
		CategoryData.setVersion(Category.getVersion());
		CategoryData.setIsActive(Category.getIsActive());

		GetByIdCategoryDtoRes result = new GetByIdCategoryDtoRes();
		result.setData(CategoryData);

		return result;
	}
}
