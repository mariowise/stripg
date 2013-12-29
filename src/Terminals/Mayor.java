package Terminals;

import Problem.StripPackingProblem;
import Data.ListData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Mayor extends GPNode{

	public String toString() { return "MAYOR"; }

    public int expectedChildren() { return 0; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
    	ListData ld = ((ListData)gpdata);
    	ld.posMayor = ((StripPackingProblem)prblm).posMayorcurrent;
        
    }
    
}
