package controller;

import dao.PlaylistDAO;
import dao.MontagemPlaylistDAO;
import dao.MusicaDAO;

import entities.Playlist;
import entities.Usuario;
import entities.Musica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import view.PlaylistMusicasView;

import java.util.List;

public class PlaylistController {

    @FXML
    private ListView<Playlist> listViewPlaylists;

    @FXML
    private TextField txtNomePlaylist;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnDeletar;

    private Usuario usuarioAtual;

    // DAOs necessários
    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private MontagemPlaylistDAO montagemDAO = new MontagemPlaylistDAO();
    private MusicaDAO musicaDAO = new MusicaDAO();

    private ObservableList<Playlist> playlistsObservable;


    public void setUsuarioAtual(Usuario usuario) {
        this.usuarioAtual = usuario;

        if (usuario != null) {
            List<Playlist> listas = playlistDAO.listarPlaylistsDoUsuario(usuario.getId());
            playlistsObservable = FXCollections.observableArrayList(listas);

            if (listViewPlaylists != null) {
                listViewPlaylists.setItems(playlistsObservable);
            }
        }
    }


    private void carregarPlaylists() {
        List<Playlist> listas = usuarioAtual == null ?
                List.of() : playlistDAO.listarPlaylistsDoUsuario(usuarioAtual.getId());

        playlistsObservable = FXCollections.observableArrayList(listas);

        if (listViewPlaylists != null) {
            listViewPlaylists.setItems(playlistsObservable);
        }
    }

    @FXML
    private void criarPlaylist() {
        String nome = txtNomePlaylist.getText().trim();

        if (nome.isEmpty()) {
            mostrarAlerta("Nome inválido", "Digite o nome da playlist.");
            return;
        }

        Playlist nova = new Playlist(nome, usuarioAtual);
        playlistDAO.criar(nova);

        txtNomePlaylist.clear();
        carregarPlaylists();
    }


    public void criarPlaylist(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da playlist não pode ser vazio");
        }

        Playlist nova = new Playlist(nome.trim(), usuarioAtual);
        playlistDAO.criar(nova);
    }


    @FXML
    private void deletarPlaylist() {
        Playlist selecionada = listViewPlaylists.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Nenhuma playlist", "Selecione uma playlist para deletar.");
            return;
        }

        playlistDAO.deletar(selecionada.getId());
        carregarPlaylists();
    }

    public void deletarPlaylist(Long playlistId) {
        playlistDAO.deletar(playlistId);
    }

    @FXML
    private void abrirPlaylist() {
        Playlist selecionada = listViewPlaylists.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Nenhuma playlist", "Selecione uma playlist.");
            return;
        }

        abrirPlaylistMusicasView(selecionada);
    }

    private void abrirPlaylistMusicasView(Playlist playlist) {
        try {
            PlaylistMusicasView view = new PlaylistMusicasView();
            view.setPlaylist(playlist);
            view.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível abrir a playlist.");
        }
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public List<Musica> listarMusicasDaPlaylist(Long playlistId) {
        return montagemDAO.listarMusicasDaPlaylist(playlistId);
    }

    public void adicionarMusicaNaPlaylist(Playlist playlist, Musica musica) {
        montagemDAO.adicionarMusica(playlist, musica);
    }

    public void removerMusicaDaPlaylist(Playlist playlist, Musica musica) {
        montagemDAO.removerMusica(playlist, musica);
    }


    public List<Musica> listarTodasMusicas() {
        return musicaDAO.listarTodas();
    }

    public List<Playlist> listarPlaylistsDoUsuario() {
        return usuarioAtual == null ? List.of() :
                playlistDAO.listarPlaylistsDoUsuario(usuarioAtual.getId());
    }
}
