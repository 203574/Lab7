package it.polito.tdp.dizionario.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.dizionario.db.ParolaDAO;

import java.util.*;

public class DizionarioModel 
{
	int numero;
	private SimpleGraph<String, DefaultEdge> paroleGraph;
	private List<String> listaParole;
	private List<String> connessi = new ArrayList<String>();


	public List<String> getConnessi() 
	{
		return connessi;
	}

	public int getNumero() 
	{
		return numero;
	}

	public void setNumero(int numero) 
	{
		this.numero = numero;
	}

	public void generaGrafo() 
	{
		 paroleGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		 listaParole = new ArrayList<String>();
		 ParolaDAO pdao= new ParolaDAO();
		 listaParole = pdao.getParole(numero);
		 
		 for (String string : listaParole)
		 {
			 paroleGraph.addVertex(string);
		 }
		 
		 for (int i = 0; i < listaParole.size(); i++)
		 {
			 String s1 = listaParole.get(i);
			 for (int j = i+1; j < listaParole.size(); j++)
			 {
				String s2 = listaParole.get(j);
				if(differisconoLettera(s1, s2))
				{
					paroleGraph.addEdge(s1, s2);
				}
			}
		}
		connessi.clear();
	}
	
	public boolean differisconoLettera(String s, String t)
	{
		int v = 0;
		char[]  s1 = s.toCharArray();
		char[] s2 = t.toCharArray();
		
		for (int i = 0; i < numero; i++)
		{
			if(s1[i] != s2[i])
			{
				v++;
			}
		}
		if(v == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<String> trovaVicini(String p)
	{
		List<String> vicini = new ArrayList<String>();
		for (DefaultEdge e : paroleGraph.edgesOf(p))
		{
			vicini.add(Graphs.getOppositeVertex(paroleGraph, e, p));
		}
		return vicini;
	}
	
	public void trovaConnessi(String p)
	{
		/*Queue<String>coda = new LinkedList<String>();
		coda.add(p);
		while(!coda.isEmpty())
		{
			String c = coda.remove();
			if(!connessi.contains(c))
			{
				connessi.add(c);
				for (String s : Graphs.neighborListOf(paroleGraph, c))
				{
					coda.add(s);
				}
			}
		}*/
		if(connessi.contains(p))
		{
			return;
		}
		connessi.add(p);
		for (String s : Graphs.neighborListOf(paroleGraph, p))
		{
			trovaConnessi(s);
		}
	}
}
