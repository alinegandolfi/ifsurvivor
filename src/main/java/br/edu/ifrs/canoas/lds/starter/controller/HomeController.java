package br.edu.ifrs.canoas.lds.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrs.canoas.lds.starter.service.MealService;

@Controller
public class HomeController {
	
	MealService mealService;
	
	@Autowired
	public HomeController(MealService mealService) {
		this.mealService = mealService;
	}
	
	@RequestMapping("/")
	public String home(Model model){
		
		model.addAttribute("auth",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		model.addAttribute("meals", mealService.list());
		return "index";
	}

}
