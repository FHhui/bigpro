package main.Operator;

import main.Solution.NSGADoubleSolution;
import main.Solution.NSGADoubleSolutionSet;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solutionSet;

public class CalDistance extends operator{
    //nsga算法的拥挤度计算算法类
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public NSSSADoubleSolutionSet Texecute(NSSSADoubleSolutionSet s){
        //多目标松鼠距离值计算方法
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[0]>s.array.get(j+1).fitness[0]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[0]-s.array.get(i-1).fitness[0])/(s.array.get(s.size()-1).fitness[0]-s.array.get(0).fitness[0]);//函数1的拥挤度计算
        }
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[1]>s.array.get(j+1).fitness[1]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[1]-s.array.get(i-1).fitness[1])/(s.array.get(s.size()-1).fitness[1]-s.array.get(0).fitness[1]);//函数1的拥挤度计算
        }

        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[2]>s.array.get(j+1).fitness[2]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[2]-s.array.get(i-1).fitness[2])/(s.array.get(s.size()-1).fitness[2]-s.array.get(0).fitness[2]);//函数1的拥挤度计算
        }
        return s;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s){
        //多目标松鼠距离值计算方法
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[0]>s.array.get(j+1).fitness[0]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[0]-s.array.get(i-1).fitness[0])/(s.array.get(s.size()-1).fitness[0]-s.array.get(0).fitness[0]);//函数1的拥挤度计算
        }
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[1]>s.array.get(j+1).fitness[1]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[1]-s.array.get(i-1).fitness[1])/(s.array.get(s.size()-1).fitness[1]-s.array.get(0).fitness[1]);//函数1的拥挤度计算
        }
        return s;
    }
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s){
        //多目标遗传算法距离值计算算子
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[0]>s.array.get(j+1).fitness[0]){
                    NSGADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[0]-s.array.get(i-1).fitness[0])/(s.array.get(s.size()-1).fitness[0]-s.array.get(0).fitness[0]);//函数1的拥挤度计算
        }
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[1]>s.array.get(j+1).fitness[1]){
                    NSGADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        s.array.get(s.array.size()-1).distance=s.array.get(0).distance=Integer.MAX_VALUE;
        for(int i=1;i<s.array.size()-1;i++){
            s.array.get(i).distance=0;
            //计算函数1拥挤度的部分
            s.array.get(i).distance=(s.array.get(i+1).fitness[1]-s.array.get(i-1).fitness[1])/(s.array.get(s.size()-1).fitness[1]-s.array.get(0).fitness[1]);//函数1的拥挤度计算
        }
        return s;
    }
}
