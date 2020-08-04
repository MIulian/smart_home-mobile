package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    EditText userText;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final Intent intent = new Intent(this, BoardActivity.class);
        Button btn_login = (Button) findViewById(R.id.loginID);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userText = (EditText) findViewById(R.id.editUsernameID);
                userName = userText.getText().toString();
                intent.putExtra("userName", MainActivity.this.userName);
                startActivity(intent);
            }
        });
    }

}