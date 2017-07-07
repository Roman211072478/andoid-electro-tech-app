package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.StaffDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.StaffDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.StaffCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class StaffDeleteTableService extends StaffCUDAbstract {

    @Override
    public String action(String request, StaffDTO staffDTO, ContextDTO ctx) {


        if(request.equals("drop"))
        {
            //put DAO here, and return result
            StaffDAO dao = new StaffDAOImpl(ctx.getContext());

            //if one use all the CRUD methods and return a object, this
            //will decrese errors
            int result = dao.deleteTable();


            return String.valueOf(result);

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  staffDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}
