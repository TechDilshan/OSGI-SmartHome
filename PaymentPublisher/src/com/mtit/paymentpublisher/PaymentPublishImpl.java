package com.mtit.paymentpublisher;

import java.util.ArrayList;
import java.util.Scanner;

public class PaymentPublishImpl implements PaymentPublish{

	@Override
	public void PaymentPublish() {
		
		System.out.println(".......Thank You......!!!");
		
	}

	
	public void ViewPaymentMethods() {
		
		ArrayList<String> payment = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		int value;
		
		System.out.println("============================\n");
		System.out.println("***Payment Methods of Available***");
		System.out.println("============================\n");
		
		payment.add("01. Card Payment");
		payment.add("02. Cash Payment");
		payment.add("03. BitCoin");
		payment.add("04. PayPal");
		
		for(int i = 0; i < payment.size(); i++) {
			System.out.println(payment.get(i));
		}
		
		System.out.print("\n");//keeping new line//
		
		System.out.println("----If You Want Exit----");
		System.out.println("Exit 99 OR -99");
		
		System.out.print("\n");
		
		System.out.print("input number here : ");
		value = input.nextInt();
		
		if(value == 99 || value == -99) {
			
			PaymentPublish();
		}
		else {
			System.out.println("--input correct number!!!--");
		}
		
	}

	
	public void PaymentDetails() {
		
		ArrayList<String> paymentDetails = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		String details;
		int count = 1;
		
		paymentDetails.add("Payment 01 - Card Number : 123456768 | Expiry Date: 10/23 | CVV code: 123 | Amount: 2300/=");
		paymentDetails.add("Payment 02 - Card Number : 247689987 | Expiry Date: 11/23 | CVV code: 456 | Amount: 450/=");
		paymentDetails.add("Payment 03 - Card Number : 456899008 | Expiry Date: 10/23 | CVV code: 789 | Amount: 2800/=");
		paymentDetails.add("Payment 04 - Card Number : 908777889 | Expiry Date: 09/23 | CVV code: 101 | Amount: 690/=");
		paymentDetails.add("Payment 05 - Card Number : 678946789 | Expiry Date: 17/23 | CVV code: 112 | Amount: 900/=");
		
		for (int i = 0; i < paymentDetails.size(); i++) {
		      System.out.println("0"+count+"."+paymentDetails.get(i));
		      count++;
		}
		System.out.println("\n");
		System.out.println("01.Add Payment Details");
		System.out.println("02.Update Payment Details");
		System.out.println("03.Delete Payment Details");
		System.out.println("04.Show Payment Details");
		System.out.println("****if Exit type 99****");
		
		System.out.print("--------------------\n");
		
		int num = -99;
		while(num == -99) {
			
			System.out.print("input number : ");
			int value = input.nextInt();
			
			if(value == 1 || value == 01) {
				System.out.print("input payment details : ");
				input.nextLine();
				details=input.nextLine();
				paymentDetails.add(details);
				
				System.out.println("pay details adding successfully...\n");
			}
			else if(value == 2 || value == 02) {
				
                int ce=01;
				
                for (int i = 0; i < paymentDetails.size(); i++) {
				      System.out.println(i+"."+paymentDetails.get(i));
				}
				
				System.out.print("\n Enter The Payment Number:"); 
				num = input.nextInt(); 
				
				System.out.print("\n input payment details : ");
				input.nextLine();
				details = input.nextLine();
				paymentDetails.set(num, details);
				
				System.out.println("\n Updated Payment Details\n"); 
				for (int i = 0; i < paymentDetails.size(); i++) {
				      System.out.println("0"+ce+"."+paymentDetails.get(i));
				      ce++;
				}
			}
            else if(value == 3 || value == 03) {
				
            	int ue = 01;
            	
            	for (int i = 0; i < paymentDetails.size(); i++) {
				      System.out.println(i+"."+paymentDetails.get(i));
				}
            	
            	System.out.print("enter the payment number for remove : ");
            	int number = input.nextInt();
            	
            	paymentDetails.remove(number);
            	
            	System.out.println(" \n ...payment details deleted sucessfully....!!! \n");
            	
            	
            	System.out.println("\n Updated Payment List\n"); 
				for (int i = 0; i < paymentDetails.size(); i++) {
				      System.out.println("0"+ue+"."+paymentDetails.get(i));
				      ue++;
				}
            	
            	
			}
            else if(value == 4 || value == 04) {
				
            	for (int i = 0; i < paymentDetails.size(); i++) {
      		      System.out.println("0"+count+"."+paymentDetails.get(i));
      		      count++;
      		    }
			}
			else if(value == 99) {
				return;
			}
			else {
				System.out.println("wrong number");
			}
			
			
			
		}
		
		
		
	}

	
	public void CalcTotal() {
		
		Scanner input = new Scanner(System.in);
		
		int size = 2;
		int num[] = new int[2];
		int total=0;
		
		for (int i = 0; i<num.length; i++) {
			
			
			System.out.print((i+1)+"." +  "Input number :");
			num[i] = input.nextInt();
			
		}
		
		for(int i = 0; i<num.length; i++) {
			
			total = total+num[i];
		}
		
		System.out.println("Total is :" + total);
		
	}
	
	public void Exit() {
		
		System.out.println("...Good Bye...!!!");
		
	}

	
}
