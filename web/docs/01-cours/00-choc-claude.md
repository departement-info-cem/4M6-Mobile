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

:::caution
Tu ne gagnes rien à copier un TP de 3M5 existant dans le dossier : l'exercice consiste à **observer** ce que Claude fait seul, à partir de l'énoncé.
:::

## Exercice 3 : valider l'app à la main <Etiquette type="main" />

Claude dit que c'est fini. C'est **toi** qui décides si c'est vrai : tu es le validateur, pas le spectateur. Ce sera ton rôle toute la session.

Dans `ex03-correction/validation.md`, pour **un** des deux TPs :

1. Relis l'énoncé et fais la liste de **chaque exigence**, une par ligne.
2. Lance l'app sur l'émulateur et teste chaque exigence. Note comment tu l'as testée et le résultat :

   | Exigence | Comment je l'ai testée | Résultat |
   |---|---|---|
   | Le bouton Ajouter ajoute à la liste | ajout de 3 éléments, dont un vide | ⚠️ l'élément vide est accepté |

3. Essaie aussi ce que l'énoncé ne dit pas : champ vide, texte très long, rotation de l'écran, bouton retour, langue anglaise.
4. Pour **un** problème trouvé, ouvre le code et trouve la ligne responsable. Explique-la en une phrase.

## Exercice 4 : Claude corrige <Etiquette type="claude" />

Dans une **nouvelle** session Claude (pour qu'il ne se souvienne pas d'avoir écrit le code), demande-lui de corriger `ex02-tp-3M5/` comme le ferait un professeur :

- une note sur 100 par TP, avec la grille de l'énoncé;
- surtout des **retours** : bogues, code mort, mauvaise gestion d'état, ce qui manque à l'énoncé.

Sauvegarde la correction dans `ex03-correction/correction.md`, puis ajoute **à la main** une comparaison avec ta validation : qu'a-t-il trouvé que tu n'avais pas vu? Qu'as-tu trouvé, en lançant l'app, qu'il n'a pas vu en lisant le code?

## Pour la suite

Le reste de la session, tu feras deux choses en alternance :

- <Etiquette type="claude" /> piloter Claude sur ton projet fil rouge et des portions de serveur;
- <Etiquette type="main" /> apprendre à **lire, tracer et écrire** ce code toi-même. C'est ce qui est évalué aux tests papier et aux examens, sans IA.
