package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Rotator implements GoDoer {
	private Stack<Double> stack;
	private double mostRecentNum;
	private double secondMostRecentNum;
	private double thirdMostRecentNum;
	public Rotator(Stack<Double> stack) {
		this.stack = stack;
	}

	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(thirdMostRecentNum);
		stack.push(secondMostRecentNum);
		stack.push(mostRecentNum);

	}

	@Override
	public void goDoIt() {
		mostRecentNum = stack.pop();
		secondMostRecentNum = stack.pop();
		thirdMostRecentNum = stack.pop();
		stack.push(secondMostRecentNum);
		stack.push(mostRecentNum);
		stack.push(thirdMostRecentNum);

	}

}
