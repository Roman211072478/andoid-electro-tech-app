package com.fiki.n3.technology.electro.electrotechn3application.services.back.emf;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.back.emf.BackEmfCUDChainFactory;


/**
 * Created by Roman on 2016/05/01.
 *
 * CUD (Create, update, delete ) this service will perform these tasks based on request
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
public class BackEmfCUDService extends IntentService {

    private ResultDTO resultDTO;
    private Bundle bundle;
    private BackEmfDTO backEmfDTO;
    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private ContextDTO contextDTO ;

    public BackEmfCUDService() {

        super("MyIntentService");
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public BackEmfCUDService getService() {
            return BackEmfCUDService.this;
        }}

    @Override
    protected void onHandleIntent(Intent intent) {


        runService(intent);
    }
    private void runService(Intent intent)
    {
        contextDTO = new ContextDTO(getApplicationContext());

        ResultReceiver receiver = intent.getParcelableExtra("receiverTag");//send ur receive through
        backEmfDTO = (BackEmfDTO)intent.getSerializableExtra("dtoTag");
        String request = (String)intent.getStringExtra("requestTag");

         bundle = new Bundle();//what we sending back to main activity

        //Now you need to pass a context down to repo
        backEmfDTO = new BackEmfDTO.Builder()
                .copy(backEmfDTO)

                .build();


        if(!request.isEmpty())
        {
            String sResult = BackEmfCUDChainFactory.performRequest(request, backEmfDTO,contextDTO);//returns primary key or -1 for unsuccessFul

            resultDTO = new ResultDTO.Builder()
                    .request(request)
                    .sResult(sResult)//whether passed or not
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
