# Custom Checked Exceptions

This demonstrates writing and using your own checked exception, [CustomCheckedException](CustomCheckedException.md), instead of relying only on Java's built-in ones.

```java
public class CustomCheckedExceptionDemo {

    public static void main(String[] args) {
        try {
            validateAge(14);
        } catch (CustomCheckedException e) {
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }
    }

    public static void validateAge(int age) throws CustomCheckedException {
        if (age < 18) {
            throw new CustomCheckedException("The user must be at least 18 years old");
        }
    }
}
```

Output:

```
The user must be at least 18 years old
```

`validateAge` declares `throws CustomCheckedException` — because [CustomCheckedException](CustomCheckedException.md) is a *checked* exception, the compiler requires this declaration on any method that might throw it. `main` then wraps the call in `try`/`catch`, catching specifically `CustomCheckedException` rather than a generic `Exception`. That specificity is the payoff of writing a custom exception class: calling code can catch (and react to) *this particular kind of failure* — an invalid age — without accidentally swallowing unrelated exceptions too.

`e.getMessage()` returns the string passed to the constructor (`"The user must be at least 18 years old"`), which is exactly the message printed. The commented-out `e.printStackTrace()` is an alternative to `getMessage()` — instead of (or in addition to) the message, it prints the full call stack showing exactly where the exception was thrown, which is more useful for debugging but noisier for a clean demo.
