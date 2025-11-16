package view;

import controller.AlbumController;
import entities.Album;
import entities.Artista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class AlbunsDoArtistaView {

    private final AlbumController albumController = new AlbumController();

    public void start(Stage stage, Artista artista) {

        // ----- Título -----
        Label titulo = new Label("Álbuns de " + artista.getNome());
        titulo.setStyle("-fx-font-size: 32px; -fx-text-fill: white;");

        // ----- Grid -----
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);

        // Filtra os álbuns daquele artista
        List<Album> albuns = albumController.listarAlbuns().stream()
                .filter(a -> a.getArtista().getId().equals(artista.getId()))
                .collect(Collectors.toList());

        int col = 0, row = 0;

        for (Album album : albuns) {
            VBox card = criarCardAlbum(album);
            grid.add(card, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        // ----- Container -----
        VBox container = new VBox(40, titulo, grid);
        container.setAlignment(Pos.CENTER);

        // ----- Botão voltar -----
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #42327a; -fx-text-fill: white; -fx-padding: 10 20;");
        btnVoltar.setOnAction(e -> {
            // Reabre a tela de artistas no mesmo stage
            ArtistaView artistaView = new ArtistaView();
            artistaView.start(stage);
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER);
        boxVoltar.setPadding(new Insets(20));

        // ----- Root -----
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #292929;");
        root.setCenter(container);
        root.setBottom(boxVoltar);

        // ----- Cena -----
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Álbuns de " + artista.getNome());
        stage.show();
    }

    private VBox criarCardAlbum(Album album) {

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(15));
        box.setPrefSize(220, 140);
        box.setStyle("-fx-background-color: #42327a; -fx-background-radius: 10;");

        Label lblTitulo = new Label(album.getTitulo());
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        Label lblAno = new Label("Ano: " + album.getAno());
        lblAno.setStyle("-fx-text-fill: white;");

        box.getChildren().addAll(lblTitulo, lblAno);

        // ----- Evento de clique para abrir músicas -----
        box.setOnMouseClicked(e -> {
            MusicasDoAlbumView musicasView = new MusicasDoAlbumView();
            musicasView.start((Stage) box.getScene().getWindow(), album);
        });

        // ----- Hover effect -----
        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #5a46a1; -fx-background-radius: 10;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #42327a; -fx-background-radius: 10;"));

        return box;
    }
}
