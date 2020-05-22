import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(MyPaint.c);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));
        g2.setColor(MyPaint.c);
		
		if(MyPaint.obj == "Circle") {
		 if(MyPaint.flag == false) {
				if(MyPaint.type == 1) {
					g.drawOval(MyPaint.x,MyPaint.y,MyPaint.w,MyPaint.h);
				}
				else if(MyPaint.type == 2) {
					g.fillOval(MyPaint.x, MyPaint.y, MyPaint.w, MyPaint.h);
				}
			}
	}
		
		/*if (MyPaint.obj == "Line") {
			
			//**************
			g2.draw;
		    
			
		}
		*/
		if(MyPaint.obj == "Square"){
			if(MyPaint.flag == false) {
				if(MyPaint.type == 1) {
				g.drawRect(MyPaint.x, MyPaint.y, 150, 150);
			
			}
				
				else if(MyPaint.type == 2) {
				g.fillRect(MyPaint.x, MyPaint.y, 150, 150);
			}
		}
			
			else  {
				for (int i = 0; i < MyPaint.rectangles.size(); i++) {
				g.drawRect(MyPaint.rectangles.get(i).x, MyPaint.rectangles.get(i).y, 150, 150);
			}
		}
		}
	}

	
}
