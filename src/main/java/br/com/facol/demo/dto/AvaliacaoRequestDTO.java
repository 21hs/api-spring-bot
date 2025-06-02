package br.com.facol.demo.dto;

public class AvaliacaoRequestDTO {

    private String usuario;
    private String academia;
    private Integer nota;
    private String comentario;

    // Getters e setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "AvaliacaoRequestDTO{" +
                "usuario='" + usuario + '\'' +
                ", academia='" + academia + '\'' +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
