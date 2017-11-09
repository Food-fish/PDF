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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/** * @author  作者 : 金涛
 * @date 创建时间：2017年11月9日 下午2:51:00 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
*/
public class Article {
	private static final String address="G:\\\\";
	//设置页数，添加附加属性
	//@Test
	public void Article1() throws Exception{
		//Document document = new Document(PageSize.A4.rotate());横向旋转打印ss
		Document document = new Document(PageSize.A4);
		try {  
		    // 解析器  
		    PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(address+"pdfText1.pdf"));
		    // 3-为pdf添加属性信息  
		    document.addAuthor("作者");  
		    document.addTitle("标题");  
		    document.addSubject("主题");  
		    document.addKeywords("关键字");
		    //页边空白    
		    document.setMargins(10, 20, 30, 40);
		    document.open();
		    // 4-PDF添加内容  
		    document.add(new Paragraph("Hello world"));
		    // 5-添加Page  
		    document.newPage();
		    // writer.setPageEmpty(false);//显示空内容的页  
		    writer.setPageEmpty(false);//不会显示空内容的页 
		    document.newPage();  
		    document.add(new Paragraph("New page"));
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
		    document.close();
		}
	}
	/*设置背景颜色*/
	//@Test
	public void myPDF() throws DocumentException, FileNotFoundException {  
		  
        // 1- 页面的属性  
        Rectangle tRectangle = new Rectangle(PageSize.A4); // 页面大小  
        // tRectangle = new Rectangle(0, 0, 800, 600);  
  
        tRectangle.setBackgroundColor(BaseColor.ORANGE); // 页面背景色  
        tRectangle.setBorder(1220);// 边框  
        tRectangle.setBorderColor(BaseColor.BLUE);// 边框颜色  
        tRectangle.setBorderWidth(244.2f);// 边框宽度  
  
        Document doc = new Document(tRectangle);// 定义文档  
        //doc = new Document(tRectangle.rotate());// 横向打印  
  
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(address+"pdfText2.pdf"));// 书写器  
  
        // PDF版本(默认1.4)  
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);  
  
        // 2-PDF文档属性  
        doc.addTitle("Title@sample");// 标题  
        doc.addAuthor("Author@rensanning");// 作者  
        doc.addSubject("Subject@iText sample");// 主题  
        doc.addKeywords("Keywords@iText");// 关键字  
        doc.addCreator("Creator@iText");// 谁创建的  
  
        // 3-综合属性：  
        doc.setMargins(10, 20, 30, 40);// 页边空白  
  
        doc.open();// 打开文档  
        doc.add(new Paragraph("Hello World"));// 添加内容  
  
        // 4-添加下一页  
        doc.newPage();  
        writer.setPageEmpty(false);// fasle-显示空内容的页;true-不会显示  
  
        doc.newPage();  
        doc.add(new Paragraph("New page"));  
  
        doc.close();  
    }
	//支持中文
	public Font getChineseFont() {  
        BaseFont bfChinese;  
        Font fontChinese = null;  
        try {
        	/*设置中文字体包*/
        	bfChinese= BaseFont.createFont("SIMHEI.TTF",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
            // fontChinese = new Font(bfChinese, 12, Font.NORMAL);  
            fontChinese = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLUE);  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return fontChinese;  
  
    }
	
	@Test
	public void addContent() throws Exception {  
        Document document = new Document(PageSize.A4);  
        PdfWriter.getInstance(document, new FileOutputStream(address+"pdfText3.pdf"));  
        document.open();  
        System.out.println(new Chunk("中文输出： ", getChineseFont()));
        // 1-Chunk块对象: a String, a Font, and some attributes  
        document.add(new Chunk("中文输出： ", getChineseFont()));  
  
        Chunk tChunk2 = new Chunk("输出的内容", getChineseFont());  
        tChunk2.setBackground(BaseColor.CYAN, 1f, 0.5f, 1f, 1.5f); // 设置背景色  
        tChunk2.setTextRise(6); // 上浮  
        tChunk2.setUnderline(0.2f, -2f); // 下划线  
        document.add(tChunk2);  
  
        document.add(Chunk.NEWLINE); // 新建一行  
        // document.add(new Phrase("Phrase page  :")); //会上浮，不知道原因？？  
  
        // 2-Phrase短语对象: a List of Chunks with leading  
        document.add(new Phrase("Phrase page  :"));  
  
        Phrase tPhrase = new Phrase();  
        Chunk name = new Chunk("China");  
        name.setUnderline(0.2f, -2f);  
        tPhrase.add(name);  
        tPhrase.add(Chunk.NEWLINE);// 放在容器中好用  
        tPhrase.add(new Chunk("换行了 :", getChineseFont()));  
        tPhrase.add(new Chunk("chinese"));  
        tPhrase.setLeading(14f);// 行间距  
        document.add(tPhrase);  
  
        // 这边就好用，上面是Chunk，就不好用  
        // 放在段落或短语中又好用  
        document.add(Chunk.NEWLINE);  
  
        Phrase director2 = new Phrase();  
        Chunk name2 = new Chunk("换行了---Japan", getChineseFont());  
        name2.setUnderline(0.2f, -2f);  
        director2.add(name2);  
        director2.add(new Chunk(","));  
        director2.add(new Chunk(" "));  
        director2.add(new Chunk("japanese上浮下", getChineseFont()).setTextRise(3f));  
        director2.setLeading(24);  
        document.add(director2);  
  
        // 3-Paragraph段落对象: a Phrase with extra properties and a newline  
        document.add(new Paragraph("Paragraph page"));  
        Paragraph info = new Paragraph();  
        info.add(new Chunk("China "));  
        info.add(new Chunk("chinese"));  
        info.add(Chunk.NEWLINE); // 好用的  
        info.add(new Phrase("Japan "));  
        info.add(new Phrase("japanese"));  
        info.setSpacingAfter(10f);// 设置段落下空白  
        document.add(info);  
  
        // 段落是比较好用的  
        Paragraph tParagraph = new Paragraph("段落是文章中最基本的单位。内容上它具有一个相对完整的意思；在文章中，段具有换行的标。段是由句子或句群组成的，在文章中用于体现作者的思路发展或全篇文章的层次。有的段落只有一个句子，称为独句段，独句段一般是文章的开头段、结尾段、"  
                + "过渡段强调段等特殊的段落。多数段落包括不止一个句子或句群，叫多句段。中文段落开头前一般空两个格。", getChineseFont());  
        tParagraph.setAlignment(Element.ALIGN_JUSTIFIED);// 对齐方式  
  
        tParagraph.setIndentationLeft(12);// 左缩进  
        tParagraph.setIndentationRight(12);// 右缩进  
        tParagraph.setFirstLineIndent(24);// 首行缩进  
  
        tParagraph.setLeading(20f);// 行间距  
        tParagraph.setSpacingBefore(5f);// 设置上空白  
        tParagraph.setSpacingAfter(10f);// 设置段落下空白  
        document.add(tParagraph);  
  
        // 每个新的段落会另起一行  
        tParagraph = new Paragraph("新的段落", getChineseFont());  
        tParagraph.setAlignment(Element.ALIGN_CENTER);// 居中  
        document.add(tParagraph);  
  
        document.close();  
    }
}
