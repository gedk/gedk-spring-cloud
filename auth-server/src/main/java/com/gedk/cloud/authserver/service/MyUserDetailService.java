package com.gedk.cloud.authserver.service;

import com.gedk.cloud.authserver.domain.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/8 15:12
 */
@Service
@Slf4j
public class MyUserDetailService  implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        //根据用户名带出来的密码，会跟客户端送过来的密码做匹配
        String password = "123456";
        log.info(userName);
        return new MyUser(
                userName,
                passwordEncoder.encode(password),
                authorityList
        );
    }
}