/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author Vanjura
 */
public class Fragata extends Navio {

    Habilidade foguetes = new Foguetes();
    Habilidade missilBalistico = new MissilBalistico();

    public Fragata() {
        super(3, 1, "Fragata", "papel", "F");
        super.adicionarHabilidade(foguetes, missilBalistico);
    }

}
