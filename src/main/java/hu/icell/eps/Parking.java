package hu.icell.eps;

public class Parking {

    private final long id;
    private final String content;

    public Parking(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
