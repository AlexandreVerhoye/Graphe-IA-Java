package cryptofthejavadancer.Model.Carte;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Graphe;
import cryptofthejavadancer.Model.Carte.Graphes.Noeud;
import cryptofthejavadancer.Model.Carte.Parseur.Fabrique_Cases;
import cryptofthejavadancer.Model.Carte.Parseur.Parseur;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Entite_Cadence;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.Objet.Objet;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Carte du jeu
 * @author Matthieu
 */
public class Map {

    private final HashMap<Coordonnees,Case> hashMapCases;                       //Stockage par coordonnées des cases
    private final ArrayList<Entite> listeEntite;                                //Liste des entités    
    private final ArrayList<Case> listeCase;                                    //Liste des cases
    private final ArrayList<Objet> listeObjet;                                  //Liste des objets
    private Coordonnees depart;                                                 //Position du point de départ
    private Coordonnees fin;   
    private Noeud debut;
    private Noeud fina;
//Position de la sortie
    
    private Entite_Cadence joueur;                                              //Cadence
    private Graphe graphe_avance;
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Map() {
        //Initialisation
        this.hashMapCases = new HashMap<>();
        this.listeCase = new ArrayList<>();
        this.listeEntite = new ArrayList<>();
        this.listeObjet = new ArrayList<>();
        this.joueur = null;
        this.graphe_avance= new Graphe();
    }
    
    //créer la map à partir d'un fichier texte
    public void chargerFichier(String adresseFichier) throws IOException {
        Parseur parseur = new Parseur(adresseFichier,this);
        parseur.lecture();
        System.out.println("Niveau chargé.");
       
        
            //Il est possible de rajouter ICI des choses se réalisant juste après le chargement de la carte...
            System.out.println("Nombre de cases :" +this.listeCase.size());
            System.out.println("Nombre d'objets :" +this.listeObjet.size());
            System.out.println("Nombre d'entités :" +this.listeEntite.size());
        int i;
        int nbDiamant = 0;
            
            for (Objet o : listeObjet){
                System.out.println(o.getType());
                if (o.getType() == Type_Objet.Diamant){
                    nbDiamant++;
                }
            }
            for (Entite e : listeEntite){
                System.out.println(e.getType());
                }
            System.out.println("Nombre de diamants :" +nbDiamant);
            System.out.println("La sortie du niveau se situe en :" +fin.getLigne()+" / "+fin.getColonne());
            System.out.println("La position de Cadence est :"+this.joueur.getCase().getLigne()+" / "+this.joueur.getCase().getLigne());
            System.out.println("L'objet a droite de Cadence est :"+ this.getCase(this.joueur.getCase().getLigne(),this.joueur.getCase().getColonne()-1).getType());
            genererGrapheAvance();
            
            for (Case c1:listeCase){
                System.out.println("");
                for (Case c2:listeCase){
                    if (graphe_avance.getLabel(c1,c2) != null){
                        System.out.print(graphe_avance.getLabel(c1,c2));
                    }
                    else{
                        System.out.print(0);
                    }
                }
            }
            
            debut = graphe_avance.getNoeud(this.getCase(depart.getLigne(), depart.getColonne()));
            fina = graphe_avance.getNoeud(this.getCase(fin.getLigne(), fin.getColonne()));
            Dijkstra dij = new Dijkstra(this.graphe_avance);
            System.out.println("\n");
            dij.calcul(debut, fina);
            /*listeCase.stream().map((c1) -> {
                System.out.println("");
            return c1;
            
            
        }).forEachOrdered((c1) -> {
            listeCase.forEach((c2) -> {
                if (graphe_simple.getLabel(c1,c2) != null){
                    System.out.print(graphe_simple.getLabel(c1,c2));
                }
                else {
                    System.out.print(0);
                }
            });
        });*/
    }       
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------
    //génère le graphe
    private void genererGrapheAvance(){
        //génère les sommets
        listeCase.forEach((c) -> {
            graphe_avance.addNoeud(c);
        });
        
       for(Case c1 : this.listeCase){
           for (Case c2 : this.listeCase){
               if((Math.abs(c1.getLigne()-c2.getLigne())+Math.abs(c1.getColonne()-c2.getColonne())) == 1){
                   switch(c2.getType()){
                       case Sol:
                           this.graphe_avance.addEdge(c1, c2);
                           this.graphe_avance.setLabel(c1, c2, 1);
                           break;
                       case Mur:
                           this.graphe_avance.addEdge(c1, c2);
                           this.graphe_avance.setLabel(c1, c2, 2);
                           break;  
                       case MurDur:
                           this.graphe_avance.addEdge(c1, c2);
                           this.graphe_avance.setLabel(c1, c2, 2);
                           break;  
                       default :
                           System.out.println("Bruh");
                       break;
                   }
               }
           }
           
                   
       }
       
       
    
    }
    
    
    


