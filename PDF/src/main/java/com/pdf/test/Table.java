package com.pdf.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/** * @author  作者 : 金涛
 * @date 创建时间：2017年11月9日 下午4:34:56 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
*/
public class Table {
	private static final String address="E:\\\\";
    String[] tableHeader= { "姓名", "性别", "年龄", "学院", "专业", "年级"}; 
    String[] tableCont= { "姓名1", "性别1", "年龄1", "学院1", "专业1", "年级1"}; 
	//@Test
	public void insertTable() throws Exception {  
		  
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);  
        // 使用PDFWriter进行写文件操作  
        PdfWriter.getInstance(document, new FileOutputStream(address+"pdfText4.pdf")); 
        document.open();  
  
        // 中文字体(现在高版本的不支持中文包)  
        BaseFont bfChinese= BaseFont.createFont("SIMHEI.TTF",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 中文、12、正常  
  
        int colNumber = 6;  
  
        // PdfPTable[PdfPTable[PdfPCell[Paragraph]]]  
        // 创建有6列的表格  
        PdfPTable datatable = new PdfPTable(colNumber);  
        // 定义表格的宽度  
        int[] cellsWidth = { 1, 1, 1, 1, 1, 1 };  
        datatable.setWidths(cellsWidth);// 单元格宽度  
        // datatable.setTotalWidth(300f);//表格的总宽度  
        datatable.setWidthPercentage(100);// 表格的宽度百分比  
  
        datatable.getDefaultCell().setPadding(2);// 单元格的间隔  
        datatable.getDefaultCell().setBorderWidth(2);// 边框宽度  
        // 设置表格的底色  
        datatable.getDefaultCell().setBackgroundColor(BaseColor.GREEN);  
        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        // PdfPTable[PdfPCell[Paragraph]]  
        // 添加表头元素  
        for (int i = 0; i < colNumber; i++) {
            datatable.addCell(new Paragraph(tableHeader[i], fontChinese));  
        }  
        // 添加表格的内容  
        for (int i = 0; i < colNumber; i++) {  
            datatable.addCell(new Paragraph(tableCont[i], fontChinese));  
        }  
  
        // 空白表格  
        for (int i = 0; i < colNumber; i++) {  
            PdfPCell cell = new PdfPCell(new Paragraph(""));  
            cell.setFixedHeight(10);// 单元格高度  
            datatable.addCell(cell);  
        }  
        datatable.setSpacingAfter(40f);// 设置表格下面空白行  
        document.add(datatable);// 把表格加入文档  
  
        // 跨行跨列表格  
        PdfPTable table = new PdfPTable(3); // 3列表格  
        PdfPCell cell; // 单元格  
        cell = new PdfPCell();  
        cell.setColspan(3);// 跨3列  
        table.addCell(cell);  
  
        cell = new PdfPCell();  
        cell.setRowspan(2);// 跨2行  
        table.addCell(cell);  
        table.addCell("row 1; cell 1");  
        table.addCell("row 1; cell 2");  
        table.addCell("row 2; cell 1");  
        table.addCell("row 2; cell 2");  
  
        document.add(table);  
  
        // 表格的嵌套  
        PdfPTable tableFather = new PdfPTable(4);  
        tableFather.setSpacingBefore(20f);// 设置表格上面空白行  
        // 1行2列  
        PdfPTable nested1 = new PdfPTable(2);  
        nested1.addCell("1.1");  
        nested1.addCell("1.2");  
        // 2行1列  
        PdfPTable nested2 = new PdfPTable(1);  
        nested2.addCell("2.1");  
        nested2.addCell("2.2");  
  
        // 将表格插入到指定位置  
        for (int k = 0; k < 12; ++k) {  
            if (k == 1) {  
                tableFather.addCell(nested1);  
            } else if (k == 6) {  
                tableFather.addCell(nested2);  
            } else {  
                tableFather.addCell("cell " + k);  
            }  
        }  
        document.add(tableFather);  
  
        document.close();  
    }
	
	
	@Test
	public void myTable() throws Exception {  
		  
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);  
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(address+"pdfText5.pdf"));  
        document.open();  
  
        PdfPTable table = new PdfPTable(6);  
        // 添加表头元素  
        for (int i = 0; i < 6; i++) {  
            table.addCell(new Paragraph(tableHeader[i], getChineseFont()));  
        }  
        // 添加表格的内容  
        for (int i = 0; i < 6; i++) {  
            table.addCell(new Paragraph(tableCont[i], getChineseFont()));  
        }  
        table.setSpacingBefore(10f);// 设置表格上面空白宽度  
  
        // 1-表格的宽度和布局  
        table.setHorizontalAlignment(Element.ALIGN_LEFT);// 居左  
        table.setTotalWidth(369.7f);// 表格的总宽度  
        table.setWidths(new float[] { 0.1565f, 0.15f, 0.15f, 0.145f, 0.15f, 0.145f });// 单元格宽度  
        table.setWidthPercentage(100);// 设置表格宽度为%100  
        // table.setLockedWidth(true);// 宽度锁定,不锁定，下面有变化  
        document.add(table);  
        document.add(new Paragraph("\n\n"));  
  
        // 居中  
        table.setHorizontalAlignment(Element.ALIGN_CENTER);  
        document.add(table);  
        document.add(new Paragraph("\n\n"));  
  
        // 居右  
        table.setWidthPercentage(50);// 宽度减半  
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);  
        document.add(table);  
        document.add(new Paragraph("\n\n"));  
  
        // 固定宽度  
        table.setTotalWidth(300);  
        table.setLockedWidth(true);// 锁定宽度  
        document.add(table);  
  
        // 2-表格的边框、高度、设置单元格颜色 、前后距离  
        PdfPCell cell = new PdfPCell(new Paragraph("合并3个单元格", getChineseFont()));  
        cell.setColspan(3);  
        cell.setBackgroundColor(BaseColor.CYAN);  
        cell.setMinimumHeight(30f);// 最小高度  
        cell.setFixedHeight(40f);// 固定高度  
        table.addCell(cell);  
  
        // 单元格内文本  
        Paragraph tParagraph = new Paragraph("居中", getChineseFont());  
        tParagraph.setAlignment(Element.ALIGN_CENTER);  
        cell = new PdfPCell(tParagraph);  
        cell.setBorderColor(new BaseColor(255, 0, 0)); // 边框 ，下面的表格有可能会覆盖  
        cell.setFixedHeight(45f);// 固定高度，覆盖前面的固定高度  
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中  
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中  
        table.addCell(cell);  
  
        // 单元格背景色  
        cell = new PdfPCell(new Paragraph("无边框", getChineseFont()));  
        cell.setBorder(Rectangle.NO_BORDER);// 无边框  
        cell.setBorderWidth(0);// 无边框  
        table.addCell(cell);  
  
        // 边框颜色  
        cell = new PdfPCell(new Paragraph("单元格边框颜色", getChineseFont()));  
        cell.setBorderColor(BaseColor.YELLOW);  
        table.addCell(cell);  
  
        document.add(new Paragraph("使用'SpacingBefore'和'setSpacingAfter'", getChineseFont()));  
        table.setSpacingBefore(15f); // 前距离  
        document.add(table);  
        table.setSpacingAfter(15f); // 后距离  
        document.add(table);  
  
        // 3-写入文档的绝对位置  
        // 参数rowStart是你想开始的行的数目，参数rowEnd是你想显示的最后的行（如果你想显示所有的行，用-1），  
        // xPos和yPos是表格的坐标，canvas是一个PdfContentByte对象。  
  
        document.add(new Paragraph(  
                "写入文档的绝对位置:(writeSelectedRows(rowStart, rowEnd, xPos, yPos, canvas))参数rowStart是你想开始的行的数目，参数rowEnd是你想显示的最后的行（如果你想显示所有的行，用-1），xPos和yPos是表格的坐标，canvas是一个PdfContentByte对象。",  
                getChineseFont()));  
        PdfContentByte tContent = writer.getDirectContent();  
        table.writeSelectedRows(0, -1, 0, -1, 100, 200, tContent);  
  
        document.add(new Paragraph("第1行到最后,从0开始计数", getChineseFont()));  
        table.writeSelectedRows(1, -1, 100, 100, tContent);  
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
