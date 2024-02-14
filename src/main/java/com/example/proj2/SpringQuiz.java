package com.example.proj2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
public class SpringQuiz {
	private String selectedCategory;
	private int score;
	private int totalQuestions;

	@GetMapping("/")
	public String quizForm() {
		return "index";
	}

	@PostMapping("/selectCategory")
	public String selectCategory(@RequestParam String category) {
		this.selectedCategory = category;
		return "redirect:/quiz";
	}

	@GetMapping("/quiz")
	public String startQuiz(Model model) {
		List<Question> questions = loadQuestions(selectedCategory);
		model.addAttribute("questions", questions);
		totalQuestions = questions.size();
		return "quiz";
	}

	@PostMapping("/")
	public String quizSubmit(@RequestParam("selectedOptions") String[] selectedOptions, Model model) {
		int correctAnswers = 0;
		List<Question> questions = loadQuestions(selectedCategory);

		for (Question question : questions) {
			System.out.println("Odpowiedzi użytkownika dla pytania '" + question.getContent() + "': " + Arrays.toString(selectedOptions));
			System.out.println("Poprawne odpowiedzi dla pytania '" + question.getContent() + "': " + Arrays.toString(question.getCorrectAnswers()));

			if (selectedOptions != null && selectedOptions.length > 0 &&
					Arrays.asList(selectedOptions).containsAll(Arrays.asList(question.getCorrectAnswers()))) {
				correctAnswers++;
			}
		}
		System.out.println("Liczba poprawnych odpowiedzi: " + correctAnswers);
		score = correctAnswers;
		return "redirect:/results";
	}

	@GetMapping("/results")
	public String showResults(Model model) {
		model.addAttribute("score", score);
		model.addAttribute("totalQuestions", totalQuestions);
		return "results";
	}

	private List<Question> loadQuestions(String category) {
		List<Question> questions = new ArrayList<>();
		String filename = "questions_" + category.toLowerCase() + ".txt";
		try {
			InputStream inputStream = getClass().getResourceAsStream("/" + filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				String[] options = Arrays.copyOfRange(parts, 1, parts.length - 1);
				String[] correctAnswers = {parts[parts.length - 1]};
				Question question = new Question(parts[0], options, correctAnswers);
				questions.add(question);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return questions;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringQuiz.class, args);
	}
}
