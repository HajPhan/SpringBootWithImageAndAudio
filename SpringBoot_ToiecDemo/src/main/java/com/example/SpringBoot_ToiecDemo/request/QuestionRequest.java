package com.example.SpringBoot_ToiecDemo.request;

public class QuestionRequest {

    private int id;
    private String images;
    private String audio;
    private String question;

    public QuestionRequest() {
    }

    public QuestionRequest(int id, String images, String audio, String question) {
        this.id = id;
        this.images = images;
        this.audio = audio;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
