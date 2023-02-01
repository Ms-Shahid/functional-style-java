
## Lambda Expression 

### Functional Interface 
**An interface which have only one abstract method & can have default & static methods**
**And the functional interface is annotated with @Functional Interface**

> Any lambda expression contains : () -> & the Implementation

java.util package contains more than 40 functional Interface
```
-Supplier   -> It will not take any argument & returns a object of given type
-Consumer   -> Takes input & consumes it & returns null
-Predicate  -> Takes input & do some test on input & return a boolean (flitering)
-Function   -> Takes input of one type & returns the other object of another type.(mapping)
-BiConsumer -> Takes 2 inputs & consume it & returns null
-BiPredicate -> Takes 2 inputs & perform the test on them & returns a boolean result
-BiFunction -> Takes inputs of different types & produces result of other type
```
### Commonly used methods
```
-filter(predicate) -> used to filter the stream through given predicate
-map(function)     -> used to map the value & produces new value such as power of a number
-reduce            -> used to reduce the result to a single value
```
### Performace of Lambda expression vs anonymous classes
***Lambda are not instances of anonymous classes, the compiled code is different***
Lambda uses compiled code called invoke-dynamics
**Lambda are > 60 times faster than anonymous classes**

### Auto boxing Effect
Convert from primitive type to wrapper class such as int to Integer
Due to which, the compiler takes long time to do autoboxing in lambdas, reduce the performance
therefore, we can use the set of interfaces to avoid this.
```
-IntPredicate -> produces int primitive types
-LongSupplier -> produces long primitive types
-IntFunction<T> -> takes int as primitive type & returns object type of T 
-LongToIntFunction -> takes long as primitive type & return int primitive type
```

### Intermediate & Stream Operations
```
methods such as fliter, map, distinct, sorted are called as intermediate operations, 
since they return stream.
methods such as forEach, collect, reduce, are called terminal operations,
since they return some object.
```

### Behaviour Parameterization
```
In Behaviour parameterization, we can extract the stream into seperate method & use it wisely by sending different
logics into the main function.
```

### complex methods & interface in stream operation
```
-IntStream/LongStream(I) - Used to perform big Number operations
-flatMap(M) - used to convert a stream of strings into invididual characters
```