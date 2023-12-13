package com.example.project;

import java.util.*;

public class EquationCollection implements Iterable<IEquation> {
    //该类有方法void generate(int n, EquationChecker checker)
    //可以使用EquationFactory产生n个无重复的(HashSet)，受checker实例检查的算式
    private final Set<IEquation> set = new HashSet<IEquation>();
    private final EquationFactory equationFactory = new EquationFactory();
    public void generate(int n, EquationChecker checker) {
        //用迭代器模式产生n个算式
        while (set.size() != n){
            IEquation equation;
            equation = EquationFactory.getEquation();
            if (checker.check(equation)) {
                set.add(equation);//将生成的equation对象加入set集合中
            }
        }

    }
    @Override
    public Iterator iterator(){
        return set.iterator();
    }
}
