# 1A — Java SE Basics

This is Day 1 of the Java Full Stack program. It covers Java's basic syntax, primitive types, flow control, and a first look at classes/objects — the building blocks everything later builds on. The four pillars of OOP (a common teaching model: inheritance, polymorphism, encapsulation, abstraction), collections, and exceptions are **not** covered here — they're part of the next session.

Designed for self-paced learning — each topic below is written to stand on its own, with no video or lecture required.

## How to use this folder

Each topic has two files, sitting next to each other in the same folder:

- **`*.md`** — read this first. It explains the concept in plain language and walks through the code and its output.
- **`*.java`** — then open/run this yourself. Reading about the output isn't the same as seeing it — compile and run each file, then try changing values and see what happens.

To compile and run any example, assuming your terminal is opened at this folder (`1A-Java-SE`):

```bash
cd src
javac D1_conversion_casting/ConversionAndCasting.java
java D1_conversion_casting.ConversionAndCasting
```

The pattern is always the same: `cd src`, then `javac <package>/<ClassName>.java`, then `java <package>.<ClassName>` — swap in whichever topic folder and file you're currently on. `javac` and `java` both use dots (`.`) for the package name, but the file path on disk still uses a slash (`/`) — that mapping between "package name" and "folder path" is exactly the concept these examples are teaching, so it's worth typing out by hand instead of copy-pasting a shortcut.

(Or just open the `src` folder as a project in IntelliJ / your IDE of choice and run any file directly.)

## Folder structure

```
1A-Java-SE/                              <- assumed terminal root
├── README.md                            <- this file
├── 1A_Java_SE_Basics.pdf                Slide deck (topics match the list below 1:1)
└── src/
    ├── D1_conversion_casting/       Type conversion & casting
    ├── D2_string_pool/              String immutability & the String Pool
    ├── D3_flow_control/             if / switch / loops / break / continue / return
    ├── D4_bitwise_operator/         Bitwise & shift operators
    ├── D5_access_modifier/          public / private / protected / default (same package)
    ├── D5_access_modifier2/         Access modifiers across packages & inheritance
    ├── D6_static_example/           static vs. instance members
    ├── D7_final_example/            final variables / methods / classes
    ├── D8_deep_copy/                Shallow copy vs. deep copy (Cloneable)
    ├── EX1_immutable/               Exercise: designing an immutable class
    ├── EX2_pass_by_value/           Exercise: pass-by-value semantics
    └── EX3_WrapperClassRequirement/ Exercise: autoboxing & wrapper classes
```

Each `D*`/`EX*` folder name matches the topic it teaches; every `.java` file in it has a matching `.md` write-up of the same name.

## Learning path

Go through these in order — each one builds on ideas from the ones before it.

| # | Topic | Concept doc | Code |
|---|---|---|---|
| 1 | Type conversion & casting | [ConversionAndCasting.md](src/D1_conversion_casting/ConversionAndCasting.md) | [ConversionAndCasting.java](src/D1_conversion_casting/ConversionAndCasting.java) |
| 2 | String Pool | [StringPoolExample.md](src/D2_string_pool/StringPoolExample.md) | [StringPoolExample.java](src/D2_string_pool/StringPoolExample.java) |
| 3a | Flow control — `break` | [BreakExample.md](src/D3_flow_control/BreakExample.md) | [BreakExample.java](src/D3_flow_control/BreakExample.java) |
| 3b | Flow control — `continue` | [ContinueExample.md](src/D3_flow_control/ContinueExample.md) | [ContinueExample.java](src/D3_flow_control/ContinueExample.java) |
| 3c | Flow control — `return` | [ReturnExample.md](src/D3_flow_control/ReturnExample.md) | [ReturnExample.java](src/D3_flow_control/ReturnExample.java) |
| 3d | Flow control — `switch` | [SwitchExample.md](src/D3_flow_control/SwitchExample.md) | [SwitchExample.java](src/D3_flow_control/SwitchExample.java) |
| 4 | Bitwise & shift operators | [BitOperationExample.md](src/D4_bitwise_operator/BitOperationExample.md) | [BitOperationExample.java](src/D4_bitwise_operator/BitOperationExample.java) |
| 5a | Access modifiers (same package) | [Demo.md](src/D5_access_modifier/Demo.md) | [Demo.java](src/D5_access_modifier/Demo.java) |
| 5b | Access modifiers (same package, different class) | [Demo2.md](src/D5_access_modifier/Demo2.md) | [Demo2.java](src/D5_access_modifier/Demo2.java) |
| 5c | Access modifiers (different package) | [DemoInAnotherPackage.md](src/D5_access_modifier2/DemoInAnotherPackage.md) | [DemoInAnotherPackage.java](src/D5_access_modifier2/DemoInAnotherPackage.java) |
| 5d | `protected` across packages via inheritance | [SubDemoInAnotherPackage.md](src/D5_access_modifier2/SubDemoInAnotherPackage.md) | [SubDemoInAnotherPackage.java](src/D5_access_modifier2/SubDemoInAnotherPackage.java) |
| 6a | Static vs. instance members | [Sample.md](src/D6_static_example/Sample.md) | [Sample.java](src/D6_static_example/Sample.java) |
| 6b | Static vs. instance — demo | [Driver.md](src/D6_static_example/Driver.md) | [Driver.java](src/D6_static_example/Driver.java) |
| 7a | `final` variable | [FinalVariables.md](src/D7_final_example/FinalVariables.md) | [FinalVariables.java](src/D7_final_example/FinalVariables.java) |
| 7b | `final` method | [FinalMethod.md](src/D7_final_example/FinalMethod.md) | [FinalMethod.java](src/D7_final_example/FinalMethod.java) |
| 7c | `final` class | [FinalClass.md](src/D7_final_example/FinalClass.md) | [FinalClass.java](src/D7_final_example/FinalClass.java) |
| 8a | Deep copy — `Cloneable` helper class | [Address.md](src/D8_deep_copy/Address.md) | [Address.java](src/D8_deep_copy/Address.java) |
| 8b | Deep copy of a nested object | [Person.md](src/D8_deep_copy/Person.md) | [Person.java](src/D8_deep_copy/Person.java) |
| 8c | Shallow "copy" vs. `Cloneable` deep copy | [DeepCopyExample.md](src/D8_deep_copy/DeepCopyExample.md) | [DeepCopyExample.java](src/D8_deep_copy/DeepCopyExample.java) |
| 8d | Exercise: shallow vs. deep copy, one level deeper | [TwoDDemo.md](src/D8_deep_copy/TwoDDemo.md) | [TwoDDemo.java](src/D8_deep_copy/TwoDDemo.java) |
| EX1 | Exercise: immutable class | [Student.md](src/EX1_immutable/Student.md) | [Student.java](src/EX1_immutable/Student.java) |
| EX2 | Exercise: pass-by-value | [Example.md](src/EX2_pass_by_value/Example.md) | [Example.java](src/EX2_pass_by_value/Example.java) |
| EX3 | Exercise: wrapper classes & autoboxing | [WrapperClass.md](src/EX3_WrapperClassRequirement/WrapperClass.md) | [WrapperClass.java](src/EX3_WrapperClassRequirement/WrapperClass.java) |

D1–D8 are core concepts; EX1–EX3 are follow-up exercises meant to be worked through after finishing D1–D8, to check that the concepts actually stuck.
