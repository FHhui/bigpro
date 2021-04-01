package main.Algorithm;

import main.Operator.*;
import main.Solution.SSAMultiTspSolution;
import main.Solution.SSAMultiTspSolutionSet;
import main.problem.Multi_Tsp;
import main.problem.Multiproblem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DMoSSA_TSP {
    /**
     * @author Fhhui
     * @description 适用于Tsp的动态多目标松鼠算法
     * @return parato sets
     */
    int generation;//迭代次数
    int humans;//种群个体数
    int citynum;
    int k = 4;//将网格分为几份
    double delta = 0;
    public HashMap<int[], ArrayList> map;
    public static final int is_best = 4;
    public static final int is_sec_best = 16;

    public DMoSSA_TSP(int generation, int humans, int citynum) {
        this.generation = generation;
        this.humans = humans;
        this.citynum = citynum;
    }


    /**
     * @param path
     * @return getResult()
     */
    public SSAMultiTspSolutionSet run(String path) {
        File root = new File(path);
        File[] files = root.listFiles();
        String[] Filename = root.list();
        if (Filename == null) {
        } else {
            for (int i = 0; i < Filename.length; i++) {
                String p1 = files[i].list()[0];
                String p2 = files[i].list()[1];
                SSAMultiTspSolutionSet child = getResult(new Multi_Tsp(100, p1, p2));
                p1 = p1.substring(0, 7);
                p2 = p2.substring(0, 7);
                String pathname = "D:\\answer_parato\\" + p1 + p2 + ".txt";
                String pathname_2 = "D:\\answer_parato_link\\" + p1 + p2 + ".txt";
                System.out.println(pathname);
                System.out.println(pathname_2);
                File file = new File(pathname);//定义一个file对象，用来初始化FileReader
                File file_2 = new File(pathname_2);
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

                FileWriter writer_2 = null;//定义一个fileReader对象，用来初始化BufferedReader

                try {
                    writer_2 = new FileWriter(file_2);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                int h = 0;
                BufferedWriter bwriter_2 = new BufferedWriter(writer_2);//new一个BufferedReader对象，将文件内容读取到缓存

                while (h < child.array.size()) {//逐行读取文件内容，不读取换行符和末尾的空格
                    //String[] ss=s.split(" ");
                    try {
                        if (child.array.get(h).rank == 1) {
                            bwriter_2.write(child.array.get(h).city_cycle.toString());
                            bwriter_2.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    h++;
                }
                try {
                    bwriter_2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        return null;

    }

    /**
     * @param p
     * @return SSAMultiTspSolutionSet
     */
    private SSAMultiTspSolutionSet getResult(Multiproblem p) {
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
            System.out.println(i);
        }

        return child;
    }

    private SSAMultiTspSolutionSet calfitness(SSAMultiTspSolutionSet s, HashMap<int[], ArrayList> map) {
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
        DMoSSA_TSP t = new DMoSSA_TSP(1000, 100, 100);
        String path = "D:\\problem_parato";
        t.run(path);
    }
}