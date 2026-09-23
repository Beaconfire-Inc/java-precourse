# CustomCheckedException (Helper Class)

This is a supporting class for [CustomCheckedExceptionDemo](CustomCheckedExceptionDemo.md) — it has no `main` and isn't meant to be run on its own.

```java
public class CustomCheckedException extends Exception {
    public CustomCheckedException(String message) {
        super(message);
    }
}
```

Extending `Exception` (rather than `RuntimeException`) makes this a **checked exception**. The distinction matters: any method that can `throw` a checked exception must either handle it with `try`/`catch` or declare it with `throws` in its own signature — the compiler enforces this. An unchecked exception (extending `RuntimeException`) has no such requirement; it can be thrown from anywhere without any declaration.

Writing your own exception class like this — rather than reusing a generic one — lets calling code catch (and handle) *this specific kind* of failure distinctly from other exceptions, and gives the exception a name that documents what actually went wrong (see [CustomCheckedExceptionDemo](CustomCheckedExceptionDemo.md) for it in use).
