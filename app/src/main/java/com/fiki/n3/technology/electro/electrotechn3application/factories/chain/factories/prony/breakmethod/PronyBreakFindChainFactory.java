package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.prony.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod.PronyBreakFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod.PronyBreakFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.PronyBreakFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class PronyBreakFindChainFactory {

    public static HashMap performRequest(String request, PronyBreakDTO pronyBreakDTO, ContextDTO ctx){
        PronyBreakFindAbstract chain = setUpChain();
        return chain.action(request,pronyBreakDTO,ctx);
    }

    public static PronyBreakFindAbstract setUpChain(){

        PronyBreakFindAbstract a = new PronyBreakFindByIdService();
        PronyBreakFindAbstract b = new PronyBreakFindAllService();

        a.setNext(b);

        return a;
    }
}
