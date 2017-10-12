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
public class Corveta extends Navio {

    Escudo escudo = new Escudo();

    public Corveta() {
        super(2, 1, "Corveta", "Papel","C");
        super.adicionarHabilidade(escudo,null);
    }
    
}
