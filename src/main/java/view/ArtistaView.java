package view;

import controller.ArtistaController;
import entities.Artista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ArtistaView {

    public void start(Stage stage) {

        // ----- Controller -----
        ArtistaController controller = new ArtistaController();
        var artistas = controller.listarArtistas();

        // ----- Título -----
        Label lblTitulo = new Label("Artistas");
        lblTitulo.setStyle("-fx-font-size: 32px; -fx-text-fill: white;");

        // ----- Grid -----
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);

        int col = 0, row = 0;

        for (Artista a : artistas) {
            grid.add(criarCartaoArtista(a), col, row);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }

        // ----- Container -----
        VBox container = new VBox(40);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(lblTitulo, grid);

        // ----- Botão voltar -----
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle(
                "-fx-background-color: #42327a; -fx-text-fill: white; -fx-padding: 10 20;"
        );
        btnVoltar.setOnAction(e -> {
            HomeView home = new HomeView();
            home.start(stage);
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
        Scene cena = new Scene(root, 1000, 700);
        stage.setScene(cena);
        stage.setTitle("Artistas");
        stage.show();
    }

    // ----- Cartão visual -----
    private VBox criarCartaoArtista(Artista artista) {

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #42327a; -fx-background-radius: 10;");
        box.setPrefSize(300, 120);

        Label lblNome = new Label(artista.getNome());
        lblNome.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        box.getChildren().add(lblNome);

        // ----- Evento de clique para abrir os álbuns -----
        box.setOnMouseClicked(e -> {
            AlbunsDoArtistaView albuns = new AlbunsDoArtistaView();
            albuns.start((Stage) box.getScene().getWindow(), artista);
        });

        // Muda o cursor ao passar por cima (opcional)
        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #5a46a1; -fx-background-radius: 10;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #42327a; -fx-background-radius: 10;"));

        return box;
    }
} 