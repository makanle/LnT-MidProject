package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	Scanner scan = new Scanner(System.in);	
	int supcount = 0;
	int mgrcount = 0;
	int admcount = 0;
	int empcount = 0;
	ArrayList<Employee> emplist = new ArrayList<Employee>();
	
	public Main() {
		while(true) {
			System.out.println("Pilih Menu");
			System.out.println("1. Tambah Karyawan");
			System.out.println("2. Tampilkan Daftar Karyawan");
			System.out.println("3. Ubah data Karyawan");
			System.out.println("4. Hapus data Karyawan");
			System.out.println("5. Keluar");
			System.out.print("> ");
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					addemp(emplist);
					break;
				case 2:
					listview(emplist);
					break;
				case 3:
					update(emplist);
					break;
				case 4:
					remove(emplist);
					break;
				case 5:
					return;
			}
		}
	}
	
	public void addemp(ArrayList<Employee> A){
		String name;
		String gend; 
		String job;
		String id;
		while(true) {
			
			System.out.print("Input Nama Karyawan [>=3]: ");
			name = scan.next();
			if(name.length() < 3) {
				System.out.println("name is less than 3 characters");
			} else {
				break;
			}
		}
		while(true) {
			System.out.print("Input Jenis Kelamin [Laki-laki | Perempuan] (Case Sensitive): ");	
			gend = scan.nextLine();
			if(!(gend.equals("Laki-laki")) || !(gend.equals("Perempuan") )) {
				System.out.println("invalid input");
			} else {
				break;
			}
		}
		while(true) {
			System.out.print("Input Jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			job = scan.nextLine();
			if(!(job.equals("Manager")) || !(job.equals("Supervisor")) || !(job.equals("Admin"))) {
				System.out.println("invalid input");
			} else {
				break;
			}
		}
		
		id = genid();
		emplist.add(new Employee(empcount, name, gend, job, id));
		empcount++;
		System.out.println("Berhasil menambahkan karyawan dengan id " + id);
		if(job.equals("Manager")) {
			mgrcount++;
		}else if(job.equals("Supervisor")) {
			supcount++;
		}else if(job.equals("Admin")) {
			admcount++;
		}
		checkBonus(job);
	}
	
	public void listview(ArrayList<Employee> A) {
		mergeSort(A, 0, A.size()); // sort by name every time the menu is chosen
		System.out.println("|-----|---------------------|" + padding("", maxpad(emplist), '-', true) + "|---------------|-------------|---------------|");
		System.out.println("|No   |Kode Karyawan        |" + padding("Nama Karyawan", maxpad(emplist), ' ', true) + "|Jenis Kelamin  |Jabatan      |Gaji Karyawan  |");
		System.out.println("|-----|---------------------|" + padding("", maxpad(emplist), '-', true) + "|---------------|-------------|---------------|"); 
		
		int length = emplist.size();
		if(length == 0) {
			return;
		}
		
		for(int i=0 ; i<length ; i++) {
			System.out.printf("|" + padding(Integer.toString(i), 5, ' ', false), "|" + padding(emplist.get(i).getName(), maxpad(emplist), ' ', false) + "|" + padding(emplist.get(i).getgend(), 15, ' ', false) + padding(emplist.get(i).getJob(), 13, ' ', false) + "|" + padding(Double.toString( emplist.get(i).getSalary() ), 15, ' ', false) + "|%n");
		}
	}
	
	public int maxpad(ArrayList<Employee> A) {
		int length = emplist.size();
		if (length == 0) {
			return 20;
		}
		int maxLength = emplist.get(0).getName().length();
		if (maxLength  <= 20) {
			maxLength = 20;
		}
		for(int i = 1 ; i < length ; i++) {
			maxLength = Math.max(maxLength, emplist.get(i).getName().length());
		}
		
		return maxLength;
	}
	
	public String padding(String str, int width, char pad, boolean ljust) {
		int padcount = width - str.length();
		if(padcount <= 0) {
			return str;
		}
		StringBuilder paddedstr = new StringBuilder();
		for(int i = 0 ; i < padcount ; i++) {
			paddedstr.append(pad);
		}
		if(ljust) {
			return str + paddedstr.toString();
		} else {
			return paddedstr.toString() + str;
		}
	}
	
	public void update(ArrayList<Employee> A) {
		String name;
		String gend; 
		String job;
		String input;
		int index;
		System.out.print("Input nomor Karyawan yang ingin diupdate: ");
		index = scan.nextInt();
		if(index == 0) {
			return;
		}
		Employee p1 = emplist.get(index - 1);
		name = emplist.get(index - 1).getName();
		gend = emplist.get(index - 1).getgend();
		job = emplist.get(index - 1).getJob();
		// subtract the count first
		if(job.equals("Manager")) {
			mgrcount--;
		}else if(job.equals("Supervisor")) {
			supcount--;
		}else if(job.equals("Admin")) {
			admcount--;
		}
		
		while(true) {
		
			System.out.print("Input Nama Karyawan [>=3]: ");
			input = scan.nextLine();
			if(input.equals("0")) {
				break;
			}
			else if(name.length() < 3) {
				System.out.println("name is less than 3 characters");
			} else {
				name = input;
				break;
			}
		}
		while(true) {
			System.out.print("Input Jenis Kelamin [Laki-laki | Perempuan] (Case Sensitive): ");	
			input = scan.nextLine();
			if(input.equals("0")) {
				break;
			}
			else if(!(gend.equals("Laki-laki")) || !(gend.equals("Perempuan")) ) {
				System.out.println("invalid input");
			} else {
				gend = input;
				break;
			}
		}
		while(true) {
			System.out.print("Input Jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			input = scan.nextLine();
			if(input.equals("0")) {
				break;
			}
			else if(!(job.equals("Manager")) || !(job.equals("Supervisor")) || !(job.equals("Admin"))) {
				System.out.println("invalid input");
			}else {
				job = input;
			}
			
			if(job.equals("Manager")) {
				mgrcount++;
				break;
			}else if(job.equals("Supervisor")) {
				supcount++;
				break;
			}else if(job.equals("Admin")) {
				admcount++;
				break;
			}
		}
		
		p1.setName(name);
		p1.setgend(gend);
		p1.setJob(job);
		System.out.println("Berhasil mengupdate karyawan dengan id " + p1.getId());
	}
	
	public void remove(ArrayList<Employee> emplist) {
		int choice;
		System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
		choice = scan.nextInt();
		if(choice == 0) {
			System.out.println("Tidak ada Karyawan yang dihapus");
			return;
		} else {
			emplist.remove(choice - 1);
		}
		empcount--;
		
		// reorder the creation date
		mergeSort2(emplist, 0, emplist.size() - 1);
		for(int i=0 ; i<emplist.size() ; i++) {
			emplist.get(i).setOrder(i);
		}
	}
	
	public String genid() {
		StringBuilder id = new StringBuilder(8);
		String Alphstr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Numstr = "0123456789";
		for(int i=0 ; i<=1 ; i++) {
			int index = (int) (26 * Math.random());
			id.append(Alphstr.charAt(index));
		}
		id.append("-");
		for(int i=3 ; i<=6 ; i++) {
			int index = (int) (26 * Math.random());
			id.append(Numstr.charAt(index));
		}
		
		return id.toString();
	}
	
	public void checkBonus(String job) {
		mergeSort2(emplist, 0, emplist.size() - 1);
		int counter = 0;
		if(job.equals("Manager")) {
			if(mgrcount % 3 != 1) {
				return;
			}
			System.out.print("Bonus Sebesar 10% telah diberikan kepada karyawan dengan id ");
			int amnt = (int)Math.floor(mgrcount / 3); 
			for(int i = 0 ; i < emplist.size() ; i++) {
				if(emplist.get(i).getJob().equals(job) && counter <= amnt){
					double salary = emplist.get(i).getSalary();
					emplist.get(i).setSalary(salary + (salary * 0.1));
					System.out.println(emplist.get(i).getId());
					amnt++;
				}
				 
			}
		}
		else if(job.equals("Supervisor")) {
			if(supcount % 3 != 1) {
				return;
			}
			int amnt = (int)Math.floor(supcount / 3);
			for(int i = 0 ; i < emplist.size() ; i++) {
				if(emplist.get(i).getJob().equals(job) && counter <= amnt){
					double salary = emplist.get(i).getSalary();
					emplist.get(i).setSalary(salary + (salary * 0.075));
					System.out.println(emplist.get(i).getId());
					amnt++;
				}
				
			}
		}
		else if(job.equals("Admin")) {
			if(admcount % 3 != 1) {
				return;
			}
			int amnt = (int)Math.floor(admcount / 3);
			for(int i = 0 ; i < emplist.size() ; i++) {
				if(emplist.get(i).getJob().equals(job) && counter <= amnt){
					double salary = emplist.get(i).getSalary();
					emplist.get(i).setSalary(salary + (salary * 0.05));
					System.out.println(emplist.get(i).getId());
					amnt++;
				}
			}
		}
	}
	/*
	public void calcBonus(ArrayList<Employee> A, int index)	{
		double salary = A.get(index).getSalary();
		A.get(index).setSalary(salary + (int)(salary * 0.1));
	}
	*/
	// to sort by name
	
	public void merge(ArrayList<Employee> A, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
		
		ArrayList<Employee> L = new ArrayList<>();
		ArrayList<Employee> R = new ArrayList<>();
		
		for(int i=0 ; i<n1 ; i++) {
			L.set(i, A.get(left + i));
		}
		for(int j=0 ; j<n2 ; j++) {
			R.set(j, A.get(mid + j + 1));
		}
		
		
		int i = 0;
		int j = 0;
		int k = left;
		
		while (i < n1 && j < n2) {
			String s1 = L.get(i).getName();
			String s2 = R.get(j).getName();
			if(s1.compareTo(s2) < 0){
				A.set(k, L.get(i));
				i++;
			} else {
				A.set(k, R.get(j));
				j++;
			}
			k++;
		}
		
		while ( i < n1) {
			A.set(k, L.get(i));
			k++;
		}
		while ( j < n2) {
			A.set(k, R.get(j));
			k++;
		}
		
	}
	
	public void mergeSort(ArrayList<Employee> A, int left, int right) {
	    if (left < right) {
	        int mid = left + (right - left) / 2;
	        mergeSort(A, left, mid);
	        mergeSort(A, mid + 1, right);
	        merge(A, left, mid, right);
	    }
	}
	
	// to sort by creation
	
	public void merge2(ArrayList<Employee> A, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
		
//		ArrayList<Employee> L = new ArrayList<>(A.subList(left, mid));
//		ArrayList<Employee> R = new ArrayList<>(A.subList(mid, right));
		
		ArrayList<Employee> L = new ArrayList<>();
		ArrayList<Employee> R = new ArrayList<>();
		
		for(int i=0 ; i<n1 ; i++) {
			L.set(i, A.get(left + i));
		}
		for(int j=0 ; j<n2 ; j++) {
			R.set(j, A.get(mid + j + 1));
		}
		
		
		int i = 0;
		int j = 0;
		int k = left;
		
		while (i < n1 && j < n2) {
//			int s1 = L.get(i).getOrder();
//			int s2 = R.get(j).getOrder();
			if(L.get(i).getOrder() < R.get(j).getOrder()){
				A.set(k, L.get(i));
				i++;
			} else {
				A.set(k, R.get(j));
				j++;
			}
			k++;
		}
		
		while ( i < n1) {
			A.set(k, L.get(i));
			k++;
		}
		while ( j < n2) {
			A.set(k, R.get(j));
			k++;
		}
		
	}
	
	public void mergeSort2(ArrayList<Employee> A, int left, int right) {
	    if (left < right) {
	        int mid = left + (right - left) / 2;
	        mergeSort2(A, left, mid);
	        mergeSort2(A, mid + 1, right);
	        merge2(A, left, mid, right);
	    }
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
