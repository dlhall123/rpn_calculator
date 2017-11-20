package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.Redoer;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;

@Controller
public class CalculatorController {
	private Stack<Double> stack;
	private OneArgumentUndoer undoer;
	private Redoer redoer;
	
	public CalculatorController() {
		stack = new Stack<Double>();
	}
	
	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("stack",stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size()>= 2);
		mv.addObject("hasUndoer", undoer != null);
		return mv;
	}
	
	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		stack.push(value);
		undoer = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		double first = stack.pop();
		double second = stack.pop();
		double result = first + second;
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
		redoer = new Redoer(result);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;  
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {
		double first = stack.pop();
		double second = stack.pop();
		double result = second-first;
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
		redoer = new Redoer(result);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	} 
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		undoer.undo(stack);
		undoer = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	
	@PostMapping("/abs")
	public ModelAndView abs() {
		double number = stack.pop();
		double abs = Math.abs(number);
		stack.push(abs);
		undoer = new OneArgumentUndoer(number);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	
}
