package FunctionSet;

import Data.ListData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class While extends GPNode{

	public String toString() { return "WHILE"; }
    public int expectedChildren() { return 2; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
        
        ListData rd = ((ListData)(gpdata));
        boolean vacio;
        children[0].eval(es, i, gpdata, adfs, gpi, prblm);
        vacio = rd.vacio;
        
        while(vacio == false){
        	
        	children[1].eval(es, i, gpdata, adfs, gpi, prblm);
        	vacio = rd.vacio;
        }
    }
	
}
