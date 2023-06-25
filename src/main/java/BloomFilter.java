import java.util.Random;

public class BloomFilter {
    private final int size;
    private final int noOfHashFunctions;
    private final byte[] filter;

    public BloomFilter(int size, int noOfHashFunctions){
        this.size = size;
        this.noOfHashFunctions = noOfHashFunctions;
        this.filter = new byte[size];
    }

    public void add(String key){
        int[] hashes = HashUtil.getHashes(key, noOfHashFunctions);
        for(int hash: hashes){
            int idx = hash % size;
            if(idx < 0) idx += size;
            int bucket = idx/8;
            int bit = idx % 8;
            filter[bucket] |= (1<<bit);
        }
    }

    public boolean exists(String key){
        int[] hashes = HashUtil.getHashes(key, noOfHashFunctions);
        for(int hash: hashes){
            int idx = hash % size;
            if(idx < 0) idx += size;
            int bucket = idx/8;
            int bit = idx % 8;
            if((filter[bucket] & (1<<bit)) == 0){
                return false;
            }
        }
        return true;
    }
}
