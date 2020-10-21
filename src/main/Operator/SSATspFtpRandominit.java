package main.Operator;

import main.Solution.SSATspSolution;
import main.Solution.SSATspSolutionSet;
import main.problem.Singleproblem;
import main.problem.TSP;

import java.util.ArrayList;
import java.util.Random;

public class SSATspFtpRandominit extends Randominit{
    //单目标松鼠，使用了邻域搜索功能的，解决单目标tsp问题的初始化算子
    double rmax;
    double rmin;
    double ravg;
    double davg;
    double dmin;
    public SSATspSolutionSet execute(SSATspSolutionSet solutionS, Singleproblem p, int population, int cityNum) {
        Random random=new Random();
        TSP p1=(TSP) p;
        double[][] distance=p1.distance;
        while (!(solutionS.array.size()==population)) {
            int first = random.nextInt(65535) % cityNum;//随机生成第一个点
            SSATspSolution sds = new SSATspSolution();
            sds.city_cycle.add(first);
            while (sds.city_cycle.size() < cityNum - 1) {
                initR(first,cityNum,distance,sds.city_cycle);
                initD(p1);
                double r=(
                        (rmax-rmin)/ (1+
                                Math.exp((ravg-dmin)/(davg-dmin)
                                )
                        )

                )+rmin;
                ArrayList<Integer> citys=new ArrayList<>();
                citys=getcitys(r,distance,cityNum,first,sds.city_cycle);
                int next=get_next(first,citys,distance);
                //System.out.println(next);
                sds.city_cycle.add(next);
                first=next;
            }
            for (int i=0;i<cityNum;i++){
                if (!sds.city_cycle.contains(i)){
                    sds.city_cycle.add(i);
                    //System.out.println(sds.city_cycle.size());
                    break;
                }
            }
            sds=p1.evalute(sds);
            solutionS.array.add(sds);
        }
        return solutionS;
    }
    public void initD(TSP t){
        //初始化d系数
        dmin=Double.MAX_VALUE;
        double sum=0;
        davg=0;
        for (int i=0;i<t.cityNum;i++){
               for (int j=0;j<t.cityNum;j++){
                   sum+=t.distance[i][j];
                   if (t.distance[i][j]<dmin){
                       dmin=t.distance[i][j];
                   }
               }
        }
        davg=sum/t.cityNum;
    }
    public void initR(int a, int cityNum, double[][] distance, ArrayList<Integer> city_cycle){
        //初始化r系变量
        rmin=Double.MAX_VALUE;
        rmax=Double.MIN_VALUE;
        double sum=0;
        int h=0;
        ravg=0;
        for (int i=0;i<cityNum;i++){
            if (!city_cycle.contains(i)){
                //这里的city_cycle相当于是一个禁忌表
                sum+=distance[a][i];
                h++;
                if (distance[a][i]>rmax){
                    rmax=distance[a][i];
                }
                if (distance[a][i]<rmin){
                    rmin=distance[a][i];
                }
        }
        }
        ravg=sum/h;
    }
    public ArrayList<Integer> getcitys(double r,double[][] distance,int cityNum,int a,ArrayList<Integer> city_cycle){
        //生成邻域内的城市列表
        ArrayList city=new ArrayList<Integer>();
        for (int i=0;i<cityNum;i++){
            //System.out.println(city_cycle.contains(a));
            if (!city_cycle.contains(i)){
                if (distance[a][i]<=r){
                    //System.out.println(city_cycle.contains(a));
                    city.add(i);
                }
            }
        }
        return city;
    }
    public int get_next(int a,ArrayList<Integer> citys,double[][] distance){
        //获得下一个城市位置函数
        double m=Math.random();
        //System.out.println(m);
        //Pi=2(n+1-i)/(n(n+1))
        double n=citys.size();
        citys=sort(a,citys,distance);
        ArrayList<Double> pi=new ArrayList<Double>();
        //System.out.println(n);
        for (int i=0;i<citys.size();i++){
            //System.out.println(1/(n*(n+1)));
            double pc=2*(n+1-i)/(n*(n+1));
            //System.out.println(pc);
            pi.add(pc);
        }
        //double p=0;
        int i=0;
        while(m>0){
            m=m-pi.get(i);
            //System.out.println(pi.get(i));
            //System.out.println(i);
            i++;
        }
        i=i-1;
        return citys.get(i);
    }
    public ArrayList<Integer> sort(int a,ArrayList<Integer> citys,double[][] distance){
        for (int i=0;i<citys.size();i++){
            for (int j=0;j<citys.size()-1-i;j++){
                //冒泡排序
                if (distance[a][citys.get(j)]>distance[a][citys.get(j+1)]){
                    int temp=citys.get(j);
                    int temp1=citys.get(j+1);
                    citys.set(j,temp1);
                    citys.set(j+1,temp);
                }
            }
        }
        return citys;
    }
}
