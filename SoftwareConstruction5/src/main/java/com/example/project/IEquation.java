package com.example.project;

public interface IEquation {
    //提供了算式两个操作数（short）、操作符（char）的getter及setter抽象方法，以及calculate抽象方法
    public abstract short getNum1();

    public abstract void setNum1(short num1);

    public abstract short getNum2();

    public abstract void setNum2(short num2);

    public abstract OP getOperator();

    public abstract void setOperator(OP operator);

    public abstract int calculate();

}
