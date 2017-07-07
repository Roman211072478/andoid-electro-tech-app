package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.BackEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.BackEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.BackEmfFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class BackEmfFindByIdService extends BackEmfFindAbstract {

    @Override
    public HashMap action(String request, BackEmfDTO backEmfDTO , ContextDTO contextDTO) {
        if(request.equals("find by id"))
        {

            BackEmf result;
            //put DAO here, and return result
            BackEmfDAO dao = new BackEmfDAOImpl(contextDTO.getContext());
            result = dao.findById(backEmfDTO.getTutorialId());



            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  backEmfDTO,contextDTO);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}
