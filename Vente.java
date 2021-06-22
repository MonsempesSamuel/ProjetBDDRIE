import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Vente {

  private int numVente;
  private int numProduit;
  private float prixVente;
  private Boolean montantVente;
  private Boolean revocableVente;
  private Boolean dureelibreVente;
  private Calendar datefinVente;
  private Boolean encherirplusieurfoisVente;
  ArrayList<Enchere> encheres;
  private Bddaccess bddaccess;
  private String table = "Vente";


  public Vente(String numVente, String numProduit, String prixVente, String montantVente, String revocableVente, String dureelibreVente, String datefinVente, String encherirplusieurfoisVente)throws ParseException {
    this.numVente = Integer.parseInt(numVente);
    this.numProduit = Integer.parseInt(numProduit);
    this.prixVente = Float.parseFloat(prixVente);
    this.montantVente = !montantVente.equals("0");
    this.revocableVente = !revocableVente.equals("0");
    this.dureelibreVente = !dureelibreVente.equals("0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.datefinVente = Calendar.getInstance();
    this.datefinVente.setTime(sdf.parse(datefinVente));
    this.encherirplusieurfoisVente = !encherirplusieurfoisVente.equals("0");
    this.bddaccess = new Bddaccess();
    this.encheres = new ArrayList<Enchere>();
  }

  public String stringVente(){
    String montante = (montantVente)?"montante":"descendante";
    String revocable = (revocableVente)?"n'est pas pas perte":"peut être à perte";
    String libre = (dureelibreVente)?"n'est pas":"est";
    String date = (!dureelibreVente)?": fini à la date suivante "+getdatefinVente():"";
    String enchere = (encherirplusieurfoisVente)?"pouvez":"ne pouvez pas";
    String vente ="Cette vente numéro "+String.valueOf(numVente)+" démarre au prix de " +String.valueOf(prixVente)+
    "€ cette vente est "+ montante+ ", elle "+revocable+", elle "+libre+ " limité en temps"+
    date+", vous "+enchere+" enchérir plusieurs fois.";
    return vente;
  }

  public String stringVentemenu(){
    String montante = (montantVente)?"montante":"descendante";
    String revocable = (revocableVente)?"n'est pas pas perte":"peut être à perte";
    String libre = (dureelibreVente)?"n'est pas":"est";
    String date = ":<br/> fini à la date suivante "+getdatefinVente();
    String enchere = (encherirplusieurfoisVente)?"pouvez":"ne pouvez pas";
    String vente ="Cette vente démarre au prix de " +String.valueOf(prixVente)+
    "€ <br/> elle est "+ montante+ ", elle "+revocable+",<br/> elle "+libre+ " limité en temps"+
    date+",<br/> vous "+enchere+" enchérir plusieurs fois.<br/><br/><br/>";
    return vente;

  }

  private Boolean trouveUtilisateurdoublon(int num){
    Boolean res = false;
    for (int i=0; i<encheres.size(); i++){
      res = res || encheres.get(i).getnumUtilisateur() == num;
    }
    return res;
  }

  public String boolEnchere(Enchere enchere, Produit produit){
    String res = "";
    if(!getdureelibreVente()&&ventefini()){
      res += "La vente est a durée limité et est finie<br/><br/>";
    }
    if(enchere.getqteproduitEnchere()>produit.getstockProduit()){
      res += "Le produit n'a pas assez de stock, stock du produit:"+ String.valueOf(produit.getstockProduit())+"<br/><br/>";
    }
    if (getrevocableVente() && enchere.getprixachatEnchere()<produit.getprixrevientProduit()) {
      res += "L'enchère est inférieure à la prix de revient ("+produit.getprixrevientProduit()+") du <br/>produit et cette vente ne permet pas de vente à perte<br/><br/>";
    }
    if (enchere.getprixachatEnchere()<getprixVente()) {
      res += "Vous ne pouvez pas enchérir en dessous <br/>du prix de vente ("+getprixVente()+")<br/><br/>";
    }
    if(!getencherirplusieurfoisVente()&&trouveUtilisateurdoublon(enchere.getnumUtilisateur())){
      res += "Vous avez déjà enchéri sur cette offre et <br/>cette vente ne permet pas de double enchère<br/><br/>";
    }
    ArrayList<Enchere> backtrackenchere = backtrackenchere(produit.getstockProduit());
    if (!verifStock( produit, enchere, backtrackenchere)) {
      res += "Le prix de cette enchère n'est pas assez élevé, <br/>enchere(s) gagnante(s):<br/><br/>";
      for (int i=0; i<backtrackenchere.size(); i++) {
        res += backtrackenchere.get(i).stringEncheremenu();
      }
    }
    return res;
  }

  public Boolean verifStock(Produit produit, Enchere enchere, ArrayList<Enchere> backtrackenchere){
      int stock = produit.getstockProduit();
      Boolean res = false;
      for (int i = 0; i<backtrackenchere.size(); i++) {
        stock-=backtrackenchere.get(i).getqteproduitEnchere();
        if(enchere.getqteproduitEnchere()<= stock||(backtrackenchere.get(i).getprixachatEnchere()<enchere.getprixachatEnchere()))res=true;
      }
      if(backtrackenchere.size()==0)res=true;
      return res;
  }

  public ArrayList<Enchere> backtrackenchere(int stock){
    ArrayList<Enchere> res = new ArrayList<Enchere>();
    if(encheres.size()!=0){
      for (int i = encheres.size()-1; i>=0; i--) {
        if(encheres.get(i).getqteproduitEnchere()<=stock){
          stock -= encheres.get(i).getqteproduitEnchere();
          res.add(encheres.get(i));
        }
      }
    }
    return res;
  }

  public void addEnchere(Enchere enchere){
    encheres.add(enchere);
  }

  public Boolean ventefini(){
    Date date=new java.util.Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return this.datefinVente.compareTo(calendar) == -1;
  }

  public ArrayList<Enchere> getencheres(){
    return this.encheres;
  }

  public int getnumVente(){
    return this.numVente;
  }

  public int getnumProduit(){
    return this.numProduit;
  }

  public float getprixVente(){
    return this.prixVente;
  }

  public Boolean getmontantVente(){
    return this.montantVente;
  }

  public Boolean getrevocableVente(){
    return this.revocableVente;
  }

  public Boolean getdureelibreVente(){
    return this.dureelibreVente;
  }

  public String getdatefinVente(){
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String res = format1.format(this.datefinVente.getTime());
    return res;
  }

  public Boolean getencherirplusieurfoisVente(){
    return this.encherirplusieurfoisVente;
  }

  public void setprixVente(float prixVente){
    updateVente(getnumVente(), getnumProduit(), prixVente, getmontantVente(), getrevocableVente(), getdureelibreVente(), getdatefinVente(), getencherirplusieurfoisVente());
    this.prixVente = prixVente;
  }

  public void setmontantVente(Boolean montantVente){
    updateVente(getnumVente(), getnumProduit(), getprixVente(), montantVente, getrevocableVente(), getdureelibreVente(), getdatefinVente(), getencherirplusieurfoisVente());
    this.montantVente = montantVente;
  }

  public void setrevocableVente(Boolean revocableVente){
    updateVente(getnumVente(), getnumProduit(), getprixVente(), getmontantVente(), revocableVente, getdureelibreVente(), getdatefinVente(), getencherirplusieurfoisVente());
    this.revocableVente = revocableVente;
  }

  public void setdureelibreVente(Boolean dureelibreVente){
    updateVente(getnumVente(), getnumProduit(), getprixVente(), getmontantVente(), getrevocableVente(), dureelibreVente, getdatefinVente(), getencherirplusieurfoisVente());
    this.dureelibreVente = dureelibreVente;
  }

  public void setdatefinVente(String datefinVente)throws ParseException {
    updateVente(getnumVente(), getnumProduit(), getprixVente(), getmontantVente(), getrevocableVente(), getdureelibreVente(), datefinVente, getencherirplusieurfoisVente());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.datefinVente.setTime(sdf.parse(datefinVente));
  }

  public void setencherirplusieurfoisVente(Boolean encherirplusieurfoisVente){
    updateVente(getnumVente(), getnumProduit(), getprixVente(), getmontantVente(), getrevocableVente(), getdureelibreVente(), getdatefinVente(), encherirplusieurfoisVente);
    this.encherirplusieurfoisVente = encherirplusieurfoisVente;
  }

  public void updateVente(int numVente, int numProduit, float prixVente, Boolean montantVente, Boolean revocableVente, Boolean dureelibreVente, String datefinVente, Boolean encherirplusieurfoisVente){
    String[] update_value = {
      "numVente",
      String.valueOf(numVente),
      "numProduit",
      String.valueOf(numProduit),
      "prixVente",
      String.valueOf(prixVente),
      "montantVente",
      (montantVente)?"1":"0",
      "revocableVente",
      (revocableVente)?"1":"0",
      "dureelibreVente",
      (dureelibreVente)?"1":"0",
      "datefinVente",
      "TO_DATE('"+getdatefinVente()+"','YYYY-MM-DD HH24:MI:SS')",
      "encherirplusieurfoisVente",
      (encherirplusieurfoisVente)?"1":"0"
    };
    this.bddaccess.update(table, update_value);
  }

  public void deleteVente(){
    this.bddaccess.delete(this.table, "numVente="+String.valueOf(this.numVente));
  }

  public void insertVente(){
    String[] column_value = {
      "numVente",
      "numProduit",
      "prixVente",
      "montantVente",
      "revocableVente",
      "dureelibreVente",
      "datefinVente",
      "encherirplusieurfoisVente",
      String.valueOf(this.numVente),
      String.valueOf(this.numProduit),
      String.valueOf(this.prixVente),
      (this.montantVente)?"1":"0",
      (this.revocableVente)?"1":"0",
      (this.dureelibreVente)?"1":"0",
      "TO_DATE('"+getdatefinVente()+"','YYYY-MM-DD HH24:MI:SS')",
      (this.encherirplusieurfoisVente)?"1":"0"
    };
    this.bddaccess.insert(this.table, column_value);
  }
}
