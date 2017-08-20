
package Beans;

import ENTIDAD.registrotmC;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


@ManagedBean(name = "importarHorarioB")
public class importarHorario {

    private List<registrotmC> registrotmL;

    public void readExcelFile(String fileName) {
        fileName = "D:\\upig-2.xls";
        /**
         * Create a new instance for cellDataList
         */
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStream = new FileInputStream(fileName);
            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printToConsole(cellDataList);
    }

    private void printToConsole(List cellDataList) {
        registrotmL = new ArrayList<registrotmC>();
        registrotmC registrotm = null;
        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            registrotm = new registrotmC();
            for (int j = 0; j < cellTempList.size(); j++) {
                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
                String stringCellValue = hssfCell.toString();

                switch (j) {
                    case 0:
                        registrotm.setCpersonal(stringCellValue);
                        break;
                    case 1:
                        registrotm.setCarrera(stringCellValue);
                        break;

                }

                System.out.print(stringCellValue + "\t");
            }
            registrotmL.add(registrotm);
            System.out.println();
        }
    }

    /**
     * @return the registrotmL
     */
    public List<registrotmC> getRegistrotmL() {
        return registrotmL;
    }

    /**
     * @param registrotmL the registrotmL to set
     */
    public void setRegistrotmL(List<registrotmC> registrotmL) {
        this.registrotmL = registrotmL;
    }

}
