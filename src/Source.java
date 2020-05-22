import java.awt.Graphics;

import javax.swing.JPanel;

public  class Source extends JPanel {
	public static void main(String[] args) {
		MyPaint paint = new MyPaint();
		paint.setVisible(true);
		paint.setSize(800, 600);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(MyPaint.c);
		
		if(MyPaint.flag == false) {
			if(MyPaint.type == 1) {
				g.drawRect(MyPaint.x, MyPaint.y, 150, 150);
			}
			else if(MyPaint.type == 2) {
				g.fillRect(MyPaint.x, MyPaint.y, 150, 150);
			}
			
		}
		else {
			for (int i = 0; i < MyPaint.rectangles.size(); i++) {
				g.drawRect(MyPaint.rectangles.get(i).x, MyPaint.rectangles.get(i).y, 150, 200);
			}
		}
	}

	
}
