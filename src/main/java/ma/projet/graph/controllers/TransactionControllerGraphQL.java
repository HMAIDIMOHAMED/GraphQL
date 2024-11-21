package ma.projet.graph.controllers;

import lombok.AllArgsConstructor;
import ma.projet.graph.entities.*;
import ma.projet.graph.repositories.CompteRepository;
import ma.projet.graph.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class TransactionControllerGraphQL {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @MutationMapping
    public Transaction addTransaction(@Argument("transaction") TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            throw new RuntimeException("Le paramètre transactionRequest est null !");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(transactionRequest.getDate());
        } catch (Exception e) {
            throw new RuntimeException("Format de date invalide : " + transactionRequest.getDate());
        }

        Compte compte = compteRepository.findById(transactionRequest.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec l'ID : " + transactionRequest.getCompteId()));

        Transaction transaction = new Transaction();
        transaction.setCompte(compte);
        transaction.setMontant(transactionRequest.getMontant());
        transaction.setDate(parsedDate);
        transaction.setType(transactionRequest.getType());
        return transactionRepository.save(transaction);
    }


    @QueryMapping
    public TransactionStats totalTransactions() {
        long count = transactionRepository.count();
        double sumDepot = transactionRepository.sumByType(TypeTransaction.Depot);
        double sumRetrait = transactionRepository.sumByType(TypeTransaction.Retrait);

        return new TransactionStats(
                (int) count,
                sumDepot,
                sumRetrait
        );
    }

    @QueryMapping
    public List<Transaction> allTransactions(){
        return transactionRepository.findAll();
    }

    @QueryMapping
    public List<Transaction> transactionsByCompteId(@Argument Long compteId) {
        return transactionRepository.findByCompteId(compteId);
    }
}

