import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***
 * COP 3530: Project 1–Array Searches and Sorts the csv file that contains information about every state 
 ** 
 ** project uses a given cvs file to read the information on it and uses muiltiple sorting methods to sort the data from the csv fil
 *  about every state.Program using bubble sorting to sort the states alphabetically, and used a selection sort to sort COVID-19 case fatality rate ascendingly. 
 *  Program uses Insertion sort to sort median household income ascendingly and used binary search to find and print a State information for a given  state name that will be requested to be 
 *  entered, if data is sorted usuing binary seach, if not using sequentioal search. used the Spearman’s Correlation that correlates between states and the covid cases. 
 *
 * @author Riyam Yatooma 
 * 
 * @version 09/17/2021
 * 
 **/
public class Project1 {
	/**
	 * Method uses a string argument and a scanner method to read the given csv file 
	 * ß
	 *  @param a string array called args 
	 *  
	 ** @return none 
	 *
	 **/

	public static void main(String[] args) {
		System.out.println("COP 3530 Project 1");
		System.out.println("Instructor: Xudong Liu\n");
		System.out.println("Array Searches and Sorts");

		Scanner fileIn = new Scanner(System.in);
		System.out.print("Enter File Name: "); 
		String fileName = fileIn.nextLine(); 

		int i = 0;
		State states[] = new State[50];

		Scanner inFile = null;
		try {
			inFile = new Scanner ( new File(fileName)); 

		} catch (FileNotFoundException e) {
			System.out.println("\nCould not open the file!");
			System.exit(1);
		}

		inFile.useDelimiter(",|\n");
		inFile.nextLine();
		while (inFile.hasNext()) {

			String statename = inFile.next();
			String capitalname = inFile.next();
			String regionname = inFile.next();
			int ushouseseats = inFile.nextInt();
			int population = inFile.nextInt();
			double covidcases = inFile.nextDouble();
			double coviddeath = inFile.nextDouble();
			int medianhouseholdincome = inFile.nextInt();
			double violentcrimerate = inFile.nextDouble();

			states[i] = new State(statename, capitalname, regionname, ushouseseats, population, covidcases, coviddeath,
					medianhouseholdincome, violentcrimerate);
			i++;
		}
		System.out.printf("%nThere were %d records read.%n%n", i);
		boolean binarySearch = false;
		String input = "";
		dowhile: do {
			System.out.println("1. Print State Report");
			System.out.println("2. Sort by name");
			System.out.println("3. Sort by COVID-19 case fatality rate");
			System.out.println("4. Sort by median household income");
			System.out.println("5. Find and print a State for a given name");
			System.out.println("6. Print Spearman’s correlation matrix");
			System.out.println("7. Quit");
			System.out.println("");

			input = fileIn.nextLine();
			switch (input)

			{
			case "1":
				printReport(states);
				break;
			case "2":
				bubbleSort(states);
				binarySearch = true;
				break;
			case "3":
				binarySearch = false;
				selectionSort(states);
				break;
			case "4":
				binarySearch = false;
				insertionSort(states);
				break;
			case "5":
				binarySequentialSearch(states, binarySearch, fileIn);
				break;
			case "6":
				spearmans(states);
				break;
			case "7":
				break dowhile;
			default:
				System.out.printf("Invalid option %s, enter 1-7%n%n", input);

			}

		} while (!input.equals("7"));

		inFile.close();
		System.out.println("Have a good day!");
	}
/**
 * Prints the data from the report
 * 
 * @param states passing a State object array
 */
	private static void printReport(State states[]) {
		System.out.printf("%-17s %-17s %-17s %-10s %15s %15s %n", "Name", "MHI", "VCR", "CFR", "Case Rate",
				"Death Rate");
		System.out.println(
				"================================================================================================");

		for (State state : states)
			state.printStateLn();
		System.out.println();
	}
/**
 * This method sorts the states names using bubble sort 
 * 
 * @param states passing a State object array
 */
	private static void bubbleSort(State states[]) {
		int out, in;
		for (out = states.length - 1; out > 1; out--)
			for (in = 0; in < out; in++)
				if (states[in].getName().compareTo(states[in + 1].getName()) > 0)
					swap(states, in, in + 1);
		System.out.println("\nStates sorted by Name.\n");
	}
/**
 * This method takes objects and swaps there places 
 * 
 * @param states passing a State object array and swapping two states
 */
	private static void swap(State states[], int one, int two) {
		State temp = states[one];
		states[one] = states[two];
		states[two] = temp;
	}
/**
 * This method uses binary Sequential Search to find and print a State for a given name, 
 * using binary search if the data is sorted by name and/or sequential search if data is not
 * @param states passing a State object array and swapping two states
 * @param binary true/false switch between binary and sequential search
 * @param in scanner to scan in name 
 * 
 */
	private static void binarySequentialSearch(State[] states, boolean binary, Scanner in) {
		System.out.println("Enter State name: ");
		String name = in.nextLine();
		if (binary) {
			System.out.println("\nBinary search\n");
			int lowerBound = 0;
			int upperBound = states.length - 1;
			int curIn;
			while (true) {
				curIn = (lowerBound + upperBound) / 2;
				if (states[curIn].getName().equals(name)) {
					states[curIn].printState();
					return;
				}

				else if (lowerBound > upperBound)
					break;
				else {
					if (states[curIn].getName().compareTo(name) < 0)
						lowerBound = curIn + 1;
					else
						upperBound = curIn - 1;
				}
			}

		} else {
			System.out.println("\nSequential search\n");
			for (State state : states) {
				if (state.getName().equals(name)) {
					state.printState();
					return;
				}
			}
		}
		System.out.printf("%nError: %s not found%n%n", name);
	}
/**
 * This method uses selectionsort to Sort COVID-19 case fatality rate ascendingly 
 * 
 * @param states passing a State object array
 */
	private static void selectionSort(State states[]) {
		int out, in, min;
		for (out = 0; out < states.length - 1; out++) {
			min = out;
			for (in = out + 1; in < states.length; in++)
				if (states[in].getCaseFatalityRate() < states[min].getCaseFatalityRate())
					min = in;
			swap(states, out, min);
		}
		System.out.println("\nStates sorted by COVID-19 Case Fatality Rate.\n");
	}
/**
 * This method uses insertion sort to Sort by median household income ascendingly 
 * 
 * @param states passing a State object array
 */
	private static void insertionSort(State states[]) {
		int in, out;
		for (out = 1; out < states.length; out++) {
			State temp = states[out];
			in = out;
			while (in > 0 && states[in - 1].getHouseholdincome() >= temp.getHouseholdincome()) {
				states[in] = states[in - 1];
				--in;
			}
			states[in] = temp;
		}
		System.out.println("\nStates sorted by Median Household Income.\n");
	}
/**
 * This method uses Spearman’srcorrelation matrix formula calculation to calculate 
 * 
 * @param states passing a State object array
 */
	private static void spearmans(State states[]) {
		double divider = states.length * (Math.pow(states.length, 2) - 1);
		State[] cases = new State[states.length];
		State[] deaths = new State[states.length];
		State[] mhi = new State[states.length];
		State[] vcr = new State[states.length];

		for (int i = 0; i < states.length; i++) {
			cases[i] = states[i];
			deaths[i] = states[i];
			mhi[i] = states[i];
			vcr[i] = states[i];
		}

		for (int i = 0; i < states.length; i++) {
			for (int j = 1; j < states.length - i; j++) {
				if (cases[j - 1].getCaseFatalityRate() > cases[j].getCaseFatalityRate()) {
					swap(cases, j, j - 1);
				}
				if (deaths[j - 1].getDeathRate() > deaths[j].getDeathRate()) {
					swap(deaths, j, j - 1);
				}
				if (mhi[j - 1].getHouseholdincome() > mhi[j].getHouseholdincome()) {
					swap(mhi, j, j - 1);
				}
				if (vcr[j - 1].getViolentcrimerate() > vcr[j].getViolentcrimerate()) {
					swap(vcr, j, j - 1);
				}
			}
		}

		double caseMHI = 0;
		double caseVCR = 0;
		double deathMHI = 0;
		double deathCR = 0;

		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < states.length; j++) {
				if (cases[i].getName().equals(mhi[j].getName())) {
					caseMHI += Math.pow(i - j, 2);
				}
				if (deaths[i].getName().equals(mhi[j].getName())) {
					deathMHI += Math.pow(i - j, 2);
				}
				if (cases[i].getName().equals(vcr[j].getName())) {
					caseVCR += Math.pow(i - j, 2);
				}
				if (deaths[i].getName().equals(vcr[j].getName())) {
					deathCR += Math.pow(i - j, 2);
				}

			}
		}

		caseMHI = 1 - (6 * caseMHI) / divider;
		deathMHI = 1 - (6 * deathMHI) / divider;
		caseVCR = 1 - (6 * caseVCR) / divider;
		deathCR = 1 - (6 * deathCR) / divider;

		System.out.println("---------------------------------------");
		System.out.printf("|%10s  |%7s    |%7s    |%n", "COVID-19", "MHI", "VCR");
		System.out.println("---------------------------------------");
		System.out.printf("| Case Rate  |%9.4f  |%9.4f  |%n", caseMHI, caseVCR);
		System.out.println("---------------------------------------");
		System.out.printf("| Death Rate |%9.4f  |%9.4f  |%n", deathMHI, deathCR);
		System.out.println("---------------------------------------\n");
	}
}
