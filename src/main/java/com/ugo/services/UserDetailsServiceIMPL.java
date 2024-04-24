package com.ugo.services;

import com.ugo.entitys.User;
import com.ugo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceIMPL implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByNombre(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        user.getRoleId().forEach(
                roles -> authorityList.add(new SimpleGrantedAuthority("Rol".concat(roles.getRoleName())))
        );
        user.getRoleId().stream()
                .flatMap(roles -> roles.getPermissionsList().stream())
                .forEach(perm -> authorityList.add(new SimpleGrantedAuthority(perm.getPermission())));
      return new org.springframework.security.core.userdetails.User(
              user.getNombre(),
              user.getPassword(),
              user.isIsAccountNoTExpired(),
              user.isIsCredentialNOExpired(),
              user.isIsAccountNoTLocked(),
              user.isIsEnable(),
              authorityList
      );
    }
}
