package com.vector.notetakingapp.controller;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.vector.notetakingapp.model.Note;
import com.vector.notetakingapp.repository.NoteRepository;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {
    @Autowired
    NoteRepository noteRepository;
    
    @RequestMapping(value="",method=RequestMethod.GET)
    public String getIndexPage(Model model) {
        List<Note> notes = noteRepository.findAll();
        System.out.println("Hello");
        if(!notes.isEmpty()) model.addAttribute("notes",notes);
        return "index";
    }

    @RequestMapping(value="/submit",method=RequestMethod.GET)
    public String submitNote(@ModelAttribute Note note,Model model)  {
        noteRepository.save(note);
        
        return getIndexPage(model);
    }


    @RequestMapping(value="/convertToPdf",method=RequestMethod.GET)
    public String getPdfNote(Model model) throws IOException {
        org.jsoup.nodes.Document document = Jsoup.connect("http://localhost:8080").get();

        File file = new File("src/main/resources/hello.html");
        if(file.createNewFile()) {
            System.out.println(file.getName());
        } 
        FileWriter writer = new FileWriter("src/main/resources/hello.html");
        writer.write(document.getElementById("datas").toString());
        writer.close();

        try {
            generatePdf("src/main/resources/hello.html");
        } catch (DocumentException  e) {
            e.printStackTrace();
        }
        noteRepository.deleteAll();
        return getIndexPage(model);
    }
    public static void generatePdf(String fileName) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("../note.pdf"));

        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer,document, new FileInputStream(fileName));
        document.close();
    }
    
    public static void generatePdf(FileInputStream input) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("new.pdf"));

        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer,document, input);
        document.close();
    }

}
