---
title: HTTP et Retrofit, premier GET
description: Séance 3 - l'app parle à Léo2000 - requête, réponse, GET avec Retrofit
hide_table_of_contents: true
---

# HTTP et Retrofit : premier GET sur Léo2000

<Row>

<Column>

:::tip Avant la séance

- Capsule : [HTTP en 15 minutes](../capsules/http) (méthode, URL, en-têtes, corps, codes, encodage).
- Recette : [appel de base](../recettes/retrofit-01-base).
- Clone le code de **Léo2000** ([`code/Leo2000`](https://github.com/departement-info-cem/4M6-Mobile/tree/main/code/Leo2000)) et lance-le une fois : `./gradlew bootRun`, puis ouvre `http://localhost:8080/api/moi` dans le navigateur. Quel code reçois-tu? Pourquoi?

:::

</Column>

<Column>

:::info Séance

1. Ce qui se passe quand une app « appelle le serveur » : requête, réseau, réponse.
2. Léo2000 : le serveur des profs, son API (dans l'énoncé du [TP1](../tp/tp1)), ses comptes de test.
3. Retrofit : une interface Kotlin décrit les routes, Retrofit fabrique le code qui envoie la requête.
4. Où tourne quoi : Léo2000 en local (`10.0.2.2:8080` depuis l'émulateur) ou l'instance déployée par les profs.

📝 Test T1 : valeurs, expressions, types.

:::

</Column>

</Row>

## Ce qui se passe pendant un appel

```text
App (émulateur)                         Léo2000 (Spring Boot)
───────────────                         ─────────────────────
clic sur « Mes groupes »
  └─ lambda onClick
       └─ service.groupes()   ── GET /api/groupes ──────────▶  ControleurGroupes.groupes()
                                  Cookie: JSESSIONID=…            └─ session.exige() : qui est connecté?
                                                                  └─ ServiceGroupes.groupesDe(u)
          (l'app attend,       ◀── 200 OK ──────────────────
           l'écran reste           Content-Type: application/json
           utilisable)             [{"id":1,"cours":"420-4M6-EM",…}]
       └─ Gson transforme le JSON en List<GroupeDTO>
  └─ l'état change, l'écran se recompose avec la liste
```

Retiens les quatre morceaux : **client avant** (le code qui déclenche), **requête** (méthode, URL, en-têtes, corps), **serveur** (le code qui répond), **réponse** (code, en-têtes, corps). C'est exactement ce qu'on te demandera de raconter à la défense du TP1.

## Exercice 1 : Léo2000 sans app <Etiquette type="main" />

Avec Bruno (ou le navigateur pour les GET) contre ton Léo2000 local :

1. `GET /api/moi` sans être connecté : note le code et le corps.
2. `POST /api/connexion` avec `{"courriel":"ada@cem.ca","motDePasse":"Passw0rd!"}` : quel en-tête de la réponse contient le cookie?
3. `GET /api/groupes` avec ce cookie : combien de groupes pour Ada? Et pour Chloé?
4. Dans `trace-get.md`, écris la requête et la réponse de l'étape 3 au complet (méthode, URL, en-têtes utiles, corps; code, en-têtes utiles, corps).

## Exercice 2 : un premier GET dans une app <Etiquette type="main" />

Projet **ReseauSimple**, sans Claude, en suivant la [recette](../recettes/retrofit-01-base) :

1. Interface Retrofit avec une seule route : `@GET("api/moi")`.
2. Un bouton qui l'appelle et affiche le code HTTP reçu (sans connexion : 401).
3. Mets un point d'arrêt dans la lambda du bouton et un autre dans le `onResponse`. Dans quel ordre s'arrêtent-ils? Que fait l'interface entre les deux?

## Exercice 3 : brancher le TP1 <Etiquette type="claude" />

Dans ton projet de TP1, demande à Claude (mode plan) d'ajouter Retrofit dans `:commun` et de remplacer les données factices de **un seul** écran (la liste des groupes) par un appel à Léo2000. Avant d'accepter, vérifie que son plan :

- met l'URL du serveur à un seul endroit (local ou déployé);
- utilise les DTO de l'API du TP1, pas des classes inventées;
- ne touche pas aux autres écrans.

Valide à la main avec Ada puis avec Chloé : la liste change-t-elle? Si non, pourquoi? (Indice : la séance 5.)
