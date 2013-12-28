package Terminals;

import Data.DoubleData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.*;
import Problem.MultiValuedRegression;

public class Y extends GPNode{
	
	public String toString() { return "y"; }

    public int expectedChildren() { return 0; }

    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        DoubleData rd = ((DoubleData)(gpdata));
        rd.x = ((MultiValuedRegression)prblm).currentY;
    }
	
}
