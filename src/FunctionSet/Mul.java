package FunctionSet;

import Data.DoubleData;
import ec.gp.*;
import ec.*;

public class Mul extends GPNode{

	public String toString() { return "*"; }

    public int expectedChildren() { return 2; }

    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {

        double result;
        DoubleData rd = ((DoubleData)(gpdata));

        children[0].eval(es, i, gpdata, adfs, gpi, prblm);
        result = rd.x;

        children[1].eval(es, i, gpdata, adfs, gpi, prblm);
        rd.x = result * rd.x;
    }
    
	
}
