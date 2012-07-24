import javax.swing.text.DefaultCaret;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleChatClient
{
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    JButton sendButton;
    Socket sock;
    String name;
    JTextField setName;
    JButton ok;
    public void go() {
        JFrame frame = new JFrame("Simple Chat Client by JJ");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(13, 20);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        Font bigFont=new Font("serif",Font.PLAIN,14);
        incoming.setFont(bigFont);
        
	DefaultCaret caret = (DefaultCaret)incoming.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        setName=new JTextField(20);
        sendButton = new JButton("Send");
        sendButton.setEnabled(false);
        ok=new JButton("Set Name");
        ok.addActionListener(new okListener());
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(setName);
        mainPanel.add(ok);
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();
        
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setVisible(true);
        
    }
    
    private void setUpNetworking() {
        try {
            sock = new Socket("180.148.49.143", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public class okListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		ok.setEnabled(false);
    		name=setName.getText();
    		setName.setEditable(false);
    		sendButton.setEnabled(true);
    	}
    }
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
            	writer.print(name+": ");
                writer.print(outgoing.getText()+"\n");
                writer.flush();
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    
    public static void main(String[] args) {
        new SimpleChatClient().go();
    }
    
    class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("client read " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

