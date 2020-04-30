package main.Operator;

import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.NSGAIIIDoubleSolutionSet;
import main.Solution.ReferencePoint;
import main.Solution.solutionSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NSGAIIIGeneration extends operator{
    HashMap<Integer,ArrayList<NSGAIIIDoubleSolution>> front;
    ArrayList<NSGAIIIDoubleSolution> front_l;
    List<ReferencePoint<NSGAIIIDoubleSolution>> referencePoints;
    NSGAIIIDoubleSolutionSet s;
    public void execute() {

    }
    public NSGAIIIGeneration(NSGAIIIDoubleSolutionSet s){
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
    }
    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public  NSGAIIIDoubleSolutionSet execute(List<ReferencePoint<NSGAIIIDoubleSolution>> ref){
        //ArrayList<NSGAIIIDoubleSolution> front=new ArrayList<>();
        int maxsize=s.size()/2;
        this.referencePoints=ref;
        NSGAIIIDoubleSolutionSet newS=new NSGAIIIDoubleSolutionSet(s.size/2);
        //首先把帕累托等级分开，啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我第一步就卡了
        //恶意吐槽时间，要不要重新在这里实现以下快速非支配排序呢，因为这样的话就有现成的了，hhhhh
        //好烦呢，用Map可以把它分开么，如果key集没有就新添加一个，aaaaa，
        // 应该可以
        //向里面放，知道放到第l-1层
        int rankingIndex=1;//表示第几层,因为是从帕累托等级为1开始的，所以，你懂的
        int candidateSolutions=0;
        while(candidateSolutions<maxsize){
           // System.out.println(front.get(rankingIndex));
            candidateSolutions+=front.get(rankingIndex).size();
            if ((newS.size()+front.get(rankingIndex).size()<=maxsize)){
                //如果没有溢出就往里面添加
                front_l = front.get(rankingIndex);
                for (int i = 0 ; i < front_l.size(); i++) {
                    newS.add(front_l.get(i));
                }
                rankingIndex++;
            }

        }
        List<Double>   ideal_point;//理想点集合
        //寻找ideal point
        ideal_point=translateObjectives(s);
        //寻找extreme point
        List<NSGAIIIDoubleSolution> extreme_point;//额外点集合
        extreme_point=findExtremePoints(s);
        //根据extreme point 我们可以利用高斯消去得到截距
        List<Double> intercepts;
        intercepts=constructHyperplane(s,extreme_point);
        //根据截距，理想点，额外点，我们可以进行函数的标量化
        int numberOfObjectives=s.array.get(1).fitness.length;
        for (int t=1; t<front.size(); t+=1)
        {
            for (NSGAIIIDoubleSolution ss : front.get(t)) {

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
        }
        //划分参考点，将参考点带入
        associate(s);
        //生成参考向量，计算距离和p
        while (s.size() < maxsize)
        //当生成玩参考向量之后，进行选择操作
        {
            int min_rp = FindNicheReferencePoint();

            NSGAIIIDoubleSolution chosen = SelectClusterMember(this.referencePoints.get(min_rp));
            if (chosen == null) // no potential member in Fl, disregard this reference point
            {
                this.referencePoints.remove(min_rp);
            }
            else
            {
                this.referencePoints.get(min_rp).AddMember();
                this.referencePoints.get(min_rp).RemovePotentialMember(chosen);
                s.add(chosen);
            }
        }
        //添加，程序结束
        return s;
    }

    NSGAIIIDoubleSolution SelectClusterMember(ReferencePoint<NSGAIIIDoubleSolution> rp)
    {
        NSGAIIIDoubleSolution chosen = null;
        if (rp.HasPotentialMember())
        {
            if (rp.MemberSize() == 0) // currently has no member
            {
                chosen =  rp.FindClosestMember();
            }
            else
            {
                chosen =  rp.RandomMember();
            }
        }
        return chosen;
    }

    int FindNicheReferencePoint()
    {
        // find the minimal cluster size
        int min_size = Integer.MAX_VALUE;
        for (ReferencePoint<NSGAIIIDoubleSolution> referencePoint : this.referencePoints)
            min_size = Math.min(min_size,referencePoint.MemberSize());

        // find the reference points with the minimal cluster size Jmin
        List<Integer> min_rps=new ArrayList<>();
        for (int r=0; r<this.referencePoints.size(); r+=1)
        {
            if (this.referencePoints.get(r).MemberSize() == min_size)
            {
                min_rps.add(r);
            }
        }
        // return a random reference point (j-bar)
        Random random=new Random();
        return min_rps.get(min_rps.size() > 1 ? random.nextInt(min_rps.size()-1) :0);
    }
    public void associate(NSGAIIIDoubleSolutionSet population) {
        for (int t = 1; t < front.size(); t++) {
            for (NSGAIIIDoubleSolution s : front.get(t)) {
                int min_rp = -1;
                double min_dist = Double.MAX_VALUE;
                for (int r = 0; r < this.referencePoints.size(); r++) {
                    double d = perpendicularDistance(this.referencePoints.get(r).position, s);
                    if (d < min_dist) {
                        min_dist=d;
                        min_rp = r;
                    }
                }
                if (t != front.size()) {
                    this.referencePoints.get(min_rp).AddMember();
                } else {
                    this.referencePoints.get(min_rp).AddPotentialMember(s, min_dist);
                }
            }
        }

    }
    public double perpendicularDistance(List<Double> direction, NSGAIIIDoubleSolution point) {
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
    public List<Double> constructHyperplane(NSGAIIIDoubleSolutionSet population, List<NSGAIIIDoubleSolution> extreme_points) {
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
            for (NSGAIIIDoubleSolution s : extreme_points)
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
    private List<NSGAIIIDoubleSolution> findExtremePoints(NSGAIIIDoubleSolutionSet population) {
        List<NSGAIIIDoubleSolution> extremePoints = new ArrayList<>();
        int numberOfObjectives=population.array.get(1).fitness.length;
        NSGAIIIDoubleSolution min_indv = null;
        for (int f=0; f < numberOfObjectives; f+=1)
        {
            double min_ASF = Double.MAX_VALUE;
            for (NSGAIIIDoubleSolution s : front.get(1)) {
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
    public List<Double> translateObjectives(NSGAIIIDoubleSolutionSet pop) {
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
    private double ASF(NSGAIIIDoubleSolution s, int index) {
        //ASF函数
        double max_ratio = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < s.fitness.length; i++) {
            double weight = (index == i) ? 1.0 : 0.000001;
            max_ratio = Math.max(max_ratio, s.fitness[i]/weight);
        }
        return max_ratio;
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
}
