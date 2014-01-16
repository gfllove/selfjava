package com.uss.mode.observer;

import java.util.Observable;
import java.util.Observer;

public class Watcher implements Observer {

	public void update(Observable o, Object arg) {
		
		System.out.println("Watcher print ......");

	}

}
