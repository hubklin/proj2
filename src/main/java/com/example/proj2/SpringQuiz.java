package com.example.proj2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SpringBootApplication
@Controller
public class SpringQuiz {
	private int score;
	private int totalQuestions;
	private final QuizService quizService;

	public SpringQuiz(QuizService quizService) {
		this.quizService = quizService;
	}

	@GetMapping("/")
	public String quizForm() {
		return "index";
	}

	@PostMapping("/startQuiz")
	public String startQuiz() {
		return "redirect:/quiz";
	}

	@GetMapping("/quiz")
	public String showQuiz(Model model) {
		List<Question> questions = quizService.loadQuestionsFromJson("questions.json");
		totalQuestions = questions.size();
		model.addAttribute("questions", questions);
		return "quiz";
	}

	@PostMapping("/quiz") // Changed endpoint to handle POST method
	public String submitQuiz(@RequestParam("selectedOptions") List<String> selectedOptions, Model model) {
		score = quizService.calculateScore(selectedOptions, quizService.loadQuestionsFromJson("questions.json"));
		model.addAttribute("score", score);
		model.addAttribute("totalQuestions", totalQuestions);
		return "results";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringQuiz.class, args);
	}
}
