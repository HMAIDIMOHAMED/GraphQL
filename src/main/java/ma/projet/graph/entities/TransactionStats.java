package ma.projet.graph.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionStats {
    private int count;
    private double sumDepot;
    private double sumRetrait;
}