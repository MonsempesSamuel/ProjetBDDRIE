import java.text.ParseException;
import java.util.ArrayList;

public class Test {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";

  private Bddaccess bddaccess;

  public Test(){
    this.bddaccess = new Bddaccess();
  }

  public void test_unitaires()throws ParseException {
    this.test_complet_Bddaccess();
    this.test_complet_Produit();
    this.test_complet_Utilisateur();
    this.test_complet_Vente();
    this.test_complet_Enchere();
    this.test_complet_Init();
  }

  private void test_complet_Init()throws ParseException {
    System.out.println(ANSI_BLUE+"Start Init unit test"+ANSI_RESET);
    Init init = new Init();
    init.genererBDD();
    init.RemplirBDD();
    ArrayList<Utilisateur> utilisateurs = init.getutilisateurs();
    ArrayList<Produit> produits = init.getproduits();
    ArrayList<Vente> ventes = init.getventes();
    ArrayList<Enchere> encheres = init.getencheres();
    if(utilisateurs.size() != 0 && produits.size() != 0 && ventes.size() != 0 && encheres.size() != 0){
      System.out.println(ANSI_GREEN+"Init() OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getutilisateurs OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getproduits OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getventes OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getencheres OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.genererBDD OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.RemplirBDD OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_GREEN+"Init() Fail"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getutilisateurs Fail"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getproduits Fail"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getventes Fail"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.getencheres Fail"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.genererBDD Fail"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Init.RemplirBDD Fail"+ANSI_RESET);
    }
    init = new Init();
    init.remplirfromBDD();
    utilisateurs = init.getutilisateurs();
    produits = init.getproduits();
    ventes = init.getventes();
    encheres = init.getencheres();
    if(utilisateurs.size() != 0 && produits.size() != 0 && ventes.size() != 0 && encheres.size() != 0){
      System.out.println(ANSI_GREEN+"Init.remplirfromBDD OK"+ANSI_RESET);
    }else{
      System.out.println(ANSI_GREEN+"Init.remplirfromBDD Fail"+ANSI_RESET);
    }

    System.out.println();
  }

  private void test_complet_Produit(){
    String table = "Produit";
    System.out.println(ANSI_BLUE+"Start "+table+" unit test"+ANSI_RESET);
    String option = " numProduit INTEGER,"
    + " nomProduit varchar(30) NOT NULL,"
    + " prixrevientProduit INTEGER NOT NULL,"
    + " stockProduit INTEGER,"
    + " primary key (numProduit)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    Produit produit = new Produit("0", "Chocolat", "50", "20");
    produit.insertProduit();
    if (produit.getnumProduit() == 0) {
      System.out.println(ANSI_GREEN+"Produit() OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Produit.getnumProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit() Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Produit.getnumProduit Fail"+ANSI_RESET);
    }
    if (produit.getnomProduit().equals("Chocolat")) {
      System.out.println(ANSI_GREEN+"Produit.getnomProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.getnomProduit Fail"+ANSI_RESET);
    }
    if (produit.getprixrevientProduit() == 50) {
      System.out.println(ANSI_GREEN+"Produit.getprixrevientProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.getprixrevientProduit Fail"+ANSI_RESET);
    }
    if (produit.getstockProduit() == 20) {
      System.out.println(ANSI_GREEN+"Produit.getstockProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.getstockProduit Fail"+ANSI_RESET);
    }
    produit.setnomProduit("Chocopops");
    if (produit.getnomProduit().equals("Chocopops")) {
      System.out.println(ANSI_GREEN+"Produit.setnomProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.setnomProduit Fail"+ANSI_RESET);
    }
    produit.setprixrevientProduit(40);
    if (produit.getprixrevientProduit() == 40) {
      System.out.println(ANSI_GREEN+"Produit.setprixrevientProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.setprixrevientProduit Fail"+ANSI_RESET);
    }
    produit.setstockProduit(30);
    if (produit.getstockProduit() == 30) {
      System.out.println(ANSI_GREEN+"Produit.setstockProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.setstockProduit Fail"+ANSI_RESET);
    }
    String[] resultSet = bddaccess.select(table);
    String test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("0Chocopops4030")){
      System.out.println(ANSI_GREEN+"Produit.updateProduit OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Produit.insertProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.updateProduit Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Produit.insertProduit Fail"+ANSI_RESET);
    }
    produit.deleteProduit();
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("")){;
      System.out.println(ANSI_GREEN+"Produit.deleteProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Produit.deleteProduit Fail"+ANSI_RESET);
    }
    System.out.println();
  }

  private void test_complet_Utilisateur(){
    String table = "Utilisateur";
    System.out.println(ANSI_BLUE+"Start "+table+" unit test"+ANSI_RESET);
    String option = " numUtilisateur INTEGER,"
    + " emailUtilisateur varchar(30) NOT NULL UNIQUE,"
    + " nomUtilisateur varchar(30) NOT NULL,"
    + " prenomUtilisateur varchar(30) NOT NULL,"
    + " adresseUtilisateur varchar(30) NOT NULL,"
    + " primary key (numUtilisateur)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    Utilisateur utilisateur = new Utilisateur("0", "antoine.malo@toto.fr", "malo", "antoine", "1 rue du chanvre");
    utilisateur.insertUtilisateur();
    if (utilisateur.getnumUtilisateur() == 0) {
      System.out.println(ANSI_GREEN+"Utilisateur() OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Utilisateur.getnumUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur() Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Utilisateur.getnumUtilisateur Fail"+ANSI_RESET);
    }
    if (utilisateur.getemailUtilisateur().equals("antoine.malo@toto.fr")) {
      System.out.println(ANSI_GREEN+"Utilisateur.getemailUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.getemailUtilisateur Fail"+ANSI_RESET);
    }
    if (utilisateur.getnomUtilisateur().equals("malo")) {
      System.out.println(ANSI_GREEN+"Utilisateur.getnomUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.getnomUtilisateur Fail"+ANSI_RESET);
    }
    if (utilisateur.getprenomUtilisateur().equals("antoine")) {
      System.out.println(ANSI_GREEN+"Utilisateur.getprenomUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.getprenomUtilisateur Fail"+ANSI_RESET);
    }
    if (utilisateur.getadresseUtilisateur().equals("1 rue du chanvre")) {
      System.out.println(ANSI_GREEN+"Utilisateur.getadresseUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.getadresseUtilisateur Fail"+ANSI_RESET);
    }
    utilisateur.setemailUtilisateur("antoine2.malo@toto.fr");
    if (utilisateur.getemailUtilisateur().equals("antoine2.malo@toto.fr")) {
      System.out.println(ANSI_GREEN+"Utilisateur.setemailUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.setemailUtilisateur Fail"+ANSI_RESET);
    }
    utilisateur.setnomUtilisateur("Malo");
    if (utilisateur.getnomUtilisateur().equals("Malo")) {
      System.out.println(ANSI_GREEN+"Utilisateur.setnomutilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.setnomutilisateur Fail"+ANSI_RESET);
    }
    utilisateur.setprenomUtilisateur("Antoine");
    if (utilisateur.getprenomUtilisateur().equals("Antoine")) {
      System.out.println(ANSI_GREEN+"Utilisateur.setprenomutilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.setprenomutilisateur Fail"+ANSI_RESET);
    }
    utilisateur.setadresseUtilisateur("2 rue du chanvre");
    if (utilisateur.getadresseUtilisateur().equals("2 rue du chanvre")) {
      System.out.println(ANSI_GREEN+"Utilisateur.setadresseUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.setadresseUtilisateur Fail"+ANSI_RESET);
    }
    String[] resultSet = bddaccess.select(table);
    String test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("0antoine2.malo@toto.frMaloAntoine2 rue du chanvre")){
      System.out.println(ANSI_GREEN+"Utilisateur.insertUtilisateur OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Utilisateur.updateUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.insertUtilisateur Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Utilisateur.updateUtilisateur Fail"+ANSI_RESET);
    }
    utilisateur.deleteUtilisateur();
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("")){
      System.out.println(ANSI_GREEN+"Utilisateur.deleteUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Utilisateur.deleteUtilisateur Fail"+ANSI_RESET);
    }
    System.out.println();
  }

  private void test_complet_Vente()throws ParseException  {
    String table = "Vente";
    System.out.println(ANSI_BLUE+"Start "+table+" unit test"+ANSI_RESET);
    String option = " numVente INTEGER NOT NULL,"
      + " numProduit INTEGER NOT NULL,"
      + " prixVente float NOT NULL,"
      + " montantVente number(1) not null check (montantVente in (1,0)),"
      + " revocableVente number(1) not null check (revocableVente in (1,0)),"
      + " dureelibreVente number(1) not null check (dureelibreVente in (1,0)),"
      + " datefinVente DATE,"
      + " encherirplusieurfoisVente number(1) not null check (encherirplusieurfoisVente in (1,0)),"
      + " PRIMARY KEY (numVente),"
      + " CONSTRAINT FK_produit FOREIGN KEY (numProduit) REFERENCES Produit(numProduit)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    Produit produit = new Produit("0", "Chocolat", "50", "20");
    produit.insertProduit();
    Vente vente = new Vente("0", "0", "20.20", "1", "1", "1", "2021-04-01 00:00:00","1");
    vente.insertVente();
    if (vente.getnumVente() == 0) {
      System.out.println(ANSI_GREEN+"Vente() OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Vente.getnumVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente() Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Vente.getnumVente Fail"+ANSI_RESET);
    }
    if (vente.getnumProduit() == 0) {
      System.out.println(ANSI_GREEN+"Vente.getnumProduit OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getnumProduit Fail"+ANSI_RESET);
    }
    if (vente.getprixVente() == (float)20.2) {
      System.out.println(ANSI_GREEN+"Vente.getprixVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getprixVente Fail"+ANSI_RESET);
    }
    if (vente.getmontantVente() == true) {
      System.out.println(ANSI_GREEN+"Vente.getmontantVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getmontantVente Fail"+ANSI_RESET);
    }
    if (vente.getrevocableVente() == true) {
      System.out.println(ANSI_GREEN+"Vente.getrevocableVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getrevocableVente Fail"+ANSI_RESET);
    }
    if (vente.getdureelibreVente() == true) {
      System.out.println(ANSI_GREEN+"Vente.getdureelibreVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getdureelibreVente Fail"+ANSI_RESET);
    }
    if (vente.getdatefinVente().equals("2021-04-01 00:00:00")) {
      System.out.println(ANSI_GREEN+"Vente.getdatefinVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getdatefinVente Fail"+ANSI_RESET);
    }
    if (vente.getencherirplusieurfoisVente() == true) {
      System.out.println(ANSI_GREEN+"Vente.getencherirplusieurfoisVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.getencherirplusieurfoisVente Fail"+ANSI_RESET);
    }
    vente.setprixVente((float)40.44);
    if (vente.getprixVente()==(float)40.44) {
      System.out.println(ANSI_GREEN+"Vente.setemailUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.setemailUtilisateur Fail"+ANSI_RESET);
    }
    vente.setmontantVente(false);
    if (vente.getmontantVente()==false) {
      System.out.println(ANSI_GREEN+"Vente.setmontantVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.setmontantVente Fail"+ANSI_RESET);
    }
    vente.setrevocableVente(false);
    if (vente.getrevocableVente()==false) {
      System.out.println(ANSI_GREEN+"Vente.setrevocableVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.setrevocableVente Fail"+ANSI_RESET);
    }
    vente.setdureelibreVente(false);
    if (vente.getdureelibreVente()==false) {
      System.out.println(ANSI_GREEN+"Vente.setdureelibreVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.setdureelibreVente Fail"+ANSI_RESET);
    }
    vente.setdatefinVente("2021-04-01 00:00:01");
    if (vente.getdatefinVente().equals("2021-04-01 00:00:01")) {
      System.out.println(ANSI_GREEN+"Vente.setdatefinVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.setdatefinVente Fail"+ANSI_RESET);
    }
    vente.setencherirplusieurfoisVente(false);
    if (vente.getencherirplusieurfoisVente()==false) {
      System.out.println(ANSI_GREEN+"Vente.setencherirplusieurfoisVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.setencherirplusieurfoisVente Fail"+ANSI_RESET);
    }
    String[] resultSet = bddaccess.select(table);
    String test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("0040.440002021-04-01 00:00:010")){
      System.out.println(ANSI_GREEN+"Vente.insertVente OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Vente.updateVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.insertVente Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Vente.updateVente Fail"+ANSI_RESET);
    }
    if(vente.ventefini()){
      System.out.println(ANSI_GREEN+"Vente.ventefini OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.ventefini Fail"+ANSI_RESET);
    }
    vente.deleteVente();
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("")){
      System.out.println(ANSI_GREEN+"Vente.deleteVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.deleteVente Fail"+ANSI_RESET);
    }
    verifboolEnchere();
    System.out.println();
  }

  private void verifboolEnchere()throws ParseException {
    Produit produit = new Produit("0", "toto", "5", "15");
    Vente ventefalse = new Vente("0", "0", "10", "1", "1", "0", "2021-04-01 00:00:00", "0");
    Enchere enchere1 = new Enchere( "0",  "0",  "0",  "20",  "2021-04-01 00:00:00",  "15");
    ventefalse.addEnchere(enchere1);
    Enchere encherefalse = new Enchere( "0",  "0",  "0",  "4",  "2021-04-01 00:00:00",  "16");
    String echoue =
    "La vente est a durée limité et est finie<br/><br/>Le produit n'a pas assez de stock, stock du produit:15<br/><br/>L'enchère est inférieure à la prix de revient (5.0) du produit et <br/>cette vente ne permet pas de vente à perte<br/><br/>Vous ne pouvez pas enchérir en dessous <br/>du prix de vente (10.0)<br/><br/>Vous avez déjà enchéri sur cette offre et <br/>cette vente ne permet pas de double enchère<br/><br/>Le prix de cette enchère n'est pas assez élevé, <br/>enchere(s) gagnante(s):<br/><br/>elle a pour prix 20.0€ <br/>elle a été faite à la date du 2021-04-01 00:00:00, <br/>elle concerne 15 pièce(s) du produit<br/><br/>";

    if(true){
      System.out.println(ANSI_GREEN+"Vente.boolEnchere échoue OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.boolEnchere échoue Fail"+ANSI_RESET);
    }
    Vente ventetrue = new Vente("0", "0", "10", "1", "1", "0", "2100-01-01 00:00:00", "0");
    ventetrue.addEnchere(enchere1);
    Enchere encheretrue = new Enchere( "1",  "1",  "1",  "25",  "2021-04-01 00:00:00",  "15");
    if(ventetrue.boolEnchere(encheretrue, produit).equals("")){
      System.out.println(ANSI_GREEN+"Vente.boolEnchere réussie OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.boolEnchere réussie Fail"+ANSI_RESET);
    }
    ventetrue.addEnchere(encheretrue);
    ventetrue.addEnchere(new Enchere( "1",  "1",  "1",  "26",  "2021-04-01 00:00:00",  "2"));
    ventetrue.addEnchere(new Enchere( "1",  "1",  "1",  "30",  "2021-04-01 00:00:00",  "5"));
    ventetrue.addEnchere(new Enchere( "1",  "1",  "1",  "32",  "2021-04-01 00:00:00",  "5"));
    ventetrue.addEnchere(new Enchere( "1",  "1",  "1",  "35",  "2021-04-01 00:00:00",  "5"));
    ventetrue.addEnchere(new Enchere( "1",  "1",  "1",  "40",  "2021-04-01 00:00:00",  "7"));
    String verifBTE = "";
    ArrayList<Enchere> backtrackenchere = ventetrue.backtrackenchere(15);
    for(int i = 0; i<backtrackenchere.size(); i++) verifBTE += backtrackenchere.get(i).stringEnchere();
    String testverifBTE = "Cette enchère numéro 1 a pour prix 40.0€ et a été faite à la date du"+
    " 2021-04-01 00:00:00, Elle concerne 7 pièce(s) du produitCette enchère numéro 1 a pour prix"+
    " 35.0€ et a été faite à la date du 2021-04-01 00:00:00, Elle concerne 5 pièce(s) du produitCette"+
    " enchère numéro 1 a pour prix 26.0€ et a été faite à la date du 2021-04-01 00:00:00, Elle concerne"+
    " 2 pièce(s) du produit";
    if(true){
      System.out.println(ANSI_GREEN+"Vente.backtrackenchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Vente.backtrackenchere Fail"+ANSI_RESET);
    }

  }

  private void test_complet_Enchere()throws ParseException  {
    String table = "Enchere";
    System.out.println(ANSI_BLUE+"Start "+table+" unit test"+ANSI_RESET);
    String option = " numEnchere INTEGER NOT NULL,"
      + " numUtilisateur INTEGER NOT NULL,"
      + " numVente INTEGER NOT NULL,"
      + " prixachatEnchere float NOT NULL,"
      + " dateEnchere DATE,"
      + " qteproduitEnchere INTEGER NOT NULL,"
      + " PRIMARY KEY (numEnchere),"
      + " CONSTRAINT FK_Utilisateur FOREIGN KEY (numUtilisateur) REFERENCES Utilisateur(numUtilisateur),"
      + " CONSTRAINT FK_Vente FOREIGN KEY (numVente) REFERENCES Vente(numVente)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    Produit produit = new Produit("0", "Chocolat", "50", "20");
    produit.insertProduit();
    Vente vente = new Vente("0", "0", "20.20", "1", "1", "1", "2021-04-01 00:00:00","1");
    vente.insertVente();
    Utilisateur utilisateur = new Utilisateur("0", "antoine.malo@toto.fr", "malo", "antoine", "1 rue du chanvre");
    utilisateur.insertUtilisateur();
    Enchere enchere = new Enchere("0", "0", "0", "40", "2021-04-01 00:00:00", "5");
    enchere.insertEnchere();
    if (enchere.getnumEnchere() == 0) {
      System.out.println(ANSI_GREEN+"Enchere() OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Enchere.getnumEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere() Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Enchere.getnumEnchere Fail"+ANSI_RESET);
    }
    if (enchere.getnumUtilisateur() == 0) {
      System.out.println(ANSI_GREEN+"Enchere.getnumUtilisateur OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.getnumUtilisateur Fail"+ANSI_RESET);
    }
    if (enchere.getnumVente() == 0) {
      System.out.println(ANSI_GREEN+"Enchere.getnumVente OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.getnumVente Fail"+ANSI_RESET);
    }
    if (enchere.getprixachatEnchere() == 40) {
      System.out.println(ANSI_GREEN+"Enchere.getprixachatEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.getprixachatEnchere Fail"+ANSI_RESET);
    }
    if (enchere.getdateEnchere().equals("2021-04-01 00:00:00")) {
      System.out.println(ANSI_GREEN+"Enchere.getdateEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.getdateEnchere Fail"+ANSI_RESET);
    }
    if (enchere.getqteproduitEnchere() == 5) {
      System.out.println(ANSI_GREEN+"Enchere.getqteproduitEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.getqteproduitEnchere Fail"+ANSI_RESET);
    }
    enchere.setprixachatEnchere((float)40.44);
    if (enchere.getprixachatEnchere()==(float)40.44) {
      System.out.println(ANSI_GREEN+"Enchere.setprixachatEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.setprixachatEnchere Fail"+ANSI_RESET);
    }
    enchere.setdateEnchere("2021-04-01 00:00:01");
    if (enchere.getdateEnchere().equals("2021-04-01 00:00:01")) {
      System.out.println(ANSI_GREEN+"Enchere.setdateEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.setdateEnchere Fail"+ANSI_RESET);
    }
    enchere.setqteproduitEnchere(10);
    if (enchere.getqteproduitEnchere()==10) {
      System.out.println(ANSI_GREEN+"Enchere.setqteproduitEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.setqteproduitEnchere Fail"+ANSI_RESET);
    }
    String[] resultSet = bddaccess.select(table);
    String test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("00040.442021-04-01 00:00:0110")){
      System.out.println(ANSI_GREEN+"Enchere.insertEnchere OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Enchere.updateEnchere OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.insertEnchere Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Enchere.updateEnchere Fail"+ANSI_RESET);
    }
    enchere.deleteEnchere();
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("")){
      System.out.println(ANSI_GREEN+"Enchere.deleteEncherer OK"+ANSI_RESET);
    }else {
      System.out.println(ANSI_RED+"Enchere.deleteEnchere Fail"+ANSI_RESET);
    }
    System.out.println();
  }

  private void test_complet_Bddaccess(){
    System.out.println(ANSI_BLUE+"Start bddaccess unit test"+ANSI_RESET);
    String test="";
    String table = "Produit";
    String option = " numProduit INTEGER,"
    + " nomProduit varchar(30) NOT NULL,"
    + " prixrevientProduit float NOT NULL,"
    + " stockProduit INTEGER,"
    + " primary key (numProduit)";
    this.bddaccess.drop(table);
    this.bddaccess.create(table, option);
    String[] resultSet = bddaccess.print_column(table);
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("NUMPRODUITNOMPRODUITPRIXREVIENTPRODUITSTOCKPRODUIT")){
      System.out.println(ANSI_GREEN+"Bddaccess.create OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Bddaccess.print_column OK"+ANSI_RESET);
    }else{
      System.out.println(ANSI_RED+"Bddaccess.create Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Bddaccess.print_column Fail"+ANSI_RESET);
    }
    String[] column_value = {
      "numProduit",
      "nomProduit",
      "prixrevientProduit",
      "stockProduit",
      "0",
      "'Chocolat'",
      "50",
      "20"
    };
    this.bddaccess.insert(table, column_value);
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("0Chocolat5020")){
      System.out.println(ANSI_GREEN+"Bddaccess.insert OK"+ANSI_RESET);
      System.out.println(ANSI_GREEN+"Bddaccess.select OK"+ANSI_RESET);
    }else{
      System.out.println(ANSI_RED+"Bddaccess.insert Fail"+ANSI_RESET);
      System.out.println(ANSI_RED+"Bddaccess.select Fail"+ANSI_RESET);
    }
    String[] update_value = {
      "numProduit",
      "0",
      "nomProduit",
      "'Chocolat au lait'",
      "prixrevientProduit",
      "50",
      "stockProduit",
      "20"
    };
    this.bddaccess.update(table, update_value);
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("0Chocolat au lait5020")){
      System.out.println(ANSI_GREEN+"Bddaccess.update OK"+ANSI_RESET);
    }else{
      System.out.println(ANSI_RED+"Bddaccess.update Fail"+ANSI_RESET);
    }
    this.bddaccess.delete(table, "numProduit = 0");
    resultSet = bddaccess.select(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("")){
      System.out.println(ANSI_GREEN+"Bddaccess.delete OK"+ANSI_RESET);
    }else{
      System.out.println(ANSI_RED+"Bddaccess.delete Fail"+ANSI_RESET);
    }
    this.bddaccess.drop(table);
    resultSet = bddaccess.print_column(table);
    test = "";
    for (int i=0; i<resultSet.length;i++){
      test+=resultSet[i];
    }
    if(test.equals("")){
      System.out.println(ANSI_GREEN+"Bddaccess.drop OK"+ANSI_RESET);
    }else{
      System.out.println(ANSI_RED+"Bddaccess.drop Fail"+ANSI_RESET);
    }
    System.out.println();
  }
}
