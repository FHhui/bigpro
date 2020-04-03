package main.Operator;

import main.Algorithm.MaShOA;
import main.Solution.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RefGeneration extends operator{
    //参考点迭代算法
    List<ReferencePoint<MaShOADoubleSolution>> referencePoints;
    MaShOADoubleSolutionSet s;
    HashMap<Integer, ArrayList<MaShOADoubleSolution>> front;
    ArrayList<MaShOADoubleSolution> front_l;
    @Override
    public void execute() {

    }
    public List<ReferencePoint<MaShOADoubleSolution>> run(MaShOADoubleSolutionSet s,List<ReferencePoint<MaShOADoubleSolution>> ref){
        //参考点迭代算法
        this.s=s;

        this.front=new HashMap<>();
        for (int i=0;i<s.size();i++){
            //首先把帕累托等级给分开
            if (front.keySet(). contains ( s.array.get(i).rank)){
                front.get(s.array.get(i).rank).add(s.array.get(i));

            } else{
                front_l=new ArrayList<>();
                front_l.add(s.array.get(i));
                front.put(s.array.get(i).rank,front_l);
            }
        }
        this.referencePoints=ref;
        List<Double>   ideal_point;//理想点集合
        //寻找ideal point
        ideal_point=translateObjectives(s);
        List<MaShOADoubleSolution> extreme_point;//额外点集合
        extreme_point=findExtremePoints(s);
        List<Double> intercepts;
        intercepts=constructHyperplane(s,extreme_point);
        int numberOfObjectives=s.array.get(1).fitness.length;

        for (MaShOADoubleSolution ss : s.array) {

            for (int f = 0; f < numberOfObjectives; f++) {
                if(Math.abs(intercepts.get(f)-ideal_point.get(f))> 10e-10)
                {
                    ss.fitness[f]=(ss.fitness[f]-ideal_point.get(f)) / (intercepts.get(f)-ideal_point.get(f));
                }
                else
                {
                    ss.fitness[f]=(ss.fitness[f]-ideal_point.get(f)) / (intercepts.get(f)-ideal_point.get(f));
                }

            }
        }

        //划分参考点，将参考点带入
        associate(s);
        for (int i=0;i<referencePoints.size();i++){
            if (referencePoints.get(i).MemberSize()==0){
                //无关联的参考点
                MaShOADoubleSolution m=selectMember();
                ReferencePoint<MaShOADoubleSolution> mm=new ReferencePoint<MaShOADoubleSolution>(m);
                referencePoints.set(i,mm);
            }
        }
        return null;
    }
    public MaShOADoubleSolution selectMember(){
        //对参考点进行遍历，选择sin值最大的
            MaShOADoubleSolution best = null;
            //找最小值
            double sin=Double.MIN_VALUE;
            for (int i=0;i<s.array.size();i++){
                double a=perpendicularDistance(s.array.get(i).referencePoint.position,s.array.get(i));
                double b=0;
                for (int j=0;j<s.array.get(i).fitness.length;j++){
                    b=s.array.get(i).fitness[j]*s.array.get(i).fitness[j];
                }
                b=Math.sqrt(b);
                double ans=b/a;
                if (ans>sin && s.array.get(i).is_select==false){
                    sin=ans;
                    best=s.array.get(i);
                }
            }
            return best;



    }
    public void associate(MaShOADoubleSolutionSet population) {
        for(MaShOADoubleSolution ss:this.s.array){
            int min_rp=-1;
            double min_dist= Double.MIN_VALUE;
            for (int r=0; r<this.referencePoints.size();r++){
                double d= perpendicularDistance(this.referencePoints.get(r).position,ss);
                if (d<min_dist){
                    min_dist=d;
                    min_rp = r;
                }
            }
            this.referencePoints.get(min_rp).AddMember();//参考点需要记录自己关联了多少
            ss.distance=min_dist;
            ss.setReferencePoint(this.referencePoints.get(min_rp));
        }
    }
    public double perpendicularDistance(List<Double> direction, MaShOADoubleSolution point) {
        double numerator = 0, denominator = 0;
        for (int i=0; i<direction.size(); i+=1)
        {
            numerator += direction.get(i)*point.fitness[i];
            denominator += Math.pow(direction.get(i),2.0);
        }
        double k = numerator/denominator;

        double d = 0;
        for (int i=0; i<direction.size(); i+=1)
        {
            d += Math.pow(k*direction.get(i) - point.fitness[i],2.0);
        }
        return Math.sqrt(d);
    }
    public List<Double> constructHyperplane(MaShOADoubleSolutionSet population, List<MaShOADoubleSolution> extreme_points) {
        // Check whether there are duplicate extreme points.
        // This might happen but the original paper does not mention how to deal with it.
        int numberOfObjectives=population.array.get(1).fitness.length;
        boolean duplicate = false;
        for (int i=0; !duplicate && i< extreme_points.size(); i+=1)
        {
            for (int j=i+1; !duplicate && j<extreme_points.size(); j+=1)
            {
                duplicate = extreme_points.get(i).equals(extreme_points.get(j));
            }
        }

        List<Double> intercepts = new ArrayList<>();

        if (duplicate) // cannot construct the unique hyperplane (this is a casual method to deal with the condition)
        {
            for (int f=0; f<numberOfObjectives; f+=1)
            {
                // extreme_points[f] stands for the individual with the largest value of objective f
                intercepts.add(extreme_points.get(f).fitness[f]);
            }
        }
        else
        {
            // Find the equation of the hyperplane
            List<Double> b = new ArrayList<>(); //(pop[0].objs().size(), 1.0);
            for (int i =0; i < numberOfObjectives;i++)
                b.add(1.0);///？？？？这里为什么是1.0不应该是0.0？？？？

            List<List<Double>> A=new ArrayList<>();
            for (MaShOADoubleSolution s : extreme_points)
            {
                List<Double> aux = new ArrayList<>();
                for (int i = 0; i < numberOfObjectives; i++)
                    aux.add(s.fitness[i]);
                A.add(aux);//A是极端点的极端值的集合的集合
            }
            List<Double> x = guassianElimination(A, b);

            // Find intercepts
            for (int f=0; f<numberOfObjectives; f+=1)
            {
                intercepts.add(1.0/x.get(f));

            }
        }
        return intercepts;
    }
    private List<MaShOADoubleSolution> findExtremePoints(MaShOADoubleSolutionSet population) {
        List<MaShOADoubleSolution> extremePoints = new ArrayList<>();
        int numberOfObjectives=population.array.get(1).fitness.length;
        MaShOADoubleSolution min_indv = null;
        for (int f=0; f < numberOfObjectives; f+=1)
        {
            double min_ASF = Double.MAX_VALUE;
            for (MaShOADoubleSolution s : front.get(1)) {
                // only consider the individuals in the first front
                double asf = ASF(s, f);
                if ( asf < min_ASF ) {
                    min_ASF = asf;
                    min_indv = s;
                }
            }
            extremePoints.add(min_indv);
        }
        return extremePoints;
    }
    public List<Double> translateObjectives(MaShOADoubleSolutionSet pop) {
        List<Double> ideal_point;
        int numberOfObjectives=pop.array.get(1).fitness.length;
        ideal_point = new ArrayList<>(numberOfObjectives);

        for (int f=0; f<numberOfObjectives; f+=1){
            double minf = Double.MAX_VALUE;
            for (int i=0; i<front.get(1).size(); i+=1) // min values must appear in the first front
            {
                minf = Math.min(minf, front.get(1).get(i).fitness[f]);
            }
            ideal_point.add(minf);
        }
        return ideal_point;
    }
    private double ASF(MaShOADoubleSolution s, int index) {
        //ASF函数
        double max_ratio = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < s.fitness.length; i++) {
            double weight = (index == i) ? 1.0 : 0.000001;
            max_ratio = Math.max(max_ratio, s.fitness[i]/weight);
        }
        return max_ratio;
    }
    public MaShOADoubleSolution SE(MaShOADoubleSolution news){
        double[] f=new double[s.array.get(0).fitness.length];
        for (int i=0;i<f.length;i++){
            f[i]=0;
        }
        for (MaShOADoubleSolution ma:s.array){
            for (int i=0;i<f.length;i++){
                f[i]+=ma.fitness[i];
            }
        }
        double[] w=new double[s.array.get(0).fitness.length];
        for (int i=0;i<w.length;i++){
            w[i]=news.fitness[i]/f[i];
        }
        double[] se=new double[s.array.get(0).fitness.length];
        for (int i=0;i<se.length;i++){
            if (w[i]==0){
                se[i]=news.fitness[i]/1e-6;
            }else{
                se[i]=news.fitness[i]/w[i];
            }
        }
        double maxse=Double.MAX_VALUE;
        for (int i=0;i<se.length;i++){
            if (maxse>se[i]){
                maxse=se[i];
            }
        }
        news.se=maxse;
        return news;
    }
    public List<Double> guassianElimination(List<List<Double>> A, List<Double> b) {
        List<Double> x = new ArrayList<>();
        //这里实际上就是高斯消元
        int N = A.size();
        for (int i=0; i<N; i+=1)
        {
            A.get(i).add(b.get(i));
        }

        for (int base=0; base<N-1; base+=1)
        {
            for (int target=base+1; target<N; target+=1)
            {
                double ratio = A.get(target).get(base)/A.get(base).get(base);
                for (int term=0; term<A.get(base).size(); term+=1)
                {
                    A.get(target).set(term, A.get(target).get(term) - A.get(base).get(term)*ratio);
                }
            }
        }

        for (int i = 0; i < N; i++)
            x.add(0.0);

        for (int i=N-1; i>=0; i-=1)
        {
            for (int known=i+1; known<N; known+=1)
            {
                A.get(i).set(N, A.get(i).get(N) - A.get(i).get(known)*x.get(known));
            }
            x.set(i, A.get(i).get(N)/A.get(i).get(i));
        }
        return x;
    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
}
