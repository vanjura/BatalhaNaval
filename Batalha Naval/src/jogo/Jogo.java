/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author Vanjura
 */
public class Jogo {

    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Lucida Console", Font.PLAIN, 12));
        Jogador player[] = new Jogador[4];
        player[0] = new Jogador("Jogador 01"); 
        player[1] = new Jogador("Jogador 02");
        player[2] = new Jogador("Jogador 03");
        player[3] = new Jogador("Jogador 04");
        //O parametro passado com o nome do jogador serve somente 
        //para localização do usuário durante o jogo;
        Batalha battle = new Batalha();
        battle.executar(player); //Vetor e players é passado como parâmetro
    }

}
