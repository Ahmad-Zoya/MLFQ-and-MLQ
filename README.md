# Multilevel Queue (MLQ) and Multilevel Feedback Queue (MLFQ) Scheduling

## Overview

This project implements two CPU scheduling algorithms: **Multilevel Queue (MLQ) Scheduling** and **Multilevel Feedback Queue (MLFQ) Scheduling**. The aim is to simulate both scheduling techniques and evaluate their performance based on average waiting time and turnaround time.

### MLQ Scheduling:
In **Multilevel Queue Scheduling**, processes are classified into multiple queues based on their priority or other attributes. Each queue uses a different scheduling algorithm like **First-Come-First-Serve (FCFS)**, **Round-Robin (RR1)**, or **Round-Robin (RR2)**.

### MLFQ Scheduling:
In **Multilevel Feedback Queue Scheduling**, a process can move between different queues based on its behavior and time requirements, allowing for a more dynamic scheduling system.

---

## Features

- **Simulates Multiple Queues**: 
  - **FCFS Queue**: Executes processes in a first-come-first-serve manner.
  - **Round-Robin Queues (RR1 and RR2)**: Executes processes based on time slices (quantums).
  
- **User Input**: 
  - Number of processes, their **arrival times**, **burst times**, and the **type of scheduling** (FCFS, RR1, RR2).
  
- **Performance Metrics**: 
  - Calculates **average waiting time** and **average turnaround time**.
  
- **Gantt Chart Simulation**: 
  - Visualizes the execution of processes across different scheduling queues.

---

## Project Structure

- **MLQ.java**: Implements the Multilevel Queue Scheduling Algorithm.
- **MLFQ.java**: Implements the Multilevel Feedback Queue Scheduling Algorithm.
  
Both programs take user input for process arrival times and burst times, then classify and schedule the processes accordingly.

---

## How to Run

### Prerequisites:
- Java Development Kit (JDK) installed.
- A Java IDE (Eclipse, IntelliJ, or even the command line).

### Steps:
1. **Compile the Program**:  
   ```bash
   javac MLQ.java
   javac MLFQ.java
   ```

2. **Run the Program**:
   ```bash
   java MLQ
   java MLFQ
   ```

3. **Input Data**:  
   - Enter the number of processes.
   - For each process, enter the **Process ID**, **Arrival Time**, **Burst Time**, and **Queue Type** (0 for FCFS, 1 for RR1, 2 for RR2).

4. **View Results**:  
   - The output will display the **Gantt Chart** for each queue, **Average Waiting Time**, and **Average Turnaround Time** for the processes.

---

## Example

### Input:
```
Enter the number of processes: 3
Enter details for Process 1:
Process ID: 1
Arrival Time: 0
Burst Time: 5
Queue Type (0: FCFS, 1: RR1, 2: RR2): 0
```

### Output:
```
FCFS Queue: P1 | P2 |
RR1 Queue: P3 |
RR2 Queue: |

Average Waiting Time: 2.67
Average Turnaround Time: 6.33
```

---

## Performance Evaluation

The implemented algorithms simulate the scheduling behavior of **MLQ** and **MLFQ**, allowing for the comparison of traditional queueing methods and more dynamic feedback systems. The **waiting time** and **turnaround time** metrics serve as indicators of efficiency.

---

## Future Improvements

- **Dynamic Time Quantum**: Introduce the ability to dynamically adjust time quantum based on process behavior.
- **More Queues**: Add more flexibility in creating additional queues.
- **Preemptive Scheduling**: Improve upon preemptive scheduling mechanisms for certain queue types.

---

## References

- **Operating System Concepts** by Silberschatz, Galvin, and Gagne – Reference for scheduling algorithms.
  
---

## License

This project is for educational purposes and is not distributed under any specific license.

