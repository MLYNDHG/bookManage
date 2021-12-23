package com.Httb.bookManage.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadImageUtil {

    public static String uploadImage(MultipartFile file) throws IOException {

        if (file != null) {
            String fileName = file.getOriginalFilename();
            String[] strings = fileName != null ? fileName.split("\\.") : null;
            if (strings != null) {
                String suffix = strings[strings.length - 1];
                String name = System.currentTimeMillis() + "." + suffix;

                // 文件上传的本机地址
                String dest = ConstantPath.DIR + name;

                // 通过字节流的形式输入输出文件
                InputStream fis = file.getInputStream();
                File newFile = new File(dest);
                FileOutputStream fos = new FileOutputStream(newFile);

                // 一次读 1MB
                byte[] bytes = new byte[1024];
                int temp;
                while ((temp = fis.read(bytes)) != -1) {
                    // 将字节读入文件
                    fos.write(bytes, 0, temp);
                }

                // 刷新
                fos.flush();

                // 关闭IO流
                fos.close();
                fis.close();

                return ConstantPath.PATH + name;
            }
        }
        return null;
    }
}
