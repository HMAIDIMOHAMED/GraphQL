package ma.projet.graph;

import ma.projet.graph.entities.Compte;
import ma.projet.graph.entities.Transaction;
import ma.projet.graph.entities.TypeCompte;
import ma.projet.graph.entities.TypeTransaction;
import ma.projet.graph.repositories.CompteRepository;
import ma.projet.graph.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class GraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphApplication.class, args);
	}


	@Bean
	CommandLineRunner start(CompteRepository compteRepository, TransactionRepository transactionRepository) {
		return args -> {
			Compte compte1 = compteRepository.save(new Compte(
					null,
					Math.random() * 9000,
					Instant.parse("2024-11-22T10:00:00Z"),
					TypeCompte.EPARGNE,
					null
			));

			Compte compte2 = compteRepository.save(new Compte(
					null,
					Math.random() * 9000,
					Instant.parse("2024-11-22T11:00:00Z"),
					TypeCompte.COURANT,
					null
			));

			Compte compte3 = compteRepository.save(new Compte(
					null,
					Math.random() * 9000,
					Instant.parse("2024-11-22T12:00:00Z"),
					TypeCompte.EPARGNE,
					null
			));

			transactionRepository.save(new Transaction(null, 2000, null, TypeTransaction.Retrait, compte1));
			transactionRepository.save(new Transaction(null, 1000, null, TypeTransaction.Depot, compte2));
			transactionRepository.save(new Transaction(null, 500, null, TypeTransaction.Retrait, compte3));
		};
	}

}
