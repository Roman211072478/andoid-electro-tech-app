package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.UserDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.UserDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.UserCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class UserDeleteTableService extends UserCUDAbstract {

    @Override
    public String action(String request, UserDTO userDTO, ContextDTO ctx) {


        if(request.equals("drop"))
        {
            //put DAO here, and return result
            UserDAO dao = new UserDAOImpl(ctx.getContext());

            //if one use all the CRUD methods and return a object, this
            //will decrese errors
            int result = dao.deleteTable();


            return String.valueOf(result);

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  userDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}
