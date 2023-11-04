package com.tastypoisonfly.exercise;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "Download", value = "/Download")
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  index = request.getParameter("index");//文件的索引

        response.setContentType("application/octet-stream");//文件输出用二进制流
        response.setHeader("Content-Disposition","attachment;filename=\"" + index +"\"");

        OutputStream os = response.getOutputStream();
        ServletContext context = getServletContext();

        InputStream is = context.getResourceAsStream("/files/" + index);

        byte[] bytes = new byte[2048];

        int bytesread = 0;

        while ((bytesread = is.read(bytes))!=-1){
            os.write(bytes,0,bytesread);
        }

        os.flush();;
        is.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}