/**
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: None
 *
 * Description: Class containing Body object and associated methods
**/
public class Body {
    
    /**
    * DO NOT EDIT ANY CODE BELOW THIS LINE
    *
    * You will get a style warning that reads:
    * "Variable '[some name]' must be private and have get/set methods."
    * Ignore any style warnings of this form. Our tests rely on these
    * variables being public. We will discuss public vs. private next week
    * in class. You are not expected to know it now.
    */
    public double px, py; //position
    public double vx, vy; //velocity
    public double m; //mass
    public String img; //image file
    
    public static final double G = 6.67e-11; //gravity constant
    
    /*DO NOT EDIT ANY CODE ABOVE THIS LINE*/
    
    /**
    * Constructor: This creates a new instance of a body object.
    */
    public Body(double mass, double posX, double posY,
    double velX, double velY, String imageFile) {
        m = mass; // Sets mass value
        px = posX; // Sets x position value
        py = posY; // Sets y position value
        vx = velX; // Sets x velocity value
        vy = velY; // Sets y velocity value
        img = imageFile; // Sets associated image file
    }
    
    /**
    * Description: returns a string representation of the body for the
    * purposes of printing. We will discuss toString methods in class.
    *
    * DO NOT EDIT THIS METHOD AT ALL!
    */
    public String toString() {
        return String.format("%12.5e %12.5e %12.5e %12.5e %12.5e %12s",
        m, px, py, vx, vy, img);
    }
    
    /**
     * Inputs: Body other representing other planet
     * Outputs: double horizontalDistance
     * Description: Returns horizontal distance between called Body and other Body
    */
    public double distanceToX(Body other) {
        return other.px - this.px;
    }
    
    /**
     * Inputs: Body other representing other planet
     * Outputs: double verticalDistance
     * Description: Returns vertical distance between called Body and the other Body
    */
    public double distanceToY(Body other) {
        return other.py - this.py; 
    }
    
    /**
     * Inputs: Body other representing other planet
     * Outputs: double totalDistance 
     * Description: Returns total distance between called Body and the other Body
    */
    public double distanceTo(Body other) {
        // Returns 
        return Math.sqrt(this.distanceToX(other) * this.distanceToX(other) + 
                         this.distanceToY(other) * this.distanceToY(other)); 
    }
    
    /**
     * Inputs: Body other representing other planet
     * Outputs: double totalForce
     * Description: Returns total force between the called Body and the other Body
    */
    public double force(Body other) {
        return G * this.m * other.m / (this.distanceTo(other) * 
                                       this.distanceTo(other)); 
    }
    
    /**
     * Inputs: Body other representing other planet
     * Outputs: double horizontalForce
     * Description: Returns force acting horizontally between the called 
     *              Body and the other Body
    */
    public double forceX(Body other) {
        return this.force(other) * this.distanceToX(other) / this.distanceTo(other); 
    }
    
    /**
     * Inputs: Body other representing other planet
     * Outputs: doubel verticalForce
     * Description: Returns force acting vertically on called 
     *              Body and the other Body
    */
    public double forceY(Body other) {
        return this.force(other) * this.distanceToY(other) / this.distanceTo(other);
    }
    
    /**
     * Inputs: None
     * Outputs: None
     * Description: Uses PennDraw to draw the Body that this function was called on
    */
    public void draw() {
        PennDraw.picture(this.px, this.py, this.img); 
    }
    
    /**
     * Inputs: double timeIncrement
     * Outputs: None
     * Description: Updates the position of the Body the function is called on
    */
    public void move(double timeStep) {
        this.px += timeStep * this.vx; // Updates px
        this.py += timeStep * this.vy; // Updates py
    }
    
    /**
     * Inputs: Body other representing other planet, double timeIncrement
     * Outputs: None
     * Description: Updates the velocities of the Body the function is called on as
     *              a result of Body other's gravity
    */
    public void getAffectedBy(Body other, double timeStep) {
        this.vx += timeStep * this.forceX(other) / this.m; // Updates vx
        this.vy += timeStep * this.forceY(other) / this.m; // Updates vy
    }
}
