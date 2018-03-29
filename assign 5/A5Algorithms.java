/* A5Algorithms.java
   CSC 225 - Summer 2015
   Programming Assignment 5 - Image Algorithms


   G. Lorne - 06/22/2015
*/ 

import java.awt.Color;
import java.util.LinkedList;


public class A5Algorithms{
	
	public static final int NOISE_TOLERANCE = 200;
	public static final int NOISE_PASSES = 69;

	/* FloodFillDFS(v, viewer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, ImageViewerA5 viewer, Color fillColour){
		if (v.visited)
			return;
		v.visited = true;
		viewer.setPixel(v.getX(), v.getY(), fillColour);
		for(int i = 0; i < v.getDegree(); i++)
			FloodFillDFS(v.getNeighbours()[i], viewer, fillColour);
	}

	/* FloodFillBFS(v, viewer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, ImageViewerA5 viewer, Color fillColour){
		LinkedList<PixelVertex> BFSqueue = new LinkedList<PixelVertex>();
		PixelVertex r, w;
		v.visited = true;
		viewer.setPixel(v.getX(), v.getY(), fillColour);
		BFSqueue.add(v);
		while(BFSqueue.size() > 0){
			r = BFSqueue.remove();
			for(int i = 0; i < r.getDegree(); i++){
				w = r.getNeighbours()[i];
				viewer.setPixel(w.getX(), w.getY(), fillColour);
				if(!w.visited){
					w.visited = true;
					BFSqueue.add(w);
				}
			}
		}
	}
	
	/* OutlineRegionDFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void OutlineRegionDFS(PixelVertex v, ImageViewerA5 viewer, Color outlineColour){
		if (v.visited)
			return;
		v.visited = true;
		if(v.getDegree() < 4)
			viewer.setPixel(v.getX(), v.getY(), outlineColour);
		for(int i = 0; i < v.getDegree(); i++)
			OutlineRegionDFS(v.getNeighbours()[i], viewer, outlineColour);
	}
	
	
	/* OutlineRegionBFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void OutlineRegionBFS(PixelVertex v, ImageViewerA5 viewer, Color outlineColour){
		LinkedList<PixelVertex> BFSqueue = new LinkedList<PixelVertex>();
		PixelVertex r, w;
		v.visited = true;
		if(v.getDegree() < 4)
			viewer.setPixel(v.getX(), v.getY(), outlineColour);
		BFSqueue.add(v);
		while(BFSqueue.size() > 0){
			r = BFSqueue.remove();
			for(int i = 0; i < r.getDegree(); i++){
				w = r.getNeighbours()[i];
				if(w.getDegree() < 4)
					viewer.setPixel(w.getX(), w.getY(), outlineColour);
				if(!w.visited){
					w.visited = true;
					BFSqueue.add(w);
				}
			}
		}
	}

	public static void recursiveDFS(PixelVertex v){
		if (v.visited)
			return;
		v.visited = true;
		for(int i = 0; i < v.getDegree(); i++)
			recursiveDFS(v.getNeighbours()[i]);
	}


	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/

	public static int CountComponents(PixelGraph G){
		int regions = 0;
		PixelVertex w;
		for(int x = 0; x < G.width; x++){
			for(int y = 0; y < G.height; y++){
				w = G.getPixelVertex(x,y);
				if(!w.visited){
					regions++;
					recursiveDFS(w);
				}
			}
		}
		return regions;
	}

	/* A5Bonus(G, v, viewer, selectedColour)
	   [optional; up to 5 bonus marks available]
	   Given a PixelGraph G, a PixelVertex v (which has been selected by the  
	   user), an ImageViewerA5 instance and the currently selected colour, 
	   perform some kind of interesting graph-based image manipulation.
	   If you have an idea for an interesting bonus feature, contact the
	   instructor before the due date to determine how many bonus marks
	   your algorithm would receive.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
    
    public static void recursiveDFSbonus(PixelVertex v, LinkedList<PixelVertex> Region){
		if (v.visited)
			return;
		v.visited = true;
		Region.push(v);
		for(int i = 0; i < v.getDegree(); i++)
			recursiveDFSbonus(v.getNeighbours()[i], Region);
	}

	public static Color findColour(PixelVertex v, PixelGraph G){
		if(v.color.equals(Color.black))
			return Color.white;
		else
			return Color.black;

	}

	/*Noise reduction that works sometimes kinda*/
	public static void A5Bonus(PixelGraph G, PixelVertex v, ImageViewerA5 viewer, Color selectedColour){
		PixelVertex w;
		LinkedList<PixelVertex> Region = new LinkedList<PixelVertex>();

		//traverse every vertex
		for(int x = 0; x < G.width; x++){
			for(int y = 0; y < G.height; y++){
				
				//DFS traverse neighbours of vertex, adding them to region stack.
				w = G.getPixelVertex(x,y);
				recursiveDFSbonus(w, Region);
				
				//if pixel has only one or zero neighbours i.e. is noise
				if(w.neighbours.size() < 2)
					viewer.setPixel(w.getX(),w.getY(),findColour(w, G));
				
				//if region stack is smaller than tolerance i.e. is noise 
				if(Region.size() < NOISE_TOLERANCE && Region.peek() != null){
						w = Region.pop();
						//change colour of region
						viewer.setPixel(w.getX(),w.getY(),findColour(w, G));
				}
				Region.removeAll(Region);
			}
		}
    }
}