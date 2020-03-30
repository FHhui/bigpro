package main.Operator;

import main.Solution.NSGADoubleSolutionSet;
import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.NSGAIIIDoubleSolutionSet;
import main.problem.Hyperproblem;
import main.problem.Multiproblem;

public class NSGADoubleCrossover extends Crossover {
    //NSGA算法的浮点数交叉算法
    double r;//调节因子

    public NSGADoubleCrossover(double pc, double r) {
        super(pc);
        this.r = r;
    }

    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s, Multiproblem mp) {
        int i = 0;
        while (i < s.size() - 1) {
            double p = Math.random();

            if (p < pc) {
                for (int j = 0; j < s.array.get(0).variables.length - 1; j++) {
                    //System.out.println(j);
                    double a1 = s.array.get(i).variables[j].doubleVariable;
                    double b1 = s.array.get(i + 1).variables[j].doubleVariable;
                    double a = Math.random() * r;
                    double b = Math.random() * r;
                    s.array.get(i).variables[j].doubleVariable = (1 - a) * a1 + b * b1;
                    s.array.get(i + 1).variables[j + 1].doubleVariable = (1 - b) * b1 + a * a1;
                    //System.out.println(s.array.get(i).variables[j].doubleVariable);
                    if (s.array.get(i).variables[j].doubleVariable >= mp.upperlimit.get(j)) {
                        s.array.get(i).variables[j].doubleVariable = mp.upperlimit.get(j) - 0.0001;
                    }
                    if (s.array.get(i + 1).variables[j].doubleVariable >= mp.upperlimit.get(j)) {
                        s.array.get(i + 1).variables[j].doubleVariable = mp.upperlimit.get(j) - 0.0001;
                    }

                    if (s.array.get(i).variables[j].doubleVariable <= mp.lowerlimit.get(j)) {
                        s.array.get(i).variables[j].doubleVariable = mp.lowerlimit.get(j) + 0.0001;
                    }
                    if (s.array.get(i + 1).variables[j].doubleVariable <= mp.lowerlimit.get(j)) {
                        s.array.get(i + 1).variables[j].doubleVariable = mp.lowerlimit.get(j) + 0.0001;
                    }
                    //System.out.println(s.array.get(i).variables[j].doubleVariable);
//                    }while(s.array.get(i).variables[j].doubleVariable>=mp.upper
//                            ||s.array.get(i).variables[j].doubleVariable<=mp.lower
//                            ||s.array.get(i+1).variables[j].doubleVariable>=mp.upper
//                            ||s.array.get(i+1).variables[j].doubleVariable<=mp.lower);

                }
                i += 2;
                //System.out.println(i+"xh");
            }
        }
        return s;
    }
    public NSGAIIIDoubleSolutionSet execute(NSGAIIIDoubleSolutionSet s1, Hyperproblem mp){
        NSGAIIIDoubleSolutionSet s=new NSGAIIIDoubleSolutionSet(s1.size);
        int i = 0;
        while (i < s1.size() - 1) {
            NSGAIIIDoubleSolution m=s1.array.get(i).copy(s1.array.get(i),mp);
            NSGAIIIDoubleSolution n=s1.array.get(i+1).copy(s1.array.get(i+1),mp);
            double p = Math.random();//突变概率
            if (p < pc) {
                for (int j = 0; j < s1.array.get(0).variables.length - 1; j++) {

                    double a1 = n.variables[j].doubleVariable;
                    double b1 = m.variables[j].doubleVariable;
                    double a = Math.random() * r;
                    double b = Math.random() * r;
                    m.variables[j].doubleVariable = (1 - a) * a1 + b * b1;
                    n.variables[j + 1].doubleVariable = (1 - b) * b1 + a * a1;
                    if (m.variables[j].doubleVariable >= mp.upperlimit.get(j)) {
                        m.variables[j].doubleVariable = mp.upperlimit.get(j) - 0.0001;
                    }
                    if (n.variables[j].doubleVariable >= mp.upperlimit.get(j)) {
                        n.variables[j].doubleVariable = mp.upperlimit.get(j) - 0.0001;
                    }
                    if (n.variables[j].doubleVariable <= mp.lowerlimit.get(j)) {
                        n.variables[j].doubleVariable = mp.lowerlimit.get(j) + 0.0001;
                    }
                    if (m.variables[j].doubleVariable <= mp.lowerlimit.get(j)) {
                        m.variables[j].doubleVariable = mp.lowerlimit.get(j) + 0.0001;
                    }

                }
                //System.out.println("1");
            }
            s.add(m);
            s.add(n);
            i += 2;
            //System.out.println(s.array.size());
            //System.out.println("1");
        }
        //System.out.println(s.array.size());
        return s;
    }
}
