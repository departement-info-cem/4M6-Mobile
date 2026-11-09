---
title: Système légacy
description: Le serveur de notes déclare les notes finales à un vieux système (Spring Boot 2, BD minimale) - intégration serveur-serveur
hide_table_of_contents: true
---

# Système légacy : déclarer les notes finales

<Row>

<Column>

:::tip Avant la séance

- Ton serveur de notes calcule la moyenne pondérée d'un étudiant dans un groupe.
- Télécharge le **Dossier scolaire** fourni par le professeur (un `.jar`) et lance-le :

```bash
java -jar dossier-scolaire.jar --server.port=9090
```

- Lis son `LISEZMOI.md` : c'est la seule documentation qui existe.

:::

</Column>

<Column>

:::info Séance

Dans la vraie vie, ton serveur n'est presque jamais seul. Le Dossier scolaire de l'établissement est **vieux** (Spring Boot 2, Java 11, une seule table) et **personne ne va le modifier pour toi** :

- ton serveur devient **client HTTP** d'un autre serveur (`RestClient`);
- un autre format de données : il faut **traduire** tes DTO vers les siens;
- un autre système qui peut être lent, en panne, ou répondre `200` avec une erreur dans le corps;
- un secret de plus : la clé d'API du Dossier scolaire;
- déclarer deux fois la même note finale ne doit **pas** créer deux résultats.

:::

</Column>

</Row>

## Exercice 1 : comprendre le vieux système <Etiquette type="main" />

Sans IA, avec Bruno et le `LISEZMOI.md`, dans `serveur/LEGACY.md` :

1. Déclare à la main une note finale. Quelle requête exacte (verbe, URL, en-têtes, corps)?
2. Quelles différences avec ton `API.md` : noms des champs, format des notes, des dates, des identifiants?
3. Envoie une déclaration invalide (note hors limites, matricule inconnu). Quel code HTTP reçois-tu? Où est l'erreur?
4. Déclare deux fois la même note finale. Que se passe-t-il?
5. Arrête le Dossier scolaire, et dis ce que **ton** serveur devra faire dans ce cas.

## Exercice 2 : le client du légacy <Etiquette type="claude" />

Avec ton `LEGACY.md` comme spécification, demande à Claude :

- un `@Service` `ServiceDossierScolaire` qui utilise `RestClient` pour déclarer une note finale;
- la traduction de tes DTO vers ceux du Dossier scolaire;
- la clé d'API lue d'une variable d'environnement, jamais dans le code;
- la route `POST /api/groupes/{id}/notes-finales` de `API.md` (enseignant du groupe seulement) qui déclare les notes de tous les étudiants du groupe;
- des tests avec un faux Dossier scolaire (pas besoin du vrai pour tester).

## Exercice 3 : quand le vieux système flanche <Etiquette type="main" />

Vérifie à la main, et corrige si nécessaire :

1. Dossier scolaire arrêté : l'enseignant reçoit-il un message clair dans l'app, ou une erreur 500?
2. Dossier scolaire lent (`java -jar dossier-scolaire.jar --dossier.lent=8000`) : ton serveur attend-il indéfiniment? Ajoute un délai d'expiration, et vérifie quelle exception arrive vraiment quand il est dépassé.
3. Réponse `200` avec `"statut": "ERREUR"` : ton serveur la traite-t-il comme un succès?
4. L'enseignant appuie deux fois sur « Déclarer » : combien de résultats dans le Dossier scolaire?
5. Combien d'objets `RestClient` sont créés si 30 enseignants déclarent en même temps? (révise le [cycle de vie Spring](cycle-vie-spring))
