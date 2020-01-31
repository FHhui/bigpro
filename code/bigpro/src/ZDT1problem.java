public class ZDT1problem extends Multiproblem{
    //zdt1问题，多目标问题
    public ZDT1problem(){
        super();
        this.numberOfObjectives=2;
        this.numberOfVariables=30;
        this.upper=1.0;
        this.lower=0.0;
    }
    public NSGADoubleSolution evalute(NSGADoubleSolution s){
        double dou =s.variables[0].getDoubleVariable();
        s.fitness[0]=dou;
        double g=1,sum=0;
        for (int i=1;i<s.variables.length;i++){
            g+=s.variables[i].getDoubleVariable();
        }
        sum=9/ (s.variables.length-1);
        g=sum*g;
        g=1.0+g;
        double h=1.0-Math.sqrt(dou/g);
        s.fitness[1]=h*g;
        return s;
    }
}
