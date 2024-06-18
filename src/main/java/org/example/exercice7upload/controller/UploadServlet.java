package org.example.exercice7upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.exercice7upload.service.Service;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "upload",  value = "/upload")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class UploadServlet extends HttpServlet {

    private Service service = new Service();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("form-upload.jsp").forward(req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("/")+"image";

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
        // i want to save the image in the database

        Part part = req.getPart("file");
        String fileName = part.getSubmittedFileName();
        part.write(uploadPath + File.separator + fileName);

        service.saveImage(fileName, part.getContentType(), part.getInputStream().readAllBytes());

        res.getWriter().println("File uploaded successfully");

    }

}
