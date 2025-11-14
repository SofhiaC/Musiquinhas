package view;

import entities.Album;
import entities.Musica;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AlbumView {

    private final Album album;

    public AlbumView(Album album) {
        this.album = album;
    }

    public void start(Stage stage) {

        // Cores do projeto
        Color fundo = Color.web("#1E1E1E");
        Color roxo = Color.web("#7E6FBB");
        Color branco = Color.WHITE;

        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(fundo, CornerRadii.EMPTY, Insets.EMPTY)));

        // ============================================================
        // TOPO ROXO
        // ============================================================
        HBox boxRoxa = new HBox(30);
        boxRoxa.setPadding(new Insets(30));
        boxRoxa.setBackground(new Background(new BackgroundFill(roxo, CornerRadii.EMPTY, Insets.EMPTY)));
        boxRoxa.setAlignment(Pos.CENTER_LEFT);

        // Capa
        ImageView capaView = null;
        try {
            Image img = new Image(album.getCapa()); 
            capaView = new ImageView(img);
            capaView.setFitWidth(150);
            capaView.setFitHeight(150);
            capaView.setPreserveRatio(true);
        } catch (Exception e) {
            VBox placeholder = new VBox();
            placeholder.setPrefSize(150, 150);
            placeholder.setAlignment(Pos.CENTER);
            placeholder.setStyle("-fx-background-color: lightgray;");
            Label p = new Label("SEM CAPA");
            p.setFont(Font.font(18));
            placeholder.getChildren().add(p);
            boxRoxa.getChildren().add(placeholder);
        }

        if (capaView != null) {
            boxRoxa.getChildren().add(capaView);
        }

        // Textos
        VBox boxTextos = new VBox(10);

        Label lblTitulo = new Label(album.getTitulo());
        lblTitulo.setFont(Font.font("Arial", 26));
        lblTitulo.setTextFill(branco);

        Label lblArtista = new Label(album.getArtista().getNome());
        lblArtista.setFont(Font.font("Arial", 18));
        lblArtista.setTextFill(branco);

        VBox listaMusicasBox = new VBox(5);
        for (Musica m : album.getMusicas()) {
            Label lbl = new Label("• " + m.getTitulo());
            lbl.setFont(Font.font(15));
            lbl.setTextFill(branco);
            listaMusicasBox.getChildren().add(lbl);
        }

        boxTextos.getChildren().addAll(lblTitulo, lblArtista, listaMusicasBox);

        boxRoxa.getChildren().add(boxTextos);
        root.setTop(boxRoxa);

        // ============================================================
        // BOTÃO VOLTAR
        // ============================================================
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-
