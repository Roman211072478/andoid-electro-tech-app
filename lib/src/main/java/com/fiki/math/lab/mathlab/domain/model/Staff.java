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

public class Staff extends LoginDetails implements Serializable {
    private Role role = new Role();
    private int empId;
    private ContactDetails contact;
    
    private Staff()
    {   
    }
    
   
     public Staff(Builder builder)
    {
        this.empId = builder.empId;
        this.role = builder.role;
        this.contact = builder.contact;
        super.login(builder.username, builder.password,builder.displayName);
        
        
    }
    
    //builder
    public static class Builder{
        private Role role = new Role();
        private int empId;
        private ContactDetails contact;
        private String displayName;       
        private String username ;
        private String password;       
        
        public Builder displayName(String displayName)
        {
            this.displayName = displayName;
            return this;
        }
        public Builder empId(int empId)
        {
            this.empId = empId;
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
        
        public Builder contact(ContactDetails contact)
        {
            this.contact = contact;
            return this;
        }
        
        public Builder copy(Staff value)
        {
            this.displayName=value.getDisplayName();
            this.contact=value.getContactDetails();
            this.username = value.getUsername();
            this.password=value.getPassword();
            this.empId = value.getEmpID();
            this.role = value.getRole();
            
            return this;
        }

        public Staff build(){
            return new Staff(this);
        }

    
    }
    
    public int empId()
    {
        return this.empId;
    }
    
    public Role getRole()
    {
        return role;
    }
 
    
    public int getEmpID()
    {
        return this.empId;
    }
    public ContactDetails getContactDetails()
    {
        return contact;
    }
    
}
