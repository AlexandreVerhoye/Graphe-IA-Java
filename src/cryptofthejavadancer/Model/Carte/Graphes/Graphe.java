/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.Carte.Graphes;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import java.util.HashMap;

/**
 *
 * @author antoi
 */
public class Graphe {
    private HashMap<Case, Noeud> Noeuds;
    private HashMap<VertexCouple, Integer> Labels;
    
    public Graphe(){
        this.Noeuds = new HashMap();
        this.Labels = new HashMap();
    }
    
    public void addNoeud(Case name){
        Noeud n = new Noeud(this, name);
        this.Noeuds.put(name, n);
    }
    
    public void addEdge(Case name1, Case name2){
        Noeuds.get(name1).setVoisins(Noeuds.get(name2));
    }
    
    public Noeud getNoeud(Case name){
        return Noeuds.get(name);
    }

    public void setLabel(Case c1, Case c2, int i) {
        VertexCouple v = new VertexCouple(Noeuds.get(c1), Noeuds.get(c2));
        this.Labels.put(v, i);
    }
    
    public Integer getLabel(Case c1, Case c2){
        VertexCouple v = new VertexCouple(Noeuds.get(c1), Noeuds.get(c2));
        return this.Labels.get(v);
    }

    public HashMap<VertexCouple, Integer> getLabels(){
        return Labels;
    }
    public HashMap<Case, Noeud> getNoeuds(){
        return Noeuds;
    }
    
    
    
    
}
