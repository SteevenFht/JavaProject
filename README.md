Flahaut Steeven


```markdown
# Sports Competition - Portfolio QA

## Description

Ce projet Java simule une compétition sportive avec plusieurs modules (compétition, sélection des concurrents, gestion des matchs, etc.).

Ce dépôt sert également de portfolio pour démontrer mes compétences en assurance qualité (QA) à travers :

- La mise en place de tests unitaires automatisés (JUnit)  
- L’intégration d’un pipeline CI avec GitHub Actions  
- La génération de documentation (Javadoc)  
- La gestion de la qualité du code  

## Technologies utilisées

- Java 17  
- JUnit 5 (plateforme console)  
- GitHub Actions pour CI/CD  

## Structure du projet

.
├── .github/
│   └── workflows/             # Définition des workflows CI/CD avec GitHub Actions
├── .scannerwork/              # Configuration pour SonarQube (analyse de la qualité du code)
├── src/                       # Code source Java
├── uml/                       # Diagrammes UML du projet
├── appli.jar                  # Fichier JAR exécutable du projet
├── pom.xml                    # Fichier de configuration Maven
├── sonar-project.properties   # Paramètres de configuration pour SonarQube
├── .gitignore                 # Fichiers et répertoires à ignorer par Git
└── README.md                  # Documentation du projet


````

## Comment lancer le projet

## Prérequis

- Java 17 installé  
- Maven installé (`mvn` disponible en ligne de commande)

## Compilation et exécution

```bash
# Nettoyer et compiler le projet
mvn clean package

# Lancer le programme
java -jar target/appli.jar
```

### Lancer les tests

```bash
mvn test
```

## Intégration Continue (CI)

Un workflow GitHub Actions est configuré pour :

* Compiler automatiquement le projet à chaque push

* Exécuter les tests avec JUnit

* Nettoyer les fichiers temporaires générés

 Le fichier de workflow se trouve dans : .github/workflows/tests.yml


## Qualité du code & documentation

* Documentation : générée via Javadoc (mvn javadoc:javadoc)
* Analyse de qualité : support partiel via SonarQube (sonar-scanner)

## Prochaines étapes

* Génération automatique de rapports de couverture (JaCoCo)
* Intégration complète de SonarQube
* Ajout de règles Checkstyle
* Implémentation de tests d’intégration et tests fonctionnels

## Contact

Pour toute question, contactez-moi via mon profil GitHub ou sur Linkedin.
