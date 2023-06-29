import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.util.*;

public class Runner {

    public static void main(String[] args) {

        plotCorrelationBetweenSizeOfBloomFilterAndFalsePositiveRate();
        plotCorrelationBetweenNumOfHashFunctionAndFalsePositiveRate();

    }

    private static void plotCorrelationBetweenNumOfHashFunctionAndFalsePositiveRate() {
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
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
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

            x.add((double) i);
            y.add(falsePositiveRate);
            System.out.println("No of hash functions: " + i + ", false positive rate: " + falsePositiveRate);
        }

        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Num of hash functions vs fpp plot",
                x.stream().mapToDouble(Double::doubleValue).toArray(),
                y.stream().mapToDouble(Double::doubleValue).toArray());

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("Num of hash functions vs fpp plot");
        frame.setContentPane(plot);
        frame.setVisible(true);
    }

    private static void plotCorrelationBetweenSizeOfBloomFilterAndFalsePositiveRate() {
        int noOfHashFunction = 3;
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
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        for(int i=100; i<10000; i+=100){
            BloomFilter bloomFilter = new BloomFilter(i, 3);
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
            x.add((double) i);
            y.add(falsePositiveRate);
            System.out.println("Size of bloom filter: " + i + ", false positive rate: " + falsePositiveRate);
        }

        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Size of bloom filter vs fpp plot",
                x.stream().mapToDouble(Double::doubleValue).toArray(),
                y.stream().mapToDouble(Double::doubleValue).toArray());

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("size of bloom filter vs fpp plot");
        frame.setContentPane(plot);
        frame.setVisible(true);
    }
}
