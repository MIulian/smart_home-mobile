package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.Board;
import com.example.myapplication.data.JsonBoard;
import com.example.myapplication.network.BoardApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity  {

    private String userName;
    private BoardApiService apiService;
    private TextView errorText;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_add);
        userName = getIntent().getStringExtra("userName");

        errorText = (TextView) findViewById(R.id.textErrorID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(BoardApiService.class);

        Button btn_save  = (Button) findViewById(R.id.buttonSaveID);
        Button btn_back  = (Button) findViewById(R.id.buttonBackID);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createNewBoard();
               callBackPage();
            }

        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPage();
            }

        });
    }
    public void createNewBoard (){
        EditText boardNameText = (EditText) findViewById(R.id.editNameID);
        EditText boardSerialText = (EditText) findViewById(R.id.editSerialID);
        Switch boardSwitchAuto = (Switch) findViewById(R.id.switchAutostartID);
        EditText boardStartText = (EditText) findViewById(R.id.editStartID);
        EditText boardContorText = (EditText) findViewById(R.id.editContorID);
        Integer contor = Integer.valueOf(boardContorText.getText().toString());
        Switch boardSwitchOff = (Switch) findViewById(R.id.switchOffID);
        int auto = 0;
        int off = 0;
        if(boardSwitchAuto.isChecked()){
            auto = 1;
        }
        if(boardSwitchOff.isChecked()) {
            off = 1;
        }
        Board newBoard = new Board(userName,
                boardNameText.getText().toString(),
                boardSerialText.getText().toString(),
                boardStartText.getText().toString(),
                auto,
                contor,
                off);
        Call<JsonBoard> call = apiService.saveBoard(newBoard);

        call.enqueue(new Callback<JsonBoard>() {
            @Override
            public void onResponse(Call<JsonBoard> call, Response<JsonBoard> response) {
                if(!response.isSuccessful()){
                    errorText.setText("Code: "+response.code());
                }

                System.out.println("API_Response Cod: " + response.code());

            }

            @Override
            public void onFailure(Call<JsonBoard> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });
    }

    public void callBackPage (){
        intent = new Intent(this, BoardActivity.class);
        intent.putExtra("userName", AddActivity.this.userName);
        startActivity(intent);
    }
}
