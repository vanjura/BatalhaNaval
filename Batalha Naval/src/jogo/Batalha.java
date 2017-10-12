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
public class Batalha {

    public void executar(Jogador player[]) {
        int t[];
        int navio, habilidade;
        Jogador inimigo;
        t = randomizar();
        do {
            for (int i = 0; i < 2; i++) {
                if (player[t[i]].getTabuleiro().getSituacao() == 0) {
                    testarNavio(player[t[i]]);
                    JOptionPane.showMessageDialog(null, "Vez de " + player[t[i]].getApelido());
                    navio = escolherNavio(player[t[i]]);
                    if (navio != 6) {
                        habilidade = escolherHabilidade(player[t[i]], navio);
                        if (habilidade != -1) {
                            chamarHabilidade(player[t[i]], navio, habilidade);
                        }
                        if (navio == 0 && habilidade == 0) {
                            usarEscudo(player[t[i]]);
                        } else if (navio == 1 && habilidade == 0) {
                            inimigo = opcaoInimigoTorpedo(player, t[i]);
                            preparar(player[t[i]], inimigo, navio, habilidade);
                        } else {
                            inimigo = opcaoInimigo(player, t[i]);
                            preparar(player[t[i]], inimigo, navio, habilidade);
                        }
                    } else {
                        player[t[i]].rodadaSemAtirar();
                        JOptionPane.showMessageDialog(null, "Ganhou +75 de energia em cada navio por ficar sem atirar.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, player[t[i]].getApelido()
                            + " foi atingido por um Pulso Eletromagnético e deverá ficar mais "
                            + player[t[i]].getTabuleiro().getSituacao() + " rodada(s) sem jogar.");
                    player[t[i]].setSituacao(player[t[i]].getSituacao() - 1);
                }
            }
        } while (teste(player[0], player[1], player[2], player[3]) > 1);
        ganhador(player[0], player[1], player[2], player[3]);
    }
    
    public void testarNavio(Jogador player){
        int existe = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 14; j++) {
                for (int k = 0; k < 14; k++) {
                    if(player.getTabuleiro().getPosMatN(j, k).equals(player.getNavio()[i].getInicial())){
                        player.getNavio()[i].status = 1;
                        existe = 1;
                        break;
                    } else {
                        player.getNavio()[i].status = 0;
                    }
                }
                if(existe == 1){
                    break;
                }
            }
            if(existe == 0){
                player.getNavio()[i].energia = 0;
            }
            existe = 0;
            if(player.getNavio()[i].energia <= 0){
                player.getNavio()[i].energia = 0;
                player.getNavio()[i].status = 0;
            }
            if(player.getNavio()[i].energia > 1000){
                player.getNavio()[i].energia = 1000;
            }
        }
    }

    public void ganhador(Jogador player1, Jogador player2, Jogador player3, Jogador player4) {
        if (player1.getSituacao() == 1) {
            mensagemDeVitoria(player1);
        } else if (player2.getSituacao() == 1) {
            mensagemDeVitoria(player2);
        } else if (player3.getSituacao() == 1) {
            mensagemDeVitoria(player3);
        } else if (player4.getSituacao() == 1) {
            mensagemDeVitoria(player4);
        }
    }

    public int teste(Jogador player1, Jogador player2, Jogador player3, Jogador player4) {
        int teste;
        player1.setSituacao(0);
        player2.setSituacao(0);
        player3.setSituacao(0);
        player4.setSituacao(0);
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (!"X".equals(player1.getTabuleiro().getPosMatN(i, j)) && !"-".equals(player1.getTabuleiro().getPosMatN(i, j))) {
                    player1.setSituacao(1);
                }
            }
        }
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (!"X".equals(player2.getTabuleiro().getPosMatN(i, j)) && !"-".equals(player2.getTabuleiro().getPosMatN(i, j))) {
                    player2.setSituacao(1);
                }
            }
        }
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (!"X".equals(player3.getTabuleiro().getPosMatN(i, j)) && !"-".equals(player3.getTabuleiro().getPosMatN(i, j))) {
                    player3.setSituacao(1);
                }
            }
        }
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (!"X".equals(player4.getTabuleiro().getPosMatN(i, j)) && !"-".equals(player4.getTabuleiro().getPosMatN(i, j))) {
                    player4.setSituacao(1);
                }
            }
        }
        teste = player1.getSituacao() + player2.getSituacao() + player3.getSituacao() + player4.getSituacao();
        System.out.println("---------------TESTE :" + teste);
        return teste;
    }

    public void usarEscudo(Jogador player) {
        int x = -1, y = -1, c;
        String texto, option;
        texto = player.verJogador(player);
        mostrarJogador(texto);
        player.getNavio()[0].energia -= 5;
        JOptionPane.showMessageDialog(null, player.getNavio()[0].habilidade[0].nome
                + "\n Descrição: " + player.getNavio()[0].habilidade[0].descricao
                + "\n Tipo de destruição: " + player.getNavio()[0].habilidade[0].tipo
        );
        do {
            do {
                option = JOptionPane.showInputDialog("Informe a linha do meio de onde irá usar o Escudo:");
                try {
                    x = Integer.parseInt(option) - 1;
                    if (x < 1 || x > 13) {
                        x = -1;
                        JOptionPane.showMessageDialog(null, "Habilidade fora do limite do tabuleiro, informe outro número.");
                    }
                } catch (NumberFormatException e) {
                    if (option == null) {
                        System.exit(0);
                    } else {
                        x = -1;
                        JOptionPane.showMessageDialog(null, "Informe somente números.");
                    }
                }
            } while (x == -1);
            do {
                option = JOptionPane.showInputDialog("Informe a coluna do meio de onde irá usar o Escudo:");
                try {
                    y = Integer.parseInt(option) - 1;
                    if (y < 1 || y > 13) {
                        y = -1;
                        JOptionPane.showMessageDialog(null, "Habilidade fora do limite do tabuleiro, informe outro número.");
                    }
                } catch (NumberFormatException e) {
                    if (option == null) {
                        System.exit(0);
                    } else {
                        y = -1;
                        JOptionPane.showMessageDialog(null, "Informe somente números.");
                    }
                }
            } while (y == -1);
            if ("C".equals(player.getTabuleiro().getPosMatN(x, y))) {
                JOptionPane.showMessageDialog(null, "Você não pode colocar um escudo em uma Corveta.");
                c = 0;
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        player.getTabuleiro().setPosMatV((i + x) - 1, (j + y) - 1, 5);
                    }
                }
                c = 1;
            }
        } while (c == 0);
        player.mostrarTabuleiroV();
    }

    public void chamarHabilidade(Jogador player, int navio, int habilidade) {
        player.getNavio()[navio].habilidade[habilidade].mostrarHabilidade();
    }

    public void preparar(Jogador player, Jogador inimigo, int navio, int habilidade) {
        if (player.getNavio()[navio].energia >= 5) {
            mostrarInimigo(inimigo.verInimigo(inimigo), inimigo);
            if (habilidade == -1) {
                player.getNavio()[navio].consumirEnergia(player.getNavio()[navio], 5);
                player.getNavio()[navio].atirar(player, inimigo);
            } else if (navio == 1 && habilidade == 0) {
                player.getNavio()[navio].usarTorpedo(player, inimigo);
            } else if (navio == 1 && habilidade == 1) {
                player.getNavio()[navio].usarSonar(inimigo);
            } else if (navio == 2 && habilidade == 0) {
                player.getNavio()[navio].usarFoguetes(player, inimigo);
            } else if (navio == 2 && habilidade == 1) {
                player.getNavio()[navio].usarMissilBalistico(player, inimigo);
            } else if (navio == 3 && habilidade == 0) {
                player.getNavio()[navio].usarCargasDeProfundidade(player, inimigo);
            } else if (navio == 3 && habilidade == 1) {
                player.getNavio()[navio].usarRadar(inimigo);
            } else if (navio == 4 && habilidade == 0) {
                player.getNavio()[navio].usarArtilharia(player, inimigo);
            } else if (navio == 4 && habilidade == 1) {
                player.getNavio()[navio].usarArtilhariaPesada(player, inimigo);
            } else if (navio == 5 && habilidade == 0) {
                player.getNavio()[navio].usarBombardeioLinear(player, inimigo);
            } else if (navio == 5 && habilidade == 1) {
                player.getNavio()[navio].usarPulsoEletromagnetico(inimigo);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Navio sem energia suficiente, a habilidade falhou.");
        }
    }

    public void mostrarJogador(String texto) {
        JOptionPane.showMessageDialog(null, "Seu tabuleiro atual "
                + "\n\n" + texto);
    }

    public void mostrarInimigo(String texto, Jogador inimigo) {
        JOptionPane.showMessageDialog(null, "Tabuleiro atual de " + inimigo.getApelido()
                + "\n\n" + texto);
    }

    public Jogador opcaoInimigoTorpedo(Jogador player[], int t) {
        Jogador inimigo = null;
        if (t == 0) {
            inimigo = selecionarInimigoTorpedo(player[1], player[3]);
        }
        if (t == 1) {
            inimigo = selecionarInimigoTorpedo(player[0], player[2]);
        }
        if (t == 2) {
            inimigo = selecionarInimigoTorpedo(player[1], player[3]);
        }
        if (t == 3) {
            inimigo = selecionarInimigoTorpedo(player[2], player[0]);
        }
        return inimigo;
    }

    public Jogador opcaoInimigo(Jogador player[], int t) {
        Jogador inimigo = null;
        if (t == 0) {
            inimigo = selecionarInimigo(player[1], player[2], player[3]);
        }
        if (t == 1) {
            inimigo = selecionarInimigo(player[0], player[2], player[3]);
        }
        if (t == 2) {
            inimigo = selecionarInimigo(player[1], player[0], player[3]);
        }
        if (t == 3) {
            inimigo = selecionarInimigo(player[1], player[2], player[0]);
        }
        return inimigo;
    }

    public Jogador selecionarInimigoTorpedo(Jogador inimigo1, Jogador inimigo2) {
        int option;
        option = Integer.parseInt(JOptionPane.showInputDialog("ID | Nome"
                + "\n 1 | " + inimigo1.getApelido()
                + "\n 2 | " + inimigo2.getApelido()));
        switch (option) {
            case 1:
                return inimigo1;
            case 2:
                return inimigo2;
        }
        return null;
    }

    public Jogador selecionarInimigo(Jogador inimigo1, Jogador inimigo2, Jogador inimigo3) {
        int inimigo = 0;
        String option;
        do {
            option = JOptionPane.showInputDialog("ID | Nome"
                    + "\n 1 | " + inimigo1.getApelido()
                    + "\n 2 | " + inimigo2.getApelido()
                    + "\n 3 | " + inimigo3.getApelido());
            try {
                inimigo = Integer.parseInt(option);
                if (inimigo < 1 || inimigo > 3) {
                    inimigo = 0;
                    JOptionPane.showMessageDialog(null, "Inimigo informado não existe.");
                }
            } catch (NumberFormatException e) {
                if (option == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (inimigo == 0);
        switch (inimigo) {
            case 1:
                return inimigo1;
            case 2:
                return inimigo2;
            case 3:
                return inimigo3;
        }
        return null;
    }

    public int escolherHabilidade(Jogador player, int navio) {
        int habilidade = 0, max;
        String option = null;
        do {
            try {
                if (navio != 0) {
                    option = JOptionPane.showInputDialog("Habilidades:"
                            + "\nEnergia atual do navio: " + player.getNavio()[navio].energia
                            + "\n\n ID | Nome(custo) "
                            + "\n 1  | Atirar(5)"
                            + "\n 2  | " + player.getNavio()[navio].habilidade[0].nome + "(" + player.getNavio()[navio].habilidade[0].consumo + ")"
                            + "\n 3  | " + player.getNavio()[navio].habilidade[1].nome + "(" + player.getNavio()[navio].habilidade[1].consumo + ")"
                            + "\n\nInforme o ID da habilidade desejada:      ");
                    max = 3;
                } else {
                    option = JOptionPane.showInputDialog("Habilidades:"
                            + "\nEnergia atual do navio: " + player.getNavio()[navio].energia
                            + "\n\n ID | Nome(custo) "
                            + "\n 1  | Atirar(5)"
                            + "\n 2  | " + player.getNavio()[navio].habilidade[0].nome + "(" + player.getNavio()[navio].habilidade[0].consumo + ")"
                            + "\n\nInforme o ID da habilidade desejada:      ");
                    max = 2;
                }
                habilidade = Integer.parseInt(option);
                if (habilidade < 1 || habilidade > max) {
                    habilidade = 0;
                    JOptionPane.showMessageDialog(null, "Essa habilidade não existe.");
                }
                if (habilidade != 1) {
                    if (player.getNavio()[navio].getEnergia() < player.getNavio()[navio].getHabilidade()[habilidade - 2].consumo) {
                        habilidade = 0;
                        JOptionPane.showMessageDialog(null, "Você não tem energia suficiente para usar essa habilidade.");
                    } else {
                        player.getNavio()[navio].consumirEnergia(player.getNavio()[navio],
                                player.getNavio()[navio].habilidade[habilidade - 2].consumo);
                    }
                }
            } catch (NumberFormatException e) {
                if (option == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (habilidade == 0);
        return habilidade - 2;
    }

    public int escolherNavio(Jogador player) {
        int navio = 0;
        String option = null;
        do {
            try {
                option = JOptionPane.showInputDialog("Informações"
                        + "\nID |    Navio     | Energia"
                        + "\n 1 | " + player.getNavio()[0].nome + "      | " + player.getNavio()[0].energia
                        + "\n 2 | " + player.getNavio()[1].nome + "    | " + player.getNavio()[1].energia
                        + "\n 3 | " + player.getNavio()[2].nome + "      | " + player.getNavio()[2].energia
                        + "\n 4 | " + player.getNavio()[3].nome + "    | " + player.getNavio()[3].energia
                        + "\n 5 | " + player.getNavio()[4].nome + "     | " + player.getNavio()[4].energia
                        + "\n 6 | " + player.getNavio()[5].nome + " | " + player.getNavio()[5].energia
                        + "\n 7 | Não Atirar   | Ganha +75 de energia"
                        + "\n\nInforme o ID do navio desejado:");
                navio = Integer.parseInt(option);
                if (navio < 1 || navio > 7) {
                    JOptionPane.showMessageDialog(null, "Não existe um navio com esse ID.");
                    navio = 0;
                } else if (player.getNavio()[navio - 1].status == 0) {
                    JOptionPane.showMessageDialog(null, "Este navio está inativo.");
                    navio = 0;
                }
            } catch (NumberFormatException e) {
                if (option == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (navio == 0);
        return navio - 1;
    }

    public int[] randomizar() {
        int t[] = new int[4];
        int i;
        do {
            t[0] = (int) (Math.random() * 4);
            t[1] = (int) (Math.random() * 4);
            t[2] = (int) (Math.random() * 4);
            t[3] = (int) (Math.random() * 4);
            i = testeRandom(t);
        } while (i != 4);
        return t;
    }

    public int testeRandom(int t[]) {
        int i = 0;
        if (t[0] != t[1]) {
            if (t[0] != t[2]) {
                if (t[0] != t[3]) {
                    i++;
                }
            }
        }
        if (t[1] != t[0]) {
            if (t[1] != t[2]) {
                if (t[1] != t[3]) {
                    i++;
                }
            }
        }
        if (t[2] != t[1]) {
            if (t[2] != t[0]) {
                if (t[2] != t[3]) {
                    i++;
                }
            }
        }
        if (t[3] != t[1]) {
            if (t[3] != t[2]) {
                if (t[3] != t[0]) {
                    i++;
                }
            }
        }

        return i;
    }

    public void mensagemDeVitoria(Jogador player) {
        JOptionPane.showMessageDialog(null, "Parabéns " + player.getApelido() + "!\n\n" + player.getNavio()[1].getMensagem());
    }

}
