package com.pdf.test;

import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/** * @author  作者 : 金涛
 * @date 创建时间：2017年11月9日 下午2:05:41 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
*/
public class Demo {
	private static final String FILE_DIR = "G:\\";
	@Test
	public void createPdf() throws Exception {
		//文件1
		// 1-创建文本对象 Document  
		Document document = new Document();   
		// 2-初始化 pdf输出对象 PdfWriter
		PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "createSamplePDF1.pdf"));
		// 3-打开 Document  
		document.open();
		// 4-往 Document 添加内容 
		document.add(new Paragraph("Hello World 肖毅"));
		// 5-关闭 Document 
		document.close();
		//文件2
		Document document1 = new Document();
		PdfWriter.getInstance(document1, new FileOutputStream(FILE_DIR + "createSamplePDF2.pdf")); 
		document1.open();
		document1.add(new Paragraph("Hello World"));
		document1.close();
		//文件3
		//Document(Rectangle pageSize, float marginLeft, float marginRight, float marginTop,float marginBottom)
		//分别指pdf页面大小和内容距离文档边的距离。
		Document document11 = new Document(PageSize.A4, 5, 15, 50, 50);
        PdfWriter.getInstance(document11, new FileOutputStream(FILE_DIR + "createSamplePDF3.pdf"));
        document11.open();
        document11.add(new Paragraph("Hello！ PDF！！！"));
        document11.close();  
    }  
}
