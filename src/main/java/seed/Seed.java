package seed;

import controller.UsuarioController;
import controller.ArtistaController;
import controller.AlbumController;
import controller.MusicaController;
import controller.AssinaturaController;
import controller.PlaylistController;
import controller.AvaliacaoController;

public class Seed {

    public static void popularBancoSeVazio() {
        //popular tabelas na ordem correta

        popularUsuarios();
        popularArtista();
        popularAlbum();
        popularMusica();
        popularAssinaturas();
        popularPlaylist();
        //se quiser popularPlaylist_musica();
        popularAvaliacao();
    }

    private static void popularUsuarios(){
        UsuarioController controller = new UsuarioController();
        if(controller.listarUsuarios().isEmpty()){
            controller.criaUsuario("Nome", "email@example.com", "senha");
        }
    }

    private static void popularArtista(){
        ArtistaController controller = new ArtistaController();
        if(controller.listarArtistas().isEmpty()){
            controller.criarArtista("Nome"); //repetir varias dessa linha e preencher o nome
            
        }
    }

    private static void popularAlbum(){
        AlbumController controller = new AlbumController();
        if(controller.listarAlbuns().isEmpty()){
            controller.criarAlbum("Album", 1200, "capa.png", 3L); //repetir varias dessa linha e preencher o nome
            //pra ser long tem que colocar L no final
            
        }
    }

    private static void popularMusica(){
        MusicaController controller = new MusicaController();
        if(controller.listarTodasMusicas().isEmpty()){
            controller.criarMusica("Musica", 200, 2L); //repetir varias dessa linha e preencher o nome
        }
    }
    
    private static void popularAssinaturas(){
        AssinaturaController controller = new AssinaturaController();
        if (controller.listarAssinaturas().isEmpty()){
            controller.criarAssinatura("Nome", "tipo", 40.00, "1 mes", "beneficios"); //repetir varias dessa linha e preencher o nome
        }
    }

    private static void popularPlaylist(){
        PlaylistController controller = new PlaylistController();
        if (controller.listarPlaylists().isEmpty()){
            controller.criarPlaylist("Nome"); //tem que ver se isso ai ta certo q nao ta linkando com o usuario
        }
    }

    //popularPlaylist_musica() SE QUISER

    private static void popularAvaliacao(){
        AvaliacaoController controller = new AvaliacaoController();
        if (controller.listarAvaliacoes().isEmpty()){
            // controller.criarAvaliacao(u, m, 5, "comentario"); 
            //algo assim, nao sei rs
            //repetir varias dessa linha e preencher o nome
        }

    }

}
