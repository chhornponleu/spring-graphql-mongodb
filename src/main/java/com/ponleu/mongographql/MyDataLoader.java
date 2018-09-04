package com.ponleu.mongographql;

import com.ponleu.mongographql.model.Article;
import com.ponleu.mongographql.model.User;
import com.ponleu.mongographql.repository.ArticleRepository;
import com.ponleu.mongographql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyDataLoader {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;


    @PostConstruct
    public void generateData() {

        userRepository.deleteAll();
        articleRepository.deleteAll();

        List<User> users = Arrays.asList(
                User.builder().name("Ponleu").createdAt(new Date()).age(27).articlesIds(new ArrayList<>()).friendsIds(new ArrayList<>()).build(),
                User.builder().name("Sokna").createdAt(new Date()).age(21).articlesIds(new ArrayList<>()).friendsIds(new ArrayList<>()).build(),
                User.builder().name("Chamrong").createdAt(new Date()).age(31).articlesIds(new ArrayList<>()).friendsIds(new ArrayList<>()).build(),
                User.builder().name("Vouchnea").createdAt(new Date()).age(19).articlesIds(new ArrayList<>()).friendsIds(new ArrayList<>()).build()
        );

        userRepository.saveAll(users);

        List<Article> articles = Arrays.asList(
                Article.builder().title("Java GraphQL").minutesRead(20).authorId(users.get(0).getId().toString()).build(),
                Article.builder().title("Spring MongoDB GraphQL").minutesRead(20).authorId(users.get(1).getId().toString()).build(),
                Article.builder().title("ExpressJS GraphQL").minutesRead(20).authorId(users.get(2).getId().toString()).build(),
                Article.builder().title("Full Stack Web Development").minutesRead(20).authorId(users.get(3).getId().toString()).build()
        );

        articleRepository.saveAll(articles);

        users.get(0).setArticlesIds(Arrays.asList(articles.get(0).getId().toString()));
        users.get(1).setArticlesIds(Arrays.asList(articles.get(1).getId().toString()));
        users.get(2).setArticlesIds(Arrays.asList(articles.get(2).getId().toString()));
        users.get(3).setArticlesIds(Arrays.asList(articles.get(3).getId().toString()));
        userRepository.saveAll(users);

        User me = users.get(0);
        me.setFriendsIds(users.stream().map(user -> {
            return user.getId().toString();
        }).collect(Collectors.toList()));
        userRepository.save(me);
    }

}
