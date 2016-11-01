package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {
	
	
	public static void main(String[] args){
		
		new Test().run();
	}
	
	public void run(){
		String output = PEMDAS("15/(3*(3+2)) ");
		System.out.println(output);
	}
	
	//PEMDAS operation
	public String PEMDAS(String input){
		ArrayList<Double> num = new ArrayList();
		ArrayList<String> operand = new ArrayList();
		Double total = 0.0;
		
		System.out.println(input);
		
		//for parenthesis
		int count = 0;
		int temp1 = 0;
		int temp2 = 0;
		String temp3 = "";
		
		//this checks for parenthesis and does it with a recursive call
		//in case you have a parentheses inside another
		for(int i = 0; i < input.length(); i++){
			if(input.substring(i, i+1).equals("(") && count == 0){
				temp1 = i;
				count++;
			}else if(input.substring(i, i+1).equals(")")&&count == 1){
				temp2 = i;
				temp3 = PEMDAS(input.substring(temp1+1,temp2)+" ");
				input = input.substring(0, temp1) + temp3 + input.substring(temp2+1, input.length());
			}else if(input.substring(i, i+1).equals("(") && count> 0){
				count++;
			}else if(input.substring(i, i+1).equals(")")&&count > 1){
				count--;
			}
		}
		
		num = findNumber(input);
		operand = findOperations(input);
		
		/*//for parenthesis
		int count = 0;
		int temp = 0;
		int temp2 = 0;
		
		for(int i = 0; i<operand.size();i++){
			if(operand.get(i).equals("(")){
				temp = i;
			}else if(operand.get(i).equals(")")){
				temp2 = i;
				PEMDAS("");
			}
		}
		*/
		
		//second loop to do powers
		for(int i = 0; i< operand.size(); i++){
			if(operand.get(i).equals("^")){
				total = Math.pow(num.get(i),num.get(i+1));
				num.set(i, total);
				num.remove(i+1);
				operand.remove(i);
				i--;
			}
		}
		
		//first loop to do multiplication and division
		for(int i = 0; i < operand.size(); i++){
			//this is multiplication
			if(operand.get(i).equals("*")){
				total = num.get(i);//inserts first number
				total *=num.get(i+1);//Multiply's it by second number
				num.set(i, total);//sets the the new number into the array list
				num.remove(i+1);//Deletes the second number to keep everything in order
				operand.remove(i);//removes the operator from the operator list
				i--;//this is to make sure we go through every operator as the operators were shifted down 1
			//This is division
			}else if(operand.get(i).equals("/")){
				total = num.get(i);//inserts first number
				total /=num.get(i+1);//Divides it by the second number
				num.set(i, total);//sets the the new number into the array list
				num.remove(i+1);//Deletes the second number to keep everything in order
				operand.remove(i);//removes the operator from the operator list
				i--;//this is to make sure we go through every operator as the operators were shifted down 1
			}
		}
		
		
		for(int i = 0; i<operand.size(); i++){
			if(operand.get(i).equals("+")){
				total = num.get(i);//inserts first number
				total +=num.get(i+1);//add's it to the second number
				num.set(i, total);//sets the the new number into the array list
				num.remove(i+1);//Deletes the second number to keep everything in order
				operand.remove(i);//removes the operator from the operator list
				i--;//this is to make sure we go through every operator as the operators were shifted down 1
			}else if(operand.get(i).equals("-")){
				total = num.get(i);//inserts first number
				total -=num.get(i+1);//subtracts it from the second number
				num.set(i, total);//sets the the new number into the array list
				num.remove(i+1);//Deletes the second number to keep everything in order
				operand.remove(i);//removes the operator from the operator list
				i--;//this is to make sure we go through every operator as the operators were shifted down 1
			}
		}
		String output = Double.toString(num.get(0));
		return output;
	}
	
	
	//this method returns a list of number is the order found
		public ArrayList<Double> findNumber(String input){
			ArrayList<Double> numbers = new ArrayList();
			for(int i = 0; i< input.length(); i++){
				int count = 1;
				if(isNumber(input.substring(i, i+1))){
					for(int j = i+1; j < input.length()+1; j++){
						if(isNumber(input.substring(j, j+1))){
							count++;
						}else{ 
						numbers.add(Double.parseDouble(input.substring(i, i+count)));
						i +=count;
						break;
						}
					}
				}
			}
			
			return numbers;
		}
	
	//this method returns a list of operations in the order found
	public ArrayList<String> findOperations(String input){
		ArrayList<String> operations = new ArrayList();
		for(int i = 0; i < input.length(); i++){
			if(isOperation(input.substring(i, i+1))){
				operations.add(input.substring(i, i+1));
			}
		}
		return operations;
	}
	
	//this method tell if a string is a number
	public boolean isNumber(String s){
		String[] allNumbers = {"0","1","2","3","4","5","6","7","8","9"};
		for(String x: allNumbers){
			if(x.compareTo(s)== 0){
				return true;
			}
		}
		return false;
	}
	
	//this method tells if a string is an operation
	public boolean isOperation(String s){
		String[] allOperations = {"+","-","*","/","^","(",")"};
		for(String x: allOperations){
			if(x.compareTo(s)==0){
				return true;
			}
		}
		return false;
	}
	
}