package com.example.redis2.controller;

import com.example.redis2.entity.User;
import com.example.redis2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@EnableCaching
public class UserController {
    private final UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id",value = "User")
    public User editUser(@PathVariable long id,@RequestBody User user){
        return userService.edit(id,user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id",value = "User")
    public String deleteUser(@PathVariable long id){
        return userService.deleteUser(id)?"Delete success":"something is wrong";
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }


    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "User")
    public User findUser(@PathVariable long id){
        return userService.findById(id);
    }

}
