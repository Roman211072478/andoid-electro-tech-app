package com.fiki.n3.technology.electro.electrotechn3application.factories.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.factories.UserDTOFactory;

/**
 * Created by Roman on 2016/04/17.
 */
public class UserDTOFactoryImpl implements UserDTOFactory {
    private static UserDTOFactoryImpl factory = null;

    private  UserDTOFactoryImpl() {
    }

    public static UserDTOFactoryImpl getInstance(){
        if(factory ==null)
            factory = new UserDTOFactoryImpl();
        return factory;
    }


    @Override
    public UserDTO creatUserDTO(Integer userId, Integer roleId, Integer loginId, Integer TutorialId) {
        UserDTO userDTO = new UserDTO.Builder()
                .userId(userId)
                .roleId(roleId)
                .loginId(loginId)
                .tutorialId(TutorialId)
                .build();

        return userDTO;
    }
}
