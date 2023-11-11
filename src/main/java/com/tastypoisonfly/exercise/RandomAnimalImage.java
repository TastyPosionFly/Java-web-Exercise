package com.tastypoisonfly.exercise;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@WebServlet(name = "RandomAnimalImage", value = "/RandomAnimalImage")
public class RandomAnimalImage extends HttpServlet {
    private static final String IMAGE_FILE_PATH = "D:/Work/JavaWeb/Java-web-Exercise/src/main/webapp/image";//存放图片的文件夹的绝对路径
    private static final String[] animalImages = getAnimalImages();
    private static int[] animalRace;//图片是猫还是狗，猫为0，狗为1

    private static String[] getAnimalImages() {
        File folder = new File(IMAGE_FILE_PATH);
        //System.out.println("Image Folder Path: " + folder.getAbsolutePath());
        File[] files = folder.listFiles();
        if (files != null) {
            String[] imageNames = new String[files.length];
            animalRace = new int[files.length];
            for (int i = 0; i < files.length; i++) {
                String fullFileName = files[i].getName();

                imageNames[i] =fullFileName;

                //System.out.println(fullFileName);

                // 获取不带后缀的文件名,用于判断图片是狗还是猫
                int lastDotIndex = fullFileName.lastIndexOf(".");
                String fileNameWithoutExtension = (lastDotIndex == -1) ? fullFileName : fullFileName.substring(0, lastDotIndex);

                if (fileNameWithoutExtension.length() == 2){
                    animalRace[i] = 1;
                }
                else {
                    animalRace[i] = 0;
                }

            }
            return imageNames;
        } else {
            //System.out.println("none");
            return new String[0];
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("doGet");
        // 获取图像文件夹的路径
        String imageFolderPath = IMAGE_FILE_PATH;


        // 获取随机的动物图片文件名
        int imageIndex = getRandomAnimalImage();

        String randomAnimalImage = animalImages[imageIndex];
        int animalType = animalRace[imageIndex];//得到动物的种类

//        System.out.println(randomAnimalImage);
//        System.out.println(animalType);

        HttpSession session = request.getSession();
        session.setAttribute("animalType",animalType);

        String imagePath = imageFolderPath + File.separator + randomAnimalImage;

        // 读取图片文件并写入响应输出流
        try (InputStream inputStream = new FileInputStream(imagePath)) {
            response.setContentType("image/jpeg"); // 根据实际图片类型设置 content type
            response.setContentLengthLong(new File(imagePath).length());

            // 将图片数据写入响应输出流
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private int getRandomAnimalImage() {
        Random random = new Random();
        int randomIndex;

        // 添加条件检查，确保 animalImages.length 是正数
        if (animalImages != null && animalImages.length > 0) {
            randomIndex = random.nextInt(animalImages.length);
        } else {
            randomIndex = 0; // 默认返回索引 0，因为数组为空或长度为负数
        }

        return randomIndex;
    }

}