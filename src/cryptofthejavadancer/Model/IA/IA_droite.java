/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Entites.Entite;

/**
 *
 * @author ah281512
 */
public class IA_droite extends IA {
    
    private int compteur;

    public IA_droite(Entite _entite) {
        super(_entite);
        this.compteur = 0 ;
    }

    @Override
    public Type_Action action() {
//       compteur++;
       Type_Action actionAFaire = Type_Action.attendre;
//       switch(compteur){
//           case 1 : actionAFaire = Type_Action.deplacement_droite;break;
//           case 2 : actionAFaire = Type_Action.deplacement_bas;break;
//           case 3 : actionAFaire = Type_Action.attendre;break;
//       }
       
       return actionAFaire;
       
        
    }
    
}
