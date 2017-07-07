package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.StaffDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.StaffDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.StaffFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class StaffFindLoginIdService extends StaffFindAbstract {

    @Override
    public HashMap action(String request, StaffDTO staffDTO, ContextDTO ctx) {
        if(request.equals("find by login id"))
        {
            StaffDTO result;
            //put DAO here, and return result
            StaffDAO dao = new StaffDAOImpl(ctx.getContext());
            result = dao.findByLoginId(staffDTO.getLoginId());

            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  staffDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}
