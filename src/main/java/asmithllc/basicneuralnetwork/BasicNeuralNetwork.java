package asmithllc.basicneuralnetwork;

/**
 *  Basic Neural Network design following:
 *      Veritasium: "Future Computers Will Be Radically Different" - https://www.youtube.com/watch?v=GVsUOuSjvcg
 *      Tsoding Daily: "First Ancient Neural Network in C" - https://www.youtube.com/watch?v=WEk_grxrCcg
 * 
 *  Interesting concepts, but I figured the the same could be done in Java using a simpler design.
 *  
 *  Tsoding used a PPM formatted image generated randomly in C as his shapes. To keep my version simple,
 *  I used a 2D array of 1's and 0's. The neural network should be able to take any canvas using a weights
 *  grid of the same dimensions and determine if the shape on it is a rectangle (0) or a circle (1).
 * 
 *  In order to get a weight grid that can do this, we have to train it. If we give it a rectangle and our
 *  BNN says its a circle, we should subtract the grid from the weights. On the other hand, if we give it a circle
 *  and it figures a rectangle, we should add the two grids.
 * 
 *  Both Tsoding's model and my own create a lack luster result of about 57% accuracy on a good day.
 * 
 * @author csmit
 */
public class BasicNeuralNetwork {
    
    //The weights grid is what we will be training, where the canvas grid contains the current shape
    //We will initialize both of these grids as zero arrays. Both grids will have the same dimension, X,Y
    int[][] weights, canvas;
    
    int xDimension, yDimension;
    float bias;
    Shapes sg; //Shape Generator

    public BasicNeuralNetwork(int yDimension, int xDimension, float bias){
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.bias = bias;
        
        this.weights = createZeroArray();
        this.canvas = createZeroArray();
        
        this.sg = new Shapes();
    }
    
    private int[][] createZeroArray(){
        int[][] grid = new int[this.xDimension][this.yDimension];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = 0;
            }
        }
        
        return grid;
    }
    
    public void train(int iterations){
        int shape; //Determine if we generate a rectangle (0) or a circle (1)
        
        //Loop through the amount of iterations and train the weight grid
        for (int i = 0; i < iterations; i++){
            canvas = createZeroArray();
            shape = (int)(Math.random() * 2);
            
            switch(shape){
                case 0 -> canvas = sg.generateRandomRectangle(canvas);
                case 1 -> canvas = sg.generateRandomCircle(canvas);
            }
            
            
            //If the shape is a rectangle and the guess is wrong, we will multiply the canvas by -1
            //If the shape is a circle and the guess is wrong, we will not multiply the canvas by anything
            if (this.guessShape(canvas) != shape){
                this.combine(shape, canvas);
            }
        }
    }
    
    public int guessShape(int[][] shape){
        int sum = 0;
        
        for (int i = 0; i < shape.length; i++){
            for (int j = 0; j < shape.length; j++){
                sum+= shape[i][j] * this.weights[i][j];
            }
        }
        
        return (sum < bias ? 0 : 1);
    }
    
    private void combine(int shape, int[][] grid){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if(shape == 0){
                    this.weights[i][j] -= grid[i][j];
                }else{
                    this.weights[i][j] += grid[i][j];
                }
                
            }
        }
        
        //System.out.println("Modified Weights:\n\n" + this.toString() + "\n");
    }
    
    @Override
    public String toString(){
        String output = "Weights for a " + xDimension + " By " + yDimension + " BNN:\n\n";
        
        for (int i = 0; i < this.weights.length; i++){
            for (int j = 0; j < this.weights[0].length; j++){
                output += this.weights[i][j] + " ";
            }
            
            output += "\n";
        }
        
        return output;
    }
    
    public double test(int iterations){
        int shape; //Determine if we generate a rectangle (0) or a circle (1)
        int cnt = 0;
        
        //Loop through the amount of iterations and train the weight grid
        for (int i = 0; i < iterations; i++){
            canvas = createZeroArray();
            shape = (int)(Math.random() * 2);
            
            switch(shape){
                case 0 -> canvas = sg.generateRandomRectangle(canvas);
                case 1 -> canvas = sg.generateRandomCircle(canvas);
            }
            
            //If the BNN guesses correct, add one to the counter
            if (this.guessShape(canvas) == shape){ cnt += 1; }
        }
        
//        System.out.println("From a test of " + iterations + " runs with a bias of " + this.bias +
//                ", the BNN had a success rate of " + (100.0 * (double)cnt / (double)iterations) + "%");
        
        return (100.0 * (double)cnt / (double)iterations);
    }
}
