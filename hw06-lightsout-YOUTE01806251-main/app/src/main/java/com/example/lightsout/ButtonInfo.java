package com.example.lightsout;

import android.widget.Button;

public class ButtonInfo {
    private int ID;
    private boolean state;
    private Button button;

    public ButtonInfo(int iD, boolean state, Button button) {
        this.ID = iD;
        this.state = state;
        this.button = button;
    }

    public int getID() {
        return ID;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ButtonInfo{" +
                "ID=" + ID +
                ", state=" + state +
                ", button=" + button +
                '}';
    }
}
