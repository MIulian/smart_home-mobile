package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class Board {

    @SerializedName("username")
    private String username;
    @SerializedName("boardId")
    private Integer boardId;
    @SerializedName("boardName")
    private String boardName;
    @SerializedName("boardSerial")
    private String boardSerial;
    @SerializedName("boardStart")
    private String boardStart;
    @SerializedName("boardAutoStart")
    private Integer boardAutoStart;
    @SerializedName("boardContor")
    private Integer boardContor;
    @SerializedName("boardOff")
    private Integer boardOff;

    public Board(String username, Integer boardId, String boardName, String boardSerial, String boardStart, Integer boardAutoStart, Integer boardContor, Integer boardOff) {
        this.username = username;
        this.boardId = boardId;
        this.boardName = boardName;
        this.boardSerial = boardSerial;
        this.boardStart = boardStart;
        this.boardAutoStart = boardAutoStart;
        this.boardContor = boardContor;
        this.boardOff = boardOff;
    }

    public Board(String username, String boardName, String boardSerial, String boardStart, Integer boardAutoStart, Integer boardContor, Integer boardOff) {
        this.username = username;
        this.boardName = boardName;
        this.boardSerial = boardSerial;
        this.boardStart = boardStart;
        this.boardAutoStart = boardAutoStart;
        this.boardContor = boardContor;
        this.boardOff = boardOff;
    }

    public Board(Board board) {
        this.username = null;
        this.boardId = board.getBoardId();
        this.boardName = board.getBoardName();
        this.boardSerial = board.getBoardSerial();
        this.boardStart = board.getBoardStart();
        this.boardAutoStart = board.getBoardAutoStart();
        this.boardContor = board.getBoardContor();
        this.boardOff = board.getBoardOff();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardSerial() {
        return boardSerial;
    }

    public void setBoardSerial(String boardSerial) {
        this.boardSerial = boardSerial;
    }

    public String getBoardStart() {
        return boardStart;
    }

    public void setBoardStart(String boardStart) {
        this.boardStart = boardStart;
    }

    public Integer getBoardAutoStart() {
        return boardAutoStart;
    }

    public void setBoardAutoStart(Integer boardAutoStart) {
        this.boardAutoStart = boardAutoStart;
    }

    public Integer getBoardContor() {
        return boardContor;
    }

    public void setBoardContor(Integer boardContor) {
        this.boardContor = boardContor;
    }

    public Integer getBoardOff() {
        return boardOff;
    }

    public void setBoardOff(Integer boardOff) {
        this.boardOff = boardOff;
    }

}
