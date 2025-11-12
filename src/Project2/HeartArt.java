//package Project2;
//
//import javax.swing.*; // GUI window ke liye
//import java.awt.*; // drawing ke liye (colors, graphics)
//import java.awt.geom.Path2D; // shapes ke liye (heart outline)
//import java.util.ArrayList;
//import java.util.List;
//
//public class HeartArt extends JPanel { // JPanel = drawing area
//
//    private final List<Point> points = new ArrayList<>(); // heart ke sare points store karne ke liye
//    private int step = 1; // kitna gap rakhe lines draw karte time
//    private int direction = 1; // line ka direction (aage-piche)
//
//    public HeartArt() {
//        setBackground(Color.BLACK); // background black rakh diya
//
//        // heart ke curve ke points nikalne ke liye formula
//        int N = 200; // total kitne points lenge (zyada -> smooth heart)
//        double scale = 10; // heart ka size
//        for (int i = 0; i < N; i++) {
//            double t = 2 * Math.PI * i / N; // angle ke basis pe points le rahe
//            // ye heart ka math formula hai
//            double x = 16 * Math.pow(Math.sin(t), 3);
//            double y = 13 * Math.cos(t) - 5 * Math.cos(2 * t)
//                    - 2 * Math.cos(3 * t) - Math.cos(4 * t);
//            // scale karke list me add kar diya
//            points.add(new Point((int) (x * scale), (int) (y * scale)));
//        }
//
//        // timer lagaya jisse baar-baar screen repaint ho -> animation mile
//        Timer timer = new Timer(50, e -> { // 50ms = speed (20 frames per sec)
//            step += direction; // step change karte ja rahe (lines shift hoti rahe)
//            if (step >= 60 || step <= 1) direction *= -1; // limit cross pe direction reverse
//            repaint(); // screen ko dobara draw kar do
//        });
//        timer.start(); // timer chalu kar diya
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g); // background clear kar diya
//
//        Graphics2D g2 = (Graphics2D) g; // better drawing ke liye Graphics2D liya
//        g2.translate(getWidth() / 2, getHeight() / 2 + 50); // heart ko center me laaya
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // lines smooth banayi
//
//        // ab cross lines draw karte hain
//        g2.setColor(Color.PINK); // line color pink
//        for (int i = 0; i < points.size(); i++) {
//            Point a = points.get(i); // ek point liya
//            Point b = points.get((i + step) % points.size()); // doosra point step ke gap pe
//            g2.drawLine(a.x, -a.y, b.x, -b.y); // line draw ki dono ke beech
//        }
//
//        // ab heart ka outer border draw karte hain
//        g2.setStroke(new BasicStroke(2)); // thodi moti line
//        g2.setColor(Color.RED); // border color red
//        Path2D path = new Path2D.Double(); // path bna rahe jisme sare points jodenge
//        Point start = points.get(0);
//        path.moveTo(start.x, -start.y); // start point
//        for (int i = 1; i < points.size(); i++) {
//            Point p = points.get(i);
//            path.lineTo(p.x, -p.y); // next points se connect karte ja rahe
//        }
//        path.closePath(); // heart band kar diya
//        g2.draw(path); // heart border draw kar diya
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("String-Art Heart ❤️"); // window ka title
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close pe exit
//        frame.add(new HeartArt()); // panel add kar diya jisme heart draw hoga
//        frame.setSize(800, 700); // window size
//        frame.setLocationRelativeTo(null); // center me window open ho
//        frame.setVisible(true); // show kar do window
//    }
//}
//
//
//package Project2;
//
//import javax.swing.*; // GUI window ke liye
//import java.awt.*; // drawing ke liye
//import java.awt.geom.Path2D; // shape ke liye
//import java.util.ArrayList;
//import java.util.List;
//
//public class HeartArt extends JPanel {
//
//    private final List<Point> points = new ArrayList<>();
//    private int step = 1;          // step = kitne gap pe line draw karni hai
//    private int direction = 1;     // step ka direction (forward/backward)
//    private int maxLines = 200;    // ek time pe kitni lines visible hongi (200 = full heart)
//
//    public HeartArt() {
//        setBackground(Color.BLACK);
//
//        // heart ke curve ke points (math formula)
//        int N = 200;
//        double scale = 10;
//        for (int i = 0; i < N; i++) {
//            double t = 2 * Math.PI * i / N;
//            double x = 16 * Math.pow(Math.sin(t), 3);
//            double y = 13 * Math.cos(t) - 5 * Math.cos(2 * t)
//                    - 2 * Math.cos(3 * t) - Math.cos(4 * t);
//            points.add(new Point((int) (x * scale), (int) (y * scale)));
//        }
//
//        // Infinite animation timer 🔁
//        Timer timer = new Timer(40, e -> {
//            step += direction; // step change karta rahe
//            if (step >= points.size() / 2 || step <= 1)
//                direction *= -1; // bounce back effect
//            repaint(); // har frame pe redraw
//        });
//        timer.start();
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2 = (Graphics2D) g;
//        g2.translate(getWidth() / 2, getHeight() / 2 + 50);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // Gradient effect (pink → red)
//        GradientPaint gradient = new GradientPaint(-100, -100, Color.PINK, 100, 100, Color.RED, true);
//        g2.setPaint(gradient);
//
//        // Intersecting lines animation
//        for (int i = 0; i < maxLines; i++) {
//            Point a = points.get(i);
//            Point b = points.get((i + step) % points.size());
//            g2.drawLine(a.x, -a.y, b.x, -b.y);
//        }
//
//        // Heart border (outer red outline)
//        g2.setColor(Color.RED);
//        g2.setStroke(new BasicStroke(2f));
//        Path2D path = new Path2D.Double();
//        Point start = points.get(0);
//        path.moveTo(start.x, -start.y);
//        for (int i = 1; i < points.size(); i++) {
//            Point p = points.get(i);
//            path.lineTo(p.x, -p.y);
//        }
//        path.closePath();
//        g2.draw(path);
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Infinity Heart ❤️‍🔥");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new HeartArt());
//        frame.setSize(800, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//}

