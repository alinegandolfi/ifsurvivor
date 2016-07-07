package br.edu.ifrs.canoas.lds.starter.controller;

import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.canoas.lds.starter.domain.Meal;
import br.edu.ifrs.canoas.lds.starter.domain.Orders;
import br.edu.ifrs.canoas.lds.starter.domain.User;
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
	
	
	/**
	 +	 * Método que retorna uma lista de pedidos por cliente.
	 +   * Data: 06/07/2016
	 +	 */
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("orders", ordersService.listByClient(userProfileService.getPrincipal().getUser()));
		return "/order/list";
	}
	
	/**
	 +	 * Método para visualizar o prato em que o cliente solicitou comprar; Ele cria um novo pedido, também.
	 +   * Data: 06/07/2016
	 +	 */
	@Secured("ROLE_CLIENT")
	@RequestMapping("/view_meal/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("meal", mealService.get(id));
		Orders order = new Orders();
		order.setMeal(mealService.get(id));
		model.addAttribute("order", order);
		model.addAttribute("readonly", false);
		return "order/form";
	}
	
	
	/**
	 +	 * Método que calcula o preço total e mostra os detalhes do pedido.
	 +   * Data: 06/07/2016
	 +	 */
	@Secured("ROLE_CLIENT")
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String review(@Valid Orders order, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs, Locale locale) {
		if (!bindingResult.hasErrors()) {
			
			order.setClient(userProfileService.getPrincipal().getUser());
			order.setDate(new Date());
			
			double totalPrice = order.getMeal().getPrice() * order.getQuantity();
			order.setTotalPrice(totalPrice);
			
			model.addAttribute("order", order);
			model.addAttribute("readonly", true);
	
			return "/order/review";
		}
			model.addAttribute("readonly", false);
			redirectAttrs.addFlashAttribute("message", messageSource.getMessage("order.failed", null, locale));
			return "/order/form";
	}
	
	/**
	 +	 * Método que salva o pedido no Banco de Dados (Problemas com transiente (meal) DE NOVO D: ).
	 +   * Data: 06/07/2016
	 +	 */
	@Secured("ROLE_CLIENT")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Orders order, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs, Locale locale) {
		if (!bindingResult.hasErrors()) {
			
			User client = userProfileService.getPrincipal().getUser();
			//Meal meal = order.getMeal();
					
			//order.setMeal(null);
			order.setClient(null);
			Orders savedOrder = ordersService.save(order);
			
			//savedOrder.setMeal(meal);
			savedOrder.setClient(client);
			savedOrder = ordersService.save(order);
			
			//Não está populando a lista de pedidos no User... Tentei reproduzir o que fizemos com o Registration mas não deu muito certo,
			//então deixei assim. :/
			//client.getOrders().add(order);
			
			redirectAttrs.addFlashAttribute("message", messageSource.getMessage("order.saved", null, locale));
			return "redirect:/order/view_meal/" + savedOrder.getId() + "?success";
		
		}
			model.addAttribute("readonly", false);
			redirectAttrs.addFlashAttribute("message", messageSource.getMessage("order.failed", null, locale));
			return "/order/form";
	}
	
}