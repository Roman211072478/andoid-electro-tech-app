package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details;



import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.LoginDetailsDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.LoginDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.LoginDetailsFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class LoginDetailsFindAllService extends LoginDetailsFindAbstract {

    @Override
    public HashMap action(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO ctx) {
        if(request.equals("find all"))
        {
            //put DAO here, and return result

            HashMap result = new HashMap();

             LoginDetailsDAO dao = new LoginDetailsDAOImpl(ctx.getContext());
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
                return nextValue.action( request,  loginDetailsDTO,ctx);
            }

            HashMap map = new HashMap();
            map.put("request", "invalid");

            return map;
        }
    }
}
