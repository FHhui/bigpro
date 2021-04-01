package main.Algorithm;

import main.Operator.*;
import main.Solution.*;
import main.problem.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Multi_SSA_Tsp {
    //会很慢
    //多目标松鼠算法用于解决tsp问题
    int generation;//迭代次数
    int humans;//种群个体数
    int citynum;
    int k = 4;//将网格分为几份
    double delta = 0;
    public HashMap<int[], ArrayList> map;
    public static final int is_best = 4;
    public static final int is_sec_best = 16;

    public Multi_SSA_Tsp(int generation, int humans, int citynum) {
        this.generation = generation;
        this.humans = humans;
        this.citynum = citynum;
    }

    public SSAMultiTspSolutionSet run(Multiproblem p) {
        return getResult(p);
    }

    public SSAMultiTspSolutionSet getResult(Multiproblem p) {
        Multi_Tsp p1 = (Multi_Tsp) p;
        double best = 0;
        SSAMultiTspSolutionSet child = new SSAMultiTspSolutionSet(humans);
        SSATspRandominit SDR = new SSATspRandominit();//初始化操作
        child = SDR.execute(child, p1, humans, citynum);
        for (int i = 0; i < generation; i++) {
            //快速非支配排序

            SSAMultiTspSolutionSet father = null;
            try {
                father = (SSAMultiTspSolutionSet) child.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            NSGAFastNonSort NSFS = new NSGAFastNonSort();
            NSFS.execute(child);

            CalLocation cl = new CalLocation();
            cl.execute(child, k);
            map = cl.getMap();
            delta = cl.getDelta();
            calfitness(child, map);

            NSSSASort nss = new NSSSASort();
            nss.execute(child);

            //随机选择，生成新位置算子·
            SSA_Tsp_NewDisplace SND = new SSA_Tsp_NewDisplace();
            SND.execute(child, p, citynum);

            for (int a = 0; a < child.array.size(); a++) {
                for (int j = 0; j < child.array.size() - i - 1; j++) {
                    if (child.array.get(j).fitness[0] > child.array.get(j + 1).fitness[0]) {
                        SSAMultiTspSolution temp = child.array.get(j).copy(child.array.get(j));
                        child.array.set(j, child.array.get(j + 1));
                        child.array.set(j + 1, temp);
                    }
                }
            }

            best = child.array.get(0).fitness[0];

            //季节变化条件算子
            SSASeasonChange SSC = new SSASeasonChange();
            SSC.execute(child, p, i, generation, best, citynum);

            //重新排列并计算
            for (int m = 0; m < child.array.size(); m++) {
                child.array.set(m, p1.evalute(child.array.get(m)));
            }

            NSGADoubleGeneration nsdg = new NSGADoubleGeneration();
            child = nsdg.execute(child, father, p1);

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

            for (int m = 0; m < child.array.size(); m++) {
                if (child.array.get(m).rank == 1) {
                    System.out.println("" + child.array.get(m).fitness[0] + " " + child.array.get(m).fitness[1] + "");
                }
            }
            //输出第几代
            if (i == 999) {
                File file = new File("D:\\dd.txt");//定义一个file对象，用来初始化FileReader
                FileWriter writer = null;//定义一个fileReader对象，用来初始化BufferedReader
                try {
                    writer = new FileWriter(file);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                int m = 0;
                BufferedWriter bwriter = new BufferedWriter(writer);//new一个BufferedReader对象，将文件内容读取到缓存
                while (m < child.array.size()) {//逐行读取文件内容，不读取换行符和末尾的空格
                    //String[] ss=s.split(" ");
                    try {
                        if (child.array.get(m).rank == 1) {
                            bwriter.write(child.array.get(m).fitness[0] + " " + child.array.get(m).fitness[1]);
                            bwriter.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    m++;
                }
                try {
                    bwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
        }

        return child;
    }

    public SSAMultiTspSolutionSet calfitness(SSAMultiTspSolutionSet s, HashMap<int[], ArrayList> map) {
        //计算映射函数
        for (int i = 0; i < s.array.size(); i++) {
            for (int[] key : map.keySet()) {
                if (key[0] == s.array.get(i).location[0] && key[1] == s.array.get(i).location[1]) {
                    //System.out.println((double) map.get(key).size());
                    s.array.get(i).evafitness =
                            ((double) map.get(key).size() / (s.array.get(i).sp.size() + 1));
                }
            }
        }
        return s;
    }

    public static void main(String args[]) {
        Multi_SSA_Tsp t = new Multi_SSA_Tsp(10000, 100, 150);

        t.getResult(new Multi_Tsp(150, "kroA150.tsp","kroB150.tsp"));
    }
}
