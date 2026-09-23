# Checked vs. Unchecked Exceptions, and Handling Them

Java exceptions split into two families, and this file's `withoutTryCatch` method is written to be able to demonstrate either one:

```java
static void withoutTryCatch() throws IOException {
    int result = 10 / 0;                    // ArithmeticException — unchecked

    int[] array = new int[10];
    Arrays.fill(array, 1);
    System.out.println(array[11]);          // ArrayIndexOutOfBoundsException — unchecked

    FileReader r = new FileReader("file2.txt");  // IOException — checked
}
```

`ArithmeticException` and `ArrayIndexOutOfBoundsException` are both **unchecked** — they extend `RuntimeException`, so nothing forces the method to declare or handle them; they can surface from almost any line of code (dividing by zero, indexing past the end of an array). An `IOException` — thrown here by `FileReader` when the file it's asked to open doesn't exist — is **checked**, which is exactly why `withoutTryCatch` has to declare `throws IOException` in its signature: the compiler won't allow a checked exception to propagate silently.

> **Note on the source file:** the `ArithmeticException` and `ArrayIndexOutOfBoundsException` lines above are commented out in `ExceptionDemo.java` — only the `FileReader` line actually runs. `"file2.txt"` doesn't exist anywhere in this project (there's a leftover, unrelated empty `file.txt` in some checkouts of this material, but the code looks for `file2.txt` specifically), so this call throws a real `FileNotFoundException` (a subclass of `IOException`) when run in an environment where `file2.txt` doesn't exist (as it doesn't in this repo).

## Running it as-is: an uncaught exception

```java
public static void main (String[] args) throws IOException {
    withoutTryCatch();
}
```

Output:

```
Exception in thread "main" java.io.FileNotFoundException: file2.txt (No such file or directory)
	at java.base/java.io.FileInputStream.open0(Native Method)
	...
	at D1_Exception.ExceptionDemo.withoutTryCatch(ExceptionDemo.java:42)
	at D1_Exception.ExceptionDemo.main(ExceptionDemo.java:13)
```

`main` itself declares `throws IOException` instead of catching it, so when `withoutTryCatch()` throws, there's nothing to stop it — it propagates all the way out of `main` and crashes the program with a full stack trace. This is the default behavior of an unhandled exception: the JVM prints the exception type, its message, and the chain of method calls that led to it, then terminates.

## Actually handling it

```java
static void withTryCatch() {
    System.out.println("IN Method 1, Calling Method 2");
    try {
        withoutTryCatch();
    } catch (ArithmeticException ae) { // catching unchecked exception, not recommended
        System.out.println("Arithmetic Exception Handled: " + ae);
    } catch (Exception e) { // catching all the other checked exception
        System.out.println("Exception Handled");
    }
    System.out.println("Successfully return");
}
```

> **Note on the source file:** `main()` only calls `withoutTryCatch()` directly — the call to `withTryCatch()` is commented out, so this method is never actually exercised when you run the file as-is. Calling it instead gives:

```java
public static void main (String[] args) throws IOException {
    withTryCatch();
}
```

Output:

```
IN Method 1, Calling Method 2
Exception Handled
Successfully return
```

The `FileNotFoundException` thrown inside `withoutTryCatch()` doesn't match the first `catch (ArithmeticException ae)` block, so it falls through to `catch (Exception e)`, which matches *any* exception, including this one — that's why `"Exception Handled"` prints rather than `"Arithmetic Exception Handled"`. Once the exception is caught, execution continues normally after the `try`/`catch`, which is why `"Successfully return"` still prints — that line would never be reached in the uncaught version above, since the crash happens before `main` gets the chance to finish.

The inline comment on the first `catch` block — `// catching unchecked exception, not recommended` — is making a style point: catching a specific unchecked exception like `ArithmeticException` is technically legal, but unchecked exceptions usually signal a programming bug (dividing by a variable that shouldn't be zero, an off-by-one index) rather than an expected, recoverable condition — the kind of thing `try`/`catch` is meant for. It's generally better to fix the bug than to catch it.
