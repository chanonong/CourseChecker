package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Course;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConnectionController {

	private String course;
	private String section;

	private Document doc = null;
	
	private List<String> nullCourseList = null;
	
	public ConnectionController() {
		nullCourseList = new ArrayList<String>();
	}
	public void doGet(String course,String section,int size) {
		//System.out.println(nullCourseList.size());
		if(size == nullCourseList.size())
		{
			System.out.println("none course on list");
			System.exit(0);
		}
		if(nullCourseList.contains(course))
			return;
		this.course = course;
		this.section = section;
		parse();
	}

	private void connect() {
		String url = "https://inter-regis.ku.ac.th/_webcourse_data.php?key_field=" + course + "%7C55%7C1";
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parse() {
		connect();
		Course course = new Course();
		// set the header
		course.setHeader(doc.getElementsByTag("p").get(0).text());
		try {
			Element table = doc.getElementsByTag("table").get(0);
			Elements results = table.getElementsByTag("tr");
			
			for(int i = 1 ; i < results.size() ; i++) {
				Element result = results.get(i);
				if(result.getElementsByTag("td").size() == 11) {
					if(result.getElementsByTag("td").get(2).text().equals(section)) {
						course.setSection(result.getElementsByTag("td").get(2).text());
						course.setInstructor(result.getElementsByTag("td").get(5).text());
						course.setAcceptedStudent(result.getElementsByTag("td").get(6).text());
						course.setEnrolledStudent(result.getElementsByTag("td").get(7).text());
						if(course.enrolled.equals(course.accepted)) course.isFull(true);
						else course.isFull(false);
						System.out.println("\t" + course.toString());
					}
				}
			}
		} catch (Exception e) {
			System.err.println("\t[ Course : " + this.course + " Section : " + this.section + " ] not found");
			nullCourseList.add(this.course);
		}
		
	}
}
