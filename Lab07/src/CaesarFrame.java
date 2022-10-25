import java.lang.annotation.Documented;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;


public class CaesarFrame extends JFrame {
    private JTextField tf1, tf2;
    private JButton jb;
    private JComboBox cb;
    private boolean decode = false;

    public CaesarFrame() {
        // 2. feladat
        super("SwingLab");
        setSize(400, 110);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 3.+ feladat
        JPanel jp1, jp2;
        GridLayout gl = new GridLayout();
        gl.setRows(2);
        gl.setColumns(1);
        this.setLayout(gl);

            // abc létrehozása a combo boxba
        Object[] abc = new Object[26];
        for (int i = (int)'A'; i <= (int)'Z'; ++i) {
            abc[i-(int)'A'] = (char)i;
        }
        cb = new JComboBox(abc);
        
        // felső panel
        jp1 = new JPanel(new FlowLayout());
        add(jp1);
        jp1.add(cb);
        tf1 = new JTextField(20); jp1.add(tf1);
        tf1.addFocusListener(new TextFieldFocus(false));
        // Documentlistener
        InputFieldKeyListener kl = new InputFieldKeyListener();
        tf1.getDocument().addDocumentListener(kl);
        
        // JButton
        jb = new JButton("Code!"); jb.addActionListener(new OkActionButtonListener());
        jp1.add(jb);
        

        // alsó panel
        jp2 = new JPanel(new BorderLayout());
        add(jp2);
        JLabel jl = new JLabel("Output"); jp2.add(jl, BorderLayout.WEST);
        tf2 = new JTextField(20); 
        tf2.setEditable(true);
        // focuslistener decode-hoz
        tf2.addFocusListener(new TextFieldFocus(true)); jp2.add(tf2);
        
        setResizable(true);
        setVisible(true);
    }

    private class OkActionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (decode) {
                tf1.setText(CaesarCode.caesarDecode(tf2.getText(), (char) cb.getSelectedItem()));
            } else {
                tf2.setText(CaesarCode.caesarCode(tf1.getText(), (char) cb.getSelectedItem()));
            }
        }
    }

    private class InputFieldKeyListener implements DocumentListener  {
        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!decode)
                tf2.setText(CaesarCode.caesarCode(tf1.getText(), (char) cb.getSelectedItem()));
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (!decode)
                tf2.setText(CaesarCode.caesarCode(tf1.getText(), (char) cb.getSelectedItem()));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (!decode)
                tf2.setText(CaesarCode.caesarCode(tf1.getText(), (char) cb.getSelectedItem()));
        }
    }

    private class TextFieldFocus implements FocusListener {
        private boolean focus;
        
        public TextFieldFocus(boolean cd) {
            focus = cd;
        }
        
        @Override
        public void focusGained(FocusEvent fe) {

        }

        @Override
        public void focusLost(FocusEvent fe) {
            decode = focus;
        }
    }
}
