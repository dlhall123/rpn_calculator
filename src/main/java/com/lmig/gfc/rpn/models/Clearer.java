package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Clearer implements GoDoer {
	
	private Stack<Double> stack;
	private Stack<Double> backupStack;
	
	public Clearer(Stack<Double> stack) {
		this.stack = stack;
		backupStack = new Stack<Double>();
	}

	@Override
	public void undo(Stack<Double> stack) {
		stackCopier(backupStack, stack);

	}

	@Override
	public void goDoIt() {
		stackCopier(stack, backupStack);
	}
	
	public void stackCopier(Stack<Double> stackToBeCopied, Stack<Double> stackToCopyTo) {
		while(!stackToBeCopied.isEmpty()) {
			stackToCopyTo.push(stackToBeCopied.pop());
		}
	}

}
