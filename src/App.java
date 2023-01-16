import backpropagation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class App {
    // lista que contiene todas las entradas
    static ArrayList<Entrada> entradas = new ArrayList<>();
    static MultiLayerPerceptron net;

    public static void main(String[] args) throws Exception {
        // variables para el entrenamiento de la red
        double trainingError, testError;
        // cargamos la red si existe
        // net = MultiLayerPerceptron.load("net.obj");
        // si no existe la red, la creamos
        // if (net == null) {
            // definimos la red
            // TODO: ver si es necesario cambiar el numero de neuronas en las capas
            int[] layers = { 783, 256, 2 };
            net = new MultiLayerPerceptron(layers, 1);
            for (Layer capa : net.getCapas()) {
                for (int i = 0; i < capa.layerSize(); i++) {
                    // TODO: buscar una funcion de transferencia que se adapte mejor al problema
                    capa.getNeuron(i).setFuncionTransferencia(new Sigmoidal());
                }
            }
        // }

        /* Entrenamiento */
        // cargamos los datos de entrenamiento
        readingData("train.csv");
        // entrenamos la red
        int epochs = entradas.size();
        trainingError = 0;
        for (int i = 0; i < epochs; i++) {
            double error = 0;
            error = net.backPropagate(entradas.get(i));
            System.out.println("Error en el paso: " + i + " es: " + error);
            trainingError += error;
        }
        // imprime mensaje de entrenamiento terminado
        System.out.println("Entrenamiento terminado");
        // calculamos el error de entrenamiento
        double trainingErrorAvg = trainingError / epochs;
        System.out.println("Error de entrenamiento: " + trainingErrorAvg);
        // guardamos la red
        // net.save("net.obj");
        // cargamos la red

        // generamos una pause para que el usuario pueda ver los resultados
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        /* Testeo */

        entradas.clear();
        // cargamos los datos de testeo
        readingData("test.csv");
        // testeamos la red
        epochs = entradas.size();
        testError = 0;
        for (int i = 0; i < epochs; i++) {
            double error = 0;
            ArrayList<Double> outputTest = net.execute(entradas.get(i));
            for (int j = 0; j < entradas.get(i).sizeOutput(); j++) {
                error += Math.abs(outputTest.get(j) - entradas.get(i).getOutput(j));
            }
            error = error / entradas.get(i).sizeOutput();
            System.out.println("Error en el paso: " + i + " es: " + error);
            testError += error;
        }
        // calculamos el error de testeo
        double testErrorAvg = testError / epochs;
        System.out.println("Testeo terminado");
        System.out.println("Error de entrenamiento: " + testErrorAvg);
        System.out.println("Error de testeo: " + testErrorAvg);
        System.out.println("Diferencia: " + Math.abs(trainingErrorAvg - testErrorAvg));

        if (testErrorAvg > trainingErrorAvg) {
            System.out.println("Overfitting");
            // es decir, la red no aprendio, sino que se memorizo los datos de entrenamiento
        }
    }

    private static void readingData(String file) {
        ArrayList<Entrada> orderedEntradas = new ArrayList<>();
        File f = new File(file);
        try {
            System.out.println("Leyendo archivo");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String aux = br.readLine(); // leemos la primera linea
            while (aux != null) { // esto actua como el while(!feof)
                String[] values = aux.split(";");
                // Cada linea tiene 785 valores, los primeros 784 son los valores de la imagen
                // y el ultimo representa el numero que representa la imagen
                if (values.length == 785) {
                    ArrayList<Double> inputs = new ArrayList<>();
                    for (int i = 0; i < 783; i++) {
                        double x = Double.parseDouble(values[i]);
                        inputs.add(x);
                    }
                    ArrayList<Double> output = new ArrayList<>();
                    double x = Double.parseDouble(values[784]);
                    output.add(x);
                    orderedEntradas.add(new Entrada(inputs, output));
                }
                aux = br.readLine(); // leemos una nueva linea
            }
            // cerramos todo
            br.close();
            fr.close();
            entradas = new ArrayList<>();
            entradas.add(orderedEntradas.get(0));
            Random rnd = new Random();
            for (int i = 1; i < orderedEntradas.size(); i++) {
                int s = entradas.size();
                int x = rnd.nextInt(s + 1);
                entradas.add(x, orderedEntradas.get(i));
            }
        } catch (IOException e) {
            System.err.println("Archivo no leido: " + e.getMessage());
        }
    }
}
