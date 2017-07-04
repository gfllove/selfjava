/**
 * 
 */
package com.uss.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gfl
 *
 */
public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//List<Map> list = new ArrayList<Map>();
		
		Map<Integer,String> map = new HashMap<Integer, String>(151,1);
		
		for(int i=0;i<150;i++){
			map.put(i, i+"value");
			
		}
		
		
		for(int i=0;i<150;i++){
			System.out.println(map.get(i));
			
		}
		
		//System.out.println(map.values());
		
		//System.out.println(map.containsValue("5value9"));
		//System.out.println(map.isEmpty());
		

	}

}
