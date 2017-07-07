package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details;


import com.fiki.math.lab.mathlab.domain.model.ContactDetails;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.ContactDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.ContactDetailsDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.ContactDetailsFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContactDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class ContactDetailFindByIdService extends ContactDetailsFindAbstract {

    @Override
    public HashMap action(String request, ContactDetailsDTO contactDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            ContactDetails result;
            //put DAO here, and return result
            ContactDetailsDAO dao = new ContactDetailsDAOImpl(ctx.getContext());
            result = dao.findById(contactDTO.getStaffId());

            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  contactDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}
