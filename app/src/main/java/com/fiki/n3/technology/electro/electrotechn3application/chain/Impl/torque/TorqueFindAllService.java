package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TorqueDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TorqueDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TorqueFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class TorqueFindAllService extends TorqueFindAbstract {

    @Override
    public HashMap action(String request, TorqueDTO torqueDTO, ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result

            HashMap result = new HashMap();

            TorqueDAO dao = new TorqueDAOImpl(ctx.getContext());
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
                return nextValue.action( request,  torqueDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
