package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class AbsoluterOfOneNumberTests {
	
	private Stack<Double> stack;
	private AbsoluterOfOneNumber abs;
	
	@Before
	public void setUp() {
		stack = new Stack<Double>();
		abs = new AbsoluterOfOneNumber(stack);
	}
	
	@Test
	public void  goDoIt_replaces_neg_number_on_stack_with_positive_number() {
		//Arrange
		stack.push(-4.5);
		
		//Act
		abs.goDoIt();
		
		//Assert
		assertThat(stack.peek()).isEqualTo(4.5);
	}
	
	@Test
	public void  goDoIt_leaves_pos_num_on_stack_pos() {
		//Arrange
		stack.push(4.5);
		
		//Act
		abs.goDoIt();
		
		//Assert
		assertThat(stack.peek()).isEqualTo(4.5);
	}
	
	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		//Arrange
		stack.push(-999.0);
		abs.goDoIt();
		
		//Act
		abs.undo(stack);
		
		//Assert
		assertThat(stack.peek()).isEqualTo(-999.0);
		
	}

}