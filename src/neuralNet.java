import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class neuralNet {

    private final int numLayers;
    private final int inpLayerLen;
    private final int outLayerLen; //Number of layers, input layer size, output layer size.
    private final double[][] biases;
    private final double[][][] weightMatrices;

    public neuralNet(int...l){ //Variable number of args (number of layers is variable)
        numLayers = l.length; inpLayerLen = l[0]; outLayerLen = l[numLayers-1];
        biases = new double[numLayers-1][];
        weightMatrices = new double[numLayers-1][][]; //One less weight matrix than number of layers.
        Random r = new Random();

        for(int i = 1; i < numLayers; i++){
            biases[i-1] = new double[l[i]];
            Arrays.fill(biases[i-1], r.nextGaussian());
            double [][] weights = new double[l[i]][l[i-1]];
            for(double[] row: weights){Arrays.fill(row, r.nextGaussian());}
            weightMatrices[i-1] = weights;
        }
    }

    public void dispBiases(){
        System.out.println("Biases: ");
        for(double[] i : biases){
            mm.displayVector(i);
        }
    }

    public void dispWeights(){
        System.out.println("Weights: ");
        for(double[][] i : weightMatrices){
            mm.displayMatrix(i);
        }
    }

    public static double[] softMax(double[] layer){
        double[] dist = new double[layer.length];
        double total = 0;
        for (int i = 0; i < layer.length; i++){
            dist[i] = Math.exp(layer[i]);
            total += dist[i];
        }
        for (int i = 0; i < layer.length; i++){
            dist[i] /= total;
        }
        return dist;
    }

    public static double sigmoid(double x){ //Activation function
        return 1/(1+Math.exp(x));
    }
    public static double relu(double x) {return Math.max(0, x);}

    private double[] actVector(double[] vect){ //Applies activation function to vector.
        double[] actVect = new double[vect.length];
        for(int i = 0; i < vect.length; i++){actVect[i] = sigmoid(vect[i]);}
        return actVect;
    }

    public double[] forwardPass(double[] inputs){
        for (int i = 1; i < numLayers; i++){
            inputs = actVector(mm.add(mm.mult(weightMatrices[i-1], inputs), biases[i-1]));
        }
        return softMax(inputs);
    }

    public void train(double[][] inputBatch, double[] outputs){ //Parameters must have same length
        for (int i = 0; i < inputBatch.length; i++){
            double grad = 1;
//            test(inputBatch[i]);
//            for (int j = 0; j < outLayerLen; j++){
//                double a = layers[numLayers-1][j];
//                double factor = 2*(a - outputs[i])*a*(1-a); //This factor only depends on the neuron
//                for (int k = 0; k < layers[numLayers-2].length; k++){
//                    double learningRate = 0.1;
//                    weightMatrices[numLayers-2][j][k] -= learningRate *factor*layers[numLayers-2][k]; //A little nudge - CHECK ORDERING OF INDEXING.
//                } //EXTEND THIS TO PROPAGATE THROUGH THE REST OF THE WEIGHT MATRICES. ALMOST THERE...
//            }
        }
    }

}


