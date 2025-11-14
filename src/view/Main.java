package view;

import controller.MusicaController;
import controller.UsuarioController;
/**
 * Entrypoint que inicia a aplicação JavaFX.
 */
public class Main {
    public static void main(String[] args) {
        // Inicializar dados de teste se banco estiver vazio
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.inicializarDadosTeste();
        //instancia musicas
        MusicaController controller = new MusicaController();
        controller.inicializarMusicas();
        //chama a interface de login
        LoginView.main(args);
    }
    
}
