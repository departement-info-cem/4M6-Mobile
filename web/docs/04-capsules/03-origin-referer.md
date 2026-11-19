---
title: Origin et Referer
description: Capsule - deux en-têtes qui disent d'où vient une requête, et ce qu'ils protègent (ou pas)
---

# Capsule : Origin et Referer

Deux en-têtes que le **navigateur** ajoute pour dire au serveur d'où vient une requête.

## Referer : la page d'où l'on vient

Quand tu cliques sur un lien de `https://omnivax.exemple/releve.php?matricule=2027001` vers un autre site, le navigateur envoie :

```http
GET /page HTTP/1.1
Host: autre-site.exemple
Referer: https://omnivax.exemple/releve.php?matricule=2027001
```

- Il contient l'**URL complète** (ou une partie, selon la politique `Referrer-Policy` de la page d'origine).
- Usage : statistiques (« d'où viennent nos visiteurs »), et parfois une vérification de sécurité faible.
- Danger : une URL qui contient une donnée sensible (un matricule, un jeton) **fuit** vers le site suivant.
- L'orthographe `Referer` (un seul r) est une faute de frappe de la norme de 1996, jamais corrigée.

## Origin : le site qui envoie la requête

```http
POST /api/connexion HTTP/1.1
Host: leo2000.exemple
Origin: https://site-pirate.exemple
```

- Seulement le **schéma + hôte + port** (`https://site-pirate.exemple`), jamais le chemin.
- Envoyé par le navigateur sur les requêtes `POST`, `PUT`, `DELETE` et sur toute requête faite par du JavaScript vers un **autre** site.
- Le serveur s'en sert pour **CORS** (« ce site a-t-il le droit de lire ma réponse? ») et pour bloquer certaines attaques **CSRF** (un site pirate qui fait envoyer une requête à ton navigateur, avec **ton** cookie).

## Ce qu'ils ne protègent pas

- Ils sont posés par le **navigateur**. Une app Android, Bruno ou `curl` envoient ce qu'ils veulent, ou rien : `curl -H "Origin: https://leo2000.exemple" …`.
- Donc : ils aident à protéger **un utilisateur dans son navigateur** contre un autre site. Ils ne prouvent **jamais** l'identité d'un client. Pour ça, Léo2000 utilise la session (le cookie), et les règles d'accès vérifiées côté serveur.

## Lien avec le cours

- Ton app Android n'envoie pas d'`Origin` : CORS ne la concerne pas.
- La page `releve.php` d'Omnivax est une page **web** : un XSS y vole le cookie d'un utilisateur de navigateur (TP3).
- Au CTF, une règle d'accès qui se fierait à `Referer` ou `Origin` se contourne en une ligne.

## Vérifie-toi

1. Une API refuse les requêtes dont l'`Origin` n'est pas son propre site. Est-ce que ça empêche Bruno de l'appeler?
2. Pourquoi un jeton dans l'URL d'une page est-il plus dangereux qu'un jeton dans un cookie?

<details>
<summary>Réponses</summary>

1. Non : Bruno met l'`Origin` qu'on veut, ou aucun.
2. L'URL part dans le `Referer` vers les autres sites, dans l'historique et dans les journaux des serveurs.

</details>
