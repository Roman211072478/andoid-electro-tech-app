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
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.tutorial.TutorialCUDService;
import com.fiki.n3.technology.electro.electrotechn3application.services.users.UserCUDService;
import com.fiki.n3.technology.electro.electrotechn3application.services.users.UserFindService;

/**
 * Created by Roman on 2016/05/15.
 */
public class StartMenuActivity extends Activity implements ServiceResultReceiver.Receiver{

    private ServiceResultReceiver receiver;
    private Button continueBtn,newGameBtn;
    private LoginDetailsDTO loginDetailsDTO;
    private UserDTO userDTO;
    private TextView displayName;
    private LoginDetails loginUser;
    private User user;
    private String request;
    private Boolean isUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu_layout);

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);

        intiComponents();
        Bundle bundle = getIntent().getExtras();

         loginUser = new LoginDetails();
        try {
                 loginUser = (LoginDetails)bundle.getSerializable("loggedInUser");

            UserDTO userDto = new UserDTO.Builder()
                    .roleId(loginUser.getRoleId())
                    .loginId(loginUser.getLoginId())
                    .displayName(loginUser.getDisplayName())
                    .build();

            displayName.setText("Welcome "+loginUser.getDisplayName().toString());

            checkIfUserExist(userDto);
            }
        catch(Exception e)
         {
             dialogBox("error menu",e.getMessage());

         }


        onClicks();


     /*   //initiate components
        initiateComponents();
        submitOnclick();
        registerButton();*/

    }




    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        dialogBox("Result reciver", "your saved game no longer exist start a new game instead");
        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO) resultData.getSerializable("ServiceTag");


        if(request.equals("tutorial"))
        {
            request = "";

            TutorialDTO object = (TutorialDTO) resultDTOFound.getResult().get("result");
            if (object.getTutorialId() == -1) {

                dialogBox("Tutorial Error", "your saved game no longer exist start a new game instead");
            }
            else {
                chooseTutorial(object);
            }

            }
        else if(request.equals("user")){
            request = "";
            if (resultDTOFound == null) {

              /*  if(resultDTOFound.getRequest().equals("find all"))
                {
                    if(resultDTOFound.getSResult().equals("-1"))
                    {
                        dialogBox("user error","no users");
                    }
                    else{

                    }*/
                }
                else if(resultDTOFound.getRequest().equals("find by login id"))
                {
                    UserDTO object = (UserDTO)resultDTOFound.getResult().get("result");

                    if(object.getUserId() == -1)
                    {
                        continueBtn.setEnabled(false);
                    }
                    else {

                        userDTO = new UserDTO.Builder()
                                .copy(object)
                                .displayName(loginUser.getDisplayName())
                                .build();

                        isUser = true;

                    }
                }
            }
        else if(request.equals("delete")){
            request ="";

            if(resultDTOFound.getSResult().equals("-1"))
            {
                dialogBox("New game","could not be add to your account");
            }
            else{

                isUser = false;
                UserDTO dto = new UserDTO.Builder()
                        .loginId(loginUser.getLoginId())
                        .roleId(loginUser.getRoleId())
                        .displayName(loginUser.getDisplayName())
                        .build();


                Intent intent = new Intent(StartMenuActivity.this, ExerciseMainMenuActivity.class);
                intent.putExtra("loggedInUser", dto);//doesn't contain user id, coz new game
                startActivity(intent);
            }

        }

   }





    private void checkIfUserExist(UserDTO objectDTO)
    {
        request = "user";
        //initiate for another service
        Intent service = new Intent(StartMenuActivity.this, UserFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", "find by login id");
        service.putExtra("dtoTag", objectDTO);
        service.putExtra("receiverTag", receiver);
        startService(service);
    }
    private void onClicks()
    {
        continueBtn();
        newGame();
    }

    private void newGame() {

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO dto = new UserDTO.Builder().build();

                if(isUser == true)
                {
                    request = "delete";

                    Intent service = new Intent(StartMenuActivity.this, UserCUDService.class);

                    //put extra values into the intent, to be send to the called service
                    service.putExtra("requestTag", "delete");
                    service.putExtra("dtoTag", userDTO);
                    service.putExtra("receiverTag", receiver);
                    startService(service);

                }
                else {

                    dto = new UserDTO.Builder()
                            .loginId(loginUser.getLoginId())
                            .roleId(loginUser.getRoleId())
                            .displayName(loginUser.getDisplayName())
                            .build();


                    Intent intent = new Intent(StartMenuActivity.this, ExerciseMainMenuActivity.class);
                    intent.putExtra("loggedInUser", dto);//doesn't contain user id, coz new game
                    startActivity(intent);
                }
            }
        });

    }

    private void chooseTutorial(TutorialDTO dto) {

        String tutorialScreen = dto.getScreenId();

        if(tutorialScreen.trim().equals("") || tutorialScreen == null)
        {
           dialogBox("Tutorial","error with the saved game name");

        }
        else {

            Intent activity = new Intent();
            activity.putExtra("loggedInUser",userDTO);

            if(tutorialScreen.equalsIgnoreCase("BackEmf"))
            {
                    goToBackEmf(activity);
            }
            else if(tutorialScreen.equalsIgnoreCase("MachineEmf"))
            {
                    goToMachineEmf(activity);

            } else if(tutorialScreen.equalsIgnoreCase("PronyBreak"))
            {
                    goToPronyBreak(activity);
            }
            else if(tutorialScreen.equalsIgnoreCase("RopeBreak"))
            {
                    goToRopeBreak(activity);
            }
            else if(tutorialScreen.equalsIgnoreCase("Swineburne"))
            {
                    goToSwineBurne(activity);
            }
            else if(tutorialScreen.equalsIgnoreCase("Torque"))
            {
                    goToToque(activity);
            }
            else {
                dialogBox("Tutorial Error","The screen id doesnt exist,please star new game instead");
            }
        }
    }

    private void goToToque(Intent intent) {

        intent.setClass(StartMenuActivity.this,TorqueActivity.class);
        startActivity(intent);
    }

    private void goToSwineBurne(Intent intent) {
        intent.setClass(StartMenuActivity.this,SwineburneActivity.class);
        startActivity(intent);
    }

    private void goToRopeBreak(Intent intent) {
        intent.setClass(StartMenuActivity.this,RopeExerciseActivity.class);
        startActivity(intent);
    }

    private void goToPronyBreak(Intent intent) {
        intent.setClass(StartMenuActivity.this,PronyBrakeExerciseActivity.class);
        startActivity(intent);
    }

    private void goToMachineEmf(Intent intent) {
        intent.setClass(StartMenuActivity.this,MachineEmfActivity.class);
        startActivity(intent);
    }

    private void goToBackEmf(Intent intent) {
        intent.setClass(StartMenuActivity.this,BackEmfExerciseLayout.class);
        startActivity(intent);
    }

    private void continueBtn() {

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request = "tutorial";
                if(isUser == true) {
                    TutorialDTO tutorialDTO = new TutorialDTO.Builder()
                            .TutorialId(userDTO.getTutorialId())
                            .build();

                    //Find ur tutorial
                    Intent service = new Intent(StartMenuActivity.this, TutorialCUDService.class);

                    //put extra values into the intent, to be send to the called service
                    service.putExtra("requestTag", "find by login id");
                    service.putExtra("dtoTag", tutorialDTO);
                    service.putExtra("receiverTag", receiver);
                    startService(service);
                }
            }
        });

    }

    private void intiComponents()
    {
        displayName = (TextView) findViewById(R.id.start_menu_display_name);
        continueBtn = (Button)findViewById(R.id.start_menu_continue_btn);
        newGameBtn = (Button)findViewById(R.id.start_menu_new_game_btn);
    }
    private void dialogBox(String title,String message)
    {
        Dialog d = new Dialog(StartMenuActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(StartMenuActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }
}
