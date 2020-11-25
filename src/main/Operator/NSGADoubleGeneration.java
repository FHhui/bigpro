package main.Operator;

import main.Algorithm.MoSSA;
import main.Solution.*;
import main.problem.Multiproblem;
import main.problem.ZDT1problem;
import main.problem.ZDT2problem;
import main.problem.ZDT6problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class NSGADoubleGeneration extends Selection {
    //author:FHhui
    //2020.1.31(nsga最后一个算子留念)
    //2020.2.21nsssa最后一个算子
    //nsga算法的迭代选择算子
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, NSSSADoubleSolutionSet s1, int k) {
        NSSSADoubleSolutionSet news = new NSSSADoubleSolutionSet(s.size());
        NSSSADoubleSolutionSet totalS = new NSSSADoubleSolutionSet(2 * s.size());
        HashMap<int[], ArrayList<NSSSADoubleSolution>> map = new HashMap<>();
        ZDT2problem p = new ZDT2problem();
        for (int i = 0; i < 2 * s.size(); i++) {
            if (i < s.size()) {
                s.array.set(i, p.evalute(s.array.get(i)));
                totalS.add(s.array.get(i));
            } else {
                int n = i - s.size();
                s1.array.set(n, p.evalute(s1.array.get(n)));
                totalS.add(s1.array.get(n));
            }
        }
//        System.out.println("合并子父代之后");
////        for (int i=0;i<totalS.array.size();i++){
////            System.out.println("[" + totalS.array.get(i).fitness[0] + "," + totalS.array.get(i).fitness[1]+ "],");
////        }
//        //将两个子代与父代两代的种群个体给放到了一个种群当中，接下来，要进行网格值的计算与动态拥挤距离的计算
//        CalLocation cl = new CalLocation();
//        totalS = cl.execute(totalS, k);
//        map = cl.getMap();
////        System.out.println("计算过位置之后");
////        for (int i=0;i<totalS.array.size();i++){
////            System.out.println("[" + totalS.array.get(i).fitness[0] + "," + totalS.array.get(i).fitness[1]+ "],");
////        }
//        //System.out.println(map.get(map.keySet().iterator().next()));
////        for (int j = 0; j < s.size(); j++) {
////            if (totalS.array.get(j).rank == 1) {
////                System.out.println("{" + totalS.array.get(j).fitness[0] + "," + totalS.array.get(j).fitness[1] + "},");
////            }
////        }
//        double delta = cl.getDelta();
//        //更新完毕map与delta
//        for (int i = 0; i < totalS.size(); i++) {
//            //vg表示方格内理想的个体数
//            double vg = ((double) totalS.array.size()) / (k * k);//因为是二维的所以方格的个数应该是k方个
//            Iterator it = map.keySet().iterator();
//            while (it.hasNext()) {
//                int[] temp = (int[]) it.next();
//                if (temp[0] == totalS.array.get(i).location[0] && temp[1] == totalS.array.get(i).location[1])
//                    if (map.get(temp).size() <= vg) {
//                        totalS.array.get(i).di = 0;
//                    } else {
//                        totalS.array.get(i).di = 1 - (vg /
//                                map.get(temp).size()
//                        );
//                    }
//            }
//        }
//        //计算完了理想密度
//        double sum_neighbor_distance = 0.0;
//        for (int i = 0; i < totalS.array.size(); i++) {
//            //计算最近欧几里得距离
//            double distance = Double.MAX_VALUE;
//            for (int j = 0; j < totalS.array.size(); j++) {
//
//                if (j != i) {
//                    double dis = Math.sqrt(
//                            (totalS.array.get(i).fitness[0] - totalS.array.get(j).fitness[0]) *
//                                    (totalS.array.get(i).fitness[0] - totalS.array.get(j).fitness[0]) +
//                                    (totalS.array.get(i).fitness[1] - totalS.array.get(j).fitness[1]) *
//                                            (totalS.array.get(i).fitness[1] - totalS.array.get(j).fitness[1])
//                    );
//
//
//                    if (dis < distance && dis!=0 ) {
//                        distance = dis;
//                }
//                }
//            }
//            totalS.array.get(i).neighbor_distance = distance;
////            System.out.println(distance);
//        }
//        Iterator it = map.keySet().iterator();
//        HashMap<int[], Double> int_map = new HashMap<>();
//
//        while (it.hasNext()) {
//            int[] temp = (int[]) it.next();
//            double sum = 0;
//            int num = 0;
//            for (int i = 0; i < totalS.array.size(); i++) {
//                if (totalS.array.get(i).location[0] == temp[0] && totalS.array.get(i).location[1] == temp[1]) {
//                    sum += totalS.array.get(i).neighbor_distance;
//                    num++;
//                }
//            }
//            double avg = 0;
//            avg = sum / num;
//            int_map.put(temp, avg);
//        }
//        HashMap<int[], Double> s_map = new HashMap<>();
//        Iterator it2 = map.keySet().iterator();
//        while (it2.hasNext()) {
//            int[] temp = (int[]) it2.next();
//            double sum = 0;
//            int num = 0;
//            for (int i = 0; i < totalS.array.size(); i++) {
//                if (totalS.array.get(i).location[0] == temp[0] && totalS.array.get(i).location[1] == temp[1]) {
//
//                    sum += (totalS.array.get(i).neighbor_distance - int_map.get(temp) )* (totalS.array.get(i).neighbor_distance - int_map.get(temp));
//
//                    num++;
//                }
//            }
//            double avg = 0;
//            if (num > 1) {
//                avg = sum / num;
////                System.out.println(sum);
////                System.out.println(num);
//            } else {
//                avg = Double.MAX_VALUE;
//            }
//            s_map.put(temp, avg);
//        }
//        //double avg_neighbor_distance=sum_neighbor_distance/totalS.array.size();
//        for (int i = 0; i < totalS.array.size(); i++) {
//            Iterator it_s_map = s_map.keySet().iterator();
//            while (it_s_map.hasNext()) {
//
//                int[] temp = (int[]) it_s_map.next();
//                if (temp[0] == totalS.array.get(i).location[0] && temp[1] == totalS.array.get(i).location[1])
//                    if (s_map.get(temp) == Double.MAX_VALUE) {
//
//                        totalS.array.get(i).distance = 0;
//                    } else {
//                        totalS.array.get(i).distance = delta / Math.log(1 / (s_map.get(temp) * s_map.get(temp)));
//                        //System.out.println(totalS.array.get(i).distance);
//                    }
//
//            }
//        }
        //for (int i=0)
        //通过以上的步骤我们就得到了动态拥挤距离
        //在得到动态拥挤距离之后，就有两种选择
//        for (int i = 0; i < totalS.array.size(); i++) {
//            for (int j = 0; j < totalS.array.size() - 1 - i; j++) {
//                //冒泡排序
//                if (totalS.array.get(j).distance > totalS.array.get(j + 1).distance) {
//
//                    NSSSADoubleSolution a = totalS.array.get(j).copy(totalS.array.get(j), p);
//                    NSSSADoubleSolution b = totalS.array.get(j).copy(totalS.array.get(j + 1), p);
//
//                    totalS.array.set(j, b);
//                    totalS.array.set(j + 1, a);
//                }
//            }
//        }
        NSGAFastNonSort NSFN=new NSGAFastNonSort();
        totalS=NSFN.execute(totalS);

        CalDistance cd=new CalDistance();
        totalS=cd.execute(totalS);

        NSSSASort nss = new NSSSASort();
        totalS = nss.execute_gird(totalS);

        for (int i=0;i<s.array.size();i++){
            //System.out.println(totalS.array.get(i).distance);
            news.add(totalS.array.get(i));
        }
        return news;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, NSSSADoubleSolutionSet s1) {
        NSSSADoubleSolutionSet news = new NSSSADoubleSolutionSet(s.size());
        NSSSADoubleSolutionSet totalS = new NSSSADoubleSolutionSet(2 * s.size());
        for (int i = 0; i < 2 * s.size(); i++) {
            if (i < s.size()) {
                totalS.add(s.array.get(i));
            } else {
                int n = i - s.size();
                totalS.add(s1.array.get(n));
            }
        }
        for (int a = 0; a < totalS.array.size(); a++) {
            totalS.array.set(a, (new ZDT2problem()).evalute(totalS.array.get(a)));
        }
        NSGAFastNonSort Nfns = new NSGAFastNonSort();
        totalS = Nfns.execute(totalS);
        CalDistance Nscd = new CalDistance();
        totalS = Nscd.execute(totalS);//懒得改了，直接把位置计算方法写在了主类里，所以出现这种情况千万别慌
        NSSSASort NS = new NSSSASort();
        totalS = NS.execute(totalS);//重新进行排序,这里的map发生了改变应该有一个重新计算的过程

        for (int i = 0; i < s.size(); i++) {
            news.add(totalS.array.get(i));
        }

        return news;
    }

    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s, NSGADoubleSolutionSet s1, Multiproblem p) {
        NSGADoubleSolutionSet news = new NSGADoubleSolutionSet(s.size());
        NSGADoubleSolutionSet totalS = new NSGADoubleSolutionSet(2 * s.size());
        for (int i = 0; i < 2 * s.size(); i++) {
            if (i < s.size()) {
                totalS.add(s.array.get(i));
            } else {
                int n = i - s.size();
                totalS.add(s1.array.get(n));
            }
        }
        ZDT1problem p1 = (ZDT1problem) p;
        for (int i = 0; i < totalS.size(); i++) {
            totalS.array.set(i, p1.evalute(totalS.array.get(i)));
            System.out.println("[" + totalS.array.get(i).fitness[0] + "," + totalS.array.get(i).fitness[1] + "],");
        }
        //System.out.println("xxxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhh");
        NSGAFastNonSort Nfns = new NSGAFastNonSort();
        totalS = Nfns.execute(totalS);
        CalDistance Nscd = new CalDistance();
        totalS = Nscd.execute(totalS);
        NSSSASort NS = new NSSSASort();
        totalS = NS.execute(totalS);//重新进行排序
        for (int i = 0; i < s.size(); i++) {
            news.add(totalS.array.get(i));
        }
        return news;
    }

    public NSSSADoubleSolutionSet lunpan(NSSSADoubleSolutionSet s) {
        int h=s.array.size()/2;
        while (true) {
            double sum = 0;

            for (int i = 0; i < s.array.size(); i++) {
                sum += s.array.get(i).distance;
            }

            double a = Math.random();
            for (int i = 0; i < s.size; i++) {
                if ((a - s.array.get(i).distance / sum) > 0) {
                    a = a - s.array.get(i).distance / sum;
                } else {
                    s.array.remove(i);
                    break;
                }
            }
            if (s.array.size()==h){
                //System.out.println(s.array.size());
                break;
            }
        }
        return s;

    }
}
