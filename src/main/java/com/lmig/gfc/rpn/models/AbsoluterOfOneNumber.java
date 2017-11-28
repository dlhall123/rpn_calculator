package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AbsoluterOfOneNumber implements Undoer, GoDoer {
	Stack<Double> stack;
	private OneArgumentUndoer undoer;
	public AbsoluterOfOneNumber(Stack<Double> stack) {
		this.stack = stack;
	}
	
	
	
	public void goDoIt() {
		double number = stack.pop();
		double result = Math.abs(number);
		parentCalculate(stack, result);
		undoer = new OneArgumentUndoer(number);
	}
	
	public void parentCalculate(Stack<Double> stack, double calc) {
		stack.push(calc);
	}



	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);
		
	}
	
	
	

}
