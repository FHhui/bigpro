public class NSGADoubleMutation extends Mutation{
    //nsga算法的浮点数变异算子
    double k;//变异的步长
    public NSGADoubleMutation(double pm,double k) {
        super(pm);
        this.k=k;
    }
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s,Multiproblem mp){

        for (int i=0;i<s.array.size();i++){
            double p=Math.random();
            if (p < pm) {
            for (int j=0;j<s.array.get(0).variables.length;j++){
                int flag=(int)(Math.random()*2);
                double temp=s.array.get(i).variables[j].doubleVariable;
//                do {
                if (flag==0){
                    s.array.get(i).variables[j].doubleVariable=temp+k*(mp.upper-s.array.get(i).variables[j].doubleVariable)*Math.random();
                }else{
                    s.array.get(i).variables[j].doubleVariable=temp-k*(s.array.get(i).variables[j].doubleVariable-mp.lower)*Math.random();
                }
                if(s.array.get(i).variables[j].doubleVariable>=mp.upper){
                    s.array.get(i).variables[j].doubleVariable=mp.upper-0.0001;
                }else if(s.array.get(i).variables[j].doubleVariable<=mp.lower){
                    s.array.get(i).variables[j].doubleVariable=mp.lower+0.0001;
                }
                //            }while(s.array.get(i).variables[j].doubleVariable>=mp.upper||s.array.get(i).variables[j].doubleVariable<=mp.lower);
            }
            }
        }
        return s;
    }


}
