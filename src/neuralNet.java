import java.util.Arrays;

public class neuralNet {

    private int numLayers, inpLayerLen, outLayerLen; //Number of layers, input layer size, output layer size.
    private double[][] layers, biases;
    private double[][][] weightMatrices;
    private double learningRate = 0.1;

    public neuralNet(int...l){ //Variable number of args (number of layers is variable)
        numLayers = l.length; inpLayerLen = l[0]; outLayerLen = l[numLayers-1];
        layers = new double[numLayers][]; biases = new double[numLayers-1][];
        weightMatrices = new double[numLayers-1][][]; //One less weight matrix than number of layers.

        layers[0] = new double[l[0]]; //Only input layer needs to be initialised and filled. REMOVE THIS LATER
        Arrays.fill(layers[0], 0.5);

        for(int i = 1; i < numLayers; i++){
            layers[i] = new double[l[i]]; biases[i-1] = new double[l[i]];
            Arrays.fill(biases[i-1], 0.5);
            double [][] weights = new double[l[i]][l[i-1]];
            for(double[] row: weights){Arrays.fill(row, 0.5);}
            weightMatrices[i-1] = weights;
        }
    }

    public void dispLayers(){
        System.out.println("Layers: ");
        for(double[] i : layers){
            mm.displayVector(i);
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

    private double sigmoid(double x){ //Activation function
        return 1/(1+Math.exp(x));
    }

    private double[] actVector(double[] vect){ //Applies activation function to vector.
        double[] actVect = new double[vect.length];
        for(int i = 0; i < vect.length; i++){actVect[i] = sigmoid(vect[i]);}
        return actVect;
    }

    public void forwardPass(){
        for (int i = 1; i < numLayers; i++){
            layers[i] = actVector(mm.add(mm.mult(weightMatrices[i-1], layers[i-1]), biases[i-1]));
        }
    }

    public int test(double[] inputs){
        if (inputs.length == inpLayerLen){
            for (int i = 0; i < inpLayerLen; i++){
                layers[0][i] = inputs[i];
            }
        }
        forwardPass();
        int maxIndex = 0;
        double max = 0;
        for (int i = 0; i < outLayerLen; i++) {
            if (layers[numLayers - 1][i] > max) {
                max = layers[numLayers - 1][i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void train(double[][] inputBatch, double[] outputs){ //Parameters must have same length
        for (int i = 0; i < inputBatch.length; i++){
            double grad = 1;
            test(inputBatch[i]);
            for (int j = 0; j < outLayerLen; j++){
                double a = layers[numLayers-1][j];
                double factor = 2*(a - outputs[i])*a*(1-a); //This factor only depends on the neuron
                for (int k = 0; k < layers[numLayers-2].length; k++){
                    weightMatrices[numLayers-2][j][k] -= learningRate*factor*layers[numLayers-2][k]; //A little nudge - CHECK ORDERING OF INDEXING.
                } //EXTEND THIS TO PROPAGATE THROUGH THE REST OF THE WEIGHT MATRICES. ALMOST THERE...
            }
        }
    }

}


