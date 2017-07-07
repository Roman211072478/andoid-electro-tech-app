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

public class User extends LoginDetails implements Serializable{

    private String tutorialId;//the current tutorial user busy with
    private int userId;
    private Role role;
  
    
 
     public User(Builder builder)
    {
        
        //default constructor                    
            this.tutorialId=builder.tutorialId;
            super.login(builder.username, builder.password,builder.displayName);
            this.userId = builder.userId;
            this.role = builder.role;
    }
    
    //builder
    public static class Builder{
        private String displayName;
        private String tutorialId;
        private String username ;
        private String password;
        private int userId;
        private Role role;
        
        public Builder displayName(String displayName)
        {
            this.displayName = displayName;
            return this;
        }
        public Builder tutorialId(String tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }
        
        public Builder username(String username)
        {
            this.username = username;
            return this;
        }
        public Builder password(String password)
        {
            this.password = password;
            return this;
        }
        public Builder role(Role role)
        {
            this.role = role;
            return this;
        }
        
        public Builder userId(int userId)
        {
            this.userId = userId;
            return this;
        }
        
        public Builder copy(User value)
        {
            this.displayName=value.getDisplayName();
            this.tutorialId=value.tutorialId;
            this.username = value.getUsername();
            this.password=value.getPassword();
            this.userId = value.userId;
            this.role = value.role;
            
            return this;
        }

        public User build(){
            return new User(this);
        }

    
    }
    
    public int getUserId()
    {
        return this.userId;
    }
    
    public String getTutorialId()
    {
        return tutorialId;
    }
    public Role getRole()
    {
        return role;
    }
 
}
