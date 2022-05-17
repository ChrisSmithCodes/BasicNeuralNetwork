package asmithllc.basicneuralnetwork;

/**
 *  Basic class to generate shapes in a grid array.
 *  The grid is made up of zero's, where the pixels of the shape are 1
 * 
 * @author csmit
 */
public class Shapes {
 
    public int[][] generateRandomRectangle(int[][] canvas){
        int[][] grid = canvas;
        
        //Initialize variables
        int x1 = (int)(Math.random() * canvas.length - 1);
        int y1 = (int)(Math.random() * canvas[0].length - 1);
        
        int x2 = 1 + x1 + (int)(Math.random() * (canvas.length - x1 - 1));
        int y2 = 1 + y1 + (int)(Math.random() * (canvas[0].length - y1 - 1));
        
        //Plot Shape
        for (int i = 0; i < canvas.length; i++){
            for (int j = 0; j < canvas[0].length; j++){
                if (i >= x1 && i <= x2 && j >= y1 && j <= y2){
                    grid[i][j] = 1;
                }
            }
        }

        return grid;
    }
    
    public int[][] generateRandomCircle(int[][] canvas){
        int[][] grid = canvas;
        
        //Initialize variables
        int xOrigin = 2 + (int)(Math.random() * (grid.length - 4));
        int yOrigin = 2 + (int)(Math.random() * (grid[0].length - 4));
        int radius = 2 + (int)(Math.random() * (-2 + Math.min(
                Math.min(xOrigin, canvas.length - xOrigin), Math.min(yOrigin, canvas[0].length - yOrigin))));
        
        double distance;
        
        //Plot
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                distance = (Math.sqrt(Math.pow(xOrigin - i, 2) + Math.pow(yOrigin - j, 2)));
                // if the distance from the current coordinate to the origin is <= a radius, plot it
                if (distance <= radius){
                    grid[i][j] = 1;
                }
            }
        }
        
        return grid;
    }
    
    public void printShape(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}