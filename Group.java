
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tanya
 */
public class Group extends javax.swing.JFrame {
Connection con;
void opencon(){
    try{
        Class.forName("java.sql.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/shopcart","root","pass");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(this,e.getMessage());

    }
}
    /**
     * Creates new form Group
     */
    public Group(String ab) {
        initComponents();
        lb.setVisible(false);
        lb.setText(ab);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        join = new javax.swing.JButton();
        create = new javax.swing.JButton();
        lb = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        join.setText("JOIN GROUP");
        join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinActionPerformed(evt);
            }
        });
        getContentPane().add(join);
        join.setBounds(150, 210, 290, 25);

        create.setText("CREATE A GROUP");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create);
        create.setBounds(150, 300, 290, 25);
        getContentPane().add(lb);
        lb.setBounds(224, 180, 102, 23);

        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back);
        back.setBounds(150, 390, 290, 25);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Tanya\\Downloads\\wr\\joingrp.jpg")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(560, 160, 320, 320);

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit);
        exit.setBounds(150, 480, 290, 25);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 990, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinActionPerformed
        // TODO add your handling code here:
        try{
            String a=JOptionPane.showInputDialog("Enter Group ID");
            if(a==null||"".equals(a)){
            JOptionPane.showMessageDialog(this,"Enter an ID.");
        }
                else{
                
                String b1="select count(*) from groupmember where gid='"+a+"' and mid='"+lb.getText()+"'";
                 Statement s11=con.createStatement();
                  ResultSet rs11=s11.executeQuery(b1);
        rs11.next();
        if(rs11.getInt(1)==1){
            JOptionPane.showMessageDialog(this,"Already a member of the group.");
        }else{
            String b=JOptionPane.showInputDialog("Enter Group Password");
            String c="select gpassword from groups where gid='"+a+"'";
            
        Statement s1=con.createStatement();
        
        ResultSet rs=s1.executeQuery(c);
        rs.next();
        
        String a1=rs.getString(1);
        
        if(a1.equals(b)){
          String z="update totalmembers set GroupExists='Y' where MID='"+lb.getText()+"'";  
          s1.executeUpdate(z);
          String z1="Insert into groupmember values('"+a+"','"+lb.getText()+"')";
          s1.executeUpdate(z1);
          
           shop g= new shop(Integer.parseInt(lb.getText()));
            g.setVisible(true);
            g.setSize(950,650);
        dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"Incorrect Password");
        }
               
               
           }
            
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"The group does not exist");
        }
       
    }//GEN-LAST:event_joinActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        // TODO add your handling code here:
         try{
      
        String b=JOptionPane.showInputDialog("Enter Group Password");
        if(b==null||"".equals(b)){
            JOptionPane.showMessageDialog(this,"Create group action cancelled. Either cancelled or no password entered.");
        }
        else{
             String q="insert into groups(gpassword,admin) values('"+b+"','"+lb.getText()+"')";
        Statement s=con.createStatement();
        s.executeUpdate(q);
        
      
        String a="select GID from groups";
       
        ResultSet rs=s.executeQuery(a);
        rs.last();
        String a1=Integer.toString(rs.getInt(1));
        JOptionPane.showMessageDialog(this,"Your GroupID is"+a1);
        String q1="insert into groupmember values('"+a1+"','"+lb.getText()+"')";
        s.executeUpdate(q1);
        String z1="Create table cart"+a1+"(Items varchar(25) primary key,Quantity int(5) not null,Unit varchar(15) not null,Category varchar(25) not null,Bought char(3) not null default 'No')";
        s.executeUpdate(z1);
       String z3="Create table histcart"+a1+"(Items varchar(25) primary key,Quantity int(5) not null,Unit varchar(15) not null,Category varchar(25) not null)";
        s.executeUpdate(z3);
        String z2="update totalmembers set Groupexists='Y' where MID='"+lb.getText()+"'"; 
        
        s.executeUpdate(z2);
           
            shop s1=new shop(Integer.parseInt(lb.getText()));
            s1.setVisible(true);
            s1.setSize(950,650);
          dispose(); 
        }
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
          
    }//GEN-LAST:event_createActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        opencon();
    }//GEN-LAST:event_formWindowOpened

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        try{
            String a="select Groupexists from totalmembers where MID='"+lb.getText()+"'"; 
       Statement s=con.createStatement();
        ResultSet rs=s.executeQuery(a);
        rs.last();
        String a1=rs.getString(1);
        if(a1.equals("Y")){
            
              shop s1=new shop(Integer.parseInt(lb.getText()));
            s1.setVisible(true);
            s1.setSize(950,650);
          dispose();
        } else{
            JOptionPane.showMessageDialog(this,"You do not have any groups. Choose or create one to continue.");
          //  new shop(Integer.parseInt(lb.getText())).setVisible(true);
         // dispose();
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
         
    }//GEN-LAST:event_backActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Group.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Group.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Group.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Group.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new Group().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton create;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton join;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
