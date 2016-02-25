package project6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * main class for bayes net program
 */
public class makeBayesNet {

	public static void main(String[] args) throws BayesNetException {
		// TODO Auto-generated method stub
		// args 0 = network file
		// args 1 = query file
		// args 2 = number of samples
		if (args.length != 3) {
			throw new BayesNetException("makeBayesNet requires three values as input");
		}
		File networkFile = new File(args[0]);
		File queryFile = new File(args[1]);
		int numSamples = Integer.parseInt(args[2]);
		
		ArrayList<absBayesNode> nodes = new ArrayList<absBayesNode>();
		ArrayList<String> network = new ArrayList<String>();
		
		BufferedReader queryReader = null;
		BufferedReader networkReader = null;
		
		String text = null;
		
		try {
			networkReader = new BufferedReader(new FileReader(networkFile));
			// initialize each node
			while ((text = networkReader.readLine()) != null) {
				network.add(text);
			}
			
			queryReader = new BufferedReader(new FileReader(queryFile));
			String[] query = queryReader.readLine().split(",");
			for (int i=0; i<query.length; i++) {
				// assign value to appropriate node
				String val = query[i];
				if (val.equalsIgnoreCase("t")) {
					nodes.add(new EvidenceNode(true));
				}
				else if (val.equalsIgnoreCase("f")) {
					nodes.add(new EvidenceNode(false));
				}
				else if (val.equalsIgnoreCase("-")) {
					nodes.add(new UnknownNode());
				}
				else if (val.equalsIgnoreCase("?")) {
					nodes.add(new QueryNode());
				}
				else {
					throw new BayesNetException("read value '" + val + "' is not valid");
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (queryReader != null) {
					queryReader.close();
				}
				if (networkReader != null) {
					networkReader.close();
				}
			}
			catch (IOException e) {
				
			}
		}
		
	}

}