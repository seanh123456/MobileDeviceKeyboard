package com.hutch.keyboard.mobile_device;

import java.io.IOException;

import com.hutch.keyboard.autocomplete.Autocomplete;

public class MobileDevice {
	private Autocomplete autocomplete;
	
	private MobileDevice() {
		autocomplete = new Autocomplete();
		System.out.println("Train -> \"The third thing that I need to tell you is that this thing does not think thoroughly.\"");
		autocomplete.addSentence("The third thing that I need to tell you is that this thing does not think thoroughly.");
		System.out.println("getWords(\"thi\"): \t" + autocomplete.getWords("thi").toString());
		System.out.println("getWords(\"nee\"): \t" + autocomplete.getWords("nee").toString());
		System.out.println("getWords(\"th\"): \t" + autocomplete.getWords("th").toString());
		System.out.println("Press <Enter> to exit.");
		try {
			System.in.read();
		} catch (IOException e) {
			// exit
		}
	}
	
	public static void main(String[] args) {
		new MobileDevice();
	}
}
