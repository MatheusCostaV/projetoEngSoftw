import java.util.List;

public class ListagemSimples implements ListagemStrategy {

    @Override
    public void listar(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        for (Tarefa t : tarefas) {
            System.out.println(t);
        }
    }
}
