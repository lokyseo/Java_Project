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
        setTitle("체크박스로 분류");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        csvData = readCsvData("ExcelRead.csv");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("이름");
        tableModel.addColumn("가격");
        tableModel.addColumn("위치");

        table = new JTable(tableModel);

        filterCheckBox = new JCheckBox[1];
        filterCheckBox[0] = new JCheckBox("라면");
        
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

        JPanel panel = new JPanel();
        for(int i = 0; i < filterCheckBox.length; i++)
        {
        	panel.add(filterCheckBox[i]);	
        }
        
        panel.add(loadButton);
        add(panel, BorderLayout.NORTH);
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
    }

    private void applyFilter()
    {
        tableModel.setRowCount(0); 
        for (String[] row : csvData) 
        {
        	for(int i = 0; i <filterCheckBox.length; i++)
        	{
        		if (filterCheckBox[i].isSelected()) 
                {
                    if (row.length >= 0 && filterCheckBox[i].getText().equals(row[0])) 
                    {
                        tableModel.addRow(row);
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