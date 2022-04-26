package hiveCommon;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelOperation {

    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;

    public ExcelOperation(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = getSheet("Data");

        }catch (Exception e)
        {

        }
    }

    public XSSFSheet getSheet(String SheetName)
    {
        XSSFSheet sheet;
        for(int i=0; i<xssfWorkbook.getNumberOfSheets();i++)
        {
            if(xssfWorkbook.getSheetAt(i).getSheetName().equalsIgnoreCase(SheetName))
            {
                sheet = xssfWorkbook.getSheetAt(i);
                return sheet;
            }
        }
        return null;
    }

    public List<String> getLabelCol(String Label, String value) {
        Iterator<Row> itR = xssfSheet.iterator();
        int Col = 0;
        int Column = 0;
        List<String> data = new ArrayList<>();

        Row firstborn = itR.next();
        Iterator<Cell> irC = firstborn.cellIterator();

        while (irC.hasNext()) {
            Cell c1 = irC.next();
            if (c1.getStringCellValue().equalsIgnoreCase(Label)) {

                Column = Col;

            }

            Col++;
        }

        while(itR.hasNext())
        {
            Row row = itR.next();
            Cell dataCell = row.getCell(Column);
            if(dataCell.getStringCellValue().equalsIgnoreCase(value))
            {
                Iterator<Cell> datait = row.cellIterator();
                for(int i = 0; i<Column; i++)
                {
                    Cell junk = datait.next();
                    datait.remove();
                }
                while(datait.hasNext())
                {
                    Cell Valve = datait.next();
                    if(Valve.getCellType().equals(CellType.STRING))
                    {
                        String StringData = Valve.getStringCellValue();
                        data.add(StringData);
                    }else {
                        String date = new SimpleDateFormat("dd/M/yyyy").format(Valve.getDateCellValue());
                        data.add(date);
                    }
                }
            }
        }
        return data;
    }

}
