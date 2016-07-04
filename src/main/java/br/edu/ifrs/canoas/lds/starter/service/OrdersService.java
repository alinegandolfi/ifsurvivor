package br.edu.ifrs.canoas.lds.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.starter.domain.Orders;
import br.edu.ifrs.canoas.lds.starter.domain.User;
import br.edu.ifrs.canoas.lds.starter.repository.OrdersRepository;

@Service
public class OrdersService {
	private OrdersRepository ordersRepository;
	
	@Autowired
	public OrdersService(OrdersRepository ordersRepository) {
		this.ordersRepository = ordersRepository;
	}
	
	public Orders get(Long id) {
		return ordersRepository.findOne(id);
	}
	
	public Orders save(Orders orders) {
		return ordersRepository.save(orders);
	}
	
	public Object listByClient(User client) {
		return ordersRepository.findByClient(client);
	}
}
