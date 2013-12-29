package Terminals;

import Data.ListData;
import Problem.StripPackingProblem;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Random extends GPNode{
	
	public String toString() { return "RANDOM"; }

    public int expectedChildren() { return 0; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
    	ListData ld = ((ListData)gpdata);
    	ld.posObjetivo = 0 + (int)(Math.random() * ((((StripPackingProblem)prblm).listaOrginalcurrent.size()-1 - 0) + 1));
        
    }

}
