package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.UserEntity;
import com.openedsource.pizzastore.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "username") String username){
        Optional<UserEntity> user = userRepository.findById(username);
        return ResponseEntity.ok().body(user.get());
    }

    // TODO searching api
    @GetMapping("/user")
    public ResponseEntity<List<UserEntity>> getUserList(){
        List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();
        return ResponseEntity.ok().body(userList);
    }
}
