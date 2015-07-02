/**
 * Creation Date:2010-6-4
 * 
 * Copyright 2008-2010 © 同程网 Inc. All Rights Reserved
 */
package com.uss.test;

import java.util.Calendar;
import java.util.Date;

/**
 * Description Of The Class<br>
 * QQ:147273974
 * 
 * @author linden.guo(郭凤林)
 * @version 1.000, 2010-6-4
 * 
 */
public class TestBase {
    
    /**
     * 测试返回值是true or false
     * @return
     */
    public static boolean get() {
        try {
            return false;
        } finally {
            return true;
        }
    }
    
    /**
     * 测试输出结果是什么
     */
    public static void strEpx() {
        String classPath = "java.lang.String";
        System.out.println(classPath + "," + classPath.replaceAll(".", "/"));
    }
    
    /**
     * 测试输出结果
     *
     */
    public static void helloWord() {
        try {
            System.out.println("Hello Word!");
            System.exit(0);
        } finally {
            System.out.println("Goodbye world!");
        }
    }
    /**
     * 测试输出结果
     *
     */
    public static void strSwitch() {
        StringBuilder sb = new StringBuilder();
        switch (1) {
            case 1 :
                sb.append("Hello a");
            case 2 :
                sb.append("b");
            case 3 :
                sb.append("c");
            default :
                sb.append("d");
        }
        System.out.println(sb.toString());
    }
    /**
     * 测试输出结果
     *
     */
    public static void increment() {
        int j = 0;
        for (int i = 0; i < 100; i++)
            j = j++;
        System.out.println(j);
    }
    
    /**
     * 数组反转
     */
    public static void arrayReverse(){
        
        String[] str = {"a","b","c","d","e"};
        
        String str1="";
        int ln=str.length;
        System.out.print("原数组:");
        for(int i=0;i<ln;i++){
            System.out.print(str[i]+",");
         }
        for(int i=0;i<ln/2;i++){
            str1=str[i];
            str[i]=str[ln-1-i];
            str[ln-1-i]=str1;

        }

        System.out.println("\n===========");
        System.out.print("反转后数组:");
        for(int i=0;i<ln;i++){
           System.out.print(str[i]+",");
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
         //System.out.println(get());
         //strEpx();
        //helloWord();
        //strSwitch();
        //increment();
         arrayReverse();
    }
    
}
