package com.rgapps.msglayer;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

import zmq.ZMQ.Event;

public class Monitor implements Runnable{

	private String endPoint;
	private ZMQ.Context context;
	private ZMQ.Socket subscriber;

	public Monitor(String endPoint, Context context) {
		super();
		this.endPoint = endPoint;
		this.context = context;
	}
	
	public void init() {
		subscriber = context.socket(ZMQ.PAIR);
		subscriber.connect(endPoint);
	}
	
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		while (!Thread.interrupted()) {
			Event event = Event.read(subscriber.base());
			if (event!=null) {
				System.out.println("Event" + event);
			}
		}
	}
	
}
