package com.rgapps.msglayer;

import org.zeromq.ZMQ;

public class ZeroMQTesting {
	public static void main(String[] args) {
		
		ZMQ.Context context = ZMQ.context(1);
		//context.socket(ZMQ.PAIR);
		
		Monitor monitor = new Monitor( "inproc://monitor.rep", context);
		monitor.init();
		monitor.start();
		try {
			ZMQ.Socket publisher = context.socket(ZMQ.REQ);
			publisher.monitor("inproc://monitor.rep" , ZMQ.EVENT_ALL);
			publisher.connect("tcp://localhost:55555");
			System.out.println("Subsciber binded");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		}catch (Exception ex) {}
		
	}
}
