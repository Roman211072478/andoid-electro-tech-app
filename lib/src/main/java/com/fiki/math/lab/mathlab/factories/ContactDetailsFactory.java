/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;

/**
 *
 * @author Roman
 */
public interface ContactDetailsFactory {
    ContactDetails createContactDetails(String emailAddress,String cellNumber);
    
}
