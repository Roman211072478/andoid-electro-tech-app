/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.Role;

/**
 *
 * @author Roman
 */
public interface RoleFactory {
    
    public Role createRole(String role,String description,Integer roleId);
    
}
