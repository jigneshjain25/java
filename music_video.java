import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
public class music_video{
	static JFrame f=new JFrame("My first music video");
	static MyDrawPanel ml;
	public static void main(String[] args){
		music_video mini=new music_video();
		mini.go();
	}
	public void setUpGui(){
		ml=new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30,30,300,300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	public void go(){
		setUpGui();
		try{
			Sequencer player=MidiSystem.getSequencer();
			player.open();
			player.addControllerEventListener(ml,new int[] {127});
			Sequence seq=new Sequence(Sequence.PPQ,4);
			Track track=seq.createTrack();
			
			int r=0;
			for(int i=0;i<60;i+=4){
				r=(int)((Math.random()*50)+1);
				track.add(makeEvent(144,1,r,100,i));
				track.add(makeEvent(176,1,127,0,i));
				track.add(makeEvent(128,1,r,100,i+2));
			}		
			player.setSequence(seq);
			player.start();
			player.setTempoInBPM(120);
		}catch(Exception ex){ex.printStackTrace();}
	}
	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick){
		MidiEvent event=null;
		try{
			ShortMessage a=new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event=new MidiEvent(a,tick);
			
		}catch(Exception ex){}
		return event;
	}
}

class MyDrawPanel extends JPanel implements ControllerEventListener{
	
	boolean msg=false;
	public void controlChange(ShortMessage event){
		msg=true;
		repaint();
	}
	public void paintComponent(Graphics g){
		if(msg){
			Graphics2D g2=(Graphics2D)g;
			int r=(int)(Math.random()*255);
			int gr=(int)(Math.random()*255);
			int b=(int)(Math.random()*255);
			g.setColor(new Color(r,gr,b));
			int ht=(int)((Math.random()*120)+10);
			int width=(int)((Math.random()*120)+10);;
			int x=(int)((Math.random()*40)+10);
			int y=(int)((Math.random()*40)+10);
			g.fillRect(x,y,ht,width);
			msg=false;
		}
	}
}
