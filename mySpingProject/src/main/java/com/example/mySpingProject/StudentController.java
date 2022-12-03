package com.example.mySpingProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

	/**
	 * URL: http://loaclhost:8080/name/dawit
	 * @param name
	 * @return first_name
	 * @throws IOException
	 */
	@GetMapping("/name/{sName}")
	public Student student(@PathVariable String sName) throws IOException{
		System.out.println("Search student by name: " + sName);
		List<Student> studentList = readData();
		for(Student student: studentList) {
			if(student.getFirst_name().equalsIgnoreCase(sName)) {
				System.out.println("Found student " + student);
				return student;
			}
		}
		// if no match is found return statement
		System.out.println("No Student found with name: "+ sName);
		return null;
	}
	
	/**
     * URL :  http://localhost:8080/student?gpa=3.4&gender=male
     * @param gpa
     * @param gender
     * @return first_name 
     * @throws IOException
     */
	@GetMapping("/student")
	public Student student(@RequestParam double gpa, @RequestParam String gender) throws IOException{
		System.out.println("search student by gpa : "+gpa+" gender : "+gender);
        List<Student>  studentList = readData();
        for(Student student : studentList){
            if( Double.valueOf(student.getGpa()).equals(gpa) &&   student.getGender().equalsIgnoreCase(gender)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found");
        return null;
	}
	
	List<Grade> gpaList = new ArrayList();
	/**
     *   URL :  http://localhost:8080/gpa
      * @return average gpa 
     * @throws IOException
     */
    @GetMapping("/gpa")
    public double averageGpa() throws IOException {
    	List<Student> studentList = readData();
    	
    	double avg = 0;
    	double sum = 0;
    	for(int i =0; i <gpaList.size(); i++) {
    		sum = sum + gpaList.get(i).getGpa();
    	}
    	avg = sum / gpaList.size();
    	return avg;
    	
    }
    
    
	// reads local text file, stores students in an array, returns list of students
	public List<Student> readData() throws IOException {
        FileReader fileReader = new FileReader("/Users/dfranco/eclipse-workspace/mySpingProject/src/main/resources/static/student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Student> studentList = new ArrayList();
        List<Grade> gpaList = new ArrayList();
        String header = bufferedReader.readLine(); // read the header
        String line = bufferedReader.readLine(); // read the first line

        while ( line != null){
            String[] data = line.split(",");// split each read line by comma
            Grade grade = new Grade(Double.parseDouble(data[2]));
            gpaList.add(grade);
            Student student = new Student(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]),data[3], data[4]); 
            studentList.add(student);
            line = bufferedReader.readLine();
            
        }
        return studentList;
    }
}
