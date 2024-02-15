package com.example.proj2;

import java.util.List;

public class Question {
    private String content;
    private String correctOption;
    private String[] options;

    public Question() {
        // Konstruktor domyślny
    }

    public Question(String content, String correctOption, String[] options) {
        this.content = content;
        this.correctOption = correctOption;
        this.options = options;
    }

    public String getContent() {
        return content;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public String[] getOptions() {
        return options;
    }
}
