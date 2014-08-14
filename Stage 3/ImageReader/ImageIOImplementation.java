//@ KIADRAGON
// ImageIOImplementation

import imagereader.IImageIO;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

public class ImageIOImplementation implements IImageIO {
	// constructor For ImageIOImplementation
	// PRIVATE STATIC INT
	private static final int HEADER_SIZE = 14, IMFORMATION_SIZE = 40, ONE = 1,
			TWO = 2, THREE = 3, FOUR = 4, FIVE = 5, SEVEN = 7, EIGHT = 8,
			TEN = 10;
	private static final int TWOFOUR = 24, ONESIX = 16, MAX_COLOR = 255,
			OXFF = 0xff;

	public Image myRead(String str) throws IOException {
		File file = new File(str);
		if (file.canExecute()) {
			throw new IllegalArgumentException("File not Found: " + str + "");
		}
		// new FILESINPUTSTREAM AND DATAINPUTSTREAM

		FileInputStream fileStream = new FileInputStream(file);
        DataInputStream dataStream = new DataInputStream(fileStream);
		// new header [] SIZE 14
		byte[] header = new byte[HEADER_SIZE];
		byte[] imformation = new byte[IMFORMATION_SIZE];
		// DATASTREAM READ
		dataStream.read(header, 0, HEADER_SIZE);
		dataStream.read(imformation, 0, IMFORMATION_SIZE);
		// HEADER DATA PROCESS
		// STORE BIWIDTH (important)
		int biWidth = byteToInt(imformation, FOUR, SEVEN);
		// STORE BIHEIGHT (important)
		int biHeight = byteToInt(imformation, EIGHT, TEN + ONE);
		// STORE BIBITCOUNT
		int biBitCount = byteToInt(imformation, TEN + FOUR, TEN + FIVE);
		// STORE BISIZEIMAGE (IMPORTANT)
		int biSizeImage = byteToInt(imformation, TEN * TWO, TEN * TWO + THREE);
		// caculate the blank each link
		int blankFillEachLine = countBlank(biBitCount, biSizeImage, biWidth,
				biHeight);
		// get color table
		// from file

		byte color[] = new byte[biSizeImage];
		dataStream.read(color, 0, biSizeImage);
		// process and get colorTable from rowData
		int[] colorTable = picturePixelProcessor(biWidth, biHeight, color,
				blankFillEachLine);
		BufferedImage image = new BufferedImage(biWidth, biHeight,
				BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, biWidth, biHeight, colorTable, 0, biWidth);

		return image;
	}

	// HANDLE WITH BYTE
	/**
	 * TRANSLATE BYTE TO DATA NEED TWO PARAMETER ONE SIZE AND ONE BYTE ARRAY
	 * WHICH IS DATA
	 **/
	public int byteToInt(byte[] rowData, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			throw new ArrayIndexOutOfBoundsException("startIndex > endIndex");
		}
		int result = 0;
		for (int i = startIndex; i < endIndex; i++) {
			result += (((int) rowData[i] & OXFF) << (EIGHT * (i - startIndex)));
		}
		return result;
	}

	// this function return how many blanks have
	public int countBlank(int biBitCount, int biSizeImage, int biWidth,
			int biHeight) {
		// if the file biBigCount is 24

		if (biBitCount == TEN * TWO + FOUR) {
			// Sytem automaticly fill with 00
			// caculate the blank number
			return ((biSizeImage / biHeight) - biWidth * THREE);
		} else {
			throw new IllegalStateException("The file isn't 24 biBitCount.");
		}
	}

	// the method is to get picture RGB value and return
	public int[] picturePixelProcessor(int biWidth, int biHeight, byte[] color,
			int blank) {
		int[] result = new int[biWidth * biHeight];
		int index = 0;
		for (int r = 0; r < biHeight; r++) {
			for (int c = 0; c < biWidth; c++) {
				result[biWidth * (biHeight - r - ONE) + c] = (MAX_COLOR & OXFF << TWOFOUR)
						| (((int) color[index + TWO] & OXFF) << ONESIX)
						| (((int) color[index + ONE] & OXFF) << EIGHT)
						| ((int) color[index] & OXFF);
				index += THREE;
			}
			index += blank;
		}

		return result;
	}

	public Image myWrite(Image image, String fileName) throws IOException {
		File imageFile = new File(fileName + ".bmp");
    	if (image == null) {
			throw new IOException("Not such Image!");
		}
		BufferedImage bI = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bI.createGraphics();
		g.drawImage(bI, null, 0, 0);
		ImageIO.write(bI, "bmp", imageFile);
		return image;
	}
}