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
import javafx.application.Application;

public class PlaylistMusicasView extends Application {

    private Playlist playlist;
    private final PlaylistController controller = new PlaylistController();

    private TableView<Musica> tabelaMusicasPlaylist;
    private ComboBox<Musica> comboTodasMusicas;

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void start(Stage stage) {
        if (playlist == null) {
            System.out.println("Playlist não definida!");
            return;
        }

        stage.setTitle("Playlist: " + playlist.getNome());

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        // Tabela de músicas da playlist
        tabelaMusicasPlaylist = new TableView<>();
        carregarMusicasDaPlaylist();

        TableColumn<Musica, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTitulo()));
        colTitulo.setPrefWidth(250);

        TableColumn<Musica, String> colArt = new TableColumn<>("Artista");
        colArt.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getArtista()));
        colArt.setPrefWidth(200);

        tabelaMusicasPlaylist.getColumns().addAll(colTitulo, colArt);

        // Seletor de músicas
        comboTodasMusicas = new ComboBox<>();
        comboTodasMusicas.setItems(FXCollections.observableArrayList(controller.listarTodasMusicas()));
        comboTodasMusicas.setPromptText("Selecione uma música para adicionar");

        Button btnAdicionar = new Button("Adicionar Música");
        btnAdicionar.setOnAction(e -> adicionarMusica());

        Button btnRemover = new Button("Remover Selecionada");
        btnRemover.setOnAction(e -> removerMusica());

        HBox botoes = new HBox(10, comboTodasMusicas, btnAdicionar, btnRemover);
        botoes.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(new Label("Músicas da Playlist:"), tabelaMusicasPlaylist, botoes);

        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    private void carregarMusicasDaPlaylist() {
        ObservableList<Musica> obs =
                FXCollections.observableArrayList(controller.listarMusicasDaPlaylist(playlist.getId()));
        tabelaMusicasPlaylist.setItems(obs);
    }

    private void adicionarMusica() {
        Musica musica = comboTodasMusicas.getValue();
        if (musica == null) return;

        controller.adicionarMusicaNaPlaylist(playlist, musica);
        carregarMusicasDaPlaylist();
    }

    private void removerMusica() {
        Musica musica = tabelaMusicasPlaylist.getSelectionModel().getSelectedItem();
        if (musica == null) return;

        controller.removerMusicaDaPlaylist(playlist, musica);
        carregarMusicasDaPlaylist();
    }
}
