package com.example.zkelly3.doodlegs.game_logic;

import java.util.List;

public class ProbRule extends Rule {
    private List<Element> options;

    public ProbRule(Element a, Element b, List<Element> c) {
        super(a, b);
        this.options = c;
    }

    @Override
    public List<Element> getResult() {
        int len = this.options.size();
        int index = (int)(Math.random()*len);
        this.output.add(this.options.get(index));
        return this.output;
    }
}
