package com.gedk.cloud.authserver.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/8 15:14
 */
@Getter
@Setter
public class MyUser extends User {
    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
