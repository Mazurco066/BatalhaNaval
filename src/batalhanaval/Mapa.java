package batalhanaval;

import java.util.Random;

/**
 * @author Mazurco066
 */
public class Mapa {
    
    /**
     * @tabuleiro representa a matriz que armazena o campo de batalha
     * @navios armazena a posição dos navios no campo
     */
    
    //Atributos
    private int acertos = 0;
    int tabuleiro[][];
    int navios[][];
    
    public Mapa(){
        
        //Gera um novo tabuleiro
        this.tabuleiro = new int[15][15];
        this.navios = new int[15][15];
      
    }
    
    /**
     * [getAcertos] método para ler acertos em outra classe 
     * @return retorna a quantidade de acertos 
     */
    public int getAcertos(){
        
        return acertos;
    }
    
    /**
     * [Método para incrementar acertos]
     */
    public void incrementaAcertos(){
        
        acertos++;
    }
    
    /**
     * @Metodos de Classe
     * @mostraTabuleiro método para imprimir o tabuleiro inimigo no console
     * @posicionarNavios método que registra navios a partir de coordenadas dadas pelo usuário
     * @sortearNavios método que sorteia posição de navios aleatóriamente
     * @verificaAcerto método que verifica se ao dar o tiro acertou navio inimigo
     * @verificaDisponibilidade método que retorna true se posição escolhida estiver disponivel
     */
    
    /**
     * [mostraTabuleiro] Imprime a matriz 15 por 15 de tabuleiro do jogo na tela
     */
    public void mostraTabuleiro(){
        
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10 \t11 \t12 \t13 \t14 \t15");
        System.out.println();
        
        for(int linha = 0 ; linha < 15 ; linha++ ){
            System.out.print((linha+1)+"");
            for(int coluna=0 ; coluna < 15 ; coluna++ ){
                
                switch (tabuleiro[linha][coluna]){
                    
                    case -1:
                        System.out.print("\t"+"O");
                    break;
                    
                    case 0:
                         System.out.print("\t"+"~");
                    break;
                    
                    case 1:
                        System.out.print("\t"+"X");
                    break;
                    
                    default:
                    break;
                }
                
            }
            System.out.println();
            //System.out.println();
        }

    }
    
    /**
     * [mostraNavios] Imprime a matrix 15 por 15 de posicionamento de navios na tela
     */
    public void mostraNavios(){
        
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10 \t11 \t12 \t13 \t14 \t15");
        System.out.println();
        
        for(int linha = 0 ; linha < 15 ; linha++ ){
            System.out.print((linha+1)+"");
            for(int coluna=0 ; coluna < 15 ; coluna++ ){
                
                switch (navios[linha][coluna]){
                    
                    case 0:
                         System.out.print("\t"+"~");
                    break;
                    
                    case 1:
                        System.out.print("\t"+"N");
                    break;
                    
                    default:
                    break;
                }
                
            }
            System.out.println();
        }
        
    }
    
    /**
     * @param li = linha que faz parte coordenada inicial
     * @param lf = linha que faz parte coordenada final   
     * @param ci = coluna que faz parte coordenada inicial
     * @param cf = coluna que faz parte coordenada final
     */
    public void posicionarNavios(int li, int lf, int ci, int cf){
        
        if (lf > li && ci == cf){
            
            for (int linha = li; linha <= lf; linha++){
                
                this.navios[linha][ci] = 1;
            }
            
        }
        
        if (li > lf){
            
            for (int linha = li; linha >= lf; linha--){
                
                this.navios[linha][ci] = 1;
            }
            
        }
        
        if (cf > ci){
            
            for (int coluna = ci; coluna <= cf; coluna++){
                
                this.navios[li][coluna] = 1;
            }
            
        }
        
        if (ci > cf){
            
            for (int coluna = ci; coluna >= cf; coluna--){
                
                this.navios[li][coluna] = 1;
            }
            
        }
        
    }
    
