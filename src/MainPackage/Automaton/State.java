package MainPackage.Automaton;

import MainPackage.Values.StateSituation;

public class State {
    public static String name;
    private int id;
    private StateSituation situation;

    public State(String name, int id, StateSituation situation){
        State.name = name;
        this.id = id;
        this.situation = situation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StateSituation getSituation() {
        return situation;
    }

    public void setSituation(StateSituation situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return this.getSituation().toString() + " state : " + State.name + this.id;
    }
}
