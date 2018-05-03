package com.example.zkelly3.doodlegs.game_logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private String name;
    private Map<Element, Boolean> elements;
    private Boolean clear;

    public Group(String name) {
        this.name = name;
        this.elements = new HashMap<Element, Boolean>();
        this.clear = Boolean.FALSE;
    }
    public String getName() {
        return this.name;
    }
    public List<Element> getCreated() {
        List<Element> created = new ArrayList<>();
        for (Map.Entry<Element, Boolean> entry :  this.elements.entrySet()) {
            if(entry.getValue() == Boolean.TRUE) {
                created.add(entry.getKey());
            }
        }
        return created;
    }
    public void pushElement(Element element) {
        this.elements.put(element, element.isCreated());
    }
    public boolean createElement(Element element) {
        if(this.elements.get(element) == Boolean.TRUE) {
            return false;
        }
        else {
            this.elements.put(element, Boolean.TRUE);
            int fullSize = this.elements.size();
            int nowSize = this.getCreated().size();
            if(nowSize >= fullSize) {
                this.clear = Boolean.TRUE;
            }
            return true;
        }
    }
    public boolean isEmpty() {
        boolean result = true;
        for (Map.Entry<Element, Boolean> entry :  this.elements.entrySet()) {
            if(entry.getValue() == Boolean.TRUE) {
                result = false;
                break;
            }
        }
        return result;
    }
}
