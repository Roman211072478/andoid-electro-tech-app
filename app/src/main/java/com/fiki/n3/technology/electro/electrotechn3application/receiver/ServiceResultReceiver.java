package com.fiki.n3.technology.electro.electrotechn3application.receiver;

/**
 * Created by Roman on 2016/05/01.
 */
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class ServiceResultReceiver extends ResultReceiver {
    private Receiver myReceiver;

    public ServiceResultReceiver(Handler handler) {
        super(handler);
        // TODO Auto-generated constructor stub
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);

    }

    public void setReceiver(Receiver receiver) {
        myReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        if (myReceiver != null) {
            myReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
