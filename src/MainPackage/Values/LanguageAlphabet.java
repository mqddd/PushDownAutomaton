package MainPackage.Values;

public enum LanguageAlphabet {
    A('a', '('),
    B('b', ')'),
    LAMBDA('l', ' ');

    public final char charValue;
    public final char parenthesisValue;
    LanguageAlphabet(char charValue, char parenthesisValue){
        this.charValue = charValue;
        this.parenthesisValue =  parenthesisValue;
    }
}
