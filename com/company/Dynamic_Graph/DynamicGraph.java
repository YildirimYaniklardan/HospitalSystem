// The file "DynamicGraph.java".

package Dynamic_Graph;

public interface DynamicGraph extends Graph{

	// Methods

	/**
	 * Constructs a vertex with the given label and weight and returns a reference to it.
	 * @param label The label of the vertex. Should not be null.
	 * @param weight Weight of the vertex.
	 * @return A reference to the constructed vertex.
	 */
	Vertex newVertex(String label, double weight);


	/**
	 * Adds the given vertex into the graph if it is not included already.
	 * @param new_vertex Vertex to be added.
	 */
	void addVertex(Vertex new_vertex);


	/**
	 * If the graph contains the vertices which have the id values 'vertexID1' and 'vertexID2', constructs
	 * an edge connecting the vertices with the weight value 'weight' and inserts it into the graph. If the graph
	 * is undirected, the reverse directed edge is also insrted. When inserting, this method assumes that 
	 * there is no edge connecting the same 2 vertices previously and adds the edge without checking.
	 * @param vertexID1 id of the first vertex (used as source if the graph is directed)
	 * @param vertexID2 id of the second vertex (used as destination if the graph is directed)
	 * @param weight Weight of the new edge
	 */
	void addEdge(int vertexID1, int vertexID2, double weight);


	/**
	 * If the graph contains an edge from the vertex with the id 'vertexID1' to the vertex
	 * with the id 'vertexID2', removes the edge and returns true; else, returns false. If
	 * the graph is undirected, the edge with the reverse direction is also removed.
	 * @param vertexID1 id of the source vertex
	 * @param vertexID2 id of the destination vertex
	 * @return True if an edge is removed.
	 */
	boolean removeEdge(int vertexID1, int vertexID2);


	/**
	 * If the graph contains the vertex with the id 'vertexID', removes the vertex as well as
	 * all the edges connected to it and returns true; otherwise, returns false.
	 * @param vertexID id of the vertex to be removed
	 * @return true if the vertex is removed, false otherwise.
	 */
	boolean removeVertex(int vertexID);


	/**
	 * Removes all the vertices with the label 'label' as well as all the edges connected to them.
	 * @param label Label of the vertices to be removed
	 */
	void removeVertex(String label);


	/**
	 * Constructs a new graph including all the vertexes (and edges connecting them) which has the specifed property.
	 * Returns a reference to the constructed graph.
	 * @param key The name of the propery.
	 * @param filter The value of the property.
	 * @return A reference to the constructed graph.
	 */
	DynamicGraph filterVertices(String key, String filter);


	/**
	 * Constructs the adjacency matrix for the graph and returns it as a 2D double array.
	 * The value positive infinity means there is no edge between the corresponding vertices.
	 * @return A 2D double array representing the adjacency matrix of this graph.
	 */
	double[][] exportMatrix();


	/**
	 * Prints the graph.
	 */
	void printGraph();
}