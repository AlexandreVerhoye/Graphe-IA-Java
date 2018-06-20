/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Case_Sol;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Graphe;
import cryptofthejavadancer.Model.Carte.Graphes.Noeud;
import cryptofthejavadancer.Model.Carte.Graphes.VertexCouple;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Type_Entite;

public class IA_sortie extends IA {

    private boolean tour;
    private Dijkstra algo;
    int turn = 0;

    public IA_sortie(Entite _entite) {
        super(_entite);
        algo = null;
        tour = false;
    }

    public Type_Action noeudToAction(Case CaseSuivante) {
        Case cC = this.getEntite().getCase();
        Type_Action retour = Type_Action.attendre;
        int X = cC.getLigne();
        int Y = cC.getColonne();
        if (CaseSuivante.getType() == Type_Case.Sol) {
            if (CaseSuivante.getEntite() == null) {
                if (CaseSuivante.getLigne() == (X - 1)) {
                    retour = Type_Action.deplacement_haut;
                } else if (CaseSuivante.getLigne() == (X + 1)) {
                    retour = Type_Action.deplacement_bas;
                } else if (CaseSuivante.getColonne() == (Y - 1)) {
                    retour = Type_Action.deplacement_gauche;
                } else if (CaseSuivante.getColonne() == (Y + 1)) {
                    retour = Type_Action.deplacement_droite;
                }
                this.algo.destroyFirst();
            } else if (CaseSuivante.getEntite().getType() != Type_Entite.Cadence) {
                if (CaseSuivante.getLigne() == (X - 1)) {
                    retour = Type_Action.interagir_haut;
                } else if (CaseSuivante.getLigne() == (X + 1)) {
                    retour = Type_Action.interagir_bas;
                } else if (CaseSuivante.getColonne() == (Y - 1)) {
                    retour = Type_Action.interagir_gauche;
                } else if (CaseSuivante.getColonne() == (Y + 1)) {
                    retour = Type_Action.interagir_droite;
                }
            }
        } else if (CaseSuivante.getType() == Type_Case.Mur) {

            if (CaseSuivante.getLigne() == (X - 1)) {
                retour = Type_Action.interagir_haut;
            } else if (CaseSuivante.getLigne() == (X + 1)) {
                retour = Type_Action.interagir_bas;
            } else if (CaseSuivante.getColonne() == (Y - 1)) {
                retour = Type_Action.interagir_gauche;
            } else if (CaseSuivante.getColonne() == (Y + 1)) {
                retour = Type_Action.interagir_droite;
            }

            for (Noeud v : getGraph().getNoeuds().values()) {
                if (v.getNeighbours().contains(getGraph().getNoeud(CaseSuivante))) {
                    VertexCouple vC = new VertexCouple(v, getGraph().getNoeud(CaseSuivante));
                    getGraph().getLabels().replace(vC, 2, 1);
                }
            }
            Case test = new Case_Sol(CaseSuivante.getLigne(), CaseSuivante.getColonne(), getMap());
            Noeud v = getGraph().getNoeud(CaseSuivante);
            v.setCase(test);
            getGraph().getNoeuds().remove(CaseSuivante, v);
            getGraph().getNoeuds().put(test, v);

//            for (Noeud v : this.algo.getGraph().getNoeuds().values()) {
//                if (v.getNeighbours().contains(this.getGraph().getNoeud(CaseSuivante))) {
//                    VertexCouple vC = new VertexCouple(v, this.getGraph().getNoeud(CaseSuivante));
//                    this.getGraph().getLabels().put(vC, 1);
//                }
//            }
            algo.getGraph().getNoeuds().put(new Case_Sol(CaseSuivante.getLigne(), CaseSuivante.getColonne(), getMap()), algo.getGraph().getNoeud(CaseSuivante));
        }
        
        System.out.println(retour);
        System.out.println(this.algo.getChemin());
        return retour;

    }

    @Override
    public Type_Action action() {
        Type_Action retour = Type_Action.attendre;
        if (this.turn == 0) {
            this.algo = new Dijkstra(this.getEntite().getMap().getGraphe());
            Map map = this.getEntite().getMap();
            this.algo.calcul(map.getGraphe().getNoeud(this.getEntite().getCase()), map.getGraphe().getNoeud(map.getCase(map.getSortie().getLigne(), map.getSortie().getColonne())));
        }
        if (this.algo.getChemin().isEmpty()) {
            retour = Type_Action.sortir;
        } else {
            retour = noeudToAction(this.algo.getChemin().get(0).getCase());
        }
        turn++;
        return retour;
    }

    public Graphe getGraph() {
        return this.algo.getGraph();
    }

}
