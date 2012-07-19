import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class QuizCardBuilder{

	JTextArea question,answer;
	ArrayList<QuizCard> cardList;
	JFrame frame;
	JLabel label1,label2;
	JButton nextButton;
	public static void main(String[] args){
		QuizCardBuilder Q=new QuizCardBuilder();
		Q.go();
	}
	public void go(){
		
		label1=new JLabel("Question :");
		label2=new JLabel("Answer :");
		frame=new JFrame("Quiz Card Builder");
		cardList=new ArrayList<QuizCard>();
		nextButton=new JButton("Next Card");
		nextButton.addActionListener(new nextButtonListener());
		
		question=new JTextArea(5,7);
		//question.setFont(new Font("sanserif",Font.BOLD,24));
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		
		JScrollPane qScroller=new JScrollPane(question);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		answer=new JTextArea(5,7);
		//answer.setFont(new Font("sanserif",Font.BOLD,24));
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		
		JScrollPane aScroller=new JScrollPane(answer);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel mainPanel=new JPanel();
		mainPanel.add(label1);
		mainPanel.add(qScroller);
		mainPanel.add(label2);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JMenuBar menuBar=new JMenuBar();
		
		JMenu fileMenu=new JMenu("File");
		JMenuItem newMenuItem=new JMenuItem("New");
		newMenuItem.addActionListener(new newListener());
		JMenu about=new JMenu("About Us");
		JMenuItem jj=new JMenuItem("Jignesh Jain");
		about.add(jj);
		jj.addActionListener(new jjListener());
		JMenuItem quit=new JMenuItem("Quit");
		quit.addActionListener(new quitListener());
		
		
		JMenuItem saveMenuItem=new JMenuItem("Save");
		saveMenuItem.addActionListener(new saveListener());
		
		fileMenu.add(newMenuItem);
	
		fileMenu.add(saveMenuItem);
		fileMenu.add(quit);
		menuBar.add(fileMenu);
		menuBar.add(about);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		
	}
	class nextButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			QuizCard card=new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			clearCard();		
			}
			
		}
	
	
	class newListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			clearCard();
			cardList.clear();
			
		}
	}
	class jjListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			question.setText("Hello, I am Jignesh Jain. I am currently pursuing Computer Engineering from VJTI, Mumbai. I have just started learning java and this app is an adoption from the book 'Head First Java by O'Reilly Publications'.");
			answer.setText("Contact:\tjigneshjain25@gmail.com\n\twww.facebook.com/jigneshthegreat");
			nextButton.setEnabled(false);
		}
	}
	class quitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	public class saveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			QuizCard card=new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			JFileChooser fileSave=new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
			clearCard();
			
		}
	}
	void saveFile(File file){
		try{
			BufferedWriter writer=new BufferedWriter(new FileWriter(file));
			for(QuizCard card:cardList){
				writer.write(card.getQuestion()+"/");
				writer.write(card.getAnswer()+"\n");
			} writer.close();
		}catch(IOException ex){
			System.out.println("ERROR WRITING THE FILE");
			ex.printStackTrace();
		}
	}
	void clearCard(){
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
}

