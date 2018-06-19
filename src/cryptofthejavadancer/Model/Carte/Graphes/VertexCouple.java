/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.Carte.Graphes;

import java.util.Objects;

/**
 *
 * @author antoi
 */
public class VertexCouple {
    private Noeud start;
    private Noeud end;
    
    public VertexCouple(Noeud _start, Noeud _end){
        start = _start;
        end = _end;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.start);
        hash = 53 * hash + Objects.hashCode(this.end);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VertexCouple other = (VertexCouple) obj;
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }
}