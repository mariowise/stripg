package Problem;

import Data.DoubleData;
import ec.gp.*;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import ec.*;

public class MultiValuedRegression extends GPProblem implements SimpleProblemForm {
	
	public static final String P_DATA = "data";

    public double currentX;
    public double currentY;

    
    public void setup(final EvolutionState state, Parameter base)
        {
        
        super.setup(state,base);

       
        if (!(input instanceof DoubleData))
            state.output.fatal("GPData class must subclass from " + DoubleData.class,
                base.push(P_DATA), null);
        }
    
    @Override
    public void evaluate(EvolutionState es, Individual indvdl, int i, int i1) {
        
        if(!indvdl.evaluated){
            
            DoubleData input = (DoubleData)(this.input);
            int hits = 0;
            double sum = 0.0;
            double expectedResult;
            double result;
            
            for (int y=0;y<10;y++)
                    {
                    currentX = es.random[i1].nextDouble();
                    currentY = es.random[i1].nextDouble();
                    expectedResult = currentX*currentX*currentY + currentX*currentY + currentY;
                    ((GPIndividual)indvdl).trees[0].child.eval(
                        es,i1,input,stack,((GPIndividual)indvdl),this);

                    result = Math.abs(expectedResult - input.x);
                    if (result <= 0.01) hits++;
                    sum += result;                  
                    }

            KozaFitness f = ((KozaFitness)indvdl.fitness);
            f.setStandardizedFitness(es,(float)sum);
            f.hits = hits;
            indvdl.evaluated = true;
        }
        
        
    }
}
