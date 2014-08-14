import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;



public class ImageTest {

    @Test
    public void test1Red() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image1test = imageIOImplementation.myRead("1.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/1_red_goal.bmp"));
        BufferedImage imageRedTest = (BufferedImage)imageProcessor.showChanelR(image1test);
        assertEquals(imageRedTest.getWidth() , actual.getWidth());
        assertEquals(imageRedTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageRedTest.getRGB(0,0,imageRedTest.getWidth(),imageRedTest.getHeight(),null,0,imageRedTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test1Green() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image1test = imageIOImplementation.myRead("1.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/1_green_goal.bmp"));
        BufferedImage imageGreenTest = (BufferedImage)imageProcessor.showChanelG(image1test);
        assertEquals(imageGreenTest.getWidth() , actual.getWidth());
        assertEquals(imageGreenTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageGreenTest.getRGB(0,0,imageGreenTest.getWidth(),imageGreenTest.getHeight(),null,0,imageGreenTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test1Blue() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image1test = imageIOImplementation.myRead("1.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/1_blue_goal.bmp"));
        BufferedImage imageBlueTest = (BufferedImage)imageProcessor.showChanelB(image1test);
        assertEquals(imageBlueTest.getWidth() , actual.getWidth());
        assertEquals(imageBlueTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageBlueTest.getRGB(0,0,imageBlueTest.getWidth(),imageBlueTest.getHeight(),null,0,imageBlueTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test1Grey() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image1test = imageIOImplementation.myRead("1.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/1_gray_goal.bmp"));
        BufferedImage imageGreyTest = (BufferedImage)imageProcessor.showGray(image1test);
        assertEquals(imageGreyTest.getWidth() , actual.getWidth());
        assertEquals(imageGreyTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageGreyTest.getRGB(0,0,imageGreyTest.getWidth(),imageGreyTest.getHeight(),null,0,imageGreyTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test2Green() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image2test = imageIOImplementation.myRead("2.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/2_green_goal.bmp"));
        BufferedImage imageGreenTest = (BufferedImage)imageProcessor.showChanelG(image2test);
        assertEquals(imageGreenTest.getWidth() , actual.getWidth());
        assertEquals(imageGreenTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageGreenTest.getRGB(0,0,imageGreenTest.getWidth(),imageGreenTest.getHeight(),null,0,imageGreenTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test2Blue() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image2test = imageIOImplementation.myRead("2.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/2_red_goal.bmp"));
        BufferedImage imageRedTest = (BufferedImage)imageProcessor.showChanelR(image2test);
        assertEquals(imageRedTest.getWidth() , actual.getWidth());
        assertEquals(imageRedTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageRedTest.getRGB(0,0,imageRedTest.getWidth(),imageRedTest.getHeight(),null,0,imageRedTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test2Red() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image2test = imageIOImplementation.myRead("2.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/2_red_goal.bmp"));
        BufferedImage imageRedTest = (BufferedImage)imageProcessor.showChanelR(image2test);
        assertEquals(imageRedTest.getWidth() , actual.getWidth());
        assertEquals(imageRedTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageRedTest.getRGB(0,0,imageRedTest.getWidth(),imageRedTest.getHeight(),null,0,imageRedTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }
    @Test
    public void test2Grey() throws IOException {
        ImageIOImplementation imageIOImplementation = new ImageIOImplementation();
        ImageProcessor imageProcessor = new ImageProcessor();
        Image image2test = imageIOImplementation.myRead("2.bmp");
        BufferedImage actual = ImageIO.read(new File("goal/2_gray_goal.bmp"));
        BufferedImage imageGreyTest = (BufferedImage)imageProcessor.showGray(image2test);
        assertEquals(imageGreyTest.getWidth() , actual.getWidth());
        assertEquals(imageGreyTest.getHeight() , actual.getHeight());
        assertArrayEquals(imageGreyTest.getRGB(0,0,imageGreyTest.getWidth(),imageGreyTest.getHeight(),null,0,imageGreyTest.getWidth()), actual.getRGB(0, 0, actual.getWidth(),actual.getHeight(), null, 0, actual.getWidth()));
    }

}
