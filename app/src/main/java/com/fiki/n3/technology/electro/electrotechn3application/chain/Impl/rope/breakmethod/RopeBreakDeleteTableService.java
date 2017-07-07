package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.RopeBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RopeBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RopeBreakCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class RopeBreakDeleteTableService extends RopeBreakCUDAbstract {

    @Override
    public String action(String request, RopeBreakDTO ropeBreakDTO, ContextDTO ctx) {


        if(request.equals("drop"))
        {
            //put DAO here, and return result
            RopeBreakDAO dao = new RopeBreakDAOImpl(ctx.getContext());
            int result = dao.deleteTable();

            return String.valueOf(result);

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  ropeBreakDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}
