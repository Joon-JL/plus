package com.sds.secframework.common.util;

import java.io.*; 
import java.awt.*;
import java.awt.image.*; 
import javax.swing.*;

public class ResizeImage {

	
	/**
	 * WIDTH 파라메터 전달 시 비율로 계산하여 썸네일 생성
	 * @param soruce    - 원본이미지 경로
	 * @param target    - 타겟이미지 경로
	 * @param targetW   - 타겟이미지 WIDTH
	 * @throws Exception
	 */
	/*public static void createThumbnail(String soruce, String target, int targetW) throws Exception  { 
        
		Image imgSource = new ImageIcon(soruce).getImage(); 

        int oldW = imgSource.getWidth(null); 
        int oldH = imgSource.getHeight(null); 

        int newW = targetW; 
        int newH = (targetW * oldH) / oldW; 

        Image imgTarget = imgSource.getScaledInstance(newW, newH, Image.SCALE_SMOOTH); 

        int pixels[] = new int[newW * newH]; 

        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, newW, newH, pixels, 0, newW); 
        pg.grabPixels(); 

        BufferedImage bi = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB); 
        bi.setRGB(0, 0, newW, newH, pixels, 0, newW); 

        FileOutputStream fos = new FileOutputStream(target); 

        com.sun.image.codec.jpeg.JPEGImageEncoder jpeg = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos); 

        com.sun.image.codec.jpeg.JPEGEncodeParam jep = jpeg.getDefaultJPEGEncodeParam(bi); 
        jep.setQuality(1, false); 

        jpeg.encode(bi, jep); 

        fos.close(); 
        
    } */
	
	/**
	 * 이미지 썸네일 사용자가 지정한 사이즈로 reszing
	 * @param soruce
	 * @param target
	 * @param targetW
	 * @param targetH
	 * @throws Exception
	 */
    /*public static void createThumbnail(String soruce, String target, int targetW, int targetH) throws Exception  { 
        
		Image imgSource = new ImageIcon(soruce).getImage(); 

		int oldW = imgSource.getWidth(null); 
        int oldH = imgSource.getHeight(null);

        int newW = targetW; 
        int newH = targetH; 

        Image imgTarget = imgSource.getScaledInstance(newW, newH, Image.SCALE_SMOOTH); 

        int pixels[] = new int[newW * newH]; 

        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, newW, newH, pixels, 0, newW); 
        pg.grabPixels(); 

        BufferedImage bi = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB); 
        bi.setRGB(0, 0, newW, newH, pixels, 0, newW); 

        FileOutputStream fos = new FileOutputStream(target); 

        com.sun.image.codec.jpeg.JPEGImageEncoder jpeg = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos); 

        com.sun.image.codec.jpeg.JPEGEncodeParam jep = jpeg.getDefaultJPEGEncodeParam(bi); 
        jep.setQuality(1, false); 

        jpeg.encode(bi, jep); 

        fos.close(); 
        
    } */
	
	/*
	public static void main(String args[]) throws Exception {
		ResizeImage im = new ResizeImage();
		
		im.createThumbnail("/upload/20080219.jpg", "/upload/20080219_thumbnail.jpg", 100);
		
	}
	*/
	
	

}