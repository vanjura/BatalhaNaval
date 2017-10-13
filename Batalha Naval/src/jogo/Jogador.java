/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import javax.swing.JOptionPane;
/**
 *
 * @author Vanjura
 */
public class Jogador {

    private String nome;
    private String apelido;
    private String nasc;
    private Tabuleiro tabuleiro;
    private Navio navio[];
    private int situacao;
    private String jogador;


    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public Jogador(String jogador) {
        this.jogador = jogador;
        this.navio = new Navio[6];
        this.nome = coletarNome(jogador);
        this.apelido = coletarApelido(jogador);
        this.nasc = coletarNasc(jogador);
        this.tabuleiro = new Tabuleiro();
        this.navio[0] = new Corveta();
        this.navio[1] = new Submarino();
        this.navio[2] = new Fragata();
        this.navio[3] = new Destroyer();
        this.navio[4] = new Cruzador();
        this.navio[5] = new PortaAvioes();
        this.preencheTabuleiro(navio[0]);
        this.preencheTabuleiro(navio[1]);
        this.preencheTabuleiro(navio[2]);
        this.preencheTabuleiro(navio[3]);
        this.preencheTabuleiro(navio[4]);
        this.preencheTabuleiro(navio[5]);
    }
    
     private String coletarNasc(String jogador){
        String nascimento = null;
        do{
            nascimento = JOptionPane.showInputDialog(jogador + " informe sua data de nascimento: ");
            if("".equals(nascimento)){
                JOptionPane.showMessageDialog(null, "Esse campo não pode estar vazio.");
            }
        }while("".equals(nascimento));
        if (nascimento == null){
            System.exit(0);
        }
        System.out.println(nascimento);
        return nascimento;
    }
    
    private String coletarApelido(String jogador){
        String nick = null;
        do{
            nick = JOptionPane.showInputDialog(jogador + " informe um apelido: ");
            if("".equals(nick)){
                JOptionPane.showMessageDialog(null, "Esse campo não pode estar vazio.");
            }
        }while("".equals(nick));
        if (nick == null){
            System.exit(0);
        }
        System.out.println(nick);
        return nick;
    }
    
    private String coletarNome(String jogador){
        String name = null;
        do{
            name = JOptionPane.showInputDialog(jogador + " informe seu nome: ");
            if("".equals(name)){
                JOptionPane.showMessageDialog(null, "Esse campo não pode estar vazio.");
            }
        }while("".equals(name));
        if (name == null){
            System.exit(0);
        }
        System.out.println(name);
        return name;
    }

    public String verJogador(Jogador player) {
        String texto = "    1  2  3  4  5  6  7  8  9  10 11 12 13 14\n";
        for (int i = 0; i < player.tabuleiro.MAX; i++) {
            if(i<9){
                texto += (i+1) + " ";
            } else{
                texto += (i+1);
            }
            for (int j = 0; j < player.tabuleiro.MAX; j++) {
                if(player.tabuleiro.getPosMatV(i, j) > 0){
                    texto += " e" + player.tabuleiro.getPosMatN(i, j);
                }else{
                    texto += "  " + player.tabuleiro.getPosMatN(i, j);
                }
            }
            texto += "      \n";
        }
        texto += "\nLegenda:"
                + "\ne = Escudo"
                + "\nC = Corveta"
                + "\nS = Submarino"
                + "\nF = Fragata"
                + "\nD = Destroyer"
                + "\nR = Crusador"
                + "\nP = Porta Aviões"
                + "\nX = Local Atingido";
        return texto;
    }

    public String verInimigo(Jogador inimigo) {
        String texto = "    1  2  3  4  5  6  7  8  9  10 11 12 13 14\n";
        for (int i = 0; i < inimigo.tabuleiro.MAX; i++) {
            if(i<9){
                texto += (i+1) + " ";
            } else{
                texto += (i+1);
            }
            for (int j = 0; j < inimigo.tabuleiro.MAX; j++) {
                if ("-".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  " + inimigo.tabuleiro.getPosMatN(i, j);
                } else if ("X".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  " + inimigo.tabuleiro.getPosMatN(i, j);
                } else if ("f".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  F";
                } else if("N".equals(inimigo.tabuleiro.getPosMatH(i, j))) {
                    texto += "  " + inimigo.tabuleiro.getPosMatN(i, j);
                }else {
                    texto += "  -";
                }
            }
            texto += "      \n";
        }
        texto += "\nLegenda:"
                + "\nC = Corveta"
                + "\nS = Submarino"
                + "\nF = Fragata"
                + "\nD = Destroyer"
                + "\nR = Crusador"
                + "\nP = Porta Aviões"
                + "\nX = Local Atingido";
        return texto;
    }

    private void preencheTabuleiro(Navio navio) {
        int x, y, c = 0;
        do {
            x = (int) (Math.random() * 14);
            y = (int) (Math.random() * 14);
            for (int i = 0; i < navio.getTamA(); i++) {
                for (int j = 0; j < navio.getTamC(); j++) {
                    if (x + i > 13 || y + j > 13 || !"-".equals(this.tabuleiro.getMatN()[x + i][y + j])) {
                        c = 0;
                        break;
                    } else {
                        c = 1;
                    }
                }
                if (c == 0) {
                    break;
                }
            }
            if (c == 1) {
                for (int i = 0; i < navio.getTamA(); i++) {
                    for (int j = 0; j < navio.getTamC(); j++) {
                        this.tabuleiro.setPosMatN((x + i), (y + j), navio.getInicial());
                    }
                }
            }
        } while (c == 0);
    }

    public void rodadaSemAtirar() {
        this.navio[0].ganharEnergia(navio[0], 75);
        this.navio[1].ganharEnergia(navio[1], 75);
        this.navio[2].ganharEnergia(navio[2], 75);
        this.navio[3].ganharEnergia(navio[3], 75);
        this.navio[4].ganharEnergia(navio[4], 75);
        this.navio[5].ganharEnergia(navio[5], 75);
    }

    public void jogadorErrou() {
        this.navio[0].ganharEnergia(navio[0], 30);
        this.navio[1].ganharEnergia(navio[1], 30);
        this.navio[2].ganharEnergia(navio[2], 30);
        this.navio[3].ganharEnergia(navio[3], 30);
        this.navio[4].ganharEnergia(navio[4], 30);
        this.navio[5].ganharEnergia(navio[5], 30);
    }

    public Navio[] getNavio() {
        return navio;
    }

    public void setNavio(Navio[] navio) {
        this.navio = navio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNasc() {
        return nasc;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
}
