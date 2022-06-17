// The file "DGraph.java".

package Dynamic_Graph;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

/**
 * A class for representing simple graphs. Edges of both directed and undirected graphs have directions.
 * In undirected graphs, operations regarding the reverse directed edges are performed automatically.
 */
public class DGraph implements DynamicGraph{

	// Inner Class

	/**
	 * This class is defined to be used as a struct consisting of a vertex and the edges adjacent to it.
	 */
	private class AdjList{

		// Data Fields (components of the struct)

		/** The vertex */
		public Vertex v;

		/** Adjacency list of the vertex v */
		public LinkedList<Edge> adjList;


		// Constructor

		public AdjList(Vertex ver, LinkedList<Edge> list){

			v=ver;
			adjList=list;
		}
	}


	// Data Fields

	/** A boolean representing whether the graph is directed or not (true if directed). */
	private boolean isDirected;

	/** ID value that will be used (and updated) when a new vertex is to be constructed. */
	private static int nextID;

	/**
	 * The member variable holding all the vertices and their adjacency lists.
	 * The lists (and vertices) are stored in a LinkedHashMap instead of a regular array for efficieny reasons.
	 * Values are (vertex, its adjacency list) pairs. Key to each (vertex, its adjaceny list) is the id value of that vertex.
	 */
	private LinkedHashMap<Integer, AdjList> vertices;


	// Static Block to initialize nextID

	static{

		nextID=0;
	}


	// Constructor

	/**
	 * Constructs and empy graph with the specified directed or undirected property.
	 * @param isDirected The created graph will be directed if this parameter is true, undirected otherwise.
	 */
	public DGraph(boolean isDirected){

		this.isDirected=isDirected;

		// default capacity 16 and load factor 0.75 (chaining implementation)
		vertices=new LinkedHashMap<Integer, AdjList>();
	}


	// Other Methods

	/**
	 * Returns the number of vertices in the graph.
	 * Time Complexity: Theta(1).
	 * @return The number of vertiecs in the graph.
	 */
	@Override
	public int getNumV(){

		return vertices.size();
	}


	/**
	 * Returns true if the graph is directed, returns false otherwise.
	 * Time Complexity: Theta(1).
	 * @return true if the graph is directed, false otherwise.
	 */
	@Override
	public boolean isDirected(){

		return isDirected;
	}


	/**
	 * Inserts the edge into the graph if the source and destination vertices are in the graph.
	 * When inserting, this method assumes that there is no edge connecting the same 2 vertices
	 * as the argument 'edge' previously, and adds the edge without checking.
	 * Time Complexity: Theta(1).
	 * @param edge Edge to be inserted.
	 */
	@Override
	public void insert(Edge edge){

		AdjList source_list=vertices.get(edge.getSource().getID());
		AdjList dest_list=vertices.get(edge.getDest().getID());

		if(source_list!=null && dest_list!=null){

			source_list.adjList.add(edge);

			if(!isDirected){

				Edge reverse=new Edge(edge.getDest(), edge.getSource(), edge.getWeight());

				dest_list.adjList.add(reverse);
			}
		}
	}


	/**
	 * Returns true if there is an edge from the vertex with the id 'source' to the vertex
	 * with the id 'dest' in the graph, returns false otherwise.
	 * Time Complexity: O(n), where n is the numeber of vertices in the graph.
	 * @param source id of the source vertex.
	 * @param dest id of the destination vertex.
	 * @return true if the vertex contains an edge from the vertex with the id 'source' to vertex with the id 'dest'.
	 */
	@Override
	public boolean isEdge(int source, int dest){

		return getEdge(source, dest)!=null;
	}


	/**
	 * If the graph contains an edge from the vertex with the id 'source' to the vertex with the
	 * id 'dest'i returns a reference to that edge, returns null otherwise.
	 * Time Complexity: O(n), where n is the number of vertices in the graph.
	 * @param source id of the source vertex.
	 * @param dest id of the destination vertex.
	 * @return A reference to the searched edge if it exist, null otherwise.
	 */
	@Override
	public Edge getEdge(int source, int dest){

		AdjList source_list=vertices.get(source);

		if(source_list==null)
			return null;

		for(Edge edge : source_list.adjList)
			if(edge.getDest().getID()==dest)
				return edge;

		return null;
	}


	/**
	 * If the graph contains the vertex with the id value 'source', returns
	 * an iterator to the edges connected to it, returns null otherwise.
	 * Time Complexity: Theta(1).
	 * @param source id of the vertex.
	 * @return An iterator to the edges adjacent to the vertex with the id 'source', null otherwise.
	 */
	@Override
	public Iterator<Edge> edgeIterator(int source){

		AdjList source_list=vertices.get(source);

		if(source_list==null)
			return null;

		return source_list.adjList.iterator();
	}


