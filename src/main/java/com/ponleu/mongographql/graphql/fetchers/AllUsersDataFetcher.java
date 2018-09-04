package com.ponleu.mongographql.graphql.fetchers;

import com.ponleu.mongographql.model.User;
import com.ponleu.mongographql.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    @Autowired
    private UserService userService;

    @Override
    public List<User> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        List<User> friends = new ArrayList<>();

        if(user!=null) {
            friends = userService.findByIdIn(user.getFriendsIds());
        }
        else {
            friends = userService.findAllUsers();
        }
        return friends;
    }
}
