import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MyPaint extends JFrame implements ActionListener, KeyListener, MouseListener {

	BorderLayout bl;
	protected static Color c;
	protected static int x;
	protected static int y;
	protected static int w;
	protected static int h;
	public static int type = 0;
	public static String obj;
	public static boolean flag = false;
	public static boolean move_flag = false;
	
	public static ArrayList<Points> rectangles = new ArrayList<Points>();
	//public static ArrayList<Points> circle = new ArrayList<Points>();
	public MyPaint() {
		super("My Paint Application");
		bl = new BorderLayout();
		setLayout(bl);
		menu();
		center();
		east();
	}
	JPanel jpEast;
	GridLayout gl;
	JButton jbColorChoose;
	JButton jbRectangle;
	JButton jbMRectangle;
	JButton jbMove;
	JButton raise;
	
	class Chatbox extends JFrame
	{
		JPanel chatboxHolder;
		JLabel l;
		Timer t;
		JTextField jt;
		boolean typing;
		JTextArea ta;
		public Chatbox() {
			createChatBox();
		}
		public void createChatBox() {
			setTitle("Chatbox");
			
			chatboxHolder = new JPanel();
			chatboxHolder.setLayout(new GridLayout(2,1));
	        l=new JLabel();
	        chatboxHolder.add(l);
	        
	        t=new Timer(1,new ActionListener(){
	            public void actionPerformed(ActionEvent ae)
	            {
	                // If the user isn't typing, he is thinking
	                if(!typing)
	                l.setText("Thinking..");
	            }
	        });
	        t.setInitialDelay(100);
	        jt=new JTextField();
	        chatboxHolder.add(jt);
	        add(chatboxHolder,BorderLayout.SOUTH);
	        jt.addKeyListener(new KeyAdapter() {
	        	public void keyPressed(KeyEvent ke)
	            {
	        		l.setText("You are typing.."); //the user is typing
	        		 t.stop();  // When key is pressed, stop the timer
	                 // so that the user is not thinking, he is typing
	        		 typing=true; //He is typing, the key is pressed
	        		 if(ke.getKeyCode()==KeyEvent.VK_ENTER) {
	        			 showLabel(jt.getText());
	        		 }
	        		 //If he presses enter, add text 
	            }
	        	
	            public void keyReleased(KeyEvent ke)
	            {
	            
	                // When the user isn't typing..
	                typing=false;
	                
	                if(!t.isRunning())
	         
	                t.start();
	            }
	        	
			});
	       // ta.setEditable(false);// Make it non-editable
	        //ta.setMargin(new Insets(7,7,7,7)); // Set some margin, for the text
	        JScrollPane js=new JScrollPane(ta);
	        add(js);
	        
	        addWindowListener(new WindowAdapter(){
	            public void windowOpened(WindowEvent we)
	            {
	                // Get the focus when window is opened
	                jt.requestFocus();
	            }
	        });
	        setSize(400,400);
	        setLocationRelativeTo(null);
	        setVisible(true);
	        
		}
		
		
		
		 private void showLabel(String text)
		    {
		        if(text.trim().isEmpty()) return;
		        ta.append(text+"\n");        
		        jt.setText("");
		        l.setText("");
		    }
		  public void main(String args[])
		    {
		        SwingUtilities.invokeLater(new Runnable(){
		            public void run()
		            {
		                new Chatbox();
		            }
		        });
		    }
	}
	
	Chatbox chat;
	public void east() {
		jpEast = new JPanel();
		jpEast.setBackground(Color.DARK_GRAY );
		
		//gl = new GridLayout(2, 1);
		//jpEast.setLayout(gl);
		add(jpEast,BorderLayout.EAST);
		
		raise = new JButton("Raise");
		raise.setBackground(Color.LIGHT_GRAY);
		raise.addActionListener(this);
		jpEast.add(raise);
		
		chat= new Chatbox();
		chat.setBackground(Color.LIGHT_GRAY);
	
		

	}
	
	
	
	JMenuBar menu;
	JMenu color,shape,move,exit;
	JMenuItem color_item,circle_item, rectangle_item, moreRectangle_item, move_item, exist_item;
	
	public void menu() {
		menu = new JMenuBar();
		menu.setBackground(Color.GRAY);
		color = new JMenu("Color");
		shape = new JMenu("Shape");
		move = new JMenu("Move");
		exit = new JMenu("Exit");
		
		color_item = new JMenuItem("Color Chooser");
		color_item.addActionListener(this);
		
		rectangle_item = new JMenuItem("Square");
		rectangle_item.addActionListener(this);
		
		moreRectangle_item = new JMenuItem("Multiple Square");
		moreRectangle_item.addActionListener(this);
		
		circle_item = new JMenuItem("Circle");
		circle_item.addActionListener(this);
		
		move_item = new JMenuItem("Move Object");
		move_item.addActionListener(this);
		
		exist_item = new JMenuItem("Close App");
		exist_item.addActionListener(this);
		
		addKeyListener(this);
		
		color.add(color_item);
		shape.add(rectangle_item);
		shape.add(moreRectangle_item);
		shape.add(circle_item);
		move.add(move_item);
		exit.add(exist_item);
		
		menu.add(color);
		menu.add(shape);
		menu.add(move);
		menu.add(exit);
		
		add(menu);
		setJMenuBar(menu);
	
	}
	
	JPanel jpCenter;
	
	public void center() {
		jpCenter = new PaintPanel();
		jpCenter.addMouseListener(this);
		jpCenter.setBackground(Color.white);
		add(jpCenter,BorderLayout.CENTER);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(obj=="Square") {
			if(flag == false) {
			x = e.getX();
			y = e.getY();
		
		}
			else {
			Points p = new Points();
			p.x = e.getX();
			p.y = e.getY();
			rectangles.add(p);
	
		}
		
			repaint();
		}
		
		if(obj== "Circle") {
			x = 100;
			y = 100;
			w = 100;
			h = 100;
			repaint();
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(move_flag == true) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				y-=3;
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x+=3;
				if(x > 770) {
					x = -90;
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				y+=3;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				x-=3;
				if(x < -90) {
					x = 780;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == color_item) {
			move_flag = false;
			c = JColorChooser.showDialog(this, "Choose Color", Color.red);
			repaint();
		
		}
		else if(e.getSource() == rectangle_item) {
			obj="Square";
			move_flag = false;
			flag = false;
			int choise = Integer.parseInt(JOptionPane.showInputDialog(this,"Enter\n1- Square\n2- Fill Square"));
			type = choise;
			JOptionPane.showMessageDialog(this, "Now Click Center Panel for Creating a Square");
		
		
		}
		else if(e.getSource() == circle_item) {
			obj="Circle";
			move_flag = false;
			flag = false;
			int choise = Integer.parseInt(JOptionPane.showInputDialog(this,"Enter\n1- Circle\n2- Fill circle"));
			type = choise;
			JOptionPane.showMessageDialog(this, "Now Click Center Panel for Creating a Circle");
		}
		else if(e.getSource() == moreRectangle_item) {
			move_flag = false;
			flag = true;
		}
		else if(e.getSource() == move_item) {
			move_flag = true;
			JOptionPane.showMessageDialog(this, "Only works one Square");
		}
		else if(e.getSource() == exist_item) {
			move_flag = false;
			System.exit(0);
		}

	}

}