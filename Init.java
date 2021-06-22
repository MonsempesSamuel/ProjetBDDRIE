import java.util.concurrent.ThreadLocalRandom;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Init {

  private Bddaccess bddaccess;
  private ArrayList<Utilisateur> utilisateurs;
  private ArrayList<Produit> produits;
  private ArrayList<Vente> ventes;
  private ArrayList<Enchere> encheres;
  private Scanner saisieUtilisateur;

  public Init(){
    this.bddaccess = new Bddaccess();
    this.utilisateurs = new ArrayList<Utilisateur>();
    this.produits = new ArrayList<Produit>();
    this.ventes = new ArrayList<Vente>();
    this.encheres = new ArrayList<Enchere>();
    this.saisieUtilisateur = new Scanner(System.in);
  }

  public ArrayList<Utilisateur> getutilisateurs(){
    return this.utilisateurs;
  }
  public ArrayList<Produit> getproduits(){
    return this.produits;
  }
  public ArrayList<Vente> getventes(){
    return this.ventes;
  }
  public ArrayList<Enchere> getencheres(){
    return this.encheres;
  }

  public void genererBDD(){
    String table = "Produit";
    String option = " numProduit INTEGER NOT NULL,"
    + " nomProduit varchar(30) NOT NULL,"
    + " prixrevientProduit float NOT NULL,"
    + " stockProduit INTEGER NOT NULL,"
    + " primary key (numProduit)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    table = "Utilisateur";
    option = " numUtilisateur INTEGER,"
    + " emailUtilisateur varchar(30) NOT NULL UNIQUE,"
    + " nomUtilisateur varchar(30) NOT NULL,"
    + " prenomUtilisateur varchar(30) NOT NULL,"
    + " adresseUtilisateur varchar(30) NOT NULL,"
    + " primary key (numUtilisateur)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    table = "Vente";
    option = " numVente INTEGER NOT NULL,"
      + " numProduit INTEGER NOT NULL,"
      + " prixVente float NOT NULL,"
      + " montantVente number(1) not null check (montantVente in (1,0)),"
      + " revocableVente number(1) not null check (revocableVente in (1,0)),"
      + " dureelibreVente number(1) not null check (dureelibreVente in (1,0)),"
      + " datefinVente DATE,"
      + " encherirplusieurfoisVente number(1) not null check (encherirplusieurfoisVente in (1,0)),"
      + " PRIMARY KEY (numVente),"
      + " CONSTRAINT FK_produit FOREIGN KEY (numProduit) REFERENCES Produit(numProduit) ON DELETE CASCADE";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    table = "Enchere";
    option = " numEnchere INTEGER NOT NULL,"
      + " numUtilisateur INTEGER NOT NULL,"
      + " numVente INTEGER NOT NULL,"
      + " prixachatEnchere float NOT NULL,"
      + " dateEnchere DATE,"
      + " qteproduitEnchere INTEGER NOT NULL,"
      + " PRIMARY KEY (numEnchere),"
      + " CONSTRAINT FK_Utilisateur FOREIGN KEY (numUtilisateur) REFERENCES Utilisateur(numUtilisateur) ON DELETE CASCADE,"
      + " CONSTRAINT FK_Vente FOREIGN KEY (numVente) REFERENCES Vente(numVente) ON DELETE CASCADE";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
  }

  public void RemplirBDD()throws ParseException{
    genererBDD();
    String[] nomproduits ={
      "Petit stepper",
      "Bateau",
      "Guirlande lumineuse",
      "Cage a hamster",
      "Cable parrallele",
      "Tapis",
      "Lentilles de contact",
      "Matelas gonflable",
      "Tablette Wacom",
      "Protection pour bras casse",
      "Trotteur",
      "Affiches"
    };
    String[] rue ={
      "Rue du lise",
      "Place du lise",
      "Grande Rue",
      "Rue du Moulin",
      "Place de la Mairie",
      "Rue du Château",
      "Rue des Écoles",
      "Rue de la Gare",
      "Rue de la Mairie",
      "Rue Principale",
      "Rue du Stade",
      "Rue de la Fontaine"
    };
    String[] prenom ={
      "Gabriel",
      "Leo",
      "Raphaël",
      "Arthur",
      "Louis",
      "Lucas",
      "Adam",
      "Jules",
      "Hugo",
      "Maël",
      "Liam",
      "Noah"
    };
    String[] nom ={
     	"Imbert",
      "Prevot",
     	"Toussaint",
      "Cornu",
     	"Maillet",
      "Lelievre",
     	"Bonneau",
      "Flament",
     	"Tournier",
      "Merlin",
     	"Salaun",
      "Vial"
    };
    for(int i = 0; i<nomproduits.length; i++){
      String randomprix = String.valueOf(ThreadLocalRandom.current().nextInt(20, 1000));
      String randomstock = String.valueOf(ThreadLocalRandom.current().nextInt(2, 10));
      Produit produit = new Produit(String.valueOf(i), nomproduits[i], randomprix, randomstock);
      produit.insertProduit();
      produits.add(produit);
    }
    for(int i = 0; i<prenom.length; i++){
      String numrue = String.valueOf(ThreadLocalRandom.current().nextInt(1, 20));
      String numUtilisateur = String.valueOf(i);
      String emailUtilisateur = prenom[i] + "." + nom[i] + "@gmail.com";
      String nomUtilisateur = nom[i];
      String prenomUtilisateur = prenom[i];
      String adresseUtilisateur = numrue + " " + rue[i];
      Utilisateur utilisateur = new Utilisateur(numUtilisateur, emailUtilisateur, nomUtilisateur, prenomUtilisateur, adresseUtilisateur);
      utilisateur.insertUtilisateur();
      utilisateurs.add(utilisateur);
      }
    for(int i = 0; i<nomproduits.length/2; i++){
      String numVente = String.valueOf(i);
      String numProduit = String.valueOf(ThreadLocalRandom.current().nextInt(0, nomproduits.length-1));
      Produit prodaux = produits.get(Integer.parseInt(numProduit));
      String prixVente = String.valueOf(prodaux.getprixrevientProduit()+ThreadLocalRandom.current().nextInt(20, 1000));
      String montantVente = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1));
      String revocableVente = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1));
      String dureelibreVente = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1));
      Date date = new Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.MINUTE, 10);
      date = cal.getTime();
      SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String datefinVente = format1.format(cal.getTime());
      String encherirplusieurfoisVente = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1));
      Vente vente = new Vente(numVente, numProduit, prixVente, montantVente, revocableVente, dureelibreVente, datefinVente, encherirplusieurfoisVente);
      vente.insertVente();
      ventes.add(vente);
    }
    int j =0;
  for(int i = 0; i<nomproduits.length/2; i++){
    for(int y = 0; y<5; y++){
      String numEnchere = String.valueOf(j);
      String numUtilisateur = String.valueOf(ThreadLocalRandom.current().nextInt(0, utilisateurs.size()-1));
      String numVente = String.valueOf(i);
      String prixachatEnchere = String.valueOf(ventes.get(i).getprixVente() + j*10);
      Date date = new Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.MINUTE, y-10);
      date = cal.getTime();
      SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateEnchere = format1.format(cal.getTime());
      String qteproduitEnchere = String.valueOf(ThreadLocalRandom.current().nextInt(1 , produits.get(i).getstockProduit()));
      j++;
      Enchere enchere = new Enchere( numEnchere,  numUtilisateur,  numVente,  prixachatEnchere,  dateEnchere,  qteproduitEnchere);
      enchere.insertEnchere();
      encheres.add(enchere);
    }
  }
}

  public void remplirfromBDD()throws ParseException{
    remplirproduit();
    remplirutilisateur();
    remplirvente();
    remplirenchere();
  }

  public void remplirproduit()throws ParseException{
    produits.clear();
    String table = "Produit";
    String[] resultSet = this.bddaccess.select(table);
    int size = resultSet.length;
    for(int i = 0; i<size; i+=4){
      Produit produit = new Produit(resultSet[i], resultSet[i+1], resultSet[i+2], resultSet[i+3]);
      produits.add(produit);
    }
  }

  public void remplirutilisateur()throws ParseException{
    utilisateurs.clear();
    String table = "Utilisateur";
    String[] resultSet = this.bddaccess.select(table);
    int size = resultSet.length;
    for(int i = 0; i<size; i+=5){
      Utilisateur utilisateur = new Utilisateur(resultSet[i], resultSet[i+1], resultSet[i+2], resultSet[i+3], resultSet[i+4]);
      utilisateurs.add(utilisateur);
    }
  }

  public void remplirvente()throws ParseException{
    ventes.clear();
    for (int j=0; j<produits.size(); j++){
      produits.get(j).getventes().clear();
    }
    String table = "Vente";
    String[] resultSet = this.bddaccess.select(table);
    int size = resultSet.length;
    for(int i = 0; i<size; i+=8){
      Vente vente = new Vente(resultSet[i], resultSet[i+1], resultSet[i+2], resultSet[i+3], resultSet[i+4], resultSet[i+5], resultSet[i+6], resultSet[i+7]);
      ventes.add(vente);
      for (int j=0; j<produits.size(); j++){
        if(vente.getnumProduit() == produits.get(j).getnumProduit()){
          produits.get(j).addVente(vente);
        }
      }
    }
  }

  public void remplirenchere()throws ParseException{
    encheres.clear();
    for (int j=0; j<ventes.size(); j++){
      ventes.get(j).getencheres().clear();
    }
    for (int j=0; j<utilisateurs.size(); j++){
      utilisateurs.get(j).getencheres().clear();
    }
    String table = "Enchere";
    String[] resultSet = this.bddaccess.select(table);
    int size = resultSet.length;
    for(int i = 0; i<size; i+=6){
      Enchere enchere = new Enchere(resultSet[i], resultSet[i+1], resultSet[i+2], resultSet[i+3], resultSet[i+4], resultSet[i+5]);
      encheres.add(enchere);
      for (int j=0; j<ventes.size(); j++){
        if(enchere.getnumVente() == ventes.get(j).getnumVente()){
          ventes.get(j).addEnchere(enchere);
        }
      }
      for (int j=0; j<utilisateurs.size(); j++){
        if(enchere.getnumUtilisateur() == utilisateurs.get(j).getnumUtilisateur()){
          utilisateurs.get(j).addEnchere(enchere);
        }
      }
    }
  }



  public void creerUtilisateur(){
    System.out.println("Rentrer l'email de utilisateur");
    String emailUtilisateur = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le nom de utilisateur");
    String nomUtilisateur = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le prénom de utilisateur");
    String prenomUtilisateur = saisieUtilisateur.nextLine();
    System.out.println("Rentrer l'adresse de utilisateur");
    String adresseUtilisateur = saisieUtilisateur.nextLine();
    creerUtilisateurBDD( emailUtilisateur,  nomUtilisateur,  prenomUtilisateur,  adresseUtilisateur);
  }

  public String creerUtilisateurBDD(String emailUtilisateur, String nomUtilisateur, String prenomUtilisateur, String adresseUtilisateur){
    String numUtilisateur;
    if(produits.size()==0){
      numUtilisateur = "0";
    }else{
      numUtilisateur = String.valueOf(utilisateurs.get(utilisateurs.size()-1).getnumUtilisateur()+1);
    }
    Utilisateur utilisateur = new Utilisateur(numUtilisateur, emailUtilisateur, nomUtilisateur, prenomUtilisateur,
    adresseUtilisateur);
    String res = verifUtilisateur(emailUtilisateur);
    if (res.equals("")){
      utilisateur.insertUtilisateur();
      utilisateurs.add(utilisateur);
    }
    return res;
  }

  public void modifierUtilisateur(){
    System.out.println("Rentrer le numéro de l'utilisateur");
    int num = saisieUtilisateur.nextInt();
    int numUtilisateur = chercheUtilisateur(num);
    System.out.println("Rentrer l'email de utilisateur");
    saisieUtilisateur.nextLine();
    String emailUtilisateur = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le nom de utilisateur");
    String nomUtilisateur = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le prénom de utilisateur");
    String prenomUtilisateur = saisieUtilisateur.nextLine();
    System.out.println("Rentrer l'adresse de utilisateur");
    String adresseUtilisateur = saisieUtilisateur.nextLine();
    utilisateurs.get(numUtilisateur).setemailUtilisateur(emailUtilisateur);
    utilisateurs.get(numUtilisateur).setnomUtilisateur(nomUtilisateur);
    utilisateurs.get(numUtilisateur).setprenomUtilisateur(prenomUtilisateur);
    utilisateurs.get(numUtilisateur).setadresseUtilisateur(adresseUtilisateur);
  }

  public int chercheUtilisateur(int num){
    int res = 0;
    for (int i=0;i<utilisateurs.size() ;i++ ) {
      if(num == utilisateurs.get(i).getnumUtilisateur())res=i;
    }
    return res;
  }


  public void supprimerUtilisateur(){
    System.out.println("Rentrer le numéro de l'utilisateur");
    int num = saisieUtilisateur.nextInt();
    int numUtilisateur = chercheUtilisateur(num);
    utilisateurs.get(numUtilisateur).deleteUtilisateur();
    utilisateurs.remove(utilisateurs.get(numUtilisateur));
  }

  public void afficherUtilisateur(){
    utilisateurs.forEach((n) -> System.out.println(n.stringUtilisateur()));
  }

  public void creerProduit(){
    System.out.println("Rentrer le nom du produit");
    String nomProduit = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le prix de revient du produit");
    String prixrevientProduit = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le stock du produit");
    String stockProduit = saisieUtilisateur.nextLine();
    creerProduitBDD(nomProduit, prixrevientProduit, stockProduit);
  }

  public void creerProduitBDD(String nomProduit, String prixrevientProduit, String stockProduit){
    String numProduit;
    if(produits.size()==0){
      numProduit = "0";
    }else{
      numProduit = String.valueOf(produits.get(produits.size()-1).getnumProduit()+1);
    }
    Produit produit = new Produit(numProduit, nomProduit, prixrevientProduit, stockProduit);
    produit.insertProduit();
    produits.add(produit);
  }

  public void modifierProduit(){
    System.out.println("Rentrer le numéro du produit");
    int num = saisieUtilisateur.nextInt();
    int numProduit = chercheProduit(num);
    System.out.println("Rentrer le nom du produit");
    saisieUtilisateur.nextLine();
    String nomProduit = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le prix de revient du produit");
    String prixrevientProduit = saisieUtilisateur.nextLine();
    System.out.println("Rentrer le stock du produit");
    String stockProduit = saisieUtilisateur.nextLine();
    produits.get(numProduit).setnomProduit(nomProduit);
    produits.get(numProduit).setprixrevientProduit(Float.parseFloat(prixrevientProduit));
    produits.get(numProduit).setstockProduit(Integer.parseInt(stockProduit));
  }

  public int chercheProduit(int num){
    int res = 0;
    for (int i=0;i<produits.size() ;i++ ) {
      if(num == produits.get(i).getnumProduit())res=i;
    }
    return res;
  }

  public void supprimerProduit(){
    System.out.println("Rentrer le numéro du produit");
    int num = saisieUtilisateur.nextInt();
    int numProduit = chercheProduit(num);
    produits.get(numProduit).deleteProduit();
    produits.remove(produits.get(numProduit));
  }

  public void afficherProduit(){
    produits.forEach((n) -> System.out.println(n.stringProduit()));
  }

  public void creerVente()throws ParseException {
    System.out.println("Rentrer le numéro du produit auquel correspond la vente");
    afficherProduit();
    int numProduit = saisieUtilisateur.nextInt();
    System.out.println("Quel est le prix de départ de ce produit ?");
    saisieUtilisateur.nextLine();
    String prixVente = saisieUtilisateur.nextLine();
    System.out.println("Cette vente est elle montante ? O/N");
    String montantVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    System.out.println("Cette vente peut elle être à perte ? O/N");
    String revocableVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    System.out.println("Cette vente est elle à durée libre ? O/N");
    String dureelibreVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    System.out.println("Un utilisateur peut il enchérir plusieurs fois sur cette vente ? O/N");
    String encherirplusieurfoisVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    String datefinVente = "2020-06-06 12:12:12";
    if(dureelibreVente.equals("0")){
      System.out.println("A quelle date fini la vente ? format yyyy-MM-dd HH:mm:ss");
      datefinVente = saisieUtilisateur.nextLine();
    }
    creerVenteBDD(String.valueOf(numProduit), prixVente, montantVente, revocableVente, dureelibreVente, datefinVente, encherirplusieurfoisVente);
  }