	/**
	 * Constructs a vertex with the given parameters.
	 * Time Complexity: Theta(1).
	 * @param label Label of the vertex, should not be null.
	 * @param weight Weight of the vertex.
	 * @return A reference to the constructed vertex.
	 */
	@Override
	public Vertex newVertex(String label, double weight){

		if(label==null)
			throw new IllegalArgumentException("Label can not be null.");

		return new Vertex(nextID++, label, weight);
	}


	/**
	 * Adds the given vertex into the graph if it is not included already.
	 * Time Complexity: Theta(1).
	 * @param new_vertex Vertex to be added.
	 */
	@Override
	public void addVertex(Vertex new_vertex){

		if(vertices.get(new_vertex.getID())==null){

			AdjList new_list=new AdjList(new_vertex, new LinkedList<Edge>());

			vertices.put(new_vertex.getID(), new_list);
		}
	}


	/**
	 * If the graph contains the vertices which have the id values 'vertexID1' and 'vertexID2', constructs
	 * an edge connecting the vertices with the weight value 'weight' and inserts it into the graph. If the graph
	 * is undirected, the reverse directed edge is also insrted. When inserting, this method assumes that 
	 * there is no edge connecting the same 2 vertices previously and adds the edge without checking.
	 * Time Complexity: Theta(1).
	 * @param vertexID1 id of the first vertex (used as source if the graph is directed)
	 * @param vertexID2 id of the second vertex (used as destination if the graph is directed)
	 * @param weight Weight of the new edge
	 */
	@Override
	public void addEdge(int vertexID1, int vertexID2, double weight){

		if(vertexID1==vertexID2)
			return;

		AdjList source_list=vertices.get(vertexID1);
		AdjList dest_list=vertices.get(vertexID2);

		if(source_list!=null && dest_list!=null){

			Edge e=new Edge(source_list.v, dest_list.v, weight);

			insert(e);
		}
	}


	/**
	 * If the graph includes an edge from the vertex with the id 'vertexID1' to the vertex
	 * with the id 'vertexID2', removes the edge and returns true; else, returns false. If
	 * the graph is undirected, the edge with the reverse direction is also removed.
	 * Time Complexity: O(n), where n is the number of vertices in the graph.
	 * @param vertexID1 id of the source vertex
	 * @param vertexID2 id of the destination vertex
	 * @return True if an edge is removed.
	 */
	@Override
	public boolean removeEdge(int vertexID1, int vertexID2){

		AdjList source_list=vertices.get(vertexID1);
		AdjList dest_list=vertices.get(vertexID2);

		if(source_list==null || dest_list==null)
			return false;

		boolean found=false;

		Iterator<Edge> it=source_list.adjList.iterator();

		while(it.hasNext()){

			if(it.next().getDest().getID()==vertexID2){

				it.remove();
				found=true;
				break;
			}
		}

		if(found && !isDirected){

			it=dest_list.adjList.iterator();

			while(it.hasNext()){

				if(it.next().getDest().getID()==vertexID1){

					it.remove();
					break;
				}
			}
		}

		return found;
	}


	/**
	 * If the graph contains the vertex with the id 'vertexID', removes the vertex as well as
	 * all the edges connected to it and returns true; otherwise, returns false.
	 * Time Complexity: O(n+m), where n is the number of vertices and m is the number of edges in the graph.
	 * @param vertexID id of the vertex to be removed
	 * @return true if the vertex is removed, false otherwise.
	 */
	@Override
	public boolean removeVertex(int vertexID){

		if(vertices.get(vertexID)==null)
			return false;

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();
		Map.Entry<Integer, AdjList> entry;
		Iterator<Edge> list_it;

		while(it.hasNext()){

			entry=it.next();

			if(entry.getKey()!=vertexID){

				list_it=entry.getValue().adjList.iterator();

				while(list_it.hasNext()){

					if(list_it.next().getDest().getID()==vertexID){

						list_it.remove();
						break;
					}
				}
			}

			else
				it.remove();
		}

		return true;
	}


