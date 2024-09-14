package com.ideaproject.schatServer.thread;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(InputThread.class);
	BufferedReader br;

	public InputThread(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void run() {
		try {
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch(Exception e) {
			log.error("Error Input Thread run : " + e);
		}
	}
}
