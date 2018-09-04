package com.ponleu.mongographql.service;

import com.ponleu.mongographql.model.Article;
import com.ponleu.mongographql.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findAllUserArticles(List<String> articleIds) {

        return articleRepository.findByIdIn(articleIds);

    }
}
