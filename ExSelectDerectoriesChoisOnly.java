package ex1;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
 
@SuppressWarnings("serial")

public class ExSelectDerectoriesChoisOnly extends JFrame {
	
	ImgSizeSearch is = new ImgSizeSearch();
	Camera cm = new Camera();
	
	private String str = "";
	
    public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public ExSelectDerectoriesChoisOnly() {
    	
    	Container container = getContentPane();
 
        JLabel label = new JLabel("폴더 선택 : ");
        container.add(label);
        JTextField oututPathTF = new JTextField();
        oututPathTF.setEnabled(false);
        container.add(oututPathTF);
        JButton btn = new JButton("선택");
        JButton entBtn = new JButton("확인");
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showDialog(this, null);
            File dir = jfc.getSelectedFile();
            oututPathTF.setText(dir==null?"":dir.getPath());
        });
        container.add(btn);
        container.add(entBtn);
        
        BorderLayout outPathLayout = new BorderLayout();
        outPathLayout.addLayoutComponent(label, BorderLayout.WEST);
        outPathLayout.addLayoutComponent(oututPathTF, BorderLayout.CENTER);
        outPathLayout.addLayoutComponent(btn, BorderLayout.EAST);
        outPathLayout.addLayoutComponent(entBtn, BorderLayout.SOUTH);
  
        container.setLayout(outPathLayout);
        
        setSize(300, 120);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        entBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ss = "";
				setStr(oututPathTF.getText().replace("\\", "\\\\"));
				is.strDir = getStr();
				cm.strDir = getStr();
				ss = getStr().substring(getStr().lastIndexOf("\\"));
				ss = ss.substring(1);
				is.strDir2 = ss;
				cm.strDir2 = ss;
				System.out.println(ImgSizeSearch.strDir);
				System.out.println(ImgSizeSearch.strDir2);
				is.main(null);
				//cm.main(null);
				setVisible(false);
				
			}
		});
    }
 
    public static void main(String[] args) {
    	new ExSelectDerectoriesChoisOnly();
    }
    
}