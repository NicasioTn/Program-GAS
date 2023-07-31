import java.awt.Button;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;

import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {

	public static void main(String[] args) {
		LuangeMenu frame = new LuangeMenu();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
class LuangeMenu extends JFrame implements ActionListener{
	
	JButton Program_bt = new JButton("Program");
	JButton About_bt = new JButton("About");
	JLabel Start_lb;	ImageIcon backG;
	JPanel Bg_img = new JPanel(null);
	JLabel bt1_lb;	ImageIcon bt1_img;
	JLabel bt2_lb;	ImageIcon bt2_img;
	JButton Exit_bt = new JButton("X");
	JButton Hide_bt = new JButton("-");
	JLabel Hide_lb;	ImageIcon Hide_img; 
	JLabel Exit_lb;	ImageIcon Exit_img; 
	
	// MyFrame
	LuangeMenu(){
		
		setSize(500, 600);
		setTitle("Menu");
		setLayout(null);
		setUndecorated(true); // Hide bar
		
		// set Size
		Bg_img.setBounds(0, 0, 500, 600);
		Exit_bt.setBounds(450, 0, 50, 50);
		Hide_bt.setBounds(400, 0, 50, 50);
		Program_bt.setBounds(57, 245, 200, 60);
		About_bt.setBounds(268, 245, 200, 60);
		
		// set Image 
		backG = new ImageIcon(this.getClass().getResource("background_3.jpg"));
		Start_lb = new JLabel(backG);
		Start_lb.setBounds(0, 0, 500, 600);
		
		bt1_img = new ImageIcon(this.getClass().getResource("Botton1.jpg"));
		bt1_lb = new JLabel(bt1_img);
		bt1_lb.setBounds(0, 0, 200, 60);
		Program_bt.add(bt1_lb);
		Program_bt.setBackground(new Color(0, 69, 60));
		
		bt2_img = new ImageIcon(this.getClass().getResource("Botton2.jpg"));
		bt2_lb = new JLabel(bt2_img);
		bt2_lb.setBounds(0, 0, 200, 60);
		About_bt.add(bt2_lb);
		About_bt.setBackground(new Color(0, 69, 60));
		
		// Tool of Display
		Hide_bt.setFont(new Font("Tahoma",Font.BOLD,25));
		Hide_bt.setBackground(new Color(192, 250, 254));
		Exit_bt.setFont(new Font("Tahoma",Font.BOLD,20));
		Exit_bt.setBackground(new Color(192, 250, 254));
		Hide_bt.setFocusable(false);
		Exit_bt.setFocusable(false);
		// add Action
		Program_bt.addActionListener(this);
		About_bt.addActionListener(this);
		Exit_bt.addActionListener(this);
		Hide_bt.addActionListener(this);
		
		// add component to panel
		Bg_img.add(Exit_bt);
		Bg_img.add(Hide_bt);
		Bg_img.add(Program_bt);
		Bg_img.add(About_bt);
		Bg_img.add(Start_lb);
		
		add(Bg_img);
	} // Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Program_bt) {
			ProGAS Pgc = new ProGAS();
			Pgc.setLocationRelativeTo(null);
			Pgc.setVisible(true);
			setVisible(false);
		}
		else if(e.getSource() == About_bt) {
			About Ab = new About();
			Ab.setLocationRelativeTo(null);
			Ab.setVisible(true);
			setVisible(false);
		}
		else if(e.getSource() == Hide_bt) {
			setState(JFrame.ICONIFIED); // To minimize a frame
		}
		else if(e.getSource() == Exit_bt) {
			System.exit(0); // Exit Program
		}
	} 
} // Class

class ProGAS extends JFrame implements ActionListener{
	
	/*
	* Read more variables in Class ProGas
	* NameVariable_TypeClass
	* Example 
	* 			Back_bt -- Read Back Button
	*/
	
