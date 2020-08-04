package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.Board;
import com.example.myapplication.data.BoardList;
import com.example.myapplication.data.CustomAdapterBoard;
import com.example.myapplication.network.BoardApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardActivity extends AppCompatActivity implements CustomAdapterBoard.OnBoardListener {

    private CustomAdapterBoard adapter;
    private RecyclerView recyclerView;
    private TextView errorText;
    private String userName;
    List<Board> boards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_list);

        userName = getIntent().getStringExtra("userName");

        final Intent intent = new Intent(this, AddActivity.class);
        Button btn_add = (Button) findViewById(R.id.buttonAddID);

        final Intent intent2 = new Intent(this, MainActivity.class);
        Button btn_exit = (Button) findViewById(R.id.buttonExitID);

        errorText = findViewById(R.id.boards_textID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BoardApiService apiService = retrofit.create(BoardApiService.class);

        Call<BoardList> call = apiService.getAll(userName);

        call.enqueue(new Callback<BoardList>() {
            @Override
            public void onResponse(Call<BoardList> call, Response<BoardList> response) {

                if(!response.isSuccessful()) {
                    errorText.setText("Code: " + response.code());
                    return;
                }
                boards = response.body().getBoards();
                generateDataList(boards);
            }

            @Override
            public void onFailure(Call<BoardList> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("userName", BoardActivity.this.userName);
                startActivity(intent);
            }
        });


        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
    private void generateDataList(List<Board> boardList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapterBoard(this, boardList , this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BoardActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickListener(int position) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("userName", boards.get(position).getUsername());
        intent.putExtra("serial", boards.get(position).getBoardSerial());
        intent.putExtra("id", boards.get(position).getBoardId());
        startActivity(intent);
    }
}
