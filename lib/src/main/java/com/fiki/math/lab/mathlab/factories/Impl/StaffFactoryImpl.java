/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;
import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.math.lab.mathlab.domain.model.Staff;
import com.fiki.math.lab.mathlab.factories.StaffFactory;

/**
 *
 * @author Roman
 */
public class StaffFactoryImpl implements StaffFactory{

     private static StaffFactoryImpl factory = null;

    private  StaffFactoryImpl() {
    }
    
    public static StaffFactoryImpl getInstance(){
        if(factory ==null)
            factory = new StaffFactoryImpl();
        return factory;
    }
    
    @Override
    public Staff createStaff(Role role, int empId, ContactDetails contact,
                             String displayName, String username,
                             String password) {
        Staff staffObject = new Staff.Builder()
                .displayName(displayName)
                .contact(contact)
                .password(password)
                .role(role)
                .empId(empId)
                .username(username)
                .build();
        return staffObject;
                
                }
    
    
}
