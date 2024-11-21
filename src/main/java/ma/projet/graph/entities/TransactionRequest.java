package ma.projet.graph.entities;

public class TransactionRequest {

    private Long compteId;
    private double montant;
    private String date;
    private TypeTransaction type;

    public TransactionRequest(Long compteId, TypeTransaction type, String date, double montant) {
        this.compteId = compteId;
        this.type = type;
        this.date = date;
        this.montant = montant;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long compteId) {
        this.compteId = compteId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TransactionRequest(){
}
}
