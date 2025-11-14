package view;

import controller.MusicaController;

/**
 * Entrypoint que inicia a aplicação JavaFX.
 */
public class Main {
    public static void main(String[] args) {
        //instancia musicas
        MusicaController controller = new MusicaController();
        controller.inicializarMusicas();
        //chama a interface de login
        LoginView.main(args);
    }
    
}
