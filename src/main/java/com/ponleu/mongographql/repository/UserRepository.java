package com.ponleu.mongographql.repository;

import com.ponleu.mongographql.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
    List<User> findByIdIn(List<String> friendsIds);
    List<User> findAll();
    User findOneById(ObjectId id);
}
