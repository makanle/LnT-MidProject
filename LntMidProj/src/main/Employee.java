package main;

public class Employee {
	public String name;
	public String gend;
	public String id;
	public String job;
	public double salary;
	public int order;
	public int salmult;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getgend() {
		return gend;
	}


	public void setgend(String gend) {
		this.gend = gend;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double d) {
		this.salary = d;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}

	public int getSalmult() {
		return salmult;
	}


	public void setSalmult(int salmult) {
		this.salmult = salmult;
	}


	public Employee(int count, String name2, String gend2, String job2, String id) {
		// TODO Auto-generated constructor stub
		this.order = count;
		this.name = name2;
		this.gend = gend2;
		this.job = job2;
		this.id = id;
	}
}
