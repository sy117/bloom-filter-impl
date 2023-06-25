import org.apache.commons.codec.digest.MurmurHash3;

import java.util.Random;

public class HashUtil {
    private static final int DEFAULT_SIZE = 10000;
    private static final int[] seeds;

    static {
        Random random = new Random();
        seeds = new int[DEFAULT_SIZE];
        for(int i=0; i<DEFAULT_SIZE; i++){
            seeds[i] = random.nextInt();
        }
    }

    public static int[] getHashes(String data, int noOfHashFunctions){
        int[] hashes = new int[noOfHashFunctions];
        for(int i=0; i<noOfHashFunctions; i++){
            hashes[i] = MurmurHash3.hash32x86(data.getBytes(), 0, data.length(), seeds[i]);
        }
        return hashes;
    }
}
