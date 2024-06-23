public class Main {
    public static void main(String[] args) {
       //mm.displayVector(mm.mult(m, v));
       neuralNet nn = new neuralNet(2, 3, 2);

       // Training data - XOR
        double[][] inputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] outputs = {0, 1, 1, 0};

        //nn.train(inputs, outputs);
        nn.dispWeights();
        nn.dispBiases();
    }

    //Plan: Use mean squared error to calc loss. Also find a practical example to apply this nn to. XOR
    //Also have a look a fast matrix multiplication algorithms and use timers to compare.
    //Randomly generate weightmatrices values.
}