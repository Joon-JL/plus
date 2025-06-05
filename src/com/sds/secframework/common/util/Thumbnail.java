//package com.sds.secframework.common.util;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.awt.image.renderable.ParameterBlock;
//import java.io.OutputStream;
// 
//import javax.media.jai.Interpolation;
//import javax.media.jai.JAI;
//import javax.media.jai.KernelJAI;
//import javax.media.jai.RenderedOp;
//
//public class Thumbnail {
//
//	public static void thumbnail (String img, String thumb, OutputStream stream, int w, int h) throws Exception {
//		
//		String filename = img;
//		String dest = thumb;
//		OutputStream out = stream;
//		int tw = w;	            // 원하는 가로 크기
//		int th = h;	            // 원하는 세로 크기
//		int width = 0;	        // 결정된 가로 크기
//		int height = 0;	        // 결정된 세로 크기
//		double widthRatio = 0.0f;
//		double heightRatio = 0.0f;
//		double ratio = 1.0f;
//		
//		// sharpen, quality를 처리를 할것. 외부에서 해상도를 조절할 수 있게 값을 받을 수도 있다
//		int sharpen = 150;
//		float quality = 0.95f;
//	
//	    if (tw <= 0 || th <= 0) throw new Exception("width, height가 0보다 커야 합니다.");
//	
//		RenderedOp im = JAI.create("fileload", filename);           // 파일을 읽어들인다.
//		widthRatio = (double)tw / (double)im.getWidth();            // width 비율
//		heightRatio = (double)th / (double)im.getHeight();          // height 비율
//	
//		// 처리하는 방법은 여러가지가 있을 수 있지만
//		// 여기서는 원하는 width, height중에 짧은쪽을 기준으로 처리한다
//		if (widthRatio < heightRatio) // 가로가 짧다
//		{
//			ratio = widthRatio;  // ratio를 아래에서 사용한다. 삭제하지 말것
//			width = tw;
//			height = (int)(ratio * im.getHeight());
//		}
//		else if (widthRatio == heightRatio) // 가로, 세로가 같다
//		{
//			ratio = widthRatio;
//			width = (int)(ratio * im.getWidth());
//			height = (int)(ratio * im.getHeight());
//		}
//		else // if (widthRatio > heightRatio) // 세로가 짧다
//		{
//			ratio = heightRatio;
//			width = (int)(ratio * im.getWidth());
//			height = th;
//		}
//		
//		
//	
//		// 출력할 새 그리판을 만든다.
//		BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics2D destGd = destImage.createGraphics();
//	
//		// sharpen
//		float kData[] = new float[9];
//		float alpha;
//		ParameterBlock pbSharpen = new ParameterBlock();
//		pbSharpen.addSource(im);
//	
//		if (sharpen > 150) alpha = (sharpen - 125.0f) / 25.0f;
//		else               alpha = sharpen / 150.0f;
//	
//		float beta = (1.0f - alpha) / 8.0f;
//		for (int i = 0; i < 9; i++) kData[i] = beta;
//		
//		kData[4] = alpha;
//		KernelJAI k = new KernelJAI(3, 3, 1, 1, kData);
//		pbSharpen.add(k);
//	
//		// scale
//		RenderedOp sharpenOp  = JAI.create("convolve", pbSharpen, null);
////		BufferedImage sharpenImage = sharpenOp.getAsBufferedImage();
//		Interpolation interp = Interpolation.getInstance(Interpolation.INTERP_NEAREST);
//		// 아래의 코드는 수정하면 안된다
//		ParameterBlock scalePb = new ParameterBlock();
//		scalePb.addSource(sharpenOp);
//		scalePb.add((float)ratio);
//		scalePb.add((float)ratio);
//		scalePb.add(0.0f);
//		scalePb.add(0.0f);
//		scalePb.add(interp);
//	
//		RenderedOp scaleOp = JAI.create("scale",  scalePb);
//		BufferedImage scaleImage = scaleOp.getAsBufferedImage();
//	
//		destGd.drawImage(scaleImage, 0, 0, width, height, null);  // 0, 0은 alignWidth, alignHeight
//	
//		com.sun.media.jai.codec.JPEGEncodeParam encodeParam = new com.sun.media.jai.codec.JPEGEncodeParam();
//		encodeParam.setQuality(quality);
//	
//		if (dest != null) JAI.create("filestore", destImage, dest, "JPEG", encodeParam);
//		else              JAI.create("encode", destImage, out, "JPEG", encodeParam);
//		
//		
//		im.dispose();
//		im = null;
//	}
//}
//
