package MainPackage;

import MainPackage.Automaton.Automaton;

import java.util.Scanner;

public class MainClass {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        loop:
        while (true){
            System.out.println("options:\n1.input a string including a and b.\n2.input an arithmetic expression.\n3.exit.");
            int inputOption = in.nextInt();
            switch (inputOption){
                case 1:
                    String inputString = in.next();
                    Automaton stringAutomaton = new Automaton(inputString);
                    try{
                        stringAutomaton.isStringAcceptedStepByStep();
                    } catch (Error | InterruptedException error){
                        System.out.println("It seems you entered wrong string!");
                    }
                    break;
                case 2:
                    String inputExpression = in.next();
                    char[] changedExpression = changeExpression(inputExpression);
                    System.out.println("equal string is: " + (String.valueOf(changedExpression).substring(0, changedExpression.length - 1)));
                    Automaton expressionAutomaton = new Automaton(changedExpression);
                    if (expressionAutomaton.isEqualExpressionAccepted()){
                        System.out.println("this string is accepted!");
                    } else {
                        System.out.println(expressionAutomaton.getResultReason());
                    }
                    break;
                case 3:
                    break loop;
                default:
                    System.out.println("Please enter a number from 1 to 3.");
            }
        }
        System.out.println("Thanks!");
    }

    public static char[] changeExpression(String inputExpression){
        StringBuilder equalChars = new StringBuilder();
        for (char inputExpressionChar : inputExpression.toCharArray()) {
            if (inputExpressionChar == '('){
                equalChars.append("a");
            } else if (inputExpressionChar == ')'){
                equalChars.append("b");
            }
        }
        equalChars.append("l");
        return equalChars.toString().toCharArray();
    }
}
