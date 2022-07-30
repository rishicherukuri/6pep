package studentinfo;

import java.sql.*;
import java.util.*;

public class student {
	
	public static String getid() {     //Gives us the d character length random id to the student 
					   //in aaa000 first 3 will be the alphabets and the 
		String id = "";		  //last 3 will be numbers
		
		Random rand = new Random();
		String alphabuffer = "abcdefghij";
		String numbuffer = "0123456789";
		int length = 6;
		
		for(int i = 0; i <6; i++) {
			if(i<3)
				id += alphabuffer.charAt(rand.nextInt(alphabuffer.length()));
			else
				id += numbuffer.charAt(rand.nextInt(numbuffer.length()));
		}
		
		return id;
	}
	
	public static void main(String args[]) throws Exception {
		
		System.out.println("Student Management System");
		
		String url = "jdbc:mysql://localhost:3306/studentinfo";
		String uname = "root";									//these are the parameters for the getConnection function
		String pass = "Rishi";									//which will be get the connection from the database from 
		String query; 										//local database having database "studentinfo"
		
		Class.forName("com.mysql.cj.jdbc.Driver");	//Loading the connector driver
		Connection con = DriverManager.getConnection(url, uname, pass);	//getting the connection instance from the Connection interface
		Statement st = con.createStatement();
		
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		while(choice < 6) {
				
			System.out.println("1. Add new  2. Update  3. View  4. Delete  5. Save&Quit || choice > 5 will close application");
			System.out.print("Enter your Choice : ");
			choice = sc.nextInt() ;
			
			switch(choice) {
			case 1:				//adds a new student data to the database
			{
				System.out.println("Add student details (name, age, city):");
				
				String input = sc.next();
				String[] inlist = input.split(",");
				
				String id = getid();
				int age = Integer.valueOf(inlist[1]);
//				query = "insert into students values ('"+id+"','"+inlist[0]+"',"+age+", '"+inlist[2]+"')";
				query="INSERT INTO student VALUES("+'"'+id+'"'+','+'"'+inlist[0]+'"'+','+age+','+'"'+inlist[2]+'"'+")";
				int rows = st.executeUpdate(query);
				
				System.out.println("\nAdded Sucessfully with Id :" + id);
			}
			break;
			
			case 2:				//update the existing students database
			{
				System.out.print("Enter the Id of the student to update: ");
				
				String update_id = sc.next();
				
				System.out.println("Enter updated details seperated with " + "," + "Ex: vamsi,22,vizag");
				String update_info = sc.next();
				
				String update_list[] = update_info.split(",");
				int age = Integer.valueOf(update_list[1]);
				
				query = "update student set sname='"+update_list[0]+"', age="+age+", city='"+update_list[2]+"' where id = '"+update_id+"'";
				
				int res = st.executeUpdate(query);
				
				System.out.println("Succesfully updated details of student with ID: " + update_id);
				
			}
			break;
			
			case 3:			//To view the data of the student 
			{	
				System.out.println("1. Whole data  2. Single entry  3. With Constraints");
				System.out.print("Enter your choice: ");
				int view_choice = sc.nextInt();
				
				switch(view_choice) {
				case 1:			//Gets the data of all students present in the DB 
				{
					query = "select * from student";
					ResultSet rs = st.executeQuery(query);
					
					while(rs.next()) {
						String sdata = "Id: " +  rs.getString(1) + "  Name : " + rs.getString(2)+
										"  Age : " + rs.getInt(3) + "  City : " + rs.getString(4) ;
						
						System.out.println(sdata);
					}
				}
				break;
				
				case 2:					//view only one student data with their ID
				{
					System.out.print("Enter the id of student to view: ");
					String view_id = sc.next();
					
					query = "select * from student where id = '"+view_id+"'";
					ResultSet rs = st.executeQuery(query);
					
					rs.next();
					String sdata = "Id: " +  rs.getString(1) + "  Name : " + rs.getString(2)+
									"  Age : " + rs.getInt(3) + "  City : " + rs.getString(4) ;
					
					System.out.println(sdata);
				
				}
				break;
				
				case 3:					//view data of the student with constraint
				{
					System.out.print("Enter the constraint to view data: ");
					
					String cons = sc.next();
					
					query = "select * from student where "+cons;
					ResultSet rs = st.executeQuery(query);
					
					while(rs.next()) {
						String sdata = "Id: " +  rs.getString(1) + "  Name : " + rs.getString(2)+
										"  Age : " + rs.getInt(3) + "  City : " + rs.getString(4) ;
						
						System.out.println(sdata);
					}
				
				}
				break;
				}
				
				break;
			}
			
			case 4:					//Delete the data about a student with their ID
			{
				System.out.print("Enter the id of student to delete: ");
				String id_to_delete = sc.next();
				
				query = "delete from student where id ='"+ id_to_delete +"'";
				
				int res = st.executeUpdate(query);
				
				if(res > 0)
					System.out.println("Deleted " +res+ " rows");
				
			}
			break;
			
			case 5:
			{
				System.out.println("Saving your data...");
				System.out.println("Closing..");
				break;
			}
			
			}
		}
		
		st.close();
		
		con.close();
	}
}

