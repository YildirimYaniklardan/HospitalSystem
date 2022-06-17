// The file "Edge.class"

package Dynamic_Graph;

public class Edge implements Comparable<Edge>{

	// Data Fields
	
	/** The source vertex for the edge */
	private Vertex source;

	/** The destination vertex for the edge */
	private Vertex dest;

	/** The weight of the edge */
	private double weight;


	// Constructors

	/**
	 * Constructs an Edge from source to dest. Sets the weight to 1.0
	 */
	public Edge(Vertex source, Vertex dest){

		this.source=source;
		this.dest=dest;
		weight=1.0;
	}

	/**
	 * Constructs an Edge from source to dest. Sets the weight to w
	 */
	public Edge(Vertex source, Vertex dest, double weight){

		this.source=source;
		this.dest=dest;
		this.weight=weight;
	}


	// Getters

	/**
	 * Returns the source vertex
	 */
	public Vertex getSource(){

		return source;
	}


	/**
	 * Returns the destination vertex
	 */
	public Vertex getDest(){

		return dest;
	}
	

	/**
	 * Returns the weight
	 */
	public double getWeight(){

		return weight;
	}


	// Other Methods

	/**
	 * Returns a string representation of the edge
	 */
	public String toString(){

		return String.format("[s:%d, d:%d, w:%s]", source.getID(), dest.getID(), weight);
	}


	/**
	 * Compares the edges according to their weights.
	 * @param other The edge to be compared.
	 * @return A positive int if this edge's weight is greater, a negative int if smaller, and 0 if equal.
	 */
	public int compareTo(Edge other){

		return Double.valueOf(weight).compareTo(other.weight);
	}
}