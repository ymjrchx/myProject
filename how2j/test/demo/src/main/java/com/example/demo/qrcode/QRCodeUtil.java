package com.example.demo.qrcode;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author chenxin
 * @date 2019/9/27 9:33
 */
public class QRCodeUtil {
    public static void qrCodeEncode(String encodedDate, File destFile) throws IOException {
        Qrcode qrcode =new Qrcode();
        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');
        qrcode.setQrcodeVersion(7);
        byte[] d = encodedDate.getBytes("UTF-8");
        BufferedImage bi = new BufferedImage(139,139,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();

        g.setBackground(Color.WHITE);
        g.clearRect(0,0,139,139);
        g.setColor(Color.BLACK);
        if(d.length>0 && d.length<123){
            boolean[][] b = qrcode.calQrcode(d);
            for (int i = 0; i < b.length ; i++) {
                for (int j = 0; j < b.length ; j++) {
                    if(b[j][i]){
                        g.fillRect(j*3+2,i*3+2,3,3);
                    }

                }

            }
        }
        g.dispose();
        bi.flush();
        ImageIO.write(bi,"png",destFile);
    }

    public static String qrCodeDecode(File imageFile){
        String decodeDate = null;
        QRCodeDecoder decoder = new QRCodeDecoder();
        BufferedImage image = null;
        try{
            image = ImageIO.read(imageFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            decodeDate = new String(decoder.decode(new J2SEImage(image)),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeDate;
    }

    static class J2SEImage implements QRCodeImage{
        BufferedImage image;

        public J2SEImage(BufferedImage image) {
            this.image = image;
        }

        @Override
        public int getWidth() {
            return image.getWidth();
        }

        @Override
        public int getHeight() {
            return image.getHeight();
        }

        @Override
        public int getPixel(int i, int i1) {
            return image.getRGB(i,i1);
        }
    }

    public static void main(String[] args) {
        String filePath = "d:/qrcode.png";
        File qrFile = new File(filePath);
        String encodeDate = "娟妹妹！乖哦，不调皮啊！\r\n" +
                "哥哥晚上给你糖糖吃！";
        try{
            QRCodeUtil.qrCodeEncode(encodeDate,qrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String reText = QRCodeUtil.qrCodeDecode(qrFile);
        System.out.println(reText);
    }

}

































































