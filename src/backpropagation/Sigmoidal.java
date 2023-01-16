package backpropagation;


public class Sigmoidal extends TransferFunction {
    @Override
    public double evaluate(double value){
        return 1 / (1 + Math.pow(Math.E, - value));
    }

    @Override
    public double evaluateDerivate(double value){
        /*double exp = Math.exp(value);
        double den = 1 + exp;
        double den2 = den*den;
        return exp/den2;*/
        return (value - Math.pow(value, 2));
    }
}