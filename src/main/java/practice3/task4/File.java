package practice3.task4;

import java.util.UUID;

public class File {
    private String type;
    private int size;
    private UUID uuid;

    public File(String type, int size) {
        this.type = type;
        this.size = size;
        this.uuid = UUID.randomUUID();
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", uuid=" + uuid +
                '}';
    }
}
