package com.ilp.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class LoanAccount extends Product {
	private double chequeDeposit;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
	}

	@Override
	public Account depositMoney(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("The current account balance is "+account.getBalance());
		System.out.print("Enter amount to be deposited : ");
		double depositMoney = scanner.nextDouble();
		System.out.println("Which type of deposit? \n"
				+ "1. Cheque deposit \n"
				+ "2. Cash Deposit");
		int choice = scanner.nextInt();
		switch(choice) {
		case 1 :depositMoney= depositMoney - (depositMoney * chequeDeposit);
				account.setBalance(depositMoney+account.getBalance());
				
		case 2 :account.setBalance(depositMoney + account.getBalance());
		
		default :break;
		}
		System.out.println("The new balance is "+account.getBalance());
		return account;
	}

	@Override
	public Account withdrawMoney(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Cannot withdraw money from Loan Account");
		System.out.println("The current account balance is "+account.getBalance());
		return account;
	}

}
