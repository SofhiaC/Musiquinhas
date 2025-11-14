package view;

import controller.PlaylistController;
import controller.SessionManager;
import entities.Playlist;
import entities.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PlaylistView extends javafx.application.Application {

    private final PlaylistController playlistController = new PlaylistController();
    private final SessionManager session = SessionManager.getInstance();

    private TextField txtNomePlaylist;
    private TableView<Playlist> tabela;
    private ObservableList<Playlist> listaObs;

    private Stage stageRef;

    @Override
    public void start(Stage stage) {

        if (!session.isLogado()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Acesso Negado", "Você deve estar logado para acessar esta tela.");
            return;
        }

        Usuario usuario = session.getUsuarioLogado();
        playlistController.setUsuarioAtual(usuario);

        stageRef = stage;
        stage.setTitle("Musiquinhas - Minhas Playlists");

        Color fundo = Color.web("#2A2A2A");
        Color branco = Color.WHITE;
        Color roxo = Color.web("#7E6FBB");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(fundo, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox topo = new HBox(10);
        topo.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = carregarLogo(120);
        if (logo != null) topo.getChildren().add(logo);

        Label titulo = new Label("Minhas Playlists");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(branco);

        topo.getChildren().add(titulo);
        root.setTop(topo);

        VBox centro = new VBox(30);
        centro.setPadding(new Insets(20, 10, 10, 10));

        // linha de criação de playlist
        HBox linhaInputs = new HBox(20);
        linhaInputs.setAlignment(Pos.CENTER_LEFT);

        txtNomePlaylist = new TextField();
        txtNomePlaylist.setPromptText("Nome da playlist");
        txtNomePlaylist.setPrefWidth(350);
        estilizarCampo(txtNomePlaylist);

        Button btnCriar = new Button("Criar");
        btnCriar.setFont(Font.font("Arial", 14));
        btnCriar.setTextFill(branco);
        btnCriar.setStyle("-fx-background-color: #7E6FBB; -fx-background-radius: 6;");
        btnCriar.setPrefSize(110, 40);
        btnCriar.setOnAction(e -> criarPlaylist());

        linhaInputs.getChildren().addAll(txtNomePlaylist, btnCriar);
        centro.getChildren().add(linhaInputs);

        // tabela
        tabela = new TableView<>();
        tabela.setPrefHeight(400);

        TableColumn<Playlist, String> colNome = new TableColumn<>("Nome");
        colNome.setPrefWidth(350);
        colNome.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNome())
        );

        TableColumn<Playlist, String> colUsuario = new TableColumn<>("Usuário");
        colUsuario.setPrefWidth(200);
        colUsuario.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getUsuario().getNome())
        );

        // ações
        TableColumn<Playlist, Void> colAcao = new TableColumn<>("Ação");
        colAcao.setPrefWidth(200);

        colAcao.setCellFactory(col -> new TableCell<>() {
            private final Button btnExcluir = new Button("Excluir");
            private final Button btnAbrir = new Button("Abrir");

            {
                btnExcluir.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white;");
                btnAbrir.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: white;");

                btnExcluir.setOnAction(e -> {
                    Playlist p = getTableView().getItems().get(getIndex());
                    playlistController.deletarPlaylist(p.getId());
                    carregarPlaylists();
                });

                btnAbrir.setOnAction(e -> {
                    Playlist p = getTableView().getItems().get(getIndex());
                    System.out.println("Abrindo playlist: " + p.getNome());

                    // Abre a tela de gerenciamento de músicas
                    PlaylistMusicasView playlistMusicasView = new PlaylistMusicasView();
                    playlistMusicasView.setPlaylist(p);
                    playlistMusicasView.start(new Stage());
                });
            }

            HBox box = new HBox(10, btnAbrir, btnExcluir);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });

        tabela.getColumns().addAll(colNome, colUsuario, colAcao);
        centro.getChildren().add(tabela);

        root.setCenter(centro);

        HBox rodape = new HBox();
        rodape.setAlignment(Pos.CENTER);
        rodape.setPadding(new Insets(10));

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setFont(Font.font("Arial", 14));
        btnVoltar.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: white; -fx-background-radius: 6;");
        btnVoltar.setOnAction(e -> new HomeView().start(stageRef));

        rodape.getChildren().add(btnVoltar);
        root.setBottom(rodape);

        carregarPlaylists();

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void criarPlaylist() {
        String nome = txtNomePlaylist.getText().trim();

        if (nome.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validação", "Digite um nome para a playlist.");
            return;
        }

        playlistController.criarPlaylist(nome);
        txtNomePlaylist.clear();
        carregarPlaylists();
    }

    private void carregarPlaylists() {
        listaObs = FXCollections.observableArrayList(playlistController.listarPlaylistsDoUsuario());
        tabela.setItems(listaObs);
    }

    private ImageView carregarLogo(double tamanho) {
        try {
            Image img = new Image(getClass().getResourceAsStream("/resources/Musiquinhas.png"));
            ImageView view = new ImageView(img);
            view.setFitWidth(tamanho);
            view.setPreserveRatio(true);
            view.setSmooth(true);
            return view;
        } catch (Exception e) {
            System.out.println("Logo não encontrada.");
            return null;
        }
    }

    private void estilizarCampo(Control c) {
        c.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 8;");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
