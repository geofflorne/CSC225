/* PixelVertex.java
   CSC 225 - Summer 2015
   Programming Assignment 5 - Pixel Vertex Data Structure


   G. Lorne - 04/08/2015
*/

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class PixelVertex{

	int X, Y;
	Color color;
	List<PixelVertex> neighbours;
	boolean visited;

	PixelVertex(int X, int Y, Color color){
		this.X = X;
		this.Y = Y;
		this.color = color;
		neighbours = new ArrayList<PixelVertex>();
		visited = false;
	}

	/* Add a constructor here (if necessary) */
	
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		/* Your code here */
		return this.X;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		/* Your code here */
		return this.Y;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	*/
	public PixelVertex[] getNeighbours(){
		PixelVertex temp[] = new PixelVertex[this.neighbours.size()];
		temp = this.neighbours.toArray(temp);
		return temp;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour){
		this.neighbours.add(newNeighbour);
	}
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neighbour){
		this.neighbours.remove(neighbour);
	}
	
	/* getDegree()
	   Return the degree of this vertex.
	*/
	public int getDegree(){
		return this.neighbours.size();
	}
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
		
		return this.neighbours.contains(otherVertex);
	}
	
}