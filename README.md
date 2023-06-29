# bloom-filter-impl
Java implementation of Bloom Filter

## Bloom Filter:
- Bloom filter is a probabilistic data structure which is used to find association of a member in a set.
- If Bloom filter return FALSE for association of a member in a set, i.e, member is not part of that set. Means true positive rate of bloom filter is 1.
- If Bloom filter return TRUE for association of a member in a set, i.e, member might be or might not be part of that set. Means false positive rate of bloom filter is greater than 0.
- The false positive rate depends on multiple factors like type of hash function, number of hash function, no of insertions etc..
- Guava library provides implementation of Bloom Filter in Java. Check [this](https://www.baeldung.com/guava-bloom-filter#:~:text=A%20Bloom%20filter%20is%20a,is%20not%20in%20the%20set.)

### Optimal number of hash functions(n):
<img width="675" alt="image" src="https://github.com/sy117/bloom-filter-impl/assets/26311536/2ba17e96-b4cc-4b1c-967e-23fdbd93bf0f">


### Optimal number of bits required(m):
<img width="657" alt="image" src="https://github.com/sy117/bloom-filter-impl/assets/26311536/66807470-06aa-4fa8-ab8c-4d5011a679df">

### Correlation between Size of Bloom Filter and False Positive Rate:
<img width="622" alt="image" src="https://github.com/sy117/bloom-filter-impl/assets/26311536/09a394af-87f8-496f-a27b-0a5118e2a4ff">

### Correlation between Num of Hash Functions and False Positive Rate:
<img width="568" alt="image" src="https://github.com/sy117/bloom-filter-impl/assets/26311536/401bab13-f784-47bc-b3ce-58bf1ab1e57a">

  

