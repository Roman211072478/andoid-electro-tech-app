package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.Tutorial;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial.TutorialFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial.TutorialFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TutorialFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class TutorialFindChainFactory {

    public static HashMap performRequest(String request, TutorialDTO tutorialDTO, ContextDTO ctx){
        TutorialFindAbstract chain = setUpChain();
        return chain.action(request,tutorialDTO,ctx);
    }

    public static TutorialFindAbstract setUpChain(){

        TutorialFindAbstract a = new TutorialFindByIdService();
        TutorialFindAbstract b = new TutorialFindAllService();

        a.setNext(b);

        return a;
    }
}
