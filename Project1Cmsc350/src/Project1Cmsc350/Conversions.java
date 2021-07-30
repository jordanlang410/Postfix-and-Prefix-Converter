/* Name: Lang, Jordan
 * Project Name: Project1Cmsc350
 * Date: 03/30/2021
 * Description: This class is used to convert the users input to a 
 * prefix or postfix expression
*/
package Project1Cmsc350;

import java.io.*;
import java.util.*;

public class Conversions {

	private static boolean Operators(String x) {
		switch (x.charAt(0)) {

		case '+':
		case '-':
		case '*':
		case '/':

			return true;
		}
		return false;
	}

	private static ArrayList<String> st(String enteredExpression) {
		StreamTokenizer st = new StreamTokenizer(new StringReader(enteredExpression));

		// store tokens in ArrayList
		ArrayList<String> tokens = new ArrayList<String>();
		// use tt_EOF to read the tokens until the end of the stream
		try {
			while (st.nextToken() != StreamTokenizer.TT_EOF) {

				// check if token is a number
				if (st.ttype == StreamTokenizer.TT_NUMBER) {
					tokens.add(String.valueOf((int) st.nval)); // use nval because of number

				} else if (st.ttype == StreamTokenizer.TT_WORD) {
					tokens.add(st.sval); // use sval because of word

					// check if token is anything else
				} else {
					tokens.add(String.valueOf((char) st.ttype));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokens;
	}

	// Method to reverse an ArrayList
	public static ArrayList<String> reverseArrayList(ArrayList<String> list) {

		ArrayList<String> revArrayList = new ArrayList<String>();
		for (int i = list.size() - 1; i >= 0; i--) {
			revArrayList.add(list.get(i));
		}
		return revArrayList;
	}

	public static String PrefixToPostfix(String enteredExpression) throws SyntaxError {

		if (!enteredExpression.equals("")) { // if the expression is not blank

			ArrayList<String> enteredExpressionArray = st(enteredExpression);
			Stack<String> stack = new Stack<String>();

			// Call the reverseArrayList method to reverse the entered expression
			enteredExpressionArray = reverseArrayList(enteredExpressionArray);

			// check for anything other than the operators in the entered expression
			for (int i = 0; i < enteredExpressionArray.size(); i++) {
				if (!Operators(enteredExpressionArray.get(i))) {
					stack.push(enteredExpressionArray.get(i) + " ");

				} else {
					try {
						String operand1 = stack.pop();
						String operand2 = stack.pop();
						String temp = operand1 + operand2 + enteredExpressionArray.get(i) + " ";
						stack.push(temp);
					} catch (EmptyStackException e) { // catch the error if the stack is empty
						throw new SyntaxError("Cannot pop an empty stack.");

					}
				}
			}
			String results = stack.pop();
			// ensure that the stack is empty before returning the results
			if (stack.empty()) {
				return results;

			} else { // If the stack is not empty, let the user know
				throw new SyntaxError("Stack is not empty.");
			}

		} else { // ensure the user enters an expression
			throw new SyntaxError("Please enter an expression.");
		}
	}

	public static String PostfixToPrefix(String enteredExpression) throws SyntaxError {

		if (!enteredExpression.equals("")) { // if the expression is not blank

			ArrayList<String> enteredExpressionArray = st(enteredExpression);
			Stack<String> stack = new Stack<>();

			// check for anything other than the operators in the entered expression
			for (int i = 0; i < enteredExpressionArray.size(); i++) {
				if (!Operators(enteredExpressionArray.get(i))) {
					stack.push(enteredExpressionArray.get(i) + " ");

				} else {
					try {
						String operand1 = stack.pop();
						String operand2 = stack.pop();
						String temp = enteredExpressionArray.get(i) + " " + operand2 + operand1;
						stack.push(temp);
					} catch (EmptyStackException ex) { // catch the error if the stack is empty
						throw new SyntaxError("Cannot pop an empty stack.");
					}
				}
			}
			String results = stack.pop();

			// ensure that the stack is empty before returning the results
			if (stack.empty()) {
				return results;

			} else { // If the stack is not empty, let the user know
				throw new SyntaxError("Stack is not empty.");
			}
		} else { // ensure the user enters an expression
			throw new SyntaxError("Please enter an expression.");
		}
	}

}
