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
        player[0] = new Jogador("P0", "Vanjura 01", "nasc");
        player[1] = new Jogador("P1", "Penczkoski 01", "nasc");
        player[2] = new Jogador("P2", "Vanjura 02", "nasc");
        player[3] = new Jogador("P3", "Penczkoski 02", "nasc");
        Batalha battle = new Batalha();
        battle.executar(player);
    }

}