public void creerVenteBDD(String numProduit, String prixVente, String montantVente, String revocableVente, String dureelibreVente, String datefinVente, String encherirplusieurfoisVente){
    String numVente;
    if(ventes.size()==0){
      numVente = "0";
    }else{
      numVente = String.valueOf(ventes.get(ventes.size()-1).getnumVente()+1);
    }
    try{
      Vente vente = new Vente(numVente, numProduit, prixVente, montantVente, revocableVente, dureelibreVente, datefinVente, encherirplusieurfoisVente);
      vente.insertVente();
      ventes.add(vente);
    } catch(Exception exep) {}
  }

  public void modifierVente()throws ParseException {
    System.out.println("Rentrer le numéro de la vente");
    int num = saisieUtilisateur.nextInt();
    int numVente = chercheVente(num);
    System.out.println("Quel est le prix de départ de ce produit ?");
    String prixVente = saisieUtilisateur.nextLine();
    System.out.println("Cette vente est elle montante ? O/N");
    String montantVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    System.out.println("Cette vente peut elle être à perte ? O/N");
    String revocableVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    System.out.println("Cette vente est elle à durée libre ? O/N");
    String dureelibreVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    System.out.println("Un utilisateur peut il enchérir plusieurs fois sur cette vente ? O/N");
    String encherirplusieurfoisVente = (saisieUtilisateur.nextLine().equals("O"))?"1":"0";
    String datefinVente = "2020-06-06 12:12:12";
    if(dureelibreVente.equals("0")){
      System.out.println("A quelle date fini la vente ? format yyyy-MM-dd HH:mm:ss");
      datefinVente = saisieUtilisateur.nextLine();
    }
    ventes.get(numVente).setprixVente(Float.parseFloat(prixVente));
    ventes.get(numVente).setmontantVente(!montantVente.equals("0"));
    ventes.get(numVente).setrevocableVente(!revocableVente.equals("0"));
    ventes.get(numVente).setdureelibreVente(!dureelibreVente.equals("0"));
    ventes.get(numVente).setdatefinVente(datefinVente);
    ventes.get(numVente).setencherirplusieurfoisVente( !encherirplusieurfoisVente.equals("0"));
  }

  public int chercheVente(int num){
    int res = 0;
    for (int i=0;i<ventes.size() ;i++ ) {
      if(num == ventes.get(i).getnumVente())res=i;
    }
    return res;
  }

  public void supprimerVente(){
    System.out.println("Rentrer le numéro de la vente");
    int num = saisieUtilisateur.nextInt();
    int numVente = chercheVente(num);
    ventes.get(numVente).deleteVente();
    ventes.remove(ventes.get(numVente));
  }

  public void afficherVente(){
    ventes.forEach((n) -> System.out.println(n.stringVente()));
  }

  public void creerEnchere(){
  }

  public void modifierEnchere(){
  }

  public int chercheEnchere(int num){
    int res = 0;
    for (int i=0;i<encheres.size() ;i++ ) {
      if(num == encheres.get(i).getnumEnchere())res=i;
    }
    return res;
  }

  public void supprimerEnchere(){
    System.out.println("Rentrer le numéro du enchere");
    int num = saisieUtilisateur.nextInt();
    int numEnchere = chercheEnchere(num);
    encheres.get(numEnchere).deleteEnchere();
    encheres.remove(encheres.get(numEnchere));
  }

  public void afficherEnchere(){
    encheres.forEach((n) -> System.out.println(n.stringEnchere()));
  }

  public String verifUtilisateur(String emailUtilisateur){
      String res = "";
      for (int i=0; i<utilisateurs.size(); i++) {
        if(utilisateurs.get(i).getemailUtilisateur().equals(emailUtilisateur))res="!----------- Cet email existe déjà -----------!";
      }
      return res;
  }

  public int verifUtilisateurMenu(String emailUtilisateur){
      int res = -1;
      for (int i=0; i<utilisateurs.size(); i++) {
        if(utilisateurs.get(i).getemailUtilisateur().equals(emailUtilisateur))res=i;
      }
      return res;
  }

}
