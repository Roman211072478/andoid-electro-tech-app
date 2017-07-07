package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;


import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class RoleCUDAbstract {
	
	 public RoleCUDAbstract nextValue;

    public void setNext(RoleCUDAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract String action(String request, RoleDTO roleDTO, ContextDTO contextDTO);
    
	
}