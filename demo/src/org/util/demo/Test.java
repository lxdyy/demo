package org.util.demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 反转栈数据
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class Test {

	public static void main (String [] args){
		@SuppressWarnings("rawtypes")
		Stack stack = new Stack();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		System.out.println(stack);
		
		reverseStack(stack);
	}

	@SuppressWarnings("rawtypes")
	private static void reverseStack(Stack stack) {
		Queue rev = new LinkedList();
		while (stack.size()>0){
			rev.offer(stack.pop());
		}
		while(rev.size()>0){
			stack.push(rev.poll());
		}
		System.out.println(stack);
		
	}
}
