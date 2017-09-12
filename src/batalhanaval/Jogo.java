package batalhanaval;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Mazurco066
 */
public class Jogo {
    
    /*
     * Attributes
     * campoJogador -  tabuleiro com os navios do jogador.
     * campoComputador - tabuleiro com os navios do computador.
     * tentativa - armazena o número de jogadas que o jogador fez.
     * acertos - armazena o número de acertos que o jogador fez.
     * disparo - armazena o disparo dado nos turnos.
     */
    
    //Definindo atributos de jogo
    private int ganhou = 0;
    int disparo[] = new int[2];
    int tentativas = 0;
    
    //Instanciando objetos para o campo do player e computador
    Mapa campoJogador;
    Mapa campoComputador;
    
    //Construtor padrão da classe
    public Jogo(){
        
        //Inicializando os objetos instanciados
        this.campoJogador = new Mapa();
        this.campoComputador = new Mapa();
        this.campoComputador.sortearNavios();
        
    }
    
    /**
     * Método para ler ganhou em outra classe.
     * 
     * @return 
     *      - retorna -1 se perdeu, 0 se não ha ganhador, e 1 se venceu.
     */
    public int getGanhou(){
        
        return ganhou;
    }
    
    /**
     * Método para posicionar os navios de acordo com a entrada de coordenadas
     * do usuário.
     * 
     * @param tamanho
     *      - recebe o quanto de espaço o navio ocupa no tabuleiro.
     * 
     * @param descricao 
     *      -recebe a descrição do navio que o para exibir na tela ao usuário.
     */
    public void posicionarNavio(int tamanho, String descricao){
        
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        int linhaInicial, colunaInicial, linhaFinal, colunaFinal;
        
        do{
        System.out.println("Entre com a posição inicial e final (" + descricao + ")");
        System.out.print("Linha Inicial: ");
        linhaInicial = sc.nextInt();
        System.out.print("Coluna Inicial: ");
        colunaInicial = sc.nextInt();
        System.out.print("Linha Final: ");
        linhaFinal = sc.nextInt();
        System.out.print("Coluna final: ");
        colunaFinal = sc.nextInt();
        
        //abaixo do limite
        if (linhaInicial < 1 || linhaFinal < 1 || colunaInicial < 1 || colunaFinal < 1){
            System.out.println("Por Favor entre com coordenadas válidas");
            continue;
        }
        
        //ultrapassou limite
        if (linhaInicial > 15 || linhaFinal > 15 || colunaInicial > 15 || colunaFinal > 15){
            System.out.println("Por Favor entre com coordenadas válidas");
            continue;
        }
        
        //Decrementando linha pois usuário não usa o 0
        linhaInicial--;
        linhaFinal--;
        colunaInicial--;
        colunaFinal--;
        
        if (linhaFinal > linhaInicial && colunaInicial == colunaFinal){
            if (linhaFinal - linhaInicial == (tamanho - 1)){
                valido = true;
            }
            else{
                System.out.println("NAVIO CURTO OU GRANDE DEMAIS... LEMBRE-SE QUE ESSE NAVIO OCUPA " + tamanho + " POSIÇÕES");
            }
        }
        else if (linhaInicial > linhaFinal && colunaInicial == colunaFinal){
            if (linhaInicial - linhaFinal == (tamanho - 1)){
                valido = true;
            }
            else{
                System.out.println("NAVIO CURTO OU GRANDE DEMAIS... LEMBRE-SE QUE ESSE NAVIO OCUPA " + tamanho + " POSIÇÕES");
            }
        }
        
        if (colunaFinal > colunaInicial && linhaInicial == linhaFinal){
            if (colunaFinal - colunaInicial == (tamanho - 1)){
                valido = true;
            }
            else{
                System.out.println("NAVIO CURTO OU GRANDE DEMAIS... LEMBRE-SE QUE ESSE NAVIO OCUPA " + tamanho + " POSIÇÕES");
            }
        }
        else if (colunaInicial > colunaFinal && linhaInicial == linhaFinal){
            if (colunaInicial - colunaFinal == (tamanho - 1)){
                valido = true;
            }
            else{
                System.out.println("NAVIO CURTO OU GRANDE DEMAIS... LEMBRE-SE QUE ESSE NAVIO OCUPA " + tamanho + " POSIÇÕES");
            }
        }
        
        //Verificação final - posição desejada esta livre?
        if (!campoJogador.verificaDisponibilidade(linhaInicial, linhaFinal, colunaInicial, colunaFinal)){
            
            System.out.println("POSIÇÃO JA OCUPADA POR OUTRO NAVIO, POR FAVOR SELECIONE UMA POSIÇÃO VALIDA");
            valido = false;
        }
        
        } while(!valido);
        
        campoJogador.posicionarNavios(linhaInicial, linhaFinal, colunaInicial, colunaFinal);
        
    }
    
