package sg.edu.rp.webservices.p13_taskmanagerwear;

import java.io.Serializable;

public class Task implements Serializable {

    private String name;
    private String description;
    private int seconds;

    public Task (String name, String description, int seconds) {
        this.name = name;
        this.description = description;
        this.seconds = seconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

}
