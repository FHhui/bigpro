package main.problem;

import main.Solution.SSATspSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class TSP extends Singleproblem{
    public int cityNum;//城市数目
    //public int scale;//规模


    public TSP(int cityNum,String filename) {
        this.cityNum = cityNum;
        try {
            init(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] distance;//距离矩阵

    public void init(String filename) throws IOException {
        //从文件中读取tsp路径问题
        double[] x;
        double[] y;
        String strbuff;
        BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        distance = new double[cityNum][cityNum];
        x = new double[cityNum];
        y = new double[cityNum];
        for (int i = 0; i < cityNum; i++) {
            // 读取一行数据，数据格式1 6734 1453
            strbuff = data.readLine();
            // 字符分割
            String[] strcol = strbuff.split(" ");
            x[i] = Integer.valueOf(strcol[1]);// x坐标
            y[i] = Integer.valueOf(strcol[2]);// y坐标
        }
        // 计算距离矩阵
        // ，针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628

        for (int i = 0; i < cityNum - 1; i++) {
            distance[i][i] = 0; // 对角线为0
            for (int j = i + 1; j < cityNum; j++) {
                double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j])
                        * (y[i] - y[j])) / 10.0);
                // 四舍五入，取整
                distance[i][j]=rij;
                distance[j][i]=rij;
            }
        }
        distance[cityNum - 1][cityNum - 1] = 0;//最后一个元素
    }

    public SSATspSolution evalute(SSATspSolution s){
        double len=0;
        for (int i=1;i<cityNum;i++){
            len+=distance[s.city_cycle.get(i-1)][s.city_cycle.get(i)];
        }
        len+=distance[s.city_cycle.get(cityNum-1)][s.city_cycle.get(0)];
        s.fitness=len;
        return s;
    }


}
