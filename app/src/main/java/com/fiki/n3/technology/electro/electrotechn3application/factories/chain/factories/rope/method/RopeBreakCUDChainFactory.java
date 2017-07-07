package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.rope.method;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod.RopeBreakCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod.RopeBreakDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod.RopeBreakDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod.RopeBreakUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RopeBreakCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class RopeBreakCUDChainFactory {

    public static String performRequest(String request, RopeBreakDTO ropeBreakDTO, ContextDTO ctx){
        RopeBreakCUDAbstract chain = setUpChain();
        return chain.action(request,ropeBreakDTO,ctx);
    }

    public static RopeBreakCUDAbstract setUpChain(){

        RopeBreakCUDAbstract a = new RopeBreakCreateService();
        RopeBreakCUDAbstract b = new RopeBreakDeleteService();
        RopeBreakCUDAbstract c = new RopeBreakUpdateService();
        RopeBreakCUDAbstract d = new RopeBreakDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}