    /*
     * @param li = linha que faz parte coordenada inicial
     * @param lf = linha que faz parte coordenada final   
     * @param ci = coluna que faz parte coordenada inicial
     * @param cf = coluna que faz parte coordenada final
     * @return - Retorna true se estiver disponivel e false se estiver ocupada por outro navio
     */
    public boolean verificaDisponibilidade(int li, int lf, int ci, int cf){
        
        boolean retorno = true;
                
        if (lf > li && ci == cf){
            
            for (int linha = li; linha <= lf; linha++){
                
                if (this.navios[linha][ci] == 1){
                    retorno = false;
                }
              
            }
            
        }
        
        if (li > lf){
            
            for (int linha = li; linha >= lf; linha--){
                
                if (this.navios[linha][ci] == 1) retorno = false;
            }
            
        }
        
        if (cf > ci){
            
            for (int coluna = ci; coluna <= cf; coluna++){
                
                if (this.navios[li][coluna] == 1) retorno = false;
            }
            
        }
        
        if (ci > cf){
            
            for (int coluna = ci; coluna >= cf; coluna--){
                
                if (this.navios[li][coluna] == 1) retorno = false;
            }
            
        }
        
        return retorno;
        
    }
    
    /**
     * [sortearNavios] distribui Randomicamente os Navios
     */
    public void sortearNavios(){
        
        Random sorteio = new Random();
        int ci[] = new int[2];
        int cf[] = new int[2];
        
        //Posicionando a Boia
        ci[0] = sorteio.nextInt(15);
        ci[1] = sorteio.nextInt(15);
        navios[ci[0]][ci[1]] = 1;
        
       //Posicionando o 2 Canos
       do{
       ci[0] = sorteio.nextInt(15);
       ci[1] = sorteio.nextInt(15);
       if(ci[0] < 14){
           cf[0] = ci[0] + 1;
           cf[1] = ci[1];
       }
       else{
           cf[0] = ci[0] - 1;
           cf[1] = ci[1];
       }
       }while(!verificaDisponibilidade(ci[0], cf[0], ci[1], cf[1]));
       posicionarNavios(ci[0], cf[0], ci[1], cf[1]);
       
        
       //Posicionando o 3 Canos
       do{
       ci[0] = sorteio.nextInt(15);
       ci[1] = sorteio.nextInt(15);
       if(ci[0] < 13){
           cf[0] = ci[0] + 2;
           cf[1] = ci[1];
       }
       else{
           cf[0] = ci[0] - 2;
           cf[1] = ci[1];
       }
       }while(!verificaDisponibilidade(ci[0], cf[0], ci[1], cf[1]));
       posicionarNavios(ci[0], cf[0], ci[1], cf[1]);
     
       //Posicionando o 4 Canos
       do{
       ci[0] = sorteio.nextInt(15);
       ci[1] = sorteio.nextInt(15);
       if(ci[0] < 12){
           cf[0] = ci[0] + 3;
           cf[1] = ci[1];
       }
       else{
           cf[0] = ci[0] - 3;
           cf[1] = ci[1];
       }
       }while(!verificaDisponibilidade(ci[0], cf[0], ci[1], cf[1]));
       posicionarNavios(ci[0], cf[0], ci[1], cf[1]);
       
       //Posicionando o Porta-Aviões
       do{
       ci[0] = sorteio.nextInt(15);
       ci[1] = sorteio.nextInt(15);
       if(ci[0] < 11){
           cf[0] = ci[0] + 4;
           cf[1] = ci[1];
       }
       else{
           cf[0] = ci[0] - 4;
           cf[1] = ci[1];
       }
       }while(!verificaDisponibilidade(ci[0], cf[0], ci[1], cf[1]));
       posicionarNavios(ci[0], cf[0], ci[1], cf[1]);
        
    }
    
    /**
     * @param tiro recebe coordenadas do disparo efetuado
     * @return retorna true se acertou o disparo e false se errou
     */
    public int verificaAcerto(int tiro[]){
        
        if (tabuleiro[tiro[0]][tiro[1]] != 0) return -1;   //Ja atacou este lugar
        
        if (navios[tiro[0]][tiro[1]] == 1){ //Acertou tiro
            
            tabuleiro[tiro[0]][tiro[1]] = 1;
            return 1;
        }
        else{
            
            tabuleiro[tiro[0]][tiro[1]] = -1;   //Errou tiro
            return 0;
        }
        
    }
    
}
