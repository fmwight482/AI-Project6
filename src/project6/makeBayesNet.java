package project6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * main class for bayes net program
 */
public class makeBayesNet {
	static Random rand = new Random();
	static ArrayList<IBayesNode> nodes = new ArrayList<IBayesNode>();
	static ArrayList<String> network = new ArrayList<String>();
	
	/**
	 * build the bayes net by filling in the node arraylist
	 * @param networkFile
	 * @param queryFile
	 * @throws BayesNetException
	 */
	static void buildBayesNet(File networkFile, File queryFile) throws BayesNetException {
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
				String name = network.get(i).substring(0, 5);
				//System.out.println("Name = " + name);
				if (val.equalsIgnoreCase("t")) {
					nodes.add(new EvidenceNode(true, rand, name));
				}
				else if (val.equalsIgnoreCase("f")) {
					nodes.add(new EvidenceNode(false, rand, name));
				}
				else if (val.equalsIgnoreCase("-")) {
					nodes.add(new UnknownNode(rand, name));
				}
				else if (val.equalsIgnoreCase("?")) {
					nodes.add(new QueryNode(rand, name));
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
				//System.out.println("s = " + s);
				//System.out.println("parentString = " + parentString);
				//System.out.println("probs = " + probs);
				
				// split string of parent nodes
				String[] parentNodes = parentString.split(" ");
				//System.out.println("parentNodes = " + Arrays.toString(parentNodes) + ", length = " + parentNodes.length);
				if (parentNodes.length > 0) {
					for (int j=0; j<parentNodes.length; j++) {
						if (parentNodes[j].length() > 0) {
							// extract node number from string
							int nodeNum = Character.getNumericValue(parentNodes[j].charAt(parentNodes[j].length() - 1));
							//System.out.println("nodeNum = " + nodeNum);
							// create edge between nodes, add it to each node
							IBayesNode parent = nodes.get(nodeNum - 1);
							IBayesNode child = nodes.get(i);
							//System.out.println("added an edge from " + parent.getName() + " to " + child.getName());
							Edge edge = new Edge(parent, child);
							nodes.get(i).addEdgeFrom(edge);
							nodes.get(j).addEdgeTo(edge);
						}
					}
				}
				
				// split string of probabilities
				String[] splitProbs = probs.split(" ");
				for (int k=0; k<splitProbs.length; k++) {
					double prob = Double.parseDouble(splitProbs[k]);
					// add probs to the cpt list in order, such that the index of each probability
					// corresponds to the binary representation of the cpt table state
					nodes.get(i).addCPT(prob);
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
	
	/**
	 * print the probability that each node is true
	 * @throws BayesNetException
	 */
	static void printProbabilityResults() throws BayesNetException {
		System.out.println("Printing probabilities:");
		for (IBayesNode bn : nodes) {
			System.out.print("Node " + bn.getName() + " has probability ");
			System.out.println(bn.getTotalProbability());
		}
	}
	
	/**
	 * print the boolean value of each node
	 * @throws BayesNetException
	 */
	static void printBooleanResults() throws BayesNetException {
		System.out.println("Printing boolean values:");
		for (IBayesNode bn : nodes) {
			System.out.println("Node " + bn.getName() + " is " + bn.getVal());
		}
	}

	public static void main(String[] args) throws BayesNetException {
		// for "real" runs, seed with time in milliseconds
		rand.setSeed(System.currentTimeMillis());
		
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
		
		buildBayesNet(networkFile, queryFile);
		
		printProbabilityResults();
		
		//printBooleanResults();
	}
}