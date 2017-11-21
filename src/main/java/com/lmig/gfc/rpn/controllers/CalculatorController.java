package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AbsCalculator;
import com.lmig.gfc.rpn.models.Adder;
import com.lmig.gfc.rpn.models.Divider;
import com.lmig.gfc.rpn.models.Multiplier;
import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.PushUndoer;
import com.lmig.gfc.rpn.models.Subtracter;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.Undoer;

@Controller
public class CalculatorController {
	private Stack<Double> stack;
	private Stack<Undoer> undoers;
	private AbsCalculator calc;
	
	public CalculatorController() {
		stack = new Stack<Double>();
		undoers = new Stack<Undoer>();
	}
	
	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("stack",stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size()>= 2);
		mv.addObject("hasUndoer", undoers.size()>0);
		return mv;
	}
	
	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		stack.push(value);
		undoers.push(new PushUndoer());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		Adder adder = new Adder(stack);
		adder.goDoIt();
		undoers.push(adder);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;  
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {
		Subtracter subtracter = new Subtracter(stack);
		subtracter.goDoIt();
		undoers.push(subtracter);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	} 
	@PostMapping("/divide")
	public ModelAndView divideTwoNumbers() {
		Divider divider = new Divider(stack);
		divider.goDoIt();
		undoers.push(divider);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	} 
	@PostMapping("/multiply")
	public ModelAndView multiplyTwoNumbers() {
		Multiplier multiplier = new Multiplier(stack);
		multiplier.goDoIt();
		undoers.push(multiplier);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	} 
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		Undoer undoer = undoers.pop();
		undoer.undo(stack);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	
	@PostMapping("/abs")
	public ModelAndView abs() {
//		double number = stack.pop();
//		double abs = Math.abs(number);
//		stack.push(abs);
		calc = new AbsCalculator();
		double number = calc.calculateAbs(stack);
		undoers.push(new OneArgumentUndoer(number));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	
}
