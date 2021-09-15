package cities;

public class Edge implements Comparable<Edge> {

	String city1;
	String city2;
	int distance;
	public Edge(String v1, String v2, int d)
	{
		this.city1=v1;
		this.city2=v2;
		this.distance=d;
		
	}
	@Override
	public int compareTo(Edge e) {
		// TODO Auto-generated method stub
		if (this.distance==e.distance)
			return 0;
		else if (this.distance>e.distance)
			return 1;
		else
			return -1;
	}
		
	

}
