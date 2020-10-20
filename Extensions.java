package ex1;

import java.io.File;
import java.io.FilenameFilter;

public class Extensions {
	
	// array of supported extensions (use a List if you prefer)
		final String[] EXTENSIONS = new String[]{
		"ppt","pptx","xlsx", "hwp", "docx", "PNG", "GIF", "gif", "png", "bmp", "jpg", "JPG", "jpeg", "JPEG", "BMP", "pdf", "PDF", "PSD", "psd", "JFIF", "jfif" // and other formats you need"
		};
		// filter to identify images based on their extensions
		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

			public boolean accept(final File dir, final String name) {
				for (final String ext : EXTENSIONS) {
					if (name.endsWith("." + ext)) {
						return (true);
					}
				}
				return (false);
			}
		};
	
}