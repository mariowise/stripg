package Terminals;

import Data.DoubleData;
import ec.gp.*;
import ec.*;
import Problem.MultiValuedRegression;

public class X extends GPNode{

	public String toString() { return "x"; }

    public int expectedChildren() { return 0; }

    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
        DoubleData rd = ((DoubleData)(gpdata));
        rd.x = ((MultiValuedRegression)prblm).currentX;
    }
	
}