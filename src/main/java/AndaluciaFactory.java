public class AndaluciaFactory extends ElementoAndaluzFactory{
    //Creamos la clase AndaluciaFactory que se extiende de ElementoAndaluzFactory y modificamos la funcion heredada
    @Override
    public ElementoAndaluz createElementoAndaluz(String tipo){
        return switch (tipo) {
            case "FeriaDeAbril" -> new FeriaDeAbril();
            case "Flamenco" -> new Flamenco();
            case "Gazpacho" -> new Gazpacho();
            default -> null;
        };
    }
}

