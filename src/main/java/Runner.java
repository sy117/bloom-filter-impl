import java.util.*;

public class Runner {

    public static void main(String[] args) {
        int size = 10000;

        List<String> dataSet = new ArrayList<>();
        Map<String, Boolean> addToFilter = new HashMap<>();

        for(int i=0; i<2000; i++){
            String uuid = UUID.randomUUID().toString();
            dataSet.add(uuid);
            addToFilter.put(uuid, true);
        }

        for(int i=0; i<2000; i++){
            String uuid = UUID.randomUUID().toString();
            dataSet.add(uuid);
        }

        /*
            with increasing no of hash functions, false positive rate decreases up to a certain point.
            But after that point the rate becomes constant
        */
        for(int i=1; i<100; i++){
            BloomFilter bloomFilter = new BloomFilter(size, i);
            addToFilter.forEach((key, value) -> {
                bloomFilter.add(key);
            });
            int falsePositive = 0;
            for(String data: dataSet){
                if(bloomFilter.exists(data) && !addToFilter.containsKey(data)){
                    falsePositive++;
                }
            }
            double falsePositiveRate = (falsePositive * 1.0) / dataSet.size();
            System.out.println("No of hash functions: " + i + ", false positive rate: " + falsePositiveRate);
        }

    }
}
