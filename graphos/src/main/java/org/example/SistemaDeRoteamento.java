package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Iterator;

public class SistemaDeRoteamento {

    public static void main(String[] args) {
        // Criando grafo direcionado e ponderado
        Graph<Vertice, DefaultWeightedEdge> grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        //Criando Vertices
        Vertice loja = new Vertice("Loja", "Centro");
        Vertice chupisca = new Vertice("Rafael", "Cohab");
        Vertice rodrigo = new Vertice("Rodrigo", "Centro");
        Vertice jose = new Vertice("Jose", "Nova Cohab");
        Vertice will= new Vertice("Will","Rio de Janeiro");

        // Adicionando vértices ao grafo
        grafo.addVertex(loja);
        grafo.addVertex(chupisca);
        grafo.addVertex(rodrigo);
        grafo.addVertex(jose);
        grafo.addVertex(will);

        // Adicione as rotas e suas distâncias como arestas ponderadas
        DefaultWeightedEdge rota1 = grafo.addEdge(loja, chupisca);
        grafo.setEdgeWeight(rota1, 5.0);

        DefaultWeightedEdge rota2 = grafo.addEdge(chupisca, jose);
        grafo.setEdgeWeight(rota2, 10.0);

        DefaultWeightedEdge rota3 = grafo.addEdge(loja, rodrigo);
        grafo.setEdgeWeight(rota3, 15.0);

        // Calcular a menor distância de Loja para cada vértice
        DijkstraShortestPath<Vertice, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);

        double menorDistanciaIntermediario = dijkstra.getPathWeight(loja, chupisca);
        System.out.println("Menor distância da loja para: "+chupisca.getNome() + "No bairro: "+chupisca.getBairro() +"é: " + menorDistanciaIntermediario);

        double menorDistanciaAcessivelDiretamente = dijkstra.getPathWeight(loja, rodrigo);
        System.out.println("Menor distância da loja para: "+rodrigo.getNome() + "No bairro: "+rodrigo.getBairro() +"é: " + menorDistanciaAcessivelDiretamente);

        double menorDistanciaAcessivelViaIntermediario = dijkstra.getPathWeight(loja, jose);
        System.out.println("Menor distância da loja para: "+jose.getNome() + "No bairro: "+jose.getBairro() +"é: " + menorDistanciaAcessivelViaIntermediario);

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
        // Acessar um vértice inacessível
        try {
            double menorDistanciaInacessivel = dijkstra.getPathWeight(loja, will);
            System.out.println("Menor distância da loja para: " + will.getNome()+" é: "+ menorDistanciaInacessivel);
        } catch (IllegalArgumentException e) {
            System.out.println("Não é possível acessar o vértice Inacessível a partir da Loja.");
        }
    }
}

