package MainPackage.Automaton;

import MainPackage.Values.LanguageAlphabet;
import MainPackage.Values.StackAlphabet;
import MainPackage.Values.StateSituation;

public class Walk {
    private Stack stack;

    private LanguageAlphabet seenAlphabet;

    private State startingState;

    public Walk(Stack stack, State startingState, LanguageAlphabet seenAlphabet){
        this.stack = stack;
        this.startingState = startingState;
        this.seenAlphabet = seenAlphabet;
    }

    public StateSituation operate(){
        switch (this.seenAlphabet){
            case A:
                this.stack.push(StackAlphabet.ZERO);
                return this.startingState.getSituation();
            case B:
                if (this.stack.topValue() == StackAlphabet.ZERO){
                    this.stack.pop();
                    return this.startingState.getSituation();
                } else {
                    return StateSituation.TRAP;
                }
            case LAMBDA:
                if (this.stack.topValue() == StackAlphabet.ZERO){
                    return this.startingState.getSituation();
                } else return StateSituation.FINAL;
        }
        return StateSituation.NONE;
    }
}
