package com.pdf.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/** * @author  作者 : 金涛
 * @date 创建时间：2017年11月9日 下午4:28:24 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
*/
public class PrintImg {
	private static final String address="E:\\\\";
	
	@Test
	public void insertObject() throws Exception {  
		  
        Document document = new Document(PageSize.A4);  
        PdfWriter.getInstance(document, new FileOutputStream(address+"pdfText1.pdf"));  
        document.open();  
  
        // Anchor超链接和锚点对象: internal and external links  
        Paragraph country = new Paragraph();  
        Anchor dest = new Anchor("我是锚点，也是超链接", getChineseFont());  
        dest.setName("CN"); // 设置锚点的名字  
        dest.setReference("http://www.baidu.com");// 连接  
        country.add(dest);  
        country.add(String.format(": %d sites", 10000));  
        document.add(country);  
  
        Anchor toUS = new Anchor("连接到设置的CN锚点。", getChineseFont());  
        toUS.setReference("#CN");// 取到锚点  
        document.add(toUS);  
  
        // 图片Image对象  
       /* Image img = Image.getInstance("D:/BaiduNetdiskDownload/图片/RNB13 图包/001.jpg");  
        img.setAlignment(Image.LEFT);  
        img.setBorder(Image.BOX);  
        img.setBorderWidth(10);  
        img.setBorderColor(BaseColor.WHITE);  
        img.scaleToFit(1000, 72);// 大小  
        img.setRotationDegrees(-30);// 旋转  
        document.add(img); */ 
  
        // Chapter, Section对象（目录对象）  
        Paragraph title = new Paragraph("一级标题", getChineseFont());  
        Chapter chapter = new Chapter(title, 1);  
  
        Paragraph title2 = new Paragraph("二级标题1", getChineseFont());  
        Section section = chapter.addSection(title2);  
        section.setBookmarkTitle("bmk");// 左边目录显示的名字，不写就默认名  
        section.setIndentation(30);  
        section.setIndentationLeft(5);  
        section.setBookmarkOpen(false);  
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);  
  
        Section section2 = chapter.addSection(new Paragraph("二级标题2", getChineseFont()));  
        section2.setIndentation(30);  
        section2.setIndentationLeft(5);  
        section2.setBookmarkOpen(false);  
        section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);  
  
        Section subsection = section.addSection(new Paragraph("三级标题1", getChineseFont()));  
        subsection.setIndentationLeft(10);  
        // subsection.setNumberDepth(1);  
        subsection.setNumberStyle(Section.NUMBERSTYLE_DOTTED);  
  
        Section subsection2 = section2.addSection(new Paragraph("三级标题2", getChineseFont()));  
        subsection2.setIndentationLeft(10);  
        subsection2.setNumberStyle(Section.NUMBERSTYLE_DOTTED);  
        document.add(chapter);  
  
        document.close();  
  
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
}
