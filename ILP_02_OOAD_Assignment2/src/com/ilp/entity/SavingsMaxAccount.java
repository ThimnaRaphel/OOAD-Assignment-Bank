package com.ilp.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class SavingsMaxAccount extends 	Product {
	private double minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
	}

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
	System.out.print("Enter amount to be withdrawn : ");
	double withdrawMoney = scanner.nextDouble();
	if(withdrawMoney>account.getBalance()) {
		System.out.println("Sorry!!!!!!!!!!!!! A mininmum balance of Rs 1000 should be mainted in the account. ");
	}
	else if(account.getBalance()-withdrawMoney<1000) {
		System.out.println("Sorry!!!!!!!!!!!!! A mininmum balance of Rs 1000 should be mainted in the account. ");
	}
	else {
		account.setBalance(account.getBalance()-withdrawMoney);
		System.out.println("Amount successfully withdrawn");
	}
	System.out.println("The new balance is "+account.getBalance());
	return account;
	
}
}
