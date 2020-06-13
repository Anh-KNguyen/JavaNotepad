package notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad implements ActionListener {

    private static JFrame frame;
    public static JTextArea jtextArea;
    private static JScrollPane scrollPane;
    private static ImageIcon icon;
    private static Image image;
    public static JFileChooser jfcOpen;
    public static JFileChooser jfcSave;
    public static FileNameExtensionFilter javaFilter, txtFilter;
    private static File file;
    private static int countLine;
    private static int row = 0;
    private static JLabel rowLab;

    public static Font initialFont;

    public static JDialog dlg;
    
    public static JPopupMenu popUp;
    
    public static boolean change = true;
    
    public static JButton dCancelLab;
    public static JButton dDontSaveLab;
    public static JButton dSaveLab;
    public static JDialog jDialog1;
    public static JLabel saveChangeLab;
    
    public static JRadioButton DownRBtn;
    public static JButton cancelBtn;
    public static JPanel directionPan;
    public static JDialog findDialog;
    public static JLabel findLab;
    public static JButton findNextBtn;
    public static JTextField findTF;
    public static JCheckBox matchCaseCB;
    public static JRadioButton upRBtn;


    Notepad() {
        frame = new JFrame("Untiled Notepad");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtextArea = new JTextArea();
        jtextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        jtextArea.setForeground(Color.BLACK);
        jtextArea.setBackground(Color.WHITE);
        
        //listen for changes in text
        jtextArea.getDocument().addDocumentListener(new DocumentListener() {
            
            @Override
            public void insertUpdate(DocumentEvent de) {
                DocumentEvent.EventType type = de.getType();
                if(type.equals(DocumentEvent.EventType.INSERT)) {
                    change = true;
                    jtextArea.update();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                DocumentEvent.EventType type = de.getType();
                if(type.equals(DocumentEvent.EventType.REMOVE)) {
                    change = true;
                    jtextArea.update(null);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                DocumentEvent.EventType type = de.getType();
                if(type.equals(DocumentEvent.EventType.CHANGE)) {
                    change = true;
                    jtextArea.update(null);
                }
            }
      
        });
        
        popUp = new JPopupMenu();
        jtextArea.setComponentPopupMenu(popUp);
        jtextArea.addMouseListener(new popUpTriggerListener());

        icon = new ImageIcon("Notepad.png");
        image = icon.getImage().getScaledInstance(28, 28,Image.SCALE_DEFAULT);

        scrollPane = new JScrollPane(jtextArea);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants
        .VERTICAL_SCROLLBAR_ALWAYS);
        
        file = null;
        
        javaFilter = new FileNameExtensionFilter("Java Files (*.java)", "java");
        txtFilter = new FileNameExtensionFilter("Text Documents (*.txt)", "txt", "text");
        
        jfcOpen = new JFileChooser();
        jfcSave = new JFileChooser();
        
        jfcOpen.setCurrentDirectory(jfcOpen.getCurrentDirectory());
        
        jfcOpen.setFileFilter(javaFilter);
        jfcOpen.setFileFilter(txtFilter);
        
        
        JMenuBar jmb = new JMenuBar();

        //File
        JMenu jmFile = new JMenu("File");

        JMenuItem jmiNew = new JMenuItem("New");
        jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                              Event.CTRL_MASK));

        JMenuItem jmiOpen = new JMenuItem("Open...");
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                              Event.CTRL_MASK));

        JMenuItem jmiSave = new JMenuItem("Save");
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                              Event.CTRL_MASK));

        JMenuItem jmiSaveAs = new JMenuItem("Save As");

        //line separator

        JMenuItem jmiPageSetup = new JMenuItem("Page Setup");
        jmiPageSetup.setEnabled(false);

        JMenuItem jmiPrint = new JMenuItem("Print");
        jmiPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                                Event.CTRL_MASK));
        jmiPrint.setEnabled(false);

        //line separator

        JMenuItem jmiExit = new JMenuItem("Exit");
        

        jmFile.add(jmiNew);
        jmFile.add(jmiOpen);
        jmFile.add(jmiSave);
        jmFile.add(jmiSaveAs);
        //line separator
        jmFile.addSeparator();
        jmFile.add(jmiPageSetup);
        jmFile.add(jmiPrint);
        //line separator
        jmFile.addSeparator();
        jmFile.add(jmiExit);



        //Edit
        JMenu jmEdit = new JMenu("Edit");

        JMenuItem jmiUndo = new JMenuItem("Undo");
        jmiUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                               Event.CTRL_MASK));
        jmiUndo.setEnabled(false);

        //line separator

        JMenuItem jmiCut = new JMenuItem("Cut");
        jmiCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                              Event.CTRL_MASK));

        JMenuItem jmiCopy = new JMenuItem("Copy");
        jmiCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                               Event.CTRL_MASK));

        JMenuItem jmiPaste = new JMenuItem("Paste");
        jmiPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                                Event.CTRL_MASK));

        JMenuItem jmiDelete = new JMenuItem("Delete");

jmiDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));

        //line separator

        JMenuItem jmiFind = new JMenuItem("Find...");
        jmiFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                               Event.CTRL_MASK));

        JMenuItem jmiFindNext = new JMenuItem("Find Next");

jmiFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));

        JMenuItem jmiReplace = new JMenuItem("Replace...");
        jmiReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                                  Event.CTRL_MASK));
        jmiReplace.setEnabled(false);

        JMenuItem jmiGoTo = new JMenuItem("Go To...");
        jmiGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                               Event.CTRL_MASK));

        //line separator

        JMenuItem jmiSelectAll = new JMenuItem("Select All");

jmiSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                            Event.CTRL_MASK));

        JMenuItem jmiTimeDate = new JMenuItem("Time/Date");

jmiTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));


        jmEdit.add(jmiUndo);
        //line separator
        jmEdit.addSeparator();
        jmEdit.add(jmiCut);
        jmEdit.add(jmiCopy);
        jmEdit.add(jmiPaste);
        jmEdit.add(jmiDelete);
        //line separator
        jmEdit.addSeparator();
        jmEdit.add(jmiFind);
        jmEdit.add(jmiFindNext);
        jmEdit.add(jmiReplace);
        jmEdit.add(jmiGoTo);
        //line separator
        jmEdit.addSeparator();
        jmEdit.add(jmiSelectAll);
        jmEdit.add(jmiTimeDate);
        
        
        //popup menu
        JMenuItem jmiUndo_p = new JMenuItem("Undo");
        JMenuItem jmiCut_p = new JMenuItem("Cut");
        JMenuItem jmiCopy_p = new JMenuItem("Copy");
        JMenuItem jmiPaste_p = new JMenuItem("Paste");
        JMenuItem jmiDelete_p = new JMenuItem("Delete");
        JMenuItem jmiSelectAll_p = new JMenuItem("Select All");
        
        jmiUndo_p.setEnabled(false);      


        //Format
        JMenu jmFormat = new JMenu("Format");
        
        JCheckBoxMenuItem jmiWordWrap = new JCheckBoxMenuItem("Word Wrap");
        
        jmiWordWrap.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent le) {
                if(jmiWordWrap.isSelected()) {
                    jtextArea.setLineWrap(true);
                    jtextArea.setWrapStyleWord(true);
                }
                else {
                    jtextArea.setLineWrap(false);
                    jtextArea.setWrapStyleWord(false);
                }
            }
        });
   
        JMenuItem jmiFont = new JMenuItem("Font");
        
        JMenu jmColor = new JMenu("Color");
        JMenuItem jmiForeground = new JMenuItem("Foreground");
        JMenuItem jmiBackground = new JMenuItem("Background");
        
        jmColor.add(jmiForeground);
        jmColor.add(jmiBackground);
        
        jmFormat.add(jmiWordWrap);
        jmFormat.add(jmiFont);
        jmFormat.add(jmColor);

        //View
        JMenu jmView = new JMenu("View");
        JCheckBoxMenuItem jmiStatusBar = new JCheckBoxMenuItem("Status Bar");
        jmiStatusBar.setEnabled(false);
        jmView.add(jmiStatusBar);


        //Help
        JMenu jmExit = new JMenu("Help");
        JMenuItem jmiViewHelp = new JMenuItem("View Help");
        jmiViewHelp.setEnabled(false);
        
        JMenuItem jmiAbout = new JMenuItem("About");

        jmExit.add(jmiViewHelp);
        jmExit.add(jmiAbout);

        jmb.add(jmFile);
        jmb.add(jmEdit);
        jmb.add(jmFormat);
        jmb.add(jmView);
        jmb.add(jmExit);
        
        
        jmiNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {    
                
                    if (change == true) {
                        saveDialog();
                        jtextArea.setText("");
                        frame.setTitle("Untitled Notepad");
                    }

                    else if (change == false) {
                        jtextArea.setText("");
                        frame.setTitle("Untitled Notepad");
                        file = null;
                        jDialog1.dispose();
                    }

            }
        });


        jmiOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                
                //display the file chooser - open option
                int result = jfcOpen.showOpenDialog(null);

                //if(result == JFileChooser.APPROVE_OPTION)
                    file = jfcOpen.getSelectedFile();
                    frame.setTitle(jfcOpen.getName(file));
                    
                
                try {
                    FileReader reader = new FileReader(file);
                    if(file.getName().endsWith(".txt") ||
                                 file.getName().endsWith(".java")) {
                        jtextArea.read(reader, file);
                    }
                } catch (FileNotFoundException ex) {
Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
           
        });
        
        jmiSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                //file doesn't exists
                if(file == null) {
                    
                    int result = jfcSave.showSaveDialog(null);
                    
                    if(result == JFileChooser.APPROVE_OPTION) {
                    
                    try {
                        
                        file = new File(jfcSave.getSelectedFile().getPath());
                        frame.setTitle(jfcSave.getName(file));
                        
                        if(file.exists()) {
                            
                            int response = JOptionPane.showConfirmDialog(frame,
                            "<html>" + jfcSave.getSelectedFile().getName() +
                            " already exists. <br/> Do you want to replace it?",
                            "Confirm Save As", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                            
                            if(response == JOptionPane.YES_OPTION) {
                                //to write to file
                                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                                 
                                //write contents of text area to file
                                out.write(jtextArea.getText());
                                out.close();
                            }
                            
                            else {
                                jtextArea.setText("");
                                file = null; 
                            }
                            
                        }
                        
                        //if file doesnt exist
                        else {
                        //to write to file
                        BufferedWriter out = new BufferedWriter(new FileWriter(file));
                        //write contents of text area to file
                        out.write(jtextArea.getText()); 
                        out.close();
                        
                        frame.setTitle(jfcSave.getName(file));
                        
                        }
                    }
                    
                    catch (IOException ex) {
                        
                    }
                    
                    
                }
                    
                }
                
                //if file exists
                else if (file != null) {
                    
                    try {
                        //to write to file
                        BufferedWriter out = new BufferedWriter(new FileWriter(file));
                        //write contents of text area to file
                        out.write(jtextArea.getText()); 
                        out.close();
                        
                        frame.setTitle(jfcSave.getName(file));
                    }
                    
                    catch (IOException ex) {
                        
                    }
                }
           
                
                
            }
        });
        
        
        jmiSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                
                saveAs();
            }
        });
        
        
        jmiExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                if (change == false) {
                    System.exit(0);
                }
                else {
                    saveDialog();
                    System.exit(0);
                    
                }
            }
        });
        
        
        
        
        jmiCut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.cut();
            }
        });
        
        //popup cut
        jmiCut_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.cut();
            }
        });
        
        jmiCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.copy();
            }
        });
        
        //popup copy
        jmiCopy_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.copy();
            }
        });
        
        jmiPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.paste();
            }
        });
        
        //popup paste
        jmiPaste_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.paste();
            }
        });
        
        jmiDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.replaceSelection("");
            }
        });
        
        //popup delete
        jmiDelete_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.replaceSelection("");
            }
        });
        
        jmiFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                
                findDialog();
            }
        });

        jmiGoTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {

             countLine = jtextArea.getLineCount();

             JDialog dlg = new JDialog(frame, "Go To Line");

             JPanel labelPan = new JPanel();
             JLabel lineLab = new JLabel("Line number:");
             labelPan.add(lineLab);

             JPanel textPan = new JPanel();
             JTextField lineTF = new JTextField(25);
             textPan.add(lineTF);

             JPanel btnPan = new JPanel();
             JButton GoToBtn = new JButton("Go To");
             GoToBtn.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent le) {

                     int userLine = Integer.parseInt(lineTF.getText());

                     String[] lineNum= jtextArea.getText().split("\n");
                     int value =lineNum[userLine].length();

System.out.println(System.lineSeparator().length());

                     try {

jtextArea.setCaretPosition(jtextArea.getLineStartOffset(userLine-1));
                     } catch (BadLocationException ex) {

Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                     }

                     dlg.dispose();

                 }
             });


             JButton GoToCancelBtn = new JButton("Cancel");
             GoToCancelBtn.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent le) {
                     dlg.dispose();
                 }
             });



             btnPan.add(GoToBtn);
             btnPan.add(GoToCancelBtn);


             dlg.add(labelPan, BorderLayout.NORTH);
             dlg.add(textPan, BorderLayout.CENTER);
             dlg.add(btnPan, BorderLayout.SOUTH);

             dlg.setSize(300, 150);
             dlg.setLocationRelativeTo(null);
             dlg.setVisible(true);
         }
        });

        
        jmiSelectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.selectAll();
            }
        });
        
        //popup select all
        jmiSelectAll_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.selectAll();
            }
        });
        
        jmiTimeDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                jtextArea.append(dtf.format(now));
            }
        });
        

        jmiFont.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                JFontChooser.showDialog(frame, initialFont);
                dlg.setVisible(true);
            }
        });
        
        
        jmiForeground.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                Color color = JColorChooser.showDialog(frame, "Choose Color",
                                                        Color.RED);          
                if (color != null)
                    jtextArea.setForeground(color);
           
            }
            
        });
        
        
        jmiBackground.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                Color color = JColorChooser.showDialog(frame, "Choose Color",
                                                        Color.RED);          
                if (color != null)
                    jtextArea.setBackground(color);
           
            }
            
        });


        jmiAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                JOptionPane.showMessageDialog(frame,
                                "<html>Notepad <br/> (c) 2019 A. Nguyen",
                                "About",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon(image));
            }
        });


        jmiExit.addActionListener(this);
        jmiPrint.addActionListener(this);

        popUp.add(jmiUndo_p);
        popUp.add(jmiCut_p);
        popUp.add(jmiCopy_p);
        popUp.add(jmiPaste_p);
        popUp.add(jmiDelete_p);
        popUp.add(jmiSelectAll_p);

        frame.setJMenuBar(jmb);


        rowLab = new JLabel("Ln " + row + ", ");
        rowLab.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(rowLab, BorderLayout.SOUTH);

        frame.add(scrollPane);

        frame.setVisible(true);

    }
    
    public static void saveDialog() {
        jDialog1 = new JDialog(frame, "Notepad");

        jDialog1.setModal(true);
        jDialog1.setResizable(false);
        jDialog1.setSize(350, 180);
        jDialog1.setLayout(new FlowLayout());
        jDialog1.setLocationRelativeTo(null);
        //jDialog1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        dSaveLab = new JButton();
        dDontSaveLab = new JButton();
        dCancelLab = new JButton();
        saveChangeLab = new JLabel();

        dSaveLab.setText("Save");
        dSaveLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                saveAs();
                jDialog1.dispose();
            }
        });

        dDontSaveLab.setText("Don't Save");
        dDontSaveLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jtextArea.setText("");
                file = null;
                jDialog1.dispose();
            }

        });

        dCancelLab.setText("Cancel");
        dCancelLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                jDialog1.dispose();
                
            }
        });

        saveChangeLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveChangeLab.setForeground(new java.awt.Color(65, 105, 225));
        saveChangeLab.setText("Do you want to save changes to Untitled?");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialog1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(dSaveLab)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dDontSaveLab)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dCancelLab))
                                        .addComponent(saveChangeLab, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialog1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(saveChangeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dSaveLab)
                                        .addComponent(dDontSaveLab)
                                        .addComponent(dCancelLab))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog1.setVisible(true);
    }
    
    public static void findDialog() {
        
        findDialog = new javax.swing.JDialog();
        findDialog.setTitle("Find");
        findDialog.setSize(500, 210);
        findDialog.setLocationRelativeTo(null);
        findDialog.setResizable(false);
        findDialog.setModal(true);

        findNextBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        findLab = new javax.swing.JLabel();
        findTF = new javax.swing.JTextField();
        directionPan = new javax.swing.JPanel();
        upRBtn = new javax.swing.JRadioButton();
        DownRBtn = new javax.swing.JRadioButton();
        matchCaseCB = new javax.swing.JCheckBox();

        findNextBtn.setText("Find Next");
        
        findNextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                findNext();
            }
        });
        
        
        
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                findDialog.dispose();
            }
        });
        
        
        findLab.setText("Find what:");

        directionPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Direction"));

        upRBtn.setText("Up");
        upRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        DownRBtn.setText("Down");

        javax.swing.GroupLayout directionPanLayout = new javax.swing.GroupLayout(directionPan);
        directionPan.setLayout(directionPanLayout);
        directionPanLayout.setHorizontalGroup(
            directionPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directionPanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(upRBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DownRBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        directionPanLayout.setVerticalGroup(
            directionPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directionPanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(directionPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upRBtn)
                    .addComponent(DownRBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        matchCaseCB.setText("Match case");

        javax.swing.GroupLayout findDialogLayout = new javax.swing.GroupLayout(findDialog.getContentPane());
        findDialog.getContentPane().setLayout(findDialogLayout);
        findDialogLayout.setHorizontalGroup(
            findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(findDialogLayout.createSequentialGroup()
                        .addComponent(findLab, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findTF, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(findDialogLayout.createSequentialGroup()
                        .addComponent(matchCaseCB)
                        .addGap(96, 96, 96)
                        .addComponent(directionPan, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(findNextBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        findDialogLayout.setVerticalGroup(
            findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findDialogLayout.createSequentialGroup()
                .addGroup(findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(findDialogLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(findLab)
                            .addComponent(findTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(findDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(directionPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(findDialogLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(matchCaseCB))))
                    .addGroup(findDialogLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(findNextBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        findDialog.setVisible(true);


    }

    public static void saveAs() {
        //display file chooser - save option
        int result = jfcSave.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            try {

                file = new File(jfcSave.getSelectedFile().getPath());
                frame.setTitle(jfcSave.getName());

                if (file.exists()) {

                    int response = JOptionPane.showConfirmDialog(frame,
                            "<html>" + jfcSave.getSelectedFile().getName()
                            + " already exists. <br/> Do you want to replace it?",
                            "Confirm Save As", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        //to write to file
                        BufferedWriter out = new BufferedWriter(new FileWriter(file));

                        //write contents of text area to file
                        out.write(jtextArea.getText());
                        out.close();
                        
                        frame.setTitle(jfcSave.getName());
                    } else {

                    }

                } //if file doesnt exist
                else {
                    //to write to file
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    //write contents of text area to file
                    out.write(jtextArea.getText());
                    out.close();
                    
                    frame.setTitle(jfcSave.getName());

                }
            } catch (IOException ex) {

            }

        }
    }
    
    
    public static void findNext() {
        String userLineFind = jtextArea.getText();
    }
    

    public static void main(String[] args) throws FileNotFoundException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Notepad();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet.");

        String command = ae.getActionCommand();

        if (command.equals("Print")) {
            jtextArea.append("\n Printing...");
        }

    }

    public class popUpTriggerListener extends MouseAdapter {

        @Override
            public void mousePressed(MouseEvent me) {
                if(me.isPopupTrigger())
                    popUp.show(me.getComponent(), me.getX(), me.getY());
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                if(me.isPopupTrigger())
                    popUp.show(me.getComponent(), me.getX(), me.getY());
            }
        
    }
        

    
}


