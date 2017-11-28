//button to clear the stack, and re-add the entire stack

//swap button - swap the position of the 2 most recent values in the stack (make undoable by hitting swap again)
//Rotate button - takes the second from last number and moves to top of stack and push to the beginning

package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AbsoluterOfOneNumber;
import com.lmig.gfc.rpn.models.Adder;
import com.lmig.gfc.rpn.models.Clearer;
import com.lmig.gfc.rpn.models.Divider;
import com.lmig.gfc.rpn.models.Exponent;
import com.lmig.gfc.rpn.models.GoDoer;
import com.lmig.gfc.rpn.models.Multiplier;
import com.lmig.gfc.rpn.models.Pusher;
import com.lmig.gfc.rpn.models.Rotator;
import com.lmig.gfc.rpn.models.Subtracter;
import com.lmig.gfc.rpn.models.Swapper;

@Controller
public class CalculatorController { 
	private Stack<Double> stack;
	private Stack<GoDoer> undoers;
	private Stack<GoDoer> redoers; 
	
	public CalculatorController() {
		stack = new Stack<Double>();
		undoers = new Stack<GoDoer>();
		redoers = new Stack<GoDoer>();
	}
	
	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("stack",stack);
		mv.addObject("hasOneOrMoreNumber", stack.size()>= 1);
		mv.addObject("hasTwoOrMoreNumbers", stack.size()>= 2);
		mv.addObject("hasThreeOrMoreNumbers", stack.size()>=3);
		mv.addObject("hasUndoer", undoers.size()>0);
		mv.addObject("hasRedoer", redoers.size()>0);
		return mv;
	}
	
	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		Pusher pusher = new Pusher(stack, value);
		return doOperation(pusher);
	}
	//Adding stack clearing
	@PostMapping("/clear")
	public ModelAndView clearStack() {
		Clearer clearer = new Clearer(stack);
		return doOperation(clearer);
	}
	//Adding Swapping
	@PostMapping("/swap")
	public ModelAndView swapStack() {
		Swapper swapper = new Swapper(stack);
		return doOperation(swapper);
	}
	//Adding rotating
	@PostMapping("/rotate")
	public ModelAndView rotateStack() {
		Rotator rotator = new Rotator(stack);
		return doOperation(rotator);
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		Adder adder = new Adder(stack);
		return doOperation(adder);
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {
		Subtracter subtracter = new Subtracter(stack);
		return doOperation(subtracter);
	} 
	@PostMapping("/divide")
	public ModelAndView divideTwoNumbers() {
		Divider divider = new Divider(stack);
		return doOperation(divider);
	} 
	@PostMapping("/multiply")
	public ModelAndView multiplyTwoNumbers() {
		Multiplier multiplier = new Multiplier(stack);
		return doOperation(multiplier);
	} 
	@PostMapping("/exponent")
	public ModelAndView exponentTwoNumbers() {
		Exponent exponent = new Exponent(stack);
		return doOperation(exponent);
	} 
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		GoDoer undoer = undoers.pop();
		undoer.undo(stack);
		redoers.push(undoer);
		return redirectToHome();
	}
	
	@PostMapping("/redo")
	public ModelAndView redo() {
		GoDoer godo = redoers.pop();
		godo.goDoIt();
		undoers.push(godo);
		return redirectToHome();
	}
	
	@PostMapping("/abs")
	public ModelAndView abs() {
		AbsoluterOfOneNumber calc = new AbsoluterOfOneNumber(stack);
		return doOperation(calc); 
	}
	
	private ModelAndView doOperation(GoDoer calc) {
		calc.goDoIt();
		undoers.push(calc);
		redoers.clear();
		return redirectToHome();
	} 
	
	private ModelAndView redirectToHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;   
		
	}
	
	
	
}
