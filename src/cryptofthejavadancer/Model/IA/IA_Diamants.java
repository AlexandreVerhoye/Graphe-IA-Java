/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Astar;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Noeud;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Objet.Objet;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class IA_Diamants extends IA {
    
     private ArrayList<Objet> diamonds;
     private boolean tour;
     private ArrayList<Noeud> cheminLoc;
     private Dijkstra dijkstra;
     private Astar algoAs;
     

    public IA_Diamants(Entite _entite) {
        super(_entite);
        algoAs = null;
        diamonds = new ArrayList<>();
        tour = false;
        cheminLoc = new ArrayList<>();
        dijkstra = null;
        
    }

    @Override
    public Type_Action action() {
        
        Type_Action retour = Type_Action.attendre;
        
        if(tour){
            this.algoAs = new Astar(this.getMap().getGraphe());
            this.algoAs.calcul(this.getMap().getDebut(), this.getMap().getFin());
            
            for (Objet o : this.entite.getMap().getListeObjet()){
                if (o.getType() == Type_Objet.Diamant){
                    this.diamonds.add(o);
                }
            }
            
            
            
            
            
            
            
            
        }
    
    
    return null;
   
    }
    
    public Objet diamantLePlusProche(){
        Objet diamant;
        int distMin = this.algoAs.getInfini();
        for (Objet o : this.diamonds){

    }
         return null;
        }
        
    }
    



    
  
