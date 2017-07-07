package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.RoleDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RoleDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RoleFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class RoleFindByIdService extends RoleFindAbstract {

    @Override
    public HashMap action(String request, RoleDTO roleDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            Role result;
            //put DAO here, and return result
            RoleDAO dao = new RoleDAOImpl(ctx.getContext());
            result = dao.findById(roleDTO.getRoleId());

            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  roleDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}
