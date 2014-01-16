package com.uss.mode.observer;

final class TestObserver {
	
	public static void main(String[] arg){
		
		Watched watched = new Watched();
		watched.addObserver(new Watcher());
		watched.changeState();
		
	}
	

	

}
