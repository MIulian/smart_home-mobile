package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.JsonUser;
import com.example.myapplication.data.User;
import com.example.myapplication.network.UserApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserNouActivity extends AppCompatActivity {

    private UserApiService apiService;
    private TextView errorText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        errorText = (TextView) findViewById(R.id.textErrorID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(UserApiService.class);

        Button btn_save  = (Button) findViewById(R.id.buttonSaveUserID);
        Button btn_back  = (Button) findViewById(R.id.buttonBackUserID);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
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

    public void createNewUser(){

        EditText userNameText = (EditText) findViewById(R.id.editUserNameID);
        EditText addressMailText = (EditText) findViewById(R.id.editEmailID);
        EditText firstNameText = (EditText) findViewById(R.id.editFirstNameID);
        EditText lastNameText = (EditText) findViewById(R.id.editLastNameID);
        EditText ageText = (EditText) findViewById(R.id.editAgeID);
        EditText addressText = (EditText) findViewById(R.id.editAdressID);
        EditText phoneText = (EditText) findViewById(R.id.editPhoneID);

        User newUser = new User(userNameText.getText().toString(), addressMailText.getText().toString(), firstNameText.getText().toString(), lastNameText.getText().toString(), Integer.parseInt(ageText.getText().toString()), addressText.getText().toString(), Integer.parseInt(phoneText.getText().toString()));

        Call<JsonUser> call = apiService.saveBoard(newUser);

        call.enqueue(new Callback<JsonUser>() {
            @Override
            public void onResponse(Call<JsonUser> call, Response<JsonUser> response) {
                if(!response.isSuccessful()){
                    errorText.setText("Code: "+response.code());
                }

                System.out.println("API_Response Cod: " + response.code());

            }

            @Override
            public void onFailure(Call<JsonUser> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });

    }

    public void callBackPage (){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
