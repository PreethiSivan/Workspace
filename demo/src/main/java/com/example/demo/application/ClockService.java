package com.example.demo.application;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class ClockService {
	static String[] oneDigit = new String[] {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};	
	static String[] twoDigits = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

	public String getTime(String time) throws CustomException{		
		String[] timeArr = time.split(":");
		if(timeArr.length != 2 || timeArr[0].length() == 0 || timeArr[0].length() > 2
				|| timeArr[1].length() != 2 || Arrays.stream(timeArr).anyMatch(n->isNotNumeric(n))){
			throw new CustomException("Enter Proper time in 24 hours format. Sample: 08:24");			
		}
		
		String hours = timeArr[0];
		String mins = timeArr[1];
		String text = "It's ";
		
		if(Integer.parseInt(hours) == Integer.parseInt(mins))
			text = text.concat("midnight");
		else if(hours.equals("12") && mins.equals("00"))
			text = text.concat("midday");		
		else if(hours.length() == 1 && mins.length() == 1)
			text = text.concat(oneDigit[Integer.parseInt(hours)]).concat(" ").concat(oneDigit[Integer.parseInt(mins)]);
		else if(hours.startsWith("0"))
			text = text.concat(oneDigit[hours.charAt(1)-'0']).concat(" ").concat(getMinutes(mins));
		else if(hours.startsWith("1"))
			text = text.concat(twoDigits[hours.charAt(1)-'0']).concat(" ").concat(getMinutes(mins));
		else if(hours.startsWith("2"))
			text = text.concat("Twenty ").concat(oneDigit[hours.charAt(1)-'0']).concat(" ").concat(getMinutes(mins));		
		
		System.out.println("Text is "+text);
		return text;
	}
	
	public static boolean isNotNumeric(String strNum) {
	    if (strNum == null) {
	        return true;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return true;
	    }
	    return false;
	}
	
	public static String getMinutes(String mins) {
		String value = "";
		if(mins.contains("00"))
			return value;			
		char start = mins.charAt(0);
		
		switch(start) {
		case '0':
			value = oneDigit[mins.charAt(1)-'0'];
			break;
		case '1':
			value = twoDigits[mins.charAt(1)-'0'];
			break;
		case '2':
			value = (mins.charAt(1)=='0')?"Twenty":"Twenty ".concat(oneDigit[mins.charAt(1)-'0']);
			break;
		case '3':
			value = (mins.charAt(1)=='0')?"Thirty":"Thirty ".concat(oneDigit[mins.charAt(1)-'0']);
			break;
		case '4':
			value = (mins.charAt(1)=='0')?"Forty":"Forty ".concat(oneDigit[mins.charAt(1)-'0']);
			break;
		case '5':
			value = (mins.charAt(1)=='0')?"Fifty":"Fifty ".concat(oneDigit[mins.charAt(1)-'0']);
			break;			
		}
		return value;
	}
}
