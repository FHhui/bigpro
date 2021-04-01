package main.Operator;

import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.solutionSet;

import java.io.*;
import java.util.ArrayList;

public class evalute extends operator{

    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public double execute(MaShOADoubleSolutionSet s){
        double gd=0;
        File file = new File("D:\\DTLZ1.txt");//定义一个file对象，用来初始化FileReader

        FileReader reader = null;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        ArrayList<double[]> sc=new ArrayList<>();
        String h = "";

        while (true) {
            try {
                if (!((h =bReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }//逐行读取文件内容，不读取换行符和末尾的空格
            String[] ss=h.split(" ");
            double[] n=new double[ss.length];
            for (int m=0;m<ss.length;m++){
                n[m]=Double.parseDouble(ss[m]);
            }
            sc.add(n);
        }
        double[] dis=new double[s.size()];
        for (int i=0;i<s.size;i++){
            dis[i]=Double.MAX_VALUE;
            for (int j=0;j<sc.size();j++){
                double pdis=0;
                for (int m=0;m<sc.get(j).length;m++){
                    pdis+=((s.array.get(i).fitness[m]-sc.get(j)[m])*(s.array.get(i).fitness[m]-sc.get(j)[m]));//距离之和
                }
                pdis=Math.sqrt(pdis);
                if (dis[i]>pdis){
                    dis[i]=pdis;
                }
            }
        }
        for (int i=0;i<s.size();i++){
            gd+=dis[i];
        }
        gd=gd/s.size();
        //System.out.println("这个算法的GD指标为"+gd);
        return gd;
    }
}
