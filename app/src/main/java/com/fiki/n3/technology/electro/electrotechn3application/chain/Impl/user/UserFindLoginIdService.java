package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.UserDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.UserDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.UserFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class UserFindLoginIdService extends UserFindAbstract {

    @Override
    public HashMap action(String request, UserDTO userDTO, ContextDTO ctx) {
        if(request.equals("find by login id"))
        {

            UserDTO result;
            //put DAO here, and return result
            UserDAO dao = new UserDAOImpl(ctx.getContext());
            result = dao.findByLoginId(userDTO.getLoginId());


            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  userDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}
