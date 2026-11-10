---
title: Plan de cours et le choc
description: Séance 1 - plan de cours, repo d'exercices, Claude Code refait les TPs de 3M5
hide_table_of_contents: true
---

# Plan de cours et le choc

<Row>

<Column>

:::tip Avant la séance

- Avoir un compte GitHub et **Claude Pro** actif (le collège fournit l'accès, voir le professeur).
- Avoir sous la main ton TP2 et ton TP3 de 3M5 (ou au moins te rappeler les énoncés) :
  - [TP2 3M5 : Interface graphique Android](https://info.cegepmontpetit.ca/3M5-Intro-Mobile/tp/tp2)
  - [TP3 3M5 : Appli Android](https://info.cegepmontpetit.ca/3M5-Intro-Mobile/tp/tp3)

:::

</Column>

<Column>

:::info Séance

1. Présentation du plan de cours : projet fil rouge, tests papier, intra et final **sans IA**.
2. Le choc : en une séance, Claude Code refait les deux TPs Android qui t'ont pris des semaines en 3M5.
3. Discussion : qu'est-ce que ça change pour toi? Qu'est-ce qu'il faut encore savoir faire **soi-même**?

:::

</Column>

</Row>

## Exercice 1 : ton repo d'exercices <Etiquette type="main" />

Ce repo contiendra **tous les exercices** de la session, un dossier par exercice.

1. Sur GitHub, crée un repo **privé** nommé `4M6-exercices-<ton-nom>`, avec un `README.md`. Ajoute le professeur comme collaborateur.
2. Clone-le sur le poste :

```bash
git clone https://github.com/<toi>/4M6-exercices-<ton-nom>.git
```

3. Connecte l'outil `gh` à ton compte pour que Claude puisse commit, push et lire le résultat (recettes [gh](../03-recettes/00-gh-connexion.mdx) et [Claude Code](../03-recettes/01-claude-cli.mdx)) :

```bash
gh auth login
```

Choisis `GitHub.com`, `HTTPS`, puis l'authentification par navigateur. Vérifie avec :

```bash
gh auth status
```

4. Crée les dossiers `ex02-tp-3M5/` et `ex03-correction/`, commit et push à la main.

## Exercice 2 : Claude refait les TP2 et TP3 de 3M5 <Etiquette type="claude" />

1. Dans un terminal, place-toi **à la racine de ton repo d'exercices** et lance Claude Code :

```bash
claude
```

2. Passe en **mode plan** (`Shift+Tab` jusqu'à « plan mode »). Donne-lui les deux énoncés (les liens ci-dessus) et demande-lui un plan pour produire les deux TPs dans `ex02-tp-3M5/`.
3. **Lis le plan** avant d'accepter. Note dans `ex02-tp-3M5/IA.md` :
   - ce que Claude a prévu que tu n'aurais pas fait;
   - ce qui te semble faux ou risqué;
   - ce que tu lui as demandé de changer.
4. Accepte le plan, laisse Claude coder, compiler, et **commit + push** (il utilise `gh`/`git` pour toi).
5. Lance les apps sur l'émulateur. Est-ce que ça marche vraiment? Note dans `IA.md` ce qui ne marche pas.

:::caution
Tu ne gagnes rien à copier un TP de 3M5 existant dans le dossier : l'exercice consiste à **observer** ce que Claude fait seul, à partir de l'énoncé.
:::

## Exercice 3 : Claude corrige <Etiquette type="claude" />

Dans une **nouvelle** session Claude (pour qu'il ne se souvienne pas d'avoir écrit le code), demande-lui de corriger `ex02-tp-3M5/` comme le ferait un professeur :

- une note sur 100 par TP, avec la grille de l'énoncé;
- surtout des **retours** : bogues, code mort, mauvaise gestion d'état, ce qui manque à l'énoncé.

Sauvegarde la correction dans `ex03-correction/correction.md`, puis ajoute **à la main** ta propre réaction : es-tu d'accord? Qu'a-t-il raté?

## Pour la suite

Le reste de la session, tu feras deux choses en alternance :

- <Etiquette type="claude" /> piloter Claude sur ton projet fil rouge et des portions de serveur;
- <Etiquette type="main" /> apprendre à **lire, tracer et écrire** ce code toi-même. C'est ce qui est évalué aux tests papier et aux examens, sans IA.
