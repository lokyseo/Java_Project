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
        // 아이콘 경로 확인 후 설정
        try {
            ImageIcon img = new ImageIcon(getClass().getResource("/resources/icon.png")); // 경로 확인
            setIconImage(img.getImage());
        } catch (Exception e) {
            System.out.println("아이콘 로드 실패: " + e.getMessage());
        }

        csvData = readCsvData("ExcelRead.csv");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("이름");
        tableModel.addColumn("가격");
        tableModel.addColumn("위치");

        table = new JTable(tableModel);

        filterCheckBox = new JCheckBox[12];
        filterCheckBox[0] = new JCheckBox("면류");
        filterCheckBox[1] = new JCheckBox("밥류");
        filterCheckBox[2] = new JCheckBox("기타");
        
        filterCheckBox[3] = new JCheckBox("한식");
        filterCheckBox[4] = new JCheckBox("일식");
        filterCheckBox[5] = new JCheckBox("중식");
        filterCheckBox[6] = new JCheckBox("양식");
        
        filterCheckBox[7] = new JCheckBox("교내");
        filterCheckBox[8] = new JCheckBox("교외");
        
        filterCheckBox[9] = new JCheckBox("5000원 이하");
        filterCheckBox[10] = new JCheckBox("5000원 ~ 10000원");
        filterCheckBox[11] = new JCheckBox("10000원 이상");
        
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

        JPanel northPanel = new JPanel(new GridLayout(4, 1));
        
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(int i = 0; i < 3; i++)
        {  
        	row1.add(filterCheckBox[i]);	
        }
        
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(int i = 3; i < 7; i++)
        {
        	row2.add(filterCheckBox[i]);	
        }
        
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(int i = 7; i < 9; i++)
        {
        	row3.add(filterCheckBox[i]);	
        }
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(int i = 9; i < 12; i++)
        {
        	row4.add(filterCheckBox[i]);	
        }
        
        row1.add(loadButton);
        northPanel.add(row1);
        northPanel.add(row2);
        northPanel.add(row3);
        northPanel.add(row4);
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
    	boolean row_Bool[] = new boolean[4];
    	boolean row_BoolChecked[] = new boolean[4];
    	
    	for (String[] row : csvData) 
        {
    		tableModel.addRow(row);
    		for(int i = 0; i < row_Bool.length; i++)
        	{
        		row_Bool[i] = false;
        		row_BoolChecked[i] = false;
        	}
        	
    		for(int i = 0; i < filterCheckBox.length; i++)
          	{
        		 if (filterCheckBox[i].isSelected() && row.length >= 0) 
                 {	    
        			 if(i < 3)// 1번째 줄 체크박스
        			 {
        				 row_BoolChecked[0] = true;
        				 if (filterCheckBox[i].getText().equals(row[3]))
         	             {         	            	
        					 row_Bool[0] = true;
         	             }
            	  				 
        			 }
        			 
        			 if(i >= 3 && i < 7 )// 2번째 줄 체크박스
        			 {
        				 row_BoolChecked[1] = true;
        				 
        				 if (filterCheckBox[i].getText().equals(row[4]))
         	             {           	
        					 row_Bool[1] = true;
         	             }   		
        			 }    
        			 
        			 if(i >= 7 && i < 9)
        			 {
        				 row_BoolChecked[2] = true;
        				 
        				 if (filterCheckBox[i].getText().equals(row[2]))
         	             {           	
        					 row_Bool[2] = true;
         	             }   		
        			 }
        			 
        			 if(i >= 9 && i < 12)
        			 {
        				 row_BoolChecked[3] = true;
        				 
        				 switch(i)
        				 {
        				 case 9 :
        					 if (Integer.parseInt(row[1]) <= 5000)
             	             {           	
            					 row_Bool[3] = true;
             	             }   
        					 break;
        				 case 10 :
        					 if (Integer.parseInt(row[1]) > 5000 && Integer.parseInt(row[1]) < 10000)
             	             {           	
            					 row_Bool[3] = true;
             	             }   
        					 break;
        				 case 11 :
        					 if (Integer.parseInt(row[1]) >= 10000)
             	             {           	
            					 row_Bool[3] = true;
             	             }   
        					 break;
        				 }
        				 
        						
        			 }
        			
                 }
          	}     
    		
    		
    		for(int i = 0; i < row_BoolChecked.length; i++)
    		{
    			if(row_BoolChecked[i] == false) //한개도 체크가 안되어 있을 때
    			{
    				row_Bool[i]= true;
    			}
    		}
    		
    		boolean allChecked_Bool = areAllTrue(row_Bool); //전부 트루인가
    		
    		if(allChecked_Bool == false)
    		{
    			tableModel.removeRow(tableModel.getRowCount() - 1);
    		}
    		
        }
    }

    
    public boolean areAllTrue(boolean[] arr)
    {
        for (boolean value : arr)
        {
            if (!value)
            {
                return false; 
            }
        }
        return true; 
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