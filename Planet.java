import java.text.DecimalFormat;

public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double g = 6.67e-11;

    public Planet(double xP,double yP,double xV,
                  double yV,double mass,String img){
        this.xxPos = xP;
        this.xxVel = xV;
        this.yyPos = yP;
        this.yyVel = yV;
        this.mass = mass;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.xxVel = p.xxVel;
        this.yyPos = p.yyPos;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double calcDistance = 0.0;
        double dx = (p.xxPos - this.xxPos)*(p.xxPos - this.xxPos);
        double dy = (p.yyPos - this.yyPos)*(p.yyPos - this.yyPos);
        calcDistance = dx+dy;
        return Math.sqrt(calcDistance);
    }

    public double calcForceExertedBy(Planet p){
        if (p.equals(this)){
            return 0;
        }
        double force = g*this.mass*p.mass/(calcDistance(p)*calcDistance(p));
        return force;
    }

    public double calcForceExertedByX(Planet p){
        if (0 == this.calcForceExertedBy(p)){
            return 0;
        }
        double r;
        if (p.xxPos>this.xxPos){
            r = p.xxPos - this.xxPos;
        }else{
            r = this.xxPos - p.xxPos;
        }
        return this.calcForceExertedBy(p)*r/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        if (0 == this.calcForceExertedBy(p)){
            return 0;
        }
        double r;
        if (p.yyPos>this.yyPos){
            r = p.yyPos - this.yyPos;
        }else{
            r = this.yyPos - p.yyPos;
        }
        return this.calcForceExertedBy(p)*r/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double force = 0.0;
        for (Planet planet : planets) {
            force = force + this.calcForceExertedByX(planet);
        }
        return force;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double force = 0.0;
        for (Planet planet : planets) {
            force = force + this.calcForceExertedByY(planet);
        }
        return force;
    }

    public void update(double time,double x_force,double y_force){
        double ax = x_force/this.mass;
        this.xxVel = this.xxVel + ax*time;
        this.xxPos = this.xxPos + this.xxVel*time;
        double ay = y_force/this.mass;
        this.yyVel = this.yyVel + ay*time;
        this.yyPos = this.yyPos + this.yyVel*time;
    }

    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
