package com.example.zkelly3.doodlegs.game_logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Rule {
    protected Element input1;
    protected Element input2;
    protected List<Element> output;

    public Rule(Element a, Element b) {
        this.input1 = a;
        this.input2 = b;
        this.output = new ArrayList<>();
    }
    public abstract List<Element> getResult();

}
