package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Exponent extends TwoNumberCalculation {
	public Exponent(Stack<Double> stack) {
		super(stack);
	}

	@Override
	protected double doMath(double firstNumber, double secondNumber) {
		return Math.pow(secondNumber, firstNumber);
	}

}
