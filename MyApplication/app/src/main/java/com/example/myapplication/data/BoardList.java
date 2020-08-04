package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoardList extends Board{
    @SerializedName("result")
    private List<Board> boards;

    public BoardList(String username, Integer boardId, String boardName, String boardSerial, String boardStart, Integer boardAutoStart, Integer boardContor, Integer boardOff) {
        super(username, boardId, boardName, boardSerial, boardStart, boardAutoStart, boardContor, boardOff);
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
