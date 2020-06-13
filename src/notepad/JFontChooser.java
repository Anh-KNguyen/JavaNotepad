package notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static notepad.Notepad.dlg;
import static notepad.Notepad.jtextArea;

public class JFontChooser extends Notepad {
    
    public static Font font;
    public static javax.swing.JButton cancel;
    public static javax.swing.JLabel fontLab;
    public static javax.swing.JList<String> fontList;
    public static javax.swing.JScrollPane fontScrlp;
    public static javax.swing.JTextField fontTF;
    public static javax.swing.JButton ok;
    public static javax.swing.JLabel sampleLab;
    public static javax.swing.JPanel samplePan;
    public static javax.swing.JLabel sizeLab;
    public static javax.swing.JList<String> sizeList;
    public static javax.swing.JScrollPane sizeScrlp;
    public static javax.swing.JTextField sizeTF;
    public static javax.swing.JLabel styleLab;
    public static javax.swing.JList<String> styleList;
    public static javax.swing.JScrollPane styleScrlp;
    public static javax.swing.JTextField styleTF;
    
    public static Object chosenFontName;
    public static int chosenFontStyle;
    public static int chosenFontSize;
    
    static Font showDialog(JFrame frame, Font initialFont) {
        
        dlg = new JDialog(frame, "Font");
        dlg.setSize(600, 600);
        dlg.setLayout(new FlowLayout());
        dlg.setLocationRelativeTo(null);
        dlg.setResizable(false);
        
        styleTF = new javax.swing.JTextField();
        sizeTF = new javax.swing.JTextField();
        styleScrlp = new javax.swing.JScrollPane();
        styleList = new javax.swing.JList<>();
        samplePan = new javax.swing.JPanel();
        sampleLab = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        fontScrlp = new javax.swing.JScrollPane();
        //get font list
        fontList = new javax.swing.JList();
        fontLab = new javax.swing.JLabel();
        fontTF = new javax.swing.JTextField();
        styleLab = new javax.swing.JLabel();
        sizeLab = new javax.swing.JLabel();
        sizeScrlp = new javax.swing.JScrollPane();
        sizeList = new javax.swing.JList<>();

        fontLab.setText("Font");
        styleLab.setText("Style");
        sizeLab.setText("Size");

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().
            getAvailableFontFamilyNames();

        fontList.setModel(new javax.swing.AbstractListModel<String>() {
            
            public int getSize() { return fontNames.length; }
            public String getElementAt(int i) { return fontNames[i]; }
        });
        
        fontList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {

                chosenFontName = fontList.getSelectedValue();

                int idx = fontList.getSelectedIndex();

                if(idx != -1)
                    fontTF.setText(fontNames[idx]);
            }
        });
        
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontScrlp.setViewportView(fontList);
        
        String[] styleNames = { "Regular", "Italic", "Bold", "Bold Italic" };

        styleList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return styleNames.length; }
            public String getElementAt(int i) { return styleNames[i]; }
        });
        styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        styleScrlp.setViewportView(styleList);
        
        styleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {

                chosenFontStyle = 0;

                if (styleList.getSelectedValue().equals("Regular")) {
                    chosenFontStyle = Font.PLAIN;
                } else if (styleList.getSelectedValue().equals("Italic")) {
                    chosenFontStyle = Font.ITALIC;
                } else if (styleList.getSelectedValue().equals("Bold")) {
                    chosenFontStyle = Font.BOLD;
                } else if (styleList.getSelectedValue().equals("Bold Italic")) {
                    chosenFontStyle = Font.BOLD + Font.ITALIC;
                }
                else {

                }

                int idx = styleList.getSelectedIndex();

                if(idx != -1)
                    styleTF.setText(styleNames[idx]);
            }
        });
        
        
        String[] sizeNames = { "8", "9", "10", "11", "12", "14", "16",
                                    "18", "20", "22", "24", "26", "28", "36",
                                    "48", "72"};
        
        sizeList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return sizeNames.length; }
            public String getElementAt(int i) { return sizeNames[i]; }
        });
        sizeScrlp.setViewportView(sizeList);
        
        sizeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {

                chosenFontSize =
                    Integer.valueOf((String)sizeList.getSelectedValue());

                int idx = sizeList.getSelectedIndex();

                if(idx != -1)
                    sizeTF.setText(sizeNames[idx]);
            }
        });
        

        samplePan.setBorder(javax.swing.BorderFactory.createTitledBorder("Sample Text"));

        sampleLab.setText("Sample");

        javax.swing.GroupLayout samplePanLayout = new javax.swing.GroupLayout(samplePan);
        samplePan.setLayout(samplePanLayout);
        samplePanLayout.setHorizontalGroup(
            samplePanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(samplePanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sampleLab, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        samplePanLayout.setVerticalGroup(
            samplePanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(samplePanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sampleLab, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        ok.setText("OK");
        
        ok.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent le) {
                    font = new Font(chosenFontName.toString(),
                          chosenFontStyle, chosenFontSize);

                    if(font != null) {
                        jtextArea.setFont(font);
                    }
                    else {
                    }
                    dlg.dispose();
                 }
             });

        cancel.setText("Cancel");
        
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                dlg.dispose();
            }
        });

        
        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(dlg.getContentPane());
        dlg.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fontLab)
                            .addComponent(fontScrlp)
                            .addComponent(fontTF, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(styleLab)
                            .addComponent(styleScrlp)
                            .addComponent(styleTF, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(sizeLab)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sizeTF, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(sizeScrlp))
                                .addGap(22, 22, 22))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(ok)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancel))
                            .addComponent(samplePan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fontLab)
                    .addComponent(styleLab)
                    .addComponent(sizeLab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fontTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(styleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sizeScrlp)
                    .addComponent(fontScrlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(styleScrlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(samplePan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(cancel))
                .addGap(25, 25, 25))
        );

    return font;
    }
}
