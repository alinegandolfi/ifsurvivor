package br.edu.ifrs.canoas.lds.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrs.canoas.lds.starter.service.MealService;

@Controller
@RequestMapping("/meal")
public class MealController {
	private MealService mealService;
	
	@Autowired
	public MealController(MealService mealservice) {
		this.mealService = mealService;
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("meals", mealService.list());
		return "/meal/list";
	}
	
	@Secured("ROLE_CLIENT")
	@RequestMapping("/view_meal/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("meal", mealService.get(id));
		model.addAttribute("readonly", true);
		return "student/form";
	}
}
