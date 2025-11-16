package view;

import seed.Seed;

/**
 * Entrypoint que inicia a aplicação JavaFX.
 */
public class Main {
    public static void main(String[] args) {
        //Seed.popularBancoSeVazio(); // insere dados apenas se estiver vazio
        //PARA TESTAR SE SUA SEED FUNCIONOU, DESCOMENTE ACIMA E SE NECESSÁRIO COMENTE AS OUTRAS SEEDS QUE TIVEREM ERRO

        //chama a interface de login
        LoginView.main(args);
    }
    
}
