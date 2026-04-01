import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        InterruptQueue queue = new InterruptQueue();
        CPU cpu = new CPU(queue);

        Thread cpuThread = new Thread(() -> cpu.run());
        cpuThread.start();

        Scanner scanner = new Scanner(System.in);
        int id = 1;

        while (true) {
            try {
                System.out.println("\n--- Generate Interrupt ---");
                System.out.print("Enter Type (Timer/Keyboard/SystemCall/exit): ");

                String type = scanner.nextLine().trim();

                if (type.equalsIgnoreCase("exit")) {
                    System.out.println("Stopping CPU...");
                    System.exit(0);
                }

                System.out.print("Enter Priority (lower = higher priority): ");
                int priority = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Execution Time (seconds): ");
                int time = Integer.parseInt(scanner.nextLine());

                Interrupt interrupt = new Interrupt(id++, type, priority, time);
                queue.addInterrupt(interrupt);

            } catch (Exception e) {
                System.out.println("❌ Invalid input!");
            }
        }
    }
}