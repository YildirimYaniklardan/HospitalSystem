// The file "Vertex.java".

package Dynamic_Graph;

import java.util.LinkedHashMap;

public class Vertex{

	// Data Fields

	private int id;

	private String label;

	private double weight;

	private LinkedHashMap<String, String> properties;


	// Constructors

	protected Vertex(int id, String label, double weight){

		this.id=id;
		this.label=label;
		this.weight=weight;

		// capacity = 3 and (default) load factor = 0.75. (Chaining implementation.)
		properties=new LinkedHashMap<String, String>(3);
	}


	// Getters

	public int getID(){

		return id;
	}


	public String getLabel(){

		return label;
	}


	public double getWeight(){

		return weight;
	}


	public String getProperty(String propName){

		return properties.get(propName);
	}


	// Other Methods

	public void add_or_update_property(String propName, String propValue){

		if(propName==null || propValue==null)
			throw new IllegalArgumentException("The input parameters can not be null.");

		properties.put(propName, propValue);
	}
}