---
title: Lire Léo2000, premiers pas Spring
description: Séances 8 et 9 - suivre une requête dans le code de Léo2000, puis ajouter une première route du TP2
hide_table_of_contents: true
---

# Lire Léo2000 : premiers pas Spring

Au TP2, tu ajoutes toi-même des routes à Léo2000. Avant d'écrire du Spring (avec Claude), il faut savoir **lire** celui qui existe.

<Row>

<Column>

:::tip Avant la séance

- Léo2000 roule chez toi en local (`code/Leo2000`, `./gradlew bootRun`).
- Ouvre-le dans IntelliJ et repère les 4 dossiers : `controleurs/`, `services/`, `depots/`, `modele/`, plus `dto/`.
- Lis l'énoncé du [TP2](../tp/tp2) : les nouvelles routes sont décrites dedans.

:::

</Column>

<Column>

:::info Séance 8

1. Le trajet d'une requête dans Spring : contrôleur (`@RestController`, `@GetMapping`) → service (règles, accès) → dépôt (BD) → DTO → JSON.
2. L'injection de dépendances : qui crée le `ServiceGroupes` que reçoit le contrôleur?
3. `ApiException` et `GestionErreurs` : comment un `throw` devient un 403 avec un message.

:::

:::info Séance 9

1. Ajouter une route du TP2 de bout en bout : API → contrôleur → service → test → écran.
2. Temps de travail TP2.

📝 Test T3 : trace client-serveur, DTO.

:::

</Column>

</Row>

## Exercice 1 : suivre une requête <Etiquette type="main" />

Sans Claude, avec des points d'arrêt dans Léo2000 lancé en **débogage** depuis IntelliJ :

1. Depuis ton app (ou Bruno), fais `PUT /api/evaluations/{id}/notes/{idEtudiant}` avec Ada.
2. Note dans `lecture-leo2000.md`, dans l'ordre, chaque méthode traversée (classe et nom) jusqu'au `save` dans la BD.
3. À quelle ligne Léo2000 décide-t-il **qui** fait la requête? À quelle ligne décide-t-il si cette personne **a le droit**?
4. Même exercice pour `GET /api/evaluations/{id}/notes`. Les deux routes vérifient-elles le droit de la même façon? (Garde ta réponse : elle resservira.)

## Exercice 2 : modifier sans Claude <Etiquette type="main" />

1. Ajoute à `GroupeDTO` un champ `nbEtudiants` (nombre d'inscrits). Fais-le apparaître dans la réponse de `GET /api/groupes`.
2. Ajoute un test JUnit qui vérifie le nombre d'inscrits du groupe d'Ada.
3. Affiche ce nombre dans ton app enseignant.

## Exercice 3 : une route du TP2 avec Claude <Etiquette type="claude" />

Choisis **une** route du TP2 (par exemple `GET /api/revisions`). En mode plan, demande à Claude de l'ajouter à Léo2000 **et** à l'app, en suivant exactement l'API de l'énoncé. Avant d'accepter :

- le plan respecte-t-il la découpe contrôleur / service / dépôt de Léo2000?
- l'identité vient-elle de `Session`, jamais d'un paramètre?
- prévoit-il un test?

Puis trace la nouvelle route comme à l'exercice 1.
