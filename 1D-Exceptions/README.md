# 1D — Exceptions, Java 8 & I/O

This is Day 4 of the Java Full Stack program. It picks up where [1C — Collections](../1C-Collections/README.md) left off and covers exception handling (checked vs. unchecked, custom exceptions), the functional-interface additions from Java 8 (`Consumer`, `Function`, `Predicate`, `Supplier`), the Stream API, and object serialization.

Designed for self-paced learning — each topic below is written to stand on its own.

## How to use this folder

Each topic has two files, sitting next to each other in the same folder:

- **`*.md`** — read this first. It explains the concept in plain language and walks through the code and its output.
- **`*.java`** — then open/run this yourself. Reading about the output isn't the same as seeing it — compile and run each file, then try changing values and see what happens.

To compile and run any example, assuming your terminal is opened at this folder (`1D-Exceptions`):

```bash
cd src
javac D1_Exception/CustomCheckedExceptionDemo.java
java D1_Exception.CustomCheckedExceptionDemo
```

The pattern is always the same: `cd src`, then `javac <package>/<ClassName>.java`, then `java <package>.<ClassName>` — swap in whichever topic folder and file you're currently on. `javac` and `java` both use dots (`.`) for the package name, but the file path on disk still uses a slash (`/`) — that mapping between "package name" and "folder path" is exactly the concept these examples are teaching, so it's worth typing out by hand instead of copy-pasting a shortcut.

(Or just open the `src` folder as a project in IntelliJ / your IDE of choice and run any file directly.)

One pair of files in `D4_Serialization` is stateful and needs to be run **in order**: `SerializationDemo` first (it writes a `student.ser` file to your current directory), then `DeserializationDemo` (it reads that file back in). Run both from the same directory so the second one can find what the first one wrote — see [SerializationDemo.md](src/D4_Serialization/SerializationDemo.md) for details. That generated `.ser` file isn't part of this repo (it's gitignored, like compiled `.class` files) — you'll produce it yourself by running the demo.

## Folder structure

```
1D-Exceptions/                               <- assumed terminal root
├── README.md                                <- this file
├── 1D_Exceptions_Java8_IO.pdf                Slide deck (topics match the list below 1:1)
└── src/
    ├── D1_Exception/                     Checked vs. unchecked exceptions, custom exceptions, Optional
    ├── D2_FunctionalInterfaces/          Consumer, Function, Predicate, Supplier
    ├── D3_StreamAPI/                     The Stream API: filter / map / sorted / collect
    ├── D4_Serialization/                 Writing and reading objects with ObjectOutputStream/ObjectInputStream
    └── EX_OtherJava8Features/            Exercise: Iterator
```

Each `D*`/`EX*` folder name matches the topic it teaches; every `.java` file in it has a matching `.md` write-up of the same name.

## Learning path

Go through these in order — each one builds on ideas from the ones before it.

| # | Topic | Concept doc | Code |
|---|---|---|---|
| 1a | Helper class for 1b (not directly runnable) | [CustomCheckedException.md](src/D1_Exception/CustomCheckedException.md) | [CustomCheckedException.java](src/D1_Exception/CustomCheckedException.java) |
| 1b | Custom checked exceptions | [CustomCheckedExceptionDemo.md](src/D1_Exception/CustomCheckedExceptionDemo.md) | [CustomCheckedExceptionDemo.java](src/D1_Exception/CustomCheckedExceptionDemo.java) |
| 1c | Checked vs. unchecked exceptions, try/catch | [ExceptionDemo.md](src/D1_Exception/ExceptionDemo.md) | [ExceptionDemo.java](src/D1_Exception/ExceptionDemo.java) |
| 1d | `Optional<T>` | [OptionalDemo.md](src/D1_Exception/OptionalDemo.md) | [OptionalDemo.java](src/D1_Exception/OptionalDemo.java) |
| 2a | `Consumer<T>` | [ConsumerDemo.md](src/D2_FunctionalInterfaces/ConsumerDemo.md) | [ConsumerDemo.java](src/D2_FunctionalInterfaces/ConsumerDemo.java) |
| 2b | `Function<T, R>` | [FunctionDemo.md](src/D2_FunctionalInterfaces/FunctionDemo.md) | [FunctionDemo.java](src/D2_FunctionalInterfaces/FunctionDemo.java) |
| 2c | `Predicate<T>` | [PredicateDemo.md](src/D2_FunctionalInterfaces/PredicateDemo.md) | [PredicateDemo.java](src/D2_FunctionalInterfaces/PredicateDemo.java) |
| 2d | `Supplier<T>` | [SupplierDemo.md](src/D2_FunctionalInterfaces/SupplierDemo.md) | [SupplierDemo.java](src/D2_FunctionalInterfaces/SupplierDemo.java) |
| 3 | The Stream API | [StreamAPIDemo.md](src/D3_StreamAPI/StreamAPIDemo.md) | [StreamAPIDemo.java](src/D3_StreamAPI/StreamAPIDemo.java) |
| 4a | Serialization — writing an object to disk | [SerializationDemo.md](src/D4_Serialization/SerializationDemo.md) | [SerializationDemo.java](src/D4_Serialization/SerializationDemo.java) |
| 4b | Deserialization — reading it back (run 4a first) | [DeserializationDemo.md](src/D4_Serialization/DeserializationDemo.md) | [DeserializationDemo.java](src/D4_Serialization/DeserializationDemo.java) |
| EX | Exercise: `Iterator` | [IteratorDemo.md](src/EX_OtherJava8Features/IteratorDemo.md) | [IteratorDemo.java](src/EX_OtherJava8Features/IteratorDemo.java) |

`CustomCheckedException.java` (1a) has no `main` method — it's a supporting class used by 1b, so it's not meant to be run on its own.
