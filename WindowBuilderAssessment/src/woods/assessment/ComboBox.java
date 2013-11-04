package woods.assessment;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComboBox implements MouseListener, ListSelectionListener{

	private JFrame frame;
	private JTextField textField;
	private JLabel myJLabel;
	private DefaultListModel<String> listModel;
	private JList<String> list, list_2, list_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboBox window = new ComboBox();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ComboBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String [] content = {"More content", "Some more content", "More and more content", "More and more content",
				"More and more content", "More and more content", "More and more content"};
		listModel = new DefaultListModel();
		setContents(content);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"}));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				textField.setText(arg0.getItem().toString());
				myJLabel.setText("Combo Box Event");
			}
		});
		
		comboBox.setBounds(0, 0, 105, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(103, 0, 200, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(comboBox.getItemAt(0).toString());
		
		JScrollPane myPane = new JScrollPane();
		list = new JList(listModel);
		list.addMouseListener(this);		
		myPane.setViewportView(list);
		myPane.setBounds(0, 59, 217, 103);
		frame.getContentPane().add(myPane);
		
		JScrollPane myPane_2 = new JScrollPane();
		list_2 = new JList(listModel);
		list_2.addMouseListener(this);
		myPane_2.setViewportView(list_2);
		myPane_2.setBounds(215, 59, 219, 103);
		frame.getContentPane().add(myPane_2);
		
		JScrollPane myPane_3 = new JScrollPane();
		list_3 = new JList(listModel);
		list_3.addMouseListener(this);
		myPane_3.setViewportView(list_3);
		myPane_3.setBounds(0, 162, 434, 68);
		frame.getContentPane().add(myPane_3);
		
		myJLabel = new JLabel("New label");
		myJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myJLabel.setBounds(131, 237, 183, 14);
		frame.getContentPane().add(myJLabel);
	}
	
	public void setContents(String[] myContent){
		for(int i = 0; i < myContent.length; i++)
			listModel.addElement(myContent[i]);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			if (e.getSource().equals(list)){
				myJLabel.setText("Top Left Panel Event");
				list.addListSelectionListener(this);
			}else if (e.getSource().equals(list_2)){
				myJLabel.setText("Top Right Panel Event");
				list_2.addListSelectionListener(this);
			}else{
				myJLabel.setText("Bottom Panel Event");
				list_3.addListSelectionListener(this);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(!arg0.getValueIsAdjusting()){
			if(arg0.getSource().equals(list))
				textField.setText(list.getSelectedValue().toString());
			else if(arg0.getSource().equals(list_2))
				textField.setText(list_2.getSelectedValue().toString());
			else
				textField.setText(list_3.getSelectedValue().toString());
		}
			
	}
	
}
