package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.LoginDetailsDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.LoginDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.LoginDetailsFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class LoginDetailsFindByUsernameService extends LoginDetailsFindAbstract {

    @Override
    public HashMap action(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO ctx) {
        if(request.equals("find by username"))
        {

            LoginDetails result = new LoginDetails();
            //put DAO here, and return result
           LoginDetailsDAO dao = new LoginDetailsDAOImpl(ctx.getContext());
            try{
                result = dao.findByUserName(loginDetailsDTO.getLoginDetails().getUsername());
            }
            catch (NullPointerException nullExcept)
            {
                result.setUserId(-1);
            }


            HashMap map = new HashMap();
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
            map.put("request","invalid");

            return map;
        }
    }


}
