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
public class Submarino extends Navio {
    Torpedo torpedo = new Torpedo();
    Sonar sonar = new Sonar();
    
    public Submarino() {
        super(3, 1, "Submarino", "papel","S");
        super.adicionarHabilidade(torpedo, sonar);
    }
    
}
