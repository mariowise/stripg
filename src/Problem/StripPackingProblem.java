package Problem;

import java.util.ArrayList;



import Data.ListData;
import Data.Pieza;
import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;

public class StripPackingProblem extends GPProblem implements SimpleProblemForm {

	public static final String P_DATA = "data";
	public ArrayList<Pieza> listaFinalcurrent = new ArrayList<Pieza>();
	public ArrayList<Pieza> listaOrginalcurrent = new ArrayList<Pieza>();
	public int posMayorcurrent;
	public int posMenorcurrent;
	public int posFirstcurrent;
	public int posLastcurrent;
	public int posRandomcurrent;
	
	public void setup(final EvolutionState state, Parameter base)
    {
    
    super.setup(state,base);

   
    if (!(input instanceof ListData))
        state.output.fatal("GPData class must subclass from " + ListData.class,
            base.push(P_DATA), null);
    }

@Override
public void evaluate(EvolutionState es, Individual indvdl, int i, int i1) {
    
    if(!indvdl.evaluated){
        
    	ListData input = (ListData)(this.input);
    	
        
    }
    
    
}
	
}
