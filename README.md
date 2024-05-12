# BloomFilter
A Bloom filter is a space-efficient probabilistic data structure used to test whether an element is a member of a set. It's particularly useful in situations where the dataset is large and memory usage is a concern. Instead of storing the actual elements of the set, a Bloom filter uses an array of bits (often called the "Bloom filter array" or "Bloom array") along with several hash functions.

- Initialization: The Bloom filter is initialized with an array of m bits, all set to 0.

- Adding Elements: When adding an element to the Bloom filter, it's hashed by multiple hash functions, each producing an index within the array. The bits at these indices are then set to 1.

- Membership Test: To check whether an element is in the set, it's hashed again using the same hash functions. If all the bits at the corresponding indices are set to 1, the element is probably in the set. If any of the bits are 0, then the element is definitely not in the set. However, false positives are possible due to hash collisions.

It has multiple applications, such as comparing datasets or counting distinct elements in each.
The "counting" program is used for calculating number of distinct elements in texts.
The "comparing" program is used for comparing two texts and writing down a set of words that are in first text that are not in another.



## How to use
First scala compiler must be installed, one must follow intsruction from following site: 
https://docs.scala-lang.org/getting-started/index.html
Then Program must be compiled
```
scalac BloomFilter.scala
```
### Counting program
To run a program on some text file following commands are needed:
```
scala Counting <filename>
```
### texts Difference
To obtain list of words that are in first text and are not in second:
```
scala Difference <filename> <filename>
```

