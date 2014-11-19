package com.uss.mode.observer;

import java.util.Observable;
import java.util.Observer;

/*
 * Observer这个是观察者，是接口。程序中的观察者类，需要实现这个接口中的update()方法
 * 
 */
public class Watcher implements Observer {

	//有被观察者发生变化，自动调用对应观察者的update方法
	public void update(Observable o, Object arg) { 
		
		System.out.println("Watcher print ......");

	}

}
