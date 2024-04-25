package com.boot.firstWebtodo.Todo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount,"Nishank","Homework",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todosCount,"Nishank","AWS",LocalDate.now().plusYears(2),false));
		todos.add(new Todo(++todosCount,"Nishank","MERN",LocalDate.now().plusYears(3),false));
	}
	
//	public List<Todo> findByUsername(String name){
//		return todos;
//	}
	
	public void addTodo(String username,String description, LocalDate date, boolean done ) {
		Todo todo = new Todo(++todosCount,username,description,date,done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public List<Todo> findByUsername(String name) {
		Predicate<? super Todo> predicate = todo -> todo.getName().equalsIgnoreCase(name);
		return todos.stream().filter(predicate).toList();
	}
	
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		 Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
	
}
