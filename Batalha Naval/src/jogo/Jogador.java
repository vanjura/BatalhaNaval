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
public class Jogador {

    private String nome;
    private String apelido;
    private String nasc;
    private Tabuleiro tabuleiro;
    private Navio navio[];
    private int situacao;


    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public Jogador(String nome, String apelido, String nasc) {
        this.navio = new Navio[6];
        this.nome = nome;
        this.apelido = apelido;
        this.nasc = nasc;
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

    public String verJogador(Jogador player) {
        String texto = "  1  2  3  4  5  6  7  8  9  10 11 12 13 14\n";
        for (int i = 0; i < player.tabuleiro.MAX; i++) {
            for (int j = 0; j < player.tabuleiro.MAX; j++) {
                texto += "  " + player.tabuleiro.getPosMatN(i, j);
            }
            texto += "  " + (i + 1) + "      \n";
        }
        texto += "\nLegenda:"
                + "\nC = Corveta"
                + "\nS = Submarino"
                + "\nF = Fragata"
                + "\nD = Destroyer"
                + "\nR = Crusador"
                + "\nP = Porta Aviões";
        return texto;
    }

    public String verInimigo(Jogador inimigo) {
        String texto = "  1  2  3  4  5  6  7  8  9  10 11 12 13 14\n";
        for (int i = 0; i < inimigo.tabuleiro.MAX; i++) {
            for (int j = 0; j < inimigo.tabuleiro.MAX; j++) {
                if ("-".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  " + inimigo.tabuleiro.getPosMatN(i, j);
                } else if ("X".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  " + inimigo.tabuleiro.getPosMatN(i, j);
                } else if ("f".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  F";
                } else if("N".equals(inimigo.tabuleiro.getPosMatN(i, j))) {
                    texto += "  N";
                }else {
                    texto += "  -";
                }
            }
            texto += "  " + (i + 1) + "      \n";
        }
        texto += "\nLegenda:"
                + "\nN = Navio dectado (não derrubado)."
                + "\nF = Fragata detectada."
                + "\nX = Local atingido.";
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

    public void mostrarTabuleiroV() {
        for (int i = 0; i < this.tabuleiro.MAX; i++) {
            for (int j = 0; j < this.tabuleiro.MAX; j++) {
                System.out.print(" " + this.tabuleiro.getMatV()[i][j]);
            }
            System.out.println("");
        }
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
