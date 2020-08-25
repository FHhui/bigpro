package main.Operator;

import main.Algorithm.MaShOA;
import main.Algorithm.MoSSA;
import main.Algorithm.SSA;
import main.Solution.*;
import main.problem.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SSASeasonChange extends operator{
    //季节变化算子
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, Multiproblem p, int t, int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = 0;
        try {
            Sct = this.GD(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        if (Sct < Smin) {
            //System.out.println("2121321321321");
            for (int i = MoSSA.is_best+ MoSSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        return s;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, Hyperproblem p, int t, int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = 0;
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        for (int i = MoSSA.is_best; i < MoSSA.is_best+ MoSSA.is_sec_best; i++) {
            for (int j = 0; j < s.array.get(0).variables.length; j++) {
                Sct += (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable)
                        * (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable);
            }
            Sct = Math.sqrt(Sct);
        }
        if (Sct < Smin) {
            for (int i = MoSSA.is_best+ MoSSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        //季节变化条件结束代表着所有的自变量改变结束，因此在这里更新适应值
        for (int i=0;i<s.array.size();i++){
            DTLZ1 p1=(DTLZ1) p;
            s.array.set(i,p1.evalute(s.array.get(i)));
        }
        return s;
    }
    public SSADoubleSolutionSet execute(SSADoubleSolutionSet s, Singleproblem p , int t, int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = 0;
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        for (int i = SSA.is_best; i < SSA.is_best+SSA.is_sec_best; i++) {
            for (int j = 0; j < s.array.get(0).variables.length; j++) {
                Sct += (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable)
                        * (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable);
            }
            Sct = Math.sqrt(Sct);
        }
        if (Sct < Smin) {
            for (int i = SSA.is_best+ SSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        for (int i=0;i<s.array.size();i++){
            RGAproblem p1=(RGAproblem) p;
            s.array.set(i,p1.evalute(s.array.get(i)));
        }
        return s;
    }
    public MaShOADoubleSolutionSet execute(MaShOADoubleSolutionSet s,Hyperproblem p,int t,int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = Double.MAX_VALUE;
        double Sct1=0;
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        for (int h=0 ;h<MaShOA.is_best;h++){
        for (int i = MaShOA.is_best; i < MaShOA.is_best+MaShOA.is_sec_best; i++) {
            for (int j = 0; j < s.array.get(0).variables.length; j++) {
                Sct1 = (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable)
                        * (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable);
            }
            //Sct = Math.sqrt(Sct);
            if (Sct>Sct1) Sct=Sct1;
            }
        }
        if (Sct < Smin) {
            System.out.println("Sct:"+Sct+"Smin:"+Smin);
            for (int i = MaShOA.is_best+ MaShOA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        return s;
    }
    public SSATspSolutionSet execute(SSATspSolutionSet s, Singleproblem p , int t, int generation,double best,int cityNum){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = s.array.get(0).fitness-best;
        if (Sct<0){
            Sct=0-Sct;
        }
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        if (Sct <= Smin) {
            //变异操作\
            System.out.println("bianyi");
            for (int i=2;i<s.array.size();i++){
            int ran1, ran2, temp,count;
            Random random=new Random();
            ran1 = random.nextInt(65535) % cityNum;
            ran2 = random.nextInt(65535) % cityNum;
            while (ran1 == ran2) {
                    ran2 = random.nextInt(65535) % cityNum;
                }
            temp = s.array.get(i).city_cycle.get(ran1);
            s.array.get(i).city_cycle.set(ran1,s.array.get(i).city_cycle.get(ran2));
            s.array.get(i).city_cycle.set(ran2,temp);
            }
        }
        return s;
    }
    private double Factorial(double n) // 阶乘
    {
        int m = 10000;
        double An = 1;
        for (int i = 1; i <= m - 1; i++) {
            An *= i / (i + n);
        }
        An = An * m * Math.pow((m + n / 2), n - 1);
        return An;
    }
    public double GD(NSSSADoubleSolutionSet s) throws IOException {
        double gd=0;
        File file = new File("D:\\ZDT6.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        ArrayList<double[]> sc=new ArrayList<>();
        String h = "";

        while ((h =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            String[] ss=h.split("\t");
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
        //double gd=0;
        for (int i=0;i<s.size();i++){
            gd+=dis[i];
        }
        gd=gd/s.size();
        System.out.println("这个算法的GD指标为"+gd);
        return gd;
    }
    private double Levy() // 列维函数
    {
        double ra = Math.random();
        ra=Math.exp(-ra*ra/2)/Math.sqrt(2*Math.PI);//[0,1]区间上的高斯函数r1
        double rb = Math.random();
        rb=Math.exp(-rb*rb/2)/Math.sqrt(2*Math.PI);//[0,1]区间上的高斯函数r2
        double beta = 1.5;
        double sigma = Math.pow(((Factorial(beta) * Math.sin(Math.PI * beta / 2))
                / (Factorial((beta - 1) / 2) * beta * Math.pow(2, ((beta - 1) / 2)))), 1 / beta);
        double levy = 1 * ra * sigma / (Math.pow((Math.abs(rb)), 1 / beta));
        return levy;
    }
}
