## Functional Programming Principles in Scala

- Hello World.
- Fibonacci
- Evaluation Strategies
- Conditionals
- Square Root using Newton's method
- Factorial
- Tail Recursion
- Pascal Triangle
- Balance Parentheses
- Count Change
- Huffman Coding
- Anagrams
- Mnemonics
- Higher order functions
- Abstraction of functions
- Binary Search Tree
- Class Hierarchy: Traits, Objects & Classes
- Pattern Matching
- Case classes
- List Utility methods
- Vector and List memory representation
- For expression with yield statement
- Functions with multiple argument set

## Scala Features

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
    5. `require(expression, message)` is used as a precondition statement.
    6. Scala object's default constructor is the class definition itself.
    7. Constructor can be overloaded by using the `this(...)` method.
    8. If a method name as operator name, prefix operators can be defined by prepending `unary_` to the name.
    9. There can be methods with multiple function calls, they may be written without
       parentheses `def union(other: IntSet): IntSet = left union right union other incl elem`

4. Points to remember
    1. If you find yourself wanting to use casts, take a step back and think about what you're trying to achieve.
    2. Avoid mutable local variables and replace it with helper functions that take accumulators, simply don't allow
       variables to change value inside the method. If you must change values, refactor it to a separate method.
    3. The definition between val and def becomes apparent when the right-hand side does not terminate.
    4. List is implemented as a LinkedList with a head element and a reference to the tail of the list.
    5. Pattern matching on list has to have a base case representing empty list and rest with a head and a tail.

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
    8. If a function calls itself as its last action, the function's stack frame can be reused. Usually helps to avoid
       deep recursive chains.

6. Conditionals
    1. Conditional operators can be evaluated using equivalent functions composed of if-else constructs.
    2. Some arguments may not be required to get evaluated like the y parameter in the `Conditionals.and` implementation
       and if that's defined using `: =>`, we can pass non-terminating parameter to it and it won't block the execution.

7. Issues
    1. How to handle the project, target folders in gitignore?
    2. How to ensure that different versions of scala are not being made available to the project?
    3. Is the folder containing build.sbt must be opened in IntelliJ? How to handle sub folders as module without root
       being one?

8. Operator Precedence
    1. (all letters)
    2. `|`
    3. `^`
    4. `&`
    5. `<` `>`
    6. `=` `!`
    7. `:`
    8. `+` `-`
    9. `*` `/` `%`
    10. (all other special characters)
    11. `a + b ^? c ?^ d less a ==> b | c  ` to `((a + b) ^?( c ?^ d)) less ((a ==> b) | c)`
    
10. Critical Exercises
    1. [Operator Methods](src/main/scala/practice/Functions.scala)
    2. [Operator Functions](src/main/scala/practice/HigherOrderFunctions.scala)
    3. [Multiple Subclasses](src/main/scala/practice/Expression.scala)
    4. [List Case Classes](src/main/scala/exercise/objsets/TweetSet.scala)
    5. [Function as arguments](src/main/scala/exercise/patmat/Huffman.scala)
    
11. Require further research
    1. FoldLeft & FoldRight
    2. ReduceLeft & ReduceRight