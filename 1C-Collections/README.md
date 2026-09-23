# 1C — Collections

This is Day 3 of the Java Full Stack program. It picks up where [1B — Object-Oriented Programming](../1B-OOP/README.md) left off and covers the Java Collections Framework — `List`, `Set`, `Queue`, `Map`, how a hash-based map actually works under the hood, and how ordering/sorting works via `Comparable` and `Comparator`.

Designed for self-paced learning — each topic below is written to stand on its own.

## How to use this folder

Each topic has two files, sitting next to each other in the same folder:

- **`*.md`** — read this first. It explains the concept in plain language and walks through the code and its output.
- **`*.java`** — then open/run this yourself. Reading about the output isn't the same as seeing it — compile and run each file, then try changing values and see what happens.

To compile and run any example, assuming your terminal is opened at this folder (`1C-Collections`):

```bash
cd src
javac D1_List/ListDemo.java
java D1_List.ListDemo
```

The pattern stays the same: `cd src`, then `javac <package>/<ClassName>.java`, then `java <package>.<ClassName>` — swap in whichever topic folder and file you're currently on. `javac` and `java` both use dots (`.`) for the package name, but the file path on disk still uses a slash (`/`) — that mapping between "package name" and "folder path" is exactly the concept these examples are teaching, so it's worth typing out by hand instead of copy-pasting a shortcut.

(Or just open the `src` folder as a project in IntelliJ / your IDE of choice and run any file directly.)

A couple of files have a helper class alongside the runnable demo (no `main` of its own) — those are noted in the learning path table below. And one demo (`D7_FailFast_FailSafe`) is *supposed* to crash partway through when you run it — that's covered in its `.md`, so it's expected behavior, not a bug in the demo.

## Folder structure

```
1C-Collections/                              <- assumed terminal root
├── README.md                                <- this file
├── 1C_Java_Collections_Updated.pdf          Slide deck (topics match the list below 1:1)
└── src/
    ├── D0_Collection_Overview/          Collection interface vs. Collections utility class
    ├── D1_List/                         ArrayList & LinkedList
    ├── D2_Set/                          HashSet & LinkedHashSet
    ├── D3_Queue/                        LinkedList as a Queue, PriorityQueue
    ├── D4_Map/                          HashMap, LinkedHashMap, TreeMap
    ├── D5_HashMap_Internals/            Building a hash map from scratch
    ├── D6_Ordering/                     Comparable (natural order) vs. Comparator (custom order)
    └── D7_FailFast_FailSafe/            Fail-fast vs. fail-safe iterators
```

Each `D*` folder name matches the topic it teaches; every `.java` file in it has a matching `.md` write-up of the same name.

## Learning path

Go through these in order — each one builds on ideas from the ones before it.

| # | Topic | Concept doc | Code |
|---|---|---|---|
| 0 | `Collection` vs. `Collections`, basic utility methods | [CollectionDemo.md](src/D0_Collection_Overview/CollectionDemo.md) | [CollectionDemo.java](src/D0_Collection_Overview/CollectionDemo.java) |
| 1 | `List` — `ArrayList` & `LinkedList` | [ListDemo.md](src/D1_List/ListDemo.md) | [ListDemo.java](src/D1_List/ListDemo.java) |
| 2 | `Set` — `HashSet` & `LinkedHashSet` | [SetDemo.md](src/D2_Set/SetDemo.md) | [SetDemo.java](src/D2_Set/SetDemo.java) |
| 3 | `Queue` — `LinkedList` & `PriorityQueue` | [QueueDemo.md](src/D3_Queue/QueueDemo.md) | [QueueDemo.java](src/D3_Queue/QueueDemo.java) |
| 4 | `Map` — `HashMap`, `LinkedHashMap`, `TreeMap` | [MapDemo.md](src/D4_Map/MapDemo.md) | [MapDemo.java](src/D4_Map/MapDemo.java) |
| 5a | Helper class for 5b (not directly runnable) | [MyHashMapNode.md](src/D5_HashMap_Internals/MyHashMapNode.md) | [MyHashMapNode.java](src/D5_HashMap_Internals/MyHashMapNode.java) |
| 5b | Building a `HashMap` from scratch | [MyHashMap.md](src/D5_HashMap_Internals/MyHashMap.md) | [MyHashMap.java](src/D5_HashMap_Internals/MyHashMap.java) |
| 6a | Natural ordering — `Comparable` on built-in types | [Ordering.md](src/D6_Ordering/Ordering.md) | [Ordering.java](src/D6_Ordering/Ordering.java) |
| 6b | Helper class for 6c — custom `Comparable` (not directly runnable) | [Student.md](src/D6_Ordering/Student.md) | [Student.java](src/D6_Ordering/Student.java) |
| 6c | Custom ordering — `Comparator`: anonymous class vs. lambda | [AnonymousVSLambda.md](src/D6_Ordering/AnonymousVSLambda.md) | [AnonymousVSLambda.java](src/D6_Ordering/AnonymousVSLambda.java) |
| 7 | Fail-fast vs. fail-safe iterators | [FailFastFailSafeIteratorDemo.md](src/D7_FailFast_FailSafe/FailFastFailSafeIteratorDemo.md) | [FailFastFailSafeIteratorDemo.java](src/D7_FailFast_FailSafe/FailFastFailSafeIteratorDemo.java) |
