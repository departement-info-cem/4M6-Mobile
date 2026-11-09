---
title: Spring par Claude
description: Faire générer une portion plus large de serveur par Claude, puis la relire et la tracer à la main
hide_table_of_contents: true
---

# Spring par Claude

<Row>

<Column>

:::tip Avant la séance

- Avoir terminé [cycle de vie Spring](cycle-vie-spring).
- Lire [cookies](cookies) : on s'en sert pour la session utilisateur.
- Lire le contrat commun `API.md` fourni par le professeur (routes, DTO, codes d'erreur du serveur de notes).

:::

</Column>

<Column>

:::info Séance

- Claude génère vite un serveur complet : entités JPA, repositories, services, contrôleurs, tests.
- Le risque : un serveur qui « marche » mais que personne ne comprend, ou qui fait confiance au client.
- On applique une **relecture guidée** : ce que tu dois pouvoir expliquer sans Claude.

:::

</Column>

</Row>

## Exercice 1 : les règles d'accès <Etiquette type="main" />

Le contrat `API.md` dit **quoi**; à toi d'écrire **qui**. Dans `serveur/ACCES.md`, pour chaque route : qui a le droit (enseignant du groupe, étudiant concerné, collègue, personne) et le code renvoyé sinon (401, 403, 404). Pense au workflow de révision : demandée par l'étudiant concerné, proposée par l'enseignant du groupe, validée par un **autre** enseignant.

Claude devra respecter `API.md` **et** `ACCES.md`.

## Exercice 2 : générer le serveur <Etiquette type="claude" />

En mode plan, donne `API.md` et `ACCES.md` à Claude et demande :

- un projet Spring Boot (Gradle, Java 21) avec H2;
- entités, repositories, `@Service`, `@RestController`;
- au moins un test JUnit par service, et un test par règle de `ACCES.md`;
- **aucun état dans les contrôleurs**.

Fais-le commit dans `serveur/`.

## Exercice 3 : relecture guidée <Etiquette type="main" />

Sans Claude, réponds dans `serveur/RELECTURE.md` :

1. Pour une requête `POST` de ton API : liste **dans l'ordre** les classes et méthodes traversées, de la réception à la réponse.
2. Combien d'instances de chaque classe existent après 30 requêtes?
3. Y a-t-il un champ modifiable dans un contrôleur ou un service? Est-ce un problème?
4. Où l'identité de l'utilisateur est-elle obtenue? Vient-elle du **client** (paramètre, corps) ou de la **session** (cookie)? Si elle vient du client, c'est une faille : on y revient en cybersec.
5. Un test généré ne vérifie rien d'utile? Lequel, et pourquoi?
6. Pour 3 règles de `ACCES.md`, montre la ligne qui la vérifie. Une règle n'est vérifiée nulle part? Elle n'existe pas.
7. Modifie à la main une règle métier (ex. : une note hors de 0 à 100 est refusée avec un 400) et ajoute le test correspondant.
