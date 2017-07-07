package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.back.emf;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf.BackEmfFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf.BackEmfFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.BackEmfFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class BackEmfFindChainFactory {

    public static HashMap performRequest(String request, BackEmfDTO backEmfDTO , ContextDTO contextDTO){
        BackEmfFindAbstract chain = setUpChain();
        return chain.action(request,backEmfDTO,contextDTO);
    }

    public static BackEmfFindAbstract setUpChain(){

        BackEmfFindAbstract a = new BackEmfFindByIdService();
        BackEmfFindAbstract b = new BackEmfFindAllService();

        a.setNext(b);

        return a;
    }
}
