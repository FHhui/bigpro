import main.Algorithm.SingleAlogorithm;
import main.Operator.BADoubleRandominit;
import main.Operator.LocalDoubleSearch;
import main.Solution.BADoubleSolution;
import main.Solution.BADoubleSolutionSet;
import main.Solution.solution;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class BA extends SingleAlogorithm {
    //ba算法蝙蝠算法
    int generation;//最大迭代次数
    int humans;//种群数量
    double r;//脉冲发射频率
    public BA(int generation,int humans){
        this.generation=generation;
        this.humans=humans;
        r=0.7;
    }
    public BADoubleSolutionSet run(Singleproblem p) {
        return getResult(p);
    }



    public BADoubleSolutionSet getResult(Singleproblem p) {
        BADoubleSolutionSet s=new BADoubleSolutionSet(humans);
        BADoubleRandominit BADR=new BADoubleRandominit();
        s=BADR.execute(s,p);

        for (int i=0;i<generation;i++){
            //迭代操作
            for (int j=0;j<humans;j++){
               // Random rand=new Random();
                BADoubleSolution Stemp= solution.clone(s.array.get(j));
                for (int m=0;m<p.getNumberOfVariables();m++){
                    Stemp.f=Stemp.fmin+
                            (Stemp.fmax- Stemp.fmin)
                            * Math.random();
                    Stemp.v+=Stemp.f *
                            (Stemp.variables[m].getDoubleVariable()
                                            -s.array.get(s.whichbest).variables[m].getDoubleVariable());
                    Stemp.variables[m].doubleVariable+=s.array.get(j).v;
                }
                if (Math.random()>r){//这就是随机变化？
                    double temp=Math.random();
                    for (int n=0;n<s.array.get(j).variables.length;n++){
                        Stemp.variables[n].doubleVariable= temp;
                        if(Stemp.variables[n].doubleVariable>=p.upperlimit.get(n)){
                            Stemp.variables[n].doubleVariable=p.upperlimit.get(n)-0.1;
                        }else if (Stemp.variables[n].doubleVariable<=p.lowerlimit.get(n)){
                            Stemp.variables[n].doubleVariable=p.lowerlimit.get(n)+0.1;
                        }
                    }
                }
                RGAproblem p1=(RGAproblem)p;
                double fitness=Stemp.fitness[0];
                Stemp=p1.evalute(Stemp);
                if (Stemp.fitness[0]<fitness&&Math.random()<s.array.get(j).A){
                    s.array.set(j,Stemp);
                    //这里的A和r是可以改变的
                    //s.array.get(j).A=s.array.get(j).A*0.99;//衰变频率
                    s.array.get(j).r0=s.array.get(j).r0*(1-Math.exp(-s.array.get(j).Rf*i));
                   r=s.array.get(j).r0;
                }
                LocalDoubleSearch BADS=new LocalDoubleSearch();
                s=BADS.execute(s);
                //System.out.println(s.array.get(j).fitness[0]);
            }
            System.out.println(s.array.get(s.whichbest).fitness[0]);
        }
        //随机初始化操作
        return null;
    }
    public static void main(String args[]){
        BA test=new BA(50,50);
        RGAproblem p=new RGAproblem();
        test.getResult(p);
    }
}
