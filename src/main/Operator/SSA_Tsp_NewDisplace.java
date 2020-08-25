package main.Operator;

import main.Algorithm.SSA;
import main.Solution.SSADoubleSolutionSet;
import main.Solution.SSATspSolutionSet;
import main.problem.Singleproblem;

import java.util.Random;

public class SSA_Tsp_NewDisplace {
    //tsp松鼠的位置交换算子
    double gc=1.9;
    Random random=new Random();
    public SSATspSolutionSet execute(SSATspSolutionSet s, Singleproblem p,int cityNum){
        //具体的操作方法
        for (int i = SSA.is_best; i<s.array.size(); i++){
            double dg=0.5+Math.random()*0.61;
            double r=Math.random();//是否遇到捕食者的标尺
            double pdp=0.1;//不遇到捕食者的最低限度
            if (r>=pdp)//产生新位置，没有遇到捕食者,如果存在捕食者就原地不动
            {
                if (i>=SSA.is_best&&i<(SSA.is_best+SSA.is_sec_best)){
                    //======>>对于次优解来说。<<========
                    for (int j=0;j<s.array.get(0).city_cycle.size();j++){
                        //针对于每一个维度
                        int a, b, c, flag;
                        int ran1, ran2, temp;
                        int[] Gh1 = new int[cityNum];
                        ran1 = random.nextInt(65535) % cityNum;
                        ran2 = random.nextInt(65535) % cityNum;

                        while (ran1 == ran2) {
                            //如果交叉位置相同的话
                            ran2 = random.nextInt(65535) % cityNum;
                        }

                        if (ran1 > ran2)// 确保ran1<ran2
                        {
                            temp = ran1;
                            ran1 = ran2;
                            ran2 = temp;
                        }
                        flag = ran2 - ran1 + 1;// 删除重复基因前染色体长度
                        for (a = 0, b = ran1; a < flag; a++, b++) {
                            Gh1[a] = s.array.get(i).city_cycle.get(b);//交叉基因组1
                        }
                        // 已近赋值i=ran2-ran1个基因
                        for (c = 0, b = flag; b < cityNum;)// 染色体长度
                        {
                            Gh1[b] = s.array.get(i).city_cycle.get(c++);//对个体1的基因组进行读取
                            for (a = 0; a < flag; a++) {
                                if (Gh1[a] == Gh1[b]) {
                                    break;
                                }
                            }
                            if (a == flag) {
                                b++;
                            }
                        }

                        for (a = 0; a < cityNum; a++) {
                            s.array.get(i).city_cycle.set(a, Gh1[a]);// 交叉完毕放回种群
                        }
                    }
                }
                else if (i>=SSA.is_best+SSA.is_sec_best&&s.array.get(i).is_sec_best==false){
                    //对于普通解，但曾经wei去过ci优解的个体来说
                    int which_second_best = (int) (SSA.is_best + Math.random() * (SSA.is_sec_best));
                    for (int j = 0; j < s.array.get(0).city_cycle.size(); j++) {
                            //针对于每一个维度
                            int a, b, c, flag;
                            int ran1, ran2, temp;
                            int[] Gh1 = new int[cityNum];
                            ran1 = random.nextInt(65535) % cityNum;
                            ran2 = random.nextInt(65535) % cityNum;

                            while (ran1 == ran2) {
                                //如果交叉位置相同的话
                                ran2 = random.nextInt(65535) % cityNum;
                            }

                            if (ran1 > ran2)// 确保ran1<ran2
                            {
                                temp = ran1;
                                ran1 = ran2;
                                ran2 = temp;
                            }
                            flag = ran2 - ran1 + 1;// 删除重复基因前染色体长度
                            for (a = 0, b = ran1; a < flag; a++, b++) {
                                Gh1[a] = s.array.get(i).city_cycle.get(b);//交叉基因组1
                            }
                            // 已近赋值i=ran2-ran1个基因
                            for (c = 0, b = flag; b < cityNum;)// 染色体长度
                            {
                                Gh1[b] = s.array.get(i).city_cycle.get(c++);//对个体1的基因组进行读取
                                for (a = 0; a < flag; a++) {
                                    if (Gh1[a] == Gh1[b]) {
                                        break;
                                    }
                                }
                                if (a == flag) {
                                    b++;
                                }
                            }
                            for (a = 0; a < cityNum; a++) {
                                s.array.get(i).city_cycle.set(a, Gh1[a]);// 交叉完毕放回种群
                            }
                    }
                }else if (i>=SSA.is_best+SSA.is_sec_best&&s.array.get(i).is_sec_best==true){
                    //对于普通解但是去过次优解的解来说
                        for (int j=0;j<s.array.get(0).city_cycle.size();j++){
                            //针对于每一个维度
                            int a, b, c, flag;
                            int ran1, ran2, temp;
                            int[] Gh1 = new int[cityNum];
                            ran1 = random.nextInt(65535) % cityNum;
                            ran2 = random.nextInt(65535) % cityNum;

                            while (ran1 == ran2) {
                                //如果交叉位置相同的话
                                ran2 = random.nextInt(65535) % cityNum;
                            }

                            if (ran1 > ran2)// 确保ran1<ran2
                            {
                                temp = ran1;
                                ran1 = ran2;
                                ran2 = temp;
                            }
                            flag = ran2 - ran1 + 1;// 删除重复基因前染色体长度
                            for (a = 0, b = ran1; a < flag; a++, b++) {
                                Gh1[a] = s.array.get(i).city_cycle.get(b);//交叉基因组1
                            }
                            // 已近赋值i=ran2-ran1个基因
                            for (c = 0, b = flag; b < cityNum;)// 染色体长度
                            {
                                Gh1[b] = s.array.get(i).city_cycle.get(c++);//对个体1的基因组进行读取
                                for (a = 0; a < flag; a++) {
                                    if (Gh1[a] == Gh1[b]) {
                                        break;
                                    }
                                }
                                if (a == flag) {
                                    b++;
                                }
                            }
                            for (a = 0; a < cityNum; a++) {
                                s.array.get(i).city_cycle.set(a, Gh1[a]);// 交叉完毕放回种群
                            }
                        }
                    }
                }else{
                        s.array.get(i).city_cycle.set(0,random.nextInt(65535) % cityNum);//这里就是想初始化一条路径而已，
                        // 我也不知道那个看起来很玄学的65535是干嘛的
                        for (int m = 1; m < cityNum;)// 染色体长度
                        {
                            s.array.get(i).city_cycle.set(m,random.nextInt(65535) % cityNum);
                            int j;
                            for (j = 0; j < m; j++) {
                                if (s.array.get(i).city_cycle.get(m) == s.array.get(i).city_cycle.get(j)) {//如果相同,那就得重新来,路径不能重复
                                    break;
                                }
                            }
                            if (j == m) {
                                m++;
                            }
                        }

                }
            }
        return s;
    }
}
