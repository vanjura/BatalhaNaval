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
public class Destroyer extends Navio{
    
    Habilidade cargasDeProfuncudade = new CargasDeProfundidade();
    Habilidade radar = new Radar();

    public Destroyer() {
        super(4, 1, "Destroyer", "Papel", "D");
        super.adicionarHabilidade(cargasDeProfuncudade, radar);
    }
    
}
