/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApachePoi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author StefStef
 */
public class ToXML {

    public ToXML(Object[][] data, String[] RowNames, String FileName) throws IOException {
        FileOutputStream fs = new FileOutputStream("test2.xlsx");
        XSSFWorkbook newFile = new XSSFWorkbook();
        XSSFSheet mainSheet = newFile.createSheet();
        int row = 0, col = 0;
        XSSFRow cTitle = mainSheet.createRow(col += 1);

        for (int i = 0; i < RowNames.length; i++) {
            XSSFCell rTitle = cTitle.createCell(row+=1);
            rTitle.setCellValue(RowNames[i]);
        }
        for (int i = 0; i < data.length; i++) {
            row = 0;
            XSSFRow c = mainSheet.createRow(col += 1);
            for (int j = 0; j < RowNames.length; j++) {
                XSSFCell r = c.createCell(row += 1);

                if (data[i][j] instanceof javax.swing.JButton) {
                    break;
                }
                r.setCellValue(String.valueOf( data[i][j]));
                System.out.println("\t" + String.valueOf( data[i][j]));

            }

        }
        newFile.write(fs);
        fs.flush();

        newFile.close();

    }

}
