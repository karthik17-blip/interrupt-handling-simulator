class ContextManager {

    public void saveState(String process) {
        System.out.println("Saving state of process: " + process);
    }

    public void restoreState(String process) {
        System.out.println("Restoring state of process: " + process);
    }
}