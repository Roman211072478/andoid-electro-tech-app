package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.SwineburneDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.SwineburneDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.SwineburneCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.SwineburneDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class SwineburneDeleteService extends SwineburneCUDAbstract {
    
	
    @Override
    public String action(String request, SwineburneDTO swineburneDTO, ContextDTO ctx) {


        if(request.equals("delete"))
        {
            //put DAO here, and return result
            SwineburneDAO dao = new SwineburneDAOImpl(ctx.getContext());
            int result = dao.delete(swineburneDTO.getTutorialId());

            return String.valueOf(result) ;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  swineburneDTO,ctx);
            }
            return "invalid";
        }

    }
}
