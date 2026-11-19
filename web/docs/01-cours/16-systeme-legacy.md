---
title: Le légacy Omnivax
description: Séance 16 - Léo2000 déclare et consulte les notes finales dans Omnivax, un vieux système PHP + MySQL - intégration serveur-serveur
hide_table_of_contents: true
---

# Le légacy Omnivax : déclarer les notes finales

<Row>

<Column>

:::tip Avant la séance

- Installe **Docker Desktop**.
- Récupère le `docker-compose.yml` fourni par le professeur et démarre Omnivax (et sa BD MySQL) :

```bash
docker compose up omnivax
```

- Omnivax écoute sur `http://localhost:9090`. Ouvre `http://localhost:9090/releve.php` et cherche le matricule `2027001`.
- Lis son `LISEZMOI.md` : c'est la seule documentation qui existe.

:::

</Column>

<Column>

:::info Séance

Dans la vraie vie, ton serveur n'est presque jamais seul. Omnivax, le dossier scolaire de l'établissement, est **vieux** (PHP, MySQL, écrit en 2009 par Skytek) et **personne ne va le modifier pour toi** :

- Léo2000 devient **client HTTP** d'un autre serveur (`RestClient`);
- un autre format de données : il faut **traduire** tes DTO vers les siens (`snake_case`, notes entières, `annee` + lettre de `session`, dates `jj/mm/aaaa`);
- un autre système qui peut être lent, en panne, ou répondre `200` avec une erreur dans le corps;
- un secret de plus : la clé d'API d'Omnivax (en-tête `X-Cle-Api`);
- déclarer deux fois la même note finale ne doit **pas** créer deux résultats.

Docker : une **image** (Omnivax empaqueté avec PHP et Apache) et un **conteneur** (une image qui tourne). `docker compose` en démarre plusieurs qui se parlent par leur nom (`omnivax`, `omnivax-bd`).

:::

</Column>

</Row>

## Exercice 1 : comprendre le vieux système <Etiquette type="main" />

Sans IA, avec Bruno et le `LISEZMOI.md` (clé de développement : `cle-dev`), dans `serveur/LEGACY.md` :

1. Déclare à la main les notes finales de deux étudiants en une requête. Quelle requête exacte (verbe, URL, en-têtes, corps)?
2. Consulte-les avec `consulter.php`. Quel format de date reçois-tu?
3. Quelles différences avec l'API de Léo2000 : noms des champs, format des notes, de la session, des dates?
4. Envoie une déclaration avec un matricule invalide. Quel code HTTP reçois-tu? Où est l'erreur?
5. Déclare deux fois la même note finale. Que se passe-t-il?
6. Arrête Omnivax (`docker compose stop omnivax`) et dis ce que **Léo2000** devra faire dans ce cas.

## Exercice 2 : le client du légacy <Etiquette type="claude" />

Avec ton `LEGACY.md` comme spécification et les deux routes `notes-finales` de l'API du [TP2](../tp/tp2), demande à Claude :

- un `@Service` `ServiceOmnivax` dans Léo2000 qui utilise `RestClient`;
- la traduction des DTO de Léo2000 vers ceux d'Omnivax (`H27` devient `annee: 2027`, `session: "H"`);
- l'URL et la clé lues de la configuration (`OMNIVAX_URL`, `OMNIVAX_CLE`), jamais dans le code;
- `POST /api/groupes/{id}/notes-finales` (déclarer) et `GET /api/groupes/{id}/notes-finales` (consulter), enseignant du groupe seulement;
- des tests avec un faux Omnivax (pas besoin du vrai pour tester).

## Exercice 3 : quand le vieux système flanche <Etiquette type="main" />

Vérifie à la main, et corrige si nécessaire :

1. Omnivax arrêté : l'enseignant reçoit-il un message clair dans l'app, ou une erreur 500?
2. Omnivax lent (`OMNIVAX_LENT_MS=8000 docker compose up omnivax`) : Léo2000 attend-il indéfiniment? Ajoute un délai d'expiration, et vérifie quelle exception arrive vraiment quand il est dépassé.
3. Réponse `200` avec `"statut": "ERREUR"` sur une ligne : Léo2000 la compte-t-il comme un succès?
4. L'enseignant appuie deux fois sur « Déclarer » : combien de résultats dans Omnivax?
5. Combien d'objets `RestClient` sont créés si 30 enseignants déclarent en même temps? (révise le [cycle de vie Spring](cycle-vie-spring))

:::caution Omnivax n'est pas sûr
Omnivax a été écrit avant que son équipe entende parler de sécurité. Tu y reviendras aux séances de cybersécurité et au TP3. D'ici là, ne mets dans ta copie locale que des données de test.
:::
