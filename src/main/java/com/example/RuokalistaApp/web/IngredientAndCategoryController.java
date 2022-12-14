package com.example.RuokalistaApp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.RuokalistaApp.domain.Category;
import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItem;
import com.example.RuokalistaApp.domain.FoodItemRepository;

@Controller
public class IngredientAndCategoryController {

	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired
	private FoodItemRepository foodItemRepository;
		
	
	//add (+modify) fooditems
	@GetMapping("/ingredients")
	public String allIngredients(Model model) {
		model.addAttribute("foodItem", new FoodItem());
		model.addAttribute("ingredients", foodItemRepository.findAll());
		return "ingredients";
	}
	
	@PostMapping("/addIngredient")
	public String addIngredient(@Valid FoodItem ingredient, BindingResult result, Model model) {
		
			if (ingredient.getName().length() == 0) {
				model.addAttribute("ingredients", foodItemRepository.findAll());
				return "ingredients";
			}
		
			//Change the name to match the formatting in DB
			String newName = ingredient.getName().trim();
			newName = newName.substring(0, 1).toUpperCase() +  newName.substring(1).toLowerCase();

			//Check if there is a ingredient with same name in DB
			List<FoodItem> allIngredients = (List<FoodItem>) foodItemRepository.findAll();
			for (int i = 0; i<allIngredients.size(); i++) {	
				if (allIngredients.get(i).getName().toUpperCase().equals(newName.toUpperCase())) {
					System.out.println("Ingredient allready in DB");
					result.rejectValue("name", "err.name", "Ainesosa on jo tietokannassa");
				}

			}
			if (result.hasErrors()) {
			//	model.addAttribute("ingredient", ingredient);
				model.addAttribute("ingredients", foodItemRepository.findAll());
				return "ingredients";
			}
			
			ingredient.setName(newName);
			foodItemRepository.save(ingredient);
			return "redirect:ingredients";
	}
	
	
	//List all categories
	@GetMapping("/categories")
	public String allCategories(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}
	//add category
	@PostMapping("/addCategory")
	public String addCategory(@Valid Category category, BindingResult result, Model model) {
			
			if (category.getName().isEmpty()) {
				model.addAttribute("categories", categoryRepository.findAll());
				return "categories";
			}
		
			//Change the name to match the formatting in DB
			String newName = category.getName().trim();
			newName = newName.substring(0, 1).toUpperCase() +  newName.substring(1).toLowerCase();
			
			//Check if there is a ingredient with same name in DB
			List<Category> allCategories = (List<Category>) categoryRepository.findAll();
			for (int i = 0; i<allCategories.size(); i++) {	
				if (allCategories.get(i).getName().toUpperCase().equals(newName.toUpperCase())) {
					result.rejectValue("name", "err.name", "Ainesosa on jo tietokannassa");
					
				}

			}
			if (result.hasErrors()) {
				model.addAttribute("categories", categoryRepository.findAll());
				return "categories";
			}
			category.setName(newName);
			categoryRepository.save(category);
	return "redirect:categories";
	}
	
}
