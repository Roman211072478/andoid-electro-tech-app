package com.fiki.n3.technology.electro.electrotechn3application.factories.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;
import com.fiki.n3.technology.electro.electrotechn3application.factories.StaffDTOFactory;

/**
 * Created by Roman on 2016/04/17.
 */
public class StaffDTOFactoryImpl implements StaffDTOFactory {
    private static StaffDTOFactoryImpl factory = null;

    private  StaffDTOFactoryImpl() {
    }

    public static StaffDTOFactoryImpl getInstance(){
        if(factory ==null)
            factory = new StaffDTOFactoryImpl();
        return factory;
    }

    @Override
    public StaffDTO createStaffDTO(Integer staffId, Integer roleId, Integer loginId) {
        StaffDTO staffDTO = new StaffDTO.Builder()
                .loginId(loginId)
                .roleId(roleId)
                .staffId(staffId)
                .build();

        return staffDTO;

    }
}
