package com.foolish.todolist.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    @PostMapping()
    public ResponseEntity create(@RequestBody UserModel usermodel) {
        UserModel user = this.userRepository.findByUsername(usermodel.getUsername());
        if(user != null) {
            System.out.println("cant create");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(usermodel));
    }
}
