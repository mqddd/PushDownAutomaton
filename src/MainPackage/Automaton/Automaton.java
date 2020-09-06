package MainPackage.Automaton;

import MainPackage.Values.ConsoleColors;
import MainPackage.Values.LanguageAlphabet;
import MainPackage.Values.StateSituation;

public class Automaton {
    private Stack stack;

    private State[] states;
    private StateSituation[] situations;

    private char[] inputStringChars;
    private String readChars;
    private String remainedChars;
    private char[] inputExpression;

    private State currentState;

    public Automaton(char[] inputExpression){
        this.inputExpression = inputExpression;
        this.stack = new Stack();
        this.situations = new StateSituation[]{StateSituation.START, StateSituation.TRAP, StateSituation.FINAL};
        this.states = new State[3];
        this.makeStates();
        this.currentState = this.states[0];
    }

    public Automaton(String inputString){
        this.inputStringChars = (inputString + "l").toCharArray();
        this.readChars = inputString;
        this.remainedChars = inputString;
        this.stack = new Stack();
        this.situations = new StateSituation[]{StateSituation.START, StateSituation.TRAP, StateSituation.FINAL};
        this.states = new State[3];
        this.makeStates();
        this.currentState = this.states[0];
    }

    public void isStringAcceptedStepByStep() throws InterruptedException {
        loop:
        for (char inputStringChar : this.inputStringChars) {
            //if (this.readChars.length() >= 1) Thread.sleep(3000);
            StateSituation inWhichSituation;
            switch (inputStringChar) {
                case 'a':
                    inWhichSituation = new Walk(stack, this.currentState, LanguageAlphabet.A).operate();
                    break;
                case 'b':
                    inWhichSituation = new Walk(stack, this.currentState, LanguageAlphabet.B).operate();
                    break;
                case 'l':
                    inWhichSituation = new Walk(stack, this.currentState, LanguageAlphabet.LAMBDA).operate();
                    break;
                default:
                    inWhichSituation = StateSituation.NONE;
            }
            if (this.remainedChars.length() >= 1){
                this.remainedChars = this.remainedChars.substring(1);
            }
            // check situation
            switch (inWhichSituation) {
                case START:
                    this.currentState = this.states[0];
                    System.out.println(this.toString());
                    Thread.sleep(3000);
                    continue;
                case TRAP:
                    this.currentState = this.states[1];
                    System.out.println(this.toString());
                    Thread.sleep(3000);
                    break loop;
                case FINAL:
                    this.currentState = this.states[2];
                    System.out.println(this.toString());
                    Thread.sleep(3000);
                    break loop;
                case NONE:
                    throw new Error("It was not supposed to happen!");
            }
        }
        if (this.currentState.getSituation() != StateSituation.FINAL){
            System.out.println("the string is not accepted!");
        } else {
            System.out.println("the string is accepted!");
        }
    }

    public boolean isEqualExpressionAccepted(){
        for (char inputExpressionChar : this.inputExpression) {
            StateSituation currentSituation;
            switch (inputExpressionChar) {
                case 'a':
                    currentSituation = new Walk(stack, this.currentState, LanguageAlphabet.A).operate();
                    break;
                case 'b':
                    currentSituation = new Walk(stack, this.currentState, LanguageAlphabet.B).operate();
                    break;
                case 'l':
                    currentSituation = new Walk(stack, this.currentState, LanguageAlphabet.LAMBDA).operate();
                    break;
                default:
                    currentSituation = StateSituation.NONE;
            }
            // check situation
            switch (currentSituation) {
                case START:
                    this.currentState = this.states[0];
                    continue;
                case TRAP:
                    this.currentState = this.states[1];
                    return false;
                case FINAL:
                    this.currentState = this.states[2];
                    return true;
                case NONE:
                    throw new Error("It was not supposed to happen!");
            }
        }
        return false;
    }

    public String getResultReason() {
        if (this.stack.isEmpty()){
            return "Some closed parenthesises are closed before they are opened!";
        } else {
            return "The opened parenthesises are more than closed parenthesises!";
        }
    }

    /* first state is START state
           second one is TRAP
           and third one is FINAL state
        */
    private void makeStates(){
        for (int i = 0; i < 3; i++){
            this.states[i] = new State("q", i, this.situations[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stepInformation = new StringBuilder();
        // read chars
        stepInformation.append("The string is : " + ConsoleColors.BLUE).append(this.readChars, 0, inputStringChars.length - remainedChars.length() - 1);
        // remained chars
        stepInformation.append(ConsoleColors.RESET).append(this.remainedChars);
        // in which state
        stepInformation.append("\n").append(this.currentState.toString());
        // stack situation
        stepInformation.append("\n").append(this.stack.toString());
        // separator
        stepInformation.append("\n________________________________________________________");
        return stepInformation.toString();
    }
}
