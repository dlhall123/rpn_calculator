package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AbsCalculator {
	double number;
	public AbsCalculator() {
	}
	
	
	
	public double calculateAbs(Stack<Double> stack) {
		number = stack.pop();
		double calc = Math.abs(number);
		parentCalculate(stack, calc);
		return number;
	}
	
	public void parentCalculate(Stack<Double> stack, double calc) {
		stack.push(calc);
	}
	
	
	

}
