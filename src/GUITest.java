
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import de.jaret.util.ui.datechooser.DateChooser;
import datechooser.beans.DateChooserCombo;



public class GUITest extends JFrame {
	
	public GUITest(){
		
		JOutlookBar accordian = new JOutlookBar(); 
		//DateChooser calendar2 = new DateChooser(null, DateChooser.KEEP_AND_MARK);
		JCalendar calendar = new JCalendar();
		DateChooserCombo combo = new DateChooserCombo();
		combo.setCalendarPreferredSize(new Dimension(350,250));
		accordian.addBar("AA",combo);
		JPanel p1 = new JPanel();
		JScrollPane sp1 = new JScrollPane(p1);
		p1.add(new JLabel("BBBa"),BorderLayout.CENTER);
		accordian.addBar("BB",sp1);
		
		accordian.setBounds(5,5,170,400);
		
		//sp1.setBounds(5,5,150,300);
		
		getContentPane().setLayout(null);
		getContentPane().add(accordian);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200,500);
		setVisible(true);
	}

	public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        }
        
		new GUITest();
	}

}
