package br.edu.ifrs.canoas.lds.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrs.canoas.lds.starter.service.MealService;
import br.edu.ifrs.canoas.lds.starter.service.UserProfileService;

@Controller
public class HomeController {
	
	MealService mealService;
	UserProfileService userService;
	
	@Autowired
	public HomeController(MealService mealService, UserProfileService userService) {
		this.mealService = mealService;
		this.userService = userService;
	}
	
	@RequestMapping("/")
	public String home(Model model){
		
		model.addAttribute("auth", userService.getPrincipal() != null);
		model.addAttribute("meals", mealService.list());
		return "index";
	}

}
