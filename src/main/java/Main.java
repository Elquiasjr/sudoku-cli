import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.model.Board;
import com.example.demo.model.Cell;
import com.example.demo.model.GameStatus;
import com.example.demo.util.BoardTemplate;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static GameStatus status = GameStatus.NAO_INICIADO;

    private static Board board;

    public static void main(String[] args) {
        var boardTemplate = BoardTemplate.BOARD_TEMPLATE;
        board = new Board();
        String input;
        try{
            input = String.valueOf(args[0]);
        } catch(Exception e){
            System.out.println("No input provided");
            return;
        }
       
        board.populateEmptyCells();

        var option = -1;
        while (true){
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option){
                case 1 -> board = startGame(input);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> System.out.printf(boardTemplate, board.getNumbers().toArray());
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static Board startGame(String input) {
        if(status == GameStatus.INICIADO){
            System.out.println("Jogo já iniciado");
            return board;
        }
        var board = new Board();
        Arrays.stream(input.split(" "))
        .forEach(entry -> { 
            String[] parts = entry.split(";");
            if(parts.length == 2){
                String[] xy = parts[0].split(",");
                String[] valueAndLock = parts[1].split(",");

                if(xy.length == 2 && valueAndLock.length == 2){
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);
                    int value = Integer.parseInt(valueAndLock[0]);
                    boolean isLocked = Boolean.parseBoolean(valueAndLock[1]);

                    board.setCell(x, y, new Cell(value, isLocked));
                }         
            }
        });
        board.populateEmptyCells();
        status = GameStatus.INICIADO;
        return board;
    }

    private static void inputNumber() {
        if(status != GameStatus.INICIADO){
            System.out.println("Jogo não iniciado, não é possível inserir números");
            return;
        }
        System.out.println("Digite as coordenadas da célula e o valor a ser inserido");
        System.out.print("row: ");
        var row = scanner.nextInt();
        System.out.print("col: ");
        var col = scanner.nextInt();
        System.out.print("value: ");
        var value = scanner.nextInt();

        var cell = new Cell(value, false);
        board.setCell(row, col, cell);

    }

    private static void showGameStatus(){
        System.out.println("Status do jogo: " + status.getStatusString());
    }

    private static void removeNumber() {
        if(status != GameStatus.INICIADO){
            System.out.println("Jogo não iniciado, não é possível inserir números");
            return;
        }
        System.out.println("Digite as coordenadas da célula a ser removida");
        System.out.print("row: ");
        var row = scanner.nextInt();
        System.out.print("col: ");
        var col = scanner.nextInt();
        board.removeCell(row, col);
    }

    private static void clearGame() {
        if(status == GameStatus.INICIADO){
            board = new Board();
            board.populateEmptyCells();
            status = GameStatus.NAO_INICIADO;
            System.out.println("Jogo limpo com sucesso");
        } else {
            System.out.println("Jogo não está iniciado, não pode ser limpo");
        }
        
    }

    private static void finishGame() {
        if(status == GameStatus.INICIADO){
            status = GameStatus.FINALIZADO;
        }
        
    }

}
