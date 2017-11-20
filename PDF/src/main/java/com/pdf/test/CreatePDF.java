package com.pdf.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
	private static final String address="E:\\\\";
	@Test
	public void Create() throws DocumentException,Exception {
		Document document = new Document(PageSize.A4); 
		try { 
	        PdfWriter.getInstance(document, new FileOutputStream(address+"pdfText3.pdf"));
	        document.addAuthor("系统管理员");  
		    document.addTitle("医院医疗收入及医疗成本预算表");  
		    document.addSubject("预算表");  
		    document.addKeywords("医疗");
	        document.setMargins(15, 15, 15, 10);
	        Paragraph tParagraph = new Paragraph("医院医疗收入及医疗成本预算表\n\n", getChineseFontBLACK(12));
	        tParagraph.setAlignment(Element.ALIGN_CENTER);
	        document.open();
	        PdfPTable datatable=getHeader();
	        PdfPTable remarks=getRemarks();
	        document.add(tParagraph);
	        document.add(remarks);
	        document.add(datatable);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			document.close();
		}
		
	}
	
	public PdfPTable getHeader() throws Exception {
		PdfPTable datatable = new PdfPTable(5);
		int[] cellsWidth = { 2, 1, 1, 1, 1 };
		datatable.setWidths(cellsWidth);
		datatable.setWidthPercentage(100);
		datatable.getDefaultCell().setPadding(5);
        datatable.getDefaultCell().setBorderWidth(1);
        datatable.getDefaultCell().setBorderWidth(0);
        datatable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);  
        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        datatable.addCell(setParagraph("项目",10,2,25,0));  
        datatable.addCell(setParagraph("行次",10,2,25,0));  
        datatable.addCell(setParagraph("上年预计执行数",10,2,25,0));  
        datatable.addCell(setParagraph("本年预算数",10,2,25,0));  
        datatable.addCell(setParagraph("比上年增减(%)",10,2,25,0));
		return datatable;
	}
	
	public PdfPTable getRemarks() throws Exception {
		PdfPTable datatable = new PdfPTable(2);
		int[] cellsWidth = {1, 1 };
		datatable.setWidths(cellsWidth);
		datatable.setWidthPercentage(100);
		datatable.getDefaultCell().setPadding(2);
        datatable.getDefaultCell().setBorderWidth(0);
        datatable.getDefaultCell().setBorderWidth(0);
        datatable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);  
        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        datatable.addCell(setParagraph("编制单位：",8,0,20,0)).setBorderColor(BaseColor.WHITE);  
        datatable.addCell(setParagraph("财医预1表-1\n金额单位：元",8,1,20,0)).setBorderColor(BaseColor.WHITE);
		return datatable;
	}
	
	public PdfPTable getTableBody() {
		return null;
		
	}
	
	public PdfPCell setParagraph(String text,int size, int fl,int hight,int left) {
		Paragraph tParagraph = new Paragraph(text, getChineseFontBLACK(size));
		PdfPCell cell = new PdfPCell();
		tParagraph.setAlignment(Element.ALIGN_CENTER);
		tParagraph.setIndentationLeft(left);
		cell.setPhrase(tParagraph);
		cell.setBorderColor(new BaseColor(0, 0, 0));
        cell.setFixedHeight(hight);
    	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if(fl==0) {
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        }else if(fl==1) {
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        }else if(fl==2) {
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        }
		return cell;
	}
	
	public Font getChineseFontBLACK(int size) {  
        BaseFont bfChinese;  
        Font fontChinese = null;  
        try {
        	bfChinese= BaseFont.createFont("SIMHEI.TTF",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            fontChinese = new Font(bfChinese, size, Font.NORMAL, BaseColor.BLACK);  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return fontChinese;  
  
    }
}
