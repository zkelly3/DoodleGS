package com.example.zkelly3.doodlegs.game_logic;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combiner {
    private Map<Pair<Element, Element>, Rule> rules;

    public Combiner() {
        this.rules = new HashMap<>();
    }
    public void addRule(Rule rule) {
        Pair<Element, Element> combination;
        Element a = rule.input1;
        Element b = rule.input2;
        if(a.getName().compareTo(b.getName()) >= 0) {
            combination = new Pair<>(b, a);
        }
        else {
            combination = new Pair<>(a, b);
        }
        this.rules.put(combination, rule);
    }
    public List<Element> combine(Element a, Element b) {
        Pair<Element, Element> combination;
        if(a.getName().compareTo(b.getName()) >= 0) {
            combination = new Pair<>(b, a);
        }
        else {
            combination = new Pair<>(a, b);
        }

        if(this.rules.containsKey(combination)) {
            return this.rules.get(combination).getResult();
        }
        else {
            return new ArrayList<>();
        }
    }
}
