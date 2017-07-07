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
public class LoginDetails implements Serializable {
    private Integer loginId;
    private String username;
    private String password;
    private String displayName;
    private Integer roleId;

    public LoginDetails() {
    }


    public void setUserId(Integer loginId) {
        this.loginId = loginId;
    }


   public void login(String username,String password,String displayName)
   {
       this.password = password;
        this.username = username;
         this.displayName = displayName;
   }
  /* public void setDisplayName(String displayName)
   {
       this.displayName = displayName;
   }*/
   
   
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
     public String getDisplayName()
    {
        return this.displayName;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
