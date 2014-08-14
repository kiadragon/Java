import imagereader.Runner;

public final class ProgramRunner {
    private ProgramRunner(){}
    public static void main(String[] args){

        ImageIOImplementation image = new ImageIOImplementation();
        ImageProcessor processor = new ImageProcessor();
        Runner.run(image, processor);
    }
}