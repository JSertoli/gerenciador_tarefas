package model;

public class Tarefa {
    private String descricao;
    private String categoria;
    private boolean concluida;

    public Tarefa(String descricao, String categoria) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.concluida = false;
    }

    public String getDescricao() { return descricao; }
    public String getCategoria() { return categoria; }
    public boolean isConcluida() { return concluida; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    @Override
    public String toString() {
        return descricao + " [" + categoria + "]";
    }
}
