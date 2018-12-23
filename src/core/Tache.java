package core;

import java.time.LocalDateTime;


public class Tache {
    private long id;
    private String titre;
    private StringBuffer description;
    private LocalDateTime beforeTime;
    private int status;

    public Tache() {
    }

    public Tache(long id, String titre, StringBuffer description, LocalDateTime beforeTime, int status) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.beforeTime = beforeTime;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public StringBuffer getDescription() {
        return description;
    }

    public LocalDateTime getBeforeTime() {
        return beforeTime;
    }

    public int getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(StringBuffer description) {
        this.description = description;
    }

    public void setBeforeTime(LocalDateTime beforeTime) {
        this.beforeTime = beforeTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nTache{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description=" + description +
                ", beforeTime=" + beforeTime +
                ", status=" + status +
                "}";
    }
}
