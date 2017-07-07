package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.LoginDetailsDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.LoginDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.LoginDetailsCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class LoginDetailsDeleteTableService extends LoginDetailsCUDAbstract {
 @Override
    public String action(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO ctx) {
	

        if(request.equals("drop"))
        {
            //put DAO here, and return result
            LoginDetailsDAO dao = new LoginDetailsDAOImpl(ctx.getContext());
            int result = dao.deleteTable();

            return String.valueOf(result);            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  loginDetailsDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}
