import java.util.ArrayList;

public class Utilisateur {
  private int numUtilisateur;
  private String emailUtilisateur; // vérif email unique
  private String nomUtilisateur;
  private String prenomUtilisateur;
  private String adresseUtilisateur;
  private Bddaccess bddaccess;
  private String table = "Utilisateur";
  ArrayList<Enchere> encheres;

  public Utilisateur(String numUtilisateur, String emailUtilisateur, String nomUtilisateur, String prenomUtilisateur, String adresseUtilisateur){
    this.numUtilisateur = Integer.parseInt(numUtilisateur);
    this.emailUtilisateur = emailUtilisateur;
    this.nomUtilisateur = nomUtilisateur;
    this.prenomUtilisateur = prenomUtilisateur;
    this.adresseUtilisateur = adresseUtilisateur;
    this.bddaccess = new Bddaccess();
    this.encheres = new ArrayList<Enchere>();
  }

  public String stringUtilisateur(){
    String utilisateur = "L'utilisateur numéro "+String.valueOf(numUtilisateur)+" appelé "+prenomUtilisateur+" "+nomUtilisateur+" a pour email "+
    emailUtilisateur+" et pour adresse "+adresseUtilisateur;
    return utilisateur;
  }


  public void addEnchere(Enchere enchere){
    encheres.add(enchere);
  }

  public int getnumUtilisateur(){
    return this.numUtilisateur;
  }

  public String getemailUtilisateur(){
    return this.emailUtilisateur;
  }

  public String getnomUtilisateur(){
    return this.nomUtilisateur;
  }

  public String getprenomUtilisateur(){
    return this.prenomUtilisateur;
  }

  public String getadresseUtilisateur(){
    return this.adresseUtilisateur;
  }
  
  public ArrayList<Enchere> getencheres(){
    return this.encheres;
  }

  public void setemailUtilisateur(String emailUtilisateur){
    updateUtilisateur(getnumUtilisateur(), emailUtilisateur, getnomUtilisateur(), getprenomUtilisateur(), getadresseUtilisateur());
    this.emailUtilisateur = emailUtilisateur;
  }

  public void setnomUtilisateur(String nomUtilisateur){
    updateUtilisateur(getnumUtilisateur(), getemailUtilisateur(), nomUtilisateur, getprenomUtilisateur(), getadresseUtilisateur());
    this.nomUtilisateur = nomUtilisateur;
  }

  public void setprenomUtilisateur(String prenomUtilisateur){
    updateUtilisateur(getnumUtilisateur(), getemailUtilisateur(), getnomUtilisateur(), prenomUtilisateur, getadresseUtilisateur());
    this.prenomUtilisateur = prenomUtilisateur;
  }

  public void setadresseUtilisateur(String adresseUtilisateur){
    updateUtilisateur(getnumUtilisateur(), getemailUtilisateur(), getnomUtilisateur(), getprenomUtilisateur(), adresseUtilisateur);
    this.adresseUtilisateur = adresseUtilisateur;
  }

  public void updateUtilisateur(int numUtilisateur,String emailUtilisateur, String nomUtilisateur, String prenomUtilisateur, String adresseUtilisateur){
    String[] update_value = {
      "numUtilisateur",
      "'"+numUtilisateur+"'",
      "emailUtilisateur",
      "'"+emailUtilisateur+"'",
      "nomUtilisateur",
      "'"+nomUtilisateur+"'",
      "prenomUtilisateur",
      "'"+prenomUtilisateur+"'",
      "adresseUtilisateur",
      "'"+adresseUtilisateur+"'",
    };
    this.bddaccess.update(table, update_value);
  }

  public void deleteUtilisateur(){
    this.bddaccess.delete(this.table, "numUtilisateur="+String.valueOf(this.numUtilisateur));
  }

  public void insertUtilisateur(){
    String[] column_value = {
      "numUtilisateur",
      "emailUtilisateur",
      "nomUtilisateur",
      "prenomUtilisateur",
      "adresseUtilisateur",
      String.valueOf(this.numUtilisateur),
      "'"+this.emailUtilisateur+"'",
      "'"+this.nomUtilisateur+"'",
      "'"+this.prenomUtilisateur+"'",
      "'"+this.adresseUtilisateur+"'"
    };
    this.bddaccess.insert(this.table, column_value);
  }
}
