package com.example.redis2.repository;

import com.example.redis2.entity.User;
import com.example.redis2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

//@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRedisService {
    private final RedisTemplate redisTemplate;
    private final UserService userService;

    public static final String USER_HASH="User";


    public User saveUser(User user){
        userService.saveUser(user);
        redisTemplate.opsForHash().put(USER_HASH,user.getId(),user);
        return user;
    }

    public List<User> findAll(){
        return redisTemplate.opsForHash().values(USER_HASH);
    }

    public User findUser(long id){
        log.info("User get from  db");
        return (User) redisTemplate.opsForHash().get(USER_HASH,id);
    }

    public String deleteUser(long id){
        log.info("User remove from redis cache");
        redisTemplate.opsForHash().delete(USER_HASH,id);
        return "Remove success";
    }

}
