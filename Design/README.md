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