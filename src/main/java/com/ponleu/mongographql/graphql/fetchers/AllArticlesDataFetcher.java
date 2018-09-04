package com.ponleu.mongographql.graphql.fetchers;

import com.ponleu.mongographql.model.Article;
import com.ponleu.mongographql.model.User;
import com.ponleu.mongographql.service.ArticleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllArticlesDataFetcher implements DataFetcher<List<Article>> {
    @Autowired
    private ArticleService articleService;

    @Override
    public List<Article> get(DataFetchingEnvironment env) {

        User user  = env.getSource();
        List<String> articleIds = new ArrayList<>();

        if(user != null) {
            articleIds = user.getArticlesIds();
        }
        List<Article> articles = articleService.findAllUserArticles(articleIds);

        return articles;
    }
}
