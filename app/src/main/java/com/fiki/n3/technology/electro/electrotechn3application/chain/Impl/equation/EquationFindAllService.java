package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.EquationDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.EquationDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.EquationFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class EquationFindAllService extends EquationFindAbstract {

    @Override
    public HashMap action(String request, EquationDTO equationDTO, ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result

            HashMap result = new HashMap();

            EquationDAO dao = new EquationDAOImpl(ctx.getContext());
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
                return nextValue.action( request,  equationDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
