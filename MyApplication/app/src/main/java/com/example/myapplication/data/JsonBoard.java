package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JsonBoard extends Board{

    @SerializedName("result")
     Board board;

    public JsonBoard(String username, Integer boardId, String boardName, String boardSerial, String boardStart, Integer boardAutoStart, Integer boardContor, Integer boardOff) {
        super(username, boardId, boardName, boardSerial, boardStart, boardAutoStart, boardContor, boardOff);
    }

    public JsonBoard(String username, String boardName, String boardSerial, String boardStart, Integer boardAutoStart, Integer boardContor, Integer boardOff) {
        super(username, boardName, boardSerial, boardStart, boardAutoStart, boardContor, boardOff);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
