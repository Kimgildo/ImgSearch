package ex1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImgSizeSearch extends JFrame{

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	static Date day = null;
	
	static String dayStr = "";
	
	public static boolean tf = true;
	public static Directory direc;
	// File representing the folder that you select using a FileChooser
	public static Label ld_ct;
	public static Label ld_bot;
	public static Label ld_sou;

	public static Frame f;
	public static Frame frameL;
	public static String strDir = "선택 안됨";//"C:\\Users\\48542\\Desktop\\img\\";
	public static String strDir2 = "";
	//public static String newDir = strDir.replace("\\","\\\\");
	//public static File dir = new File(strDir2);

	public static Button dirBtn;
	public static Button dateBtn;

	static int Img_width = 0;
	static int Img_height = 0;

	//리스트 저장 및 카운팅
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> sizeList = new ArrayList<>();
	static ArrayList<String> dateList = new ArrayList<String>();
	static ArrayList<String> numList = new ArrayList<String>();
	
	static int cnt = 0; //기준 미달 파일 개수
	static int allCnt = 0; //총 파일 개수
	static int dCount = 0;  //디렉토리의 총개수

	// array of supported extensions (use a List if you prefer)
	static final String[] EXTENSIONS = new String[]{
			"ppt","pptx","xlsx", "hwp", "docx", "PNG", "GIF", "gif", "png", "bmp", "jpg", "JPG", "jpeg", "JPEG", "BMP", "pdf", "PDF", "PSD", "psd", "JFIF", "jfif" // and other formats you need"
	};
	// filter to identify images based on their extensions
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith("." + ext)) {
					return (true);
				}
			}
			return (false);
		}
	};


	//재귀함수 호출(Size)
	public static void ListFile( String strDir ) {
		String dirPath = "C:\\SizeFolder";
		
		File dirFile = new File(dirPath); 
		if (!dirFile.exists()) { //폴더 없으면 폴더 생성 
			dirFile.mkdirs(); 
		}
		
		File dir = new File(strDir);
		File path = new File( strDir ); 
		File[] fList = path.listFiles();
		String s = "";
		if (dir.isDirectory()) {
			dCount++;
			try {

				for( int i = 0; i < fList.length; i++ ) { 
					if( fList[i].isFile() ) {
						if(IMAGE_FILTER.accept(dir, fList[i].getName())) {
							BufferedImage bimg = null;
							try {
								bimg = ImageIO.read(fList[i]);
								allCnt++;
								System.out.print(allCnt+" : ");
								System.out.println( fList[i].getPath() );
								//height이 안될때
								if(bimg.getWidth() >= Img_width && bimg.getHeight() < Img_height) {
									//System.out.println("-----------------------");
									//System.out.println("image: " + f.getName());
									//getStr().substring(getStr().lastIndexOf("\\"));
									File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));

									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									s = Integer.toString(bimg.getWidth()) + " X " + Integer.toString(bimg.getHeight());
									sizeList.add(s);
									continue;
								}else if(bimg.getWidth() < Img_height && bimg.getHeight() >= Img_width) {
									//width이 안될때
									File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));
									
									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									s = Integer.toString(bimg.getWidth()) + " X " + Integer.toString(bimg.getHeight());
									sizeList.add(s);
									continue;
								}else if(bimg.getWidth() <Img_width && bimg.getHeight() < Img_height) {
									//height, width이 안될때
									File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));
									
									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									s = Integer.toString(bimg.getWidth()) + " X " + Integer.toString(bimg.getHeight());
									sizeList.add(s);
									continue;
								}else if(bimg.getWidth() <Img_height && bimg.getHeight() < Img_width) {
									//height, width이 안될때
									File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));
									
									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									s = Integer.toString(bimg.getWidth()) + " X " + Integer.toString(bimg.getHeight());
									sizeList.add(s);
									continue;
								}else if(bimg.getWidth() < Img_width && bimg.getHeight() < Img_width && bimg.getHeight() >= Img_height) {
									File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));
									
									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									s = Integer.toString(bimg.getWidth()) + " X " + Integer.toString(bimg.getHeight());
									sizeList.add(s);
									continue;
								}else if(bimg.getWidth() >= Img_height && bimg.getWidth() < Img_width && bimg.getHeight() < Img_width) {
									File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));
									
									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									s = Integer.toString(bimg.getWidth()) + " X " + Integer.toString(bimg.getHeight());
									sizeList.add(s);
								}


							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}catch (java.lang.NullPointerException e1) {
								e1.printStackTrace();
								ld_ct.setText("경로에 이미지가 아닌 파일이 포함되어 있습니다");
								ld_bot.setVisible(true);
								ld_sou.setVisible(true);

							}finally {
								frameL.addWindowListener(new WindowAdapter() {
									@Override
									public void windowClosing(WindowEvent e) {
										cnt = 0;
										allCnt = 0;
										dCount = 0;
										frameL.dispose();
									}
								});
							}

						}else {
							System.out.println("다른 확장자 존재 : 오류");
							ld_ct.setText("경로에 이미지가 아닌 파일이 포함되어 있습니다");
							ld_bot.setVisible(true);
							ld_sou.setVisible(true);

							frameL.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									cnt = 0;
									allCnt = 0;
									dCount = 0;
									frameL.dispose();
								}
							});
						}

					}else if( fList[i].isDirectory() ) { 
						ListFile( fList[i].getPath() ); // 재귀함수 호출 
					} 
				}
			}catch (java.lang.NullPointerException e2){
				System.out.println("여기로 다른 확장자 존재 : 오류");
				ld_ct.setText("경로에 이미지가 아닌 파일이 포함되어 있습니다");
				ld_bot.setVisible(true);
				ld_sou.setVisible(true);

				frameL.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						cnt = 0;
						allCnt = 0;
						dCount = 0;
						frameL.dispose();
					}
				});
			}

		}
	}
	//재귀함수 호출(Num)
			public static void ListNumFile( String strDir ) {
				
				String dirPath1 = "C:\\CameraFolder";
				String dirPath2 = "C:\\PhoneFolder";
				
				
				File dirFile1 = new File(dirPath1); 
				if (!dirFile1.exists()) { //폴더 없으면 폴더 생성 
					dirFile1.mkdirs(); 
				}
				
				File dirFile2 = new File(dirPath2); 
				if (!dirFile2.exists()) { //폴더 없으면 폴더 생성 
					dirFile2.mkdirs(); 
				}
				
				File dir = new File(strDir);
				File path = new File( strDir ); 
				File[] fList = path.listFiles();
				String s = "";
				if (dir.isDirectory()) {
					try {

						for( int i = 0; i < fList.length; i++ ) { 
							if( fList[i].isFile() ) {
								if(IMAGE_FILTER.accept(dir, fList[i].getName())) {
									BufferedImage bimg = null;
									try {
										bimg = ImageIO.read(fList[i]);
										allCnt++;
										//System.out.print(allCnt+"_");
										//System.out.println( fList[i].getPath() );
										//height이 안될때
										//System.out.println(fList[i].getName().substring(13, 15));
										if(fList[i].getName().substring(13, 15).equals("01") || 
												fList[i].getName().substring(13, 15).equals("02") || 
												fList[i].getName().substring(13, 15).equals("03")) {
											File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
											file.renameTo(new File(dirPath1+"/"+fList[i].getName()));
											list.add("디지털카메라");
											System.out.println("디지털카메라");
										}else {
											File file =new File(fList[i].getPath()); // 옮기 고자 하는 경로로 파일 이동 
											file.renameTo(new File(dirPath2+"/"+fList[i].getName()));
											list.add("휴대전화");
											System.out.println("휴대전화");
										}
										} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();

									}catch (java.lang.NullPointerException e1) {
										e1.printStackTrace();
										

									}

								}else {
									
								}

							}else if( fList[i].isDirectory() ) { 
								ListNumFile( fList[i].getPath() ); // 재귀함수 호출 
							} 
						}
					}catch (java.lang.NullPointerException e2){
						System.out.println("여기로 다른 확장자 존재 : 오류");
						
					}

				}
			}
			
	//재귀함수 호출(Date)
	public static void ListFileDate( String strDir ) { 
		
		String dirPath = "C:\\DateFolder";
		
		File dirFile = new File(dirPath); 
		if (!dirFile.exists()) { //폴더 없으면 폴더 생성 
			dirFile.mkdirs(); 
		}
		
		File dir = new File(strDir);
		File path = new File( strDir ); 
		File[] fList = path.listFiles();
		String s = "";
		if (dir.isDirectory()) {
			dCount++;
			try {

				for( int i = 0; i < fList.length; i++ ) { 
					if( fList[i].isFile() ) {
						if(IMAGE_FILTER.accept(dir, fList[i].getName())) {
							BufferedImage bimg = null;
							try {
								bimg = ImageIO.read(fList[i]);
								allCnt++;
								System.out.print(allCnt+" : ");
								System.out.println( fList[i].getPath() );
								//System.out.println( fList[i].getPath() );
								//height이 안될때
								File file = new File(fList[i].getPath());
								try {
									Metadata metadata = ImageMetadataReader.readMetadata(file);
									printImageTags(metadata);
									
									if(!tf) {
						
										file.renameTo(new File(dirPath+"/"+fList[i].getName()));
										
										list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
										System.out.println(fList[i].getName());
										dateList.add("직접 찾아야 되요ㅠㅠ");
										continue;
									}
								} catch (ImageProcessingException e) {
									//System.err.println("error 1a: " + e);
									//list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									System.out.println(fList[i].getName());
									//dateList.add("직접 찾아야 되요ㅠㅠ");
								} catch (IOException e) {
									//System.err.println("error 1b: " + e);
								}
								
								if(DatePicker.day1.compareTo( day ) < 0 && DatePicker.day2.compareTo( day ) > 0 ) {
									
								}else {
									file.renameTo(new File(dirPath+"/"+fList[i].getName()));
									list.add(fList[i].getPath().substring(fList[i].getPath().lastIndexOf(strDir2)));
									//list.add(fList[i].getPath());
									dateList.add(dayStr);
									continue;
								}
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}catch (java.lang.NullPointerException e1) {
								e1.printStackTrace();
								ld_ct.setText("경로에 이미지가 아닌 파일이 포함되어 있습니다");
								ld_bot.setVisible(true);
								ld_sou.setVisible(true);

							}finally {
								frameL.addWindowListener(new WindowAdapter() {
									@Override
									public void windowClosing(WindowEvent e) {
										cnt = 0;
										allCnt = 0;
										dCount = 0;
										frameL.dispose();
									}
								});
							}

						}else {
							System.out.println("다른 확장자 존재 : 오류");
							ld_ct.setText("경로에 이미지가 아닌 파일이 포함되어 있습니다");
							ld_bot.setVisible(true);
							ld_sou.setVisible(true);

							frameL.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									cnt = 0;
									allCnt = 0;
									dCount = 0;
									frameL.dispose();
								}
							});
						}

					}else if( fList[i].isDirectory() ) { 
						ListFileDate( fList[i].getPath() ); // 재귀함수 호출 
					} 
				}
			}catch (java.lang.NullPointerException e2){
				System.out.println("여기로 다른 확장자 존재 : 오류");
				ld_ct.setText("경로에 이미지가 아닌 파일이 포함되어 있습니다");
				ld_bot.setVisible(true);
				ld_sou.setVisible(true);

				frameL.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						cnt = 0;
						allCnt = 0;
						dCount = 0;
						frameL.dispose();
					}
				});
			}

		}
	}


	private static void printImageTags(Metadata metadata)
	{
		// iterate over the exif data and print to System.out
		label : for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()){
					if(tag.getTagName().equals("Date/Time Original")) {            		
						System.out.print(tag.getTagName());
						System.out.print(" - ");
						System.out.println(tag.getDescription());
						dayStr = tag.getDescription();
						if(dayStr.equals("")) {
							tf=false;
							break label;
						}else {
							
							String getDesc = tag.getDescription();
							getDesc = getDesc.substring(0, 10);
							getDesc = getDesc.replace(':', '-');
							try {
								day = dateFormat.parse(getDesc);
								//System.out.println(day);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								tf=false;
								break label;
							}
						}
						//날짜 비교
						tf=true;
						break label;
				}
			}
	
		for (String error : directory.getErrors()){
			System.err.println("ERROR: " + error);
			}
		tf=false;
		}
		
	}

	public static void main(String[] args) {

		f = new Frame("이미지 숫자 분석");
		f.setBounds(500, 140, 440, 550);

		Label label = new Label("Img SizeNum Check");
		Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 33);
		label.setBounds(60, 100, 320, 60);
		label.setFont(font);

		Label label2 = new Label("이미지 확장자 : JPG, JPEG, PNG, GIF, BMP, PSD, JFIF");
		Font font_label2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		label2.setBounds(28, 160, 500, 60);
		label2.setFont(font_label2);

		Label label3 = new Label("");
		Font font_label3 = new Font(Font.SANS_SERIF, Font.BOLD, 13);
		label3.setBounds(30, 330, 500, 60);
		label3.setFont(font_label3);
		
		Label label4 = new Label("폴더 내에 있는 이미지들 이름 순으로 분석합니다");
		Font font_label4 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		label4.setBounds(10, 390, 500, 30);
		label4.setFont(font_label4);
		
		Label label5 = new Label("01~03 : 디지털카메라/04~06 : 휴대전화");
		Font font_label5 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		label5.setBounds(10, 420, 500, 30);
		label5.setFont(font_label5);
		
		Label label6 = new Label("<-오류시 초기화 버튼 클릭");
		Font font_label6 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		label6.setBounds(260, 500, 200, 30);
		label6.setFont(font_label6);
		
		Button sizeBtn = new Button("사이즈 입력");
		dirBtn = new Button("폴더 선택");
		dateBtn = new Button("날짜 선택");
		Button listBtn = new Button("이미지 체크 시작");
		Button resetBtn = new Button("초기화");
		
		sizeBtn.setEnabled(false);
		dateBtn.setEnabled(false);
		listBtn.setEnabled(false);
		
		dirBtn.setBounds(20, 230, 90, 100);
		sizeBtn.setBounds(120, 230, 90, 100);
		dateBtn.setBounds(220, 230, 90, 100);
		listBtn.setBounds(320, 230, 90, 100);
		resetBtn.setBounds(190, 500, 60, 30);

		Panel pCenter = new Panel();

		Panel pSouth = new Panel();
		Font font2 = new Font("돋움", Font.BOLD, 15);

		Label l1 = new Label("사이즈 : "+Img_width + " X " + Img_height);
		l1.setBackground(Color.WHITE);
		l1.setFont(font2);

		Label l2 = new Label("폴더  : "+strDir);
		l2.setBackground(Color.WHITE);
		Font font23 = new Font("돋움", Font.BOLD, 12);
		l2.setFont(font23);
		l2.setBounds(10, 450, 500, 50);
		
		Label l3 = new Label("기간 : "+DatePicker.str1+" ~ "+DatePicker.str2);
		l3.setBackground(Color.white);
		l3.setFont(font2);
		l3.setBounds(100, 60, 500, 60);

		pCenter.add(l1);

		f.add(label);
		f.add(sizeBtn);
		f.add(dirBtn);
		f.add(listBtn);
		f.add(dateBtn);
		f.add(label2);
		f.add(label3);
		f.add(label4);
		f.add(label5);
		f.add(label6);
		f.add(resetBtn);
		f.add(l2);
		f.add(l3);

		f.add(pCenter, BorderLayout.CENTER);
		
		
		f.setResizable(false);
		f.setVisible(true);

		//종료
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//초기화 버튼 클릭 시
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				strDir = "선택 안됨";
				Img_height = 0;
				Img_width = 0;
				DatePicker.str1 = "0000-00-00";
				DatePicker.str2 = "0000-00-00";
				f.dispose();
				main(args);
			}
		});
		//날짜 선택 버튼 클릭 시
		dateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				DatePicker dp = new DatePicker();
				dp.main(args);
			}
		});
		//폴더 선택 버튼 클릭 시
		dirBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new ExSelectDerectoriesChoisOnly();
			}
		});

		//사이즈 선택 버튼 클릭 시
		sizeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame sizef = new Frame("사이즈");
				sizef.setBounds(600, 400, 400, 200);

				Panel pNorths = new Panel();
				JLabel q1s = new JLabel("사이즈 입력(width, height)");
				Font fonts = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
				q1s.setFont(fonts);

				pNorths.add(q1s);

				// 중앙
				Panel pMiddles = new Panel();
				TextField sizeTf = new TextField(20);
				sizeTf.setText("Width");
				sizeTf.setBackground(Color.WHITE);
				sizeTf.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						sizeTf.setText("");
					}
				});
				TextField sizeTf2 = new TextField(20);
				sizeTf2.setText("Height");
				sizeTf2.setBackground(Color.WHITE);
				sizeTf2.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						sizeTf2.setText("");
					}
				});


				pMiddles.add(sizeTf);
				pMiddles.add(sizeTf2);

				// 하단
				Panel pSouths = new Panel();
				Button entBtn = new Button("입력");
				entBtn.setEnabled(false); //입력버튼 비활성화
				pSouths.add(entBtn);
				// 텍스트 필드에 값이 한글자라도 들어왔을때 입력 버튼 활성화
				sizeTf2.addTextListener(new TextListener() {

					@Override
					public void textValueChanged(TextEvent e) {
						if(sizeTf.getText().trim().equals("")) {
							entBtn.setEnabled(false);
						}else {
							entBtn.setEnabled(true);
						}
					}
				});

				// 클릭으로 입력버튼 눌렸을 떄
				entBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e1) {
						if(Integer.parseInt(sizeTf.getText())>=Integer.parseInt(sizeTf2.getText())) {								
							Img_width = Integer.parseInt(sizeTf.getText());
							Img_height = Integer.parseInt(sizeTf2.getText());
						}else {
							Img_width = Integer.parseInt(sizeTf2.getText());
							Img_height = Integer.parseInt(sizeTf.getText());
						}
						sizef.dispose();
						f.dispose();
						main(args);
					}
				});

				// 엔터로 입력버튼 눌렀을 떄
				entBtn.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							if(Integer.parseInt(sizeTf.getText())>=Integer.parseInt(sizeTf2.getText())) {								
								Img_width = Integer.parseInt(sizeTf.getText());
								Img_height = Integer.parseInt(sizeTf2.getText());
							}else {
								Img_width = Integer.parseInt(sizeTf2.getText());
								Img_height = Integer.parseInt(sizeTf.getText());
							}
							sizef.dispose();
							f.dispose();
							main(args);
						}
					}
				});



				sizef.add(pNorths, BorderLayout.NORTH);
				sizef.add(pMiddles, BorderLayout.CENTER);
				sizef.add(pSouths, BorderLayout.SOUTH);

				sizef.setVisible(true);
				sizef.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						sizef.dispose();
					}
				});
			}
		});

		//버튼 눌러 값 입력시 버튼 색 변경
		if(!DatePicker.str1.equals("0000-00-00") && !DatePicker.str2.equals("0000-00-00")) {
			dateBtn.setBackground(Color.LIGHT_GRAY);
		}

		if(Img_height != 0 && Img_width != 0) {
			sizeBtn.setBackground(Color.LIGHT_GRAY);
		}

		if(!strDir.equals("선택 안됨")) {
			dirBtn.setBackground(Color.LIGHT_GRAY);
		}

		//리스트보기 버튼 활성화
		if(!strDir.equals("선택 안됨")) {
			listBtn.setEnabled(true);
		}

		//사진 보기 클릭시
		listBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listBtn.setBackground(Color.gray);

				if(Img_height != 0 && Img_width != 0 && DatePicker.str1.equals("0000-00-00")
						&& DatePicker.str2.equals("0000-00-00")) {

					Font font3 = new Font("돋움", Font.BOLD, 15);
					Font font5 = new Font("돋움", Font.BOLD, 22);

					System.out.println("--------------------");
					System.out.println("리스트 주소 : "+strDir);

					frameL = new Frame("기준 미달 이미지");
					System.out.println("--------------------");
					frameL.setBounds(600, 40, 900, 600);

					ld_bot = new Label("확장자 : JPG, JPEG, PNG, GIF, BMP, PSD, JFIF 만 가능", Label.CENTER);
					Font ft_bot = new Font("돋움", Font.BOLD, 20);
					ld_bot.setBackground(Color.LIGHT_GRAY);
					ld_bot.setFont(ft_bot);
					frameL.add(ld_bot, BorderLayout.SOUTH);
					ld_bot.setVisible(false);

					ld_sou = new Label("이미지만 검색하여 분석중입니다. 잠시만 기다려 주세요");
					Font font_ldsou = new Font(Font.SANS_SERIF, Font.BOLD, 25);
					ld_sou.setBounds(130, 360, 700, 60);
					ld_sou.setBackground(Color.LIGHT_GRAY);
					ld_sou.setFont(font_ldsou);
					ld_sou.setVisible(false);
					frameL.add(ld_sou);

					ld_ct = new Label("잠시만 기다려 주세요", Label.CENTER);
					Font ft_ld = new Font("돋움", Font.BOLD, 36);
					ld_ct.setBackground(Color.LIGHT_GRAY);
					ld_ct.setFont(ft_ld);
					frameL.add(ld_ct, BorderLayout.CENTER);

					frameL.setVisible(true);


					ListFile(strDir);

					ld_ct.setVisible(false);
					ld_bot.setVisible(false);
					ld_sou.setVisible(false);

					//상단
					Panel pNorth = new Panel();
					Label q1 = new Label("하위 폴더 갯수 : "+(dCount-1));
					q1.setBackground(Color.WHITE);
					q1.setFont(font3);

					Label q2 = new Label("총 파일 갯수 : "+allCnt);
					q2.setBackground(Color.WHITE);
					q2.setFont(font3);

					Label q3 = new Label("기준 미달 이미지 갯수 : "+list.size());
					q3.setBackground(Color.WHITE);
					q3.setFont(font3);

					Label q4 = new Label("::: 폴더 이름\\하위  폴더 이름\\이미지 제목 ------> Width X Height :::");
					q4.setBackground(Color.WHITE);
					q4.setFont(font5);

					// 중단 (키 값)
					Panel p1 = new Panel();
					p1.setLayout(new GridLayout());

					TextArea Ta = new TextArea();
					JScrollPane spkey = new JScrollPane(Ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					Ta.setBackground(Color.WHITE);
					Font font4 = new Font("돋음", Font.BOLD, 20);
					Ta.setFont(font4);
					Ta.setEditable(false); //ta를 직접 수정 불가
					p1.add(spkey);

					pNorth.add(q1);
					pNorth.add(q2);
					pNorth.add(q3);
					frameL.add(pNorth, BorderLayout.NORTH);
					frameL.add(q4, BorderLayout.SOUTH);
					frameL.add(p1, BorderLayout.CENTER);

					frameL.setVisible(true);

					System.out.println("폴더 개수 : "+ (dCount-1));
					System.out.println("----------------");
					System.out.println("총 파일 갯수 : "+ allCnt);
					System.out.println("----------------");
					System.out.println("기준 미달 이미지 갯수 : " + list.size());
					System.out.println("--------------------");
					System.out.println(":::이미지 목록:::");

					String TaStr = "";
					for(int i=0; i<list.size(); i++) {
						TaStr += (i+1) +" : "+list.get(i) + " ------> "+sizeList.get(i) + "\n"+ "------------------------------------------------------------------------------------\n";
						Ta.setText(TaStr);
						System.out.println((i+1)+" : "+list.get(i)+" ------> "+sizeList.get(i));
					}
					//종료
					frameL.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

				}else if(Img_height == 0 && Img_width == 0 && !DatePicker.str1.equals("0000-00-00")
						&& !DatePicker.str2.equals("0000-00-00")) {
					Font font3 = new Font("돋움", Font.BOLD, 15);
					Font font5 = new Font("돋움", Font.BOLD, 22);

					System.out.println("--------------------");
					System.out.println("리스트 주소 : "+strDir);

					frameL = new Frame("기준 미달 이미지");
					System.out.println("--------------------");
					frameL.setBounds(600, 40, 900, 600);

					ld_bot = new Label("확장자 : JPG, JPEG, PNG, GIF, BMP, PSD, JFIF 만 가능", Label.CENTER);
					Font ft_bot = new Font("돋움", Font.BOLD, 20);
					ld_bot.setBackground(Color.LIGHT_GRAY);
					ld_bot.setFont(ft_bot);
					frameL.add(ld_bot, BorderLayout.SOUTH);
					ld_bot.setVisible(false);

					ld_sou = new Label("이미지만 검색하여 분석중입니다. 잠시만 기다려 주세요");
					Font font_ldsou = new Font(Font.SANS_SERIF, Font.BOLD, 25);
					ld_sou.setBounds(130, 360, 700, 60);
					ld_sou.setBackground(Color.LIGHT_GRAY);
					ld_sou.setFont(font_ldsou);
					ld_sou.setVisible(false);
					frameL.add(ld_sou);

					ld_ct = new Label("잠시만 기다려 주세요", Label.CENTER);
					Font ft_ld = new Font("돋움", Font.BOLD, 36);
					ld_ct.setBackground(Color.LIGHT_GRAY);
					ld_ct.setFont(ft_ld);
					frameL.add(ld_ct, BorderLayout.CENTER);

					frameL.setVisible(true);


					ListFileDate(strDir);

					ld_ct.setVisible(false);
					ld_bot.setVisible(false);
					ld_sou.setVisible(false);

					//상단
					Panel pNorth = new Panel();
					Label q1 = new Label("하위 폴더 갯수 : "+(dCount-1));
					q1.setBackground(Color.WHITE);
					q1.setFont(font3);

					Label q2 = new Label("총 파일 갯수 : "+allCnt);
					q2.setBackground(Color.WHITE);
					q2.setFont(font3);

					Label q3 = new Label("기준 미달 이미지 갯수 : "+list.size());
					q3.setBackground(Color.WHITE);
					q3.setFont(font3);

					Label q4 = new Label("::: 폴더 이름\\하위  폴더 이름\\이미지 제목 ------> 찍은 날짜 :::");
					q4.setBackground(Color.WHITE);
					q4.setFont(font5);

					// 중단 (키 값)
					Panel p1 = new Panel();
					p1.setLayout(new GridLayout());

					TextArea Ta = new TextArea();
					JScrollPane spkey = new JScrollPane(Ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					Ta.setBackground(Color.WHITE);
					Font font4 = new Font("돋음", Font.BOLD, 20);
					Ta.setFont(font4);
					Ta.setEditable(false); //ta를 직접 수정 불가
					p1.add(spkey);

					pNorth.add(q1);
					pNorth.add(q2);
					pNorth.add(q3);
					frameL.add(pNorth, BorderLayout.NORTH);
					frameL.add(q4, BorderLayout.SOUTH);
					frameL.add(p1, BorderLayout.CENTER);

					frameL.setVisible(true);

					System.out.println("폴더 개수 : "+ (dCount-1));
					System.out.println("----------------");
					System.out.println("총 파일 갯수 : "+ allCnt);
					System.out.println("----------------");
					System.out.println("기준 미달 이미지 갯수 : " + list.size());
					System.out.println("--------------------");
					System.out.println(":::이미지 목록:::");

					String TaStr = "";
					for(int i=0; i<list.size(); i++) {
						TaStr += (i+1) +" : "+list.get(i) + " ------> "+dateList.get(i) + "\n"+ "------------------------------------------------------------------------------------\n";
						Ta.setText(TaStr);
						System.out.println((i+1)+" : "+list.get(i)+" ------> "+dateList.get(i));
					}
					//종료
					frameL.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
				}else if(Img_height == 0 && Img_width == 0 && DatePicker.str1.equals("0000-00-00")
						&& DatePicker.str2.equals("0000-00-00")) {
					Font font3 = new Font("돋움", Font.BOLD, 15);
					Font font5 = new Font("돋움", Font.BOLD, 22);

					System.out.println("--------------------");
					System.out.println("리스트 주소 : "+strDir);

					frameL = new Frame("기준 미달 이미지");
					System.out.println("--------------------");
					frameL.setBounds(600, 40, 900, 600);

					ld_bot = new Label("확장자 : JPG, JPEG, PNG, GIF, BMP, PSD, JFIF 만 가능", Label.CENTER);
					Font ft_bot = new Font("돋움", Font.BOLD, 20);
					ld_bot.setBackground(Color.LIGHT_GRAY);
					ld_bot.setFont(ft_bot);
					frameL.add(ld_bot, BorderLayout.SOUTH);
					ld_bot.setVisible(false);

					ld_sou = new Label("이미지만 검색하여 분석중입니다. 잠시만 기다려 주세요");
					Font font_ldsou = new Font(Font.SANS_SERIF, Font.BOLD, 25);
					ld_sou.setBounds(130, 360, 700, 60);
					ld_sou.setBackground(Color.LIGHT_GRAY);
					ld_sou.setFont(font_ldsou);
					ld_sou.setVisible(false);
					frameL.add(ld_sou);

					ld_ct = new Label("잠시만 기다려 주세요", Label.CENTER);
					Font ft_ld = new Font("돋움", Font.BOLD, 36);
					ld_ct.setBackground(Color.LIGHT_GRAY);
					ld_ct.setFont(ft_ld);
					frameL.add(ld_ct, BorderLayout.CENTER);

					frameL.setVisible(true);


					ListNumFile(strDir);

					ld_ct.setVisible(false);
					ld_bot.setVisible(false);
					ld_sou.setVisible(false);

					//상단
					Panel pNorth = new Panel();
					Label q1 = new Label("하위 폴더 갯수 : "+(dCount-1));
					q1.setBackground(Color.WHITE);
					q1.setFont(font3);

					Label q2 = new Label("총 파일 갯수 : "+allCnt);
					q2.setBackground(Color.WHITE);
					q2.setFont(font3);

					Label q3 = new Label("기준 미달 이미지 갯수 : "+list.size());
					q3.setBackground(Color.WHITE);
					q3.setFont(font3);

					Label q4 = new Label("::: 폴더 이름\\하위  폴더 이름\\이미지 제목 ------> 찍은 날짜 :::");
					q4.setBackground(Color.WHITE);
					q4.setFont(font5);

					// 중단 (키 값)
					Panel p1 = new Panel();
					p1.setLayout(new GridLayout());

					TextArea Ta = new TextArea();
					JScrollPane spkey = new JScrollPane(Ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					Ta.setBackground(Color.WHITE);
					Font font4 = new Font("돋음", Font.BOLD, 20);
					Ta.setFont(font4);
					Ta.setEditable(false); //ta를 직접 수정 불가
					p1.add(spkey);

					pNorth.add(q1);
					pNorth.add(q2);
					pNorth.add(q3);
					frameL.add(pNorth, BorderLayout.NORTH);
					frameL.add(q4, BorderLayout.SOUTH);
					frameL.add(p1, BorderLayout.CENTER);

					frameL.setVisible(true);

					System.out.println("폴더 개수 : "+ (dCount-1));
					System.out.println("----------------");
					System.out.println("총 파일 갯수 : "+ allCnt);
					System.out.println("----------------");
					System.out.println("기준 미달 이미지 갯수 : " + list.size());
					System.out.println("--------------------");
					System.out.println(":::이미지 목록:::");

					String TaStr = "";
					for(int i=0; i<list.size(); i++) {
						TaStr += list.get(i)+"\n";
						Ta.setText(TaStr);
						System.out.println(list.get(i));
					}
					//종료
					frameL.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
				}else {//사이즈, 날짜 두개다 입력 시
					listBtn.setEnabled(false);
				}
			}
		});

	}
}