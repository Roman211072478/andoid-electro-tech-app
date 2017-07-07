/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.math.lab.mathlab.factories.RoleFactory;

/**
 *
 * @author Roman
 */
public class RoleFactoryImpl implements RoleFactory{

     private static RoleFactoryImpl factory = null;

    private  RoleFactoryImpl() {
    }
    
    public static RoleFactoryImpl getInstance(){
        if(factory ==null)
            factory = new RoleFactoryImpl();
        return factory;
    }
    
    @Override
    public Role createRole(String role, String description,Integer roleId) {
       Role roleObject = new Role(role,description);
        roleObject.setRoleId(roleId);
       
       return roleObject;
    }
    
    
}
