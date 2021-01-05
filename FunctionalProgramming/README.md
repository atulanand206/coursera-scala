## Week 1

1. Sbt interpreter can be launched using command sbt in terminal, followed by the console command in the sbt terminal.
    1. All the methods are available to be consumed in the Sbt interpreter.
    2. For e.g. running `println(2+2)` prints the value.
    3. Or running `2+2` creates a variable `res1`.
    4. As Scala has implicit returns for the functions, scala functions usually return a new variable, like
       calling `res1` in the interpreter will create a new variable `res2` and return that in the interpreter.
    5. We can also mention the variable in the interpreter statement and that variable name will be used in the return
       value.

2. Differences from Java
    1. Scala classes can't have static members.
    2. Traits are like interfaces & they can have default method implementations.
    3. Objects are like classes, but they are singleton in nature.

3. Key features of Scala
    1. `_` acts as wildcard to import all the members of a scala, or if a variable name is not required to be referenced
       usually in for loops.
    2. The main method in a class can reside inside the Scala Objects, does not work with Classes.
    3. Functions can be defined and referenced directly in the Scala REPL.
    4. Parentheses are not required around the method definition when it's a one-liner. A single If-else construct
       inside a method still keeps the method a one-liner if their bodies are also one-liner.

4. Points to remember
    1. If you find yourself wanting to use casts, take a step back and think about what you're trying to achieve.
    2. Avoid mutable local variables and replace it with helper functions that take accumulators, simply don't allow
       variables to change value inside the method. If you must change values, refactor it to a separate method.
    3. The definition between val and def becomes apparent when the right-hand side does not terminate.

5. Expression evaluation
    1. Expression evaluation is reduction of an expression to a value.
    2. Parameters are not evaluated until required in the Call by name paradigm.
    3. Non-terminating parameter which isn't used, if passed in a function will not affect the termination of evaluation
       in the call by name paradigm.
    4. Call by value paradigm requires all the arguments to be evaluated before the function evaluation commences.
    5. The most common use case of non-termination is if an argument returns itself, the call by value paradigm will
       result into an infinite loop and never get terminated.
    5. Scala uses Call by value as call by value is exponentially more efficient for functions in practice because it
       avoids repeated re-computation of argument expression that call by name entails.
    6. Scala has the functionality to force call by name. While defining the argument's data type in a function, `:` can
       be replaced with `: =>` and that argument would be referenced as call by name.
    7. Functions can be defined as referencing an infinite loop as it won't be evaluated then and there, while when done
       so with val, it will lead to an infinite loop instantly.

6. Conditionals
    1. Conditional operators can be evaluated using equivalent functions composed of if-else constructs.
    2. Some arguments may not be required to get evaluated like the y parameter in the `Conditionals.and` implementation
       and if that's defined using `: =>`, we can pass non-terminating parameter to it and it won't block the execution.