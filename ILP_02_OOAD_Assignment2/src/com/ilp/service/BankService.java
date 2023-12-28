package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankService {
	
	public static Service createService() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Service code: ");
		String serviceCode=scanner.nextLine();
		System.out.println("Enter the Service name: ");
		String serviceName=scanner.nextLine();
		System.out.println("Enter the Service rate: ");
		double serviceRate=scanner.nextDouble();
		Service service = new Service(serviceCode,serviceName,serviceRate);
		return service;
}
	@SuppressWarnings("resource")
	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList,ArrayList<Product> productList) {
		ArrayList<Service> newServiceList = new ArrayList<Service>();
		char serviceChoice = 0;
		char choice=0;
		Scanner scanner = new Scanner(System.in);
		do {
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("Enter the Product code: ");
		String productCode=scanner1.nextLine();
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("Enter the Product name: ");
		String productName=scanner2.nextLine();
		for (Service service : serviceList) {
			System.out.println(service.getServiceCode() + " " + service.getServiceName());
		}
		do {
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("Enter the code of service you want to add");
		String code = scanner3.nextLine();
		for (Service service : serviceList) {
			if(service.getServiceCode().equalsIgnoreCase(code)) {
				newServiceList.add(service);
			}
		}
		Scanner scanner4 = new Scanner(System.in);
		System.out.println("Do you want to add more services? ");
		serviceChoice=scanner4.next().charAt(0);
	}while(serviceChoice=='y');
			if(productName.equalsIgnoreCase("Savings Max Account")) {
				SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(productCode,productName,newServiceList);
				productList.add(savingsMaxAccount);
			}
			else if(productName.equalsIgnoreCase("Loan Account")) {
				LoanAccount loanAccount = new LoanAccount(productCode,productName,newServiceList);
				productList.add(loanAccount);
			}
			else if(productName.equalsIgnoreCase("Current Account")) {
				CurrentAccount currentAccount = new CurrentAccount(productCode,productName,newServiceList);
				productList.add(currentAccount);
			}
		System.out.print("Do You Want To Add More Products (y/n) : ");
		choice = scanner.next().charAt(0);
		}while(choice=='y');
		return productList;
}
	public static Account createAccount(Product product) {
		Scanner scanner = new Scanner(System.in);
		String accountType;
		System.out.println("Enter the Account Number ");
		String accountNo = scanner.nextLine();
		System.out.println("Enter the balance ");
		double balance = scanner.nextDouble();
		accountType = product.getProductName();
	    Account account = new Account(accountNo,accountType,balance,product);
	    return account;
	}
	public static Customer createCustomer(ArrayList<Product> productList) {
		Scanner scanner = new Scanner(System.in);
		char flag;
		System.out.println("*****************Create a new Account*************** ");
		System.out.println("Enter customer code ");
		String customerCode = scanner.nextLine();
		System.out.println("Enter customer name ");
		String customerName = scanner.nextLine();
		ArrayList<Account> accountList = new ArrayList<Account>();
		Customer customer = new Customer(customerCode,customerName,accountList);
		System.out.println("************Products Available*********** ");
		for(Product product  : productList) {
			System.out.println(product.getProductCode()+" "+product.getProductName());
		}
		do {
			System.out.println("Select the product code to add as account: ");
			String productChoice=scanner.nextLine(); 
			boolean valid = false;
			while(!valid){
			for(Product product : productList) {
				if (product.getProductCode().equalsIgnoreCase(productChoice)) {
					valid = true;
					Account newAccount = BankService.createAccount(product);
					accountList.add(newAccount);
					System.out.println(product.getProductName()+" created for "+customerName);
					System.out.println("Account is active.!!!!!! ");
					System.out.println("You have the following services :");
					for(Service service : product.getServiceList()) {
						System.out.println("    "+ service.getServiceName());
					}
					break;
				}
			}
			if (!valid) {
		        System.out.println("Please select a valid product code");
		        // Prompt the user for input again
		        System.out.println("Select the product code to add as an account: ");
		        productChoice = scanner.nextLine();
		    }
			}		
			System.out.println("Do You Want to add more accounts? (y/n) : ");
			flag = scanner.nextLine().charAt(0);
		}while(flag=='y');
			return customer;
}
	
	public static void manageAccounts(Customer customer) {
		int choice=0;
		char repeatChoice=0;
		String newAccountCode;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter customer code ");
		String customerCode = scanner.nextLine();
		System.out.println(customer.getCustomerName()+" has the following accounts");
		for (Account account : customer.getAccountList()) {
			System.out.println(account.getAccountType());
		}
		System.out.println("Enter the account: ");
		String accountChoice = scanner.nextLine();
		do {
			System.out.println("1. Deposit   2. Withdraw   3.Display Balance  4.Create Account");
			System.out.println("Enter your choice ");
			choice = scanner.nextInt();
			for (Account account : customer.getAccountList()) {
				if(account.getAccountType().equalsIgnoreCase(accountChoice)) {
			switch(choice) {
			case 1 :account.getProduct().depositMoney(account);
					break;
			case 2 :account.getProduct().withdrawMoney(account);
					break;
			case 3 :System.out.println("Account Number : "+account.getAccountNo());
			 		System.out.println("Account Balance : "+account.getBalance());
			 		break;
			default :break;
			}
			}
			}
			System.out.print("Do You Want To Change More (y/n) : ");
			repeatChoice=scanner.next().charAt(0);
		}while(repeatChoice=='y');
		
	}
	public static void displayDetails(Customer customer) {
		System.out.println("*************Customer Details**************");
		System.out.println("Customer Code : " + customer.getCustomerCode());
		System.out.println("Customer Name : " + customer.getCustomerName());
		for(Account account : customer.getAccountList()) {
			displayDetails(account);
		}
	}
	public static void displayDetails(Account account) {
			System.out.println("Account Number : " + account.getAccountNo());
			System.out.println("Account Name : " + account.getAccountType());
			System.out.println("Account Balance : " + account.getBalance());
			displayDetails(account.getProduct());
	}
	public static void displayDetails(Product product) {
		System.out.println("Product Code : " + product.getProductCode());
		System.out.println("Product Name : " + product.getProductName());
		displayDetails(product.getServiceList());
	}
	public static void displayDetails(ArrayList<Service> newserviceList) {
		for(Service service : newserviceList) {
			System.out.println("Service Code : " + service.getServiceCode());
			System.out.println("Service Name : " + service.getServiceName());
			System.out.println("Service Rate : " + service.getRate());
		}
	}
}

	

