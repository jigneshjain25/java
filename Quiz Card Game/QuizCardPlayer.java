import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class QuizCardPlayer{

	JFrame frame;
	JTextArea display;
	boolean isShowAnswer;
	QuizCard currentCard;
	int cardIndex;
	JButton button;
	ArrayList<QuizCard> cardList;
	public static void main(String[] args){
		QuizCardPlayer player=new QuizCardPlayer();
		player.go();
	}
	public void go(){
		
	
		button=new JButton("Show Answer");
		button.addActionListener(new buttonListener());
		
		JPanel mainPanel=new JPanel();
		
		display=new JTextArea(10,30);
		display.setEditable(false);
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		JScrollPane qScroller=new JScrollPane(display);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		cardList=new ArrayList<QuizCard>();
		cardIndex=0;
		
		JMenuBar menuBar=new JMenuBar();
		JMenu file=new JMenu("File");
		JMenu about=new JMenu("About Us");
		JMenuItem jj=new JMenuItem("Jignesh Jain");
		jj.addActionListener(new jjListener());
		JMenuItem load=new JMenuItem("Load");
		JMenuItem quit=new JMenuItem("Quit");
		file.add(load);
		file.add(quit);
		about.add(jj);
		menuBar.add(file);
		menuBar.add(about);
		
		
		load.addActionListener(new openListener());
		quit.addActionListener(new quitListener());
		
		mainPanel.add(qScroller);
		mainPanel.add(button);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		frame=new JFrame("Quiz Card Player");
		frame.setSize(400,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		
		
	}
	class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(isShowAnswer)
			{
				display.setText(cardList.get(cardIndex).getAnswer());
				cardIndex++;
				isShowAnswer=false;
				button.setText("Next Card");
			}
			else{
				if(cardIndex>=cardList.size()){
					display.setText("Oops! That was the last card!");
					button.setEnabled(false);
			
				}
				else{
					isShowAnswer=true;
					display.setText(cardList.get(cardIndex).getQuestion());
					button.setText("Show Answer");
				}
			}
		
		}
	}
	class openListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			cardList.clear();
			JFileChooser fileOpen=new JFileChooser();
			fileOpen.showOpenDialog(frame);
			openFile(fileOpen.getSelectedFile());
			cardIndex=0;
			display.setText(cardList.get(cardIndex).getQuestion());
			isShowAnswer=true;
			button.setText("Show Answer");
		
		}
	}
	class quitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	class jjListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			display.setText("Hello, I am Jignesh Jain. I am currently pursuing Computer Engineering from VJTI, Mumbai. I have just started learning java and this app is an adoption from the book 'Head First Java by O'Reilly Publications'.\n\nContact:\tjigneshjain25@gmail.com\n\twww.facebook.com/jigneshthegreat");
			button.setEnabled(false);
		}
	}
	void openFile(File file){
		try{	
		
			FileReader fileReader=new FileReader(file);
			BufferedReader reader=new BufferedReader(fileReader);
			String line=null;
			while((line=reader.readLine())!=null){
				
				String result[]=line.split("/");
				QuizCard card=new QuizCard(result[0],result[1]);
				cardList.add(card);
			}
			button.setEnabled(true);
			
		}catch(IOException ex){
			System.out.println("ERROR OPENING THE FILE");
			ex.printStackTrace();
		}
	}
}