    //Renvoie la case présente à ses coordonnées
    public Case getCase(int ligne,int colonne) {
        return this.hashMapCases.get(new Coordonnees(ligne,colonne));
    }
    
    //Change la case à ses coordonnées
    public void setCase(int ligne, int colonne, Case _case) {
        this.hashMapCases.put(new Coordonnees(ligne,colonne), _case);
        this.listeCase.add(_case);
    }
    
    //Change le type de case à ses coordonnées (lors du minage)
    public void changeTypeCase(Case caseInitiale, Type_Case typeNouvelleCase) {
        Case nouvelleCase = Fabrique_Cases.construireCase(typeNouvelleCase, caseInitiale.getLigne(), caseInitiale.getColonne(), this);
        this.setCase(caseInitiale.getLigne(), caseInitiale.getColonne(), nouvelleCase);
        this.listeCase.remove(caseInitiale);
    }
    
    //Ajoute une entité à la position donnée
    public void ajouteEntite(int ligne,int colonne, Entite entite) {
        this.getCase(ligne, colonne).setEntite(entite);
        entite.setCase(this.getCase(ligne, colonne));
        if(entite.getType() != Type_Entite.Cadence) {
            this.listeEntite.add(entite);
        }
    }
    
    //Fixe le joueur
    public void setJoueur(Entite_Cadence cadence) {
        this.joueur = cadence;
    }
    
    //Ajoute un objet à la position donnée
    public void ajouteObjet(int ligne,int colonne, Objet objet) {
        this.getCase(ligne, colonne).setObjet(objet);
        objet.setCase(this.getCase(ligne, colonne));
        this.listeObjet.add(objet);
    }
    
    //Renvoie la hashmap des cases
    public HashMap<Coordonnees,Case> getHashMapCases() {
        return this.hashMapCases;
    }
    
    //Fixe le point de départ
    public void setDepart(int numLigne,int numColonne) {
        this.depart = new Coordonnees(numLigne,numColonne);
    }
    
    //Fixe la position de la sortie
    public void setSortie(int numLigne,int numColonne) {
        this.fin = new Coordonnees(numLigne,numColonne);
    }
    
    //Renvoie la position du point de départ
    public Coordonnees getDepart() {
        return this.depart;
    }
    
    //Renvoie la position du point de sortie
    public Coordonnees getSortie() {
        return this.fin;
    }
    
    //Renvoie la liste des entités
    public ArrayList<Entite> getListeEntite() {
        return this.listeEntite;
    }
    
    //Renvoie la liste des cases
    public ArrayList<Case> getListeCase() {
        return this.listeCase;
    }
    
    //Renvoie la liste des objets
    public ArrayList<Objet> getListeObjet() {
        return this.listeObjet;
    }
    
    //Renvoie le joueur
    public Entite_Cadence getJoueur() {
        return this.joueur;
    }
    
    //Renvoie le graphe
    public Graphe getGraphe(){
        return this.graphe_avance;
    }
    
//------------------------------------------------------------------------------

}
