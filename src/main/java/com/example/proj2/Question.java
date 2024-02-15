package com.example.proj2;

public class Question {
    private String content;
    private String correctOption;
    private String[] options;
    private String codeSnippet;
    private String imagePath;

    public Question(String content, String correctOption, String[] options, String codeSnippet, String imagePath) {
        this.content = content;
        this.correctOption = correctOption;
        this.options = options;
        this.codeSnippet = codeSnippet;
        this.imagePath = imagePath;
    }

    public Question() {
        // Konstruktor domyślny
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

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
