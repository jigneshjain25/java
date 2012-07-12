//A Simple animated gui
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class simplegui{
	JFrame f;
	JButton button3;
	int x=200,y=70;
	public static void main(String[] args){
		simplegui gui=new simplegui();
		gui.go();
	}
	public void go(){
		f=new JFrame();
		f.setVisible(true);
		f.setSize(300,300);
		mypanel newpanel=new mypanel();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button=new JButton("Change Colors");
		JButton button2=new JButton("Change Label");
		button3=new JButton("I am a label");
		f.getContentPane().add(BorderLayout.SOUTH,button);
		f.getContentPane().add(BorderLayout.WEST,button3);
		f.getContentPane().add(BorderLayout.EAST,button2);
		button.addActionListener(new buttonl());
		button2.addActionListener(new buttonll());
		f.getContentPane().add(newpanel);
		for(int i=0;i<300;i++)
		{
			x++;
			y++;
			newpanel.repaint();
			try{
				Thread.sleep(50);
			   }catch(Exception ex){}
		}
	}
	class buttonl implements ActionListener{
		public void actionPerformed(ActionEvent event){
			f.repaint();
		}
	}
	class buttonll implements ActionListener{
		public void actionPerformed(ActionEvent event){
			button3.setText("I am not a label");
		}
	}
	class mypanel extends JPanel{
		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0,0,this.getWidth(),this.getHeight());
			int red=(int)(Math.random()*255);
			int green=(int)(Math.random()*255);
			int blue=(int)(Math.random()*255);
			Color randomcolor=new Color(red,green,blue);
			g.setColor(randomcolor);
			g.fillOval(x,y,40,40);
		}
	
	}
}	
