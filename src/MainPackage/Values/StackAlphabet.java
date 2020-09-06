package MainPackage.Values;

public enum StackAlphabet {
    ZERO('0'),
    ONE('1'),
    DOLLAR('$');

    public final char value;
    StackAlphabet(char value){
        this.value = value;
    }
}
