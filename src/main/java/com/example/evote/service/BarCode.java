package com.example.evote.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;


public class BarCode {
    BufferedImage barcode(String args,int wid,int hig) {

        int width = wid;
        int height = hig;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(args, BarcodeFormat.QR_CODE, width, height);;


            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int color = bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF;
                    image.setRGB(x, y, color);
                }
            }


            System.out.println("QR code generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
