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
    public NSGAIIIDoubleSolutionSet execute(NSGAIIIDoubleSolutionSet s,Hyperproblem mp){
        int i = 0;
        while (i < s.size() - 1) {
            double p = Math.random();//突变概率
            if (p < pc) {
                for (int j = 0; j < s.array.get(0).variables.length - 1; j++) {
                    double a1 = s.array.get(i).variables[j].doubleVariable;
                    double b1 = s.array.get(i + 1).variables[j].doubleVariable;
                    double a = Math.random() * r;
                    double b = Math.random() * r;
                    s.array.get(i).variables[j].doubleVariable = (1 - a) * a1 + b * b1;
                    s.array.get(i + 1).variables[j + 1].doubleVariable = (1 - b) * b1 + a * a1;
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
                }
                i += 2;
            }
        }
        return s;
    }
}
