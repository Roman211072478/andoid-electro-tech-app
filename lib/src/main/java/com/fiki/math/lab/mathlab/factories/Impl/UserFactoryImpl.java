/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.math.lab.mathlab.domain.model.User;
import com.fiki.math.lab.mathlab.factories.UserFactory;

/**
 *
 * @author Roman
 */
public class UserFactoryImpl implements UserFactory{
    
    private static UserFactoryImpl factory = null;

    private  UserFactoryImpl() {
    }
    
    public static UserFactoryImpl getInstance(){
        if(factory ==null)
            factory = new UserFactoryImpl();
        return factory;
    }
    @Override
    public User createUser(String displayName, String tutorialId, String username, String password, int userId, Role role) {

        User user = new User.Builder()
                .displayName(displayName)
                .password(password)
                .role(role)
                .tutorialId(tutorialId)
                .userId(userId)
                .username(username)
                .build();
        return user;
    }
    
}
