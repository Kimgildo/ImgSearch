package ex1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Camera {
	
	public static Label ld_ct;
	public static Label ld_bot;
	public static Label ld_sou;

	public static Frame f;
	public static Frame frameL;
	public static String strDir = "선택 안됨";//"C:\\Users\\48542\\Desktop\\img\\";
	public static String strDir2 = "";
	static int cnt = 0; //기준 미달 파일 개수
	static int allCnt = 0; //총 파일 개수
	static int dCount = 0;  //디렉토리의 총개수
	

	//리스트 저장 및 카운팅
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> sizeList = new ArrayList<>();
	static ArrayList<String> dateList = new ArrayList<String>();
	
	
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
		public static void ListNumFile( String strDir ) {
			String dirPath = "C:\\CameraFolder";
			
			File dirFile = new File(dirPath); 
			if (!dirFile.exists()) { //폴더 없으면 폴더 생성 
				dirFile.mkdirs(); 
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
										System.out.println("디지털카메라");
									}else
										System.out.println("휴대전화");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();

								}catch (java.lang.NullPointerException e1) {
									e1.printStackTrace();
									

								}

							}else {
								System.out.println("다른 확장자 존재 : 오류");
								
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
		
	public static void main(String[] args) {
		
	
	}
}
