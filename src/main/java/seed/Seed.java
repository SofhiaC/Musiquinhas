package seed;

import controller.UsuarioController;
import entities.Musica;
import entities.Usuario;
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
            controller.criaUsuario("Sofhia", "sofhia@gmail.com", "1234");
            controller.criaUsuario("Lara", "lara@gmail.com", "1234");
            controller.criaUsuario("Julia", "julia@gmail.com", "1234");
            controller.criaUsuario("Fernanda", "fernanda@gmail.com", "1234");
            controller.criaUsuario("Pedro", "pedro@gmail.com", "1234");

        }
    }

    private static void popularArtista(){
        ArtistaController controller = new ArtistaController();
        if(controller.listarArtistas().isEmpty()){
            controller.criarArtista("Pink Floyd"); //repetir varias dessa linha e preencher o nome
            controller.criarArtista("Frank Ocean"); 
            controller.criarArtista("Radiohead"); 

            
        }
    }

    private static void popularAlbum(){
        AlbumController controller = new AlbumController();
        if(controller.listarAlbuns().isEmpty()){
            controller.criarAlbum("OK Computer", 1997, "capa.png", 3L); //repetir varias dessa linha e preencher o nome
            //pra ser long tem que colocar L no final
            controller.criarAlbum("In Rabows", 2007, "capa.png", 3L);
            controller.criarAlbum("The Dark Side of the Moon", 1973, "capa.png", 1L);
            controller.criarAlbum("Wish You Were Here", 1994, "capa.png", 1L);
            controller.criarAlbum("Moon River", 2018, "capa.png", 2L);
        }
    }

    private static void popularMusica(){
        MusicaController controller = new MusicaController();
        if(controller.listarTodasMusicas().isEmpty()){
            //radiohead
            controller.criarMusica("Airbag", 287, 1L); //repetir varias dessa linha e preencher o nome
            controller.criarMusica("Paranoid Android", 387, 1L);
            controller.criarMusica("Subterranean Homesick Alien", 247, 1L);
            controller.criarMusica("Lucky", 200, 1L);

            controller.criarMusica("15 Step", 337, 2L);
            controller.criarMusica("Bodysnatchers", 242, 2L);
            controller.criarMusica("Nude", 255, 2L);

            //pink floyd
            controller.criarMusica("Speak to Me", 64, 3L);
            controller.criarMusica("On the Run", 216, 3L);
            controller.criarMusica("Time", 422, 3L);
            controller.criarMusica("Us and Them", 472, 3L);

            controller.criarMusica("Have a Cigar", 307, 4L);
            controller.criarMusica("Welcome to the Machine", 450, 4L);
            controller.criarMusica("Wish You Were Here", 338, 4L);

            //musicas do album moon river
            controller.criarMusica("Moon River", 186, 5L);
        }
    }
    
    private static void popularAssinaturas(){
        AssinaturaController controller = new AssinaturaController();
        if (controller.listarAssinaturas().isEmpty()){
            controller.criarAssinatura("Meia", "Exclusivo para estudantes", 20.00, "1 mes", "Metade do preço se você se cadastrar como estudante da sua instituição de ensino."); //repetir varias dessa linha e preencher o nome
            controller.criarAssinatura("Familia", "Para até 5 pessoas", 50.00, "1 mes", "Compartilhe os benefícios do Premium com aqueles que moram com você.");
        }
    }

    private static void popularPlaylist(){
        PlaylistController controller = new PlaylistController();
        UsuarioController usuarioController = new UsuarioController();

        if (controller.listarPlaylists().isEmpty()){

            Usuario u1 = usuarioController.buscarPorId(1L); // pega o primeiro usuário

            controller.criarPlaylist("Good Vibes", u1);
            controller.criarPlaylist("Depressão Total", u1);
            controller.criarPlaylist("Só as Melhores", u1);
        }
    }

    //popularPlaylist_musica() SE QUISER

    private static void popularAvaliacao() {

    AvaliacaoController controller = new AvaliacaoController();

    if (controller.listarAvaliacoes().isEmpty()) {

        // Buscar um usuário existente
        UsuarioController usuarioController = new UsuarioController();
        MusicaController musicaController = new MusicaController();

        Usuario u1 = usuarioController.buscarPorId(1L);
        Usuario u2 = usuarioController.buscarPorId(2L);

        Musica m1 = musicaController.buscarMusica(1L);
        Musica m2 = musicaController.buscarMusica(2L);
        Musica m3 = musicaController.buscarMusica(3L);

        // Verifica se encontrou
        if (u1 == null || u2 == null || m1 == null) {
            System.out.println("Usuários ou músicas não encontrados. Seed de avaliação abortada.");
            return;
        }

        // Cria avaliações
        controller.criarAvaliacao(u1, m1, 5, "Incrível!");
        controller.criarAvaliacao(u1, m2, 4, "Muito boa!");
        controller.criarAvaliacao(u2, m1, 3, "Gostei, mas não tanto.");
        controller.criarAvaliacao(u2, m3, 5, "Perfeita!");

    }
}

}
