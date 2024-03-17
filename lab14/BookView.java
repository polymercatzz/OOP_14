import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class BookView implements ActionListener,WindowListener{
    private JFrame jfm, jfn;
    private JPanel main, pg1, pg2,  pf1, pf2, main2, pg3, pf3;
    private JLabel lname, lprice, ltype;
    private JTextField fname, fprice, fint, fname2, fprice2;
    private JComboBox com, com2;
    private JButton add, up, de, left, right, ins;
    private String[] data = {"General", "Computer","Math&Sci","Photo"};
    private Book book;
    private ArrayList<Book> abook;
    public BookView(){
        abook = new ArrayList<Book>();
        jfm = new JFrame();
        main = new JPanel();
        pg1 = new JPanel(new GridLayout(3,2));
        pg2 = new JPanel(new GridLayout(2,1));
        pf1 = new JPanel(new FlowLayout());
        pf2 = new JPanel(new FlowLayout());
        lname = new JLabel("Name");
        lprice = new JLabel("Price");
        ltype = new JLabel("Type");
        com = new JComboBox(data);
        fname = new JTextField(10);
        fprice = new JTextField();
        fint = new JTextField("0",5);
        add = new JButton("Add");
        up = new JButton("Update");
        de = new JButton("Delete");
        left = new JButton("<<<");
        right = new JButton(">>>");
        
        pg1.add(lname);
        pg1.add(fname);
        pg1.add(lprice);
        pg1.add(fprice);
        pg1.add(ltype);
        pg1.add(com);

        
        pf1.add(left);
        pf1.add(fint);
        pf1.add(right);
        
        pf2.add(add);
        pf2.add(up);
        pf2.add(de);
        
        pg2.add(pf1);
        pg2.add(pf2);
        
        main.add(pg1);
        main.add(pg2);
        
        this.add.addActionListener(this);
        this.up.addActionListener(this);
        this.de.addActionListener(this);
        this.left.addActionListener(this);
        this.right.addActionListener(this);
        
        jfm.add(main);
        jfm.addWindowListener(this);
        jfm.setSize(220,180);
        jfm.setResizable(false);
        jfm.setVisible(true);
        jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        try {
        // Set Look and Feel
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
        new BookView();
    });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(add)){
            jfn = new JFrame();
            main2 = new JPanel();
            pg3 = new JPanel(new GridLayout(3,2));
            pf2 = new JPanel(new FlowLayout());
            ins = new JButton("Insert");
            com2 = new JComboBox(data);
            fname2= new JTextField(10);
            fprice2 = new JTextField();
            
            pg3.add(lname);
            pg3.add(fname2);
            pg3.add(lprice);
            pg3.add(fprice2);
            pg3.add(ltype);
            pg3.add(com2);
            pf2.add(ins);
            
            main2.add(pg3);
            main2.add(pf2);
            
            ins.addActionListener(this);
            
            jfn.add(main2);
            jfn.setSize(220,150);
            jfn.setResizable(false);
            jfn.setVisible(true);
            jfn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else if (e.getSource().equals(ins)){
            book = new Book(fname2.getText(),Double.parseDouble(fprice2.getText()), (String) com2.getSelectedItem());
            abook.add(book);
            JOptionPane.showMessageDialog(null, "Done it.", "", JOptionPane.INFORMATION_MESSAGE);
            jfn.dispose();
            fname.setText(abook.get(abook.size()-1).getName());
            fprice.setText(abook.get(abook.size()-1).getPrice()+"");
            com.setSelectedItem(abook.get(abook.size()-1).getType());
            fint.setText(abook.size()+"");
        }else if (e.getSource().equals(up)){
            if ((Integer.parseInt(fint.getText())) == 0){
                fname.setText("");
                fprice.setText("");
                com.setSelectedItem("General");
                fint.setText(0+"");
            }else{
                abook.set(Integer.parseInt(fint.getText())-1, new Book(fname.getText(),Double.parseDouble(fprice.getText()), (String) com2.getSelectedItem()));
                fname.setText(abook.get(Integer.parseInt(fint.getText())-1).getName());
                fprice.setText(abook.get(Integer.parseInt(fint.getText())-1).getPrice()+"");
                com.setSelectedItem(abook.get(Integer.parseInt(fint.getText())-1).getType());
                JOptionPane.showMessageDialog(null, "Done it.", "Update Command", JOptionPane.INFORMATION_MESSAGE); 
            }
            
        }else if (e.getSource().equals(de)){
            if ((Integer.parseInt(fint.getText())-1) == 0){
                abook.remove(Integer.parseInt(fint.getText())-1);
                fname.setText("");
                fprice.setText("");
                com.setSelectedItem("General");
                fint.setText(0+"");
                JOptionPane.showMessageDialog(null, "Done it.", "Delete Command", JOptionPane.INFORMATION_MESSAGE);
            }else{
                abook.remove(Integer.parseInt(fint.getText())-1);
                fname.setText(abook.get(Integer.parseInt(fint.getText())-2).getName());
                fprice.setText(abook.get(Integer.parseInt(fint.getText())-2).getPrice()+"");
                com.setSelectedItem(abook.get(Integer.parseInt(fint.getText())-2).getType());
                fint.setText(Integer.parseInt(fint.getText())-1+"");
                JOptionPane.showMessageDialog(null, "Done it.", "Delete Command", JOptionPane.INFORMATION_MESSAGE);
               }
        }else if (e.getSource().equals(left)){
            if ((Integer.parseInt(fint.getText())-1) != 0){
                fname.setText(abook.get(Integer.parseInt(fint.getText())-2).getName());
                fprice.setText(abook.get(Integer.parseInt(fint.getText())-2).getPrice()+"");
                com.setSelectedItem(abook.get(Integer.parseInt(fint.getText())-2).getType());
                fint.setText(Integer.parseInt(fint.getText())-1+"");
            }
        }else if (e.getSource().equals(right)){
            if ((Integer.parseInt(fint.getText())) < abook.size()){
                fname.setText(abook.get(Integer.parseInt(fint.getText())).getName());
                fprice.setText(abook.get(Integer.parseInt(fint.getText())).getPrice()+"");
                com.setSelectedItem(abook.get(Integer.parseInt(fint.getText())).getType());
                fint.setText(Integer.parseInt(fint.getText())+1+"");
            }
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
        File f = new File("Book.data");
        if (f.exists()){
            try (FileInputStream fn = new  FileInputStream("Book.data");
                 ObjectInputStream in = new ObjectInputStream(fn);){
                 abook = (ArrayList<Book>) in.readObject();
                 if(!abook.isEmpty()) {
                    fname.setText(abook.get(0).getName());
                    fprice.setText(abook.get(0).getPrice()+"");
                    com.setSelectedItem(abook.get(0).getType());
                    fint.setText(1+""); 
                 }
            } catch(EOFException x){
            } catch (IOException i){
                i.printStackTrace();
            } catch (ClassNotFoundException c){
                c.printStackTrace();
            }
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        try (FileOutputStream fn = new  FileOutputStream("Book.data");
             ObjectOutputStream outt = new ObjectOutputStream(fn);){
            if(!abook.isEmpty()) {
                outt.writeObject(abook);
            }
        }catch (IOException i){
            i.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        }

    @Override
    public void windowIconified(WindowEvent e) {
        }

    @Override
    public void windowDeiconified(WindowEvent e) {
        }

    @Override
    public void windowActivated(WindowEvent e) {
        }

    @Override
    public void windowDeactivated(WindowEvent e) {
        }
}
