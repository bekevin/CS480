package test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

	private JPanel panel;
	private JButton button;
	private JLabel inputLabel;
	private JTextField inputField;
	private JLabel outputLabel;

	public GUI() {
		JFrame temp = new JFrame();
		setTitle("Calculator");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

	}

	private void buildPanel() {
		inputLabel = new JLabel("Enter your input:");
		inputField = new JTextField("(5+3)*6/2-1", 10);
		button = new JButton("Calculate");
		outputLabel = new JLabel("Your answer will go here");
		panel = new JPanel();

		button.addActionListener(new buttonListener());

		panel.add(inputLabel);
		panel.add(inputField);
		panel.add(button);
		panel.add(outputLabel);

	}

	private class buttonListener implements ActionListener {

		Calculator calculator = new Calculator();
		String output;
		String input;

		public void actionPerformed(ActionEvent arg) {
			try {
				input = inputField.getText();
				if (calculator.verification(input) == false || input.length() == 0
						|| calculator.verificationBracket(input) == false) {
					outputLabel.setText("That is not a valid input");
					
				}else{
					output = calculator.PEMDAS(input);
					outputLabel.setText("your answer is: " + output);
				}
				
			} catch (Exception e) {
				outputLabel.setText("That is not a valid input");
			}

		}

	}
	public static void main(String[] args){
		new GUI();
	}

}
