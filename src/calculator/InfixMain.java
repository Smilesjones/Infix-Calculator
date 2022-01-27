/*
Filename: InfixMain.java
Author: Stephen Jones
Date: 03NOV2018
Purpose: Project 1 main class that defines the GUI, creates the object
to perform infix calculations, and catches the DivideByZeroException and
displays an error message where appropriate.

References used to help create code:

    (n.d.). Retrieved November 4, 2018,
from http://www.java2s.com/Code/Java/Swing-JFC/BoxLayoutGlueSample.htm

 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class InfixMain extends JFrame implements ActionListener {

    //GUI component variables
    private JTextField infixEnter;
    private JButton evaluateButton;
    private JTextField infixResult;
    private JLabel enterLabel, resultLabel;
    private JPanel mainPanel, northPanel, southPanel;
    private Box centerBox;

    //Constructor defining the GUI and it's components
    public InfixMain(){
        //Main JPanel
        mainPanel = new JPanel(new BorderLayout());
        //Add main panel to frame
        super.add(mainPanel);
        //North and south sub-panels
        northPanel = new JPanel(new GridLayout(1,2));
        southPanel = new JPanel(new GridLayout(1,2));
        //JTextField for entering data
        infixEnter = new JTextField();
        //JButton to click for expression evaluation
        evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(this);
        //JTextField for reading the results
        infixResult = new JTextField();
        infixResult.setEditable(false);
        infixResult.setBackground(Color.LIGHT_GRAY);
        //JLabel for entering input and reading results
        enterLabel = new JLabel("Enter Infix Expression");
        resultLabel = new JLabel("Result", SwingConstants.RIGHT);
        //Create box and add button with glue spacers
        centerBox = Box.createHorizontalBox();
        centerBox.add(Box.createGlue());
        centerBox.add(evaluateButton);
        centerBox.add(Box.createGlue());
        //Add appropriate components to the north panel
        northPanel.add(enterLabel);
        northPanel.add(infixEnter);
        //Add appropriate components to the south panel
        southPanel.add(resultLabel);
        southPanel.add(infixResult);
        //Add north and south panels and centerBox to the main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerBox, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    //Actionlistener for the evaluate button.
    //Evaluates input data and sets the result text to the solution
    @Override
    public void actionPerformed(ActionEvent e) {
        //Input retrieved from the enter text field
        String input = infixEnter.getText();
        //Try/catch to catch DividByZeroException
        try {
            //Create object to calculate the expression solution
            InfixCalc runCalc = new InfixCalc(input);
            //Set the result text the string of the solution
            infixResult.setText(runCalc.toString());
        }catch(DivideByZeroException dz){
            //Pop-up error explaining the exception
            JOptionPane.showMessageDialog(null, "Division by zero detected.\n" +
                    "Please correct entry.", "Division by Zero",
                    JOptionPane.ERROR_MESSAGE);
            infixResult.setText("Error");
        }
    }

    //Method to set the frame characteristics
    public static void setFrame(JFrame frame){
        frame.setName("Infix Expression Evaluator");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Main method that instantiate the frame object and calls setFrame method
    public static void main(String[] args) {
        InfixMain infixFrame = new InfixMain();
        setFrame(infixFrame);
    }
}
