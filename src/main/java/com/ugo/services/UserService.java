package com.ugo.services;

import com.ugo.entitys.Users;
import com.ugo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository urs;

    public void saveUser(Users user){
        urs.save(user);
    }
}
