package com.fiki.n3.technology.electro.electrotechn3application.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;
import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.RegisterActivity;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.login.details.LoginDetailsFindService;
import com.fiki.n3.technology.electro.electrotechn3application.services.role.RoleFindService;


/**
 * Created by Roman on 2016/05/12.
 */
public class LoginActivity extends Activity implements ServiceResultReceiver.Receiver {

    private ServiceResultReceiver receiver;
    private EditText txtUsername, txtPassword;
    private Button btnSubmit,btnRegister,btnExit;
    private LoginDetailsDTO loginDetailsDTO;
    private String request = "";
    private LoginDetails loggedInAs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        receiver = new ServiceResultReceiver(new Handler());
        receiver.setReceiver(this);


        //initiate components
        initiateComponents();
        submitOnclick();
        registerButton();

    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO) resultData.getSerializable("ServiceTag");

        if (request.equals("loginDetails")) {
            request = "";

            if (resultDTOFound == null) {

                dialogBox("Login Error", "Null point Exception");

                txtPassword.setText("");
                request = "";

            } else {


                if (resultDTOFound.getSResult().equals("find by username")) {
                    LoginDetails object = (LoginDetails) resultDTOFound.getResult().get("result");


                    if (object.getLoginId() == -1) {

                        dialogBox("Login Error", "User doesn't exist");

                        txtPassword.setText("");
                        request = "";

                    } else if (object.getUsername().equals(txtUsername.getText().toString()) &&
                            object.getPassword().equals(txtPassword.getText().toString())) {

                        loggedInAs = object;
                        // dialogBox("Success","welcome"+object.getDisplayName() + object.getRoleId());

                        getRole(object);

                    }
                    else{
                        dialogBox("Login error","Wrong pass word");
                        txtPassword.setText("");
                    }

                }
            }
        } else if (request.equals("role")) {

            //RoleDTO objectDto = (RoleDTO) resultDTOFound.getResult().get("result");
            Role role = (Role) resultDTOFound.getResult().get("result");


            if (role.getRoleId() == -1) {

                dialogBox("LoginError", "User does not exist");
                txtPassword.setText("");
                request = "";

            } else {

                dialogBox("Role", "rolesucces :" + role.getRole());

                //condition on the menus (staff,or user)
                if(role.getRole().equalsIgnoreCase("user"))
                {
                    Intent intent = new Intent(LoginActivity.this,StartMenuActivity.class);
                    intent.putExtra("loggedInUser",loggedInAs);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
    private void getRole(LoginDetails object){

        try {

            Role role = new Role();
            role.setRoleId(object.getRoleId());

            request="role";
            RoleDTO roleDTO = new RoleDTO.Builder()
                    .role(role)
                    .roleId(object.getRoleId())
                    .build();

            Intent service;
            service = new Intent(LoginActivity.this, RoleFindService.class);

            //put extra values into the intent, to be send to the called service
            service.putExtra("requestTag", "find by id");
            service.putExtra("dtoTag", roleDTO);
            service.putExtra("receiverTag", receiver);

            startService(service);
        }
        catch(Exception excep)
        {

        }

    }

    private void submitOnclick()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txtUsername.getText().toString().equals("") || txtPassword.getText().toString().equals("")) {
                    dialogBox("Login error","Please enter in login details");

                }
                else {
                    if (txtUsername.getText().toString().equals("fiki") && txtPassword.getText().toString().equals("awesome")) {
                        //admin by pass

                        Intent in = new Intent(LoginActivity.this, AdministratorActivity.class);
                        startActivity(in);
                        finish();

                    }
                    else {

                        request="loginDetails";
                        LoginDetails loginDetails = new LoginDetails();
                        loginDetails.setUsername(txtUsername.getText().toString());

                        loginDetailsDTO = new LoginDetailsDTO.Builder()
                                .loginDetails(loginDetails)
                                .build();

                        try {
                            Intent service;
                            service = new Intent(LoginActivity.this, LoginDetailsFindService.class);

                            //put extra values into the intent, to be send to the called service
                            service.putExtra("requestTag", "find by username");
                            service.putExtra("dtoTag", loginDetailsDTO);
                            service.putExtra("receiverTag", receiver);

                            startService(service);
                        }
                        catch(Exception excep)
                        {

                        }

                    }
                }

            }
        });
    }

    private void registerButton()
    {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initiateComponents()
    {

        txtUsername = (EditText)findViewById(R.id.username_login_form);
        txtPassword = (EditText)findViewById(R.id.password_login_form);

        btnSubmit = (Button)findViewById(R.id.login_form_submit_button);
        btnRegister = (Button)findViewById(R.id.login_form_register_button);
        btnExit = (Button)findViewById(R.id.login_form_exit);
    }

    private void dialogBox(String title,String message)
    {
        Dialog d = new Dialog(LoginActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(LoginActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();
    }
}