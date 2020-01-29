public class SSASeasonChange extends operator{
    //季节变化算子
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }

    public SSADoubleSolutionSet execute(SSADoubleSolutionSet s,Singleproblem p ,int t,int generation){
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
            for (int i = SSA.is_best+SSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lower+ levy * (p.upper - p.lower);
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
