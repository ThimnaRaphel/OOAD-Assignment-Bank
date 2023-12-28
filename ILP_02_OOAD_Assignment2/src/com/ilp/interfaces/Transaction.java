package com.ilp.interfaces;

import java.util.Scanner;

import com.ilp.entity.Account;

public interface Transaction {
	public Account depositMoney(Account account);
	public Account withdrawMoney(Account account);
//	Scanner scanner = new Scanner(System.in);
//	System.out.println("Enter the amount to be deposited ");
//	double depositMoney = scanner.nextDouble();
}