	int column, row; 
	boolean hasTable = true ;
	boolean isMm = true,cobutton = false;
	String status = "";
	String x[];				
	String value[][] ;		 // file input
	Double deep; 
	Double base[][], All_Gas[][], perCent[][];
	Double ShowAll = 0.0, max = 0.0, min= 0.0, avg = 0.0;
	
	
	// JButton
	JButton Back_bt = new JButton("Exit Program");
	JButton Back_bt2 = new JButton("Back to Menu");
	JButton Op_bt;		JButton Cr_bt;  	// File open, Clear button  
	JButton OK_bt; 		JButton Clr_bt; 	// Set Value OK, Clear button						
	Button  Table_bt[][];
	JButton Show_Prop[] = new JButton [4];
	JButton change_bt;
	// JPanel
	JPanel MainBG_pn = new JPanel();
	JPanel Lef_pn = new JPanel(null);						// left Panel Zone
	JPanel File_pn = new JPanel(null);
	JPanel setValue_pn = new JPanel(null);
	JPanel Value_pn = new JPanel(null);
	JPanel Display_pn = new JPanel(null);
	JPanel DpGas_pn = new JPanel(null);
	JPanel DpDistance_pn = new JPanel(null);
	JPanel Rit_pn = new JPanel();							// Right Panel Zone
	JPanel Table_pn = new JPanel(); 
	JPanel MSLef_pn = new JPanel(null);						// Right below
	JPanel PopRit_pn = new JPanel(null);
	
	
	// JLabel -- Label -- ImageIcon 
	Label All_lb,Hig_lb, Low_lb, Avg_lb;
	JLabel ImgBG_lb; 	ImageIcon BG_img;  	// Add Image Icon 
	Label file_lb;
	Label setVa_lb;		Label Met_lb;		// Line 154 - LLine 156 is Show Text about Properties
	Label Rec_lb; 		Label Text_lb; 		
	JLabel Area;
	Label Topic_Ms;							// Show Text in Display Distance, Area
	JLabel Area_lb;
	JLabel Distance_lb;
	JLabel unit1 = new JLabel("<html>m<sup>3</sup> </html>");
	JLabel unit2 = new JLabel("<html>m<sup>3</sup> </html>");
	JLabel unit3 = new JLabel("<html>m<sup>3</sup> </html>");
	JLabel unit4 = new JLabel("<html>m<sup>3</sup> </html>");
	JLabel unitArea = new JLabel("<html>m<sup>3</sup> </html>");
	JLabel unitDistance = new JLabel("m");
	ImageIcon Logo;
	
	// JTextField
	JTextField src_tf;
	JTextField setVa_tf;
	
	// TextArea
	TextArea Ms_tf;		// Display Status current work
	
	// JMenuItem
	JMenuItem tem1 = new JMenuItem();
	JMenuItem tem2 = new JMenuItem();
	JMenuItem tem3 = new JMenuItem();
	JPopupMenu poppo = new JPopupMenu();
	
	ProGAS(){   
		
		ImageIcon Logo = new ImageIcon(this.getClass().getClassLoader().getResource("Logo.png"));
		// -------------------------- Frame Detail ------------------------------- //
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1320, 745);
		setTitle("Program Gas Calculates");
		setLayout(null);
		setIconImage(Logo.getImage());
		
		// ---------------------------------- Background Image  ---------------------------------- //
		BG_img = new ImageIcon(this.getClass().getResource("Background_Max.jpg"));
		ImgBG_lb = new JLabel(BG_img); 			//-- setBounds after add Image on JLabel -- 
		ImgBG_lb.setBounds(0, 0, 1920, 1080); 
		MainBG_pn.setBounds(0, -5, 1920, 1080);
		MainBG_pn.add(ImgBG_lb);
		
		Lef_pn.setBackground(new Color(4, 194, 196).darker());
		Lef_pn.setBounds(20, 20, 300, 673);
		Rit_pn.setBackground(new Color(4, 194, 196));
		Rit_pn.setBounds(339, 20, 942, 542);
		Rit_pn.setLayout(null);
		
		
		//  -------------------------- Select a file  -----------------------------------
			File_pn.setBackground(new Color(4, 194, 196));  // 215 * 3
			File_pn.setBounds(5, 5, 289, 90);
			file_lb = new Label("File : ");		file_lb.setBounds(5, 7, 25, 30);
			File_pn.add(file_lb);
			src_tf = new JTextField();			src_tf.setBounds(45, 10, 235, 27);
			File_pn.add(src_tf);
			Op_bt = new JButton("Open File");				Op_bt.setBounds(70, 50, 90, 30);
			Op_bt.setBackground(new Color(247, 191, 46));	Op_bt.setFocusable(false);
			File_pn.add(Op_bt);	
			Cr_bt = new JButton("Clear");					Cr_bt.setBounds(170, 50, 90, 30);
			Cr_bt.setBackground(new Color(247, 191, 46));	Cr_bt.setFocusable(false);
			File_pn.add(Cr_bt);
			
