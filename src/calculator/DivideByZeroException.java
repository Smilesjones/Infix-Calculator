/*
Filename: DividByZeroException.java
Author: Stephen Jones
Date: 03NOV2018
Purpose: Project 1 exception class instantiated whenever expression includes
a zero in the denominator.
 */
package calculator;

public class DivideByZeroException extends Exception {

    public DivideByZeroException() {
    }

    public DivideByZeroException(String msg) {
        super(msg);
    }
}
