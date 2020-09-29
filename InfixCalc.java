/*
Filename: InfixCalc.java
Author: Stephen Jones
Date: 03NOV2018
Purpose: Project 1 expression evaluation class that performs evalution on the 
infix expression entered by the user.  Seperates expression into numerical and 
operational elements, adheres to precedence rules for operators and parentheses,
calculates expression correctly, and throws a DivideByZeroException when 
appropriate.

References used to help create code:

    1.Learn.umuc.edu. Accessed November 03, 2018. 
https://learn.umuc.edu/d2l/le/content/330288/viewContent/13092754/View.


 */
package project1;

import java.util.LinkedList;
import java.util.Stack;


public class InfixCalc extends Stack {
    
    //Variables
    private int answer;
    private char [] elements;
    //Stack objects for operands and operators
    Stack<Integer> operands = new Stack();
    Stack<Character> operators = new Stack();
    
    //Constructor with expression parameter
    public InfixCalc (String expression) throws DivideByZeroException{
        //Seperate expression string to individual tokens/characters
        elements = expression.toCharArray();
        //Call method to calulate the expression and assign to variable
        answer = calculateExpression(elements);
    }
    
    public int calculateExpression (char[] infixElements) throws DivideByZeroException{
        //Local variable for the next element in the expression array
        char next;
        //Statment to return zero if there is no expression
        if (infixElements.length == 0){
            return answer = 0;
        }
    
        //While there are more tokens, loop through the expression
        for (int i = 0; i < infixElements.length; i++){
            //Assign next to the next token
            next = infixElements[i];
            //If the next token is an operand
            if (next == '0'|| next == '1' || next == '2'|| next == '3'||
                   next == '4'|| next == '5' || next == '6'|| next == '7'|| 
                   next == '8'|| next == '9'){
                //Convert the char token to a double for calculations
                int number = Character.getNumericValue(next);
                //Push the operand onto the operand stack
                operands.push(number);                 
            //Else if the next token is a left parenthesis
            }else if(next == '('){
                //Push it onto the operator stack
                operators.push(next);                
            //Else if the next token is a right parenthesis
            }else if (next == ')'){
                //While the operator stack is not empty...
                while(!operators.empty()){
                    //And if the top element on the stack is a left parenthesis
                    if(operators.peek() == '('){
                        //The right parenthsis has found its match, so break.
                        break;
                    //Else call method to run a calculation
                    }else{
                        calcSimpleExpress();
                    }
                    //This cycle repeats until the right paranthesis has "found"
                    //it's left match
                }
                
            //Else if the next token is an operator    
            }else if(next == '+'||next == '-'||next == '*'||next == '/'){
               //While the operator stack is not empty and
               //the operator at the top of the stack has higher
               //or the same precedence than the current operator
               
               //First while loop is for * and /.  No need to specify what next
               //is since all of the next operators are the same or lower 
               //precedence than the operator at the top of the stack 
               while((!operators.empty())&& 
                           (operators.peek() == '*'|| operators.peek() =='/')){
                       calcSimpleExpress();
                   }
               //Second while loop is for the + or - exclusively, 
               //this is so the expression operation goes from left to right
               while((!operators.empty())&& (next == '+'||next == '-')&&
                           (operators.peek() == '+'|| operators.peek() =='-')){
                       calcSimpleExpress();
                   }
                //Push the current operator on the operators stack
                operators.push(next);
            }
        }
        //While the operator stack is not empty, 
        //call method to perform calculations
        while (!operators.empty()){
            calcSimpleExpress();
        }
        //Return the final result, which is at the top of the operand stack
        return operands.peek();
    }
            
    //Method to perform calulations on two operands with a single operator
    public void calcSimpleExpress() throws DivideByZeroException{
        //Variables for two operands, an operator, and result
        //The first operand in the stack (the top one) should be the second 
        //variable used in the calculation
        int y = operands.pop();
        int x = operands.pop();
        int result = 0;
        char currentOperator = operators.pop();
        //Perform the calculation depending on the operator
            switch (currentOperator){
                case '+': result =  (x + y);
                            break;
                case '-': result =  (x - y);
                            break;
                case '*': result =  (x * y);
                            break;
                case '/': if (y == 0){//throw exception if zero in denominator
                            throw new DivideByZeroException();
                        }else{
                            result = (x/y);
                        }
            }
        //If/else statements to remove left parentheses that have been matched
        if (operators.empty()){
            operands.push(result);
            return;
        }else if(operators.peek() == '('){
            operators.pop();
        }
        //Push the result onto the operand stack
        operands.push(result);
    }
    //Stack specific methods that utilize 
    //Method to return the expression result as a String
    @Override
    public String toString(){
        String solution = "" + answer;
        return solution;
    }
    
}
