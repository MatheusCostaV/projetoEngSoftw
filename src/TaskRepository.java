import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static final TaskRepository INSTANCE = new TaskRepository(); // Singleton simples

    private List<Tarefa> tarefas = new ArrayList<>();
    private int proximoId = 1;

    private TaskRepository() {
    }

    public static TaskRepository getInstance() {
        return INSTANCE;
    }

    public Tarefa adicionar(String nome, String descricao, Status status) {
        Tarefa t = new Tarefa(proximoId++, nome, descricao, status);
        tarefas.add(t);
        return t;
    }

    public List<Tarefa> listar() {
        return tarefas;
    }

    public boolean removerPorId(int id) {
        return tarefas.removeIf(t -> t.getId() == id);
    }

    public boolean atualizarStatus(int id, Status novoStatus) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) {
                t.setStatus(novoStatus);
                return true;
            }
        }
        return false;
    }
}
