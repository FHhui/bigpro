public class NSSSASort extends Sort{
    //多目标松鼠算子的排序操作
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s){
        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                //System.out.println(j);
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSSSADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    if(s.array.get(j).distance<s.array.get(j).distance){
                        //拥挤值距离小的往后排
                        NSSSADoubleSolution ss=solution.clone(s.array.get(j));
                        s.array.set(j,s.array.get(j+1));
                        s.array.set((j+1),ss);
                    }
                }
            }
        }
        return s;
    }
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s){
        for(int i=0;i<s.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSGADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    NSGADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }
            }
        }
        return s;
    }

}
