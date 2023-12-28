package com.ilp.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class CurrentAccount extends Product {

	public CurrentAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
	}

	@Override
	public Account depositMoney(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("The current account balance is "+account.getBalance());
		System.out.print("Enter amount to be deposited : ");
		double depositMoney = scanner.nextDouble();
		account.setBalance(depositMoney+account.getBalance());
		System.out.println("The new balance is "+account.getBalance());
		return account;
		
	}

	@Override
	public Account withdrawMoney(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("The current account balance is "+account.getBalance());
		System.out.print("Enter amount to be withdrawn : ");
		double withdrawMoney = scanner.nextDouble(); 
		account.setBalance(account.getBalance()-withdrawMoney);
		System.out.println("Amount successfully withdrawn");
		System.out.println("The new balance is "+account.getBalance());
		return account;
		
	}

}
