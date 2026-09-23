# 1E — Threads

This is Day 5 of the Java Full Stack program. It picks up where [1D — Exceptions, Java 8 & I/O](../1D-Exceptions/README.md) left off and covers multithreading: creating threads, their life cycle, daemon threads, the classic concurrency problems (race conditions, deadlock) and how to avoid them, and thread-safe lazy initialization.

Designed for self-paced learning — each topic below is written to stand on its own.

## How to use this folder

Each topic has two files, sitting next to each other in the same folder:

- **`*.md`** — read this first. It explains the concept in plain language and walks through the code and its output.
- **`*.java`** — then open/run this yourself. Reading about the output isn't the same as seeing it — compile and run each file, then try changing values and see what happens.

To compile and run any example, assuming your terminal is opened at this folder (`1E-Threads`):

```bash
cd src
javac D1_Creation/PrintNumber.java
java D1_Creation.PrintNumber
```

The pattern is always the same: `cd src`, then `javac <package>/<ClassName>.java`, then `java <package>.<ClassName>` — swap in whichever topic folder and file you're currently on. `javac` and `java` both use dots (`.`) for the package name, but the file path on disk still uses a slash (`/`) — that mapping between "package name" and "folder path" is exactly the concept these examples are teaching, so it's worth typing out by hand instead of copy-pasting a shortcut.

(Or just open the `src` folder as a project in IntelliJ / your IDE of choice and run any file directly.)

A few things specific to this topic, worth knowing before you start running files:

- **Output order can vary between runs.** Several demos run multiple threads at once with no guaranteed ordering between them (e.g. [TicketSeller](src/D1_Creation/TicketSeller.md)) — that's expected, and each `.md` says so where it applies.
- **Two demos never terminate on their own**: [BlockingQueueDemo](src/D4_ThreadProblems/BlockingQueueDemo.md) loops forever by design, and the hidden deadlock demo in [Counter.md](src/D4_ThreadProblems/Counter.md#a-second-demo-hidden-in-a-nested-class) genuinely deadlocks forever. Both need to be stopped manually (Ctrl+C in your terminal).
- **One demo is invoked differently**: the deadlock demo just mentioned lives in a nested class inside `Counter.java`, so it's run as `java D4_ThreadProblems.Counter\$driver` (see that file's `.md` for why).

## Folder structure

```
1E-Threads/                                  <- assumed terminal root
├── README.md                                <- this file
├── 1E_Threads_I.pdf                          Slide deck (topics match the list below 1:1)
└── src/
    ├── D1_Creation/                      Creating threads: Runnable, extending Thread, sharing a Runnable
    ├── D2_LifeCycle/                     sleep, interrupt, join, wait/notify, start vs. run
    ├── D3_DaemonThread/                  Daemon threads
    ├── D4_ThreadProblems/                Race conditions, deadlock, and how to avoid them
    └── D5_Singleton/                     Thread-safe lazy singleton (double-checked locking)
```

Each `D*` folder name matches the topic it teaches; every `.java` file in it has a matching `.md` write-up of the same name.

## Learning path

Go through these in order — each one builds on ideas from the ones before it.

| # | Topic | Concept doc | Code |
|---|---|---|---|
| 1a | Creating a thread: `Runnable` | [MyRunnable.md](src/D1_Creation/MyRunnable.md) | [MyRunnable.java](src/D1_Creation/MyRunnable.java) |
| 1b | Creating a thread: extending `Thread`; `start()` vs. `run()` | [MyThread.md](src/D1_Creation/MyThread.md) | [MyThread.java](src/D1_Creation/MyThread.java) |
| 1c | A minimal `Thread` subclass | [PrintNumber.md](src/D1_Creation/PrintNumber.md) | [PrintNumber.java](src/D1_Creation/PrintNumber.java) |
| 1d | Sharing one `Runnable` across multiple threads | [TicketSeller.md](src/D1_Creation/TicketSeller.md) | [TicketSeller.java](src/D1_Creation/TicketSeller.java) |
| 2a | `join(timeout)` | [JoinWithArgumentDemo.md](src/D2_LifeCycle/JoinWithArgumentDemo.md) | [JoinWithArgumentDemo.java](src/D2_LifeCycle/JoinWithArgumentDemo.java) |
| 2b | `sleep()`, `interrupt()`, `join()` | [LifeCycleDemo.md](src/D2_LifeCycle/LifeCycleDemo.md) | [LifeCycleDemo.java](src/D2_LifeCycle/LifeCycleDemo.java) |
| 2c | `wait()`/`notify()` — the producer-consumer pattern | [ProducerConsumer.md](src/D2_LifeCycle/ProducerConsumer.md) | [ProducerConsumer.java](src/D2_LifeCycle/ProducerConsumer.java) |
| 2d | Why a thread can only be started once | [StartVSRunDemo.md](src/D2_LifeCycle/StartVSRunDemo.md) | [StartVSRunDemo.java](src/D2_LifeCycle/StartVSRunDemo.java) |
| 3 | Daemon threads | [DaemonThreadDemo.md](src/D3_DaemonThread/DaemonThreadDemo.md) | [DaemonThreadDemo.java](src/D3_DaemonThread/DaemonThreadDemo.java) |
| 4a | `BlockingQueue` (runs forever — see note above) | [BlockingQueueDemo.md](src/D4_ThreadProblems/BlockingQueueDemo.md) | [BlockingQueueDemo.java](src/D4_ThreadProblems/BlockingQueueDemo.java) |
| 4b | Synchronization, and a hidden deadlock demo (see note above) | [Counter.md](src/D4_ThreadProblems/Counter.md) | [Counter.java](src/D4_ThreadProblems/Counter.java) |
| 4c | Deadlock — attempted (and why it doesn't actually happen) | [DeadLockDemo.md](src/D4_ThreadProblems/DeadLockDemo.md) | [DeadLockDemo.java](src/D4_ThreadProblems/DeadLockDemo.java) |
| 4d | A mutual-`join()` deadlock, rescued by a timeout | [DeadLockDemo2.md](src/D4_ThreadProblems/DeadLockDemo2.md) | [DeadLockDemo2.java](src/D4_ThreadProblems/DeadLockDemo2.java) |
| 4e | Avoiding it with `ReentrantLock.tryLock()` | [ReentrantLockSolution.md](src/D4_ThreadProblems/ReentrantLockSolution.md) | [ReentrantLockSolution.java](src/D4_ThreadProblems/ReentrantLockSolution.java) |
| 5 | Thread-safe lazy singleton | [Singleton.md](src/D5_Singleton/Singleton.md) | [Singleton.java](src/D5_Singleton/Singleton.java) |
