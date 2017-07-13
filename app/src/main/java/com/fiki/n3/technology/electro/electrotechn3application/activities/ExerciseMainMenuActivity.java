package com.fiki.n3.technology.electro.electrotechn3application.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;
import com.fiki.math.lab.mathlab.domain.model.User;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.emf.MachineEmfActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.pronybreak.PronyBrakeExerciseActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.rope.RopeExerciseActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.swineburne.SwineburneActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.torque.TorqueActivity;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/16.
 */
public class ExerciseMainMenuActivity extends Activity implements ServiceResultReceiver.Receiver{

        private ServiceResultReceiver receiver;
        private Button btnBackEmf,btnMachineEmf,btnProny,btnRope,btnSwine,btnTorque;
        private LoginDetailsDTO loginDetailsDTO;
        private UserDTO userDTO;
        private TextView displayName;
        private LoginDetails loginUser;
        private User user;
        private String request;
        private Intent activity;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.exercise_main_menu_layout);

            receiver = new ServiceResultReceiver(new Handler());
            receiver.setReceiver(this);

            Bundle bundle = getIntent().getExtras();

            try {
                userDTO = (UserDTO) bundle.getSerializable("loggedInUser");

                dialogBox("Game", "This is a new game");
            }
            catch(Exception e)
            {
                dialogBox("error menu",e.getMessage());
            }

            intiComponents();
            onClicks();
        }

    private void onClicks() {
        backEmfOnClick();
        machineEmfOnClick();
        pronyOnClick();
        ropeBreakOnClick();
        swineburneOnClick();
        torqueOnClick();
    }

    private void torqueOnClick() {
        btnTorque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity = new Intent(ExerciseMainMenuActivity.this,TorqueActivity.class);
                activity.putExtra("loggedInUser",userDTO);
                startActivity(activity);
            }
        });
    }

    private void swineburneOnClick() {
        btnSwine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = new Intent(ExerciseMainMenuActivity.this,SwineburneActivity.class);
                activity.putExtra("loggedInUser",userDTO);
                startActivity(activity);
            }
        });

    }

    private void ropeBreakOnClick() {
        btnRope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = new Intent(ExerciseMainMenuActivity.this,RopeExerciseActivity.class);
                activity.putExtra("loggedInUser",userDTO);
                startActivity(activity);
            }
        });

    }

    private void pronyOnClick() {
        btnProny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = new Intent(ExerciseMainMenuActivity.this,PronyBrakeExerciseActivity.class);
                activity.putExtra("loggedInUser",userDTO);
                startActivity(activity);
            }
        });

    }

    private void machineEmfOnClick() {
        btnMachineEmf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = new Intent(ExerciseMainMenuActivity.this,MachineEmfActivity.class);
                activity.putExtra("loggedInUser",userDTO);
                startActivity(activity);
            }
        });

    }

    private void backEmfOnClick() {
        btnBackEmf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = new Intent(ExerciseMainMenuActivity.this,BackEmfExerciseLayout.class);
                activity.putExtra("loggedInUser",userDTO);
                startActivity(activity);
            }
        });
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

    }
    private void intiComponents() {

        btnBackEmf = (Button)findViewById(R.id.backemf_btn_mainMenu);
        btnMachineEmf = (Button)findViewById(R.id.machine_btn_mainMenu);
        btnProny = (Button)findViewById(R.id.prony_btn_mainMenu);
        btnRope = (Button)findViewById(R.id.rope_btn_mainMenu);
        btnSwine = (Button)findViewById(R.id.swineburne_btn_mainMenu);
        btnTorque = (Button)findViewById(R.id.torque_btn_mainMenu);

    }


    private void dialogBox(String title,String message)
    {
        Dialog d = new Dialog(ExerciseMainMenuActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(ExerciseMainMenuActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }
}
