package Terminals;

import java.util.ArrayList;

import Problem.StripPackingProblem;
import Data.ListData;
import Data.Pieza;
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
    	if(!ld.listaOrginal.isEmpty()){
    		ld.posObjetivo = mayor(((StripPackingProblem)prblm).data.listaOrginal);
    		ld.vacio = false;
    	}
    	else{
    		ld.vacio = true;
    	}
    }
    
    public int mayor(ArrayList<Pieza> listaOriginal){
    	
    	int idPiezaMayor = 0;
    	double largoPieza = 0;
    	
    	for(int i = 0; i < listaOriginal.size(); i++){
    		if(listaOriginal.get(i).getLargoPieza() > largoPieza){
    			
    			idPiezaMayor = i;
    			largoPieza = listaOriginal.get(i).getLargoPieza();
    			
    		}
    	}
    	
    	return idPiezaMayor;
    	
    } 
    
}
