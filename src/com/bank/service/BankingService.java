package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import user.bank.userdata.Banking_Usersdata;

public class BankingService {
    List<Banking_Usersdata> userStorage = new ArrayList<Banking_Usersdata>();
	Scanner sc = new Scanner(System.in);
//	int pin = 1111;
//	Long acctNo = 12345678l;
//	Long balance = 100l;

	public void welcomepage() {
		System.out.println("Welcome to Maddy Bank");
		initialOptions();
	}

	public void initialOptions() {
		System.out.println("1. Create Account");
		System.out.println("2. Login Account");
		int op = sc.nextInt();
		if (op == 1) {
			createAccount();
		} else if (op == 2) {
			loginAccount();
		} else {
			initialOptions();
		}
	}

	private void createAccount() {
		
		Banking_Usersdata user = new Banking_Usersdata();
		System.out.println("Enter Your New User Name");
		sc.nextLine();
		String name = sc.nextLine();
		user.setName(name);
		Long acctNo = randomNumbers();
		user.setAccountNumber(acctNo);
		System.out.println("Enter Your Address");
		
		
		String address = sc.nextLine();
		user.setAddress(address);
		System.out.println("Enter Your Phone Number");
		long phn = sc.nextLong();
		user.setPhoneNumber(phn);
		System.out.println("Enter Your New PIN Number");
		int pin = sc.nextInt();
		user.setPinNumber(pin);
		System.out.println("Enter Your Opening Balance Amount");
		
		long balance = sc.nextLong();
		user.setBalance(balance);
		userStorage.add(user);
		System.out.println("Hellow "+user.getName()+" Your Account Created Successful");
		System.out.println("Your Account Details");
		System.out.println("Name: "+user.getName());
		System.out.println("Unique Account Number: "+ user.getAccountNumber());
		System.out.println("PIN Number: "+ user.getPinNumber());
		System.out.println("Address: "+user.getAddress());
		System.out.println("Phone Number: "+user.getPhoneNumber());
		System.out.println("Balance: "+user.getBalance());
		welcomepage();
		
		
		
		
		

	}

	private void loginAccount() {
		System.out.println("Enter Your 8 Digit Account Number");
		long acctno = sc.nextLong();
		List<Banking_Usersdata> list = userStorage.stream()
				.filter(user -> user.getAccountNumber().equals(acctno))
				.collect(Collectors.toList());
		
//		Banking_Usersdata user = null;
//		for(int  i = 0; i < list.size(); i++) {
//			if(userStorage.get(i).getAccountNumber().equals(acctno)) {
//				user = userStorage.get(i);
//			}
//		}
		
//		for(Banking_Usersdata user1: userStorage) {
//			if(user1.getAccountNumber().equals(acctno)) {
//				user = user1;
//			}
//		}
		if (list.isEmpty()) {
			System.out.println("User Not Found");
			loginAccount();
		}else {
			
			Banking_Usersdata user = list.get(0);
		
		if(Objects.nonNull(user)) {
			System.out.println("Enter your 4 Digit PIN Number");
			int pin = sc.nextInt();
			if (pin == user.getPinNumber()) {
				homePage(user);
			} else {
				System.out.println("Incorrect PIN Number");
				loginAccount();
			}

		} else {
			System.out.println("Please Enter Correct Account Number");
			loginAccount();
		}
		}
	}

	private void homePage(Banking_Usersdata user) {
		System.out.println("----Welcome To Home Page----");
		System.out.println("1. View Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. View User Details");
		System.out.println("5. Logout");

		homePageOptions(user);

	}

	private void homePageOptions(Banking_Usersdata user) {
		System.out.println("Enter Option");
		int option = sc.nextInt();
		if (option == 1) {
			System.out.println("Your Account Balance is " + user.getBalance() );
			System.out.println("Click 1 For Go To Home Page");
			int exit = sc.nextInt();
			if (exit == 1) {
				homePage(user);
			}
		} else if (option == 2) {
			System.out.println("Enter Your Deposit Amount");
			long deposit = sc.nextLong();
			System.out.println("RS " + deposit + " is Deposited Successful");
			Long balance = deposit + user.getBalance();
			user.setBalance(balance);
			System.out.println("Your Total Balance is "+ user.getBalance());
			System.out.println("Click 1 For Go To Home Page");
			int exit = sc.nextInt();
			if (exit == 1) {
				homePage(user);
			}
		} else if (option == 3) {
			System.out.println("Enter Your Withdraw Amount");
			long withdraw = sc.nextLong();
			if (withdraw <= user.getBalance()) {
			System.out.println("RS " + " is Withdraw Successful");
			Long balance=user.getBalance()-withdraw;
			user.setBalance(balance);
			System.out.println("Your Total Balance is "+ balance);
			}else {
				System.out.println("Insufficient Balance");
				homePage(user);
			}
			
			System.out.println("Click 1 For Go To Home Page");
			int exit = sc.nextInt();
			if (exit == 1) {
				homePage(user);
			}

		}else if (option == 4) {
			System.out.println("Your Account Details");
			System.out.println("Name: "+user.getName());
			System.out.println("Account Number: "+ user.getAccountNumber());
			System.out.println("PIN Number: "+ user.getPinNumber());
			System.out.println("Address: "+user.getAddress());
			System.out.println("Phone Number: "+user.getPhoneNumber());
			System.out.println("Balance: "+user.getBalance());
			System.out.println("Click 1 For Go To Home Page");
			int exit = sc.nextInt();
			if (exit == 1) {
				homePage(user);
			}
		}else if(option == 5) {
			System.out.println("Logout Successful");
			welcomepage();
		}else {
			System.out.println("Select Correct Option");
			homePage(user);
		}
	}
	public Long randomNumbers() {
        Random random = new Random();
        Integer in = random.ints(1, 10000000, 99999999).findFirst().getAsInt();
		return in.longValue();
		
	}
	
}
