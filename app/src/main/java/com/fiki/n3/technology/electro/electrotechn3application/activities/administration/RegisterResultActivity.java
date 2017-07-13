package com.fiki.n3.technology.electro.electrotechn3application.activities.administration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.LoginActivity;


/**
 * Created by Roman on 2016/05/14.
 */
public class RegisterResultActivity extends Activity {

    private Button btnLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);

        btnLink = (Button)findViewById(R.id.register_success_linkButton);

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterResultActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
