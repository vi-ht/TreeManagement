/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmenttree_layout;

/**
 *
 * @author Thanh Vi
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;


public class TreeLayout extends JFrame {
      JScrollPane TreePane;
      JPanel controlPanel;
      JPanel controlTree;
      JPanel BottomcontrolPanel; 
      JPanel TopPanel;
      JPanel BottomPanel;
      JTree Tree;
      JButton SavetoFile;
      JButton Exit;
      JButton Save1;
      JButton New1;
      JButton Remove1;
      JPanel ButtonPanel;
      JButton Save2; 
      JButton New2;
      JButton Remove2;
      JPanel ButtonPanel2;
      JLabel Lcode1;
      JLabel Lname1;
      JLabel insert1;
      JTextField Dcode1;
      JTextField Dname1;
      JPanel TextPanel;
      JLabel Lcode2;
      JLabel Lname2;
      JLabel Lsalary2;
      JLabel insert3;
      JLabel insert4;
      JTextField Ecode2;
      JTextField Ename2;
      JTextField Esalary2;
      JPanel TextPanel2;
      String filename = "Tree.txt";
      DefaultMutableTreeNode root=null;
      DefaultMutableTreeNode deptNode=null;
      DefaultMutableTreeNode empNode=null;
      boolean addNewDep=false;
      boolean addNewEmp=false;
      boolean change=false;
      boolean click = false;
      Vector<String> codeD = new Vector();
      Vector<String> codeE = new Vector(); 
      
      public TreeLayout() {
            initComponents();  
             javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Departments");
            Tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
            Tree.setFont(new java.awt.Font("Tahoma", 0, 18));
            Tree.setBackground(Color.pink);
            root = (DefaultMutableTreeNode)Tree.getModel().getRoot();
            loadData();
            Save1.setEnabled(false);
            Save2.setEnabled(false);
            New2.setEnabled(false);
            Remove1.setEnabled(false);
            Remove2.setEnabled(false);
            Dcode1.setEditable(false);
            Dname1.setEditable(false);
            Ecode2.setEditable(false);
            Ename2.setEditable(false);
            Esalary2.setEditable(false);
            TreePath path = new TreePath(root);
            Tree.expandPath(path);   
            TreeMouseClick();
            DepNew();
            DepRemove();
            DepSave();
            EmpNew();
            EmpRemove();
            EmpSave();
            EmpSaveToFile();
            Exit();
      }
      void initComponents(){
            TreePane= new JScrollPane(); 
            TreePane.setPreferredSize(new Dimension(300, 495));
            
            Tree= new JTree(); 
            Tree.setPreferredSize(new Dimension(280, 630));
            
            controlPanel = new JPanel(new BorderLayout(0,10)); 
            controlPanel.setPreferredSize(new Dimension(575, 540));
            
            controlTree = new JPanel(new BorderLayout()); 
            controlTree.setPreferredSize(new Dimension(300, 540));
            
            TopPanel = new JPanel(new BorderLayout()); 
           
            TopPanel.setBorder(BorderFactory.createTitledBorder(null,"Department Details",TitledBorder.DEFAULT_JUSTIFICATION,
                                                                                                            TitledBorder.DEFAULT_POSITION,new java.awt.Font("Tahoma", 0, 18)));
            TopPanel.setFont(new java.awt.Font("Tahoma", 0, 18));

             TopPanel.setPreferredSize(new Dimension(575, 235));
            
            BottomPanel = new JPanel(new BorderLayout()); 
            BottomPanel.setPreferredSize(new Dimension(575, 330));
            BottomPanel.setBorder(BorderFactory.createTitledBorder(null,"Employee Details",TitledBorder.DEFAULT_JUSTIFICATION, 
                                                                                                                  TitledBorder.DEFAULT_POSITION,new java.awt.Font("Tahoma", 0, 18)));
            
            BottomcontrolPanel = new JPanel(); 
            BottomcontrolPanel.setPreferredSize(new Dimension(300, 50));
            BottomcontrolPanel.setLayout(new FlowLayout(FlowLayout.LEFT,14,10));
            
            SavetoFile = new JButton();
            SavetoFile.setText("Save To File");
            SavetoFile.setBackground(Color.pink);
            SavetoFile.setFont(new java.awt.Font("Tahoma", 0, 18));
            SavetoFile.setPreferredSize(new Dimension(130, 30));
            
            Exit = new JButton();
            Exit.setText("Exit");
            Exit.setBackground(Color.pink);
            Exit.setFont(new java.awt.Font("Tahoma", 0, 18));
            Exit.setPreferredSize(new Dimension(130, 30));
            
            New1 = new JButton();
            New1.setText("New");
            New1.setBackground(Color.pink);
            New1.setFont(new java.awt.Font("Tahoma", 0, 18));
            New1.setPreferredSize(new Dimension(120, 30));
            
            Save1= new JButton();
            Save1.setText("Save");
            Save1.setBackground(Color.pink);
            Save1.setFont(new java.awt.Font("Tahoma", 0, 18));
            Save1.setPreferredSize(new Dimension(120, 30));
            
            Remove1= new JButton();
            Remove1.setText("Remove");
            Remove1.setBackground(Color.pink);
            Remove1.setFont(new java.awt.Font("Tahoma", 0, 18));
            Remove1.setPreferredSize(new Dimension(120, 30));
            
            ButtonPanel = new JPanel(); 
            ButtonPanel.setPreferredSize(new Dimension(575, 50));
            ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
            
            New2= new JButton();
            New2.setText("New");
            New2.setBackground(Color.pink);
            New2.setFont(new java.awt.Font("Tahoma", 0, 18));
            New2.setPreferredSize(new Dimension(120, 30));
            
            Save2= new JButton();
            Save2.setText("Save");
            Save2.setBackground(Color.pink);
            Save2.setFont(new java.awt.Font("Tahoma", 0, 18));
            Save2.setPreferredSize(new Dimension(120, 30));
            
            Remove2= new JButton();
            Remove2.setText("Remove");
            Remove2.setBackground(Color.pink);
            Remove2.setFont(new java.awt.Font("Tahoma", 0, 18));
            Remove2.setPreferredSize(new Dimension(120, 30));
            
            ButtonPanel2 = new JPanel(); 
            ButtonPanel2.setPreferredSize(new Dimension(575, 50));
            ButtonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
            
            Lcode1 = new JLabel();
            Lcode1.setText("Dept. Code:");
            Lcode1.setFont(new java.awt.Font("Tahoma", 0, 18));
            Lcode1.setPreferredSize(new Dimension(100, 35));
            
            Lname1 = new JLabel();
            Lname1.setText("Dept. Name:");
            Lname1.setFont(new java.awt.Font("Tahoma", 0, 18));
            Lname1.setPreferredSize(new Dimension(100, 35));
            
            insert1 = new JLabel();
            insert1.setText("");
            insert1.setPreferredSize(new Dimension(200, 35));
                      
            Dcode1 = new JTextField();
            Dcode1.setText("");
            Dcode1.setFont(new java.awt.Font("Tahoma", 0, 18));
            Dcode1.setPreferredSize(new Dimension(200, 35));
            
            Dname1 = new JTextField();
            Dname1.setText("");
            Dname1.setFont(new java.awt.Font("Tahoma", 0, 18));
            Dname1.setPreferredSize(new Dimension(400, 35));
                    
            TextPanel = new JPanel(); 
            TextPanel.setPreferredSize(new Dimension(550, 150));
            TextPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,28));
            
            Lcode2 = new JLabel();
            Lcode2.setText("Emp. Code:");
            Lcode2.setFont(new java.awt.Font("Tahoma", 0, 18));
            Lcode2.setPreferredSize(new Dimension(100, 35));
            
            Lname2 = new JLabel();
            Lname2.setText("Emp. Name:");
            Lname2.setPreferredSize(new Dimension(100, 35));
            Lname2.setFont(new java.awt.Font("Tahoma", 0, 18));
            
            Lsalary2 = new JLabel();
            Lsalary2.setText("Salary:");
            Lsalary2.setPreferredSize(new Dimension(100, 35));
            Lsalary2.setFont(new java.awt.Font("Tahoma", 0, 18));
            
            insert3 = new JLabel();
            insert3.setText("");
            insert3.setPreferredSize(new Dimension(200, 35));
            
            insert4 = new JLabel();
            insert4.setText("");
            insert4.setPreferredSize(new Dimension(200, 35));
            
            Ecode2 = new JTextField();
            Ecode2.setText("");
            Ecode2.setFont(new java.awt.Font("Tahoma", 0, 18));
            Ecode2.setPreferredSize(new Dimension(200, 35));
            
            Ename2 = new JTextField();
            Ename2.setText("");
            Ename2.setFont(new java.awt.Font("Tahoma", 0, 18));
            Ename2.setPreferredSize(new Dimension(400, 35));
            
            Esalary2 = new JTextField();
            Esalary2.setText("");
            Esalary2.setFont(new java.awt.Font("Tahoma", 0, 18));
            Esalary2.setPreferredSize(new Dimension(200, 35));
            
            TextPanel2 = new JPanel(); 
            TextPanel2.setPreferredSize(new Dimension(550, 200));
            TextPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,20,28));
            
            Container container = getContentPane();
            container.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
            container.add(controlTree);
            container.add(controlPanel);
            
            controlTree.add(TreePane,BorderLayout.NORTH);
            controlTree.add(BottomcontrolPanel,BorderLayout.CENTER);

            controlPanel.add(TopPanel, BorderLayout.NORTH);
            controlPanel.add(BottomPanel, BorderLayout.CENTER);
            
            TreePane.setViewportView(Tree);
            
            BottomcontrolPanel.add(SavetoFile); 
            BottomcontrolPanel.add(Exit);
            
            TopPanel.add(ButtonPanel,BorderLayout.SOUTH);
            TopPanel.add(TextPanel,BorderLayout.CENTER);
            
            ButtonPanel.add(New1);
            ButtonPanel.add(Save1);
            ButtonPanel.add(Remove1);
            
            TextPanel.add(Lcode1);
            TextPanel.add(Dcode1);
            TextPanel.add(insert1);
            TextPanel.add(Lname1);
            TextPanel.add(Dname1);
            
            BottomPanel.add(ButtonPanel2,BorderLayout.SOUTH);
            BottomPanel.add(TextPanel2,BorderLayout.CENTER);
            
            ButtonPanel2.add(New2);
            ButtonPanel2.add(Save2);
            ButtonPanel2.add(Remove2);
            
            TextPanel2.add(Lcode2);
            TextPanel2.add(Ecode2);
            TextPanel2.add(insert3);
            TextPanel2.add(Lname2);
            TextPanel2.add(Ename2);
            TextPanel2.add(Lsalary2);
            TextPanel2.add(Esalary2);
            TextPanel2.add(insert4);
      }  
//---------------------------------------------------------------------------------------------------------------------------------------------------   
     private void loadData(){
            try{
            File f = new File(filename);
                   if(!f.exists()) return;
                   FileReader fr = new FileReader(f);
                   BufferedReader br = new BufferedReader(fr);
                   String line = "";
                   while((line=br.readLine()) !=null){
                         boolean isDept = line.charAt(line.length()-1) == ':';
                         StringTokenizer stk = new StringTokenizer(line, ":,-");
                               String code = stk.nextToken();
                               String name = stk.nextToken();
                               if(isDept){
                                     Department newDept = new Department(code, name);
                                     deptNode = new DefaultMutableTreeNode(newDept);
                                     root.add(deptNode);
                                     codeD.add(code);
                               }else{
                                     String Salary = stk.nextToken().trim();
                                     Employee newEmp = new Employee(code, name, Salary);
                                     empNode = new DefaultMutableTreeNode(newEmp);
                                     deptNode.add(empNode);
                                     codeE.add(code);
                               }
                   }
                   br.close(); fr.close();
                   }
                  catch(Exception e){
                         e.printStackTrace();
                   }}
  //---------------------------------------------------------------------------------------------------------------------------------------------------    
     private void viewDeptAndEmp(){
           Department curDep = null;
           Employee curEmp = null;
           if(deptNode!=null) curDep = (Department)(deptNode.getUserObject());
           if(empNode!=null) curEmp = (Employee)(empNode.getUserObject());
           this.Dcode1.setText(curDep != null ? curDep.getDcode() : "");
           this.Dname1.setText(curDep != null ? curDep.getDname(): "");
           this.Ecode2.setText(curEmp != null ? curEmp.getEcode(): "");
           this.Ename2.setText(curEmp != null ? curEmp.getEname(): "");
           this.Esalary2.setText("" + (curEmp != null ? curEmp.getEsalary(): ""));
           this.Dcode1.setEditable(false);
           this.Ecode2.setEditable(false);
           
     }
 //---------------------------------------------------------------------------------------------------------------------------------------------------    
        private void TreeMouseClick(){    
            Tree.addMouseListener(new java.awt.event.MouseAdapter() {
                  @Override
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                        Tree.cancelEditing();
                        
                        TreePath path = Tree.getSelectionPath();
                        if(path==null) return;
                        DefaultMutableTreeNode selectedNode = null;
                        selectedNode = (DefaultMutableTreeNode)(path.getLastPathComponent());
                        Object selectedObj = selectedNode.getUserObject();
                        if(selectedNode==root) deptNode=empNode=null;
                        else{
                              if(selectedObj instanceof Department){
                                    deptNode=selectedNode;
                                    empNode=null;
                                    Save1.setEnabled(true);
                                    New2.setEnabled(true);
                                    Remove1.setEnabled(true);
                                    Dname1.setEditable(true);
                              }else if(selectedObj instanceof Employee){
                                    empNode=selectedNode;
                                    deptNode=(DefaultMutableTreeNode)(selectedNode.getParent());
                                    
                                    New2.setEnabled(true);
                                    Remove1.setEnabled(true);
                                    Remove2.setEnabled(true);
                                    Ename2.setEditable(true);
                                    Esalary2.setEditable(true);
                              }
                        }
                        viewDeptAndEmp();
                        addNewDep=addNewEmp=false;
                        click = true;
                        Save1.setEnabled(false);
                        Save2.setEnabled(false);
                  }
            });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------     
           private void DepNew(){
            New1.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                              addNewDep=true;
                              Save1.setEnabled(true);
                              Dcode1.setEditable(true);
                              Dname1.setEditable(true);
                              New2.setEnabled(true);
                              Dcode1.setText("");
                              Dname1.setText("");
                              Ecode2.setText("");
                              Ename2.setText("");
                              Esalary2.setText("");
                              Dcode1.setEditable(true);
                              Dcode1.requestFocus();           
                  }
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------           
           private void DepRemove (){
            Remove1.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                         Department rmvDep =(Department)(deptNode.getUserObject());
                             if(deptNode.getChildCount()>0){
                                   String msg = "Remove all employee before deleting a department.";
                                   JOptionPane.showMessageDialog(controlPanel, msg);
                             } else{
                                   int response = JOptionPane.showConfirmDialog(controlPanel, "Delete this department - OK?.");
                                   if(response==JOptionPane.OK_OPTION){
                                         for (int i = 0; i <codeD.size(); i++) {
                                          if(rmvDep.getDcode().equals(codeD.get(i))){
                                                 codeD.remove(i);
                                          }
                                          } 
                                         root.remove(deptNode);
                                         Tree.updateUI();
                                         Dcode1.setText("");
                                         Dname1.setText("");
                                         Dcode1.setEditable(false);
                                         Dname1.setEditable(false);
                                         Ecode2.setEditable(false);
                                          Save1.setEnabled(false);
                                          Remove1.setEnabled(false);
                                   }
                             }    change=true;         
                  }
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------           
            private void DepSave (){
            Save1.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                              
                             String code = Dcode1.getText().trim().toUpperCase();
                              for (int i = 0; i <codeD.size(); i++) {
                                    if(code.equals(codeD.get(i))){
                                          JOptionPane.showMessageDialog(controlPanel, code + " existed code!");
                                          Dcode1.requestFocus();
                                    Dcode1.setText("");
                                          return;
                                    }
                              }
                              if(!code.matches("[a-zA-Z]{2}"))
                              {
                                    JOptionPane.showMessageDialog(controlPanel, "Invalid code!");
                                    Dcode1.requestFocus();
                                    Dcode1.setText("");
                                    return;
                              }else Dcode1.setText(code);
                             String name = Dname1.getText().trim();
                             if(!name.matches("[a-zA-Z\\s]+"))
                              {
                                    JOptionPane.showMessageDialog(controlPanel, "Invalid name!");
                                    Dname1.requestFocus();
                                    Dname1.setText("");
                                    return;
                              }else Dname1.setText(name);
                             if(addNewDep==true){
                                   Department newDep = new Department(code, name);
                                   root.add(new DefaultMutableTreeNode(newDep));
                             }else ((Department)deptNode.getUserObject()).setDname(name);
                             Tree.updateUI();
                             addNewDep=false;
                             change=true;
                  }
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------   
            private void EmpNew(){
            New2.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                              addNewEmp=true;
                              Save2.setEnabled(true);
                              Ecode2.setEditable(true);
                              Ename2.setEditable(true);
                              Esalary2.setEditable(true);
                              Ecode2.setText("");
                              Ename2.setText("");
                              Esalary2.setText("");
                              Ecode2.setEditable(true);
                              Ecode2.requestFocus();   
                              
                  }
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------            
       private void EmpRemove(){
            Remove2.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                        Employee rmvEmp =(Employee)(empNode.getUserObject());
                             if(empNode!=null){
                                   int response = JOptionPane.showConfirmDialog(controlPanel, "Delete this employee - OK?.");
                                   if(response==JOptionPane.OK_OPTION){
                                         for (int i = 0; i <codeE.size(); i++) {
                                          if(rmvEmp.getEcode().equals(codeE.get(i))){
                                                 codeE.remove(i);
                                          }
                                     } 
                                         deptNode.remove(empNode);    
                                         Tree.updateUI();
                                   }
                             } change=true;
                              Ecode2.setText("");
                              Ename2.setText("");
                              Esalary2.setText("");
                              Ecode2.setEditable(false);
                              Ename2.setEditable(false);
                              Esalary2.setEditable(false);
                              Save2.setEnabled(false);
                              Remove2.setEnabled(false);
                  }
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------     
       private void EmpSave(){
            Save2.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                             String code = Ecode2.getText().trim().toUpperCase();
                             for (int i = 0; i <codeE.size(); i++) {
                                    if(code.equals(codeE.get(i))){
                                          JOptionPane.showMessageDialog(controlPanel, code + " existed code!");
                                          Ecode2.requestFocus();
                                          Ecode2.setText("");
                                          return;
                                    }
                              }
                              if(!code.matches("[E]\\d{3}"))
                              {
                                    JOptionPane.showMessageDialog(controlPanel, "Invalid code!");
                                    Ecode2.requestFocus();
                                    Ecode2.setText("");
                                    return;
                              }else Ecode2.setText(code);
                             String name = Ename2.getText().trim();
                             if(!name.matches("[a-zA-Z\\s]+"))
                              {
                                    JOptionPane.showMessageDialog(controlPanel, "Invalid name!");
                                    Ename2.requestFocus();
                                    Ename2.setText("");
                                    return;
                              }else Ename2.setText(name);
                             String salaryStr = Esalary2.getText().trim();
                             if(!salaryStr.matches("\\d+"))
                              {
                                    JOptionPane.showMessageDialog(controlPanel, "Invalid Salary!");
                                    Esalary2.requestFocus();
                                    Esalary2.setText("");
                                    int Salary = Integer.parseInt(salaryStr);
                                    if(Salary<=0)
                                    {
                                          JOptionPane.showMessageDialog(controlPanel, "Salary must be >0!");
                                          Esalary2.requestFocus();
                                          Esalary2.setText("");
                                          return;
                                    }
                              }else Esalary2.setText(salaryStr);
                             if(addNewEmp==true){
                                   Employee newEmp = new Employee(code, name,salaryStr);
                                   deptNode.add(new DefaultMutableTreeNode(newEmp));

                             }else{
                                   Employee emp = (Employee) (empNode.getUserObject());
                                   emp.setEname(name);
                                   emp.setEsalary(salaryStr);
                             }
                             Tree.updateUI();
                             addNewEmp=false;
                             change=true;
                  }
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------       
       private void saveFile(){
             if(root.getChildCount()==0) return;
                             String S;
                             try {
                                   FileWriter f = new FileWriter(filename);
                                   PrintWriter pf = new PrintWriter(f);
                                   Enumeration depts = root.children();
                                   while(depts.hasMoreElements()){
                                         DefaultMutableTreeNode depNode = (DefaultMutableTreeNode)depts.nextElement();
                                         Department dept = (Department)(depNode.getUserObject());
                                         S = dept.getDcode() + "," + dept.getDname() + ":";
                                         pf.println(S);
                                         Enumeration emps = depNode.children();
                                         while(emps.hasMoreElements()){
                                                DefaultMutableTreeNode empNode = (DefaultMutableTreeNode)emps.nextElement();
                                                Employee emp = (Employee)(empNode.getUserObject());
                                                S = emp.getEcode() + "," + emp.getEname() + "-" + emp.getEsalary();
                                                pf.println(S); 
                                         }
                                   }
                                   pf.close(); f.close();
                                   JOptionPane.showMessageDialog(controlPanel, "Data are saved to the file " + filename);
                              } catch (Exception a) {
                                    JOptionPane.showMessageDialog(controlPanel, a);
                              }change=false;
       }
 //---------------------------------------------------------------------------------------------------------------------------------------------------       
             private void EmpSaveToFile(){
            SavetoFile.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                             saveFile();
                  }
                  });
      }
             private void Exit(){
            Exit.addActionListener(new ActionListener() {
                  @Override 
                  public void actionPerformed(ActionEvent e) {
                            int r;
                           if(change==true){
                                 r = JOptionPane.showConfirmDialog(controlPanel, "Save file and quit?", "Save File?", JOptionPane.YES_NO_CANCEL_OPTION);
                                 if(r==JOptionPane.YES_OPTION){
                                      saveFile();
                                      System.exit(0);
                                 }
                                 else if(r==JOptionPane.NO_OPTION){
                                       System.exit(0);
                                 }else{}      
                           }
                           else{
                                 r = JOptionPane.showConfirmDialog(controlPanel, "Are you sure?", "Close?", JOptionPane.YES_NO_OPTION);
                                 if(r==JOptionPane.YES_OPTION){
                                 System.exit(0); 
                                 }
                           }
                     }                                                
                  });
      }
 //---------------------------------------------------------------------------------------------------------------------------------------------------            
      public static void main(String[] args) {
            TreeLayout c = new TreeLayout();
            c.setDefaultCloseOperation(EXIT_ON_CLOSE);
            c.setSize(940,617);
            c.setResizable(false);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
      }
}
