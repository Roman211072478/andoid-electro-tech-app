package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.contact.details;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details.ContactDetailFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details.ContactDetailFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.ContactDetailsFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContactDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class ContactDetailsFindChainFactory {

    public static HashMap performRequest(String request, ContactDetailsDTO contactDetailsDTO, ContextDTO ctx){
        ContactDetailsFindAbstract chain = setUpChain();
        return chain.action(request,contactDetailsDTO,ctx);
    }

    public static ContactDetailsFindAbstract setUpChain(){

        ContactDetailsFindAbstract a = new ContactDetailFindByIdService();
        ContactDetailsFindAbstract b = new ContactDetailFindAllService();

        a.setNext(b);

        return a;
    }
}
