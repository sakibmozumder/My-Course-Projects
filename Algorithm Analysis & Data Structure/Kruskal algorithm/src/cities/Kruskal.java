package cities;
import java.util.*;
public class Kruskal {
	
	ArrayList<Edge> mst =new ArrayList<>();
	
	public Kruskal(ArrayList<String>vertices, ArrayList<Edge> edges)
	{
		
		int numVertices = vertices.size();
		DisjSets ds = new DisjSets( numVertices );
		PriorityQueue<Edge> pq = new PriorityQueue<>( edges );
		
		
		
		while( mst.size( ) != numVertices - 1 )
		{
			Edge e = pq.poll(); // Edge e = (u, v)
			int uset = ds.find( vertices.indexOf(e.city1) );
			int vset = ds.find( vertices.indexOf(e.city2) );
			if( uset != vset )
			{
				// Accept the edge
				mst.add( e );
				ds.union( uset, vset );
		
			}
		
		
		}
	}
}
