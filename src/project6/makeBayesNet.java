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
	static QueryNode queryNode;
	
	/**
	 * build the bayes net by filling in the node arraylist
	 * @param networkFileName
	 * @param queryFileName
	 * @throws BayesNetException
	 */
	static void buildBayesNet(String networkFileName, String queryFileName) throws BayesNetException {
		File networkFile = new File(networkFileName);
		File queryFile = new File(queryFileName);
		
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
					queryNode = new QueryNode(rand, name);
					nodes.add(queryNode);
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
			System.out.println("Node " + bn.getName() + " is " + bn.getVal(false));
		}
	}
	
	/**
	 * Run the rejection sampling algorithm with the given number of samples
	 * @param samples
	 * @return the probability that the query node is true
	 * @throws BayesNetException
	 */
	static double rejectionSampling(int samples) throws BayesNetException {
		int countTrue = 0;
		int countFalse = 0;
		for (int i=0; i<samples; i++) {
			Boolean shouldReject = new Boolean(false);
			boolean isTrue = queryNode.getVal(shouldReject);
			if (!shouldReject) {
				if (isTrue) {
					countTrue++;
				}
				else {
					countFalse++;
				}
			}
		}
		return countTrue / (countTrue + countFalse);
	}
	
	/**
	 * Run the likelihood weighted sampling algorithm with the given number of samples
	 * @param samples
	 * @return the probability that the query node is true
	 * @throws BayesNetException
	 */
	static double liklihoodWeightedSampling(int samples) throws BayesNetException {
		double prob = queryNode.getLocalProbability();
		return prob;
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
		int numSamples = Integer.parseInt(args[2]);
		
		buildBayesNet(args[0], args[1]);
		
		printProbabilityResults();
		
		printBooleanResults();
	}
}