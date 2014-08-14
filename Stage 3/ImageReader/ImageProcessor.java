// @KIADRAGON
// IIMAGEPROCESS EXTENDS FROM IIMAGEPROCESSOR
import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageProcessor implements IImageProcessor {
	// PRIVATE STATIC INT
	private static final int TWOFOUR = 24, EIGHT = 8, ONESIX = 16,
			MAX_COLOR = 255;

	// constructor
	// showChannelR and process with it
	public Image showChanelR(Image image) {
		BufferedImage cloneImage = (BufferedImage) image;
		BufferedImage rImage = new BufferedImage(cloneImage.getWidth(),
				cloneImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		int width = rImage.getWidth();
		int height = rImage.getHeight();
		// get color from original image
		int[] color = new int[width * height];
		// getColor
		color = cloneImage.getRGB(0, 0, width, height, color, 0, width);
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				// filter change to red
				color[r * width + c] = color[r * width + c] & 0xffff0000;
			}
		}
		rImage.setRGB(0, 0, width, height, color, 0, width);
		return rImage;
	}

	// showChannelG and process with it
	public Image showChanelG(Image image) {
		BufferedImage cloneImage = (BufferedImage) image;
		BufferedImage gImage = new BufferedImage(cloneImage.getWidth(),
				cloneImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		int width = gImage.getWidth();
		int height = gImage.getHeight();
		// get color from original image
		int[] color = new int[width * height];
		// getColor
		color = cloneImage.getRGB(0, 0, width, height, color, 0, width);
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				// filter change to green
				color[r * width + c] = color[r * width + c] & 0xff00ff00;
			}
		}
		gImage.setRGB(0, 0, width, height, color, 0, width);
		return gImage;
	}

	// showChannelB and process with it
	public Image showChanelB(Image image) {
		BufferedImage cloneImage = (BufferedImage) image;
		BufferedImage bImage = new BufferedImage(cloneImage.getWidth(),
				cloneImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		int width = bImage.getWidth();
		int height = bImage.getHeight();
		// get color from original image
		int[] color = new int[width * height];
		// getColor
		color = cloneImage.getRGB(0, 0, width, height, color, 0, width);

		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				// filter change to blue
				color[r * width + c] = (int) (color[r * width + c] & 0xff0000ff);
			}
		}
		bImage.setRGB(0, 0, width, height, color, 0, width);
		return bImage;
	}

	// showGrey and process with image
	public Image showGray(Image image) {
		BufferedImage cloneImage = (BufferedImage) image;
		BufferedImage greyImage = new BufferedImage(cloneImage.getWidth(),
				cloneImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		int width = cloneImage.getWidth();
		int height = cloneImage.getHeight();
		// get color from original image
		int[] color = new int[width * height];
		// getColor
		color = cloneImage.getRGB(0, 0, width, height, color, 0, width);
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				// filter change to grey
				// caculate out greys
				int grey = (int) (0.299
						* ((color[r * width + c] & 0x00ff0000) >> ONESIX)
						+ 0.587
						* ((color[r * width + c] & 0x0000ff00) >> EIGHT) + 0.114 * (color[r
						* width + c] & 0x000000ff));
				// change color
				color[r * width + c] = grey | (grey << EIGHT)
						| (grey << ONESIX) | (MAX_COLOR & 0xff << TWOFOUR);
			}
		}
		// set color
		greyImage.setRGB(0, 0, width, height, color, 0, width);
		return greyImage;
	}
}