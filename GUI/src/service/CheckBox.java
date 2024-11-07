package service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CheckBox extends JFrame 
{
    private JTable table;
    private DefaultTableModel tableModel;
    private JCheckBox[] filterCheckBox;
    private List<String[]> csvData;

    public CheckBox()
    {
        setTitle("오늘 점심 뭐 먹지");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        csvData = readCsvData("ExcelRead.csv");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("이름");
        tableModel.addColumn("가격");
        tableModel.addColumn("위치");

        table = new JTable(tableModel);

        filterCheckBox = new JCheckBox[7];
        filterCheckBox[0] = new JCheckBox("면류");
        filterCheckBox[1] = new JCheckBox("밥류");
        filterCheckBox[2] = new JCheckBox("기타");
        
        filterCheckBox[3] = new JCheckBox("한식");
        filterCheckBox[4] = new JCheckBox("일식");
        filterCheckBox[5] = new JCheckBox("중식");
        filterCheckBox[6] = new JCheckBox("양식");
        
        for(int i = 0; i < filterCheckBox.length; i++)
        {
        	filterCheckBox[i].addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    applyFilter();
                }
            });
        }              
       
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton loadButton = new JButton("체크박스 초기화");
        loadButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loadData();
            }
        });

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(int i = 0; i < 3; i++)
        {  
        	row1.add(filterCheckBox[i]);	
        }
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(int i = 3; i < 6; i++)
        {
        	row2.add(filterCheckBox[i]);	
        }
        row1.add(loadButton);
        northPanel.add(row1);
        northPanel.add(row2);
        loadData();
        
        add(northPanel, BorderLayout.NORTH);
    }

    private List<String[]> readCsvData(String filePath) 
    {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                data.add(values);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return data;
    }

    private void loadData() 
    {
        tableModel.setRowCount(0); 
        for (String[] row : csvData) 
        {
            tableModel.addRow(row);
        }
        for(int i = 0; i < filterCheckBox.length; i++)
        {
        	filterCheckBox[i].setSelected(false);
        }
        
       
    }

    private void applyFilter()
    {
    	tableModel.setRowCount(0); 
    	
    	for (String[] row : csvData) 
        {
    		tableModel.addRow(row);
    		
    		for(int i = 0; i < filterCheckBox.length; i++)
          	{
        		 if (filterCheckBox[i].isSelected()) 
                 {	    			
        			 if (row.length >= 0 && !filterCheckBox[i].getText().equals(row[3]) && i < 3)
     	             {
     	            	tableModel.removeRow(tableModel.getRowCount() - 1);
     	            	break;
     	             }
        			 else if (row.length >= 0 && !filterCheckBox[i].getText().equals(row[4]) && i >= 3 && i < 6 )
     	             {
     	            	tableModel.removeRow(tableModel.getRowCount() - 1);
     	            	break;
     	             }
                 }
          	}     
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new CheckBox().setVisible(true);
            }
        });
    }
}