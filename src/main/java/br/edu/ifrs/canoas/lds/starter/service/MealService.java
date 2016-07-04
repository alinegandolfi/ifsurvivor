package br.edu.ifrs.canoas.lds.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.starter.domain.Meal;
import br.edu.ifrs.canoas.lds.starter.repository.MealRepository;

@Service
public class MealService {
	private MealRepository mealRepository;
	
	@Autowired
	public MealService(MealRepository mealRepository) {
		this.mealRepository = mealRepository;
	}
	
	public Meal get(Long id) {
		return mealRepository.findOne(id);
	}
	
	public Iterable<Meal> list() {
		return mealRepository.findAll();
	}
}