package Project2;

import javax.swing.*; // GUI ke liye
import java.awt.*; // drawing ke liye
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class HeartArt extends JPanel {

    private final List<Point> points = new ArrayList<>();
    private int step = 1;          // lines move karne ke liye
    private int direction = 1;     // step ka direction change karne ke liye
    private int maxLines = 200;    // ek time pe kitni lines draw hongi
    private float alpha = 0f;      // text fade-in ke liye
    private boolean fadeIn = true; // direction of fading

    public HeartArt() {
        setBackground(Color.BLACK);

        // heart ke curve ke points (math formula)
        int N = 200;
        double scale = 15;
        for (int i = 0; i < N; i++) {
            double t = 2 * Math.PI * i / N;
            double x = 16 * Math.pow(Math.sin(t), 3);
            double y = 13 * Math.cos(t) - 5 * Math.cos(2 * t)
                    - 2 * Math.cos(3 * t) - Math.cos(4 * t);
            points.add(new Point((int) (x * scale), (int) (y * scale)));
        }

        // Timer for infinite animation + text fade effect
        Timer timer = new Timer(40, e -> {
            step += direction;
            if (step >= points.size() / 2 || step <= 1)
                direction *= -1;

            // text ke fade in/out ke liye alpha adjust
            if (fadeIn) {
                alpha += 0.02f;
                if (alpha >= 1f) fadeIn = false;
            } else {
                alpha -= 0.02f;
                if (alpha <= 0.1f) fadeIn = true;
            }

            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth() / 2, getHeight() / 2 + 50);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gradient effect for heart lines
        GradientPaint gradient = new GradientPaint(-100, -100, Color.PINK, 100, 100, Color.RED, true);
        g2.setPaint(gradient);

        // Draw moving intersecting lines
        for (int i = 0; i < maxLines; i++) {
            Point a = points.get(i);
            Point b = points.get((i + step) % points.size());
            g2.drawLine(a.x, -a.y, b.x, -b.y);
        }

        // Heart border
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2f));
        Path2D path = new Path2D.Double();
        Point start = points.get(0);
        path.moveTo(start.x, -start.y);
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            path.lineTo(p.x, -p.y);
        }
        path.closePath();
        g2.draw(path);

        // ❤️ Naam Display with Fade Effect ❤️
        String name = "Anjana"; // <--- yahan apna naam likh
        g2.setFont(new Font("Segoe Script", Font.BOLD, 50));
        g2.setColor(new Color(255, 105, 180, Math.min(255, (int) (alpha * 255)))); // pink glow + fade
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        g2.drawString(name, -textWidth / 2, 30); // heart ke beech me likh diya
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Heart of Love ❤️‍🔥");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new HeartArt());
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

