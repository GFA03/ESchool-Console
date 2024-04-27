package org.example.models;

import java.util.Objects;

public class Group {
    private Long id;
    private String name;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(Long id) {
        this.id = id;
        this.name = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Group ID: " + id + "\n" +
                "Name: " + name + "\n\n";
    }
}
