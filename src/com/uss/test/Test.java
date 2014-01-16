package com.uss.test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Set<Integer> set = new TreeSet<Integer>();
		for(int i=0;i<20;i++){
			set.add(i);
		}
		for(Iterator<Integer> ite =set.iterator();ite.hasNext();){
			System.out.println(ite.next());
		}*/
		
		//int[] num = {1,2,6,7,3,0,9,4};
		/*for(int i:num){
			System.out.println(i);
		}*/
		/*Arrays.sort(num);
		for(int i:num){
			System.out.println(i);
		}*/
		
		//System.out.println(Arrays.binarySearch(num, 2));
        
        /*Map map = new HashMap();
        map.put("A", "1");
        
        System.out.println(map.get("A"));
        
        ArrayList a = new ArrayList(2);
        a.add("1");
        a.add("2");
        a.add("3");
        
        System.out.println(a.get(0));*/
        
   
            
              RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();

              
              /*System.out.println(mxbean.getName());
              System.out.println(mxbean.getVmVendor());
              System.out.println(mxbean.getClassPath());
              System.out.println(mxbean.getLibraryPath());
              System.out.println(mxbean.getManagementSpecVersion());
              System.out.println(mxbean.getSpecName());
              System.out.println(mxbean.getSpecVendor());
              System.out.println(mxbean.getSpecVersion());
              System.out.println(mxbean.getStartTime());
              System.out.println(mxbean.getUptime());
              System.out.println(mxbean.getVmName());
              System.out.println("=====================");*/
              Map map = mxbean.getSystemProperties();
              Collection cl = map.values();
              for(Iterator ite = cl.iterator();ite.hasNext();){
                  System.out.println(ite.next());
                  
              }
              
              
              
              
             
           
                  
               
                  
             
        

	}

}
