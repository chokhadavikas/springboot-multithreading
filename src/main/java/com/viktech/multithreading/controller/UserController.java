package com.viktech.multithreading.controller;

import com.viktech.multithreading.entity.User;
import com.viktech.multithreading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value="files") MultipartFile[] multipartFiles) throws Exception {

        for (MultipartFile file:multipartFiles){
            userService.saveUser(file);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value="/users")
    public CompletableFuture<ResponseEntity> findAllUsers(){
        return userService.findAllUsers().thenApply(users -> ResponseEntity.ok().body(users));

    }

    @GetMapping(value = "/getUserByMultipleThread")
    public ResponseEntity getUsersByMultipleThread(){

        CompletableFuture<List<User>> users1= userService.findAllUsers();
        CompletableFuture<List<User>> users2= userService.findAllUsers();
        CompletableFuture<List<User>> users3= userService.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
