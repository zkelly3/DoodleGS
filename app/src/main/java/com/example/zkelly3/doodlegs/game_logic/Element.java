package com.example.zkelly3.doodlegs.game_logic;

public class Element {
    private String name;
    private Group group;
    private Boolean created;

    public Element(String name, Group group, Boolean created) {
        this.name = name;
        this.group = group;
        this.created = created;
    }
    public String getName() {
        return this.name;
    }
    public Group getGroup() {
        return this.group;
    }
    public Boolean isCreated() {
        return this.created;
    }
}
