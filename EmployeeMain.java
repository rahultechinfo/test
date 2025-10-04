package employeeManagementSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class EmployeeMain {

	public static void main(String[] args) {
	    
		List <Employee> employeeList = new ArrayList <Employee>();
		
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
	    
		
		//print each employee's details on a one line
	    System.out.println(employeeList);
		
		//print each employee's details on a separate line
		/*
		 for(Employee employee : employeeList) {
		  System.out.println(employee);
		}
		*/
	   
	    System.out.println();
	    
	    //male and female employees in the organization
		Map<String,Long> noOfMaleAndFemaleEmployees = employeeList.stream()
		                 .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		
		System.out.println("No Of Male And Female Employees: " + noOfMaleAndFemaleEmployees);
		System.out.println();
		
		//name of all departments in the organization
		List<String> departments = employeeList.stream()
				                                 .map(Employee::getDepartment)
				                                 .distinct()
		                                         .collect(Collectors.toList());
		System.out.println("Departments: " + departments);
		/*
		employeeList.stream()
        .map(Employee::getDepartment)
        .distinct()
        .forEach(System.out::println);
		*/
		System.out.println();
		
		
		//Average age of male and female employees
		Map<String,Double> avgAgeOfMaleAndFemaleEmployee = employeeList.stream()
		                   .collect(Collectors.groupingBy(Employee::getGender,
		                		    Collectors.averagingInt(Employee::getAge)));
		System.out.println("Average age of male and female employees: " + avgAgeOfMaleAndFemaleEmployee);
		System.out.println();

		
		
		//Details of Highest paid employee in the organization
		Optional<Employee> highestPaidEmployeeWrapper = employeeList.stream()
		                    .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		//System.out.println(highestPaidEmployeeWrapper);
		
		  Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
		  System.out.println("Highest Paid Employee: " + highestPaidEmployee);
		  System.out.println();

		
		  
		//Get the names of the employees who have joined after 2015.
		 List<String> employeesJoinedAfter2015 = employeeList.stream()
		             .filter(e -> e.getYearOfJoining() > 2015)
		             .map(Employee::getName)
		             .collect(Collectors.toList());
		 
		 System.out.println("Employees Joined After 2015: " + employeesJoinedAfter2015);	
		 System.out.println();

		 
		 
		 //Count the number of employees in each department
		 Map<String,Long> employeeCountByDepartment = employeeList.stream()
		                         .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
	     
		 System.out.println("Employee Count By Department:==> ");
		 
		 for(Map.Entry<String, Long> entry : employeeCountByDepartment.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		 }
		 System.out.println();

		 
		/* 
		 Set<Entry<String, Long>> entrySet = employeeCountByDepartment.entrySet();
		 
		 for(Entry<String, Long> entry : entrySet) {
			 System.out.println(entry.getKey() + ": " + entry.getValue());
		 }
		 */
		
		
		  
		 //Average salary of each department?
		Map<String,Double> avgSalaryOfDepartments = 
		                  employeeList.stream()
				               .collect(Collectors.groupingBy(Employee::getDepartment, 
				            		   Collectors.averagingDouble(Employee::getSalary)));
				               /*.entrySet()
				               .forEach(System.out::println);*/

		System.out.println("Average Salary Of Departments:==>");

		for(Map.Entry<String,Double> entry : avgSalaryOfDepartments.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println();
		
		
		
		//Get the details of youngest male employee in the product development department.
		Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper = 
					 employeeList.stream()
			             .filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Product Development"))
			             .min(Comparator.comparingInt(Employee::getAge));				
				//System.out.println(youngestMaleEmployeeInProductDevelopmentWrapper);
				
		 System.out.println("Youngest male employee in the product development department: ");
		
		 
		 youngestMaleEmployeeInProductDevelopmentWrapper.ifPresent(System.out::println);
		 
			/*
			 * if(youngestMaleEmployeeInProductDevelopmentWrapper.isPresent()) {
			 * System.out.println(youngestMaleEmployeeInProductDevelopmentWrapper.get()); 
			 * }
			 */
		 
		 Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper1 = employeeList.stream()
	             .filter(e -> e.getGender()== "Male" && e.getDepartment() == "Product Development")
	             .sorted(Comparator.comparingInt(Employee::getAge))
	             .findFirst();
		 System.out.println(youngestMaleEmployeeInProductDevelopmentWrapper1.orElse(null));
		 
			/*
			 * Both approaches (using min() and sorted().findFirst()) will give you the same result,
			 * but the min() method is more efficient since it doesn't require sorting the entire stream,
			 * only finding the minimum element. However, if you need a sorted list of employees for some other purpose,
			 * using sorted() might be more appropriate.
			 */
		 
		 Employee youngestMaleEmployeeInProductDevelopmentWrapper2  = employeeList.stream()
	             .filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Product Development"))
	             .min(Comparator.comparingInt(Employee::getAge))
	             .orElseThrow(() -> new EmployeeNotFoundException("No male employee found in Product Development"));
		
		 System.out.println(youngestMaleEmployeeInProductDevelopmentWrapper2);
		 System.out.println();
		
		 
		 //Most Working experience in the organization
		 Optional<Employee> seniorMostEmployeeWrapper = employeeList.stream()
		             .sorted(Comparator.comparingInt(Employee::getYearOfJoining))
		             .findFirst();
		             
		 Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();
		 System.out.println("Senior Most Employee in the Organization: ");
		 System.out.println(seniorMostEmployee);
		 System.out.println();
		 
		 
		 
		 //male and female employees in the sales and marketing team
		 Map<String, Long> countMaleFemaleEmployeesInSalesMarketing =  employeeList.stream()
				               .filter(e -> e.getDepartment().equals("Sales And Marketing"))
				               .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		 System.out.println("Male and Female in the sales and Marketing team: ");
		 System.out.println(countMaleFemaleEmployeesInSalesMarketing);
		 System.out.println();
		 
		 
		 
		 //Average salary of Male and Female Employee
		 Map<String,Double> avgSalaryOfMaleAndFemaleEmployees = employeeList.stream()
				               .collect(Collectors.groupingBy(Employee::getGender, 
				            		   Collectors.averagingDouble(Employee::getSalary)));
		 System.out.println("Average salary of Male and Female Employee: ");
		 System.out.println(avgSalaryOfMaleAndFemaleEmployees);
		 System.out.println();
		 
		 
		 
		 //List down the names of all employees in each department
		 Map<String,List<Employee>> employeeListByDepartment = employeeList.stream()
				                      .collect(Collectors.groupingBy(Employee::getDepartment));
		 System.out.println(employeeListByDepartment);
		 
			
			  for(Map.Entry<String,List<Employee>> entryEmployee : employeeListByDepartment.entrySet()) { 
				  
				  System.out.println(entryEmployee.getKey() + " : "+ entryEmployee.getValue());
			  
			  }
			  
			  //Average salary and Total salary of the whole organization
			// employeeList.stream()
			         
				 
		}

}
