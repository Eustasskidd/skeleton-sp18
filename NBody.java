import java.util.Arrays;

public class NBody {

    public static double readRadius(String path){
        In in = new In(path);
        in.readDouble();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int len = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[len];
        for (int i = 0;i<planets.length;i++) {
            planets[i] = new Planet(in.readDouble(),in.readDouble(),
                    in.readDouble(),in.readDouble(),in.readDouble(),
                    in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
//        double T = Double.parseDouble(args[0]);
//        double dt = Double.parseDouble(args[1]);
//        String fileName = args[2];
        double T = 157788000.0;
        double dt = 25000.0;
        String fileName = "data/planets.txt";
        StdDraw.setXscale(0, readRadius(fileName));
        StdDraw.setYscale(0, readRadius(fileName));
        StdDraw.picture(0,0,"images/starfield.jpg");
        Planet[] planets = readPlanets(fileName);
        for (Planet planet : planets) {
            planet.draw();
        }
        for (double i = 0;i<T;i=i+dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int j = 0;j<planets.length;j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0;j<planets.length;j++) {
                planets[j].update(dt,xForces[j],yForces[j]);
                System.out.println(planets[j].xxPos);
            }
            StdDraw.enableDoubleBuffering();
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }
}