			Op_bt.addActionListener(this);
			Cr_bt.addActionListener(this);		
		
		// ------------------------ set value of Liquid ( need input) --------------------------- 
			setValue_pn.setBackground(new Color(4, 194, 196));
			setValue_pn.setBounds(5, 100, 289, 99);
			setVa_lb = new Label("Set Value of Liquid");	setVa_lb.setBounds(100, 0, 390, 25);
			setVa_tf = new JTextField(""); 					setVa_tf.setBounds(25, 29, 190, 27);
			Met_lb = new Label("Meter(M)");					Met_lb.setBounds(225, 29, 200, 30);
			OK_bt = new JButton("OK");						OK_bt.setBounds(25, 59, 90, 36);
			OK_bt.setBackground(Color.yellow);				OK_bt.setFocusable(false);
			Clr_bt = new JButton("Clear");					Clr_bt.setBounds(124, 59, 90, 36);
			Clr_bt.setBackground(Color.yellow);				Clr_bt.setFocusable(false);
			
			OK_bt.addActionListener(this);
			Clr_bt.addActionListener(this);
		
		// -----------------------------------  Properties more Values  --------------------------------------- //
		Value_pn.setBackground(new Color(4, 194, 196));
		Value_pn.setBounds(5, 204, 289, 112);
	
		All_lb = new Label("All Volume          :");
		All_lb.setBounds(5, 5, 290, 25);
		Hig_lb = new Label("Higher Value     :");
		Hig_lb.setBounds(5, 30, 290, 25);
		Low_lb = new Label("Lower Value      :");
		Low_lb.setBounds(5, 55, 290, 25);
		Avg_lb = new Label("Average Value  :");
		Avg_lb.setBounds(5, 80, 290, 25);
		
		unit1.setBounds(265, 3, 20, 25);
		Value_pn.add(unit1);
		unit2.setBounds(265, 30, 20, 25);
		Value_pn.add(unit2);
		unit3.setBounds(265, 55, 20, 25);
		Value_pn.add(unit3);
		unit4.setBounds(265, 80, 20, 25);
		Value_pn.add(unit4);
		
		setValue_pn.add(setVa_lb);	
		Value_pn.add(All_lb); 		
		Value_pn.add(Hig_lb); 		
		Value_pn.add(Low_lb); 		
		Value_pn.add(Avg_lb);
		setValue_pn.add(setVa_tf); 
		setValue_pn.add(Met_lb);	
		setValue_pn.add(OK_bt);		
		setValue_pn.add(Clr_bt);
		
		
 		// ----------------------  Set Size Monitor of GAS and Distance Again ---------------------------
				Display_pn.setBackground(new Color(4, 194, 196));
				Display_pn.setBounds(5,	321, 289, 348);	
																													
				DpGas_pn.setBackground(Color.gray.brighter());				DpGas_pn.setLocation(10, 10); DpGas_pn.setSize(269, 164);  //DpGas_pn.setBounds(10, 10, 269, 164);      
				DpDistance_pn.setBackground(Color.yellow);					DpDistance_pn.setLocation(10, 175); DpDistance_pn.setSize(269, 164);  //DpDistance_pn.setBounds(10, 175, 269, 164);
				
				Area_lb = new JLabel("Area Gas      ");
				Area_lb.setBounds(50, 10, 190, 30);
				Distance_lb = new JLabel("Distance ");
				Distance_lb.setBounds(50, 120, 190, 30);
				
				unitArea.setBounds(220, 10, 50, 30);
				DpGas_pn.add(unitArea);
				unitDistance.setBounds(220, 120, 50, 30);
				DpDistance_pn.add(unitDistance);
				
				DpGas_pn.add(Area_lb);
				DpDistance_pn.add(Distance_lb);
				
				Display_pn.add(DpGas_pn);
				Display_pn.add(DpDistance_pn);
		
		// ---------------------------- Properties left Right ---------------------------------- //
				
				MSLef_pn.setBackground(new Color(4, 194, 196));	
				MSLef_pn.setBounds(339, 575, 358, 118);
				PopRit_pn.setBackground(new Color(4, 194, 196));	
				PopRit_pn.setBounds(714, 575, 358, 118);
		
