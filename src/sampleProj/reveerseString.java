package sampleProj;

import java.lang.*;
import java.io.*;
import java.util.*;

public class reveerseString {

	public static void main(String[] args) {
		String stringInput ="Hello World";
		//get the STring length
		int iStrLength=stringInput.length();
		while(iStrLength >0)
		{
			System.out.print(stringInput.charAt(iStrLength -1));
			iStrLength--;
				}
    }

}
