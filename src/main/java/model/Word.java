package model;

import java.util.ArrayList;

public class Word {
    private String engString;
    private String vieString;


    public Word() {

    }

    public Word(String engString, String vieString) {
        this.engString = engString;
        this.vieString = vieString;
    }

    @Override
    public String toString() {
        return "Word{" +
                "engString='" + engString + '\'' +
                ", vieString='" + vieString + '\'' +
                '}';
    }

    public String getEngString() {
        return engString;
    }

    public void setEngString(String engString) {
        this.engString = engString;
    }

    public String getVieString() {
        return vieString;
    }

    public void setVieString(String vieString) {
        this.vieString = vieString;
    }

}
