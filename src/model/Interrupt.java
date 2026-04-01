class Interrupt implements Comparable<Interrupt> {
    int id;
    String type;
    int priority;
    int executionTime;

    public Interrupt(int id, String type, int priority, int executionTime) {
        this.id = id;
        this.type = type;
        this.priority = priority;
        this.executionTime = executionTime;
    }

    @Override
    public int compareTo(Interrupt other) {
        return this.priority - other.priority; // lower value = higher priority
    }

    @Override
    public String toString() {
        return "Interrupt[ID=" + id + ", Type=" + type + ", Priority=" + priority + "]";
    }
}