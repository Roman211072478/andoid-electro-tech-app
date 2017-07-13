package com.fiki.n3.technology.electro.electrotechn3application.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.reset.db.RunReset;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.ManageRolesActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.TutorialAdminActivity;
import com.fiki.n3.technology.electro.electrotechn3application.dialogs.DialogHandler;


/**
 * Created by Roman on 2016/05/12.
 */
public class AdministratorActivity extends Activity  {

    private Button btnTutorial,btnRole,btnStaff,btnPlay,btnExit,btnReset;
    private DialogHandler dialog = new DialogHandler(AdministratorActivity.this);
    private RunReset reset = new RunReset(AdministratorActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        //initiate components

        initComponents();
        setButtons();
    }


    private void btnRole()
    {
        btnRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, ManageRolesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btnExit()
    {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setButtons()
    {
        btnRole();
        btnExit();
        btnTutorials();
        btnPlayAction();
        btnResetDb();

    }

    private void btnResetDb() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.Confirm("Update", "Are you sure reset all databases?",
                        "No", "Yes",
                        new Runnable() { //coolest thing ever
                            @Override
                            public void run() {
                                allow();
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {

                            }
                        }
                );
            }
        });
    }
    private void allow()
    {
        boolean pass = reset.reset();

        if(pass)
        {
            dialog.outputMessage("Reset dbs"," All tables were reset  ");
        }
        else
        {
            dialog.outputMessage("Reset dbs"," Error occured while All tables were being reset");
        }
    }

    private void btnTutorials() {
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, TutorialAdminActivity.class);
                startActivity(intent);
            }
        });

    }
    private void btnPlayAction()
    {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents()
    {
        btnTutorial = (Button)findViewById(R.id.admin_tutorial_btn);
        btnRole = (Button)findViewById(R.id.admin_role_btn);
        btnStaff = (Button)findViewById(R.id.admin_staff_btn);
        btnPlay = (Button)findViewById(R.id.admin_play_btn);
        btnExit = (Button)findViewById(R.id.admin_exit_btn);
        btnReset = (Button)findViewById(R.id.RestReposAdmin);
    }
}
