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

4. Points to remember
    1. If you find yourself wanting to use casts, take a step back and think about what you're trying to achieve.
    2. Avoid mutable local variables and replace it with helper functions that take accumulators, simply don't allow
       variables to change value inside the method. If you must change values, refactor it to a separate method.