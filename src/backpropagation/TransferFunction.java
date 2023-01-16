/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpropagation;

/**
 *
 * @author lucacrot
 */
public abstract class TransferFunction {
    
    public abstract double evaluate(double value);
    public abstract double evaluateDerivate(double value);
    
}

class Heavyside extends TransferFunction {
    @Override
    public double evaluate(double value){
        return value >= 0.0 ? 1.0 : 0.0;
    }

    @Override
    public double evaluateDerivate(double value){
        return 1.0;
    }
}

class Sign extends TransferFunction {
    @Override
    public double evaluate(double value){
        return value >= 0.0 ? 1.0 : -1.0;
    }

    @Override
    public double evaluateDerivate(double value){
        return 1.0;
    }
}

class Hyperbolic extends TransferFunction {
    @Override
    public double evaluate(double value){
        return Math.tanh(value);
    }

    @Override
    public double evaluateDerivate(double value){
        return 1 - Math.pow(value, 2);
        /*double cosh = Math.cosh(value);
        double sech = 1/cosh;
        return sech*sech;*/
    }
}
