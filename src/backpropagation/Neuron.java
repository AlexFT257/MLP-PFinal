package backpropagation;

public class Neuron {
    private double[] weights;
    private double threshold;
    private double delta;
    private double value;
    private TransferFunction function;

    public Neuron(int inputs) {
        if (inputs != 0)
            weights = new double[inputs];
        threshold = Math.random();
        delta = Math.random();
        value = Math.random();
        if (inputs != 0) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Math.random();
            }
        }
        function = new Hyperbolic();
    }

    public TransferFunction getFuncionTransferencia() {
        return function;
    }

    public void setFuncionTransferencia(TransferFunction fun) {
        function = fun;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double valor) {
        value = valor;
    }

    public double getWeight(int index) {
        return weights[index];
    }

    public void setWeight(int index, double weight) {
        weights[index] = weight;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double bias) {
        threshold = bias;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delt) {
        delta = delt;
    }

    public int getWeightsSize() {
        if (weights != null) {
            return weights.length;
        } else
            return 0;
    }
}