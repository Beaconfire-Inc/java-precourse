# Java Pre-Course

Pre-course, self-paced Java material for the Java Full Stack program, organized by session. Each session folder is written to stand on its own, pairing every `.java` source file with a `.md` write-up that explains the concept, walks through the code, and shows its output.

## Prerequisites

- **JDK 8 or newer** (any recent JDK works fine — the examples don't rely on version-specific syntax). Verify with:
  ```bash
  java -version
  javac -version
  ```
- A terminal, or any IDE that can open a folder as a Java project (IntelliJ, VS Code with the Java extension, etc.). Each session's README shows both a terminal-based `javac`/`java` workflow and the IDE alternative.

## How this repo is organized

Every session gets its own top-level folder: a slide deck (PDF), a `src/` folder with one subfolder per topic, and a `README.md` that lists that session's topics in learning order. Start with that folder's `README.md` — it has the compile/run instructions and the full topic list, so this root README doesn't repeat them.

| Session | Topic | Folder |
|---|---|---|
| 1A | Java SE Basics — types, casting, flow control, access modifiers, `static`, `final`, shallow vs. deep copy | [1A-Java-SE/](1A-Java-SE/README.md) |
| 1B | Object-Oriented Programming — inheritance, polymorphism, abstraction, encapsulation, immutability | [1B-OOP/](1B-OOP/README.md) |
| 1C | Collections — List, Set, Queue, Map, HashMap internals, Comparable vs. Comparator, fail-fast/fail-safe iterators | [1C-Collections/](1C-Collections/README.md) |
| 1D | Exceptions, Java 8 & I/O — checked/unchecked exceptions, Optional, Consumer/Function/Predicate/Supplier, Streams, serialization | [1D-Exceptions/](1D-Exceptions/README.md) |
| 1E | Threads — creation, life cycle, daemon threads, race conditions, deadlock, thread-safe singleton | [1E-Threads/](1E-Threads/README.md) |

Sessions are meant to be worked through in order — each one assumes everything from the sessions before it. As more sessions are added, they'll be appended to this table in the order they're meant to be studied.

## Getting the code

```bash
git clone git@github.com:Beaconfire-Inc/java-precourse.git
cd java-precourse
```

Then open whichever session folder you're on and follow its own `README.md`.

## Got thoughts?

Found a typo, think some example could be explained better, or just want to throw in your own demo? Open an issue or send a PR — this repo's meant to grow with whoever's learning from it, not stay frozen the day it was published.

## License

[MIT](LICENSE) — use, copy, modify, and share freely, including commercially. Just keep the copyright notice.
