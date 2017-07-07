package com.fiki.n3.technology.electro.electrotechn3application.dto;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class UserDTO  implements Serializable {

    private Integer userId;
    private Integer roleId;
    private Integer loginId;
    private Integer TutorialId;
    private String displayName;



    public UserDTO(Builder builder)
    {

        this.roleId = builder.roleId;
        this.userId = builder.userId;
        this.loginId = builder.loginId;
        this.TutorialId = builder.TutorialId;
        this.displayName = builder.displayName;

    }



    //builder
    public static class Builder{
        private Integer userId;
        private Integer roleId;
        private Integer loginId;
        private Integer TutorialId;
        private String displayName;




        public Builder displayName(String displayName)
        {
            this.displayName = displayName;
            return this;
        } public Builder userId(Integer userId)
        {
            this.userId = userId;
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
        public Builder tutorialId(Integer TutorialId)
        {
            this.TutorialId = TutorialId;
            return this;
        }


        public Builder copy(UserDTO value)
        {
            this.roleId = value.getRoleId();
            this.userId = value.getUserId();
            this.loginId = value.getLoginId();
            this.TutorialId = value.getTutorialId();
            this.displayName = value.getDisplayName();


            return this;
        }

        public UserDTO build(){
            return new UserDTO(this);
        }


    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public Integer getRoleId()
    {
        return roleId;
    }


    public Integer getLoginId()
    {
        return this.loginId;
    }
    public Integer getTutorialId()
    {
        return this.TutorialId;
    }
    public String getDisplayName() {
        return displayName;
    }
}
