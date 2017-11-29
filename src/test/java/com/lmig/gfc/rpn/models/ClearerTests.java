package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class ClearerTests {

	private Stack<Double> stack;
	private Clearer clearer;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
	}

	@Test
	public void goDoIt_empties_stack() {
		// Arrange
		stack.push(1.5);
		stack.push(3.2);
		clearer = new Clearer(stack);
		// Act
		clearer.goDoIt();

		// Assert
		assertThat(stack.isEmpty());
	}

	@Test
	public void undo_undoes() {
		// Arrange
		stack.push(1.5);
		stack.push(3.2);
		stack.push(8.9);
		clearer = new Clearer(stack);

		// Act
		clearer.goDoIt();
		clearer.undo(stack);

		// Assert
		assertThat(stack.pop()).isEqualTo(8.9);
		assertThat(stack.pop()).isEqualTo(3.2);
		assertThat(stack.pop()).isEqualTo(1.5);
	}
}
