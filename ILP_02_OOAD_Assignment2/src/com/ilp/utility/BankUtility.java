package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.BankService;

public class BankUtility {

	public static void main(String[] args) {

		ArrayList<Service> serviceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		Account account = null;
		Customer customer = null;
		ArrayList<Product> productList = new ArrayList<Product>();
		char again;
		int mainMenuChoice;
		do {
			System.out.println("1.Create Service\r\n" + "2.Create Product\r\n" + "3.Create Customer\r\n"
					+ "4.Manage Accounts\r\n" + "5.Display Customer\r\n" + "6.Exit\r\n" + "\r\n" + "Enter the choice");
			mainMenuChoice = scanner.nextInt();
			switch (mainMenuChoice) {
					case 1:
						serviceList.add(BankService.createService());
						break;
					case 2:
						productList = BankService.createProduct(serviceList,productList);
						for (Product product : productList) {
							System.out.println("The product is " + product.getProductName());
							System.out.println("The services of the product is ");
							System.out.println("Service Code\tService Name\tService rate");
							for(Service service : product.getServiceList()) {
								System.out.println(service.getServiceCode()+"\t\t"+service.getServiceName()+"\t\t"+service.getRate());
							}
						}
						break;
					case 3:
						customer = BankService.createCustomer(productList);
						break;
					case 4:
						BankService.manageAccounts(customer);
						break;
					case 5:
						BankService.displayDetails(customer);
						break;
					case 6:
						System.exit(0);
					default:
						break;
					}
			System.out.println("Do You Want to Continue (y/n) : ");
			scanner.nextLine();
			again = scanner.nextLine().charAt(0);
		} while (again == 'y');
	}
}
