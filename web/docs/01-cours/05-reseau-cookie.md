---
title: Cookie et session
description: Séance 5 - connexion et déconnexion sur Léo2000, le cookie de session, Origin et Referer
hide_table_of_contents: true
---

# Cookie et session

<Row>

<Column>

:::tip Avant la séance

- Recette : [envoi en POST et cookies](../recettes/retrofit-04-cookie).
- Capsule : [Origin et Referer](../capsules/origin-referer).
- Ouvre `code/Leo2000/src/main/java/ca/cem/notes/services/Session.java` : d'où Léo2000 tire-t-il l'identité de l'utilisateur?

:::

</Column>

<Column>

:::info Séance

1. HTTP ne se souvient de rien : sans cookie, chaque requête est un inconnu.
2. La connexion sur Léo2000 : `POST /api/connexion` → le serveur crée une **session** (en mémoire) et renvoie `Set-Cookie: JSESSIONID=…`.
3. Le client renvoie `Cookie: JSESSIONID=…` à chaque requête : c'est la **seule** preuve d'identité.
4. Dans un navigateur, dans Bruno, dans OkHttp (le client sous Retrofit) : qui garde le cookie?
5. Déconnexion : le serveur oublie la session; le cookie ne vaut plus rien.

:::

</Column>

</Row>

## Exercice 1 : le cookie à la main <Etiquette type="main" />

Avec Bruno, gestion automatique des cookies **désactivée** :

1. Connecte Ada, copie la valeur de `JSESSIONID`.
2. `GET /api/moi` sans cookie, puis avec le cookie d'Ada. Codes?
3. Connecte Chloé dans un autre onglet. Avec le cookie d'Ada, `GET /api/groupes` : qui voit-on? Le serveur a-t-il regardé un paramètre « utilisateur » quelque part?
4. Déconnecte Ada, puis réessaie son ancien cookie.
5. Redémarre Léo2000 et réessaie le cookie de Chloé. Explique dans `cookie.md` ce que ça implique pour un serveur hébergé qui s'endort quand personne ne l'utilise.

## Exercice 2 : connexion dans l'app <Etiquette type="main" />

Dans **ReseauSimple**, sans Claude : un `CookieJar` en mémoire dans OkHttp ([recette](../recettes/retrofit-04-cookie)), un écran de connexion, puis `GET /api/moi` qui affiche le nom. Ajoute la déconnexion. Mets un point d'arrêt dans le `CookieJar` : quand est-il appelé?

## Exercice 3 : le TP1 se connecte <Etiquette type="claude" />

Avec Claude : connexion, inscription (app étudiant) et déconnexion (tiroir) sur Léo2000 dans les deux apps. Avant d'accepter le plan, vérifie qu'il ne stocke **pas** le mot de passe dans l'app et qu'il ne passe jamais l'identité de l'utilisateur en paramètre d'une requête.

Valide à la main : connecte Chloé dans l'app étudiant et Ada dans l'app enseignant, côte à côte sur le même émulateur. Chacune voit-elle ses groupes?

## Capsule DTO et cookie : à toi de répondre <Etiquette type="main" />

Sans notes, en 5 lignes chacune : qu'est-ce qu'un DTO et qui l'utilise? Qui crée le cookie, qui le garde, qui le relit? Si on te vole ton cookie, que peut-on faire et pendant combien de temps?
