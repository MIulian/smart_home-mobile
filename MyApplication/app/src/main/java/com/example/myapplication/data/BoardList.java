package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoardList extends Board{
    @SerializedName("result")
    private List<Board> boards;

    public BoardList(String username, Integer boardId, String boardName, String boardSerial, String boardStart, String boardStartDate, String boardRunTime, boolean boardAutoStart, Integer boardContor, boolean boardOff) {
        super(username, boardId, boardName, boardSerial, boardStart, boardStartDate, boardRunTime, boardAutoStart, boardContor, boardOff);
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
