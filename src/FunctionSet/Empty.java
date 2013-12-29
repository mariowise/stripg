package FunctionSet;

import Data.ListData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Empty extends GPNode{
	public String toString() { return "EMPTY"; }
    public int expectedChildren() { return 1; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
        
        ListData rd = ((ListData)(gpdata));
        
        children[0].eval(es, i, gpdata, adfs, gpi, prblm);
        if(rd.listaOrginal.isEmpty()){
        	
        	rd.vacio = true;
        	
        }
        else{
        	
        	rd.vacio = false;
        	
        }
    }
}
