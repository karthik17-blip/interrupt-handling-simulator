import java.util.PriorityQueue;

class InterruptQueue {
    private PriorityQueue<Interrupt> queue;

    public InterruptQueue() {
        queue = new PriorityQueue<>();
    }

    public synchronized void addInterrupt(Interrupt interrupt) {
        System.out.println("\nAdded to Queue: " + interrupt);
        System.out.println("👉 Priority = " + interrupt.priority + " (lower = higher priority)");
        queue.add(interrupt);
    }

    public synchronized Interrupt getNextInterrupt() {
        return queue.poll();
    }

    public synchronized Interrupt peek() {
        return queue.peek();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}