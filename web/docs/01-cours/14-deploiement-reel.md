---
title: Déploiement réel
description: Choisir un hébergeur, une BD séparée, un pipeline relié au repo git, et pointer l'app Android vers la production
hide_table_of_contents: true
---

# Déploiement réel

<Row>

<Column>

:::tip Avant la séance

- Ton serveur fil rouge passe ses tests en local.
- Relis l'ancienne page [Déploiement Render](deploiement) : on part de là, mais cette fois **avec une vraie BD séparée** et un **pipeline**.

:::

</Column>

<Column>

:::info Séance 1 : architecture

- En local, tout tourne sur ton poste : serveur + H2 en mémoire ou dans un fichier.
- En production : le **serveur** chez un hébergeur, la **BD** (PostgreSQL) chez un autre service ou un autre conteneur, le **code** sur GitHub. Trois endroits, reliés par des URL et des secrets.
- Le pipeline : un push sur `main` → tests → construction → déploiement.

:::

:::info Séance 2 : la prod au quotidien

- Variables d'environnement et secrets (jamais dans le repo).
- Profils Spring : `application.properties` (local, H2) et `application-prod.properties` (PostgreSQL).
- Lire les logs de production, quand on ne peut plus mettre de point d'arrêt.
- L'app Android qui pointe vers l'URL de production.

:::

</Column>

</Row>

## Exercice 1 : choisir <Etiquette type="main" />

Dans `deploiement/CHOIX.md`, compare **au moins 3 hébergeurs** pour le serveur (ex. Render, Railway, Fly.io, Google Cloud Run, Azure App Service) et **2 services de BD** PostgreSQL (ex. Neon, Supabase, la BD de l'hébergeur). Pour chacun :

- gratuit ou non, et ce qui arrive à la fin du gratuit (mise en veille, suppression des données?);
- déploiement depuis un repo GitHub? par Docker?
- région (où sont les données de ton client?);
- comment on lit les logs.

Les conditions changent souvent : cite la page consultée et la date. Termine par ton choix et pourquoi.

## Exercice 2 : la BD séparée <Etiquette type="main" />

1. Crée la BD PostgreSQL chez le service choisi.
2. Ajoute le pilote PostgreSQL au `build.gradle` et crée `application-prod.properties` :

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USER}
spring.datasource.password=${DATABASE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

3. Lance le serveur **en local** contre la BD distante avec `SPRING_PROFILES_ACTIVE=prod` et les variables d'environnement. Vérifie avec Bruno.

## Exercice 3 : le pipeline <Etiquette type="claude" />

Demande à Claude :

- un workflow GitHub Actions qui lance `./gradlew test` à chaque push et à chaque PR;
- le déploiement automatique chez ton hébergeur quand `main` passe les tests (intégration git de l'hébergeur ou *deploy hook*).

Puis vérifie **à la main** : casse volontairement un test, pousse, et confirme que **rien n'est déployé**.

## Exercice 4 : la prod <Etiquette type="main" />

1. Configure les variables d'environnement chez l'hébergeur (jamais dans le repo).
2. Change l'URL de base Retrofit de ton app Android vers la production. Le `10.0.2.2` ne marche plus : pourquoi?
3. Crée un utilisateur depuis l'app, puis retrouve-le **dans la BD** de production.
4. Provoque une erreur 500 et retrouve sa trace dans les logs de l'hébergeur.
5. Dans `deploiement/SCHEMA.md`, dessine qui parle à qui : les deux apps, ton serveur, ta BD, le Dossier scolaire, GitHub, le pipeline. Pour chaque flèche : protocole et secret utilisé.
