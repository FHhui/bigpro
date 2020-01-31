public class NSGADoubleGeneration extends Selection {
    //author:FHhui 2020.1.31(nsga最后一个算子留念)
    //nsga算法的迭代选择算子
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s,NSGADoubleSolutionSet s1,Multiproblem p){
        NSGADoubleSolutionSet news=new NSGADoubleSolutionSet(s.size());
        NSGADoubleSolutionSet totalS=new NSGADoubleSolutionSet(2*s.size());
        for (int i=0;i<2*s.size();i++){
            if (i<s.size()){
                totalS.add(s.array.get(i));
            }else{
                int n=i-s.size();
                totalS.add(s1.array.get(n));
            }
        }
        ZDT1problem p1=(ZDT1problem)p;
        for (int i=0;i<totalS.size();i++){
            totalS.array.set(i,p1.evalute(totalS.array.get(i)));
            System.out.println("["+totalS.array.get(i).fitness[0]+","+totalS.array.get(i).fitness[1]+"],");
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhh");
        NSGAFastNonSort Nfns=new NSGAFastNonSort();
        totalS=Nfns.execute(totalS);
        NSGACalDistance Nscd=new NSGACalDistance();
        totalS=Nscd.execute(totalS);
        for (int i=0;i<s.size();i++){
            news.add(totalS.array.get(i));
        }
        return news;
    }
}
