package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.prony.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod.PronyBreakCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod.PronyBreakDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod.PronyBreakDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod.PronyBreakUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.PronyBreakCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class PronyBreakCUDChainFactory {

    public static String performRequest(String request, PronyBreakDTO pronyBreakDTO, ContextDTO ctx){
        PronyBreakCUDAbstract chain = setUpChain();
        return chain.action(request,pronyBreakDTO,ctx);
    }

    public static PronyBreakCUDAbstract setUpChain(){

        PronyBreakCUDAbstract a = new PronyBreakCreateService();
        PronyBreakCUDAbstract b = new PronyBreakDeleteService();
        PronyBreakCUDAbstract c = new PronyBreakUpdateService();
        PronyBreakCUDAbstract d = new PronyBreakDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}
