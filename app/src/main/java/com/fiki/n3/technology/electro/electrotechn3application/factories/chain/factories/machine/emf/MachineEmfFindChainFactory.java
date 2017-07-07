package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.machine.emf;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf.MachineEmfFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf.MachineEmfFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.MachineEmfFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class MachineEmfFindChainFactory {

    public static HashMap performRequest(String request, MachineEmfDTO machineEmfDTO, ContextDTO ctx){
        MachineEmfFindAbstract chain = setUpChain();
        return chain.action(request,machineEmfDTO,ctx);
    }

    public static MachineEmfFindAbstract setUpChain(){

        MachineEmfFindAbstract a = new MachineEmfFindByIdService();
        MachineEmfFindAbstract b = new MachineEmfFindAllService();

        a.setNext(b);

        return a;
    }
}
