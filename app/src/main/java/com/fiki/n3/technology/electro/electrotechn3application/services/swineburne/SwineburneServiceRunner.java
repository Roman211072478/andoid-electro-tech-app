package com.fiki.n3.technology.electro.electrotechn3application.services.swineburne;

import android.content.Context;
import android.content.Intent;

import com.fiki.n3.technology.electro.electrotechn3application.dto.SwineburneDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/26.
 */
public class SwineburneServiceRunner {

    private Context ctx;

    public SwineburneServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(SwineburneDTO dto, ServiceResultReceiver receiver, String request)
    {
        Intent service = new Intent(ctx, SwineburneFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(SwineburneDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, SwineburneCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( SwineburneDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( SwineburneDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(SwineburneDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(SwineburneDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(SwineburneDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(SwineburneDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }
}
