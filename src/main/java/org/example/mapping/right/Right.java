package org.example.mapping.right;

import java.util.List;

public class Right {

    private int version;

    private String name;

    private List<RightLine> lines;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RightLine> getLines() {
        return lines;
    }

    public void setLines(List<RightLine> lines) {
        this.lines = lines;
    }
}
