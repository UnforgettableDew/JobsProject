package model.entity;

public class Job {
    private int id;
    private String title;

    public Job(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Job() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}