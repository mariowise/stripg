package Terminals;

import Data.ListData;
import Problem.StripPackingProblem;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class First extends GPNode{

	public String toString() { return "FIRST"; }

    public int expectedChildren() { return 0; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
    	ListData ld = ((ListData)gpdata);
    	if(!ld.listaOrginal.isEmpty()){
    		ld.posObjetivo = 0;
    		ld.vacio = false;
    	}
    	else{
    		ld.vacio = true;
    	}
    }
}
