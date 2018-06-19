/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.Carte.Graphes;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class Noeud {
    private Graphe Graph; 
    private ArrayList<Noeud> Voisins;
    private Case c;
    
    public Noeud(Graphe graph, Case c){
        this.Graph = graph;
        Voisins = new ArrayList();
        this.c=c;
        
    }
    
    public ArrayList<Noeud> getNeighbours(){
        return this.Voisins;
    }
    
    public void setVoisins(Noeud n){
        Voisins.add(n);
    }
    
    public Case getCase(){
        return this.c;
    }

}
