package com.fiki.n3.technology.electro.electrotechn3application.dto;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class StaffDTO  implements Serializable {
    private Integer staffId;
    private Integer roleId;
    private Integer loginId;



    public StaffDTO(Builder builder)
    {
        
        this.roleId = builder.roleId;
        this.staffId = builder.staffId;
        this.loginId = builder.loginId;


    }

    //builder
    public static class Builder{
        private Integer staffId;
        private Integer roleId;
        private Integer loginId;
       
       

        public Builder staffId(Integer staffId)
        {
            this.staffId = staffId;
            return this;
        }
        public Builder roleId(Integer roleId)
        {
            this.roleId = roleId;
            return this;
        }

        public Builder loginId(Integer loginId)
        {
            this.loginId = loginId;
            return this;
        }



        public Builder copy(StaffDTO value)
        {
            this.roleId = value.getRoleId();
            this.staffId = value.getStaffId();
            this.loginId = value.getLoginId();
           
            return this;
        }

        public StaffDTO build(){
            return new StaffDTO(this);
        }


    }

    public Integer getStaffId()
    {
        return this.staffId;
    }

    public Integer getRoleId()
    {
        return roleId;
    }


    public Integer getLoginId()
    {
        return this.loginId;
    }

}
