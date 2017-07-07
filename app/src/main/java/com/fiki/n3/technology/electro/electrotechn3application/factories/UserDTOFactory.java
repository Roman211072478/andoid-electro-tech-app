package com.fiki.n3.technology.electro.electrotechn3application.factories;


import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;

/**
 * Created by Roman on 2016/04/17.
 */
public interface UserDTOFactory {

    UserDTO creatUserDTO(Integer userId,
                         Integer roleId,
                         Integer loginId,
                         Integer TutorialId);

}
