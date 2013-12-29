package Data;

import java.util.ArrayList;

import ec.gp.GPData;

public class ListData extends GPData{

	public ArrayList<Pieza> listaFinal = new ArrayList<Pieza>();
	public ArrayList<Pieza> listaOrginal = new ArrayList<Pieza>();
	
	public void copyTo(final GPData gpd){ 
        ((ListData)gpd).listaFinal = listaFinal; 
        ((ListData)gpd).listaOrginal=listaOrginal;
    }
}
