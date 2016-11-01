package calculator;

import java.util.ArrayList;
import java.util.Iterator;

public class Calculate {
	public static void main(String[] args){
		Calculate first = new Calculate();
		first.addSubtract("50+3-25");
	}
	
	public String addSubtract(String input){
		Calculate cal = new Calculate();
		ArrayList<Double> numbers = new ArrayList();
		ArrayList<Character> operations = new ArrayList();
		numbers = cal.findNumbers(input);
		operations = cal.findAddsSubtracts(input);
		
		for(int i = 0; i<operations.size(); i++){
			if(operations.indexOf(i)=='+'){
				numbers.indexOf(i) + numbers.indexOf(i+1);
			}else{
				
			}
		}
		
		return"";
	}
	
	//Finds the operations + and -
	public ArrayList<Character> findAddsSubtracts(String input){
		ArrayList<Character> output = new ArrayList();
		int counter = 0;
		for(int i=0; i< input.length(); i++){
			if (input.substring(i) == "+" || input.substring(i)== "-"){
				output.add(input.charAt(i));
			}
		}
		return output;
	}
	
	//Finds the numbers
	public ArrayList<Double> findNumbers(String input){
		ArrayList<Double> output = new ArrayList();
		int counter = 0;
		for(int i=0; i< input.length(); i++){
			if (input.substring(i) == "+" || input.substring(i)== "-"){
				output.add(Double.parseDouble(input.substring(i)));
			}
		}
		return output;
	}
}