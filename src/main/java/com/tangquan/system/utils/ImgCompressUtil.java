package com.tangquan.system.utils;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * @author zq
 */
public class ImgCompressUtil {

  /**
   * log4j
   */
  private final static Logger logger = LoggerFactory.getLogger(ImgCompressUtil.class);

  public static byte[] ratioCompressImg(InputStream inputStream, String fileName) {
    try {
      BufferedImage srcFile = ImageIO.read(inputStream);
      int width = srcFile.getWidth();
      int height = srcFile.getHeight();

      String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

      BufferedImage buffImg;
      if (suffix.equals("png")) {
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      } else {
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      }

      Graphics2D graphics = buffImg.createGraphics();
      graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_DEFAULT), 0, 0, null);
      graphics.dispose();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(buffImg, suffix, baos);
      return baos.toByteArray();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return new byte[0];
  }


  /**
   * 根据设置的宽高等比例压缩图片文件<br> 先保存原文件，再压缩、上传
   * 当自定义的宽带小于原始图片宽带切宽度大于0时才进行等比缩放
   *
   * @param file 要进行压缩的文件
   * @param width 宽度
   */
  public static void ratioCompressImg(File file, String fileName, int width) {
    if (file == null) {
      throw new IllegalArgumentException("file not can is null");
    }
    try {
      /** 对服务器上的临时文件进行处理 */
      Image srcFile = ImageIO.read(file);
      int w = srcFile.getWidth(null);
      if (width < 0 || width >= w) {
        return;
      }
      int h = srcFile.getHeight(null);
      int height = h * width / w;

      String srcImgPath = file.getAbsoluteFile().toString();
      String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

      BufferedImage buffImg;
      if (suffix.equals("png")) {
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      } else {
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      }

      Graphics2D graphics = buffImg.createGraphics();
      graphics.setBackground(new Color(255, 255, 255));
      graphics.setColor(new Color(255, 255, 255));
      graphics.fillRect(0, 0, width, height);
      graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

      ImageIO.write(buffImg, suffix, new File(srcImgPath));

    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }


  /**
   * 图片压缩主方法
   *
   * @param file 图片路径
   * @param width 目标宽
   * @param JPEGCompression 压缩质量/百分比
   */
  public static void ImgCompress(File file, int width, float JPEGCompression) {
    if (file == null) {
      throw new IllegalArgumentException("file not can is null");
    }
    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      int w = bufferedImage.getWidth();
      if (width < 0 || width >= w) {
        return;
      }
      int h = bufferedImage.getHeight();
      int height = h * width / w;
      BufferedImage image_to_save = new BufferedImage(width, height,
          bufferedImage.getType());
      image_to_save.getGraphics().drawImage(
          bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
          0, null);
      FileOutputStream fos = new FileOutputStream(file);

      saveAsJPEG(image_to_save, JPEGCompression, fos);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }


  /**
   * 以JPEG编码保存图片
   *
   * @param image_to_save 要处理的图像图片
   * @param JPEGCompression 压缩比
   * @param fos 文件输出流
   */
  private static void saveAsJPEG(BufferedImage image_to_save, float JPEGCompression,
      FileOutputStream fos) throws IOException {

    // Image writer
    JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO
        .getImageWritersBySuffix("jpg").next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
    imageWriter.setOutput(ios);
    // and metadata
    IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(
        new ImageTypeSpecifier(image_to_save), null);

    if (JPEGCompression >= 0 && JPEGCompression <= 1f) {
      // new Compression
      JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter
          .getDefaultWriteParam();
      jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
      jpegParams.setCompressionQuality(JPEGCompression);
    }

    // new Write and clean up
    imageWriter.write(imageMetaData,
        new IIOImage(image_to_save, null, null), null);
    ios.close();
    imageWriter.dispose();
  }


}