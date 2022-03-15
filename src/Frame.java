import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
                            //geben acht auf die Ereignisse die ausgeführt werden (wie Mausklicks usw.)
public class Frame extends JFrame implements ActionListener, ChangeListener {

    private int count = 0;
    private JLabel label;
    private JButton button1, button2;
    private JSlider slider;
    private JPanel panel;

    public Frame(){
        super("Window");

        setLayout(null);

        button1 = new JButton("+");
        button1.setBackground(Color.yellow);
        button1.setBounds(40,10,400,50);
        //diese Listener
        button1.addActionListener(this);
        button2 = new JButton("-");
        button2.setBackground(Color.yellow);
        button2.setBounds(40,70,400,50);
        //diese Listener
        button2.addActionListener(this);
        /*
        button.addActionListener(e -> {
            count++;
            System.out.println("Du hast diesen Knopf " + count + " mal gedrückt");
            button.setText("Du hast diesen Knopf " + count + " mal gedrückt");
        }); auch solche funktionale Implementierung
        */
        add(button1);
        add(button2);

        label = new JLabel("Du hast diesen Knopf noch nicht gedrückt");
        label.setBounds(100,130,400,30);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        add(label);

        slider = new JSlider(0,100,50);
        slider.setBounds(100,160,400,30);
        slider.addChangeListener(this);
        add(slider);

        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setBounds(40,200,400,50);
        add(panel);

        setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }



    public static void main(String[] args) {
        new Frame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var b = ((JButton) e.getSource()); //um zu interagieren
        count += b.equals(button1) ? 1 : -1;
        //System.out.println("Du hast diesen Knopf " + count + " mal gedrückt");
        label.setText("Du hast diesen Knopf " + count + " mal gedrückt");
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println(slider.getValue());
    }

}
