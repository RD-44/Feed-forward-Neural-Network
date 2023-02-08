public class mm {
    public static double[] mult(double[][] matrix, double[] vector){
        int height = matrix.length;
        int mWidth = matrix[0].length;
        if(mWidth != vector.length) {
            try {
                throw new Exception("Shape error.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        double [] product = new double[height];
        for (int i = 0; i < height; i++){
            for(int j = 0; j < mWidth; j++){
                product[i] += matrix[i][j] * vector[j];
            }
        }
        return product;
    }

    public static double[] add(double[] v1, double[] v2){ //Vector Addition.
        if(v1.length != v2.length) {
            try {
                throw new Exception("Shape error.");
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        double [] sum = new double[v1.length];
        for (int i = 0; i < v1.length; i++){
            sum[i] = v1[i] + v2[i];
        }
        return sum;
    }

    public static void displayMatrix(double[][] matrix){
        for (int i = 0; i < matrix.length; i++){
            System.out.print("[");
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
        System.out.println("----");
    }

    public static void displayVector(double[] vector){
        for (int i = 0; i < vector.length; i++){
            System.out.println(" " + vector[i] + " ");
        }
        System.out.println("----");
    }
}
