package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.Iterator;
import java.util.Scanner;


public class SistemaDeRoteamento {

    public static void adicionarPessoaAoGrafo(Graph<Vertice, DefaultWeightedEdge> grafo) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o bairro onde a pessoa mora: ");
        String neighborhood = scanner.nextLine();

        Vertice pessoa = new Vertice(nome, neighborhood);

        if(buscarPessoaPorNome(grafo,nome)!=null) {
            if(pessoa.getNome().equals(buscarPessoaPorNome(grafo,nome).getNome()))
            {
                System.out.println("Pessoa já existe.");
            }
            else{
                grafo.addVertex(pessoa);
                System.out.println("Pessoa adicionada ao grafo.");
            }
        } else{
            grafo.addVertex(pessoa);
            System.out.println("Pessoa adicionada ao grafo.");
        }


    }
    public static void removerPessoaDoGrafo(Graph<Vertice, DefaultWeightedEdge> grafo, String nome) {
        Vertice pessoaEncontrada = null;
        pessoaEncontrada = buscarPessoaPorNome(grafo,nome);

        if (pessoaEncontrada != null) {
            grafo.removeVertex(pessoaEncontrada);
            System.out.println("Pessoa removida do grafo.");
        } else {
            System.out.println("Pessoa com o nome " + nome + " não encontrada no grafo.");
        }
    }

    public static Vertice buscarPessoaPorNome(Graph<Vertice, DefaultWeightedEdge> grafo, String nomeProcurado) {
        for (Vertice pessoa : grafo.vertexSet()) {
            if (pessoa.getNome().equals(nomeProcurado)) {
                return pessoa;
            }
        }
        return null;
    }
    public static void criarAresta(Graph<Vertice, DefaultWeightedEdge> grafo, String nome1, String nome2,Double peso) {
        Vertice pessoa1 = buscarPessoaPorNome(grafo, nome1);
        Vertice pessoa2 = buscarPessoaPorNome(grafo, nome2);

        if (pessoa1 != null && pessoa2 != null) {
            DefaultWeightedEdge aresta = grafo.addEdge(pessoa1, pessoa2);
            grafo.setEdgeWeight(aresta, peso);
            System.out.println("Aresta criada entre " + pessoa1.getNome() + " e " + pessoa2.getNome() + " com peso " + peso);
        } else {
            System.out.println("Pessoas não encontradas para criar a aresta.");
        }
    }
    public static void removerAresta(Graph<Vertice, DefaultWeightedEdge> grafo, String nome1, String nome2) {
        Vertice pessoa1 = buscarPessoaPorNome(grafo, nome1);
        Vertice pessoa2 = buscarPessoaPorNome(grafo, nome2);

        if (pessoa1 != null && pessoa2 != null) {
            DefaultWeightedEdge aresta = grafo.getEdge(pessoa1, pessoa2);
            if (aresta != null) {
                grafo.removeEdge(aresta);
                System.out.println("Aresta removida entre " + pessoa1.getNome() + " e " + pessoa2.getNome());
            } else {
                System.out.println("Aresta não encontrada entre " + pessoa1.getNome() + " e " + pessoa2.getNome());
            }
        } else {
            System.out.println("Pessoas não encontradas para remover a aresta.");
        }
    }

    public static void imprimirGrafo(Graph<Vertice, DefaultWeightedEdge> grafo) {
        System.out.println("\n-------------*****-------------\n");
        System.out.println("REPRESENTAÇÃO EM LISTA DE ADJACÊNCIA");
        System.out.println("\n-------------*****-------------\n");
        for (Vertice vertice : grafo.vertexSet()) {
            System.out.print(vertice + " -> ");
            Iterator<DefaultWeightedEdge> arestas = grafo.edgesOf(vertice).iterator();
            while (arestas.hasNext()) {
                DefaultWeightedEdge aresta = arestas.next();
                Vertice vizinho = grafo.getEdgeTarget(aresta);
                System.out.print(vizinho.getNome() + " - " + vizinho.getBairro());
                if (arestas.hasNext()) {
                    System.out.print(", ");
                }
            }

            System.out.println("\n");
        }
        System.out.println("------------*****-------------");
    }

    public static void buscarCaminhoMaisCurto(Graph<Vertice,DefaultWeightedEdge>grafo,String nome, String nom2){
        DijkstraShortestPath<Vertice, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);
        Vertice pessoa1 = null,pessoa2 = null;
        Double resultado=0.0;
        if(buscarPessoaPorNome(grafo,nome)!=null && buscarPessoaPorNome(grafo,nom2)!=null) {
            pessoa1 = buscarPessoaPorNome(grafo, nome);
            pessoa2 = buscarPessoaPorNome(grafo, nom2);
        }
        if(pessoa1 !=null && pessoa2 !=null) {
            resultado = dijkstra.getPathWeight(pessoa1, pessoa2);
        }
        if(resultado != 0){
            System.out.println("Menor distância de "+pessoa1.getNome()+" para: "+pessoa2.getNome() + "é: " + resultado);
        }else{
            System.out.println("Caminho não encontrado");
        }
    }


    public static void main(String[] args) {
        Graph<Vertice, DefaultWeightedEdge> grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------*****----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar Pessoa ao Grafo");
            System.out.println("2. Remover Pessoa do Grafo");
            System.out.println("3. Criar Aresta");
            System.out.println("4. Remover Aresta");
            System.out.println("5. Buscar Caminho Mais Curto");
            System.out.println("6. Imprimir Grafo");
            System.out.println("7. Sair");
            System.out.println("----------*****----------");
            System.out.println("\n");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    adicionarPessoaAoGrafo(grafo);
                    break;
                case 2:
                    System.out.print("Digite o usuário do vertice a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    removerPessoaDoGrafo(grafo, nomeRemover);
                    break;
                case 3:
                    System.out.print("Digite o usuário da primeira pessoa: ");
                    String nome1 = scanner.nextLine();
                    System.out.print("Digite o usuário da segunda pessoa: ");
                    String nome2 = scanner.nextLine();
                    System.out.print("Digite o peso da aresta: ");
                    double peso = scanner.nextDouble();
                    criarAresta(grafo, nome1, nome2, peso);
                    break;
                case 4:
                    System.out.print("Digite o usuário da primeira pessoa: ");
                    String nome1Remover = scanner.nextLine();
                    System.out.print("Digite o usuário da segunda pessoa: ");
                    String nome2Remover = scanner.nextLine();
                    removerAresta(grafo, nome1Remover, nome2Remover);
                    break;
                case 5:
                    System.out.print("Digite o usuário da primeira pessoa: ");
                    String nomeOrigem = scanner.nextLine();
                    System.out.print("Digite o usuário da segunda pessoa: ");
                    String nomeDestino = scanner.nextLine();
                    buscarCaminhoMaisCurto(grafo, nomeOrigem, nomeDestino);
                    break;
                case 6:
                    imprimirGrafo(grafo);
                    break;
                case 7:
                    System.out.println("ADEUS COWBOY, ATÉ A PRÓXIMA.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}

