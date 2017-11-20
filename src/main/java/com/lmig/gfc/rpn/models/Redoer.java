package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Redoer {
	private double result;
	public Redoer(double result){
		this.result = result;		
	}
	public void redo(Stack<Double> stack) {
		stack.pop();
		stack.pop();
		stack.push(result);
	}

}
