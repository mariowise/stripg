package Data;

import java.util.ArrayList;

import ec.gp.GPData;

public class ListData extends GPData{

	public ArrayList<Pieza> listaFinal = new ArrayList<Pieza>();
	public ArrayList<Pieza> listaOrginal = new ArrayList<Pieza>();
	public int posMayor;
	public int posMenor;
	public int posFirst;
	public int posLast;
	public int posRandom;
	
	public void copyTo(final GPData gpd){ 
        ((ListData)gpd).listaFinal = listaFinal; 
        ((ListData)gpd).listaOrginal=listaOrginal;
        ((ListData)gpd).posMayor = posMenor;
        ((ListData)gpd).posMenor = posMenor;
        ((ListData)gpd).posFirst = posFirst;
        ((ListData)gpd).posLast = posLast;
        ((ListData)gpd).posRandom = posRandom;
    }
}
