package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TutorialDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TutorialDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TutorialFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class TutorialFindAllService extends TutorialFindAbstract {

    @Override
    public HashMap action(String request, TutorialDTO tutorialDTO, ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result
            HashMap result = new HashMap();

            TutorialDAO dao = new TutorialDAOImpl(ctx.getContext());
            result = dao.getList();

            HashMap map = new HashMap();
			 if(result.containsKey("error"))
            {
                map.put("error", request);
            }
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  tutorialDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