	/**
	 * Removes all the vertices with the label 'label' as well as all the edges connected to them.
	 * Time Complexity: O(n+m), where n is the number of vertices and m is the number of edges in the graph.
	 * Note: The best case is obtained when all the edges has the label 'label', resulting in Theta(n).
	 * Implementation detail: An extra check could be done so that the best case is also obtained when no vertex
	 * has the specifed label, but it is avoided since it slows down the method (although it preserves the O(n+m))
	 * in all other cases.
	 * @param label Label of the vertices to be removed
	 */
	@Override
	public void removeVertex(String label){

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();
		AdjList list;
		Iterator<Edge> list_it;

		while(it.hasNext()){

			list=it.next().getValue();

			if(list.v.getLabel().equals(label))
				it.remove();

			else{

				list_it=list.adjList.iterator();

				while(list_it.hasNext()){

					if(list_it.next().getDest().getLabel().equals(label))
						list_it.remove();
				}
			}
		}
	}


	/**
	 * Constructs a new graph including all the vertexes (and edges connecting them) which has the specifed property.
	 * Returns a reference to the constructed graph.
	 * Time Complexity: O(n+m), where n is the number of vertices and m is the number of edges in the graph.
	 * @param key The name of the propery. Should not be null.
	 * @param filter The value of the property. Should not be null.
	 * @return A reference to the constructed graph.
	 */
	@Override
	public DynamicGraph filterVertices(String key, String filter){

		if(key==null || filter==null)
			throw new IllegalArgumentException("The argument 'key' or 'filter' should not be null.");

		DGraph graph=new DGraph(isDirected);

		int counter=0;

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();
		Map.Entry<Integer, AdjList> entry;

		while(it.hasNext()){

			entry=it.next();

			if(filter.equals(entry.getValue().v.getProperty(key))){

				graph.vertices.put(entry.getKey(), new AdjList(entry.getValue().v, new LinkedList<Edge>()));
				counter++;
			}
		}

		Iterator<Edge> list_it;
		Edge e;

		if(counter>1){

			it=vertices.entrySet().iterator();

			while(it.hasNext()){

				entry=it.next();

				list_it=entry.getValue().adjList.iterator();

				while(list_it.hasNext()){

					e=list_it.next();

					if(filter.equals(e.getSource().getProperty(key)) && filter.equals(e.getDest().getProperty(key)))
						graph.vertices.get(entry.getKey()).adjList.add(e);
				}
			}
		}

		return graph;
	}


	/**
	 * Constructs the adjacency matrix for the graph and returns it as a 2D double array.
	 * The value positive infinity means there is no edge between the corresponding vertices.
	 * Time Complexity: Theta(n^2), where n is the number of vertices in the graph.
	 * @return A 2D double array representing the adjacency matrix of this graph.
	 */
	@Override
	public double[][] exportMatrix(){

		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>((int)(vertices.size()*1.4));

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();

		int index=0, i, j;

		while(it.hasNext())
			map.put(it.next().getKey(), index++);
		
		double[][] matrix=new double[vertices.size()][vertices.size()];

		for(i=0; i<matrix.length; ++i)
			for(j=0; j<matrix[i].length; ++j)
				matrix[i][j]=Double.POSITIVE_INFINITY;

		Map.Entry<Integer, AdjList> entry;
		Iterator<Edge> list_it;
		Edge e;

		it=vertices.entrySet().iterator();

		while(it.hasNext()){

			entry=it.next();

			list_it=entry.getValue().adjList.iterator();

			while(list_it.hasNext()){

				e=list_it.next();

				matrix[map.get(entry.getKey())][map.get(e.getDest().getID())]=e.getWeight();
			}
		}

		return matrix;
	}


	/**
	 * Prints the graph.
	 * Time Complexity: Theta(n+m), where n is the number of vertices and m is the number of edges in the graph.
	 */
	@Override
	public void printGraph(){

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();
		Map.Entry<Integer, AdjList> entry;
		Iterator<Edge> list_it;

		while(it.hasNext()){

			entry=it.next();

			System.out.print(String.format("Vertex id: %d\nEdges: ", entry.getKey()));

			list_it=entry.getValue().adjList.iterator();

			while(list_it.hasNext()){

				System.out.print(list_it.next().toString());

				if(list_it.hasNext())
					System.out.print(", ");
			}

			if(it.hasNext())
				System.out.println("\n");
		}

		System.out.println();
	}


