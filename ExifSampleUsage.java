package ex1;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ExifSampleUsage {
	
	public static boolean tf = false;
	public static Directory direc;
	public static void main(String[] args) {

		new ExifSampleUsage("C:\\Users\\48542\\Desktop\\imgss\\새 폴더\\A07240002.jpg");
		System.out.println("-----------------------------");
	}

	public ExifSampleUsage(String fileName)
	{
		File file = new File(fileName);
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(file);
			printImageTags(metadata);
		} catch (ImageProcessingException e) {
			System.err.println("error 1a: " + e);
		} catch (IOException e) {
			System.err.println("error 1b: " + e);
		}
	}

	private void printImageTags(Metadata metadata)
	{
		// iterate over the exif data and print to System.out
		label : for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()){
				if(tag.getTagName().equals("Date/Time Digitized")) {            		
					System.out.print(tag.getTagName());
					System.out.print(" - ");
					System.out.println(tag.getDescription());
					tf = true;
					break label;
				}
			}


			for (String error : directory.getErrors()){
				System.err.println("ERROR: " + error);
			}
		}
	if(tf) {
		System.out.println("존재");
	}else {
		System.out.println("없음");
	}


	}
}
