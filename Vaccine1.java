import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Myframe extends JFrame implements ItemListener, ActionListener {
    JLabel vaccinedetails, name, dose, vaccine, fName, fdose1, fVaccine;
    JCheckBox firstDose, secondDose;
    JRadioButton coviShield, coVaxin, sputnik_v;
    JTextField iName;
    JPanel jp;

    Myframe() {
        vaccinedetails = new JLabel("Vaccination Detail");
        name = new JLabel("Name");
        dose = new JLabel("Dose");
        vaccine = new JLabel("Vaccine");
        fName = new JLabel("Name_____________________");
        fdose1 = new JLabel("1st Dose__NO");
        fVaccine = new JLabel("Vaccine_______________");

        firstDose = new JCheckBox("1st Dose");
        secondDose = new JCheckBox("2nd Dose");

        coviShield = new JRadioButton("Covishield");
        coVaxin = new JRadioButton("Covaxin");
        sputnik_v = new JRadioButton("Sputnik V");

        iName = new JTextField(20);

        ButtonGroup vtype = new ButtonGroup();
        vtype.add(coVaxin);
        vtype.add(coviShield);
        vtype.add(sputnik_v);

        jp = new JPanel();
        jp.add(fName);
        jp.add(fdose1);
        jp.add(fVaccine);
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));

        setLayout(null);

        add(vaccinedetails);
        add(name);
        add(iName);
        add(dose);
        add(firstDose);
        add(secondDose);
        add(vaccine);
        add(coviShield);
        add(coVaxin);
        add(sputnik_v);
        add(jp);

        vaccinedetails.setBounds(300, 20, 200, 20);
        iName.setBounds(300, 50, 200, 20);
        name.setBounds(200, 50, 200, 20);
        dose.setBounds(100, 100, 200, 20);
        firstDose.setBounds(100, 130, 200, 20);
        secondDose.setBounds(100, 160, 200, 20);
        vaccine.setBounds(360, 100, 200, 20);
        coviShield.setBounds(360, 130, 200, 20);
        coVaxin.setBounds(360, 160, 200, 20);
        sputnik_v.setBounds(360, 190, 200, 20);

        jp.setBounds(0, 350, 720, 100);

        iName.addActionListener(this);
        firstDose.addItemListener(this);
        coVaxin.addItemListener(this);
        coviShield.addItemListener(this);
        sputnik_v.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(firstDose)) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                fdose1.setText("1st Dose__YES ");
            } else {
                fdose1.setText("1st Dose__NO ");
            }
        } else {
            String selected = ((JRadioButton) e.getSource()).getText();
            fVaccine.setText("Vaccine__" + selected + " ");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(iName)) {
            fName.setText("Name_" + iName.getText() + " ");
        }
    }
}

public class Vaccine1 {
    public static void main(String[] args) {
        Myframe frame = new Myframe();
        frame.setSize(720, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
