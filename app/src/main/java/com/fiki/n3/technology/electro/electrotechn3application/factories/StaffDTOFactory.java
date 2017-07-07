package com.fiki.n3.technology.electro.electrotechn3application.factories;


import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

/**
 * Created by Roman on 2016/04/17.
 */
public interface StaffDTOFactory {
    StaffDTO createStaffDTO(Integer staffId, Integer roleId, Integer loginId);
}
