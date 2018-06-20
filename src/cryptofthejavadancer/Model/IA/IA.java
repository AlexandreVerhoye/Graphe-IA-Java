package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Astar;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Graphe;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.Entites.Entite;

/**
 * Classe générique des IA
 * @author Matthieu
 */
public abstract class IA {

    public Entite entite;         
    protected Dijkstra algoDij;
    protected Astar algoAs; //Entité liée à l'IA
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    
    public IA(Entite _entite) {
        this.entite = _entite;
    }
    
    public Graphe getGraphe(){
        return this.algoAs.getGraph();
    }
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Renvoie l'entité
    public Entite getEntite() {
        return this.entite;
    }
    
    //Renvoie la case de l'entité
    public Case getCase() {
        return this.entite.getCase();
    }
    
    //Renvoie la carte du jeu
    public Map getMap() {
        return this.entite.getMap();
    }
    
    //Méthode appelé à chaque pulsation de la musique et devant renvoyer l'action à réaliser
    public abstract Type_Action action();
//------------------------------------------------------------------------------

}
