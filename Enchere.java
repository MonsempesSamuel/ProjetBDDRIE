import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Enchere {

  private int numEnchere;
  private int numUtilisateur;
  private int numVente;
  private float prixachatEnchere;
  private Calendar dateEnchere;
  private int qteproduitEnchere;
  private Bddaccess bddaccess;
  private String table = "Enchere";

  public Enchere(String numEnchere, String numUtilisateur, String numVente, String prixachatEnchere, String dateEnchere, String qteproduitEnchere)throws ParseException {
    this.numEnchere = Integer.parseInt(numEnchere);
    this.numUtilisateur = Integer.parseInt(numUtilisateur);
    this.numVente = Integer.parseInt(numVente);
    this.prixachatEnchere = Float.parseFloat(prixachatEnchere);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.dateEnchere = Calendar.getInstance();
    this.dateEnchere.setTime(sdf.parse(dateEnchere));
    this.qteproduitEnchere = Integer.parseInt(qteproduitEnchere);
    this.bddaccess = new Bddaccess();
  }

  public String stringEnchere(){
    String enchere = "Cette enchère numéro "+numEnchere+" a pour prix " + String.valueOf(prixachatEnchere) +
    "€, elle a été faite à la date du " + getdateEnchere() + ", elle concerne "+qteproduitEnchere+" pièce(s) du produit";
    return enchere;
  }

  public String stringEncheremenu(){
    String enchere = "elle a pour prix " + String.valueOf(prixachatEnchere) +
    "€ <br/>elle a été faite à la date du " + getdateEnchere() + ", <br/>elle concerne "+qteproduitEnchere+" pièce(s) du produit<br/><br/>";
    return enchere;
  }

  public int getnumEnchere(){
    return this.numEnchere;
  }

  public int getnumUtilisateur(){
    return this.numUtilisateur;
  }

  public int getnumVente(){
    return this.numVente;
  }

  public float getprixachatEnchere(){
    return this.prixachatEnchere;
  }

  public String getdateEnchere(){
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String res = format1.format(this.dateEnchere.getTime());
    return res;
  }

  public int getqteproduitEnchere(){
    return this.qteproduitEnchere;
  }

  public void setprixachatEnchere(float prixachatEnchere){
    updateEnchere(getnumEnchere(), getnumUtilisateur(), getnumVente(), prixachatEnchere, getdateEnchere(), getqteproduitEnchere());
    this.prixachatEnchere = prixachatEnchere;
  }

  public void setdateEnchere(String dateEnchere)throws ParseException {
    updateEnchere(getnumEnchere(), getnumUtilisateur(), getnumVente(), getprixachatEnchere(), dateEnchere, getqteproduitEnchere());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.dateEnchere.setTime(sdf.parse(dateEnchere));
  }

  public void setqteproduitEnchere(int qteproduitEnchere){
    updateEnchere(getnumEnchere(), getnumUtilisateur(), getnumVente(), getprixachatEnchere(), getdateEnchere(), qteproduitEnchere);
    this.qteproduitEnchere = qteproduitEnchere;
  }

  public void updateEnchere(int numEnchere, int numUtilisateur, int numVente, float prixachatEnchere, String dateEnchere, int setqteproduitEnchere){
    String[] update_value = {
      "numEnchere",
      String.valueOf(numEnchere),
      "numUtilisateur",
      String.valueOf(numUtilisateur),
      "numVente",
      String.valueOf(numVente),
      "prixachatEnchere",
      String.valueOf(prixachatEnchere),
      "dateEnchere",
      "TO_DATE('"+getdateEnchere()+"','YYYY-MM-DD HH24:MI:SS')",
      "qteproduitEnchere",
      String.valueOf(setqteproduitEnchere)
    };
    this.bddaccess.update(table, update_value);
  }

  public void deleteEnchere(){
    this.bddaccess.delete(this.table, "numEnchere="+String.valueOf(this.numEnchere));
  }

  public void insertEnchere(){
    String[] column_value = {
      "numEnchere",
      "numUtilisateur",
      "numVente",
      "prixachatEnchere",
      "dateEnchere",
      "qteproduitEnchere",
      String.valueOf(numEnchere),
      String.valueOf(numUtilisateur),
      String.valueOf(numVente),
      String.valueOf(prixachatEnchere),
      "TO_DATE('"+getdateEnchere()+"','YYYY-MM-DD HH24:MI:SS')",
      String.valueOf(qteproduitEnchere)
    };
    this.bddaccess.insert(this.table, column_value);
  }
}
