## CS61B: Data Structures and Algorithms

csdiy对CS61B的介绍：  [https://csdiy.wiki/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/CS61B/](https://csdiy.wiki/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/CS61B/)  

CS61系列第二门课，用Java讲解常见的数据结构与算法。这门课的开头详细介绍了Java语法，所以不需要Java基础。  

我学习的是spring2024版本。  

### 测试

CS61B的很多lab/project没有提供所有测试样例，需要自己编写。由于无法使用autograder评测，我不能保证自己的所有代码准确无误，但是我尽可能完成了所有测试并通过。  

如果希望有完整的测试环境，可以学习2018版本。  

### 格式

我的代码没有去掉 ``To do`` 或 ``fix it`` 注释。其余部分均符合格式要求。  

从proj1b开始，将变量命名由驼峰变为下划线。 

### 具体测试说明

**proj1a**

包含了要求的所有测试函数。许多测试函数不独立，与其它函数结合来尽量测试所有边界情况。  

**proj1b**

包含了要求的所有测试函数，每个测试函数独立，只测试对应的情况。增加了一些其它函数来检查特殊情况。  

``add_first_and_last`` ：测试 ``addFirst`` 和 ``addLast`` 函数结合使用的情况。  

``is_empty_after_remove_to_empty`` ：添加后清空AList，检查 ``is_empty`` 情况。  

``get_and_size_and_is_empty_after_resizing`` ：resize up和resize down后，分别检查 ``get`` 、 ``size`` 和 ``is_empty`` 函数（未完成）。