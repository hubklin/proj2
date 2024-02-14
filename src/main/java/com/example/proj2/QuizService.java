package com.example.proj2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class QuizService {

    public List<Question> loadQuestionsFromJson(String jsonFileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" + jsonFileName);
            questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public int calculateScore(List<String> selectedOptions, List<Question> questions) {
        int score = 0;
        for (int i = 0; i < selectedOptions.size(); i++) {
            String selectedOption = selectedOptions.get(i);
            Question question = questions.get(i);
            if (question.getCorrectOption().equalsIgnoreCase(selectedOption)) {
                score++;
            }
        }
        return score;
    }
}
