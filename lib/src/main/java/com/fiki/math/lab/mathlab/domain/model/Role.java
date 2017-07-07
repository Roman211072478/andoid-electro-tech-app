/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.domain.model;

import java.io.Serializable;

/**
 *
 * @author Roman
 */
public class Role implements Serializable{
    private String role;
    private String description;
    private Integer roleId;

    public Role(String role, String description) {
        this.description = description;
        this.role = role;
    }

    public Role() {

    }

    public String getRole() {
        return this.role;
    }

    public Integer getRoleId() {
        return this.roleId;
    }


    public String getDescription() {
        return this.description;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
}
