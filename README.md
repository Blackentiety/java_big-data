# Projet Nexus ERP : Pipeline de traitement de données

## Description

Ce projet implémente un pipeline de données pour l'entreprise Nexus suite à une défaillance réseau. L'application sécurise l'accès au système, nettoie les logs corrompus via un moteur Spark, et archive les données traitées de manière optimisée.

## Arborescence du projet

```text
Nexus_Project/
├── archives_logs_serveurs/    # Dossier généré après exécution (ignoré par Git)
├── sql/
│   └── dump.sql               # Script de création et d'initialisation DB
├── src/
│   └── main/
│       └── java/
│           └── com/tyrostudent/intranet/
│               ├── Main.java        # Orchestrateur principal
│               ├── DataEngine.java  # Logique Spark et JDBC
│               └── SystemBoot.java  # Sécurité et initialisation
├── .gitignore                 # Exclusion des fichiers temporaires et builds
├── pom.xml                    # Dépendances Maven (Spark, MySQL)
└── README.md                  # Documentation du projet

```

## Installation et Prérequis

### 1. Base de données

Le projet nécessite un serveur MySQL. Pour initialiser l'environnement, importez le fichier de dump :

```bash
mysql -u [utilisateur] -p < sql/dump.sql

```

La base `nexus_erp` sera créée avec une table `server_logs` contenant 10 entrées (dont 2 lignes corrompues avec `region = NULL`).

### 2. Configuration JVM

Pour une exécution sous Java 17+, les options de machine virtuelle (VM Options) suivantes doivent être configurées dans l'IDE :

```text
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.invoke=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.net=ALL-UNNAMED
--add-opens=java.base/java.nio=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED
--add-opens=java.base/sun.nio.ch=ALL-UNNAMED
--add-opens=java.base/sun.nio.cs=ALL-UNNAMED
--add-opens=java.base/sun.security.action=ALL-UNNAMED
--add-opens=java.util.prefs=ALL-UNNAMED

```

## Fonctionnement du Pipeline

1. **Vérification de sécurité** : Le module `SystemBoot` valide le niveau d'accréditation (requis >= 5).
2. **Traitement Spark** :
* Connexion JDBC à la base `nexus_erp`.
* Filtrage des données pour éliminer les régions `NULL`.
* Tri ascendant par région, puis par nom de serveur.


3. **Archivage** : Les données sont exportées au format JSON avec un partitionnement par région dans le dossier `archives_logs_serveurs`. Le mode `Overwrite` est utilisé pour rafraîchir les données à chaque exécution.