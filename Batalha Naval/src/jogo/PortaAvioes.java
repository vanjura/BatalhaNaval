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
public class PortaAvioes extends Navio{

    Habilidade bombardeioLinear = new BombardeioLinear();
    Habilidade pulsoEletromagnetico = new PulsoEletromagnetico();
    public PortaAvioes() {
        super(6, 2, "Porta Aviões", "papel", "P");
        super.adicionarHabilidade(bombardeioLinear, pulsoEletromagnetico);
    }
    
}
