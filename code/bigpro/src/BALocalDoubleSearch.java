public class BALocalDoubleSearch extends LocalSearch{
    public BADoubleSolutionSet execute(BADoubleSolutionSet s) {
        double best=Double.MAX_VALUE;
        for (int i=0;i<s.size();i++){
            //寻找最优解的操作
            if (s.array.get(i).fitness[0]<best){
                s.whichbest=i;
                s.best=s.array.get(i).fitness[0];
                best=s.best;
            }
        }
        return s;
    }
}
