## Functional Program Design

- JSON implementation using Case classes
- Partial Functions
- Case Study: Water Pouring Problem

1. Points to remember
    1. Partial function pattern matching only guarantees MatchError for the outer-most block and nested patterns are not
       matched
    2. LazyList (deprecated Stream) is used to generate infinite sequences. The tail of the sequence is not calculated
       until asked for.
    3. The Cons operator for LazyList is `#::` which is `::` for List.
    4. Type-directed programming, a language mechanism that infers values from types.
        1. Implicit values are searched in the enclosing lexical scope (imports, parameters, inherited members) as well
           as in the implicit scope of the queried type.
        2. If there is no available implicit definition matching the queried type, an error is reported:
        3. On the other hand, if more than one implicit definition are eligible, an ambiguity is reported:
        4. Actually, several implicit definitions matching the same type don’t generate an ambiguity if one is more
           specific than the other.
    5. Type classes provide yet another form of polymorphism. The method sort can be called with lists containing
       elements of any type A for which there is an implicit value of type Ordering[A]. At compile-time, the compiler
       resolves the specific Ordering implementation that matches the type of the list elements.
    6. Instances of the Ordering[A] type class must satisfy the following properties:
        1. inverse: the sign of the result of comparing x and y must be the inverse of the sign of the result of
           comparing y and x,
        2. transitive: if a value x is lower than y and that y is lower than z, then x must also be lower than z,
        3. consistent: if two values x and y are equal, then the sign of the result of comparing x and z should be the
           same as the sign of the result of comparing y and z.
    7. The Numeric trait has implementations for a new type to conform with the ring algebraic structure when extended. 
    8. The while loop can be implemented using a tail recursive if-else construct.