---
title: Cybersec élargie
description: Au-delà des injections et du contrôle d'accès - secrets, dépendances, configuration, transport, et Claude comme auditeur
hide_table_of_contents: true
---

# Cybersec élargie

<Row>

<Column>

:::tip Avant la séance

- Avoir fait [injections et crypto](injection) et [contrôle d'accès](controle-acces).
- Survoler le [OWASP Top 10](https://owasp.org/Top10/) : repère les catégories déjà vues (injection, contrôle d'accès, crypto).

:::

</Column>

<Column>

:::info Séance

Les failles réelles ne sont pas toutes dans le code d'un contrôleur :

- **Secrets** : mot de passe de BD dans le repo, dans l'historique git, dans l'APK.
- **Dépendances** : une librairie vulnérable, ou une librairie qui n'existe pas (nom inventé par une IA, puis enregistré par un attaquant).
- **Configuration** : console H2 ou actuator exposés en prod, traces de pile renvoyées au client.
- **Transport** : HTTP en clair, `usesCleartextTraffic`, HTTPS.
- **Abus** : pas de limite sur `/connexion` = essais de mots de passe à volonté.
- **CORS** : protège le navigateur, **pas** ton serveur contre une app Android ou curl.

:::

</Column>

</Row>

## Exercice 1 : secrets <Etiquette type="main" />

Sur ton projet fil rouge :

1. Cherche des secrets dans le code **et** dans l'historique :

```bash
git log -p | grep -i -E "password|secret|apikey|token" | head
```

2. Décompresse ton APK (`unzip app-debug.apk -d apk`) et cherche l'URL du serveur et toute clé (`grep -r`). Qu'est-ce qu'un attaquant peut extraire de ton app?
3. Un secret a déjà été poussé? Le supprimer du code ne suffit pas : explique pourquoi et ce qu'il faut faire (le **changer**).

## Exercice 2 : configuration et transport <Etiquette type="main" />

Vérifie sur ta production, avec curl :

- `/h2-console` et `/actuator` répondent-ils?
- une erreur renvoie-t-elle une trace de pile Java au client?
- l'URL en `http://` est-elle redirigée vers `https://`?
- 100 essais de connexion avec un mauvais mot de passe sont-ils tous acceptés?

Note chaque constat au format **FAILLE / EXPLOIT / CORRECTIF**.

## Exercice 3 : Claude auditeur <Etiquette type="claude" />

Dans une session neuve, demande à Claude un audit de sécurité de ton serveur et de ton app (format FAILLE / EXPLOIT / CORRECTIF).

## Exercice 4 : auditer l'auditeur <Etiquette type="main" />

Pour **chaque** faille rapportée par Claude :

- rejoue l'exploit toi-même (Bruno, curl) : vraie faille ou **faux positif**?
- compare avec tes exercices 1 et 2 : qu'a-t-il **oublié**?

Rédige `securite/AUDIT.md` : vraies failles, faux positifs, oublis. C'est cette capacité de vérification qui est évaluée.