	/**
	 * Precondition: The graph has at least 1 vertex. Edges are sorted.
	 */
	private double bfs(){

		// Mapping vertex ids to array indexes.

		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>((int)(vertices.size()*1.4));

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();
		//var it=vertices.entrySet().iterator();
		int index=0;

		while(it.hasNext())
			map.put(it.next().getKey(), index++);

		// BFS Algorithm

		boolean[] visited_or_identified=new boolean[vertices.size()];
		visited_or_identified[map.get(vertices.entrySet().iterator().next().getKey())]=true;

		LinkedList<Vertex> queue=new LinkedList<Vertex>();
		queue.add(vertices.entrySet().iterator().next().getValue().v);

		LinkedList<Vertex> visitSeq=new LinkedList<Vertex>();

		double totalWeight=0;


		Vertex currentVer;
		int ind;

		while(!queue.isEmpty()){

			currentVer=queue.pollFirst();

			visitSeq.add(currentVer);

			for(Edge e : vertices.get(currentVer.getID()).adjList){

				ind=map.get(e.getDest().getID());

				if(!visited_or_identified[ind]){

					queue.add(e.getDest());
					visited_or_identified[ind]=true;

					totalWeight+=e.getWeight();
				}
			}
		}

		System.out.print("BFS Visit Sequence: "); // Comment out this and the next 3 lines if undesired.
		for(Vertex v : visitSeq)
			System.out.print(v.getID()+" ");
		System.out.print("\nBFS total weight: "+totalWeight+"\n\n");

		return totalWeight;
	}


	/**
	 * Precondition: The graph has at least 1 vertex. Edges are sorted.
	 */
	private double dfs(){

		// Mapping vertex ids to array indexes.

		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>((int)(vertices.size()*1.4));

		Iterator<Map.Entry<Integer, AdjList>> it=vertices.entrySet().iterator();
		//var it=vertices.entrySet().iterator();
		int index=0;

		while(it.hasNext())
			map.put(it.next().getKey(), index++);

		// DFS Algorithm

		boolean[] visited_or_identified=new boolean[vertices.size()];
		visited_or_identified[map.get(vertices.entrySet().iterator().next().getKey())]=true;

		LinkedList<Vertex> stack=new LinkedList<Vertex>();
		stack.add(vertices.entrySet().iterator().next().getValue().v);

		LinkedList<Vertex> visitSeq=new LinkedList<Vertex>();

		double totalWeight=0;


		Vertex firstUnvisited;
		int ind;

		while(!stack.isEmpty()){

			firstUnvisited=null;

			for(Edge e : vertices.get(stack.getLast().getID()).adjList){

				ind=map.get(e.getDest().getID());

				if(!visited_or_identified[ind]){

					firstUnvisited=e.getDest();
					visited_or_identified[ind]=true;
					stack.add(firstUnvisited);

					totalWeight+=e.getWeight();
					break;
				}
			}

			if(firstUnvisited==null)
				visitSeq.add(stack.removeLast());
		}

		System.out.print("DFS Visit Sequence: "); // Comment out this and the next 3 lines if undesired.
		for(Vertex v : visitSeq)
			System.out.print(v.getID()+" ");
		System.out.print("\nDFS total weight: "+totalWeight+"\n\n");

		return totalWeight;
	}


	/**
	 * Performs a BFS and DFS through the graph, returns the total weight of the edges traveled during BFS
	 * minus total weight of the edges traveled durng DFS. In both traversals, if there are multiple options
	 * to choose an edge, edge with the lowest weight has priority. This method also sorts the edges as a side effect.
	 * Time Complexity: O((n^2).log(n)), where n is the number of vertices in the graph.
	 * Note: This method also prints the visit sequence of the traversals, chosen start vertex id and the
	 * return value of the function for control purposes. The printing segments of the function (marked) can be
	 * commented out if this is an undesired effect.
	 * @param graph which the traversals are performen on.
	 * @return total weight of edges traversed during BFS - total weight of edges traversed during DFS.
	 */
	public static double compareBFStoDFS(DGraph graph){

		if(graph.vertices.size()==0)
			return 0;

		// Sorting all the edges

		Iterator<Map.Entry<Integer, AdjList>> it=graph.vertices.entrySet().iterator();
		//var it=graph.vertices.entrySet().iterator();

		while(it.hasNext())
			it.next().getValue().adjList.sort(null);

		// Traversals

		System.out.println("Start vertex chosen for both traversals: "+graph.vertices.entrySet().iterator().next().getKey()+"\n"); // Comment out this line if undesired.

		double diff=graph.bfs()-graph.dfs();

		System.out.println("total weight of BFS - total weight of DFS = "+diff); // Comment out this line if undesired.

		return diff;
	}


	/**
	 * Returns the "Boosting" property of the vertex as a double if exist, returns 0 otherwise.
	 */
	private static double getBoosting(Vertex v){

		String boost=v.getProperty("Boosting");

		if(boost==null)
			return 0;

		else
			return Double.valueOf(boost);
	}

	public Vertex getVertex(int vertexID){

		return vertices.get(vertexID).v;
	}


