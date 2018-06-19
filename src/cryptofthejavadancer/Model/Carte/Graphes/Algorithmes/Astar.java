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

    private Graphe graph;
    private Noeud debut;
    private Noeud fin;
    private HashMap<Noeud, Integer> distance;
    private HashMap<Noeud, Boolean> visited;
    private HashMap<Noeud, Noeud> predecessor;
    private ArrayList<Noeud> path;
    private Integer infini;

    public Astar(Graphe graph) {
        this.graph = graph;
        this.distance = new HashMap<Noeud, Integer>();
        this.visited = new HashMap<Noeud, Boolean>();
        this.predecessor = new HashMap<Noeud, Noeud>();
        this.path = new ArrayList<Noeud>();
        this.infini = null;
    }

    public void initialisation() {
        int max = getInfini();
        for (Noeud v : graph.getNoeuds().values()) {
            distance.put(v, max);
            visited.put(v, false);
            predecessor.put(v, null);
        }
        distance.put(debut, 0);
    }

    public Noeud closestVertex() {

        int min = getInfini() + 1;
        Noeud plusProche = null;
        for (Noeud v : distance.keySet()) {
            if (visited.get(v) == false) {
                if (heuristic(v) < min) {
                    plusProche = v;
                    min = heuristic(v);
                }
            }
        }
        return plusProche;
    }

    public void calcul(Noeud _debug, Noeud _fin) {
        debut = _debug;
        fin = _fin;
        this.initialisation();

        while (!visited.get(fin)) {
            Noeud a = closestVertex();
            visited.put(a, true);
            for (Noeud b : visited.keySet()) {
                relaxing(a, b);
            }
        }
        Noeud v = fin;
        while (v != null) {
            path.add(0, v);
            v = predecessor.get(v);
        }
        this.path.remove(debut);
//System.out.println(path);

    }

    public void relaxing(Noeud a, Noeud b) {
        if (this.graph.getLabels().get(new VertexCouple(a, b)) != null) {
            if (distance.get(b) > (distance.get(a) + this.graph.getLabels().get(new VertexCouple(a, b)))) {
                distance.put(b, (distance.get(a) + this.graph.getLabels().get(new VertexCouple(a, b))));
                predecessor.put(b, a);
            }
        }
    }

    public int getInfini() {
        if (infini == null) {
            infini = 0;
            for (Integer vLabel : graph.getLabels().values()) {
                infini += vLabel;
            }
            infini++;
        }
        return infini;
    }

    public int heuristic(Noeud v) {
        return (int) (distance.get(v) + euclidian_distance(v, fin));
    }

    public double euclidian_distance(Noeud d, Noeud f) {
        int ligneD = d.getCase().getLigne();
        int colonneD = d.getCase().getColonne();

        int ligneF = f.getCase().getLigne();
        int colonneF = f.getCase().getColonne();

        int distanceLargeur = Math.abs(colonneD - colonneF);
        int distanceHauteur = Math.abs(ligneD - ligneF);
        double max = Math.sqrt(distanceLargeur * distanceLargeur + distanceHauteur * distanceHauteur);
        return max;
    }

    public ArrayList<Noeud> getPath() {
        return path;
    }

    public void destroyFirst() {
        this.path.remove(0);
    }

    public Graphe getGraph() {
        return graph;
    }

    public ArrayList<Noeud> getPath(Noeud start, Noeud end) {
        ArrayList<Noeud> pathR = new ArrayList<>();
        Noeud v = end;
        while (v != start) {
            pathR.add(0, v);
            v = predecessor.get(v);
        }
        return pathR;
    }

}
