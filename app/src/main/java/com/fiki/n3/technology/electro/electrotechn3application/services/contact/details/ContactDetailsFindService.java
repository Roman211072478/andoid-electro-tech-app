package com.fiki.n3.technology.electro.electrotechn3application.services.contact.details;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.fiki.roman.andoirdmathlab.dto.ContactDetailsDTO;
import com.fiki.roman.andoirdmathlab.dto.ContextDTO;
import com.fiki.roman.andoirdmathlab.dto.ResultDTO;
import com.fiki.roman.andoirdmathlab.factories.chain.factories.contact.details.ContactDetailsFindChainFactory;
import com.fiki.roman.andoirdmathlab.receiver.ServiceResultReceiver;

import java.util.HashMap;

/**
 * Created by Roman on 2016/05/01.
 *
 * Reason for Intent Service
 *
 * My services are used to access the database.
 * A connection is opened to the database when we accessing it, and close when request is done.
 * This limits database interactions makes the application makes one connection to the database
 * at a time.
 * Having a service open a connection twice could result in an error or a delay in application
 * performance. For this reason, intent services on DAO’s are suitable in order for all
 * the services to be queued instead of running at the same time.
 * Separating tasks from the main thread is always a good idea, as it allows the application to
 * keep its performance and whatever happens on the other thread not to affect the main thread.
 * There will be some service which will rely on a previous service in order to populate a
 * value object, which consist of multiple entities.
 * For this reason one would want database access services to be queued.
 */
public class ContactDetailsFindService extends IntentService {

    private ResultDTO resultDTO;
    private Bundle bundle;
    private ContextDTO ctx;

    public ContactDetailsFindService() {

        super("UserFindService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        runService(intent);
    }
    private void runService(Intent intent)
    {
        ctx = new ContextDTO(getApplicationContext());
        ContactDetailsDTO contactDetailsDTO;

        ServiceResultReceiver receiver = intent.getParcelableExtra("receiverTag");//send ur receive through
        contactDetailsDTO = (ContactDetailsDTO)intent.getSerializableExtra("dtoTag");
        String request = (String)intent.getStringExtra("requestTag");

        bundle = new Bundle();//what we sending back to main activity

        if(request.equals("find all"))
        {

            HashMap resultmap = ContactDetailsFindChainFactory.performRequest(request, contactDetailsDTO,ctx);

            HashMap result = (HashMap)resultmap.get("result");
            if(!resultmap.containsKey("error")) {
                String mapSResult = (String) resultmap.get("request");


                resultDTO = new ResultDTO.Builder()
                        .request(request)
                        .sResult(mapSResult)
                        .result(resultmap)
                        .build();
                bundle.putSerializable("ServiceTag", resultDTO);
            }
            else
            {
                resultDTO = new ResultDTO.Builder()
                        .request(request)
                        .sResult("-1")
                        .result(null)
                        .build();
                bundle.putSerializable("ServiceTag", resultDTO);
            }
        }
        else if( request.equals("find by id"))
        {
            HashMap resultmap = ContactDetailsFindChainFactory.performRequest(request, contactDetailsDTO,ctx);
            String mapSResult = (String)resultmap.get("request");

            resultDTO = new ResultDTO.Builder()
                    .request(request)
                    .sResult(mapSResult)
                    .result(resultmap)
                    .build();
            bundle.putSerializable("ServiceTag", resultDTO);
        }
        else
        {
            resultDTO = new ResultDTO.Builder()
                    .request(request)
                    .sResult("failed")//whether passed or not
                    .build();
            bundle.putSerializable("ServiceTag", resultDTO);
        }


        receiver.send(0,bundle);
    }


    public Bundle runForTesting(Intent intent)
    {
        runService(intent);
        return bundle;
    }
}
