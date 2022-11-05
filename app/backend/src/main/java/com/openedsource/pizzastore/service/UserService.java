package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.UserEntity;
import com.openedsource.pizzastore.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void insertUser(UserEntity userEntity) {

        Optional<UserEntity> user = userRepository.findById(userEntity.getUsername());
        if (user.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        userRepository.save(userEntity);
    }
}
