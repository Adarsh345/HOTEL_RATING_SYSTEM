package com.lcwd.user.service.repository;

import com.lcwd.user.service.entitys.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface UserRepository extends MongoRepository<User, Serializable> {
    User findById(String userId);

    User getUserById(String userId);
}
