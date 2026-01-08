public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private Status status;

    public Tarefa(int id, String nome, String descricao, Status status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " [" + status + "] - " + descricao;
    }
}
