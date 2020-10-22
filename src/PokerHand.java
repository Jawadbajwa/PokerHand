import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Rank {
	int rank;
	int cardNum;
	
	Rank() {
		rank = 0;
		cardNum = 0;
	}
}

public class PokerHand {

	private static Functionality func = new Functionality();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Open the file
		
		int aa = func.check ("QS 2H AC 6H KS AD 7D KH QH TC");
		
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			int p1 = 0;
			int p2 = 0;
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				System.out.println (strLine);
				
				int ret = func.check(strLine);
				if (ret > 0)
					p1++;
				else
					p2++;
			}

			System.out.println("Player 1: " + p1 + " hands");
			System.out.println("Player 2: " + p2 + " hands");
			//Close the input stream
			fstream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
