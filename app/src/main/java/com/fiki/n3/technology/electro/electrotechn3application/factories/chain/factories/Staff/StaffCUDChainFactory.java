package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.Staff;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff.StaffUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.StaffCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class StaffCUDChainFactory {

    public static String performRequest(String request, StaffDTO staffDTO, ContextDTO ctx){
        StaffCUDAbstract chain = setUpChain();
        return chain.action(request,staffDTO,ctx);
    }

    public static StaffCUDAbstract setUpChain(){

        StaffCUDAbstract a = new StaffCreateService();
        StaffCUDAbstract b = new StaffDeleteService();
        StaffCUDAbstract c = new StaffUpdateService();
        StaffCUDAbstract d = new StaffDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}
