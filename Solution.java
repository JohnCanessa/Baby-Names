import java.util.ArrayList;
import java.util.Scanner;

/*
 * 
 */
public class Solution {
	
	/*
	 * Build a list with list of synonyms.
	 */
	static ArrayList<ArrayList<String>> collectSynonyms(ArrayList<String> pairs) {
		
		// **** list of list of synonyms ****
		ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
		
		// **** ****
		while (pairs.size() != 0) {
			
			// **** get the first name and synonym out of the list ****
			String name 	= pairs.remove(0);
			String synonym 	= pairs.remove(0);

			// **** add them to a temp list ****
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(name);
			temp.add(synonym);
			
			// **** loop finding synonyms for names in the temp list (add them as needed) ****
			int i = 0;
			for (boolean traversed = false; !traversed; ) {
							
				// **** ****
				String curName = temp.get(i);
								
				// **** look for this name in the pairs ****
				int j = pairs.indexOf(curName);
								
				// **** name found ****
				if (j != -1) {
					
					// **** add new synonym ****
					if ((j % 2) == 0) {
						temp.add(pairs.get(j + 1));
					}
					
					// **** add new name ****
					else {
						temp.add(pairs.get(j - 1));
					}
					
					// **** remove pair ****
					if ((j % 2) == 0) {
						pairs.remove(j + 1);
						pairs.remove(j);
					} else {
						pairs.remove(j);
						pairs.remove(j - 1);
					}
				}
				
				// **** name not found ****
				else {
					i++;
				}
								
				// **** check if we finish traversing the list ****
				if (i >= temp.size())
					traversed = true;
			}

			// **** add the temp list to the synonyms list ****
			synonyms.add(temp);
		}
		
		// ???? ????
		System.out.println("collectSynonyms <<< synonyms: " + synonyms.toString());

		// **** return list of list of synonyms ****
		return synonyms;
	}

	/*
	 * Print names and true frequencies.
	 */
	static void trueFreq(ArrayList<String> freqs, ArrayList<String> pairs) {
		
		// **** build list of synonyms ****
		ArrayList<ArrayList<String>> synonyms = collectSynonyms(pairs);
		System.out.println();

		// **** traverse list of list of synonyms adding frequencies ****
		for (int i = 0; i < synonyms.size(); i++) {
			
			// **** get the next list ****
			ArrayList<String> synLst = synonyms.get(i);
			
			// **** traverse this list ****
			int sum 	= 0;
			String name = "";
			for (int j = 0; j < synLst.size(); j++) {
				
				// **** for ease of use ****
				name 			= synLst.get(j);
				int index 		= freqs.indexOf(name);

				// **** check if name does not have a frequency ****
				if (index == -1)
					continue;
			
				int frequency 	= Integer.valueOf(freqs.get(index + 1));
				
				// **** remove the frequency and associated name from the list ****
				freqs.remove(index + 1);
				freqs.remove(index);
				
				// **** add to the true frequency ****
				sum += frequency;
			}
			
			// **** display the name and frequency sum ****
			System.out.println(name + " " + sum);
		}		
		
		// ***** display remaining names and frequencies (without synonyms) ****
		for (int i = 0; i < freqs.size(); i += 2)
			System.out.println(freqs.get(i) + " " + freqs.get(i + 1));	
	}

	/*
	 * Test scaffolding.
	 */
	public static void main(String[] args) {

		// **** frequency list ****
		ArrayList<String> freqs = new ArrayList<String>();
		
		// **** open scanner ****
		Scanner sc = new Scanner(System.in);
		
		// **** read in the number of entries in the map ****
		int n = sc.nextInt();
		
		// ???? ????
		System.out.println("main <<< n: " + n);
		
		// **** loop filling the frequency list ****
		for (int i = 0; i < n; i++) {
			
			// **** read name and frequency ****
			String name = sc.next();
			int freq = sc.nextInt();
			
			// **** ****
			freqs.add(name);
			freqs.add(String.valueOf(freq));
		}
		
		// ???? ????
		System.out.println("main <<< freqs: " + freqs.toString());

		
		// **** read in the number of pairs list ****
		n = sc.nextInt();
		
		// ???? ????
		System.out.println("main <<< n: " + n);
		
		// **** list of pairs ****
		ArrayList<String> pairs = new ArrayList<String>();
		
		// **** loop reading the name pairs ****
		for (int i = 0; i < n; i++) {
			pairs.add(sc.next());
			pairs.add(sc.next());
		}
		
		// ???? ????
		System.out.println("main <<< pairs: " + pairs.toString());
		
		// **** close scanner ****
		sc.close();
		
		// **** print the true trequencies ****
		trueFreq(freqs, pairs);
	}

}
