---
title: Durcir sa prod
description: Corriger les failles trouvées pendant le CTF et l'audit sur le projet fil rouge déployé
hide_table_of_contents: true
---

# Durcir sa prod

<Row>

<Column>

:::tip Avant la séance

Rassemble les failles trouvées sur **ton** serveur : ton `AUDIT.md` ([cybersec élargie](cybersec-elargie)) et ce que les autres équipes ont trouvé pendant le [CTF](ctf).

:::

</Column>

<Column>

:::info Séance

- Prioriser : ce qui donne accès aux données d'un autre utilisateur passe en premier.
- Corriger, **prouver** par un test, redéployer, rejouer l'exploit contre la prod.

:::

</Column>

</Row>

## Exercice 1 : corriger <Etiquette type="claude" />

Pour chaque faille, demande à Claude le correctif **et un test qui échoue avant le correctif**. Le pipeline redéploie.

## Exercice 2 : prouver <Etiquette type="main" />

Pour chaque faille, dans `securite/CORRECTIFS.md` :

1. l'exploit rejoué contre la prod **avant** (réponse obtenue);
2. le commit du correctif;
3. l'exploit rejoué **après** (réponse obtenue, ex. 403);
4. en une phrase, pourquoi le correctif fonctionne. Un correctif que tu ne sais pas expliquer ne compte pas.
