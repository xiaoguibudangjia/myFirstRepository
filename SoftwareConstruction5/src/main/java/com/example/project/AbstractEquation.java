package com.example.project;

public abstract class AbstractEquation implements IEquation{
    //实现了IEquation的部分方法，包括getter、setter，它还覆盖实现equals、hashCode方法。
    // AbstractEquation还提供了三个参数的构造方法
    //定义两个数字和枚举类型的操作符
    private short num1 ;
    private short num2 ;
    private OP operator;

    //空参构造和全参构造
    public AbstractEquation() {

    }

    public AbstractEquation(short num1, short num2, OP operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    //getter、setter方法
    public short getNum1() {
        return num1;
    }

    public void setNum1(short num1) {
        this.num1 = num1;
    }

    public short getNum2() {
        return num2;
    }

    public void setNum2(short num2) {
        this.num2 = num2;
    }

    public OP getOperator() {
        return operator;
    }

    public void setOperator(OP operator) {
        this.operator = operator;
    }

    //重写equals方法
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractEquation equation = (AbstractEquation) obj;
        return equation.getNum1() == ((AbstractEquation) obj).getNum1() && equation.getNum2() == ((AbstractEquation) obj).getNum2() &&equation.getOperator() == ((AbstractEquation) obj).getOperator();
    }

    //重写hashCode方法
    @Override
    public int hashCode(){
        int result = 17;
        return result * 31 + num1 + num2 + operator.hashCode();
    }

    //重写toString方法
    @Override
    public String toString() {
        return String.format("%3d %s %-3d=",num1,operator == OP.ADD?"+":"-",num2);
    }
}
