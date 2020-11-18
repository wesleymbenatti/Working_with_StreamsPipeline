package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		//C://USERS//WESLEYMORAESBENATTI//DESKTOP//arquivo.txt
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(", ");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));

				line = br.readLine();
			}

			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			Comparator<String> comp = (e1, e2) -> e1.toUpperCase().compareTo(e2.toUpperCase());
			
			//emails recebendo a lista com todos os emails dos funcionários que tem o salário acima de 2000.00
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted(comp).collect(Collectors.toList());
			
			System.out.println("Emails of people whose salary is more than " + salary);
			emails.forEach(System.out::println);

			//sumSalary recebendo a soma dos salários de todos os funcionários que começam com a letra M
			double sumSalary = list.stream()
					.filter(s -> s.getName().charAt(0) == 'M')
					.map(s -> s.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.print("Sum of salary of people whose name stats with 'M': " + sumSalary );

			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		sc.close();

	}

}
