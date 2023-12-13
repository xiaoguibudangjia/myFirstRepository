package com.example.project;

public class EquationCheckerOfRange implements  EquationChecker {
    private int min;
    private int max;

    //使用饿汉式单例模式
    //创建 EquationsCheckerOfRange 的一个对象
    private static final EquationCheckerOfRange instance = new EquationCheckerOfRange(0,100);

    public EquationCheckerOfRange(){
    }

    //让构造函数为 private，这样该类就不会被实例化
    private EquationCheckerOfRange (int min, int max) {
        this.max = max;
        this.min = min;
    }

    //获取唯一可用的对象
    public static EquationCheckerOfRange getInstance(){
            return instance;
    }

    //check方法
    public boolean check(IEquation equation){
        int max = 100;
        int min = 0;
        return equation.getNum2() <= max && equation.getNum2() >= min && equation.getNum1() >= min && equation.getNum1() <= max && equation.calculate() >= min && equation.calculate() <= max;
    }

}
