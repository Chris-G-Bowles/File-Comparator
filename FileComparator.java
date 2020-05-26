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
		String file2Location;
		if (args.length == 0) {
			System.out.print("Enter file 2's location: ");
			file2Location = input.nextLine();
		} else {
			file2Location = args[1];
		}
		input.close();
		try {
			FileInputStream inputStream = new FileInputStream(file1Location);
			int token;
			ArrayList<Integer> file1 = new ArrayList<>();
			do {
				try {
					token = inputStream.read();
				} catch (Exception e) {
					token = -1;
				}
				if (token >= 0 && token <= 255) {
					file1.add(token);
				}
			} while (token >= 0 && token <= 255);
			inputStream.close();
			try {
				inputStream = new FileInputStream(file2Location);
				ArrayList<Integer> file2 = new ArrayList<>();
				do {
					try {
						token = inputStream.read();
					} catch (Exception e) {
						token = -1;
					}
					if (token >= 0 && token <= 255) {
						file2.add(token);
					}
				} while (token >= 0 && token <= 255);
				inputStream.close();
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
			} catch (Exception e) {
				System.out.println("Error: " + file2Location + " not found.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + file1Location + " not found.");
		}
	}
	
	private static String hexadecimal(int number) {
		return Integer.toHexString(number).toUpperCase();
	}
	
	private static void error(String message) {
		System.out.println("Error: " + message);
		System.exit(1);
	}
}
