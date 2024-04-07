package org.example.models;

public class Group {
    private final Long id;
    private static Long ID_SEQ = 1L;
    private String name;

    public Group(String name) {
        this.id = ID_SEQ++;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
