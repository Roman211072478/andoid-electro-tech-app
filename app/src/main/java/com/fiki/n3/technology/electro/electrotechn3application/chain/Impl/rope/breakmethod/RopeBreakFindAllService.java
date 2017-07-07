package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.RopeBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RopeBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RopeBreakFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class RopeBreakFindAllService extends RopeBreakFindAbstract {

    @Override
    public HashMap action(String request, RopeBreakDTO ropeBreakDTO , ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result

            HashMap result = new HashMap();

            RopeBreakDAO dao = new RopeBreakDAOImpl(ctx.getContext());
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
                return nextValue.action( request,  ropeBreakDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
