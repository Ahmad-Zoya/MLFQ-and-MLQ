import java.util.Scanner;

public class MLQ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        int[] processIDs = new int[numProcesses];
        int[] arrivalTimes = new int[numProcesses];
        int[] burstTimes = new int[numProcesses];
        int[] queueType = new int[numProcesses]; 

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter details for Process " + (i + 1));
            System.out.print("Process ID: ");
            processIDs[i] = scanner.nextInt();
            System.out.print("Arrival Time: ");
            arrivalTimes[i] = scanner.nextInt();
            System.out.print("Burst Time: ");
            burstTimes[i] = scanner.nextInt();
            System.out.print("Queue Type (0: FCFS, 1: RR1, 2: RR2): ");
            queueType[i] = scanner.nextInt();
        }

        int[] fcfsQueue = new int[numProcesses];
        int[] rr1Queue = new int[numProcesses];
        int[] rr2Queue = new int[numProcesses];
        int[] completionTimes = new int[numProcesses];
        int currentTime = 0;

        while (true) {
            boolean allProcessesDone = true;

            for (int i = 0; i < numProcesses; i++) {
                if (queueType[i] == 0 && arrivalTimes[i] <= currentTime && burstTimes[i] > 0) {
                    fcfsQueue[i] = burstTimes[i];
                    burstTimes[i] = 0;
                    completionTimes[i] = currentTime + fcfsQueue[i];
                    currentTime = completionTimes[i];
                }
                if (burstTimes[i] > 0) {
                    allProcessesDone = false;
                }
            }

            for (int i = 0; i < numProcesses; i++) {
                if (queueType[i] == 1 && arrivalTimes[i] <= currentTime && burstTimes[i] > 0) {
                    if (burstTimes[i] <= 1) {
                        rr1Queue[i] += burstTimes[i];
                        burstTimes[i] = 0;
                        completionTimes[i] = currentTime + rr1Queue[i];
                    } else {
                        rr1Queue[i] += 1;
                        burstTimes[i] -= 1;
                        currentTime++;
                    }
                }
                if (burstTimes[i] > 0) {
                    allProcessesDone = false;
                }
            }

            for (int i = 0; i < numProcesses; i++) {
                if (queueType[i] == 2 && arrivalTimes[i] <= currentTime && burstTimes[i] > 0) {
                    if (burstTimes[i] <= 2) {
                        rr2Queue[i] += burstTimes[i];
                        burstTimes[i] = 0;
                        completionTimes[i] = currentTime + rr2Queue[i];
                    } else {
                        rr2Queue[i] += 2;
                        burstTimes[i] -= 2;
                        currentTime += 2;
                    }
                }
                if (burstTimes[i] > 0) {
                    allProcessesDone = false;
                }
            }

            if (allProcessesDone) {
                break;
            }
        }

        int[] waitingTimes = new int[numProcesses];
        int[] turnaroundTimes = new int[numProcesses];
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (int i = 0; i < numProcesses; i++) {
            turnaroundTimes[i] = completionTimes[i] - arrivalTimes[i];
            waitingTimes[i] = turnaroundTimes[i] - fcfsQueue[i] - rr1Queue[i] - rr2Queue[i];
            totalWaitingTime += waitingTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
        }

        System.out.println("\nGantt Charts:");
        printGanttChart("FCFS Queue", fcfsQueue);
        printGanttChart("RR1 Queue", rr1Queue);
        printGanttChart("RR2 Queue", rr2Queue);

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / numProcesses));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / numProcesses));
    }

    public static void printGanttChart(String queueName, int[] queue) {
        System.out.println(queueName + ": ");
        for (int value : queue) {
            System.out.print("P" + value + " | ");
        }
        System.out.println();
    }
}
