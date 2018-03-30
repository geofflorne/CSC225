/* PixelGraph.java
   CSC 225 - Summer 2015
   Programming Assignment 4 - Pixel Graph Data Structure

   B. Bird - 04/08/2015
*/ 

import java.awt.Color;
import java.util.Arrays;

public class PixelGraph{
    
    int width, height;
    PixelVertex[][] graph;
	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	*/
	public PixelGraph(Color[][] imagePixels){
		this.width = imagePixels.length;
        this.height = imagePixels[0].length;
        this.graph = new PixelVertex[width][height];
        
        //traverse graph
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                
                //initialize Pixel
                PixelVertex newVertex = new PixelVertex(x, y, imagePixels[x][y]);
                
                //If current pixel is not on top border and is same colour as above pixel...
                if(y > 0 && this.getPixelVertex(x, y - 1).color.equals(newVertex.color)){
                    //set above pixel to neighbour list
                    newVertex.addNeighbour(this.getPixelVertex(x, y - 1));
                    //set current pixel to above pixel's neighbour list
                    this.getPixelVertex(x, y - 1).addNeighbour(newVertex);
                }
                
                //If current pixel is not on left border and is same colour as left pixel...
                if(x > 0 && this.getPixelVertex(x - 1, y).color.equals(newVertex.color)){
                    //set left pixel to neighbour list
                    newVertex.addNeighbour(this.getPixelVertex(x - 1, y));
                    //set current pixel to left pixel's neighbour list
                    this.getPixelVertex(x - 1, y).addNeighbour(newVertex);
                }
                
                graph[x][y] = newVertex;
            }
        }
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/
	public PixelVertex getPixelVertex(int x, int y){
		return this.graph[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return this.width;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return this.width;
	}
	
}