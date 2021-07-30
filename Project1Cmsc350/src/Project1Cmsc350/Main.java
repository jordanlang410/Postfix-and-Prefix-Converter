/* Name: Lang, Jordan
 * Project Name: Project1Cmsc350
 * Date: 03/30/2021
 * Description: This class creates the GUI for the user to select from
 * and implements the conversion class to output the prefix or postfix expression
*/
package Project1Cmsc350;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;

public class Main extends JFrame implements ActionListener {

	private JButton prefixToPostfixButton;
	private JButton postfixToPrefixButton;
	private JLabel expressionLabel;
	private JLabel resultsLabel;
	private JTextField enteredExpression;
	private JTextField resultsExpression;

	public Main() {

		setTitle("Expression Converter");
		setSize(400, 160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		add(mainPanel);

		// create buttons for the panel
		this.prefixToPostfixButton = new JButton("Prefix to Postfix");
		this.postfixToPrefixButton = new JButton("Postfix to Prefix");
		this.expressionLabel = new JLabel("Enter Expression");
		this.resultsLabel = new JLabel("Results");
		this.enteredExpression = new JTextField(25);
		this.resultsExpression = new JTextField(25);

		mainPanel.add(northPanel(), BorderLayout.NORTH);
		mainPanel.add(centerPanel(), BorderLayout.CENTER);
		mainPanel.add(southPanel(), BorderLayout.SOUTH);

	}

	// create JPanel upper layout
	private JPanel northPanel() {
		JPanel northPanel = new JPanel();
		northPanel.add(expressionLabel);
		northPanel.add(enteredExpression);
		return northPanel;
	}

	// create JPanel center layout
	private JPanel centerPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.add(prefixToPostfixButton);
		centerPanel.add(postfixToPrefixButton);
		prefixToPostfixButton.addActionListener(this); // add prefix to postfix button
		postfixToPrefixButton.addActionListener(this); // add postfix to prefix button
		return centerPanel;
	}

	// create JPanel lower layout
	private JPanel southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.add(resultsLabel);
		southPanel.add(resultsExpression);
		resultsExpression.setEditable(false);
		return southPanel;
	}

	public static void main(String[] args) {

		Main display = new Main();
		display.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String expressionEntered = enteredExpression.getText();

			if (e.getSource() == prefixToPostfixButton) {
				// call the prefixToPostfix method from the Conversions call to add to the button
				String convert = Conversions.PrefixToPostfix(expressionEntered);
				resultsExpression.setText(convert);

			} else if (e.getSource() == postfixToPrefixButton) {
				// call the postfixToPrefix method from the Conversions class to add to the button
				String convert = Conversions.PostfixToPrefix(expressionEntered);
				resultsExpression.setText(convert);

			}
		}

		// display the exceptions in a new panel if they occur
		catch (SyntaxError e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

}
