package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation;


import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.EquationDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.EquationDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.EquationFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class EquationFindByIdService extends EquationFindAbstract {

    @Override
    public HashMap action(String request, EquationDTO equationDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            Equation result;
            //put DAO here, and return result
            EquationDAO dao = new EquationDAOImpl(ctx.getContext());
            result = dao.findById(equationDTO.getEquationId());


            HashMap map = new HashMap();
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
            map.put("request","invalid");

            return map;
        }
    }


}
