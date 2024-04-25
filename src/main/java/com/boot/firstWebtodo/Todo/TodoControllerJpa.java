package com.boot.firstWebtodo.Todo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	private TodoRepository todoRepository;	
	
	//Autowiring using constructor
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	
	
	
	@RequestMapping("list-todos")
	public String listAllTodo(ModelMap model) {
		String name = getLoggedinUsername(model);
		List<Todo> todos= todoRepository.findByName(name);
		model.addAttribute("todos",todos);
		return "listTodos";
	}


	
	@RequestMapping(value="add-todo" , method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String name = getLoggedinUsername(model);
		Todo todo = new Todo(0,name,"",LocalDate.now().plusDays(10),false);
		model.put("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo" , method = RequestMethod.POST)
	public String addNewTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String name = getLoggedinUsername(model);
		
		todo.setName(name);
		
		todoRepository.save(todo);
		
//		todoService.addTodo(name,todo.getDescription(),todo.getTargetDate(),todo.isDone());
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id,ModelMap model) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	
	@RequestMapping(value="update-todo" , method=RequestMethod.GET)
	public String showupdateTodo(@RequestParam int id,ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo" , method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo,BindingResult result,ModelMap model) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String name = getLoggedinUsername(model);
		todo.setName(name);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
}
