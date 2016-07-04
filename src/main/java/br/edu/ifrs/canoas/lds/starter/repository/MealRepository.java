package br.edu.ifrs.canoas.lds.starter.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.ifrs.canoas.lds.starter.domain.Meal;


public interface MealRepository extends CrudRepository<Meal, Long> {

}
