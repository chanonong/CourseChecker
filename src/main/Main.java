package main;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ConnectionController;
import controller.CourseChecker;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("CourseChecker by AntiSec");
		String cid = "";
		String secid = "";
		ArrayList<String[]> data = new ArrayList<String[]>();
		while (true) {
			System.out.print("Course Id ( ! to finish ) : ");
			cid = scn.nextLine().trim();
			if (cid.equals("!")) break;
			while (true) {
				System.out.print("Section Id ( ! to finish ) : ");
				secid = scn.nextLine().trim();
				if(secid.equals("!")) break;
				try { 
					Integer.parseInt(cid);
					Integer.parseInt(secid);
					String[] tmp = {cid,secid};
					data.add(tmp);
				} catch (Exception e) {
					System.err.println("not a valid course/section id");
					break;
				}
			} 
			
		} 
		ConnectionController c = new ConnectionController();
		CourseChecker cc = new CourseChecker(data, c);
	}
}
