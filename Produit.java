import java.util.ArrayList;

public class Produit {

  private int numProduit;
  private String nomProduit;
  private float prixrevientProduit;
  private int stockProduit;
  ArrayList<Vente> ventes;
  private Bddaccess bddaccess;
  private String table = "Produit";

  public Produit(String numProduit, String nomProduit, String prixrevientProduit, String stockProduit){
    this.numProduit = Integer.parseInt(numProduit);
    this.nomProduit = nomProduit;
    this.prixrevientProduit = Float.parseFloat(prixrevientProduit);
    this.stockProduit = Integer.parseInt(stockProduit);
    this.bddaccess = new Bddaccess();
    this.ventes = new ArrayList<Vente>(); ;
  }

  public String stringProduit(){
    String produit = "Ce produit numéro "+String.valueOf(numProduit)+" nommé "+ nomProduit +
    " a pour prix de revient " + String.valueOf(prixrevientProduit) + "€ et a un stock de "+
    String.valueOf(stockProduit) + " pièce(s).";
    return produit;
  }

  public String stringProduitmenu(){
    String produit = "Nom:"+ nomProduit +
    " Prix:" + String.valueOf(prixrevientProduit) + "€ Stock: "+
    String.valueOf(stockProduit);
    return produit;
  }

  public String boolVente(){
    String res = "";
    if(ventes.size() != 0 && !ventes.get(ventes.size()-1).getdureelibreVente()
    && !ventes.get(ventes.size()-1).ventefini()){
      res = "La précédente vente sur ce produit n'est pas terminée";
    }else if(stockProduit ==0){
      res = "Le produit n'a plus de stock";
    }
    return res;
  }

  public void addVente(Vente vente){
    ventes.add(vente);
  }

  public int getnumProduit(){
    return this.numProduit;
  }
  public String getnomProduit(){
    return this.nomProduit;
  }
  public float getprixrevientProduit(){
    return this.prixrevientProduit;
  }
  public int getstockProduit(){
    return this.stockProduit;
  }
  public ArrayList<Vente> getventes(){
    return this.ventes;
  }

  public void setnomProduit(String nomProduit){
    updateProduit(getnumProduit(), nomProduit, getprixrevientProduit(), getstockProduit());
    this.nomProduit = nomProduit;
  }

  public void setprixrevientProduit(float prixrevientProduit){
    updateProduit(getnumProduit(), getnomProduit(), prixrevientProduit, getstockProduit());
    this.prixrevientProduit = prixrevientProduit;
  }

  public void setstockProduit(int stockProduit){
    updateProduit(getnumProduit(), getnomProduit(), getprixrevientProduit(), stockProduit);
    this.stockProduit = stockProduit;
  }

  public void updateProduit(int numProduit, String nomProduit, float prixrevientProduit, int stockProduit){
    String[] update_value = {
      "numProduit",
      String.valueOf(numProduit),
      "nomProduit",
      "'"+nomProduit+"'",
      "prixrevientProduit",
      String.valueOf(prixrevientProduit),
      "stockProduit",
      String.valueOf(stockProduit)
    };
    this.bddaccess.update(table, update_value);
  }

  public void deleteProduit(){
    this.bddaccess.delete(this.table, "numProduit="+String.valueOf(this.numProduit));
  }

  public void insertProduit(){
    String[] column_value = {
      "numProduit",
      "nomProduit",
      "prixrevientProduit",
      "stockProduit",
      String.valueOf(this.numProduit),
      "'"+this.nomProduit+"'",
      String.valueOf(this.prixrevientProduit),
      String.valueOf(this.stockProduit)
    };
    this.bddaccess.insert(this.table, column_value);
  }
}