	/**
	 * Computes the shortest paths from the start vertex to all other connected vertices. "Boosting" properties of the vertices are substracted from the
	 * total weight of the edges in the path (exluding start and end vertices of the path). Fills the 'parents' and 'distances' arrays accordingly.
	 * Preconditions: This method assumes that no vertex has a greater "Boosting" value than any of the weight values of its edges. Also all edges must
	 * have non-negative weight values.
	 * Time Complexity: O(n^2), where n is the number of vertices in the graph.
	 * Note: This method also prints the output arguments in a properly formatted way for control purposes. The printing segments of the function (marked)
	 * can be commented out if this is an undesired effect.
	 * @param start The starting vertex from which the shortest paths are found. (Should not be null.)
	 * @param graph The graph that the shortest paths are computed in. (Must contain the start vertex.)
	 * @param parents Output parameter that will be (allocated and) filled with the parents of the corresponding vertices in the shortest path to them.
	 * @param distances Output parameter that will be (allocated and) filled with the total distances of the shortest paths to the corresponding vertices.
	 */
	public static void shortestPathsFrom(Vertex start, DGraph graph, Vertex[] parents, double[] distances){

		if(start==null || graph==null)
			throw new IllegalArgumentException("The arguments 'start or 'graph' can not be null.");

		// Mapping vertex ids to array indexes.

		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>((int)(graph.vertices.size()*1.4));

		Iterator<Map.Entry<Integer, AdjList>> entryIt=graph.vertices.entrySet().iterator();
		int index=0, i;

		while(entryIt.hasNext())
			map.put(entryIt.next().getKey(), index++);

		// Initializing distances. (Boosting value of the start vertex is later added to all distances except the start vertex's.)

		distances=new double[graph.vertices.size()];

		for(i=0; i<distances.length; ++i)
			distances[i]=Double.POSITIVE_INFINITY;

		distances[map.get(start.getID())]=0;

		// Initializing parents.

		parents=new Vertex[graph.vertices.size()];

		for(i=0; i<parents.length; ++i)
			parents[i]=start;

		parents[map.get(start.getID())]=null;

		// Initializing unfixed vertices list.

		LinkedList<Vertex> unfixedVertices=new LinkedList<Vertex>();

		for(Map.Entry<Integer, AdjList> entry : graph.vertices.entrySet())
			unfixedVertices.add(entry.getValue().v);

		// Modified Dijkstra's Algorithm

		Vertex closestVer, ver;
		double closestDist, dist, newPath;
		Iterator<Vertex> it;
		Iterator<Edge> list_it;
		Edge e;

		while(!unfixedVertices.isEmpty()){

			// Finding the smallest unfixed distance.

			closestVer=unfixedVertices.getFirst();
			closestDist=distances[map.get(closestVer.getID())];

			it=unfixedVertices.iterator();

			while(it.hasNext()){

				ver=it.next();
				dist=distances[map.get(ver.getID())];

				if(dist<closestDist){

					closestVer=ver;
					closestDist=dist;
				}
			}

			// Fixing the closest distance.

			unfixedVertices.remove(closestVer);

			// Updating the remaining unfixed distances.

			list_it=graph.vertices.get(closestVer.getID()).adjList.iterator();

			while(list_it.hasNext()){

				e=list_it.next();
				newPath=closestDist+e.getWeight()-getBoosting(closestVer);

				if(distances[map.get(e.getDest().getID())]>newPath){

					distances[map.get(e.getDest().getID())]=newPath;
					parents[map.get(e.getDest().getID())]=closestVer;
				}
			}
		}

		// Adding start vertex's Boosting value to all distances except for the start vetex's.

		for(i=0; i<distances.length; ++i)
			distances[i]+=getBoosting(start);

		distances[map.get(start.getID())]-=getBoosting(start);


		// The rest of the function only prints the output parameters in a properly formatted way.
		// If this is an undesired effect, the rest of this function from here can be commented out.

		System.out.println(String.format("%-14s%-45s%-37s%-30s", "Vertex ID", "Name of the Place", "Total Distance From Start Vertex", "Parent ID in the Shortest Path"));

		entryIt=graph.vertices.entrySet().iterator();

		String parent;

		while(entryIt.hasNext()){

			i=entryIt.next().getKey();
			
			if(parents[map.get(i)]!=null)
				parent=String.valueOf(parents[map.get(i)].getID());
			else
				parent="None";

				System.out.println(String.format("%-14s%-45s%-37s%-30s", i, graph.vertices.get(i).v.getLabel(), distances[map.get(i)], parent));
		}
	}
}