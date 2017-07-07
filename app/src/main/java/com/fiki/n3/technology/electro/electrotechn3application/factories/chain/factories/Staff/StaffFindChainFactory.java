package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.Staff;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffFindLoginIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.StaffFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class StaffFindChainFactory {

    public static HashMap performRequest(String request, StaffDTO staffDTO, ContextDTO ctx){
        StaffFindAbstract chain = setUpChain();
        return chain.action(request,staffDTO,ctx);
    }

    public static StaffFindAbstract setUpChain(){

        StaffFindAbstract a = new StaffFindByIdService();
        StaffFindAbstract b = new StaffFindAllService();
        StaffFindAbstract c = new StaffFindLoginIdService();

        a.setNext(b);
        b.setNext(c);

        return a;
    }
}