				// Message Block
				Topic_Ms = new Label("Message");					Topic_Ms.setBounds(2, 2, 50, 20);
				Ms_tf = new TextArea("", 20, 25,
							TextArea.SCROLLBARS_VERTICAL_ONLY);		Ms_tf.setBounds(6, 25, 346, 87);
				MSLef_pn.add(Topic_Ms);
				MSLef_pn.add(Ms_tf);
				
		// --------------------- Properties Show Color on table, word of Color ----------------------------- // 
				for(int i = 1 ;i <= 4;i++) {
					Rec_lb = new Label();
					Rec_lb.setSize(35, 35);
						if(i == 1) {
							Rec_lb.setBackground(Color.RED);
							Rec_lb.setLocation(19, 20);
							Text_lb = new Label("No Gas");
							Text_lb.setBounds(64, 12, 100, 50); // +45 -8 only 1 and 3
						}
						else if (i == 2) {
							Rec_lb.setBackground(Color.GREEN);
							Rec_lb.setLocation(19, 65);
							Text_lb = new Label("More Gas");
							Text_lb.setBounds(64, 58, 100, 50); // +45 -7 only 2 and 4
						}
						else if (i == 3) {
							Rec_lb.setBackground(Color.YELLOW);
							Rec_lb.setLocation(170, 20);
							Text_lb = new Label("Gas Medium");
							Text_lb.setBounds(215, 12, 100, 50);
						}
						else if (i == 4) {
							Rec_lb.setBackground(Color.ORANGE);
							Rec_lb.setLocation(170, 65);
							Text_lb = new Label("Gas quite a few");
							Text_lb.setBounds(215, 58, 92, 50);
						}
					PopRit_pn.add(Text_lb);
					PopRit_pn.add(Rec_lb);
				}
		
		// --------------------------------- Button ------------------------------------------------- //
			Back_bt.setBounds(1088,575,192,53);
			Back_bt.setBackground(new Color(247, 191, 46));
			Back_bt.setFocusable(false);
			
			Back_bt2.setBounds(1088,641,192,52);
			Back_bt2.setBackground(new Color(247, 191, 46));
			Back_bt2.setFocusable(false);
			
			Back_bt.addActionListener(this);
			Back_bt2.addActionListener(this);
								
		// Add into Panel
			Lef_pn.add(File_pn);
			Lef_pn.add(Value_pn);
			Lef_pn.add(setValue_pn);
			Lef_pn.add(Display_pn);
			
			
		// Add EveryThings on Frame
			add(Lef_pn);	add(Rit_pn);
			add(MSLef_pn);	add(PopRit_pn);
			add(Back_bt);	add(Back_bt2);		
		
		
			add(MainBG_pn);
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//  ---------------------------- Set Date + Pattern ------------------------------
		LocalDateTime myDate_obj = LocalDateTime.now();
		DateTimeFormatter myFormate_obj = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss");
		String formattedDate = myDate_obj.format(myFormate_obj);
		String pattern = "#,###,###,###.00";
		String perCent_pattern = "###.00";
		
		DecimalFormat Decfor = new DecimalFormat(pattern);
		DecimalFormat Dec_float = new DecimalFormat(perCent_pattern);
		
		String text = "Text Documents";
		
		if(e.getSource() == Back_bt2) {
			LuangeMenu atMenu = new LuangeMenu();
			atMenu.setLocationRelativeTo(null);
			atMenu.setVisible(true);
			setVisible(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		} 
	//  ------------------------------------ Open file ----------------------------------- 
		if(e.getSource() == Op_bt) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\evilm\\Desktop"));
			// ----------- import only file .txt ----------------
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(text, "txt"));
			fileChooser.setAcceptAllFileFilterUsed(true);
			
