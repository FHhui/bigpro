package main.Algorithm;

import main.Operator.*;
import main.Solution.NSSSADoubleSolution;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solutionSet;
import main.problem.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MoSSA extends MultiAlgorithm {
    //多目标松鼠算法，
    //将松鼠算法写成多目标松鼠算法
    int generation = 100;//迭代次数
    int k = 4;//将网格分为几份
    int human = 50;//种群大小
    double delta = 0;//表示网格边距的大小
    public static int is_best = 4;//最优个数
    public static int is_sec_best = 10;//次优个数
    //最优个数与次优个数导致了种群全部成为了最优的现象。
    public HashMap<int[], ArrayList<NSSSADoubleSolution>> map;

    public NSSSADoubleSolutionSet run(Multiproblem p) {
        return getResult(p);
    }

    public MoSSA(int generation, int humans) {
        this.generation = generation;
        this.human = humans;
    }

    public NSSSADoubleSolutionSet calLocation(NSSSADoubleSolutionSet s) {
        //位置划分函数
        map = new HashMap<>();//初始化参数
        //冒泡排序.
        for (int m = 0; m < s.array.get(0).fitness.length; m++) {
            for (int i = 0; i < s.array.size(); i++) {
                for (int j = 0; j < s.array.size() - i - 1; j++) {
                    if (s.array.get(j).fitness[m] > s.array.get(j + 1).fitness[m]) {
                        NSSSADoubleSolution temp = s.array.get(j).copy(s.array.get(j), new ZDT2problem());
                        s.array.set(j, s.array.get(j + 1));
                        s.array.set(j + 1, temp);
                    }
                }
            }
            //单函数冒泡排序结束
            delta = (s.array.get(s.array.size() - 1).fitness[m]
                    - s.array.get(0).fitness[m]) / k;//k表示需要划分多少个表格在一个纬度上
            for (int i = 0; i < s.size(); i++) {
                s.array.get(i).location[m] = (int) Math.floor((s.array.get(i).fitness[m] - s.array.get(0).fitness[m]) / delta);
                if (s.array.get(i).location[m] == k)
                    s.array.get(i).location[m] = k - 1;
                //向下取整
            }
        }
        //到这一步每一个个体的location都已经更新完毕，然后统计每一个位置和
        map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int[] a = {i, j};
                map.put(a, new ArrayList<NSSSADoubleSolution>());
            }
        }//初始化map

        //向map里放入数
        for (int i = 0; i < s.array.size(); i++) {
            int[] h = s.array.get(i).location;
            Iterator it = map.keySet().iterator();
            if (it.hasNext()) {
                int[] temp = (int[]) it.next();
                if (temp.equals(h)) {

                    map.get(temp).add(s.array.get(i));
                    //map.replace(temp,map.get(temp).add(s.array.get(i)));
                }
            }
        }
        this.map = map;
        this.delta = delta;

        return s;
    }

    public NSSSADoubleSolutionSet getResult(Multiproblem p) {
        NSSSADoubleSolutionSet s = new NSSSADoubleSolutionSet(human);
        NSSSADoubleRandominit NSDR = new NSSSADoubleRandominit();//**更改问题
        s = NSDR.execute(s, p);//随机初始化操作
        for (int i = 0; i < this.generation; i++) {
            //迭代操作
            //快速非支配排序
            NSSSADoubleSolutionSet f = solutionSet.clone(s);
            //            for (int a = 0; a < human; a++) {
//                System.out.println("["+s.array.get(a).fitness[0]+","+s.array.get(a).fitness[1]+"],");
//
//            }
//            System.out.println("adjkajdkajdkajdkajdkajdkajdkjakdjakdjkajdka");
//            for (int a = 0; a < human; a++) {
//                System.out.println("["+f.array.get(a).fitness[0]+","+f.array.get(a).fitness[1]+"],");
//
//            }
            //System.out.println(s.array.size());
            NSGAFastNonSort NSFN = new NSGAFastNonSort();//快速非支配排序算子
            NSFN.execute(s);
            //System.out.println("快速非支配排序结束");
/***这一部分是替换部分
 * ***/
            CalLocation cl = new CalLocation();
            s = cl.execute(s, k);
            map = cl.getMap();
            delta = cl.getDelta();
            s = calfitness(s, map);

            //种群迁移
            /**
             * this part is able to change with the part which is mentioned
             * */
//            CalDistance cld = new CalDistance();
//            s = cld.execute(s);

            NSSSASort nss = new NSSSASort();
            s = nss.execute_evalfitness(s);
            NSFN.execute(s);
            for (int a = 0; a < human; a++) {
                s.array.set(a, (new ZDT2problem()).evalute(s.array.get(a)));
            }
            System.out.println("计算完成距离之后");
//            System.out.println(s.array.get(0).distance+"oooooooooooooooooooooooooooo");
//            System.out.println(s.array.get(1).distance+"oooooooooooooooooooooooooooo");
//            System.out.println(s.array.get(2).distance+"oooooooooooooooooooooooooooo");
//            System.out.println(s.array.get(3).distance+"oooooooooooooooooooooooooooo");
            for (int j = 0; j < s.size(); j++) {
                if (s.array.get(j).rank == 1) {
                    System.out.println("[" + s.array.get(j).fitness[0] + "," + s.array.get(j).fitness[1] + "],");
                }
            }

            SSANewDisplacePlace sndp = new SSANewDisplacePlace();
            sndp.execute(s, p);
            //  System.out.println("种群迁移结束");
            //季节条件
            NSFN.execute(s);
            for (int a = 0; a < human; a++) {
                s.array.set(a, (new ZDT2problem()).evalute(s.array.get(a)));
            }
            System.out.println("种群迁移部分完成之后");
            for (int j = 0; j < s.size(); j++) {
                if (s.array.get(j).rank == 1) {
                    System.out.println("[" + s.array.get(j).fitness[0] + "," + s.array.get(j).fitness[1] + "],");
                }
            }
            for (int a = 0; a < human; a++) {
                s.array.set(a, (new ZDT2problem()).evalute(s.array.get(a)));
            }

            SSASeasonChange ssc = new SSASeasonChange();
            s = ssc.execute(s, p, i, generation);
            NSFN.execute(s);
            System.out.println("季节改变部分完成之后");
            for (int a = 0; a < human; a++) {
                s.array.set(a, (new ZDT2problem()).evalute(s.array.get(a)));
            }
            for (int j = 0; j < s.size(); j++) {
                if (s.array.get(j).rank == 1) {
                    System.out.println("[" + s.array.get(j).fitness[0] + "," + s.array.get(j).fitness[1] + "],");
                }
            }
            //     System.out.println("季节改变结束");
            //根据季节等条件更新位置
            //动态拥挤度更新算子
            //子父代精英选择机制

            for (int a = 0; a < human; a++) {
                s.array.set(a, (new ZDT2problem()).evalute(s.array.get(a)));
            }



                NSGADoubleGeneration nsdg = new NSGADoubleGeneration();
                s = nsdg.execute(s, f,k);

            //System.out.println(s.array.size());

            System.out.println("所有操作完成之后");

            NSFN.execute(s);
            for (int j = 0; j < s.size(); j++) {
                if (s.array.get(j).rank == 1) {
                    System.out.println("[" + s.array.get(j).fitness[0] + "," + s.array.get(j).fitness[1] + "],");
                }
            }
        }

        return null;
    }

    public NSSSADoubleSolutionSet calfitness(NSSSADoubleSolutionSet s, HashMap<int[], ArrayList<NSSSADoubleSolution>> map) {
        //计算映射函数
        for (int i = 0; i < s.size(); i++) {
            for (int[] key : map.keySet()) {
                if (key[0] == s.array.get(i).location[0] && key[1] == s.array.get(i).location[1]) {
                    s.array.get(i).evafitness =
                            ((double) map.get(key).size() / (s.array.get(i).sp.size() + 1));
                }
            }
        }
        return s;
    }

    public static void main(String args[]) {
        MoSSA test = new MoSSA(10, 50);
        ZDT2problem z = new ZDT2problem();
        test.getResult(z);
    }
}