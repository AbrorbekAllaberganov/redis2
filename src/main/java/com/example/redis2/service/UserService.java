package com.example.redis2.service;

import com.example.redis2.entity.User;
import com.example.redis2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User userRequest){
        User user=new User();
        user.setId(userRequest.getId());
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());

        return userRepository.save(user);
    }

    public User edit(long id,User userRequest){
        User user=findById(id);
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        return userRepository.save(user);
    }

    public boolean deleteUser(long id){
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return false;
    }

    public List<User> getAll(){
        log.info("get by db");
        return userRepository.findAll();
    }

    public User findById(long id){
        log.info("Find by db");
        return userRepository.findById(id).orElseThrow();
    }


}
