package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;

import java.io.Serializable;

/**
 * Created by Roman on 2016/05/02.
 */
public class LoginDetailsDTO implements Serializable {
    private LoginDetails loginDetails;
    private Integer loginID;

    public LoginDetailsDTO(Builder builder)
    {

        this.loginDetails = builder.loginDetails;
        this.loginID = builder.loginID;
    }

    //builder
    public static class Builder{
        private LoginDetails loginDetails;
        private Integer loginID;




        public Builder loginDetails(LoginDetails loginDetails)
        {
            this.loginDetails = loginDetails;
            return this;
        }
        public Builder loginID(Integer loginID)
        {
            this.loginID = loginID;
            return this;
        }

        public Builder copy(LoginDetailsDTO value)
        {
            this.loginDetails = value.getLoginDetails();
            this.loginID = value.getLoginID();

            return this;
        }

        public LoginDetailsDTO build(){
            return new LoginDetailsDTO(this);
        }
    }

    public LoginDetails getLoginDetails()
    {
        return this.loginDetails;
    }

    public Integer getLoginID()
    {
        return loginID;
    }
}
