package com.example.project;
public class SubEquation extends AbstractEquation {
    private static final OP OPERATOR = OP.SUB;

    //全参构造和空参构造
    public SubEquation(SubEquationBuilder builder){
        this.setNum1(builder.num1);
        this.setNum2(builder.num2);
        this.setOperator(OPERATOR);
    }

    public SubEquation(){

    }

    //实现calculate方法
    @Override
    public int calculate() {
        return getNum1() - getNum2();
    }

    //增加静态内部类 SubEquationBuilder 使用构造模式构造算式
    public static class SubEquationBuilder {
        private short num1;
        private short num2;

        public SubEquationBuilder setNum1(short num1) {
            this.num1 = num1;
            return this;
        }

        public SubEquationBuilder setNum2(short num2) {
            this.num2 = num2;
            return this;
        }

        public SubEquationBuilder() {}

        public SubEquation build() {
            return new SubEquation(this);
        }
    }
}
