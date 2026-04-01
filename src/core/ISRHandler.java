class ISRHandler {

    private InterruptQueue queue;

    public ISRHandler(InterruptQueue queue) {
        this.queue = queue;
    }

    public void handleInterrupt(Interrupt interrupt) {

        System.out.println("Handling Interrupt: " + interrupt);

        int remainingTime = interrupt.executionTime;

        while (remainingTime > 0) {

            synchronized(System.out) {
                System.out.println("⏳ Executing... Remaining Time: " + remainingTime + "s");
            }
            
            try {
                Thread.sleep(2000); // ⏳ slower execution
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            remainingTime--;

            // 🔥 Check for nested interrupt
            Interrupt next = queue.peek();

            if (next != null && next.priority < interrupt.priority) {

                System.out.println("\n⚡ Nested Interrupt Occurred!");
                System.out.println("👉 Higher Priority Interrupt Found: " + next);

                // Save remaining time
                interrupt.executionTime = remainingTime;

                // Put current interrupt back
                queue.addInterrupt(interrupt);

                System.out.println("⏸ Pausing current interrupt...\n");

                // Handle higher priority interrupt
                Interrupt high = queue.getNextInterrupt();
                handleInterrupt(high);

                System.out.println("\n▶ Resuming previous interrupt...\n");

                return;
            }
        }

        System.out.println("Completed Interrupt: " + interrupt.id);
    }
}