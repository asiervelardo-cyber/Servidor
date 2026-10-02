public class AndaluciaFactory extends ElementoAndaluzFactory{
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

