---
title: POST et DTO
description: Séance 4 - envoyer des données à Léo2000, DTO côté client, codes d'erreur
hide_table_of_contents: true
---

# POST et DTO

<Row>

<Column>

:::tip Avant la séance

- Recettes : [objet dans l'interface](../recettes/retrofit-02-objet) et [liste d'objets](../recettes/retrofit-03-liste).
- Relis la section DTO de l'API dans l'énoncé du [TP1](../tp/tp1).
- Capsule : [les codes HTTP](../capsules/http#les-codes) : quelle différence entre 400, 401, 403 et 404?

:::

</Column>

<Column>

:::info Séance

1. Un **DTO** (classe de transfert) : la forme exacte du JSON échangé, ni plus ni moins. Pourquoi ce n'est pas la classe de la BD (`Utilisateur` contient le hachage du mot de passe, `UtilisateurDTO` non).
2. `@POST` et `@Body` : Retrofit transforme ton objet Kotlin en JSON.
3. Quand ça échoue : `onResponse` avec un code 4xx (le serveur a répondu « non ») contre `onFailure` (pas de réponse du tout).

:::

</Column>

</Row>

## Exercice 1 : lire les DTO de Léo2000 <Etiquette type="main" />

Ouvre `code/Leo2000/src/main/java/ca/cem/notes/dto/Dto.java`. Dans `dto.md` :

1. Pour `UtilisateurDTO` et `NoteDTO`, écris la `data class` Kotlin correspondante **à la main**. Quels champs peuvent être `null`?
2. Compare `Utilisateur` (dans `modele/`) et `UtilisateurDTO` : quel champ n'est jamais envoyé au client? Pourquoi?
3. Écris le JSON exact envoyé par `POST /api/groupes/{id}/evaluations` pour une évaluation « TP2 », 15 %, le 2027-04-01.

## Exercice 2 : créer une évaluation depuis l'app <Etiquette type="main" />

Dans **ReseauSimple** (séance 3), sans Claude :

1. Connexion en dur avec Ada (on verra la vraie connexion à la séance 5) puis `POST /api/groupes/{id}/evaluations`.
2. Affiche l'évaluation créée (le `EvaluationDTO` reçu, avec son `id`).
3. Envoie une pondération de 200 : quel code reçois-tu? Dans quelle méthode arrives-tu, `onResponse` ou `onFailure`? Affiche le `message` du corps d'erreur.
4. Arrête Léo2000 et recommence : quelle méthode maintenant?

## Exercice 3 : le TP1 crée des choses <Etiquette type="claude" />

Avec Claude, en mode plan : la création d'un groupe et la création d'une évaluation dans l'app enseignant passent par Léo2000. Exige dans ta demande que :

- les DTO soient dans `:commun` et suivent l'API du TP1 champ par champ;
- chaque erreur (400, 403, réseau) affiche un message compréhensible, pas une trace.

Valide à la main : crée une évaluation qui dépasse 100 % au total. Que voit l'utilisateur?
