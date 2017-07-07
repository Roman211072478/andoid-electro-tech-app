package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.SwineburneDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.SwineburneDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.SwineburneFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.SwineburneDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class SwineburneFindAllService extends SwineburneFindAbstract {

    @Override
    public HashMap action(String request, SwineburneDTO swineburneDTO, ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result

            HashMap result = new HashMap();

            SwineburneDAO dao = new SwineburneDAOImpl(ctx.getContext());
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
                return nextValue.action( request,  swineburneDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
