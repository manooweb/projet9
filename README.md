# Mise en place de l'environnement de développement

## Initialisation de la base de données
Télécharger MySql 8

Créer un utilisateur

Créer une nouvelle base de données nommée `livrai`

Appliquer le script SQL db/init-script.sql

## Lancement du serveur
Télécharger Eclipse (un jdk > 6 est nécessaire)

Importer le projet Maven dans Eclipse.

Télécharger tomcat 8.5 et l'ajouter à la vue Server d'Eclipse.

Ajouter le projet `app` au server.

Configurez la connexion a la base de donnees avec les variables d'environnement suivantes :

| Variable | Valeur par defaut |
| --- | --- |
| `LIVRAI_DB_HOST` | `localhost` |
| `LIVRAI_DB_PORT` | `3306` |
| `LIVRAI_DB_NAME` | `livrai` |
| `LIVRAI_DB_USER` | `root` |
| `LIVRAI_DB_PASSWORD` | `password` |

Les valeurs par defaut conservent le comportement historique. Pour une base de donnees differente, definissez les variables avant de lancer Tomcat. Par exemple :

```bash
export LIVRAI_DB_HOST=localhost
export LIVRAI_DB_PORT=3306
export LIVRAI_DB_NAME=livrai
export LIVRAI_DB_USER=root
export LIVRAI_DB_PASSWORD=password
```

Lancer le serveur.

Visiter l'URL localhost:8080/app

## 1ère connexion
Il est nécessaire de créer un utilisateur admin en base:
```
INSERT INTO user (email, name, password, admin) VALUES
  ('<email>', 'Livrai', '<password>', TRUE);

```
