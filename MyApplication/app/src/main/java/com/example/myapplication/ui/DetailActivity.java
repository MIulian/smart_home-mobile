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

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    String userName, serial;
    int id;
    private BoardApiService apiService;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_edit);

        userName = getIntent().getStringExtra("userName");
        serial = getIntent().getStringExtra("serial");
        id = getIntent().getIntExtra("id", 0);

        errorText = (TextView) findViewById(R.id.edit_textErrorID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(BoardApiService.class);

        Button btn_back = (Button) findViewById(R.id.edit_buttonBackID);
        Button btn_edit = (Button) findViewById(R.id.edit_buttonSaveID);
        Button btn_delete = (Button) findViewById(R.id.edit_buttonDeleteID);

        detailsBoard(serial);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPage();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditedBoard(id);
                callBackPage();

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBoard(serial);
                callBackPage();
            }
        });

    }

    public void detailsBoard (String serial){
        final EditText boardNameText = (EditText) findViewById(R.id.editNameID);
        final EditText boardSerialText = (EditText) findViewById(R.id.editSerialID);
        final Switch boardSwitchAuto = (Switch) findViewById(R.id.switchAutostartID);
        final EditText boardStartText = (EditText) findViewById(R.id.editStartID);
        final EditText boardStartDateText = (EditText) findViewById(R.id.editStartDateID);
        final EditText boardRunTimeText = (EditText) findViewById(R.id.editRunTimeID);
        final EditText boardContorText = (EditText) findViewById(R.id.editContorID);
        final Switch boardSwitchOff = (Switch) findViewById(R.id.switchOffID);

        Call<JsonBoard> call = apiService.getOne(serial);

        call.enqueue(new Callback<JsonBoard>() {
            @Override
            public void onResponse(Call<JsonBoard> call, Response<JsonBoard> response) {
                if(!response.isSuccessful()){
                    errorText.setText("API_Response Cod: "+response.code());
                }
                System.out.println("API_Response Cod: " + response.code());
                Board board = response.body().getBoard();
                if (board != null) {
                    boardNameText.setText(board.getBoardName());
                    boardSerialText.setText(board.getBoardSerial());
                    boardSwitchAuto.setChecked(board.getBoardAutoStart());
                    boardStartText.setText(board.getBoardStart());
                    boardStartDateText.setText(board.getBoardStartDate());
                    boardRunTimeText.setText(board.getBoardRunTime());
                    boardContorText.setText(String.valueOf(board.getBoardContor()));
                    boardSwitchOff.setChecked(board.getBoardOff());
                }

            }

            @Override
            public void onFailure(Call<JsonBoard> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });
    }

    public void callBackPage (){
        intent = new Intent(this, BoardActivity.class);
        intent.putExtra("userName", DetailActivity.this.userName);
        startActivity(intent);
    }

    public void saveEditedBoard (int id){
        EditText boardNameText = (EditText) findViewById(R.id.editNameID);
        EditText boardSerialText = (EditText) findViewById(R.id.editSerialID);
        Switch boardSwitchAuto = (Switch) findViewById(R.id.switchAutostartID);
        EditText boardStartText = (EditText) findViewById(R.id.editStartID);
        EditText boardStartDateText = (EditText) findViewById(R.id.editStartDateID);
        EditText boardRuntimeText = (EditText) findViewById(R.id.editRunTimeID);
        EditText boardContorText = (EditText) findViewById(R.id.editContorID);
        Integer contor = Integer.valueOf(boardContorText.getText().toString());
        Switch boardSwitchOff = (Switch) findViewById(R.id.switchOffID);

        Board newBoard = new Board(userName,
                boardNameText.getText().toString(),
                boardSerialText.getText().toString(),
                boardStartText.getText().toString(),
                boardStartDateText.getText().toString(),
                runTimeSet(boardRuntimeText.getText().toString()),
                boardSwitchAuto.isChecked(),
                contor,
                boardSwitchOff.isChecked());
        Call<JsonBoard> call = apiService.editBoard(newBoard);

        call.enqueue(new Callback<JsonBoard>() {
            @Override
            public void onResponse(Call<JsonBoard> call, Response<JsonBoard> response) {
                if(!response.isSuccessful()){
                    errorText.setText("API_Response Cod: "+response.code());
                }

                System.out.println("API_Response Cod: " + response.code());

            }

            @Override
            public void onFailure(Call<JsonBoard> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });
    }

    public void deleteBoard (String serial){


        Call<Void> call = apiService.deleteBoard(serial);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    errorText.setText("API_Response Cod: "+response.code());
                }

                System.out.println("API_Response Cod: " + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });
    }
    public String runTimeSet ( String runTime){
        String result = "";
        if(runTime != null && !(runTime.equals(" "))) {
            int time = Integer.valueOf(runTime);
            int hour = 00 ;
            int min = 00 ;
            DecimalFormat form = new DecimalFormat("00");
            hour = (time / 60);
            min = (time - (hour * 60));
            result = form.format(hour)+":"+form.format(min);
        }
        return result;
    }

}
