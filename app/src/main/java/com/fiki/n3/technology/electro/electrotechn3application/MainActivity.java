package com.fiki.n3.technology.electro.electrotechn3application;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;
import com.fiki.math.lab.mathlab.factories.BackEmfFactory;
import com.fiki.math.lab.mathlab.factories.Impl.BackEmfFactoryImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.BackEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.BackEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.back.emf.BackEmfCUDService;


public class MainActivity extends Activity implements ServiceResultReceiver.Receiver {
    final BackEmfDAO dao = new BackEmfDAOImpl(this);

    private BackEmfFactory factory = BackEmfFactoryImpl.getInstance();

    private Button bCreate;
    private Button bView;
    private Button backEmfExercise;
    public ServiceResultReceiver myReceiver;


    /**
     *
     */
    /*@Override
    public void onPause() {
        super.onPause();

        this.finish();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new ServiceResultReceiver(new Handler());

        myReceiver.setReceiver(this);

        bCreate = (Button)findViewById(R.id.btnCreate);
        bView = (Button)findViewById(R.id.btnView);
        backEmfExercise = (Button)findViewById(R.id.btnBackEmfTransportToLanding);
        initCreateBtn();
        initViewBtn();
        initBackEmfTransporter();
    }
    private void initCreateBtn()
    {
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double armatureCurrent = 15;
                double resistance = 79;
                double volts = 210;
                double emf = 60;

                BackEmf backEmf = factory.createBackEmf(armatureCurrent, resistance, volts, emf);
                try {


                    Intent service;
                    service = new Intent(MainActivity.this,BackEmfCUDService.class);

                    BackEmfDTO dto = new BackEmfDTO.Builder()
                            .backEmf(backEmf)
                            .tutorialId(12)
                            .build();

                    service.putExtra("requestTag", "create");
                    service.putExtra("dtoTag", dto );
                    service.putExtra("receiverTag", myReceiver);


                    startService(service);

                   // dao.create(backEmf, 98);

                  /*  Dialog d = new Dialog(MainActivity.this);
                    d.setTitle("title Successful");
                    TextView tv = new TextView(MainActivity.this);

                    tv.setText("congrats ");
                    d.setContentView(tv);
                    d.show();*/
                } catch (Exception ex) {
                    //System.out.print(ex.getMessage());
                    Dialog d = new Dialog(MainActivity.this);
                    d.setTitle("title error");
                    TextView tv = new TextView(MainActivity.this);
                    String error = ex.toString();
                    tv.setText("Erorr " + error);
                    d.setContentView(tv);
                    d.show();

                }

  /*              armatureCurrent = 10;
                resistance = 20;
                volts = 30;
                emf = 40;

                backEmf = factory.createBackEmf(armatureCurrent, resistance, volts, emf);

                try {


                    Intent service;
                    service = new Intent(MainActivity.this, BackEmfCUDService.class);

                    BackEmfDTO dto = new BackEmfDTO.Builder()
                            .context(MainActivity.this)
                            .backEmf(backEmf)
                            .tutorialId(87)
                            .build();

                    service.putExtra("requestTag", "create");
                    service.putExtra("dtoTag", dto );
                    service.putExtra("receiverTag", myReceiver);


                    startService(service);

                   // dao.create(backEmf, 22);

                    Dialog d = new Dialog(MainActivity.this);
                    d.setTitle("title Successful");
                    TextView tv = new TextView(MainActivity.this);

                    tv.setText("congrats ");
                    d.setContentView(tv);
                    d.show();
                } catch (Exception ex) {
                    //System.out.print(ex.getMessage());
                    Dialog d = new Dialog(MainActivity.this);
                    d.setTitle("title error");
                    TextView tv = new TextView(MainActivity.this);
                    String error = ex.toString();
                    tv.setText("Erorr " + error);
                    d.setContentView(tv);
                    d.show();

                }
*/

            }
        });
    }
    private void initViewBtn()
    {
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent("com.fiki.roman.andoirdmathlab.RESULTVEW");
                startActivity(in);

            }
        });
    }
    private void initBackEmfTransporter()
    {

        backEmfExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this, BackEmfExerciseLayout.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");

        String result = resultDTOFound.getSResult();
        Dialog d = new Dialog(MainActivity.this);
        d.setTitle("result "+result);
        TextView tv = new TextView(MainActivity.this);

        tv.setText("insert successful ");
        d.setContentView(tv);
        d.show();

    }
}
