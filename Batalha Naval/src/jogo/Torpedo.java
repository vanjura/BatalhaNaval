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
public class Torpedo extends Habilidade{
    
    public Torpedo() {
        super("Torpedo", "Lança um torpedo que se move lentamente em uma linha\n"
                + "reta através de um dos dois tabuleiros adjacentes (não é possível\n"
                + "lançar um torpedo no tabuleiro oposto). Se ele encontrar um navio\n"
                + "no caminho, ele irá explodir, atingindo uma área 3x3, que ignora\n"
                + "escudos.", "Agressivo (3x3)");
    }
    
    public void usarTorpedo(){
        
    }
    
}