    /**
     * Método que posiciona uma boia no tabuleiro de acordo com as coordenadas
     * entradas pelo usuário.
     */
    public void posicionarBoia(){
        
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        int linhaInicial, colunaInicial;
        
        do{
        System.out.println("Entre com a posição inicial e final (BOIA OCUPA 1 POSIÇÃO SOMENTE)");
        System.out.print("Coluna: ");
        linhaInicial = sc.nextInt();
        System.out.print("Linha: ");
        colunaInicial = sc.nextInt();
        
        //Decrementando para adicionar ao mapa
        linhaInicial--;
        colunaInicial--;
        
        if (linhaInicial < 0 || linhaInicial > 14 || colunaInicial < 0 || colunaInicial > 14){
            System.out.println("Por Favor entre com alguma coordenava válida");
        }
        else{
            
            if (this.campoJogador.navios[linhaInicial][colunaInicial] == 0){
                
                valido = true;
            }
        }
        
        } while(!valido);
        
        this.campoJogador.navios[linhaInicial][colunaInicial] = 1;
        
    }
    
    /**
     * Método que pega coordenadas do disparo com o usuário
     * e chama os métodos necessários para realizar o mesmo.
     */
    public void realizarDisparo(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Entre com as coordenadas que deseja realizar o Disparo");
        System.out.print("Linha: ");
        disparo[0] = sc.nextInt();
        disparo[0]--;
        
        System.out.print("Coluna: ");
        disparo[1] = sc.nextInt();
        disparo[1]--;
        
        if (disparo[0] < 0 || disparo[0] > 14 || disparo[1] < 0 || disparo[1] > 14){
            System.out.println("Ente com alguma coordenava válida!");
            realizarDisparo();
        }
        else{   //Validação simples para garantir que coordenada seja válida
            
            switch (this.campoComputador.verificaAcerto(disparo)){
            
            case -1:
                System.out.println("Você ja atacou esta posição antes por favor informe novas cordenas para ataque");
                realizarDisparo();
            break;
            
            case 0:
                //limpaTela();
                System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
                System.out.println("Você errou o disparo nas cordenadas-> " + (disparo[0]+1) + ":" + (disparo[1]+1));
                tentativas++;
            break;
            
            case 1:
                //limpaTela();
                System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
                System.out.println("Você acertou o disparo nas cordenadas-> " + (disparo[0]+1) + ":" + (disparo[1]+1));
                this.campoJogador.incrementaAcertos();
                tentativas++;
                verificaGanhador();
            break;
            
            default:
            break;
            
        }
            
        }
     
    }
    
    /**
     * Método que realizara contra ataque do computador ao jogador.
     */
    public void realizarContraAtaque(){
        
        Random sorteio = new Random();
        int tiro[] = new int[2];
        
        //Definindo coordenadas do ataque
        tiro[0] = sorteio.nextInt(15);
        tiro[1] = sorteio.nextInt(15);
        
        switch (this.campoJogador.verificaAcerto(tiro)){
            
            case -1:
                realizarContraAtaque();
            break;
            
            case 0:
                System.out.println("Computador Errou disparo nas coordenadas: "  + (tiro[0]+1) + ":" + (tiro[1]+1));
                System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
            break;
            
            case 1:
                System.out.println("Computador Acertou disparo nas coordenadas: "  + (tiro[0]+1) + ":" + (tiro[1]+1));
                System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
                this.campoComputador.incrementaAcertos();
                verificaGanhador();
            break;
            
            default:
            break;
            
        }
        
    }
    
    /**
     * Método que verifica a cada jogada se um ganhador ja foi definido.
     */
    public void verificaGanhador(){
        
        if (this.campoJogador.getAcertos() == 15){
            
            ganhou = 1;
        }
        
        if (this.campoComputador.getAcertos() == 15){
            
            ganhou = -1;
        }
        
    }
    
    /**
     * Método que funciona como um limpador de tela.
     */
    public void limpaTela(){
        
        for (int i = 0; i < 20; i++){
            System.out.println();
        }
    }
    
}
