package FunctionSet;

import Data.ListData;
import Problem.StripPackingProblem;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class RandomPush extends GPNode{

	public String toString() { return "RANDOM_PUSH"; }
    public int expectedChildren() { return 1; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
        int posicion;
        int random;
        ListData rd = ((ListData)(gpdata));
        
        children[0].eval(es, i, gpdata, adfs, gpi, prblm);
        posicion = rd.posObjetivo;
        random = 0 + (int)(Math.random() * ((10 - 0) + 1));
        if(random >= 5 || rd.listaFinal.size()==0){
        	
        	rd.listaFinal.add(0, rd.listaOrginal.get(posicion));
        	
        }
        else{
        	
        	rd.listaFinal.add(rd.listaFinal.size()-1, rd.listaOrginal.get(posicion));
        	
        }
        
        rd.listaOrginal.remove(posicion);
    }
	
}
