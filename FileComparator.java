//File Comparator

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileComparator {
	public static void main(String[] args) {
		System.out.println("* File Comparator *");
		if (args.length != 0 && args.length != 2) {
			error("This program's usage is as follows:\n" +
					"java FileComparator\n" +
					"java FileComparator <file 1 location> <file 2 location>");
		}
		Scanner input = new Scanner(System.in);
		String file1Location;
		if (args.length == 0) {
			System.out.print("Enter file 1's location: ");
			file1Location = input.nextLine();
		} else {
			file1Location = args[0];
		}
		FileInputStream inputStream1 = null;
		try {
			inputStream1 = new FileInputStream(file1Location);
		} catch (Exception e) {
			error(file1Location + " is not a valid file.");
		}
		String file2Location;
		if (args.length == 0) {
			System.out.print("Enter file 2's location: ");
			file2Location = input.nextLine();
		} else {
			file2Location = args[1];
		}
		FileInputStream inputStream2 = null;
		try {
			inputStream2 = new FileInputStream(file2Location);
		} catch (Exception e) {
			error(file2Location + " is not a valid file.");
		}
		input.close();
		int token1;
		ArrayList<Integer> file1 = new ArrayList<>();
		do {
			try {
				token1 = inputStream1.read();
			} catch (Exception e) {
				token1 = -1;
			}
			if (token1 >= 0 && token1 <= 255) {
				file1.add(token1);
			}
		} while (token1 >= 0 && token1 <= 255);
		try {
			inputStream1.close();
		} catch (Exception e) {
			error("Unable to close inputStream1.");
		}
		int token2;
		ArrayList<Integer> file2 = new ArrayList<>();
		do {
			try {
				token2 = inputStream2.read();
			} catch (Exception e) {
				token2 = -1;
			}
			if (token2 >= 0 && token2 <= 255) {
				file2.add(token2);
			}
		} while (token2 >= 0 && token2 <= 255);
		try {
			inputStream2.close();
		} catch (Exception e) {
			error("Unable to close inputStream2.");
		}
		System.out.println("\n" + file1Location + ": " + file1.size() + " bytes");
		System.out.println(file2Location + ": " + file2.size() + " bytes");
		System.out.println("\nBinary differences:");
		System.out.println("Address   File 1   File 2");
		int i;
		for (i = 0; i < file1.size() && i < file2.size(); i++) {
			if (!file1.get(i).equals(file2.get(i))) {
				System.out.printf("%7s   %6s   %6s\n", hexadecimal(i), hexadecimal(file1.get(i)),
						hexadecimal(file2.get(i)));
			}
		}
		System.out.println("\n" + i + " bytes checked.");
	}
	
	private static String hexadecimal(int number) {
		return Integer.toHexString(number).toUpperCase();
	}
	
	private static void error(String message) {
		System.out.println("Error: " + message);
		System.exit(1);
	}
}
