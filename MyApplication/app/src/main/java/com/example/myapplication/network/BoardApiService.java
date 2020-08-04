package com.example.myapplication.network;

import com.example.myapplication.data.Board;
import com.example.myapplication.data.BoardList;
import com.example.myapplication.data.JsonBoard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoardApiService {

    //toate panourile de comanda ale unui user
    @GET("mobil/user-boards/{username}")
    Call<BoardList> getAll(@Path("username") String username);
    //get one-board
    @GET("mobil/{board_serial}")
    Call<JsonBoard> getOne(@Path("board_serial") String boardSerial);
    //edit board
    @PUT("mobil/")
    Call<JsonBoard> editBoard(@Body Board board );
    //add board
    @POST("mobil/")
    Call<JsonBoard> saveBoard(@Body Board board );
    //delete board
    @DELETE("mobil/{board_serial}")
    Call<Void> deleteBoard(@Path("board_serial") String boardSerial);
}
