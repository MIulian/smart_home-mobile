package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JsonBoard extends Board{

    @SerializedName("result")
     Board board;

    public JsonBoard(String username,
                     Integer boardId,
                     String boardName,
                     String boardSerial,
                     String boardStart,
                     String boardStartDate,
                     String boardRunTime,
                     boolean boardAutoStart,
                     Integer boardContor,
                     boolean boardOff) {
        super(username,
                boardId,
                boardName,
                boardSerial,
                boardStart,
                boardStartDate,
                boardRunTime,
                boardAutoStart,
                boardContor,
                boardOff);
    }

    public JsonBoard(String username, String boardName, String boardSerial, String boardStart, String boardStartDate, String boardRunTime, boolean boardAutoStart, Integer boardContor, boolean boardOff) {
        super(username, boardName, boardSerial, boardStart, boardStartDate, boardRunTime,boardAutoStart, boardContor, boardOff);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
