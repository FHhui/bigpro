# bigpro
元启发式算法小组代码框架  
该框架主要有algorithm，operator，problem，solutionSet，四个主类组成，分别对应着算法，运算符，问题，解决方法。  
其中算法algorithm主要有Singlealgorithm，Multialgorithm，Hyperalgorithm，分别对应单目标算法，多目标算法，超多目标算法，算法类的子类主要实现run方法与getResult方法，run方法通过调用getResult以及相关运算符得到solutionSet类型的返回值  
问题problem类主要有Singleproblem，Multiproblem，Hyperproblem类组成，分别对应，单目标问题，多目标问题，超多目标问题，problem类主要包含name（问题名字，多，单，超多），numberOfVariables，自变量维度，numberOfObjectives目标自变量的维度，numberOfconstraints定义域，以及相应的Getter和Setter方法。  
解集solutionSet类主要包含一个solution的ArrayList组成，包含着相关变量的增删改查，solution类便是解的类，相对比而言也就是松鼠算法中的松鼠，每个solution包含着一个double类型的fittness数组，也就是适应值数组，同时还包含这一个variable类型的variables数组，variable类型就是自变量类型，因为在我们部署算法体系中，适应值是固定的double类型的，但是自变量却不一定是自变量类行的，variable主要包含着Binaryvariable，RealVariable，DoubleVariable，二进制自变量，实数自变量，浮点数自变量目前已经用到的三种自变量类型。  
运算符operator类主要包含着我们在算法部署过程中多次重复使用的算子，在这里我们将它抽象出来，形成我们框架的方法库，目前为止，框架中已经有了Crossover交叉算子，LocalSearch本地搜索算子，Randominit随机初始化算子，Selection选择算子，Sort排序算子，Mutation变异算子     
  利用序列化机制实现了深度克隆，这样在方法调用时防止java内存地址指向同一个变量而引起的算法错误。-----------2020.1.21  