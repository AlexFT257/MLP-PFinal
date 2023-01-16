package backpropagation;

public class ReLU extends TransferFunction {
    @Override
    public double evaluate(double value){
        return Math.max(0, value);
    }

    @Override
    public double evaluateDerivate(double value){
        return value > 0 ? 1 : 0;
    }
}
