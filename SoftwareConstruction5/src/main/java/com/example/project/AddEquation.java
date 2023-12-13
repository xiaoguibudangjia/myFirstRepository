package com.example.project;
public class AddEquation extends AbstractEquation {
    //再次声明一下成员属性以便通过反射机制访问
    private short num1;
    private short num2;
    private static final OP OPERATOR = OP.ADD;

    public AddEquation(AddEquationBuilder builder){
        this.setNum1(builder.num1);
        this.setNum2(builder.num2);
        this.setOperator(OPERATOR);
    }

    public AddEquation(){}

    //实现calculate方法
    @Override
    public int calculate() {
        return getNum1() + getNum2();
    }


    //增加静态内部类AddEquationBuilder使用构造模式构造算式
    public static class AddEquationBuilder {
        private short num1;
        private short num2;

        public AddEquationBuilder setNum1(short num1) {
            this.num1 = num1;
            return this;
        }

        public AddEquationBuilder setNum2(short num2) {
            this.num2 = num2;
            return this;
        }

        public AddEquation build() {
            return new AddEquation(this);
        }

    }
}
