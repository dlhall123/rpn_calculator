package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class AdderTests {

	private Stack<Double> stack;
	private Adder adder;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		adder = new Adder(stack);
	}

	@Test
	public void goDoIt_adds_two_numbers_together_and_places_result_on_stack() {
		// Arrange
		stack.push(3.0);
		stack.push(4.0);

		// Act
		adder.goDoIt();

		// Assert
		assertThat(stack.peek()).isEqualTo(7.0);
	}

	@Test
	public void goDoIt_throws_EmptyStackException_when_one_number_on_stack() {
		// Arrange
		stack.push(4.5);

		try {
			// Act
			adder.goDoIt();
			// Assert
			fail("EmptyStackException was not thrown");
		} catch (EmptyStackException ese) {
		}

	}

	@Test
	public void goDoIt_throws_EmptyStackException_when_fewer_than_two_numbers_on_stack() {
		// Arrange
		stack.push(4.5);

		try {
			// Act
			adder.goDoIt();
			// Assert
			fail("EmptyStackException was not thrown");
		} catch (EmptyStackException ese) {
		}

	}

	@Test
	public void goDoIt_throws_null_pointer_exception_when_passed_null_stack() {
		// Arrange
		adder = new Adder(null);

		try {
			// Act
			adder.goDoIt();
			// Assert
			fail("Null pointer exception not thrown");

		} catch (NullPointerException npe) {
		}

	}

	@Test
	public void undo_returns_stack_to_the_previous_state() {
		// Arrange
		stack.push(4.5);
		stack.push(5.0);
		adder.goDoIt();

		// Act
		adder.undo(stack);
		// Assert
		assertThat(stack.pop()).isEqualTo(5);
		assertThat(stack.pop()).isEqualTo(4.5);
	}

	@Test
	public void undo_throws_null_pointer_exception_when_passed_null_stack() {
		// Arrange
		// Already arranged for test

		try {
			// Act
			adder.undo(null);
			// Assert
			fail("Null pointer exception not thrown");

		} catch (NullPointerException npe) {
		}

	}

}
