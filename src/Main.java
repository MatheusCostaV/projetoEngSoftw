import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskRepository repo = TaskRepository.getInstance(); // usando Singleton
        ListagemStrategy strategy = new ListagemSimples();  // usando Strategy

        int opcao;

        do {
            System.out.println("==== LISTA DE TAREFAS ====");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Remover tarefa");
            System.out.println("4 - Alterar status");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = lerInt(sc);

            switch (opcao) {
                case 1:
                    adicionarTarefa(sc, repo);
                    break;
                case 2:
                    strategy.listar(repo.listar()); // Strategy
                    break;
                case 3:
                    removerTarefa(sc, repo);
                    break;
                case 4:
                    alterarStatus(sc, repo);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }

            System.out.println();
        } while (opcao != 0);

        sc.close();
    }

    private static void adicionarTarefa(Scanner sc, TaskRepository repo) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Descricao: ");
        String descricao = sc.nextLine();

        System.out.println("Status inicial:");
        System.out.println("0 - DISPONIVEL");
        System.out.println("1 - FAZENDO");
        System.out.println("2 - FEITA");
        int opStatus = lerInt(sc);

        if (opStatus < 0 || opStatus >= Status.values().length) {
            System.out.println("Status invalido. Usando DISPONIVEL.");
            opStatus = 0;
        }

        Status status = Status.values()[opStatus];

        repo.adicionar(nome, descricao, status);
        System.out.println("Tarefa adicionada!");
    }

    private static void removerTarefa(Scanner sc, TaskRepository repo) {
        System.out.print("ID da tarefa a remover: ");
        int id = lerInt(sc);

        boolean removida = repo.removerPorId(id);
        if (removida) {
            System.out.println("Tarefa removida.");
        } else {
            System.out.println("Tarefa nao encontrada.");
        }
    }

    private static void alterarStatus(Scanner sc, TaskRepository repo) {
        System.out.print("ID da tarefa: ");
        int id = lerInt(sc);

        System.out.println("Novo status:");
        System.out.println("0 - DISPONIVEL");
        System.out.println("1 - FAZENDO");
        System.out.println("2 - FEITA");
        int opStatus = lerInt(sc);

        if (opStatus < 0 || opStatus >= Status.values().length) {
            System.out.println("Status invalido.");
            return;
        }

        Status novo = Status.values()[opStatus];
        boolean ok = repo.atualizarStatus(id, novo);

        if (ok) {
            System.out.println("Status atualizado.");
        } else {
            System.out.println("Tarefa nao encontrada.");
        }
    }

    private static int lerInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Digite um numero: ");
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }
}
