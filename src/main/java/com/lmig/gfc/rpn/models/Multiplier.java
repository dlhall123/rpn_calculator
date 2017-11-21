package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Multiplier extends TwoNumberCalculation {
	public Multiplier(Stack<Double> stack) {
		super(stack);
	}

	@Override
	protected double doMath(double firstNumber, double secondNumber) {
		return firstNumber * secondNumber;
	}
	

}
