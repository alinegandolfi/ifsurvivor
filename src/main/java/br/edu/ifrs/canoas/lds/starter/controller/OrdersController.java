package br.edu.ifrs.canoas.lds.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrs.canoas.lds.starter.service.MealService;
import br.edu.ifrs.canoas.lds.starter.service.OrdersService;
import br.edu.ifrs.canoas.lds.starter.service.UserProfileService;

@Controller
@RequestMapping("/order")
public class OrdersController {
	private OrdersService ordersService;
	private MealService mealService;
	private MessageSource messageSource;
	private UserProfileService userProfileService;
	
	@Autowired
	public OrdersController(OrdersService ordersService, MealService mealService, MessageSource messageSource, UserProfileService userProfileService){
		this.ordersService = ordersService;
		this.mealService = mealService;
		this.messageSource = messageSource;
		this.userProfileService = userProfileService;
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("orders", ordersService.listByClient(userProfileService.getPrincipal().getUser()));
		return "/order/list";
	}
	
}
