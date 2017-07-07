package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.rope.method;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod.RopeBreakFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod.RopeBreakFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RopeBreakFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class RopeBreakFindChainFactory {

    public static HashMap performRequest(String request, RopeBreakDTO ropeBreakDTO, ContextDTO ctx){
        RopeBreakFindAbstract chain = setUpChain();
        return chain.action(request,ropeBreakDTO,ctx);
    }

    public static RopeBreakFindAbstract setUpChain(){

        RopeBreakFindAbstract a = new RopeBreakFindByIdService();
        RopeBreakFindAbstract b = new RopeBreakFindAllService();

        a.setNext(b);

        return a;
    }
}
