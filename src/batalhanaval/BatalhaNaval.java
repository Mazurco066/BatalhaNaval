package batalhanaval;

import java.util.Scanner;

/**
 * @author Mazurco066
 */
public class BatalhaNaval {
    
    public static void main(String[] args) {
        /**
         *   @Descrição Jogo batalha naval feito em java
         *   @Objetivo Desenvolver um jogo batalha naval em java para disciplina de POO
         *   @Desenvolvedor Gabriel Mazurco Ribeiro
         *   @GitHub https://github.com/Mazurco066
         */
        
        //Instanciando Objeto do tipo jogo para controlar a jogatina
        Jogo gameplay = new Jogo();
        Scanner sc = new Scanner(System.in);
        
        //jogador escolhe onde posicionará seus navios
        gameplay.posicionarNavio(5, "PORTA AVIÕES - Ocupa 5 espaços em linha RETA");       //Navio 1 - porta aviões
        gameplay.posicionarNavio(4, "NAVIO DE 4 CANOS - Ocupa 4 espaços em linha RETA");   //Navio 2 - quatro canos
        gameplay.posicionarNavio(3, "NAVIO DE 3 CANOS - Ocupa 3 espaços em linha RETA");   //Navio 3 - três canos
        gameplay.posicionarNavio(2, "NAVIO DE 2 CANOS - Ocupa 2 espaços em linha RETA");   //Navio 4 - dois canos
        gameplay.posicionarBoia();  //Navio 5 - boia
        //jogador ja escolheu posicionamento de seus navios
        
        //Iniciando o jogo
        gameplay.limpaTela();
        gameplay.campoJogador.mostraNavios();
        //gameplay.campoComputador.mostraNavios();  //para fins de testes
        
        System.out.println("\n^^^ SEUS NAVIOS ESTÃO POSICIONADOS COMO INDICADO ACIMA! BOM JOGO! ^^^");
        System.out.println("\nIniciando o Jogo . . . . \n--------------------------------------------------------------------------------------------------------------------------\n");
        
        do{
            /*System.out.println("SEU TABULEIRO");
            gameplay.campoJogador.mostraTabuleiro();
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
            */
            
            System.out.println("TABULEIRO INIMIGO");
            gameplay.campoComputador.mostraTabuleiro();
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
            
            gameplay.realizarDisparo();         //Vez do Jogador atacar
            if (gameplay.getGanhou() != 0){
                break;
            }
            gameplay.realizarContraAtaque();    //Vez do Computador Contra Atacar
            
        }
        while(gameplay.getGanhou() == 0);
        
        //Mensagem Final do jogo
        if (gameplay.getGanhou() == 1){
            //Você ganhou
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println("PARABENS VOCÊ É O GRANDE VENCEDOR!");
            System.out.println("Tentativas de Acertos: " + gameplay.tentativas);
            System.out.println("Obrigado por jogar");
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        }
        else{
            //Computador ganhou
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println("VOCÊ PERDEU PARA UM BOT ALEATORIO");
            System.out.println("Tentativas de Acerto: " + gameplay.tentativas);
            System.out.println("Tente Novamente");
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        }
        
    }
    
}
