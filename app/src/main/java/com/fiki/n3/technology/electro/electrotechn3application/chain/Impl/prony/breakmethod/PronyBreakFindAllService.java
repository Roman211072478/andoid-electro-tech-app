package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.PronyBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.PronyBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.PronyBreakFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class PronyBreakFindAllService extends PronyBreakFindAbstract {

    @Override
    public HashMap action(String request, PronyBreakDTO pronyBreakDTO, ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result

            HashMap result = new HashMap();

            PronyBreakDAO dao = new PronyBreakDAOImpl(ctx.getContext());
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
                return nextValue.action( request,  pronyBreakDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
