package com.example.proj2;

import java.util.Arrays;

public class Question {
    private String content;
    private String[] options;
    private String[] correctAnswers;

    public Question(String content, String[] options, String[] correctAnswers) {
        this.content = content;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }

    public String getContent() {
        return content;
    }

    public String[] getOptions() {
        return options;
    }

    public String[] getCorrectAnswers() {
        return correctAnswers;
    }

    public boolean isCorrect(String[] userAnswers) {
        // Sprawdź czy odpowiedzi użytkownika zawierają wszystkie poprawne odpowiedzi
        return Arrays.asList(userAnswers).containsAll(Arrays.asList(correctAnswers))
                // i czy liczba odpowiedzi użytkownika jest równa liczbie poprawnych odpowiedzi
                && userAnswers.length == correctAnswers.length;
    }
}