---
title: HTTP en 15 minutes
description: Capsule - méthode, URL, en-têtes, corps, codes de réponse, encodage
---

# Capsule : HTTP en 15 minutes

HTTP est du **texte** échangé sur une connexion : le client envoie une requête, le serveur renvoie une réponse. Rien de plus.

## Une requête

```http
POST /api/groupes/1/evaluations HTTP/1.1
Host: localhost:8080
Content-Type: application/json; charset=utf-8
Cookie: JSESSIONID=4F1C0B…
Content-Length: 58

{"titre":"Intra","ponderation":15.0,"date":"2027-03-10"}
```

| Morceau | Ici | Rôle |
|---|---|---|
| Méthode | `POST` | l'intention : `GET` lire, `POST` créer ou agir, `PUT` remplacer, `DELETE` supprimer |
| Chemin | `/api/groupes/1/evaluations` | la ressource; `1` est un **paramètre de chemin** |
| Paramètres de requête | (aucun) | après `?` : `/api/etudiants?nom=ch` |
| En-têtes | `Host`, `Content-Type`, `Cookie`… | des métadonnées, une par ligne `Nom: valeur` |
| Ligne vide | | fin des en-têtes |
| Corps | le JSON | les données; un `GET` n'en a pas |

## Une réponse

```http
HTTP/1.1 201 Created
Content-Type: application/json
Set-Cookie: JSESSIONID=…; Path=/; HttpOnly

{"id":7,"titre":"Intra","ponderation":15.0,"date":"2027-03-10"}
```

## Les codes

| Famille | Sens | Ceux qu'on croise |
|---|---|---|
| 2xx | ça a marché | 200 OK, 201 créé, 204 OK sans corps |
| 3xx | va voir ailleurs | 302 redirection |
| 4xx | **ta** requête pose problème | 400 données invalides, 401 pas connecté, 403 connecté mais pas le droit, 404 introuvable, 405 mauvaise méthode, 409 conflit |
| 5xx | le **serveur** a un problème | 500 bogue, 503 indisponible (ou un service dont il dépend) |

401 ou 403? **401** : « je ne sais pas qui tu es ». **403** : « je sais qui tu es, et c'est non ».

Avec Retrofit, un 4xx ou 5xx arrive dans `onResponse` (le serveur a **répondu**) : `response.isSuccessful` est faux et le message est dans `response.errorBody()`. `onFailure` veut dire **aucune** réponse : serveur éteint, mauvaise adresse, délai dépassé, JSON illisible.

## Les en-têtes qu'on utilise dans ce cours

| En-tête | Sens | Qui l'envoie |
|---|---|---|
| `Content-Type` | format du corps (`application/json`, `text/html`) | les deux |
| `Accept` | formats que le client sait lire | client |
| `Cookie` / `Set-Cookie` | session (voir [séance 5](../cours/reseau-cookie)) | client / serveur |
| `Origin`, `Referer` | d'où vient la requête (voir [capsule](origin-referer)) | navigateur |
| `X-Cle-Api` | clé d'API inventée par Omnivax (un `X-` = en-tête maison) | client |
| `Location` | où aller (avec 3xx ou 201) | serveur |

## L'encodage

Trois encodages différents se croisent dans une requête :

- **Caractères** : le texte du corps est en **UTF-8**. `é` occupe 2 octets. Un serveur qui lit en Latin-1 affiche `Ã©` : c'est le signe d'un `charset` mal annoncé.
- **URL** : certains caractères ne peuvent pas apparaître tels quels dans une URL. On les **encode en pourcentage** : espace → `%20`, `'` → `%27`, `é` → `%C3%A9`. Retrofit le fait pour `@Query` et `@Path`; à la main dans Bruno ou curl, c'est ton travail.
- **JSON** : les guillemets et les sauts de ligne dans une chaîne sont échappés : `"motif": "Q3 \"mal\" corrigée\n"`.

Exemple : `/api/etudiants?nom=Chlo%C3%A9` cherche « Chloé ».

## Vérifie-toi

1. Une app reçoit 403 sur `PUT /api/evaluations/4/notes/9`. L'utilisateur est-il connecté?
2. `onFailure` est appelé avec « Expected BEGIN_OBJECT but was STRING ». Le serveur a-t-il répondu?
3. Encode en pourcentage la valeur `x' OR 1=1 --` pour un paramètre de requête.

<details>
<summary>Réponses</summary>

1. Oui : sinon ce serait 401.
2. Oui, mais pas le JSON attendu (souvent une page HTML d'erreur) : le décodage a échoué côté client.
3. `x%27%20OR%201%3D1%20--`

</details>
