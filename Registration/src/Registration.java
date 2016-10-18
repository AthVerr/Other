//4 ergasia asfaleia
//icsd08011-icsd08041

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.security.*;
import java.util.*;
import java.security.spec.*;
import java.sql.Timestamp;


public class Registration extends JFrame implements ActionListener{     
   
    //ftiaxnw ta grafika
    Container pane;
    GridLayout layout2=new GridLayout(3,1);
    JPanel row1=new JPanel();
    JLabel Onoma=new JLabel ("Onoma " ,JLabel.LEFT);
    JTextField  Onom= new JTextField(15);
    
    JPanel row2=new JPanel();
    JLabel Description=new JLabel ("description" ,JLabel.LEFT);
    JTextField  Descript= new JTextField(15);
    
    JPanel row3=new JPanel();
    JLabel User_name=new JLabel ("user_name" ,JLabel.LEFT);
    JTextField  User_na= new JTextField(15);
    
    JPanel row4=new JPanel();
    JButton Ok=new JButton("OK");
    JButton Authenticate=new JButton("Authenticate");
    
     //2i fasi authentikopoiisi
    JPanel row5=new JPanel();
    JLabel username=new JLabel ("user_name" ,JLabel.LEFT);
    JTextField  userna= new JTextField(15);
   
    JPanel row6=new JPanel();
    JLabel File=new JLabel ("file" ,JLabel.LEFT);
    JTextField  file= new JTextField(15);
    
    JPanel row7=new JPanel();
    JButton Insertion=new JButton("Insert");
    
    
    Registration()throws IOException,ClassNotFoundException,SecurityException { 
    super("Registration"); //titlos plaisiou 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);  //me ti methodo setVisible orizw na emfanizetai to parathiro
    setSize(400,400);
    pane= getContentPane(); //dimiourgia ipodoxea
    GridLayout layout=new GridLayout(5,1);
    
    pane.setLayout(layout);
    pane.setLayout(layout2);

    row1.add(Onoma);
    row1.add(Onom);
    pane.add(row1);
    
    row2.add(Description);
    row2.add(Descript);
    pane.add(row2);
    
    row3.add(User_name);
    row3.add(User_na);
    pane.add(row3);
    
    row4.add(Ok);
    row4.add(Authenticate);
    pane.add(row4);
    
    row5.add(username);
    row5.add(userna);
    
    row6.add(File);
    row6.add(file);
  
    row7.add(Insertion);
   
    
    setContentPane(pane); //topothetisi ipodoxea sto plaisio
    pack(); //prosarmogi plaisiou analoga me auta pou valame
    Ok.addActionListener(this); 
    Authenticate.addActionListener(this); 
    Insertion.addActionListener(this);
    pack();
    }
    
 public void actionPerformed(ActionEvent evt) throws SecurityException {
    Object source=evt.getSource(); 
    if(source==Ok){
       //pernei ta stoixeia 
       String x=Onom.getText();
       String y=Descript.getText();
       String z=User_na.getText();
       
  try{
         ObjectOutputStream  out=new ObjectOutputStream(new FileOutputStream("eggrafi.txt"));          
           out.writeObject("onoma:"+x +"Description:" +y +"username:" +z); //vazw ta stoixeia sto arxio eggrafi
           out.flush();
           out.close();
       } 
         
    
catch(FileNotFoundException ex){    
   System.out.println("End of File reached");
   ex.printStackTrace();
}   
catch(IOException ex){
   System.out.println("End of File reached");
   ex.printStackTrace(); 
} 
       //midenizw tis times sto parathiro
  Onom.setText(""); 
  Descript.setText("");
  User_na.setText("");
   
  try{ 
    
File f=null;
    //ftiaxnw fakelo gia ta kleidia
    f=new File ("C:/Users/Anjelina/Documents/NetBeansProjects/Registration/Keys/"+User_na.getText());   //ftiaxnw ton fakelo gia ta kleidia
    f.mkdir();
    //vazw ta kleidia mesa
    FileOutputStream out2=new FileOutputStream("Keys/Private_key.txt");
    FileOutputStream out3=new FileOutputStream("Keys/Public_key.txt");
   //dikaiwma mono gia xrisi na ektelesi ton fakelo m ta kleidia
    boolean bval = f.setExecutable(true);
    System.out.println("set the owner's execute permission: "+ bval);
            
       //ftiaxnw ta  kleidia
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");  
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
    keyGen.initialize(1024, random);
    
    
    KeyPair pair = keyGen.generateKeyPair();
    PrivateKey priv = pair.getPrivate();
    PublicKey pub = pair.getPublic();
    //ta kwdikopoiw
    byte[] pubkey = pub.getEncoded();
    out3.write(pubkey);
    
    byte[] privkey=priv.toString().getBytes();
    out2.write(privkey);
    out2.close();
    out3.close();          
    
 }catch(Exception e){e.printStackTrace();} 

   }
 //2 fasi
if(source==Authenticate){
   pane.removeAll(); 
   pane.setLayout(layout2);
   pane.add(row5);
   pane.add(row6); 
   pane.add(row7);
   revalidate();
   repaint();
}
if(source==Insertion){      
   
   String b=file.getText(); //dinei o xristis to path
  
   //an dwsei to swsto proxwraei
   if(b=="C:/Users/Anjelina/Documents/NetBeansProjects/Registration/Keys/"){
       
       
       try{
           
            Date date= new Date();              //Timestamp
            Timestamp timestamp=new Timestamp(date.getTime());
            Random nonce = SecureRandom.getInstance ("SHA1PRNG"); //nonce
                 
       /* ftiaxnw ti tha ypograpsei diladi to nonce to timstamp kai to username */
            String data=timestamp.toString()+nonce.toString()+userna.getText();
            byte[] sigToVerify = data.getBytes();            
           
           Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");   
            dsa.update(sigToVerify);         
            byte[] realSig = dsa.sign();

           /* apothikeusi sfragidas se arxeio */
           FileOutputStream sigfos = new FileOutputStream("C:/Users/Anjelina/Documents/NetBeansProjects/Registration/Keys/sig/");
           sigfos.write(realSig);
            sigfos.close();                        
                
           //diavazei ta kwdikopoimena kleidia
            FileInputStream keyfis = new FileInputStream("C:/Users/Anjelina/Documents/NetBeansProjects/Registration/Keys/");
           byte[] encKey = new byte[keyfis.available()];  
          keyfis.read(encKey);
         keyfis.close();

X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
PublicKey pubKey =
keyFactory.generatePublic(pubKeySpec);

            Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
           sig.initVerify(pubKey); //proetimazei  to kleidi
           //ypografei me ta dedomena p prepei na elegxontai
           FileInputStream datafis = new FileInputStream("C:/Users/Anjelina/Documents/NetBeansProjects/Registration/Keys/sig/");
           BufferedInputStream bufin = new BufferedInputStream(datafis);
          byte[] buffer = new byte[1024];
            int len;
         while (bufin.available() != 0) {
           len = bufin.read(buffer);
                  sig.update(buffer, 0, len);
                     }
               bufin.close();
               
//epalitheusi tis ypografis
boolean verifies = sig.verify(sigToVerify);
System.out.println("signature verifies: " + verifies);         
   
} catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
}
   }                                        
//midenizw tis times sto parathiro
   userna.setText(""); 
   file.setText("");
    
}    

 }
public static void main(String[] args){ 
  try{
     Registration R=new Registration();
       
}catch(Exception e){}
}
}

