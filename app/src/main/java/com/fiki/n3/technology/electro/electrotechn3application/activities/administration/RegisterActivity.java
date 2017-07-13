package com.fiki.n3.technology.electro.electrotechn3application.activities.administration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;
import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.login.details.LoginDetailsCUDService;
import com.fiki.n3.technology.electro.electrotechn3application.services.role.RoleCUDService;
import com.fiki.n3.technology.electro.electrotechn3application.services.role.RoleFindService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roman on 2016/05/14.
 */
public class RegisterActivity extends Activity implements ServiceResultReceiver.Receiver {

    private ServiceResultReceiver receiver;
    private EditText txtUsername, txtPassword,txtRetypePassword,txtDisplay;
    private Button btnSubmit;
    private LoginDetailsDTO loginDetailsDTO;
    private String request ="";
    private Integer roleId;

    private List<Role> roleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        receiver = new ServiceResultReceiver(new Handler());

        receiver.setReceiver(this);

        initButtons();
        setRegisterOnclick();

        preloadCreate();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }



    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");

        if (resultDTOFound == null) {


        }
        else{
            if(request.equals("role")) {

                if(resultDTOFound.getRequest().equals("find all"))
                {
                    roleList = listPopulateHander(resultDTOFound);
                    findRoleId(roleList);

                }
                else if((resultDTOFound.getRequest().equals("create"))) {

                    if(resultDTOFound.getSResult().equals("-1"))
                    {

                        Toast.makeText(RegisterActivity.this, "internal error on role", Toast.LENGTH_SHORT);
                        populateService();
                    }else{
                        Toast.makeText(RegisterActivity.this, "User role ready", Toast.LENGTH_SHORT);
                        populateService();
                    }
                }

            }
            else if(request.equals("register")) {
                request = "";
                if(resultDTOFound.getRequest().equals("create"))
                {

                    if(resultDTOFound.getSResult().equals("-1"))
                    {

                        txtPassword.setText("");

                        txtRetypePassword.setText("");
                        dialogOutput("Error","Please try a unique names");
                    }
                    else{



                        txtPassword.setText("");
                        txtRetypePassword.setText("");
                        txtUsername.setText("");
                        txtDisplay.setText("");

                        Intent intent = new Intent(RegisterActivity.this,RegisterResultActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }


            }
        }

    }

    private void findRoleId(List<Role> list)
    {
        if(list != null)
        {
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).getRole().equals("user"))
                {
                    roleId = list.get(i).getRoleId();
                }
            }
        }
        else{
            Toast.makeText(RegisterActivity.this,"No end users allowed",Toast.LENGTH_LONG);
        }


    }

    private void preloadCreate()
    {   /*
            Create a user role, and if it exist do nothing
        */
        request = "role";
        String role = "user";
        String description = "end user";

        Role roleObject = new Role();
        roleObject.setRole(role);
        roleObject.setDescription(description);

        RoleDTO dto = new RoleDTO.Builder()
                .role(roleObject)
                .build();

        try {
            Intent service;
            service = new Intent();
            service.setClass(RegisterActivity.this, RoleCUDService.class);

            //put extra values into the intent, to be send to the called service
            service.putExtra("requestTag", "create");
            service.putExtra("dtoTag", dto);
            service.putExtra("receiverTag", receiver);

            startService(service);
        } catch (Exception e) {

            dialogOutput("Error", e.getMessage());

        }
    }


    @Nullable
    private List<Role> listPopulateHander(ResultDTO resultDTO)//in receiver
    {
        if(resultDTO.getSResult().equals("-1"))
        {
            Toast.makeText(RegisterActivity.this,"internal error",Toast.LENGTH_SHORT);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<Role> roles = new ArrayList<Role>();

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();

            myList = (HashMap) objectMap.get("result");

            //Object
            Role role;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    role = (Role) me.getValue();

                    roles.add(role);
                    list.add(role.getRoleId() + " | " + role.getRole() + " | " + role.getDescription());//Add to string list
                }

                return roles;
            }
        }

        return null;
    }

    private void populateService()//get the role list
    {
        request = "role";
        RoleDTO roleDTO = new RoleDTO.Builder()
                .build();

        Intent service;
        service = new Intent(RegisterActivity.this,RoleFindService.class);

        service.putExtra("requestTag", "find all");
        service.putExtra("dtoTag", roleDTO);
        service.putExtra("receiverTag", receiver);

        startService(service);
    }

private void createUser(String username,String password,String displayName)
    {
        request = "register";
        LoginDetails object = new LoginDetails();
        object.setRoleId(roleId);
        object.setUsername(username);
        object.setPassword(password);
        object.setDisplayName(displayName);


        LoginDetailsDTO loginDto = new LoginDetailsDTO.Builder()
                .loginDetails(object)
                .build();

        Intent service;
        service = new Intent(RegisterActivity.this, LoginDetailsCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", "create");
        service.putExtra("dtoTag", loginDto);
        service.putExtra("receiverTag", receiver);

        startService(service);
    }

    private void setRegisterOnclick() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String userName = txtUsername.getText().toString();
               String password = txtPassword.getText().toString();
               String retypePassword = txtRetypePassword.getText().toString();
                String displayName = txtDisplay.getText().toString();

                if(userName.trim().equals("") || password.trim().equals("") )
                {
                    dialogOutput("Register Error", "Please enter in Register details");

                    request ="";
                }
                else{

                    if(displayName.trim().equals(""))
                    {
                        dialogOutput("Register Error","please enter a unique display name");
                        txtPassword.setText("");
                        txtRetypePassword.setText("");

                    }
                    else{

                        int sizeOfString = password.length();
                        if(sizeOfString > 3)
                        {
                            if(password.equals(retypePassword))
                            {
                                createUser(userName, password,displayName);
                            }
                            else
                            {
                                request = "";
                                txtPassword.setText("");
                                txtRetypePassword.setText("");
                                dialogOutput("Register error", "You password needs to be to match");
                            }
                        }
                        else {
                            txtPassword.setText("");
                            txtRetypePassword.setText("");

                            dialogOutput("Register error", "You password needs to be more than 3 chracters");

                        }

                    }



                }

            }
        });

    }


    private void initButtons() {
        txtUsername = (EditText)findViewById(R.id.register_username_login_form);
        txtPassword = (EditText)findViewById(R.id.register_password_login_form);
        txtRetypePassword = (EditText)findViewById(R.id.register_retype_password_login_form);
        txtDisplay = (EditText)findViewById(R.id.register_display_name);


        btnSubmit =(Button)findViewById(R.id.register_form_submit_button);

    }
    private void dialogOutput(String title,String message)
    {
        Dialog d = new Dialog(RegisterActivity.this);
        d.setTitle(title);
        TextView tv = new TextView(RegisterActivity.this);

        tv.setText(message);
        d.setContentView(tv);
        d.show();

    }

}
