import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class DemoMinimale {

  private Scanner saisieUtilisateur;
  private Init init;

  public DemoMinimale(){
    this.saisieUtilisateur = new Scanner(System.in);
    }

  public void menuPrincipal()throws ParseException{
    String menuPrincipal="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Tests Unitaires \n 2) Test à la main \n 3) Quitter";
    int reponse;
    do{
      System.out.println(menuPrincipal);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          Test test = new Test();
          test.test_unitaires();
          break;
        case 2:
          menuchoixBDDDemo();
          break;
        case 3:
          reponse = 5;
          break;
        default:
          reponse = 4;
      }
    }while (reponse < 5);
  }





  public void menuchoixBDDDemo()throws ParseException{
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Générer une BDD vide \n 2) Générer une BDD remplie \n 3) Récupérer la BDD existante \n 4) Retour";
    int reponse;
    do{
      this.init = new Init();
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          init.genererBDD();
          menuchoixdeDemo();
          break;
        case 2:
          init.genererBDD();
          init.RemplirBDD();
          menuchoixdeDemo();
          break;
        case 3:
          init.remplirfromBDD();
          menuchoixdeDemo();
          break;
        case 4:
          reponse = 6;
          break;
        default:
          reponse = 5;
      }
    }while (reponse < 6);
  }

  public void menuchoixdeDemo()throws ParseException{
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Démo parcours utilisateur \n 2) Démo minimale \n 3) Retour";
    int reponse;
    do{
      this.init = new Init();
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          Init init2 = new Init();
          init2.remplirfromBDD();
          Menunormal frame = new Menunormal(init2);
          frame.setLayout(new GridLayout(0,1));
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setSize(450, 900);
          frame.setVisible(true);
          break;
        case 2:
          menuPrincipalDemo();
          break;
        case 3:
          reponse = 5;
          break;
        default:
          reponse = 4;
      }
    }while (reponse < 5);
  }

  public void menuPrincipalDemo()throws ParseException{
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Produit \n 2) Vente \n 3) Utilisateur \n 4) Enchere \n 5) Retour";
    int reponse;
    do{
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          demoProduit();
          break;
        case 2:
          demoVente();
          break;
        case 3:
          demoUtilisateur();
          break;
        case 4:
          demoEnchere();
          break;
        case 5:
          reponse = 7;
          break;
        default:
          reponse = 6;
      }
    }while (reponse < 7);
  }

  public void demoUtilisateur(){
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Créer \n 2) Modifier \n 3) Supprimer \n 4) Afficher \n 5) Retour";
    int reponse;
    do{
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          init.creerUtilisateur();
          break;
        case 2:
          init.modifierUtilisateur();
          break;
        case 3:
          init.supprimerUtilisateur();
          break;
        case 4:
          init.afficherUtilisateur();
          break;
        case 5:
          reponse = 7;
          break;
        default:
          reponse = 6;
      }
    }while (reponse < 7);
  }

  public void demoProduit(){
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Créer \n 2) Modifier \n 3) Supprimer \n 4) Afficher \n 5) Retour";
    int reponse;
    do{
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          init.creerProduit();
          break;
        case 2:
          init.modifierProduit();
          break;
        case 3:
          init.supprimerProduit();
          break;
        case 4:
          init.afficherProduit();
          break;
        case 5:
          reponse = 7;
          break;
        default:
          reponse = 6;
      }
    }while (reponse < 7);
  }

  public void demoVente()throws ParseException{
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Créer \n 2) Modifier \n 3) Supprimer \n 4) Afficher \n 5) Retour";
    int reponse;
    do{
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          init.creerVente();
          break;
        case 2:
          init.modifierVente();
          break;
        case 3:
          init.supprimerVente();
          break;
        case 4:
          init.afficherVente();
          break;
        case 5:
          reponse = 7;
          break;
        default:
          reponse = 6;
      }
    }while (reponse < 7);
  }

  public void demoEnchere(){
    String menuPrincipalDemo ="Sélectionner le numéro du menu auquel vous voulez accéder \n"+
                          " 1) Créer \n 2) Modifier \n 3) Supprimer \n 4) Afficher \n 5) Retour";
    int reponse;
    do{
      System.out.println(menuPrincipalDemo);
      reponse = saisieUtilisateur.nextInt();
      switch (reponse) {
        case 1:
          init.creerEnchere();
          break;
        case 2:
          init.modifierEnchere();
          break;
        case 3:
          init.supprimerEnchere();
          break;
        case 4:
          init.afficherEnchere();
          break;
        case 5:
          reponse = 7;
          break;
        default:
          reponse = 6;
      }
    }while (reponse < 7);
  }
}
