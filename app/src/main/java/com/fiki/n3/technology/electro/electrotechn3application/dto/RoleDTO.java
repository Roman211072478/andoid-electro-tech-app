package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.Role;

import java.io.Serializable;

/**
 * Created by Roman on 2016/05/02.
 */
public class RoleDTO implements Serializable {

    private Role role;
    private Integer roleId;

    public RoleDTO(Builder builder)
    {

        this.role = builder.role;
        this.roleId = builder.roleId;
    }

    //builder
    public static class Builder{
        private Role role;
        private Integer roleId;


        public Builder role(Role role)
        {
            this.role = role;
            return this;
        }
        public Builder roleId(Integer roleId)
        {
            this.roleId = roleId;
            return this;
        }

        public Builder copy(RoleDTO value)
        {
            this.role = value.getRole();
            this.roleId = value.getRoleId();

            return this;
        }

        public RoleDTO build(){
            return new RoleDTO(this);
        }
    }

    public Role getRole()
    {
        return this.role;
    }

    public Integer getRoleId()
    {
        return roleId;
    }

}
