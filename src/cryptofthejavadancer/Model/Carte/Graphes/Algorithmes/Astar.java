/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.Carte.Graphes.Algorithmes;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Graphes.Graphe;
import cryptofthejavadancer.Model.Carte.Graphes.Noeud;
import cryptofthejavadancer.Model.Carte.Graphes.VertexCouple;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author av414423
 */
public class Astar {

    private Integer infini;
    private Graphe graph;
    private Noeud debut;
    private Noeud fin;
    
    private HashMap<Noeud, Integer> distance;
    private HashMap<Noeud, Boolean> visite;
    private HashMap<Noeud, Noeud> predecesseur;
    
    private ArrayList<Noeud> chemin;

    public Astar(Graphe graph) {
        this.graph = graph;
        
        distance = new HashMap();
        visite = new HashMap();
        predecesseur = new HashMap();
        
        chemin = new ArrayList();
        infini = null;
    }
    
    
    public void initialisation(){
        int max = getInfini();
        for (Noeud n : graph.getNoeuds().values()){
            distance.put(n, max);
            visite.put(n, Boolean.FALSE);
            predecesseur.put(n, null);
        }
        
        distance.put(debut, 0);
    }
    
    public Integer infini(){
        Integer tot = 0;
        
        for (Integer i : graph.getLabels().values()){
            tot += i;
        }
        return tot;
    }
    
    public void Relaxing(Noeud a,Noeud b){
        if(this.graph.getLabels().get(new VertexCouple(a,b)) != null){
            if(distance.get(b)>(distance.get(a)+this.graph.getLabels().get(new VertexCouple(a,b)))){
                distance.put(b, distance.get(a)+this.graph.getLabels().get(new VertexCouple(a,b)));
                predecesseur.put(b, a);
            }
        }
    }
    
    public Noeud plusProcheSommet(){
      int min = getInfini()+1;
      Noeud plusProche = null;
        for (Noeud v : distance.keySet()){
            if (visite.get(v)==false){
                if (distance.get(v)<min){
                    plusProche = v;
                    min = distance.get(v);
                }
            }
        }
        return plusProche;
}
    
 public int heuristic(Noeud v1, Noeud v2){
     
     double disEuclid;
     
     Case c1 = v1.getCase();
     Case c2 = v2.getCase();
     
     double x1 = c1.getColonne();
     double y1 = c1.getLigne();
     
     double x2 = c2.getColonne();
     double y2 = c2.getLigne();
     
     disEuclid = Math.sqrt((Math.pow(x2-x1, 2) + Math.pow(x2-x1, 2)));
     
     return (int) (distance.get(v1)+disEuclid);
 }
    
 public void calcul(Noeud _debug, Noeud _fin){
        this.debut = _debug;
        this.fin = _fin;
        this.initialisation();
        
}


/////////////////////////////////getter////////////////////////////////////////////
    
    public Graphe getGraph() {
        return graph;
    }

    public Noeud getDébut() {
        return debut;
    }

    public Noeud getFin() {
        return fin;
    }

    public HashMap<Noeud, Integer> getDistance() {
        return distance;
    }

    public HashMap<Noeud, Boolean> getVisite() {
        return visite;
    }

    public HashMap<Noeud, Noeud> getPredecesseur() {
        return predecesseur;
    }

    public ArrayList<Noeud> getChemin() {
        return chemin;
    }

    public Integer getInfini() {
        if (infini == null){
            infini = 0;
        
        for (Integer vLabel : graph.getLabels().values()){
            infini += vLabel;
        }
        infini ++;
        }
        return infini;
    }
    
    
//////////////////////////////////////setter//////////////////////////////////

    public void setGraph(Graphe graph) {
        this.graph = graph;
    }

    public void setDébut(Noeud debut) {
        this.debut = debut;
    }

    public void setFin(Noeud fin) {
        this.fin = fin;
    }

    public void setDistance(HashMap<Noeud, Integer> distance) {
        this.distance = distance;
    }

    public void setVisite(HashMap<Noeud, Boolean> visite) {
        this.visite = visite;
    }

    public void setPredecesseur(HashMap<Noeud, Noeud> predecesseur) {
        this.predecesseur = predecesseur;
    }

    public void setChemin(ArrayList<Noeud> chemin) {
        this.chemin = chemin;
    }  
    
}

