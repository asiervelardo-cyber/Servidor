public class Main {
    public static void main(String[] args) {
        AndaluciaFactory andalucia = new AndaluciaFactory();
        andalucia.createElementoAndaluz("FeriaDeAbril").describir();
        andalucia.createElementoAndaluz("Flamenco").describir();
        andalucia.createElementoAndaluz("Gazpacho").describir();
    }
}
