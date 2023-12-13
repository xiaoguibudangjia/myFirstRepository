package com.example.project;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Printer {
    public static void print(EquationChecker checker){//print方法向文件和控制台输出算式
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要生成的算式数量：");
        int eqNum = sc.nextInt();//算式总数eqNum
        System.out.println("每行输出多少个算式？：");
        int lineNum = sc.nextInt();//每行有lineNum个算式
        EquationCollection equationCollection = new EquationCollection();

        try{
            FileOutputStream outputStream = new FileOutputStream("Equation.txt");
            equationCollection.generate(eqNum,EquationCheckerOfRange.getInstance());
            outputStream.write(("============以下为生成的算式练习题============\n" ).getBytes());
            int i = 1;//控制格式的变量
            equationCollection.generate(eqNum,checker);
            for (IEquation equation : equationCollection) {//遍历集合向文件中写入算式
                outputStream.write((equation + "     ").getBytes());
                if (i % lineNum == 0){//控制每行输出lineNum个算式
                    outputStream.write("\n".getBytes());
                }
                i++;
            }

            //同样的方法向文件中写入练习题答案
            outputStream.write("\n==========以下为算式练习题的答案==========\n".getBytes());
            int j = 1;
            for (IEquation equation : equationCollection) {
                outputStream.write((""+equation + "\t" + equation.calculate() + "\t\t").getBytes());
                if (j % lineNum == 0){
                    outputStream.write("\n".getBytes());
                }
                j++;
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("练习题和答案都已生成完毕！");
        //输出到控制台
        System.out.println("文件中的内容如下：");
        try {//从文件中读出
            FileReader reader = new FileReader("Equation.txt");
            int ch;
            while ((ch = reader.read())!= -1){
                System.out.print((char) ch+"");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
