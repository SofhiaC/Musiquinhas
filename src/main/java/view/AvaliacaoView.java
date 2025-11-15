package view;

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
import controller.AvaliacaoController;
import controller.MusicaController;
import controller.SessionManager;
import entities.Avaliacao;
import entities.Musica;
import entities.Usuario;

public class AvaliacaoView extends javafx.application.Application {

    private final MusicaController musicaController = new MusicaController();
    private final AvaliacaoController avaliacaoController = new AvaliacaoController();
    private final SessionManager session = SessionManager.getInstance();

    private ComboBox<Musica> comboMusicas;
    private TextField txtNota;
    private TextArea taComentario;
    private TableView<Avaliacao> tabela;
    private ObservableList<Avaliacao> listaObs;

    private Stage stageRef;

    @Override
    public void start(Stage stage) {
        if (!session.isLogado()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Acesso Negado", "Você deve estar logado para acessar esta tela.");
            return;
        }

        stageRef = stage;
        stage.setTitle("Musiquinhas - Avaliações");

        Color fundoEscuro = Color.web("#2A2A2A");
        Color branco = Color.WHITE;
        Color roxo = Color.web("#7E6FBB");

        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(fundoEscuro, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(20));

        // topo: logo + título (opcional)
        HBox topo = new HBox(10);
        topo.setAlignment(Pos.CENTER_LEFT);
        ImageView logo = carregarLogo(120);
        if (logo != null) {
            topo.getChildren().add(logo);
        }
        Label titulo = new Label("Avaliações");
        titulo.setTextFill(branco);
        titulo.setFont(Font.font("Arial", 22));
        topo.getChildren().add(titulo);
        root.setTop(topo);

        // centro: campos de avaliação + tabela
        VBox centro = new VBox(30);
        centro.setPadding(new Insets(20, 10, 10, 10));

        // Linha de inputs
        HBox linhaInputs = new HBox(20);
        linhaInputs.setAlignment(Pos.CENTER_LEFT);

        comboMusicas = new ComboBox<>();
        comboMusicas.setPrefWidth(380);
        comboMusicas.setPromptText("Música");
        comboMusicas.setItems(FXCollections.observableArrayList(musicaController.listarTodasMusicas()));
        // Mostrar apenas o título na ComboBox
        comboMusicas.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Musica m) {
                return m == null ? "" : m.getTitulo();
            }
            @Override
            public Musica fromString(String string) { return null; }
        });

        txtNota = new TextField();
        txtNota.setPromptText("Nota");
        txtNota.setPrefWidth(120);

        taComentario = new TextArea();
        taComentario.setPromptText("Comentário");
        taComentario.setPrefRowCount(1);
        taComentario.setPrefWidth(420);
        taComentario.setWrapText(true);

        Button btnAvaliar = new Button("Avaliar");
        btnAvaliar.setFont(Font.font("Arial", 14));
        btnAvaliar.setTextFill(branco);
        btnAvaliar.setStyle("-fx-background-color: #7E6FBB; -fx-background-radius: 6;");
        btnAvaliar.setPrefSize(100, 40);
        btnAvaliar.setOnAction(e -> onAvaliar());

        // estilização minimal para os campos (seguir padrão)
        estilizarCampo(comboMusicas);
        estilizarCampo(txtNota);
        taComentario.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6;");

        linhaInputs.getChildren().addAll(comboMusicas, txtNota, taComentario, btnAvaliar);
        centro.getChildren().add(linhaInputs);

        // Tabela
        tabela = new TableView<>();
        tabela.setPrefHeight(400);

        TableColumn<Avaliacao, String> colUsuario = new TableColumn<>("Usuário");
        colUsuario.setPrefWidth(160);
        colUsuario.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getUsuario() != null ? data.getValue().getUsuario().getNome() : ""
                )
        );

        TableColumn<Avaliacao, Integer> colNota = new TableColumn<>("Nota");
        colNota.setPrefWidth(80);
        colNota.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNota()).asObject()
        );

        TableColumn<Avaliacao, String> colMusica = new TableColumn<>("Música");
        colMusica.setPrefWidth(220);
        colMusica.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getMusica() != null ? data.getValue().getMusica().getTitulo() : ""
                )
        );

        TableColumn<Avaliacao, String> colComentario = new TableColumn<>("Comentário");
        colComentario.setPrefWidth(420);
        colComentario.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getComentario() != null ? data.getValue().getComentario() : ""
                )
        );

        TableColumn<Avaliacao, Void> colAcao = new TableColumn<>("Ação");
        colAcao.setPrefWidth(100);
        colAcao.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Excluir");
            {
                btn.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white;");
                btn.setOnAction(e -> {
                    Avaliacao a = getTableView().getItems().get(getIndex());
                    if (a != null) {
                        avaliacaoController.deletarAvaliacao(a.getId());
                        listaObs.remove(a);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        tabela.getColumns().addAll(colUsuario, colNota, colMusica, colComentario, colAcao);
        centro.getChildren().add(tabela);

        root.setCenter(centro);

        // inferior: botão voltar
        HBox rodape = new HBox();
        rodape.setPadding(new Insets(10));
        rodape.setAlignment(Pos.CENTER);
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setFont(Font.font("Arial", 14));
        btnVoltar.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: white; -fx-background-radius: 6;");
        btnVoltar.setOnAction(e -> {
            // voltar para HomeView na mesma Stage
            new HomeView().start(stageRef);
        });
        rodape.getChildren().add(btnVoltar);
        root.setBottom(rodape);

        // carregar avaliações
        carregarAvaliacoes();

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    private void onAvaliar() {
        Musica musica = comboMusicas.getValue();
        String notaStr = txtNota.getText().trim();
        String comentario = taComentario.getText().trim();

        if (musica == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validação", "Escolha uma música.");
            return;
        }
        if (notaStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validação", "Informe a nota (1 a 5).");
            return;
        }
        int nota;
        try {
            nota = Integer.parseInt(notaStr);
        } catch (NumberFormatException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validação", "Nota inválida. Informe um número entre 1 e 5.");
            return;
        }
        if (nota < 1 || nota > 5) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validação", "Nota deve ser entre 1 e 5.");
            return;
        }

        Usuario usuario = session.getUsuarioLogado();
        try {
            avaliacaoController.criarAvaliacao(usuario, musica, nota, comentario);
            // recarregar lista para pegar id gerado e atualização
            carregarAvaliacoes();
            // limpar campos
            txtNota.clear();
            taComentario.clear();
            comboMusicas.getSelectionModel().clearSelection();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Avaliação cadastrada!");
        } catch (Exception ex) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível salvar avaliação: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void carregarAvaliacoes() {
        listaObs = FXCollections.observableArrayList(avaliacaoController.listarAvaliacoes());
        tabela.setItems(listaObs);
    }

    private ImageView carregarLogo(double tamanho) {
        try {
            Image img = new Image(getClass().getResourceAsStream("/images/Musiquinhas.png"));
            ImageView view = new ImageView(img);
            view.setPreserveRatio(true);
            view.setFitWidth(tamanho);
            view.setSmooth(true);
            return view;
        } catch (Exception e) {
            System.out.println("Logo não encontrada.");
            return null;
        }
    }

    private void estilizarCampo(Control field) {
        if (field instanceof TextField) {
            field.setPrefWidth(120);
            field.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 8;");
        } else if (field instanceof ComboBox) {
            field.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6;");
        } else {
            field.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6;");
        }
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
