package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Article;
import com.lawencon.community.model.File;

@Repository
public class ArticleDao extends AbstractJpaDao<Article> {

	public List<Article> getLastTwo() {
		String sql = "SELECT id, file_id, title, content, version, is_active FROM article ORDER BY created_at DESC LIMIT 2";
		List<?> results = createNativeQuery(sql).getResultList();
		List<Article> articles = new ArrayList<Article>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Article article = new Article();
			article.setId(obj[0].toString());
			
			File file = new File();
			if(obj[1]!=null) file.setId(obj[1].toString());
			article.setFile(file);
			
			article.setTitle(obj[2].toString());
			article.setContent(obj[3].toString());
			article.setVersion(Integer.valueOf(obj[4].toString()));
			article.setIsActive(Boolean.valueOf(obj[5].toString()));
			
			articles.add(article);
		}
		return articles;
	}
}
