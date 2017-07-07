package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.back.emf;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf.BackEmfCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf.BackEmfDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf.BackEmfDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf.BackEmfUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.BackEmfCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class BackEmfCUDChainFactory {

    public static String performRequest(String request, BackEmfDTO backEmfDTO, ContextDTO contextDTO){
        BackEmfCUDAbstract chain = setUpChain();
        return chain.action(request,backEmfDTO,contextDTO);
    }

    public static BackEmfCUDAbstract setUpChain(){

        BackEmfCUDAbstract a = new BackEmfCreateService();
        BackEmfCUDAbstract b = new BackEmfDeleteService();
        BackEmfCUDAbstract c = new BackEmfUpdateService();
        BackEmfCUDAbstract d = new BackEmfDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}
