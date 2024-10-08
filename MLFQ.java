import java.util.*;

public class MLFQ {
    static class Process {
        int id;
        int arrivalTime;
        int burstTime;
        int queueType;
        int remainingBurstTime;
        int age; // Age of the process in RR queue

        public Process(int id, int arrivalTime, int burstTime, int queueType) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.queueType = queueType;
            this.remainingBurstTime = burstTime;
            this.age = 0; // Initialize age to 0
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        Process[] processes = new Process[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter details for Process " + (i + 1));
            System.out.print("Process ID: ");
            int processID = scanner.nextInt();
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            System.out.print("Queue Type (0: FCFS, 1: RR1): ");
            int queueType = scanner.nextInt();

            processes[i] = new Process(processID, arrivalTime, burstTime, queueType);
        }

        int[] completionTimes = new int[numProcesses];
        int currentTime = 0;

        Queue<Integer> fcfsQueue = new LinkedList<>();
        Queue<Integer> rr1Queue = new LinkedList<>();

        int[] processIDs = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processIDs[i] = processes[i].id;
        }

        while (true) {
            boolean allProcessesDone = true;

            for (int i = 0; i < numProcesses; i++) {
                Process process = processes[i];
                if (process.queueType == 0 && process.arrivalTime <= currentTime && process.remainingBurstTime > 0) {
                    int processID = process.id - 1;
                    int executeTime = Math.min(process.remainingBurstTime, 1);
                    completionTimes[processID] = currentTime + executeTime;
                    process.remainingBurstTime -= executeTime;
                    currentTime = completionTimes[processID];

                    if (process.burstTime - process.remainingBurstTime >= 4) {
                        process.queueType = 1; 
                        process.age = 0; 
                        rr1Queue.add(processID);
                    }
                }
                if (process.remainingBurstTime > 0) {
                    allProcessesDone = false;
                }
            }

            for (int i = 0; i < numProcesses; i++) {
                Process process = processes[i];
                if (process.queueType == 1 && process.arrivalTime <= currentTime && process.remainingBurstTime > 0) {
                    int processID = process.id - 1;
                    int executeTime = Math.min(process.remainingBurstTime, 1);
                    completionTimes[processID] = currentTime + executeTime;
                    process.remainingBurstTime -= executeTime;
                    currentTime = completionTimes[processID];

                    if (process.age >= 2) {
                        process.queueType = 0; 
                        fcfsQueue.add(processID);
                    } else {
                        process.age++; 
                    }
                }
                if (process.remainingBurstTime > 0) {
                    allProcessesDone = false;
                }
            }

            if (allProcessesDone) {
                break;
            }
        }

        int[] turnaroundTimes = new int[numProcesses];
        int[] waitingTimes = new int[numProcesses];

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (int i = 0; i < numProcesses; i++) {
            int processID = processIDs[i] - 1;
            turnaroundTimes[processID] = completionTimes[processID] - processes[processID].arrivalTime;
            waitingTimes[processID] = turnaroundTimes[processID] - processes[processID].burstTime;
            totalWaitingTime += waitingTimes[processID];
            totalTurnaroundTime += turnaroundTimes[processID];
        }

        System.out.println("\nGantt Chart:");
        printGanttChart(processIDs, completionTimes);

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / numProcesses));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / numProcesses));
    }

    public static void printGanttChart(int[] processIDs, int[] completionTimes) {
        System.out.println("Gantt Chart:");
        for (int i = 0; i < processIDs.length; i++) {
            System.out.print("P" + processIDs[i] + " | ");
        }
        System.out.println();
        System.out.print("Completion Times: ");
        for (int i = 0; i < completionTimes.length; i++) {
            System.out.print(completionTimes[i] + " | ");
        }
        System.out.println();
    }
}
