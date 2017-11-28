package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Swapper implements GoDoer {
	private Stack<Double> stack;
	private double swapNumber1;
	private double swapNumber2;
	
	public Swapper(Stack<Double> stack) {
		this.stack = stack;
	}
	
	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		stack.pop();
		stack.push(swapNumber2);
		stack.push(swapNumber1);

	}

	@Override
	public void goDoIt() {
		swapNumber1 = stack.pop();
		swapNumber2 = stack.pop();
		stack.push(swapNumber1);
		stack.push(swapNumber2);

	}

}
