package com.fiki.n3.technology.electro.electrotechn3application.services.rope.breakmethod;

import android.content.Context;
import android.content.Intent;

import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/26.
 */
public class RopeBreakServiceRunner {
    private Context ctx;

    public RopeBreakServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(RopeBreakDTO dto, ServiceResultReceiver receiver, String request)
    {
        Intent service = new Intent(ctx, RopeBreakFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(RopeBreakDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, RopeBreakCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( RopeBreakDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( RopeBreakDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(RopeBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(RopeBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(RopeBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(RopeBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }
}
