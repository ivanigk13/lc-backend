package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.ArticleDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dto.article.DeleteArticleDtoRes;
import com.lawencon.community.dto.article.GetAllArticleDtoRes;
import com.lawencon.community.dto.article.GetArticleDtoDataRes;
import com.lawencon.community.dto.article.GetByIdArticleDtoRes;
import com.lawencon.community.dto.article.GetLastTwoArticleDtoRes;
import com.lawencon.community.dto.article.InsertArticleDtoDataRes;
import com.lawencon.community.dto.article.InsertArticleDtoReq;
import com.lawencon.community.dto.article.InsertArticleDtoRes;
import com.lawencon.community.dto.article.UpdateArticleDtoDataRes;
import com.lawencon.community.dto.article.UpdateArticleDtoReq;
import com.lawencon.community.dto.article.UpdateArticleDtoRes;
import com.lawencon.community.model.Article;
import com.lawencon.community.model.File;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService extends BaseCommunityService {

	private final ArticleDao articleDao;
	private final FileDao fileDao;
	
	public InsertArticleDtoRes insert(String data, MultipartFile file) throws Exception {
		try {
			InsertArticleDtoReq articleReq = new ObjectMapper().readValue(data, InsertArticleDtoReq.class);
			Article article = new Article();
			
			begin();
			if(file != null) {
				File fileArticle = new File();
				String splitter = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
						file.getOriginalFilename().length());
				fileArticle.setExtensionName(splitter);
				fileArticle.setContent(file.getBytes());
				fileArticle.setCreatedBy(getId());
				
				fileArticle = fileDao.save(fileArticle);
				article.setFile(fileArticle);
			}
			
			article.setTitle(articleReq.getTitle());
			article.setContent(articleReq.getContent());
			article.setCreatedBy(getId());
			
			article = articleDao.save(article);
			commit();
			
			InsertArticleDtoDataRes articleId = new InsertArticleDtoDataRes();
			articleId.setId(article.getId());
			
			InsertArticleDtoRes result = new InsertArticleDtoRes();
			result.setData(articleId);
			result.setMsg("Insert Successfully");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public UpdateArticleDtoRes update(String data, MultipartFile file) throws Exception {
		try {
			UpdateArticleDtoReq articleReq = new ObjectMapper().readValue(data, UpdateArticleDtoReq.class);
			Article article = articleDao.getById(articleReq.getId());
			
			begin();
			if(file != null) {
				File fileArticle = new File();
				String splitter = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
						file.getOriginalFilename().length());
				fileArticle.setExtensionName(splitter);
				fileArticle.setContent(file.getBytes());
				fileArticle.setUpdatedBy(getId());
				
				fileArticle = fileDao.save(fileArticle);
				article.setFile(fileArticle);
			}
			
			article.setTitle(articleReq.getTitle());
			article.setContent(articleReq.getContent());
			article.setUpdatedBy(getId());
			
			article = articleDao.save(article);
			commit();
			
			UpdateArticleDtoDataRes articleVersion = new UpdateArticleDtoDataRes();
			articleVersion.setVersion(article.getVersion());
			
			UpdateArticleDtoRes result = new UpdateArticleDtoRes();
			result.setData(articleVersion);
			result.setMsg("Update Successfully");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetAllArticleDtoRes getAll(Integer start, Integer max) throws Exception {
		List<Article> articles;
		if(start == null) articles = articleDao.getAll();
		else articles = articleDao.getAll(start, max);
		
		List<GetArticleDtoDataRes> data = new ArrayList<GetArticleDtoDataRes>();
		
		articles.forEach(article -> {
			GetArticleDtoDataRes dataRes = new GetArticleDtoDataRes();
			dataRes.setId(article.getId());
			if(article.getFile() != null) {
			dataRes.setFileId(article.getFile().getId());
			}
			dataRes.setTitle(article.getTitle());
			dataRes.setContent(article.getContent());
			dataRes.setVersion(article.getVersion());
			dataRes.setIsActive(article.getIsActive());
			
			data.add(dataRes);
		});
		
		GetAllArticleDtoRes result = new GetAllArticleDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetByIdArticleDtoRes getById(String id) throws Exception {
		Article article = articleDao.getById(id);
		
		GetArticleDtoDataRes dataRes = new GetArticleDtoDataRes();
		dataRes.setId(article.getId());
		dataRes.setFileId(article.getFile().getId());
		dataRes.setTitle(article.getTitle());
		dataRes.setContent(article.getContent());
		dataRes.setVersion(article.getVersion());
		dataRes.setIsActive(article.getIsActive());
		
		GetByIdArticleDtoRes result = new GetByIdArticleDtoRes();
		result.setData(dataRes);
		
		return result;
	}
	
	public DeleteArticleDtoRes deleteById(String id) throws Exception {
		try {			
			begin();
			boolean isDeleted = articleDao.deleteById(id);
			commit();
			
			DeleteArticleDtoRes articleRes = new DeleteArticleDtoRes();
			if(isDeleted) {
				articleRes.setMsg("Delete Successfully");
			} else {
				articleRes.setMsg("Delete Unsuccessfully");
			}
			
			return articleRes;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetLastTwoArticleDtoRes getLastTwo() throws Exception {
		List<Article> articles = articleDao.getLastTwo();
		
		List<GetArticleDtoDataRes> data = new ArrayList<GetArticleDtoDataRes>();
		
		articles.forEach(article -> {
			GetArticleDtoDataRes dataRes = new GetArticleDtoDataRes();
			dataRes.setId(article.getId());
			dataRes.setFileId(article.getFile().getId());
			dataRes.setTitle(article.getTitle());
			dataRes.setContent(article.getContent());
			dataRes.setVersion(article.getVersion());
			dataRes.setIsActive(article.getIsActive());
			
			data.add(dataRes);
		});
		
		GetLastTwoArticleDtoRes result = new GetLastTwoArticleDtoRes();
		result.setData(data);
		
		return result;
	}
}
