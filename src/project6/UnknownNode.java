package project6;

public class UnknownNode extends absBayesNode implements IBayesNode {
	private boolean hasVal;
	private boolean value;

	/**
	 * Default Constructor
	 */
	public UnknownNode() {
		super();
		hasVal = false;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isTrue() {		
		if (hasVal) {
			return value;
		} else {
			boolean thisValue = false;
			int count = 0;
			int parentVal = 0;
			// get parent values from edgesFrom
			// construct a value from the truth of the parents
			for (Edge e : edgesFrom) {
				if (e.getParent().isTrue()) {
					parentVal = 1 << count;// set the bit associated with the
											// parent
				}
				count++;
			} // end for loop
			count = 0;
			for (int c : cpt) {
				if (c == parentVal) {
					thisValue = false ;//makeVal(prob.get(count));
					// look through truth list
				}
				count++;
			}
			// look through CPT to find a true condition in cpt
			// value = thisValue;
			hasVal = true;
			return thisValue;
		}

	}
	
}
