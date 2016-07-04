package br.edu.ifrs.canoas.lds.starter.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.canoas.lds.starter.domain.Orders;
import br.edu.ifrs.canoas.lds.starter.domain.User;


public interface OrdersRepository extends CrudRepository<Orders, Long> {

	List<Orders> findByClient(User client);
}
