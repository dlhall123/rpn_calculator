package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer implements Undoer {
	private double number;

	public OneArgumentUndoer(double number) {
		this.number = number;
	}
	
	public void undo(Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
	}
	
	protected void parentUndo(Stack<Double> stack) {
		stack.push(number);
	}

}
