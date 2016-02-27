package project6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
		
		ArrayList<IBayesNode> nodes = new ArrayList<IBayesNode>();
		ArrayList<String> network = new ArrayList<String>();
		
		BufferedReader queryReader = null;
		BufferedReader networkReader = null;
		
		String text = null;
		
		try {
			networkReader = new BufferedReader(new FileReader(networkFile));
			// record info from the text file
			while ((text = networkReader.readLine()) != null) {
				network.add(text);
			}
			
			queryReader = new BufferedReader(new FileReader(queryFile));
			String[] query = queryReader.readLine().split(",");
			for (int i=0; i<query.length; i++) {
				// initialize each node as the appropriate type
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
			
			// fill each node with the appropriate probabilities and parent
			for (int i=0; i<network.size(); i++) {
				// parse input from network text file into useful values
				String s = network.get(i);
				String parentString = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
				String probs = s.substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]"));
				// test that substrings are correct
				System.out.println("s = " + s);
				System.out.println("parentString = " + parentString);
				System.out.println("probs = " + probs);
				
				// split string of parent nodes
				String[] parentNodes = parentString.split(" ");
				System.out.println("parentNodes = " + Arrays.toString(parentNodes) + ", length = " + parentNodes.length);
				if (parentNodes.length > 0) {
					for (int j=0; j<parentNodes.length; j++) {
						if (parentNodes[j].length() > 0) {
							// extract node number from string
							int nodeNum = Character.getNumericValue(parentNodes[j].charAt(parentNodes[j].length() - 1));
							System.out.println("nodeNum = " + nodeNum);
							// create edge between nodes, add it to each node
							Edge edge = new Edge(nodes.get(j), nodes.get(i));
							nodes.get(j).addEdgeFrom(edge);
							nodes.get(i).addEdgeTo(edge);
						}
					}
				}
				
				// split string of probabilities
				String[] splitProbs = probs.split(" ");
				for (int k=0; k<splitProbs.length; k++) {
					double prob = Double.parseDouble(splitProbs[k]);
					nodes.get(k).addCPT(prob);
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