package Problem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



import Data.ListData;
import Data.Pieza;
import Heuristic.Phenotype;
import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import Heuristic.Phenotype;


public class StripPackingProblem extends GPProblem implements SimpleProblemForm {

	public static final String P_DATA = "data";

	public ListData data = new ListData();
	public static ListData dataOriginal = new ListData();


	public void setup(final EvolutionState state, Parameter base)
	{
		

		super.setup(state,base);
		 

		if (!(input instanceof ListData))
			state.output.fatal("GPData class must subclass from " + ListData.class,
					base.push(P_DATA), null);

		try {
			data.listaOrginal = lectura("src/B1.txt");
			data.copyTo(dataOriginal);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void evaluate(EvolutionState es, Individual indvdl, int i, int i1) {

		if(!indvdl.evaluated){

			dataOriginal.copyTo(data);
			ListData input = data;
			int hits = 0;
			int sum = 0;
			double expectedResult;
			double result;

			

			expectedResult = data.optimo;

			
			
			
			
			((GPIndividual)indvdl).trees[0].child.eval(
					es,i1,input,stack,((GPIndividual)indvdl),this);

				
			
				
				
				Phenotype fenoma = new Phenotype(data.anchoGlobal);
				int x;

				for(x = 0; x<data.listaFinal.size(); x++){
					//System.out.println("Este sera el push: "+x+" de la pieza: "+data.listaFinal.get(x).getNumPieza());
					fenoma.push(data.listaFinal.get(x));

				}
				
			
				
				double fitness = fenoma.fitness();
				
				System.out.println(fitness);
		
				result = Math.abs(expectedResult - fitness);

				if (result <= (expectedResult)*0.10) hits++;
				sum += result;
			
				//data.listaFinal.clear();
				
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
			   String[] datosPrimera = sCadena.split("\t");
			   data.cantPiezas = Integer.parseInt(datosPrimera[0]);
			   data.anchoGlobal = (Integer.parseInt(datosPrimera[1]));
			   data.optimo = ((double)Integer.parseInt(datosPrimera[2]));
			   			   
			   index++;
		   }
		   else{
			   String[] valores = sCadena.split("\t");
			   Pieza newPieza = new Pieza(Integer.parseInt(valores[0]), (Integer.parseInt(valores[1])), (Integer.parseInt(valores[2])));
			   entrada.add(newPieza);
		   }
		}
	bf.close();
	return entrada;
}

public void escritura(ArrayList<Pieza> piezas){
	
	for(int i = 0; i < piezas.size(); i++){
		
		System.out.print(piezas.get(i).getNumPieza()+" ");
		
	}
	
	System.out.println();
	
}
	
}
