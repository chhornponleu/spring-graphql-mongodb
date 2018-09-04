package com.ponleu.mongographql.graphql.fetchers;

import com.ponleu.mongographql.model.User;
import com.ponleu.mongographql.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.catalina.LifecycleState;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserDataFetcher implements DataFetcher<User> {


    @Autowired
    private UserService userService;

    @Override
    public User get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        User user= userService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
        return user;
    }
}
