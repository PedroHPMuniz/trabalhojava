import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListaProdutos {

    public static class Produto {
        private String nome;
        private int quantidade;
        private float valor;
        private String categoria;

        public Produto(String nome, int quantidade, float valor, String categoria) {
            this.nome = nome;
            this.quantidade = quantidade;
            this.valor = valor;
            this.categoria = categoria;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public float getValor() {
            return valor;
        }

        public void setValor(float valor) {
            this.valor = valor;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    }

    public static void inserirProduto(Produto[] lista, int tamanho) {
        Scanner scanner = new Scanner(System.in);

        mostrarProdutos(lista);

        int indice;
        do {
            System.out.println("Informe onde você deseja inserir o produto (valores de 1 a " + tamanho + "): ");
            indice = scanner.nextInt();
        } while (indice < 1 || indice > tamanho);

        scanner.nextLine(); // Limpar o buffer

        System.out.println("Insira o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.println("Insira a quantidade de produtos: ");
        int quantidade = scanner.nextInt();

        System.out.println("Insira o valor do produto: ");
        float valor = scanner.nextFloat();

        scanner.nextLine(); // Limpar o buffer

        System.out.println("Insira a categoria do produto: ");
        String categoria = scanner.nextLine();

        Produto produto = new Produto(nome, quantidade, valor, categoria);
        lista[indice - 1] = produto;

        System.out.println("Produto adicionado.");
    }

    public static void mostrarProdutos(Produto[] lista) {
        System.out.println("Lista de compras:");
        System.out.println("Produto | Quantidade | Valor | Categoria");
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                Produto produto = lista[i];
                System.out.println(
                        produto.getNome() + " | " + produto.getQuantidade() + " | " + produto.getValor() + " | "
                                + produto.getCategoria());
            }
        }
    }

    public static void removerProduto(Produto[] lista, String nomeProduto) {
        boolean encontrado = false;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && lista[i].getNome().equalsIgnoreCase(nomeProduto)) {
                encontrado = true;
                lista[i] = null;
                System.out.println("Produto removido.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void calcularValorPorCategoria(Produto[] lista) {
        Map<String, Float> valorPorCategoria = new HashMap<>();

        for (int i = 0; i < lista.length; i++) {
            Produto produto = lista[i];
            if (produto != null) {
                String categoria = produto.getCategoria();
                float valorProduto = produto.getQuantidade() * produto.getValor();

                if (valorPorCategoria.containsKey(categoria)) {
                    float valorTotalCategoria = valorPorCategoria.get(categoria);
                    valorPorCategoria.put(categoria, valorTotalCategoria + valorProduto);
                } else {
                    valorPorCategoria.put(categoria, valorProduto);
                }
            }
        }

        // Imprimir o valor total por categoria
        for (Map.Entry<String, Float> entry : valorPorCategoria.entrySet()) {
            System.out.println("Categoria: " + entry.getKey() + ", Valor total: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Produto[] lista;
        int produtos, opcao;
        Scanner scanner = new Scanner(System.in);
        String nomeProduto;

        System.out.println("Insira a quantidade de produtos que serão inseridos na sua lista de compras:");
        produtos = scanner.nextInt();
        lista = new Produto[produtos];

        do {
            System.out.println(
                    "Escolha uma opção:\n1 - Mostrar lista de compras.\n2 - Inserir produtos na lista de compras.\n3 - Calcular valor dos produtos da lista por categoria.\n4 - Remover produto da lista de compras.\n0 - Sair.");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    mostrarProdutos(lista);
                    break;
                case 2:
                    inserirProduto(lista, produtos);
                    break;
                case 3:
                    calcularValorPorCategoria(lista);
                    break;
                case 4:
                    System.out.println("Insira o produto a ser removido.");
                    nomeProduto = scanner.next();
                    removerProduto(lista, nomeProduto);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}