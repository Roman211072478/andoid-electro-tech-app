package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.machine.emf;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf.MachineEmfCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf.MachineEmfDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf.MachineEmfDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf.MachineEmfUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.MachineEmfCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class MachineEmfCUDChainFactory {

    public static String performRequest(String request, MachineEmfDTO machineEmfDTO, ContextDTO ctx){
        MachineEmfCUDAbstract chain = setUpChain();
        return chain.action(request,machineEmfDTO,ctx);
    }

    public static MachineEmfCUDAbstract setUpChain(){

        MachineEmfCUDAbstract a = new MachineEmfCreateService();
        MachineEmfCUDAbstract b = new MachineEmfDeleteService();
        MachineEmfCUDAbstract c = new MachineEmfUpdateService();
        MachineEmfCUDAbstract d = new MachineEmfDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}
