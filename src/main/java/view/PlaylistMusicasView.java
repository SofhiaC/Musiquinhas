package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.PlaylistController;
import entities.Musica;
import entities.Playlist;
import entities.Usuario;

/**
 * Tela para visualizar e gerenciar músicas de uma playlist.
 * Agora não estende Application, usa initStage(Stage) para abrir a janela.
 */
public class PlaylistMusicasView {

    private Playlist playlist;
    private Usuario usuarioAtual;
    private final PlaylistController controller = new PlaylistController();

    private TableView<Musica> tabelaMusicas;
    private ComboBox<Musica> comboTodasMusicas;

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void setUsuarioAtual(Usuario usuario) {
        this.usuarioAtual = usuario;
        controller.setUsuarioAtual(usuario);
    }

    public void initStage(Stage stage) {
        if (playlist == null || usuarioAtual == null) {
            System.out.println("Playlist ou usuário não definido!");
            return;
        }

        stage.setTitle("Playlist: " + playlist.getNome());

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        // Tabela de músicas da playlist
        tabelaMusicas = new TableView<>();
        tabelaMusicas.setPrefHeight(400);
        carregarMusicasDaPlaylist();

        TableColumn<Musica, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTitulo()));
        colTitulo.setPrefWidth(250);

        TableColumn<Musica, String> colArtista = new TableColumn<>("Artista");
        colArtista.setCellValueFactory(c -> {
            String nomeArtista = "";
            if (c.getValue().getAlbum() != null && c.getValue().getAlbum().getArtista() != null) {
                nomeArtista = c.getValue().getAlbum().getArtista().getNome();
            }
            return new javafx.beans.property.SimpleStringProperty(nomeArtista);
        });        
        colArtista.setPrefWidth(200);

        tabelaMusicas.getColumns().addAll(colTitulo, colArtista);

        // ComboBox com todas as músicas
        comboTodasMusicas = new ComboBox<>();
        ObservableList<Musica> todas = FXCollections.observableArrayList(controller.listarTodasMusicas());
        comboTodasMusicas.setItems(todas);
        comboTodasMusicas.setPromptText("Selecione uma música para adicionar");

        // Botões de adicionar/remover
        Button btnAdicionar = new Button("Adicionar Música");
        btnAdicionar.setOnAction(e -> adicionarMusica());

        Button btnRemover = new Button("Remover Selecionada");
        btnRemover.setOnAction(e -> removerMusica());

        HBox botoes = new HBox(10, comboTodasMusicas, btnAdicionar, btnRemover);
        botoes.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(new Label("Músicas da Playlist:"), tabelaMusicas, botoes);

        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void carregarMusicasDaPlaylist() {
        if (playlist != null) {
            ObservableList<Musica> obs = FXCollections.observableArrayList(
                    controller.listarMusicasDaPlaylist(playlist.getId())
            );
            tabelaMusicas.setItems(obs);
        }
    }

    private void adicionarMusica() {
        Musica musica = comboTodasMusicas.getValue();
        if (musica == null) return;

        // Adiciona via controller
        controller.adicionarMusicaNaPlaylist(playlist, musica);
        carregarMusicasDaPlaylist();
    }

    private void removerMusica() {
        Musica musica = tabelaMusicas.getSelectionModel().getSelectedItem();
        if (musica == null) return;

        // Remove via controller
        controller.removerMusicaDaPlaylist(playlist, musica);
        carregarMusicasDaPlaylist();
    }
}
