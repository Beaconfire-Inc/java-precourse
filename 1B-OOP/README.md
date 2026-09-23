# 1B — Object-Oriented Programming

This is Day 2 of the Java Full Stack program. It picks up where [1A — Java SE Basics](../1A-Java-SE/README.md) left off and covers a common teaching model for OOP — the four pillars: inheritance, polymorphism, encapsulation, and abstraction — plus immutability, which builds directly on encapsulation.

Designed for self-paced learning — each topic below is written to stand on its own, with no video or lecture required.

## How to use this folder

Each topic has two files, sitting next to each other in the same folder:

- **`*.md`** — read this first. It explains the concept in plain language and walks through the code and its output.
- **`*.java`** — then open/run this yourself. Reading about the output isn't the same as seeing it — compile and run each file, then try changing values and see what happens.

To compile and run any example, assuming your terminal is opened at this folder (`1B-OOP`):

```bash
cd src
javac D1_Inheritance/InheritanceDemo.java
java D1_Inheritance.InheritanceDemo
```

The pattern is always the same: `cd src`, then `javac <package>/<ClassName>.java`, then `java <package>.<ClassName>` — swap in whichever topic folder and file you're currently on. `javac` and `java` both use dots (`.`) for the package name, but the file path on disk still uses a slash (`/`) — that mapping between "package name" and "folder path" is exactly the concept these examples are teaching, so it's worth typing out by hand instead of copy-pasting a shortcut.

(Or just open the `src` folder as a project in IntelliJ / your IDE of choice and run any file directly.)

A couple of the `.md` write-ups in this folder show a working version of the demo that differs slightly from the `.java` source — for example, where the source has a section commented out, or an extra bit of code not used in the walkthrough. Those differences are always called out explicitly in the `.md` with a **Note** — the source file itself is never silently modified, so what you compile and run always matches what's actually in the repo.

## Folder structure

```
1B-OOP/                                  <- assumed terminal root
├── README.md                            <- this file
├── 1B_Java_OOP.pdf                      Slide deck (topics match the list below 1:1)
└── src/
    ├── D0_Playground/                Constructors & object state, explored via debugger
    ├── D1_Inheritance/               extends, super, constructor chaining, this
    ├── D2_DiamondProblem/            Why Java disallows multiple inheritance, and the workarounds
    ├── D3_Polymorphism/              Overriding (runtime) vs. overloading (compile-time)
    ├── D4_Abstract/                  Abstract classes vs. interfaces
    ├── D5_Encapsulation/             private fields + public getters/setters
    └── D6_Immutable_Class/           Mutable vs. immutable objects, defensive copying
```

Each `D*` folder name matches the topic it teaches; every `.java` file in it has a matching `.md` write-up of the same name.

## Learning path

Go through these in order — each one builds on ideas from the ones before it.

| # | Topic | Concept doc | Code |
|---|---|---|---|
| 0 | Constructors & object state (debugger exercise) | [Playground.md](src/D0_Playground/Playground.md) | [Playground.java](src/D0_Playground/Playground.java) |
| 1a | Inheritance — `extends`, `super`, constructor chaining | [InheritanceDemo.md](src/D1_Inheritance/InheritanceDemo.md) | [InheritanceDemo.java](src/D1_Inheritance/InheritanceDemo.java) |
| 1b | The `this` keyword | [ThisDemo.md](src/D1_Inheritance/ThisDemo.md) | [ThisDemo.java](src/D1_Inheritance/ThisDemo.java) |
| 2 | The diamond problem — interfaces & aggregation | [DiamondProblemDemo.md](src/D2_DiamondProblem/DiamondProblemDemo.md) | [DiamondProblemDemo.java](src/D2_DiamondProblem/DiamondProblemDemo.java) |
| 3 | Polymorphism — overriding vs. overloading | [PolymorphismDemo.md](src/D3_Polymorphism/PolymorphismDemo.md) | [PolymorphismDemo.java](src/D3_Polymorphism/PolymorphismDemo.java) |
| 4 | Abstract classes & interfaces | [AbstractDemo.md](src/D4_Abstract/AbstractDemo.md) | [AbstractDemo.java](src/D4_Abstract/AbstractDemo.java) |
| 5 | Encapsulation | [EncapsulationDemo.md](src/D5_Encapsulation/EncapsulationDemo.md) | [EncapsulationDemo.java](src/D5_Encapsulation/EncapsulationDemo.java) |
| 6a | Helper class used by 6b/6c (not directly runnable) | [Address.md](src/D6_Immutable_Class/Address.md) | [Address.java](src/D6_Immutable_Class/Address.java) |
| 6b | A mutable class (baseline) | [MutablePersonDemo.md](src/D6_Immutable_Class/MutablePersonDemo.md) | [MutablePersonDemo.java](src/D6_Immutable_Class/MutablePersonDemo.java) |
| 6c | An immutable class — `final` + defensive copying | [ImmutablePersonDemo.md](src/D6_Immutable_Class/ImmutablePersonDemo.md) | [ImmutablePersonDemo.java](src/D6_Immutable_Class/ImmutablePersonDemo.java) |

`Address.java` (6a) has no `main` method — it's a supporting class used by both 6b and 6c, so it's not meant to be run on its own.
