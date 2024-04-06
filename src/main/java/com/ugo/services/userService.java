package com.ugo.services;

import com.ugo.entitys.users;
import com.ugo.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private usersRepository urs;

    public void saveUser(users user){
        urs.save(user);
    }
}