			int response = fileChooser.showSaveDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());	
					String scr_now = file.getPath();	//System.out.println(file);
					src_tf.setText(scr_now);			// Show Source from Select
					
					try {
						BufferedReader input = new BufferedReader(new FileReader(scr_now));
							for (int m = 1; ; m++)  // Row 
							{	
								String data = input.readLine();
								if(data == null) 
									break; 
								x = data.split(" "); 			// column
								column = x.length ; row = m;	// Count Column, Row
							}
							Table_pn.setLayout(new GridLayout(row, column));
							Table_bt = new Button[row][column];
							value = new String[row][column];
							
							// -------------------- Read and Keep Data in Store -------------------------
							try {
									BufferedReader data = new BufferedReader(new FileReader(scr_now));
									for (int i = 0; ; i++)
									{	
										String a = data.readLine();
										if(a == null)
											break; 
										x = a.split(" ");
										for(int j = 0;	j < column ;j++) 
										{
											value[i][j] = x[j];
											System.out.print(value[i][j] + "\t");
										}
										System.out.print("\n");
									}
							}
							catch (FileNotFoundException ex) {
									System.out.println("File Not Found !!");
							} 
							catch(IOException ex) {
									System.out.println("IOException !!");
							}
							
						} 
					catch (FileNotFoundException ex) {
							System.out.println("File Not Found !!");
					} 
					catch(IOException ex) {
							System.out.println("IOException !!");
					}
					
					status += " <<< " + formattedDate + " >>> " + "You press Open File  " + "\n"; 
					Ms_tf.setText(status);
				}
		}
		
		//  ----------------------- Clear Source file -------------------------------- 
 		else if (e.getSource() == Cr_bt) {
			
 			
			// -------------- CASE Source File is Empty -----------------------------
			if(src_tf.getText().compareToIgnoreCase("") == 0) {
				status += " <<< " + formattedDate + " >>> " + "File path is Empty  " + "\n";
				Ms_tf.setText(status);
			}
			else {
				src_tf.setText("");			
				status += " <<< " + formattedDate + " >>> " + "You press Clear  " + "\n"; 
				Ms_tf.setText(status);
			}
		}
		else if (e.getSource() == OK_bt) {
			
			//  -----  Catch Exception -- Case User do it Error !  -----
			if(src_tf.getText().compareToIgnoreCase("") == 0)
			{
				status += " <<< " + formattedDate + " >>> " + "You forgot select file !!!  " + "\n"; 
				Ms_tf.setText(status);
			}
			else if(setVa_tf.getText().compareToIgnoreCase("") == 0)
			{
				status += " <<< " + formattedDate + " >>> " + "Value is Empty \n"
						+ "ProGram is not Calculate !!!  " + "\n"; 
				Ms_tf.setText(status);
			}
			else
			{
				if(hasTable == false)
				{
					status += " <<< " + formattedDate + " >>> " + "Please press Back to menu \nReset Program for new Caculate  " + "\n"; 
					Ms_tf.setText(status);
				}
				else  // -----  hasTable == true ----- 
				{
					deep = Double.parseDouble(setVa_tf.getText());
					base = new Double [row][column];
					All_Gas = new Double [row][column];
					perCent = new Double [row][column];
					double te;
					
					for(int i = 0 ;i < row ; i++)
					{
						for(int j = 0 ;j < column;j++)
						{
							base[i][j] = Double.parseDouble(value[i][j]);
							te = (deep - (base[i][j] - 200));
							if(te > 200.0)
							{
								te = 200.0;
							}
							else if (te < 0.0)
							{
								te = 0.0;
							}
							//System.out.print(deep - (base[i][j] - 200)+"\t");
							
							All_Gas[i][j] = (150 * 150 * te);
							if(i == 0 && j == 0){
								min = max = All_Gas[0][0];
							}
							ShowAll += All_Gas[i][j];
							// ----- minimum max --------
							if(max < All_Gas[i][j]  ) {
								max = All_Gas[i][j];
							}
							if( min > All_Gas[i][j]  ) {
								min = All_Gas[i][j];
							}
							System.out.println(All_Gas[i][j]);
							perCent[i][j] = (deep -(base[i][j] - 200)) * 0.5;
							if(perCent[i][j] > 100.0)
								perCent[i][j] = 100.0;
							if(perCent[i][j]< 0.0)
								perCent[i][j] = 0.0;
							
							System.out.println(perCent[i][j]);
						}
					}
					Rit_pn.add(Table_pn);
					Rit_pn.validate(); 						// Update might do Program Slow
					avg = (ShowAll / (row*column));
					System.out.print("\nAll : " + ShowAll
							+ "\nMin : " + min
							+ "\nAvg : " + avg
							+ "\nMax : "+ max 
							+ "\n");
					
					All_lb.setText("All Volume         :   " + Decfor.format(ShowAll));
					Hig_lb.setText("Higher Value     :    " + Decfor.format(max));
					Low_lb.setText("Lower Value      :   " + Decfor.format(min));
					Avg_lb.setText("Average Value  :    " + Decfor.format(avg));
					Table_pn.setBackground(new Color(230, 230, 230));
					Table_pn.setBounds(0, 0, 942, 542);
					
					for(int i = 0 ;i < row ; i++)
					{
						for(int j = 0 ;j < column ; j++)
						{
							String Per = Double.toString(perCent[i][j]);	
							Font font = new Font("settings",Font.TYPE1_FONT,12);
							Table_bt[i][j] = new Button(Per+"%");
							Table_bt[i][j].setFont(font);
							
							if(perCent[i][j] > 60.0) {
								
								Table_bt[i][j].setBackground(Color.green);
								Item(i,j,Color.green);
								Item(i,j,Color.green,true);
								
							}	else if(perCent[i][j] >= 50.0 && perCent[i][j] < 60.0  ) {
								
								Table_bt[i][j].setBackground(Color.yellow);
								Item(i,j,Color.yellow);
								Item(i,j,Color.yellow,true);
								
							}	else if(perCent[i][j] < 50.0 && perCent[i][j] > 0.0) {
								
								Table_bt[i][j].setBackground(Color.orange);
								Item(i,j,Color.orange);
								Item(i,j,Color.orange,true);
								
							}	else  if(perCent[i][j] == 0.0) {
								
								Table_bt[i][j].setBackground(Color.red);
								Item(i,j,Color.red);
								Item(i,j,Color.red,true);
							}
							
							Per = Double.toString(perCent[i][j]);							
							Font font1 = new Font("",Font.LAYOUT_LEFT_TO_RIGHT,10);
							Table_bt[i][j].setFont(font1);
							Table_bt[i][j].setLabel(Per + "%");
							Table_pn.add(Table_bt[i][j]);
						}
					}
					Rit_pn.add(Table_pn);
					Rit_pn.validate();
					this.hasTable = false;  			// Created Table Success
					status += " <<< " + formattedDate + " >>> " + "You press Ok  " + "\n"; 
					Ms_tf.setText(status);
				}
				
			}
			
		}
	//  --------------------------------- Clear Source file -------------------------------- 
		else if (e.getSource() == Clr_bt)
		{
			if(setVa_tf.getText().compareToIgnoreCase("") == 0) 
			{
				status += " <<< " + formattedDate + " >>> " + "Please Enter Value  " + "\n";
				Ms_tf.setText(status);
			}
			else 
			{
				setVa_tf.setText("");
				status += " <<< " + formattedDate + " >>> " + "You press Clear Set Value " + "\n";
				Ms_tf.setText(status);
			}
		}
	//  ------------------------------- Clear Message --------------------------------
		else if(e.getSource() == Back_bt)
		{
			int response_yes = JOptionPane.showConfirmDialog(null, 
						"Do you Want to Exite Program ?", "Alert Message",JOptionPane.YES_NO_OPTION);
			if(response_yes == JOptionPane.YES_OPTION)
				System.exit(0);
			
		}
	} // Constructor
	void Item	(int i,int j,Color color)
	{
			Table_bt[i][j].addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub	
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					Table_bt[i][j].setBackground(color);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					Table_bt[i][j].setBackground(color.white);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				}
			});
	} // method
	void Item (int i,int j,Color color,Boolean cobutton)
	{
			Table_bt[i][j].addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					Table_bt[i][j].setBackground(color); 
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
						if(cobutton == true)
						{
							Table_bt[i][j].setBackground(color.white);
							double top = base[i][j]-200;
							if(top < 0) {
								top = 0;
							}
						
							String Top = "Top Horizon : "+ (top)+ " m.";
							String Fliud = "Fluid Contact : "+ (deep)+" m.";
							String Base = "Base Horizon : "+ (base[i][j])+" m.";
						
							tem1.setText(Top);
							tem2.setText(Fliud);
							tem3.setText(Base);
							poppo.add(tem1);
							poppo.add(tem2);
							poppo.add(tem3);
							
							poppo.show(Table_bt[i][j],e.getX()+25,e.getY()+20);	
							
						}					
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
					String Dis_play = "###";
					DecimalFormat per_int = new DecimalFormat(Dis_play);
					double top = base[i][j]-200;
					if(top < 0) {
						top = 0;
					}
					if(cobutton == isMm) {
						
						
						String xc = "Gas : "+ (All_Gas[i][j]) +" m^3"+"\n"
								+"Deep: " + (deep)+ " m." + "\n"
								+"Percent : " + (perCent[i][j]) + " % " +"\n"
								+"Top Horizon : " +(top) + " m." + "\n"
								+"Fluid Contact : " + (deep) + " m." +"\n"
								+"Base Horizon : " + (base[i][j]) + " m.";
						
						
					int response_yes = JOptionPane.showConfirmDialog(null, xc, "Detail",JOptionPane.INFORMATION_MESSAGE);
						if(response_yes == JOptionPane.YES_OPTION)
						{
							Area_lb.setText("Area GAS : " + All_Gas[i][j]);
							double Dis = (base[i][j] - deep);
							if(Dis < 0.0)
							{
								Dis = 0.0;
							}
							else if (Dis > 200.0 )
							{
								Dis = 200.0;
							}
							Distance_lb.setText("Distance : " + Dis );
							
							if(perCent[i][j] == 100.0)															
							{   
								int a = 164 + 130;
								DpGas_pn.setBounds(10, 10,269, a);
								DpDistance_pn.setBounds(10, (a+15), 269, (164-135));
								Area_lb.setBounds(50, 10, 190, 30);
								Distance_lb.setBounds(50, 1, 190, 30);
								unitDistance.setBounds(220, 1, 50, 30);
								
							}
							else if(perCent[i][j] < 100.0 && perCent[i][j] >= 80 )								 
							{
								int a = 164 + 80;
								DpGas_pn.setBounds(10, 10,269, a);
								DpDistance_pn.setBounds(10, (a+15), 269, (164-85));
								Distance_lb.setBounds(50, 5, 190, 30);
								unitDistance.setBounds(220, 5, 50, 30);
							}
							else if(perCent[i][j] < 80 && perCent[i][j] >= 60 ) 
							{
								int a = 164 + 35;
								DpGas_pn.setBounds(10, 10,269, a);
								DpDistance_pn.setBounds(10, (a+15), 269, (164 - 40));
								Distance_lb.setBounds(50, 5, 190, 30);
								unitDistance.setBounds(220, 5, 50, 30);
							}
							else if(perCent[i][j] < 60 && perCent[i][j] >= 40 ) 
							{
								int a = 164 - 40;
								DpGas_pn.setBounds(10, 10,269, a);
								DpDistance_pn.setBounds(10, (a+15), 269, (164 + 35));
								Distance_lb.setBounds(50, 5, 190, 30);
								unitDistance.setBounds(220, 5, 50, 30);
							}
							else if(perCent[i][j] < 40 && perCent[i][j] >= 20) 
							{
								int a = 164 - 110;
								DpGas_pn.setBounds(10, 10,269, a);
								DpDistance_pn.setBounds(10, (a+15), 269, (164 + 105));
								Distance_lb.setBounds(50, 5, 190, 30);
								unitDistance.setBounds(220, 5, 50, 30);
							}
							else if(perCent[i][j] < 20 && perCent[i][j] >= 0 ) 
							{
								int a = 164 - 130;
								DpGas_pn.setBounds(10, 10,269, a);
								DpDistance_pn.setBounds(10, (a+15), 269, (164 + 125));
								Area_lb.setBounds(50, 5, 190, 30);
								unitArea.setBounds(220, 5, 50, 30);
								Distance_lb.setBounds(50, 5, 190, 30);
								unitDistance.setBounds(220, 5, 50, 30);
							}

						}
					} // if	
					Table_bt[i][j].setBackground(color);	
				} // Override mouseClicked
			}); // add mouseListener
		} // Method
	
	
	
	
	}	// Class	
		

class About extends JFrame implements ActionListener{
	
	JButton Back_bt = new JButton("Back");
	JLabel Bg_ln ;
	ImageIcon Bg_img;
	
	About(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 400);
		setTitle("About");
		setLayout(null);
		setUndecorated(true);
		
		Back_bt.setBounds(250,300,100,40);
		Back_bt.setBackground(Color.yellow);
		Back_bt.setFocusable(false);
		Back_bt.addActionListener(this);
		
		Bg_img = new ImageIcon(this.getClass().getResource("About_Bg.jpg"));
		Bg_ln = new JLabel(Bg_img);
		Bg_ln.setBounds(0, 0, 600, 400);
		
		add(Back_bt);
		add(Bg_ln);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Back_bt) {
			LuangeMenu atMenu = new LuangeMenu();
			atMenu.setLocationRelativeTo(null);
			atMenu.setVisible(true);
			setVisible(false);
		}
	}
}
