package controller;

import java.util.Date;
import java.util.List;

public class CourseChecker {

	private List<String[]> data;
	private ConnectionController c;

	public CourseChecker(List<String[]> data,ConnectionController c) {
		this.data = data;
		this.c = c;
		run();
	}
	
	private void run() {
		while(true) {
			try {
				System.out.println("Update on : " + new Date());
				getNewData(data);       // method that updates time
				Thread.sleep(30*1000);  // sleep 30 second by default
			} catch ( InterruptedException e ) { /*ignore it */ }
		}
	}

	private void getNewData(List<String[]> data) {
		//System.out.println(data.size());
		for (String[] s : data) {
			c.doGet(s[0], s[1],data.size());
		}
	}
}
