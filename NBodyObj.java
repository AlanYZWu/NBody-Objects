
/**
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java NBodyObj simulationTime timeIncrement fileName
 *
 * Description: Simulates an area of space and some planets
**/

public class NBodyObj {
    public static void main(String[] args) {
        double simulationTime = Double.parseDouble(args[0]); // Total time sim runs
        double timeStep = Double.parseDouble(args[1]); // Time elapsed after loop
        double timeElapsed = 0; // Counter tracking how much time has passed
        String fileName = args[2]; // Name of file containing Space object values
        
        Space universe = new Space(fileName); // Space object to be simulated
        
        PennDraw.enableAnimation(30); // Enables animation
        
        while (timeElapsed < simulationTime) {
            universe.draw(); // Draws the Space object and the bodies within
            universe.simulate(timeStep); // Updates the positions of the bodies
            
            PennDraw.advance(); // Contiues to the next frame of animation
            
            timeElapsed += timeStep; // Increases time counter
        }
        
        PennDraw.disableAnimation(); // Disables animation
        
        System.out.println(universe); // Prints Space object
    }
}