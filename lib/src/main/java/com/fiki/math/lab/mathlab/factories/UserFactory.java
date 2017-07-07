/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.math.lab.mathlab.domain.model.User;

/**
 *
 * @author Roman
 */
public interface UserFactory {
    
    public User createUser( String displayName,
         String tutorialId,
         String username ,
         String password,
         int userId,
         Role role);
    
}
