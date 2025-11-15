package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.SessionManager;

/**
 * Tela Home após o login.
 * Exibe boas-vindas do usuário, opção de editar dados e botões para navegação.
 */
public class HomeView extends Application {
    private final SessionManager sessionManager = SessionManager.getInstance();
    
    private Stage stageRef;
    private Label lblBemvindo;

    @Override
    public void start(Stage stage) {
        // Verificar se usuário está logado
        if (!sessionManager.isLogado()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Acesso Negado", "Você deve estar logado para acessar esta tela.");
            return;
        }

        stage.setTitle("Musiquinhas - Home");
        stageRef = stage;

        // Cores principais
        Color fundoEscuro = Color.web("#2A2A2A");
        Color roxo = Color.web("#7E6FBB");
        Color branco = Color.WHITE;

        // Layout principal com BorderPane
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(fundoEscuro, CornerRadii.EMPTY, Insets.EMPTY)));

        // ---------- CABEÇALHO ----------
        HBox cabecalho = criarCabecalho(roxo, branco);
        root.setTop(cabecalho);

        // ---------- CONTEÚDO PRINCIPAL ----------
        VBox conteudo = criarConteudoPrincipal(branco);
        root.setCenter(conteudo);

        // ---------- RODAPÉ COM LOGO ----------
        HBox rodape = criarRodape();
        root.setBottom(rodape);

        // Configurar cena
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Cria o cabeçalho com boas-vindas e botão de editar
     */
    private HBox criarCabecalho(Color roxo, Color branco) {
        HBox cabecalho = new HBox(20);
        cabecalho.setPadding(new Insets(20));
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #7E6FBB; -fx-border-width: 0 0 2 0;");

        // Título de boas-vindas
        String nomeUsuario = sessionManager.getNomeUsuario();
        lblBemvindo = new Label("Bem-vindo(a), " + nomeUsuario + "!");
        lblBemvindo.setFont(Font.font("Arial", 28));
        lblBemvindo.setTextFill(branco);

        // Botão de editar dados
        Button btnEditar = new Button("Configurações");
        btnEditar.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10; -fx-background-radius: 6;");
        btnEditar.setFont(Font.font("Arial", 12));
        btnEditar.setCursor(javafx.scene.Cursor.HAND);
        
        btnEditar.setOnMouseEntered(e -> btnEditar.setStyle("-fx-background-color: #9988DD; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10; -fx-background-radius: 6;"));
        btnEditar.setOnMouseExited(e -> btnEditar.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10; -fx-background-radius: 6;"));
        
        btnEditar.setOnAction(e -> {
            // Fechar HomeView e abrir UsuarioView
            stageRef.close();
            new UsuarioView().start(new Stage());
        });       
        // Espaçador para empurrar botão para direita
        Region espacador = new Region();
        HBox.setHgrow(espacador, Priority.ALWAYS);

        cabecalho.getChildren().addAll(lblBemvindo, espacador, btnEditar);
        return cabecalho;
    }

    /**
     * Cria o conteúdo principal com botões de navegação
     */
    private VBox criarConteudoPrincipal(Color branco) {
        VBox conteudo = new VBox(30);
        conteudo.setPadding(new Insets(50));
        conteudo.setAlignment(Pos.CENTER);
        conteudo.setStyle("-fx-background-color: #2A2A2A;");

        // Grade de botões principais
        VBox botoesBox = criarBotoesNavegacao(branco);
        conteudo.getChildren().add(botoesBox);

        return conteudo;
    }

    /**
     * Cria a grade de botões principais
     */
    private VBox criarBotoesNavegacao(Color branco) {
        VBox botoesBox = new VBox(20);
        botoesBox.setAlignment(Pos.CENTER);

        // Primeira linha de botões
        HBox linha1 = new HBox(30);
        linha1.setAlignment(Pos.CENTER);

        Button btnPlaylists = criarBotaoGrande("Playlists");
        Button btnAvaliacoes = criarBotaoGrande("Avaliações");

        btnPlaylists.setOnAction(e -> {
            System.out.println("Playlists clicado");
            PlaylistView tela = new PlaylistView();
            Stage stageAtual = (Stage) btnPlaylists.getScene().getWindow();
            try {
                // tenta abrir usando o stage atual
                tela.start(stageAtual);
            } catch (Exception ex) {
                // se falhar, tenta abrir em uma nova janela para não deixar o botão sem ação
                ex.printStackTrace();
                try {
                    tela.start(new Stage());
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        });
        btnAvaliacoes.setOnAction(e -> {
            System.out.println("Avaliações clicado");

            AvaliacaoView tela = new AvaliacaoView();
            Stage stageAtual = (Stage) btnAvaliacoes.getScene().getWindow();

            tela.start(stageAtual);
        });


        linha1.getChildren().addAll(btnPlaylists, btnAvaliacoes);

        // Segunda linha de botões
        HBox linha2 = new HBox(30);
        linha2.setAlignment(Pos.CENTER);

        Button btnAssinatura = criarBotaoGrande("Assinatura");
        Button btnAlbuns = criarBotaoGrande("Álbuns");

        btnAssinatura.setOnAction(e -> {
            AssinaturaView tela = new AssinaturaView();
            tela.start((Stage) btnAssinatura.getScene().getWindow());
        });

        btnAlbuns.setOnAction(e -> System.out.println("Álbuns clicado"));

        linha2.getChildren().addAll(btnAssinatura, btnAlbuns);

        botoesBox.getChildren().addAll(linha1, linha2);
        return botoesBox;
    }

    /**
     * Cria um botão grande com estilo
     */
    private Button criarBotaoGrande(String texto) {
        Button btn = new Button(texto);
        btn.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: #FFFFFF; -fx-font-size: 16; -fx-padding: 20; -fx-background-radius: 8;");
        btn.setFont(Font.font("Arial", 16));
        btn.setPrefWidth(200);
        btn.setPrefHeight(100);
        btn.setWrapText(true);
        btn.setCursor(javafx.scene.Cursor.HAND);
        
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #9988DD; -fx-text-fill: #FFFFFF; -fx-font-size: 16; -fx-padding: 20; -fx-background-radius: 8;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: #FFFFFF; -fx-font-size: 16; -fx-padding: 20; -fx-background-radius: 8;"));
        
        return btn;
    }

    /**
     * Cria o rodapé com a logo
     */
    private HBox criarRodape() {
        HBox rodape = new HBox();
        rodape.setPadding(new Insets(20));
        rodape.setAlignment(Pos.CENTER);
        rodape.setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #7E6FBB; -fx-border-width: 2 0 0 0;");

        ImageView logoView = carregarLogo(200);
        if (logoView != null) {
            rodape.getChildren().add(logoView);
        } else {
            Label lblFallback = new Label("Musiquinhas");
            lblFallback.setTextFill(Color.web("#7E6FBB"));
            lblFallback.setFont(Font.font("Arial", 20));
            rodape.getChildren().add(lblFallback);
        }

        return rodape;
    }

    /**
     * Carrega e redimensiona a logo
     */
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

    /**
     * Exibe um alerta na tela
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    //testes
    public static void main(String[] args) {
        launch(args);
    }
}
