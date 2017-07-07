package com.fiki.n3.technology.electro.electrotechn3application.services.tutorial;

import android.content.Context;
import android.content.Intent;

import com.fiki.roman.andoirdmathlab.dto.TutorialDTO;
import com.fiki.roman.andoirdmathlab.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/19.
 */
public class TutorialServiceRunner {

    private Context ctx;

    public TutorialServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(TutorialDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, TutorialFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(TutorialDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, TutorialCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( TutorialDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( TutorialDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(TutorialDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(TutorialDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(TutorialDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(TutorialDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }
}
