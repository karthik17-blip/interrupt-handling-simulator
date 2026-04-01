class CPU {

    private String currentProcess;
    private ContextManager contextManager;
    private ISRHandler isrHandler;
    private InterruptQueue interruptQueue;

    public CPU(InterruptQueue queue) {
        this.currentProcess = "Process-1";
        this.contextManager = new ContextManager();
        this.interruptQueue = queue;
        this.isrHandler = new ISRHandler(queue);
    }

    public void run() {
        System.out.println("CPU Started Running: " + currentProcess);

        while (true) {

            try {
                Thread.sleep(1000); // small delay to keep checking
            } catch (InterruptedException e) {
                break;
            }

            if (!interruptQueue.isEmpty()) {

                Interrupt interrupt = interruptQueue.getNextInterrupt();

                System.out.println("\n🚀 CPU picked interrupt...");
                System.out.println("👉 Executing Priority: " + interrupt.priority +
                                   " | Type: " + interrupt.type);

                contextManager.saveState(currentProcess);

                isrHandler.handleInterrupt(interrupt);

                contextManager.restoreState(currentProcess);

                System.out.println("Resuming " + currentProcess);
            }
        }
    }
}