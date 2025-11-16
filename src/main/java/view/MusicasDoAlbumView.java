package view;

import controller.MusicaController;
import entities.Album;
import entities.Musica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.stream.Collectors;

public class MusicasDoAlbumView {

    private final MusicaController musicaController = new MusicaController();

    public void start(Stage stage, Album album) {

        // ----- Título -----
        javafx.scene.control.Label titulo = new javafx.scene.control.Label("Músicas do álbum: " + album.getTitulo());
        titulo.setFont(Font.font(28));
        titulo.setStyle("-fx-text-fill: white;");

        // ----- TableView -----
        TableView<Musica> tabela = new TableView<>();
        tabela.setPrefWidth(800);
        tabela.setStyle("-fx-background-color: white; -fx-control-inner-background: white; -fx-text-fill: black;");

        // ----- Colunas -----
        TableColumn<Musica, String> colNome = new TableColumn<>("Nome da Música");
        colNome.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colNome.setPrefWidth(600);

        TableColumn<Musica, String> colDuracao = new TableColumn<>("Duração");
        colDuracao.setCellValueFactory(cellData -> {
            int duracaoSegundos = cellData.getValue().getDuracao();
            int min = duracaoSegundos / 60;
            int seg = duracaoSegundos % 60;
            return new SimpleStringProperty(String.format("%02d:%02d", min, seg));
        });
        colDuracao.setPrefWidth(180);

        tabela.getColumns().addAll(colNome, colDuracao);

        // Remove coluna fantasma
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ----- Preencher tabela apenas com as músicas do álbum -----
        List<Musica> musicas = musicaController.listarTodasMusicas().stream()
                .filter(m -> m.getAlbum().getId().equals(album.getId()))
                .collect(Collectors.toList());

        ObservableList<Musica> dados = FXCollections.observableArrayList(musicas);
        tabela.setItems(dados);

        VBox container = new VBox(30, titulo, tabela);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(30));

        // ----- Botão voltar -----
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #42327a; -fx-text-fill: white; -fx-padding: 10 20;");
        btnVoltar.setOnAction(e -> {
            AlbunsDoArtistaView albunsView = new AlbunsDoArtistaView();
            albunsView.start(stage, album.getArtista());
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER);
        boxVoltar.setPadding(new Insets(20));

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #292929;");
        root.setCenter(container);
        root.setBottom(boxVoltar);

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Músicas do álbum: " + album.getTitulo());
        stage.show();
    }
}
