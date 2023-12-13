package com.example.project;

import java.util.Random;

public class EquationFactory {
    //工厂类EquationFactory有方法IEquation getEquation(op)
    // 可以根据op的枚举类型返回AddEquation或者SubEquation
    public static IEquation getEquation(OP op) {//产生根据传进来的操作符不同的加减法算式
        short num1;
        short num2;
        Random random = new Random();//随机对象random
        num1 = (short) random.nextInt();//生成两个随机数字
        num2 = (short) random.nextInt();
        if (op == OP.ADD) {
            AddEquation.AddEquationBuilder builder = new AddEquation.AddEquationBuilder();
            builder.setNum1(num1).setNum2(num2);
            return new AddEquation(builder);
        }
        else if (op == OP.SUB) {
            SubEquation.SubEquationBuilder builder = new SubEquation.SubEquationBuilder();
            builder.setNum1(num1).setNum2(num2);
            return new SubEquation(builder);
        }
        return null;
    }

    //方法IEquation getEquation()可以返回随机的算式
    public static IEquation getEquation() {//获取随机的操作符从而获取随机的算式
        Random random = new Random();
        OP op = random.nextBoolean() ? OP.ADD : OP.SUB;
        return getEquation(op);
    }
}
