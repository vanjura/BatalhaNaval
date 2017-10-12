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
public class Cruzador extends Navio{

    Habilidade artilharia = new Artilharia();
    Habilidade artilhariaPesada = new ArtilhariaPesada();
    public Cruzador() {
        super(5, 1, "Cruzador", "papel", "R");
        super.adicionarHabilidade(artilharia, artilhariaPesada);
    }
    
}
