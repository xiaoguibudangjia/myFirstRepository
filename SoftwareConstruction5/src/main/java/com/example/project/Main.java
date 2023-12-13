package com.example.project;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("======该程序每次运行可以按要求输出若干100以内的算式与答案=======");//功能说明
        Printer.print(EquationCheckerOfRange.getInstance());//把算式输出到文件和控制台
//        reflectAddEquation();//反射机制解析AddEquation

//        //生成五个习题并序列化为JSON格式
//        ObjectMapper objectMapper = new ObjectMapper();
//        EquationCollection equationCollection = new EquationCollection();
//        equationCollection.generate(5,EquationCheckerOfRange.getInstance());
//        String json = objectMapper.writeValueAsString(equationCollection);
//        System.out.println("=======生成五个习题并序列化为JSON格式=======");
//        System.out.println(json);

    }
    public static void reflectAddEquation() throws Exception {//通过java的反射机制解析AddEquation
        Class<AddEquation> clazz = AddEquation.class;
        //创建对象
        AddEquation addEquation = clazz.newInstance();
        //访问成员变量
        Field num1Field = clazz.getDeclaredField("num1");
        Field num2Field = clazz.getDeclaredField("num2");
        //启动访问控制权限
        num1Field.setAccessible(true);
        num2Field.setAccessible(true);
        //设置成员变量的值
        num1Field.set(addEquation, (short)10);
        num2Field.set(addEquation, (short)20);
        //获取clazz对象的成员变量的值
        System.out.println("=======通过java的反射机制解析AddEquation=======");
        System.out.println("==============修改num1和num2的值==========");
        short num1 = (short) num1Field.get(addEquation);
        System.out.println(" num1 = "+num1);
        short num2 = (short) num2Field.get(addEquation);
        System.out.println(" num2 = "+num2);
        //访问成员方法，由于calculate的特殊性，输出的结果为父类中的两数之和，默认值为0
        Method methodEquation = clazz.getMethod("calculate");
        int result = (int) methodEquation.invoke(addEquation);
        System.out.println(" result = "+result);
        //获取AddEquation中的成员方法，如果需要查看父类的方法可以用getMethods()
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("==================Methods================");
        for(Method method : methods){
            System.out.println("Method name: \t"+method);
            System.out.println("Method return type : \t"+method.getReturnType());
            System.out.println("Method parameter count: \t"+method.getParameterCount());
            System.out.println();
        }
        //获取AddEquation中的成员变量，包括从超类中继承的变量
        Field[] fields = clazz.getSuperclass().getDeclaredFields();
        System.out.println("==================Fields================");
        for (Field field : fields){
            System.out.println("Field name: \t"+field.getName()+"\t\t"+field.getType());
        }
    }
}
