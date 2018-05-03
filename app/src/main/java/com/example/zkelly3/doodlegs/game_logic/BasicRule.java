package com.example.zkelly3.doodlegs.game_logic;

import java.util.List;

public class BasicRule extends Rule {
    public BasicRule(Element a, Element b, List<Element> c) {
        super(a, b);
        this.output = c;
    }

    @Override
    public List<Element> getResult() {
        return this.output;
    }
}
