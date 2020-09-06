package MainPackage.Automaton;

import MainPackage.Values.StackAlphabet;

import java.util.Arrays;

public class Stack {
    private StackAlphabet Zero = StackAlphabet.ZERO;
    private StackAlphabet One = StackAlphabet.ONE;
    private StackAlphabet Dollar = StackAlphabet.DOLLAR;

    private char[] stackArray;
    private final int initialStackSize;
    private int stackSize;
    private int stackPointerValue;

    private boolean isEmpty;

    public Stack(){
        // default initial stack size
        this.stackArray = new char[50];
        this.initialStackSize = 50;
        this.stackSize = 50;
        this.stackPointerValue = -1;
        this.isEmpty = true;
        this.pushDollarToStack();
    }

    public Stack(int initialStackSize){
        if (initialStackSize <= 0){
            throw new Error("Stack size should be at least one!");
        } else {
            this.stackArray = new char[initialStackSize];
            this.initialStackSize = initialStackSize;
            this.stackSize = initialStackSize;
            this.stackPointerValue = -1;
            this.pushDollarToStack();
        }
    }

    public void push(StackAlphabet alphabet){
        if (this.isStackFull()) {
            this.increaseStackSize();
            this.isEmpty = false;
        }
        this.stackArray[++this.stackPointerValue] = alphabet.value;
        this.isEmpty = false;
    }

    public void pop(){
        if (!this.isStackEmpty()){
            this.stackPointerValue--;
            this.isEmpty = this.isStackEmpty();
        } else {
            this.isEmpty = true;
            throw new Error("stack is empty");
        }
    }

    public StackAlphabet topValue(){
        if (this.stackArray[this.stackPointerValue] == '1'){
            return this.One;
        } else if (this.stackArray[this.stackPointerValue] == '0'){
            return this.Zero;
        } else return this.Dollar;
    }

    private void increaseStackSize(){
        this.stackSize += this.initialStackSize;
        this.stackArray = Arrays.copyOf(this.stackArray, this.stackSize);
    }

    private boolean isStackFull(){
        return this.stackPointerValue == this.stackSize - 1;
    }

    private boolean isStackEmpty(){
        return this.stackArray[this.stackPointerValue] == this.Dollar.value;
    }

    private void pushDollarToStack(){
        this.stackArray[++this.stackPointerValue] = this.Dollar.value;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String toString() {
        StringBuilder stackValues = new StringBuilder();
        for (int i = 0; i <= this.stackPointerValue; i++) {
            stackValues.append(this.stackArray[i]);
            if (i != this.stackPointerValue)
                stackValues.append(" | ");
        }
        return "Stack [ " + stackValues + " ]";
    }
}
