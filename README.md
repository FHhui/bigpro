# bigpro    
## 项目描述：    
该框架主要有algorithm，operator，problem，solutionSet，四个主类组成，分别对应着算法，运算符，问题，解决方法。  
其中算法algorithm主要有Singlealgorithm，Multialgorithm，Hyperalgorithm，分别对应单目标算法，多目标算法，超多目标算法，算法类的子类主要实现run方法与getResult方法，run方法通过调用getResult以及相关运算符得到solutionSet类型的返回值  
问题problem类主要有Singleproblem，Multiproblem，Hyperproblem类组成，分别对应，单目标问题，多目标问题，超多目标问题，problem类主要包含name（问题名字，多，单，超多），numberOfVariables，自变量维度，numberOfObjectives目标自变量的维度，numberOfconstraints定义域，以及相应的Getter和Setter方法。  
解集solutionSet类主要包含一个solution的ArrayList组成，包含着相关变量的增删改查，solution类便是解的类，相对比而言也就是松鼠算法中的松鼠，每个solution包含着一个double类型的fittness数组，也就是适应值数组，同时还包含这一个variable类型的variables数组，variable类型就是自变量类型，因为在我们部署算法体系中，适应值是固定的double类型的，但是自变量却不一定是自变量类行的，variable主要包含着Binaryvariable，RealVariable，DoubleVariable，二进制自变量，实数自变量，浮点数自变量目前已经用到的三种自变量类型。  
运算符operator类主要包含着我们在算法部署过程中多次重复使用的算子，在这里我们将它抽象出来，形成我们框架的方法库，目前为止，框架中已经有了Crossover交叉算子，LocalSearch本地搜索算子，Randominit随机初始化算子，Selection选择算子，Sort排序算子，Mutation变异算子     
  利用序列化机制实现了深度克隆，这样在方法调用时防止java内存地址指向同一个变量而引起的算法错误。-----------2020.1.21     
  uml类图链接：https://github.com/FHhui/bigpro/blob/master/uml%E7%B1%BB%E5%9B%BE.jpg
Inspired by JMetal    
## 功能：    
### 功能1：   
通过调用框架中的算法与算子快速解决一个单目标，多目标，超多目标优化问题。    
例如：  SSA t=new SSA(100,50);    
        t.getResult(new RGAproblem());    
部署SSA算法解决拉斯特林函数问题（30维多峰函数）
### 功能2：   
方便不同算法在同一环境下比较性能的优劣程度
### 功能3：   
可以将不同的元启发式算法以及优化问题统一部署整合
## Installation：    
IntellJ IDEA安装流程：file——>project from version control——>url:git@github.com:FHhui/bigpro.git
### Build from source:
git clone https://github.com/FHhui/bigpro.git
## 预览  
## 计划功能：
- [ ] 到处csv数据类型文件的功能  
- [ ] 部署多目标松鼠  
## 贡献    
我们欢迎任何形式的反馈和贡献
## 突破性技术
