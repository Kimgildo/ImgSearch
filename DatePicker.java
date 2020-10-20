package ex1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class DatePicker {
	static String str1 = "0000-00-00";
	static String str2 = "0000-00-00";
	static String str3 = "";
	static Frame frame1;
	static Frame frame2;
	static Frame main_frame;
	static Date day1 = null;
	static Date day2 = null;
	
	static ImgSizeSearch is = new ImgSizeSearch();
	
	public static void main(String[] args) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		main_frame = new Frame("날짜 선택");
		main_frame.setBounds(500, 140, 400, 160);
		
		Panel main_pCenter = new Panel();
		
		Button but1 = new Button("처음 날짜 선택");
		Button but2 = new Button("나중 날짜 선택");
		Button butOK = new Button("확인");
		
		Panel pCenter = new Panel();
		
		Label label_fd = new Label("처음 날짜 : "+ str1);
		label_fd.setBackground(Color.WHITE);
		
		Label label_ld = new Label("나중 날짜 : "+ str2);
		label_ld.setBackground(Color.WHITE);
		
		pCenter.add(label_fd,BorderLayout.EAST);
		pCenter.add(label_ld,BorderLayout.WEST);
				
		main_frame.add(pCenter,BorderLayout.CENTER);
		main_frame.add(but1,BorderLayout.LINE_START);
		main_frame.add(but2, BorderLayout.LINE_END);
		main_frame.add(butOK, BorderLayout.SOUTH);
		
		main_frame.setVisible(true);
		
		but1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main_frame.dispose();
				UtilDateModel model = new UtilDateModel();

				JDatePanelImpl datePanel = new JDatePanelImpl(model);

				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
				datePicker.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							day1 = dateFormat.parse(model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());
							System.out.println(day1);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//System.out.println(model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());
						str1 = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
						frame1.dispose();
						main(args);
					}
				});
				
				frame1 = new Frame("날짜 선택");
				frame1.setBounds(500, 140, 300, 100);
				
				frame1.setResizable(false);
				
				Label label = new Label("오른쪽 ... 클릭 후 날짜 선택! ");
				label.setBackground(Color.white);
				
				frame1.add(datePicker,BorderLayout.NORTH);
				frame1.add(label,BorderLayout.CENTER);
				frame1.setVisible(true);
				
				//종료
				frame1.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
		
		but2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main_frame.dispose();
				UtilDateModel model = new UtilDateModel();

				JDatePanelImpl datePanel = new JDatePanelImpl(model);

				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
				datePicker.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							day2 = dateFormat.parse(model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());
							System.out.println(day2);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//System.out.println(model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());
						str2 = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
						frame2.dispose();
						main(args);
					}
				});
				
				frame2 = new Frame("날짜 선택");
				frame2.setBounds(500, 140, 300, 100);
				
				frame2.setResizable(false);
				
				Label label = new Label("오른쪽 ... 클릭 후 날짜 선택! ");
				label.setBackground(Color.white);
				
				frame2.add(datePicker,BorderLayout.NORTH);
				frame2.add(label,BorderLayout.CENTER);
				frame2.setVisible(true);
				
				//종료
				frame2.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
		
		butOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImgSizeSearch.f.dispose();
				System.out.println(str1 + ":::"+str2);
				str3 = str1+str2;
				main_frame.dispose();
				is.main(null);
			}
		});
		main_frame.setResizable(false);
		//종료
		main_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
}
