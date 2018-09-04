package com.ponleu.mongographql.repository;


import com.ponleu.mongographql.model.Article;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, ObjectId> {

    List<Article> findByIdIn(List<String> articleIds);
}
