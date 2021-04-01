package main.Operator;

import java.io.*;
import java.util.ArrayList;

public class TestDecode {
    public static void main(String args[]) {
        String path = "D:\\newyork.txt";
        File file = new File(path);
        FileReader fr = null;
        ArrayList<String[]> strs = new ArrayList<>();

        ArrayList<ArrayList<ArrayList<Double>>> list_points=new ArrayList<>();
        try {
            fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {

                //System.out.println(str);
                String[] strings = str.split("\\s+");
                //System.out.println(strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + strings[4] + " " + strings[5] + " " + strings[7]);
                strs.add(strings);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file_write = new File("D:\\newyork_start_finish_.txt");
        FileWriter fw = null;
        //ArrayList<String[]> strs=new ArrayList<>();
        try {
            fw = new FileWriter(file_write);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < strs.size(); i++) {
                ArrayList<Integer> points = (ArrayList<Integer>) Decode(strs.get(i)[7]);
                ArrayList<Double> _points = new ArrayList<>();
                ArrayList<ArrayList<Double>> start_end_points = new ArrayList<>();
                //String[] str_strs = strs.get(i);

                //String write = str_strs[0] + "     " + str_strs[1] + "     " + str_strs[2] + "     " + str_strs[3] + "     " + str_strs[4] + "     " + str_strs[5] + "     ";
                for (int j = 0; j < points.size(); j++) {
                    if (j < 2) {

                    } else {
                        points.set(j, points.get(j) + points.get(j - 2));
                    }

                    double h = ((double) points.get(j)) / 100000;
                    if (j == 0 || j == 1 || j == points.size() - 2 || j == points.size() - 1){
                        if (j % 2 == 0) {
                            //write = write + String.valueOf(h) + " ";
                            _points.add(h);
                        } else {
                            _points.add(h);
                            //write = write + String.valueOf(h) + ",    ";
                            start_end_points.add(_points);
                        }
                        if (j==points.size()-1){
                            list_points.add(start_end_points);
                        }
                    }

                }
                //for (int j=0;j<)
                //System.out.println(write);
                //bw.write(write);
                //bw.newLine();
            }
            ArrayList<Double> difference=new ArrayList<>();
            ArrayList<Integer> line=new ArrayList<>();
            System.out.println(list_points.size());
            for (int i=0;i<list_points.size();i++){
                double difference_min=Double.MAX_VALUE;
                int line_id = 0;
                for (int j=0;j<list_points.size();j++){
                    if (i!=j){
                        double dif=Math.abs(list_points.get(i).get(0).get(0)-list_points.get(j).get(1).get(0))+
                                Math.abs(list_points.get(i).get(0).get(1)-list_points.get(j).get(1).get(1));
                        //System.out.println(dif);
                        if (dif<difference_min){
                            difference_min=dif;
                            line_id=j;
                        }
                    }
                }
                difference.add(difference_min);
                line.add(line_id);
            }
            for (int i = 0; i < strs.size(); i++) {
                String[] str_strs = strs.get(i);
                String write = str_strs[0] + "     " + str_strs[1] + "     " + str_strs[2] + "     " + str_strs[3] + "     " + str_strs[4] + "     " + str_strs[5] + "     ";
//                if (!str_strs[3].equals("-101")){
                write=write+"    "+difference.get(i)+"  "+line.get(i);
                System.out.println(write);
                bw.write(write);
                bw.newLine();
             //   }
            }
            for(int i=0;i<difference.size();i++){
                for (int j=0;j<difference.size()-i-1;j++){
                    if (difference.get(j)>difference.get(j+1)){
                        double temp=difference.get(j);
                        difference.set(j,difference.get(j+1));
                        difference.set(j+1,temp);

                    }
                }
            }
            System.out.println(difference.get(0));
            System.out.println(difference.get(93));
            System.out.println(difference.get(difference.size()-1));
            double sum=0;

            for (int i=0;i<difference.size();i++){
                sum+=difference.get(i);
            }
            double avg=sum/difference.size();
            System.out.println(avg);
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static java.util.List<java.lang.Integer> Decode(String encoded_polylines) {
        java.util.List<java.lang.Integer> trucks = new java.util.ArrayList<java.lang.Integer>();
        int truck = 0;
        int carriage_q = 0;
        for (int x = 0, xx = encoded_polylines.length(); x < xx; ++x) {
            int i = encoded_polylines.charAt(x);
            i -= 63;
            int _5_bits = i << (32 - 5) >>> (32 - 5);
            truck |= _5_bits << carriage_q;
            carriage_q += 5;
            boolean is_last = (i & (1 << 5)) == 0;
            if (is_last) {
                boolean is_negative = (truck & 1) == 1;
                truck >>>= 1;
                if (is_negative) {
                    truck = ~truck;
                }
                trucks.add(truck);
                carriage_q = 0;
                truck = 0;
            }
        }
        return trucks;
    }
}
