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
		model.addAttribute("questions", questions);
		return "quiz";
	}

	@PostMapping("/quiz")
	public String submitQuiz(@RequestParam("selectedOptions") List<String> selectedOptions, Model model) {
		int score = quizService.calculateScore(selectedOptions, quizService.loadQuestionsFromJson("questions.json"));
		int totalQuestions = quizService.loadQuestionsFromJson("questions.json").size();
		model.addAttribute("score", score);
		model.addAttribute("totalQuestions", totalQuestions);
		return "redirect:/results?score=" + score + "&totalQuestions=" + totalQuestions;
	}

	@GetMapping("/results")
	public String showResults(@RequestParam("score") int score, @RequestParam("totalQuestions") int totalQuestions, Model model) {
		int correctAnswers = score;
		int incorrectAnswers = totalQuestions - correctAnswers;


		String grade;
		if (score < 5) {
			grade = "Ocena: 2.0 - Nie zdałeś";
		} else if (score == 5) {
			grade = "Ocena: 3.0 - Zdałeś";
		} else if (score >= 6 && score <= 7) {
			grade = "Ocena: 3.5 - Zdałeś";
		} else if (score >= 8 && score <= 9) {
			grade = "Ocena: 4.0 - Zdałeś";
		} else if (score >= 10 && score <= 11) {
			grade = "Ocena: 4.5 - Zdałeś";
		} else {
			grade = "Ocena: 5.0 - Zdałeś";
		}

		model.addAttribute("score", score);
		model.addAttribute("totalQuestions", totalQuestions);
		model.addAttribute("correctAnswers", correctAnswers);
		model.addAttribute("incorrectAnswers", incorrectAnswers);
		model.addAttribute("grade", grade);

		return "results";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringQuiz.class, args);
	}
}
