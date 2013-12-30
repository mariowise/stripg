package Problem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public ListData data = new ListData();
	
	
	
	public void setup(final EvolutionState state, Parameter base)
    {
    
    super.setup(state,base);

   
    if (!(input instanceof ListData))
        state.output.fatal("GPData class must subclass from " + ListData.class,
            base.push(P_DATA), null);
    
    	try {
			data.listaOrginal = lectura("/home/arthen/workspace/PGspp/src/B1.txt");
			data.listaFinal = new ArrayList<Pieza>();
		    data.cantPiezas = data.listaOrginal.size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

@Override
public void evaluate(EvolutionState es, Individual indvdl, int i, int i1) {
    
    if(!indvdl.evaluated){
        
    	ListData input = data;
    	int hits = 0;
        int sum = 0;
        int expectedResult;
        int result;
       
        
        
        expectedResult = 0;
        ((GPIndividual)indvdl).trees[0].child.eval(
                es,i1,input,stack,((GPIndividual)indvdl),this);
        
        
        
        if(data.listaOrginal.isEmpty()){
        	result = 0;
        }
        else{
        	result = 1;
        }
        
        if(result == 0) hits++;
        sum+=result;
        
        
        
        
        KozaFitness f = ((KozaFitness)indvdl.fitness);
        f.setStandardizedFitness(es,(float)sum);
        f.hits = hits;
        indvdl.evaluated = true;
    }
    
    
}

public ArrayList<Pieza> lectura(String path) throws IOException{
	
	FileReader fr = new FileReader(path);
	BufferedReader bf = new BufferedReader(fr);
	
	String sCadena;
	int index = 0;
	ArrayList<Pieza> entrada = new ArrayList<Pieza>();
	
	while ((sCadena = bf.readLine())!=null) {
		
		   if(index == 0){
			   index++;
		   }
		   else{
			   String[] valores = sCadena.split("\t");
			   Pieza newPieza = new Pieza(Integer.parseInt(valores[0]), ((double)Integer.parseInt(valores[1])), ((double)Integer.parseInt(valores[2])));
			   entrada.add(newPieza);
		   }
		}
	bf.close();
	return entrada;
}
	
}
