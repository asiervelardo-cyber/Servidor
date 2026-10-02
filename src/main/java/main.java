public class Main {
    public static void main(String[] args) {
        //Creamos un objeto AndaluciaFactory y usamos sus funciones;
        AndaluciaFactory andalucia = new AndaluciaFactory();
        andalucia.createElementoAndaluz("FeriaDeAbril").describir();
        andalucia.createElementoAndaluz("Flamenco").describir();
        andalucia.createElementoAndaluz("Gazpacho").describir();
    }
}
