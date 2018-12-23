package org.warn.tc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;





public class TemperatureConverter {
	
	//constants
	private final static String FARENHEIT_NAME = "Farenheit";
	private final static String CELCIUS_NAME = "Celcius";
	private final static String CONV1 = "FAR-CEL";
	private final static String CONV2 = "CEL-FAR";
	private final static String FAR_SYMBOL = "F";
	private final static String CEL_SYMBOL = "C";
	private final static String SWAP = "SWAP";
	private final static String EXIT = "EXIT";
	
	//instance variables
	private String currentConversionCode;
	private String currentConversionName;
	private String unit1Symbol;
	private String unit2Symbol;
	
	
	
	//constructor
	public TemperatureConverter() {
		currentConversionCode = CONV1;
		currentConversionName = FARENHEIT_NAME;
		unit1Symbol = FAR_SYMBOL;
		unit2Symbol = CEL_SYMBOL;
	}
	
	
	
	
	public static void main(String[] args) {
		TemperatureConverter tempConverter = new TemperatureConverter();
		tempConverter.runConv();
	}
	
	
	
	public void runConv() {
		
		System.out.println("=== Temperature Converter ===\n");
		System.out.println("If you want to swap the units, enter SWAP");
		System.out.println("If you want to close the application, enter EXIT");
		
		while(true) {
			String inputTemp = getUserInput();
			if( inputTemp.equalsIgnoreCase(EXIT) ) {
				System.out.println("\nGood Bye!");
				System.exit(0);
			}
			else if( inputTemp.equalsIgnoreCase(SWAP) ) {
				swapConv();
				continue;
			} else {
				String convertedTemp = convert(inputTemp, currentConversionCode);
				printResult( inputTemp, convertedTemp );
			}
		}
		
	}
	
	
	
	public String convert(String inputTemp, String conversionType) {
		
		NumberFormat nf1 = NumberFormat.getInstance();
		nf1.setMaximumFractionDigits(2);
		nf1.setMinimumFractionDigits(1);
		double convertedTemp = 0;
		
		try {
			if( conversionType.equals(CONV1) ) {
				convertedTemp = (5*(Double.parseDouble(inputTemp)-32))/9;
			}
			else if( conversionType.equals(CONV2) ) {
				convertedTemp = ((9*Double.parseDouble(inputTemp))/5)+32;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter a numrical value!");
			return null;
		}
		return String.valueOf(nf1.format(convertedTemp));
	}
	
	
	
	public String getUserInput() {
		
		try {
			System.out.print("\nPlease enter " + currentConversionName + " value: ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String inputTemp = in.readLine();
			return inputTemp;
		}
		catch(IOException e) {
			System.out.println("An error has ocurred! Please try again..");
			return null;
		}
		
	}
	
	
	
	public void printResult( String inputTemp, String convertedTemp ) {
	
		if(convertedTemp!=null) {
			System.out.println(inputTemp + unit1Symbol +" => " + convertedTemp + unit2Symbol);
		}
		
	}
	
	
	
	public void swapConv() {
		
		if( currentConversionCode.equals(CONV1) ) {
			currentConversionCode = CONV2;
			currentConversionName = CELCIUS_NAME;
			unit1Symbol = CEL_SYMBOL;
			unit2Symbol = FAR_SYMBOL;
			System.out.println("Conversion has been changed to Celcius => Farenheit");
		} else {
			currentConversionCode = CONV1;
			currentConversionName = FARENHEIT_NAME;
			unit1Symbol = FAR_SYMBOL;
			unit2Symbol = CEL_SYMBOL;
			System.out.println("Conversion has been changed to Farenheit => Celcius");
		}
	}
	
	
	
	public String getCurrentConversionCode() {
		return currentConversionCode;
	}

	public void setCurrentConversionCode(String currentConversionCode) {
		this.currentConversionCode = currentConversionCode;
	}




	public String getCurrentConversionName() {
		return currentConversionName;
	}

	public void setCurrentConversionName(String currentConversionName) {
		this.currentConversionName = currentConversionName;
	}




	public String getUnit1Symbol() {
		return unit1Symbol;
	}

	public void setUnit1Symbol(String unit1Symbol) {
		this.unit1Symbol = unit1Symbol;
	}




	public String getUnit2Symbol() {
		return unit2Symbol;
	}

	public void setUnit2Symbol(String unit2Symbol) {
		this.unit2Symbol = unit2Symbol;
	}
	

}
