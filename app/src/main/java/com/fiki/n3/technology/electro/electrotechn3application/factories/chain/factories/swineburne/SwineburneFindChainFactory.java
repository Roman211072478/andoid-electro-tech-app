package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.swineburne;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne.SwineburneFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne.SwineburneFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.SwineburneFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.SwineburneDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class SwineburneFindChainFactory {

    public static HashMap performRequest(String request, SwineburneDTO swineburneDTO, ContextDTO ctx){
        SwineburneFindAbstract chain = setUpChain();
        return chain.action(request,swineburneDTO,ctx);
    }

    public static SwineburneFindAbstract setUpChain(){

        SwineburneFindAbstract a = new SwineburneFindByIdService();
        SwineburneFindAbstract b = new SwineburneFindAllService();

        a.setNext(b);

        return a;
    }
}
