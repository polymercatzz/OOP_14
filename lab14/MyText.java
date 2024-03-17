import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyText implements ActionListener{
    private JFrame jf;
    private JPanel jp;
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem nw, open, save, close;
    private JTextArea area;
    public MyText(){
        jf = new JFrame("My Text Editer");
        jp = new JPanel(new GridLayout(1,1));
        menubar = new JMenuBar();
        menu = new JMenu("File");
        nw = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        close = new JMenuItem("Close");
        area = new JTextArea();
        menu.add(nw);
        menu.add(open);
        menu.add(save);
        menu.addSeparator();
        menu.add(close);
        nw.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        close.addActionListener(this);
        menubar.add(menu);
        jp.add(area);
        jf.add(jp);
        jf.setJMenuBar(menubar);
        jf.setSize(500, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new MyText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")){
            area.setText("");
        } else if (e.getActionCommand().equals("Open")){
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(jf);
            File f = fc.getSelectedFile();
            System.out.println(f.getName());
            try (FileInputStream fn = new  FileInputStream(f.getName());
             ObjectInputStream outt = new ObjectInputStream(fn);){
                area.setText((String) outt.readObject());
            }catch (IOException i){
                i.printStackTrace();
            } catch (ClassNotFoundException ex) {
            }
        } else if (e.getActionCommand().equals("Save")){
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(jf);
            File f = fc.getSelectedFile();
            System.out.println(f.getName());
            try (FileOutputStream fn = new  FileOutputStream(f.getName());
             ObjectOutputStream outt = new ObjectOutputStream(fn);){
                outt.writeObject(area.getText());
            }catch (IOException i){
                i.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Close")){
            jf.dispose();
        }
    }
}
