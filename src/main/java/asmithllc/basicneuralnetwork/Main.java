package asmithllc.basicneuralnetwork;

/**
 *
 * @author csmit
 */
public class Main {
            
    public static void main(String[] args){
        for (float i = 1; i < 3; i += 0.1){
            double sum = 0;

            //Test our training
            for (int j = 0; j < 50; j++){
                BasicNeuralNetwork BNN = new BasicNeuralNetwork(50, 50, i);
                BNN.train(10000);
                sum += BNN.test(200);
            }

            System.out.println("Bias " + i + " Results: " + (sum / 50.0));
        }
    }
}
