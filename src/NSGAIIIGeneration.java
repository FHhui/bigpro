import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NSGAIIIGeneration extends operator{
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public  NSGAIIIDoubleSolutionSet execute(NSGAIIIDoubleSolutionSet s){
        //ArrayList<NSGAIIIDoubleSolution> front=new ArrayList<>();
        NSGAIIIDoubleSolutionSet newS=new NSGAIIIDoubleSolutionSet(s.size/2);
        HashMap<Integer,ArrayList<NSGAIIIDoubleSolution>> front=new HashMap<>();
        ArrayList<NSGAIIIDoubleSolution> front_l;
        //首先把帕累托等级分开，啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我第一步就卡了
        //恶意吐槽时间，要不要重新在这里实现以下快速非支配排序呢，因为这样的话就有现成的了，hhhhh
        //好烦呢，用Map可以把它分开么，如果key集没有就新添加一个，aaaaa，
        // 应该可以
        for (int i=0;i<s.size;i++){
            //首先把帕累托等级给分开
            if (front.keySet(). contains ( s.array.get(i).nq)){
                front.get(s.array.get(i).nq).add(s.array.get(i));
            } else{
                front_l=new ArrayList<>();
                front_l.add(s.array.get(i));
                front.put(s.array.get(i).nq,front_l);
            }
        }
        //向里面放，知道放到第l-1层


        //寻找ideal point

        //寻找extreme point


        //根据extreme point 我们可以利用高斯消去得到截距


        //根据截距，理想点，额外点，我们可以进行函数的标量化


        //划分参考点，将参考点带入


        //生成参考向量，计算距离和p


        //添加，程序结束


        return s;
    }
//    public List<Double> translateObjectives(List<S> population) {
//        List<Double> ideal_point;
//        ideal_point = new ArrayList<>(numberOfObjectives);
//
//        for (int f=0; f<numberOfObjectives; f+=1){
//            double minf = Double.MAX_VALUE;
//            for (int i=0; i<fronts.get(0).size(); i+=1) // min values must appear in the first front
//            {
//                minf = Math.min(minf, fronts.get(0).get(i).getObjective(f));
//            }
//            ideal_point.add(minf);
//
//            for (List<S> list : fronts)
//            {
//                for (S s : list)
//                {
//                    if (f==0) // in the first objective we create the vector of conv_objs
//                        setAttribute(s, new ArrayList<Double>());
//
//                    getAttribute(s).add(s.getObjective(f)-minf);
//
//                }
//            }
//        }
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
            {/
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
