package com.ponleu.mongographql.service;

import com.ponleu.mongographql.model.User;
import com.ponleu.mongographql.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByIdIn(List<String> friendsIds) {
        return userRepository.findByIdIn(friendsIds);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findOneById(ObjectId id) {
        return userRepository.findOneById(id);
    }
}
