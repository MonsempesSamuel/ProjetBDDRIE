import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Menunormal extends JFrame {

  private ArrayList<Utilisateur> utilisateurs;
  private ArrayList<Produit> produits;
  private Init init;
  private Utilisateur utilisateur;
  private Produit produit;
  private Vente vente;



  private final CardLayout cl = new CardLayout();
  private final JPanel cards = new JPanel(cl);

  public Menunormal(Init init)throws ParseException{

    JLabel utilisateurSelected = new JLabel("", JLabel.CENTER);
    JLabel produitSelected = new JLabel("", JLabel.CENTER);
    JLabel venteproduitSelected = new JLabel("", JLabel.CENTER);
    JLabel strventeproduit = new JLabel("", JLabel.CENTER);
    JComboBox itemProduit = new JComboBox();
    JComboBox itemVente = new JComboBox();


    this.utilisateurs = init.getutilisateurs();
    this.produits = init.getproduits();
    this.init = init;

    JPanel contentPane = new JPanel();
    setContentPane(contentPane);

    JPanel utilisateurP = new JPanel();
    JLabel label = new JLabel("Entrez votre email", JLabel.CENTER);
    JTextField  textField = new JTextField(20);
    JButton btn1 = new JButton("Valider");
    JButton btn2 = new JButton("Créer un compte");
    JLabel error = new JLabel("", JLabel.CENTER);
    error.setForeground(Color.red);
    utilisateurP.setLayout(new FlowLayout());
    utilisateurP.add(label);
    utilisateurP.add(textField);
    utilisateurP.add(btn1);
    utilisateurP.add(btn2);
    utilisateurP.add(error);

    btn1.addActionListener(e -> {
      int verifU = init.verifUtilisateurMenu(textField.getText());
      if(verifU !=-1){
        utilisateur = utilisateurs.get(verifU);
        error.setText("");
        textField.setText("");
        utilisateurSelected.setText("Bonjour, "+utilisateur.getprenomUtilisateur()+" "+utilisateur.getnomUtilisateur());
        try{
          init.remplirproduit();
        }catch(Exception exep){}
          String[] straux = new String[produits.size()];
          for (int i=0; i<produits.size(); i++) {
            straux[i] = i+") "+produits.get(i).stringProduitmenu();
          }
          itemProduit.setModel(new DefaultComboBoxModel(straux));
          cl.show(cards, "afficherproduits");
        }else{
          error.setText("!----------- Cet email n'existe pas -----------!");
        }
      });
      btn2.addActionListener(e -> {
        error.setText("");
        textField.setText("");
        cl.show(cards, "creerutilisateur");
      });
      cards.add(utilisateurP, "utilisateurP");

      JPanel creerutilisateur = new JPanel();
      JLabel creerutilisateurnom = new JLabel("Entrez votre nom", JLabel.CENTER);
      JTextField  creerutilisateurnomtextField = new JTextField(20);
      JLabel creerutilisateurprenom = new JLabel("Entrez votre prenom", JLabel.CENTER);
      JTextField  creerutilisateurprenomtextField = new JTextField(20);
      JLabel creerutilisateuremail = new JLabel("Entrez votre email", JLabel.CENTER);
      JTextField  creerutilisateuremailtextField = new JTextField(20);
      JLabel creerutilisateuradresse = new JLabel("Entrez votre adresse", JLabel.CENTER);
      JTextField  creerutilisateuradressetextField = new JTextField(20);
      JButton creerutilisateurbtn1 = new JButton("Retour");
      JButton creerutilisateurbtn2 = new JButton("Valider");
      JLabel errorcreerutilisateur = new JLabel("", JLabel.CENTER);
      errorcreerutilisateur.setForeground(Color.red);
      creerutilisateur.setLayout(new FlowLayout());
      creerutilisateur.add(creerutilisateurnom);
      creerutilisateur.add(creerutilisateurnomtextField);
      creerutilisateur.add(creerutilisateurprenom);
      creerutilisateur.add(creerutilisateurprenomtextField);
      creerutilisateur.add(creerutilisateuremail);
      creerutilisateur.add(creerutilisateuremailtextField);
      creerutilisateur.add(creerutilisateuradresse);
      creerutilisateur.add(creerutilisateuradressetextField);
      creerutilisateur.add(creerutilisateurbtn1);
      creerutilisateur.add(creerutilisateurbtn2);
      creerutilisateur.add(errorcreerutilisateur);
      creerutilisateurbtn1.addActionListener(e -> {
        creerutilisateuremailtextField.setText("");
        creerutilisateurnomtextField.setText("");
        creerutilisateurprenomtextField.setText("");
        creerutilisateuradressetextField.setText("");
        errorcreerutilisateur.setText("");
        cl.show(cards, "utilisateurP");
      });
      creerutilisateurbtn2.addActionListener(e -> {
        try {
          init.remplirutilisateur();
        } catch(Exception exep) {}
          String msg = init.creerUtilisateurBDD(creerutilisateuremailtextField.getText(), creerutilisateurnomtextField.getText(),
          creerutilisateurprenomtextField.getText(), creerutilisateuradressetextField.getText());
          if(msg.equals("")){
            creerutilisateuremailtextField.setText("");
            creerutilisateurnomtextField.setText("");
            creerutilisateurprenomtextField.setText("");
            creerutilisateuradressetextField.setText("");
            errorcreerutilisateur.setText("");
            cl.show(cards, "utilisateurP");
          }else{
            errorcreerutilisateur.setText(msg);
          }
        });
        cards.add(creerutilisateur, "creerutilisateur");

        JPanel afficherproduits = new JPanel();
        JButton afficherproduitsbtn1 = new JButton("Retour");
        JButton afficherproduitsbtn2 = new JButton("Sélectionner");
        JButton afficherproduitsbtn3 = new JButton("Ajouter un produit");
        afficherproduits.add(utilisateurSelected);
        afficherproduits.add(itemProduit);
        afficherproduits.add(afficherproduitsbtn1);
        afficherproduits.add(afficherproduitsbtn2);
        afficherproduits.add(afficherproduitsbtn3);
        afficherproduitsbtn1.addActionListener(e -> {
          cl.show(cards, "utilisateurP");
        });
        afficherproduitsbtn2.addActionListener(e -> {
          produit = produits.get(itemProduit.getSelectedIndex());
          try{
            init.remplirvente();
          }catch(Exception exep){}
            String[] straux = new String[produit.getventes().size()+1];
            String strvente = "<html>";
            for (int i=0; i<produit.getventes().size(); i++) {
              straux[i] = "("+i+")";
              strvente += i+") "+produit.getventes().get(i).stringVentemenu();
            }
            strvente += "</html>";
            itemVente.setModel(new DefaultComboBoxModel(straux));
            strventeproduit.setText(strvente);
            produitSelected.setText("<html>"+utilisateurSelected.getText()+" <br/><br/>produit sélectionné: <br/>"+itemProduit.getSelectedItem()+"<br/> </html>");
            cl.show(cards, "affichervente");
          });
          afficherproduitsbtn3.addActionListener(e -> {
            cl.show(cards, "creerproduit");
          });
          cards.add(afficherproduits, "afficherproduits");

          JPanel creerproduit = new JPanel();
          JLabel creerproduitnom = new JLabel("Entrez le nom du produit", JLabel.CENTER);
          JTextField  creerproduitnomtextField = new JTextField(20);
          JLabel creerproduitprix = new JLabel("Entrez le prix de revient (en €)", JLabel.CENTER);
          JTextField  creerproduitprixtextField = new JTextField(20);
          creerproduitprixtextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && creerproduitprixtextField.getText().contains(".")) {
                e.consume();  // ignorer l'événement
              }
            }
          });
          JLabel creerproduitquantite = new JLabel("Entrez la quantité", JLabel.CENTER);
          JTextField  creerproduitquantitetextField = new JTextField(23);
          creerproduitquantitetextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                e.consume();  // ignorer l'événement
              }
            }
          });
          JButton creerproduitbtn1 = new JButton("Valider");
          JButton creerproduitbtn2 = new JButton("Retour");
          creerproduit.setLayout(new FlowLayout());
          creerproduit.add(creerproduitnom);
          creerproduit.add(creerproduitnomtextField);
          creerproduit.add(creerproduitprix);
          creerproduit.add(creerproduitprixtextField);
          creerproduit.add(creerproduitquantite);
          creerproduit.add(creerproduitquantitetextField);
          creerproduit.add(creerproduitbtn2);
          creerproduit.add(creerproduitbtn1);
          creerproduitbtn1.addActionListener(e -> {
            try{
              init.remplirproduit();
            }catch(Exception exep){}
              init.creerProduitBDD(creerproduitnomtextField.getText(), creerproduitprixtextField.getText(),
              creerproduitquantitetextField.getText());
              creerproduitnomtextField.setText("");
              creerproduitquantitetextField.setText("");
              creerproduitprixtextField.setText("");
              String[] straux = new String[produits.size()];
              for (int i=0; i<produits.size(); i++) {
                straux[i] = i+") "+produits.get(i).stringProduitmenu();
              }
              itemProduit.setModel(new DefaultComboBoxModel(straux));
              cl.show(cards, "afficherproduits");
            });
            creerproduitbtn2.addActionListener(e -> {
              try{
                init.remplirproduit();
              }catch(Exception exep){}
                creerproduitnomtextField.setText("");
                creerproduitquantitetextField.setText("");
                creerproduitprixtextField.setText("");
                String[] straux = new String[produits.size()];
                for (int i=0; i<produits.size(); i++) {
                  straux[i] = i+") "+produits.get(i).stringProduitmenu();
                }
                itemProduit.setModel(new DefaultComboBoxModel(straux));
                cl.show(cards, "afficherproduits");
              });
              cards.add(creerproduit, "creerproduit");

              JPanel affichervente = new JPanel();
              JButton afficherventebtn1 = new JButton("Retour");
              JButton afficherventebtn2 = new JButton("Sélectionner");
              JButton afficherventebtn3 = new JButton("Ajouter une vente");
              JLabel afficherventeerror = new JLabel("", JLabel.CENTER);
              afficherventeerror.setForeground(Color.red);
              JLabel afficherventeligne = new JLabel("_______________________________________________________________________", JLabel.CENTER);
              affichervente.add(produitSelected);
              affichervente.add(afficherventeligne);
              affichervente.add(strventeproduit);
              affichervente.add(itemVente);
              affichervente.add(afficherventebtn1);
              affichervente.add(afficherventebtn2);
              affichervente.add(afficherventebtn3);
              affichervente.add(afficherventeerror);
              afficherventebtn1.addActionListener(e -> {
                afficherventeerror.setText("");
                try{
                  init.remplirproduit();
                }catch(Exception exep){}
                  String[] straux = new String[produits.size()];
                  for (int i=0; i<produits.size(); i++) {
                    straux[i] = i+") "+produits.get(i).stringProduitmenu();
                  }
                  itemProduit.setModel(new DefaultComboBoxModel(straux));
                  cl.show(cards, "afficherproduits");
                }
                );
                afficherventebtn2.addActionListener(e -> {
                  afficherventeerror.setText("");
                  try{
                    init.remplirenchere();
                  }catch(Exception exep){}
                    vente = produit.getventes().get(itemVente.getSelectedIndex());
                    ArrayList<Enchere> backtrackenchere = vente.getencheres();
                    String straux = "Voici les enchères de cette vente:<br/><br/>";
                    for (int i=0; i<backtrackenchere.size(); i++) {
                      for (int j=0; j<utilisateurs.size(); j++){
                        if(backtrackenchere.get(i).getnumUtilisateur() == utilisateurs.get(j).getnumUtilisateur()){
                          straux += "Cette enchère a été effectué par "+utilisateurs.get(j).getprenomUtilisateur()+" "+utilisateurs.get(j).getnomUtilisateur()+"<br/>";
                        }
                      }
                      straux += backtrackenchere.get(i).stringEncheremenu();
                    }
                    venteproduitSelected.setText("<html>"+utilisateurSelected.getText()+" <br/><br/>produit sélectionné: <br/>"+itemProduit.getSelectedItem()+"<br/><br/> "+itemVente.getSelectedIndex()+") "+vente.stringVentemenu()+straux+"</html>");
                    cl.show(cards, "afficherenchere");
                  });
                  afficherventebtn3.addActionListener(e -> {
                    if(produit.getstockProduit()==0){
                      afficherventeerror.setText("Ce produit n'a pas de stock");
                    }
                    else if(prodvente()){
                      afficherventeerror.setText("Il y a déjà une vente en cours");
                    }else{
                      afficherventeerror.setText("");
                      cl.show(cards, "creervente");
                    }
                  });
                  cards.add(affichervente, "affichervente");

                  JPanel afficherenchere = new JPanel();
                  JButton afficherencherebtn1 = new JButton("Retour");
                  JButton afficherencherebtn3 = new JButton("Ajouter une enchere");
                  JLabel afficherenchereerror = new JLabel("", JLabel.CENTER);
                  JLabel afficherenchereligne = new JLabel("_______________________________________________________________________", JLabel.CENTER);
                  afficherenchere.add(venteproduitSelected);
                  afficherenchere.add(afficherenchereligne);
                  afficherenchere.add(afficherencherebtn1);
                  afficherenchere.add(afficherencherebtn3);
                  afficherenchere.add(afficherenchereerror);
                  afficherencherebtn1.addActionListener(e -> {
                    try{
                      init.remplirvente();
                    }catch(Exception exep){}
                      String strvente = "<html>";
                      String[] straux = new String[produit.getventes().size()];
                      for (int i=0; i<produit.getventes().size(); i++) {
                        straux[i] = "("+i+")";
                        strvente += i+") "+produit.getventes().get(i).stringVentemenu();
                      }
                      strvente += "</html>";
                      itemVente.setModel(new DefaultComboBoxModel(straux));
                      strventeproduit.setText(strvente);
                      cl.show(cards, "affichervente");
                    });
                    afficherencherebtn3.addActionListener(e -> {
                      cl.show(cards, "creerenchere");
                    });
                    cards.add(afficherenchere, "afficherenchere");

                    JPanel creervente = new JPanel();

                    JLabel creerventeprix = new JLabel("Entrez le prix de départ de la vente", JLabel.CENTER);
                    JTextField  creerventeprixtextField = new JTextField(30);
                    creerventeprixtextField.addKeyListener(new KeyAdapter() {
                      public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(creerventeprixtextField.getText().contains("."))) {
                          e.consume();  // ignorer l'événement
                        }
                      }
                    });
                    JLabel creerventedatedefin = new JLabel("Dans combien de temps fini la vente (en minutes)", JLabel.CENTER);
                    JTextField  creerventedatedefintextField = new JTextField(20);
                    creerventedatedefintextField.addKeyListener(new KeyAdapter() {
                      public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                          e.consume();  // ignorer l'événement
                        }
                      }
                    });
                    JCheckBox creerventedrevocablevente = new JCheckBox("Vente révocable ? ");
                    JCheckBox creerventedmontantvente = new JCheckBox("Vente descendante ?");
                    JCheckBox creerventedureelibretvente = new JCheckBox("Vente à durée libre ?");
                    JCheckBox creerventeencherirvente = new JCheckBox("Les utilisateurs peuvent-ils encherir plusieurs fois ?");
                    JButton creerventebtn1 = new JButton("Valider");
                    JButton creerventebtn2 = new JButton("Retour");
                    creervente.setLayout(new FlowLayout());
                    creervente.add(creerventedatedefin);
                    creervente.add(creerventedatedefintextField);
                    creervente.add(creerventeprix);
                    creervente.add(creerventeprixtextField);
                    creervente.add(creerventedmontantvente);
                    creervente.add(creerventedrevocablevente);
                    creervente.add(creerventedureelibretvente);
                    creervente.add(creerventeencherirvente);
                    creervente.add(creerventebtn2);
                    creervente.add(creerventebtn1);
                    creerventebtn2.addActionListener(e -> {
                      creerventedatedefintextField.setText("");
                      creerventeprixtextField.setText("");
                      creerventedrevocablevente.setSelected(false);
                      creerventedmontantvente.setSelected(false);
                      creerventedureelibretvente.setSelected(false);
                      creerventeencherirvente.setSelected(false);
                      try{
                        init.remplirvente();
                      }catch(Exception exep){}
                        String strvente = "<html>";
                        String[] straux = new String[produit.getventes().size()];
                        for (int i=0; i<produit.getventes().size(); i++) {
                          straux[i] = "("+i+")";
                          strvente += i+") "+produit.getventes().get(i).stringVentemenu();
                        }
                        strvente += "</html>";
                        itemVente.setModel(new DefaultComboBoxModel(straux));
                        strventeproduit.setText(strvente);
                        cl.show(cards, "affichervente");
                      });
                      creerventebtn1.addActionListener(e -> {
                        Date date = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.MINUTE, Integer.parseInt(creerventedatedefintextField.getText()));
                        date = cal.getTime();
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String datefinVente = format1.format(cal.getTime());
                        init.creerVenteBDD(String.valueOf(produit.getnumProduit()), creerventeprixtextField.getText(),
                        (creerventedmontantvente.isSelected())?"1":"0",
                        (creerventedrevocablevente.isSelected())?"1":"0",
                        (creerventedureelibretvente.isSelected())?"1":"0",
                        datefinVente,
                        (creerventeencherirvente.isSelected())?"1":"0");
                        creerventedatedefintextField.setText("");
                        creerventeprixtextField.setText("");
                        creerventedrevocablevente.setSelected(false);
                        creerventedmontantvente.setSelected(false);
                        creerventedureelibretvente.setSelected(false);
                        creerventeencherirvente.setSelected(false);
                        try{
                          init.remplirvente();
                        }catch(Exception exep){}
                          String strvente = "<html>";
                          String[] straux = new String[produit.getventes().size()];
                          for (int i=0; i<produit.getventes().size(); i++) {
                            straux[i] = "("+i+")";
                            strvente += i+") "+produit.getventes().get(i).stringVentemenu();
                          }
                          strvente += "</html>";
                          itemVente.setModel(new DefaultComboBoxModel(straux));
                          strventeproduit.setText(strvente);
                          cl.show(cards, "affichervente");
                        });
                        cards.add(creervente, "creervente");

                        JPanel creerenchere = new JPanel();
                        JLabel creerenchereprix = new JLabel("Entrez le prix (en €):", JLabel.CENTER);
                        JTextField  creerenchereprixtextField = new JTextField(20);
                        creerenchereprixtextField.addKeyListener(new KeyAdapter() {
                          public void keyTyped(KeyEvent e) {
                            char c = e.getKeyChar();
                            if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(creerenchereprixtextField.getText().contains("."))) {
                              e.consume();  // ignorer l'événement
                            }
                          }
                        });
                        JLabel creerencherequantite = new JLabel("Entrez la quantité :", JLabel.CENTER);
                        JTextField  creerencherequantitetextField = new JTextField(20);
                        creerencherequantitetextField.addKeyListener(new KeyAdapter() {
                          public void keyTyped(KeyEvent e) {
                            char c = e.getKeyChar();
                            if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                              e.consume();  // ignorer l'événement
                            }
                          }
                        });
                        JButton creerencherebtn1 = new JButton("Valider");
                        JButton creerencherebtn2 = new JButton("Retour");
                        JLabel creerenchereerror = new JLabel("", JLabel.CENTER);
                        creerenchere.setLayout(new FlowLayout());
                        creerenchereerror.setForeground(Color.red);
                        creerenchere.add(creerenchereprix);
                        creerenchere.add(creerenchereprixtextField);
                        creerenchere.add(creerencherequantite);
                        creerenchere.add(creerencherequantitetextField);
                        creerenchere.add(creerencherebtn2);
                        creerenchere.add(creerencherebtn1);
                        creerenchere.add(creerenchereerror);
                        creerencherebtn1.addActionListener(e -> {
                          try{
                          String numEnchere;
                          if(init.getencheres().size()==0){
                            numEnchere = "0";
                          }else{
                            numEnchere = String.valueOf(init.getencheres().get(init.getencheres().size()-1).getnumEnchere()+1);
                          }
                          Date date = new Date();
                          Calendar cal = Calendar.getInstance();
                          cal.setTime(date);
                          cal.add(Calendar.MINUTE, 10);
                          date = cal.getTime();
                          SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                          String dateEnchere = format1.format(cal.getTime());
                          Enchere enchere = new Enchere(numEnchere, String.valueOf(utilisateur.getnumUtilisateur()),
                          String.valueOf(vente.getnumVente()), creerenchereprixtextField.getText(), dateEnchere,
                          creerencherequantitetextField.getText());
                          String err = vente.boolEnchere(enchere, produit);
                          if(err.equals("")){
                                enchere.insertEnchere();
                                creerenchereerror.setText("");
                                creerencherequantitetextField.setText("");
                                creerenchereprixtextField.setText("");
                              try{
                                init.remplirenchere();

                              }catch(Exception exep){}
                                vente = produit.getventes().get(itemVente.getSelectedIndex());
                                ArrayList<Enchere> backtrackenchere = vente.getencheres();
                                String straux = "Voici les enchères de cette vente:<br/><br/>";
                                for (int i=0; i<backtrackenchere.size(); i++) {
                                  for (int j=0; j<utilisateurs.size(); j++){
                                    if(backtrackenchere.get(i).getnumUtilisateur() == utilisateurs.get(j).getnumUtilisateur()){
                                      straux += "Cette enchère a été effectué par "+utilisateurs.get(j).getprenomUtilisateur()+" "+utilisateurs.get(j).getnomUtilisateur()+"<br/>";
                                    }
                                  }
                                  straux += backtrackenchere.get(i).stringEncheremenu();
                                }
                                venteproduitSelected.setText("<html>"+utilisateurSelected.getText()+" <br/><br/>produit sélectionné: <br/>"+itemProduit.getSelectedItem()+"<br/><br/> "+itemVente.getSelectedIndex()+") "+vente.stringVentemenu()+straux+"</html>");
                                cl.show(cards, "afficherenchere");
                              }else{
                                creerenchereerror.setText("<html>"+err+"</html>");
                              }
                            }catch(Exception exep){}
                          });
                          creerencherebtn2.addActionListener(e -> {
                            creerenchereerror.setText("");
                            creerencherequantitetextField.setText("");
                            creerenchereprixtextField.setText("");
                            try{
                              init.remplirenchere();
                            }catch(Exception exep){}
                              vente = produit.getventes().get(itemVente.getSelectedIndex());
                              ArrayList<Enchere> backtrackenchere = vente.getencheres();
                              String straux = "Voici les enchères de cette vente:<br/><br/>";
                              for (int i=0; i<backtrackenchere.size(); i++) {
                                for (int j=0; j<utilisateurs.size(); j++){
                                  if(backtrackenchere.get(i).getnumUtilisateur() == utilisateurs.get(j).getnumUtilisateur()){
                                    straux += "Cette enchère a été effectué par "+utilisateurs.get(j).getprenomUtilisateur()+" "+utilisateurs.get(j).getnomUtilisateur()+"<br/>";
                                  }
                                }
                                straux += backtrackenchere.get(i).stringEncheremenu();
                              }
                              venteproduitSelected.setText("<html>"+utilisateurSelected.getText()+" <br/><br/>produit sélectionné: <br/>"+itemProduit.getSelectedItem()+"<br/><br/> "+itemVente.getSelectedIndex()+") "+vente.stringVentemenu()+straux+"</html>");
                              cl.show(cards, "afficherenchere");
                            });
                            cards.add(creerenchere, "creerenchere");


                            contentPane.add(cards);

                            cl.show(cards, "utilisateurP");
                          }

                          public Boolean prodvente(){
                            return ((produit.getventes().size()>0) && (!produit.getventes().get(produit.getventes().size()-1).ventefini()));
                          }

                          public Boolean prodvente2(){
                            return (produit.getventes().size()>0);
                          }
                        }
