/**
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java Space
 *
 * Description: Class that creates the Space object and its associated methods
**/
public class Space {
    
    /**
    * DO NOT EDIT ANY CODE BELOW THIS LINE
    *
    * You will get a style warning that reads:
    * "Variable '[some name]' must be private and have get/set methods."
    * Ignore any style warnings of this form. Our tests rely on these
    * variables being public. We will discuss public vs. private next week
    * in class. You are not expected to know it now.
    */
    public Body[] bodies; //array of Body objects in the space
    public double radius; //radius of the universe
    
    /* DO NOT EDIT ANY CODE ABOVE THIS LINE */
    
    /**
    * Constructor: This creates a new instance of a space object.
    */
    public Space(String filename) {
        In inStream = new In(filename); // File reader
        
        int numBodies = inStream.readInt(); // Number of bodies in Space object
        bodies = new Body[numBodies]; // Sets bodies array size
        
        radius = inStream.readDouble(); // Sets radius value
        PennDraw.setXscale(-radius, radius); // Sets canvas horizontal size
        PennDraw.setYscale(-radius, radius); // Sets canvas vertical size 
        
        // Initializes the bodies array
        for (int i = 0; i < numBodies; i++) {
            double mass = inStream.readDouble(); // Mass val for Body at bodies[i]
            double posX = inStream.readDouble(); // posX val for Body at bodies[i]
            double posY = inStream.readDouble(); // posX val for Body at bodies[i]
            double velX = inStream.readDouble(); // velX val for Body at bodies[i]
            double velY = inStream.readDouble(); // velY val for Body at bodies[i]
            String imgFile = inStream.readString(); // imgFile for Body at bodies[i]
            bodies[i] = new Body(mass, posX, posY, velX, velY, imgFile); 
        }
    }
    
    /**
    * Description: returns a string representation of space for the purposes
    * of printing. We have discussed toString methods in class.
    *
    * DO NOT EDIT THIS METHOD AT ALL!
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("" + bodies.length + "\n");
        sb.append(String.format("%.2e\n", radius) + "\n");
        for (int i = 0; i < bodies.length; i++) {
            sb.append(bodies[i].toString() + "\n");
        }
        return sb.toString();
    }
    
    /**
     * Inputs: None
     * Outputs: None
     * Description: Uses PennDraw to draw the Universe
    */
    public void draw() {
        PennDraw.clear(); // Clears canvas
        PennDraw.picture(0.0, 0.0, "starfield.jpg"); // Draws background
        
        // Draws each Body
        for (int i = 0; i < this.bodies.length; i++) {
            bodies[i].draw();
        }
        
    }
    
    /**
     * Inputs: double timeIncrement
     * Outputs: None
     * Description: Simulates one time increment of time timeStep within the
     *              universe. Updates the velocities and then the positions of each
     *              body.
    */
    public void simulate(double timeStep) {
        // Updates velocities of each body
        for (int i = 0; i < this.bodies.length; i++) {
            for (int j = 0; j < this.bodies.length; j++) {
                if (i != j) {
                    bodies[i].getAffectedBy(bodies[j], timeStep);
                }
            }
        }
        
        // Updates position of each body
        for (int i = 0; i < this.bodies.length; i++) {
            bodies[i].move(timeStep);
        }
    }
    
    public static void main(String[] args) {
        Space s = new Space("solarSystem.txt");
        s.draw();
    }
}
