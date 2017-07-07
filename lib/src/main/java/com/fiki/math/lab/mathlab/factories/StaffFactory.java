/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;
import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.math.lab.mathlab.domain.model.Staff;

/**
 *
 * @author Roman
 */
public interface StaffFactory {
    
    public Staff createStaff( Role role,
         int empId,
         ContactDetails contact,
         String displayName,       
         String username ,
         String password);
    
}
