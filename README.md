Flahaut Steeven

Righetti Massimiliano

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

```

.
├── src/                  # Code source Java
├── test/                 # Tests unitaires Java
├── lib/                  # Bibliothèques tierces (JUnit)
├── docs/                 # Documentation générée (Javadoc)
├── Makefile              # Automatisation compilation, test, doc
└── .github/workflows/    # Workflow CI GitHub Actions

````

## Comment lancer le projet

### Prérequis

- Java 17 installé  
- Make installé (Linux/MacOS)  

### Compilation et lancement

TODO => suite au changement de make vers maven

### Lancer les tests

TODO => suite au changement de make vers maven

## Intégration Continue (CI)

Un workflow GitHub Actions est configuré pour :

* Compiler le code à chaque push sur `main`
* Télécharger JUnit console pour exécuter les tests
* Exécuter les tests automatiquement
* Nettoyer les fichiers générés après tests

Le fichier de workflow se trouve dans `.github/workflows/makefile.yml`.

## Qualité du code & documentation

* Documentation Javadoc générée avec `make docs`
* Les tests et la compilation sont automatisés via Makefile et GitHub Actions

## Prochaines étapes

* Ajout de rapports de couverture de tests
* Intégration d’outils de qualité de code (Checkstyle, SonarQube)
* Ajout de tests d’intégration et tests fonctionnels automatisés

## Contact

Pour toute question, contactez-moi via mon profil GitHub ou sur Linkedin.