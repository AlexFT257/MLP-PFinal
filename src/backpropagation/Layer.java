package backpropagation;

import java.util.ArrayList;

public class Layer {
    private ArrayList<Neuron> neuronas;

    public Layer() {
        neuronas = new ArrayList<>();
    }

    public void addNeuron(Neuron neurona) {
        neuronas.add(neurona);
    }

    public Neuron getNeuron(int index) {
        return neuronas.get(index);
    }

    public int layerSize() {
        return neuronas.size();
    }
}