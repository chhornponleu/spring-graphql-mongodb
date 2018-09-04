package com.ponleu.mongographql.service;

import com.ponleu.mongographql.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAllUserArticles(List<String> articleIds);
}
