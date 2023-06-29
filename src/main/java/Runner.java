import org.math.plot.Plot2DPanel;

import javax.swing.*;
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
        double[] x = new double[100];
        double[] y = new double[100];
        for(int i=1; i<101; i++){
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
            x[i-1] = i * 1.0;
            y[i-1] = falsePositiveRate;
            System.out.println("No of hash functions: " + i + ", false positive rate: " + falsePositiveRate);
        }

        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Num of hash functions vs fpp plot", x, y);

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("Num of hash functions vs fpp plot");
        frame.setContentPane(plot);
        frame.setVisible(true);
    }
}
