package com.aigenvector.dawdle.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.aigenvector.dawdle.ExperimentManager;

public class ExportListener implements ActionListener {
	public ExportListener() {
		// Stubbed
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Excel File", "xls", "xlsx"));
		int result = fc.showOpenDialog(null);
		File file = null;
		FileOutputStream output = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			if (file != null) {
				try {
					if(!file.exists()) {
						file.createNewFile();
					}
					output = new FileOutputStream(file);
					Workbook wb = new HSSFWorkbook();
					Sheet s = wb.createSheet();
					Row r = null;
					Cell c = null;
					
					CellStyle cellStyle = wb.createCellStyle();
					CreationHelper createHelper = wb.getCreationHelper();
					cellStyle.setDataFormat(
					    createHelper.createDataFormat().getFormat("MM/DD/YYYY HH:MM:SS.000"));
					
					int rowCounter = 0;
					for (Long dTime : ExperimentManager.getInstance().getDistractionTimes()) {
						r = s.createRow(rowCounter++);
						c = r.createCell(0);
						c.setCellValue(new Date(dTime));
						c.setCellStyle(cellStyle);
					}
					s.autoSizeColumn(0);
					wb.write(output);
					output.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
