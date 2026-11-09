---
title: Worktrees et Claude en parallèle
description: git worktree pour faire avancer plusieurs sessions Claude en même temps sans qu'elles se marchent dessus
hide_table_of_contents: true
---

# Worktrees : plusieurs Claude en parallèle

<Row>

<Column>

:::tip Avant la séance

- Le TP2 est commencé : ton serveur répond aux routes de base de `API.md`.
- Lis la page [système légacy](systeme-legacy) : c'est une des trois tâches de la séance.
- Relis la page [Git](git) : branches, fusion, conflits.
- Joue les trois scénarios de [tuto-worktree](https://jorisdeguet.github.io/tuto-worktree/#sc=commit&mode=sans), d'abord **sans** worktree, puis **avec** : commiter pendant que Claude code, corriger un bogue urgent pendant une longue fonctionnalité, faire travailler trois Claude en parallèle.

:::

</Column>

<Column>

:::info Séance

Une session Claude travaille plusieurs minutes. Pendant ce temps, tu attends… ou tu en lances une autre. Mais deux sessions dans **le même dossier** se marchent dessus : elles modifient les mêmes fichiers, lancent des compilations en même temps, et changent de branche sous les pieds de l'autre.

`git worktree` règle ça : **un seul repo**, plusieurs dossiers de travail, chacun sur **sa** branche.

```bash
git worktree add ../notes-enseignant -b integration-enseignant
git worktree list
git worktree remove ../notes-enseignant
```

Les trois dossiers partagent le même historique : un commit fait dans l'un est visible (comme branche) dans les autres. Pas besoin de cloner trois fois.

À retenir : ce n'est pas une fonction de GitHub, c'est **git**. GitHub ne voit que les branches que tu pousses.

:::

</Column>

</Row>

## Exercice 1 : trois dossiers, un repo <Etiquette type="main" />

À la racine de ton projet fil rouge, sur `main` à jour :

```bash
git worktree add ../notes-enseignant -b integration-enseignant
git worktree add ../notes-etudiant -b integration-etudiant
git worktree add ../notes-legacy -b dossier-scolaire
git worktree list
```

1. Ouvre `../notes-enseignant/.git` : c'est un **fichier**, pas un dossier. Que contient-il?
2. Fais un commit dans `../notes-etudiant`. Vois-tu la branche depuis le dossier principal (`git branch`)?
3. Essaie `git switch integration-etudiant` dans le dossier principal. Pourquoi git refuse-t-il?

## Exercice 2 : découper le travail <Etiquette type="main" />

Avant de lancer quoi que ce soit, écris dans `docs/PARALLELE.md` les trois tâches et, pour chacune, **les fichiers qu'elle a le droit de toucher** :

| Worktree | Tâche | Fichiers permis |
|---|---|---|
| `notes-enseignant` | brancher l'app enseignant sur le serveur | `android/enseignant/**` |
| `notes-etudiant` | brancher l'app étudiant sur le serveur | `android/etudiant/**` |
| `notes-legacy` | client du Dossier scolaire et route `notes-finales` | `serveur/**` |

Le module `:commun` (DTO, Retrofit) est partagé : décide **qui** a le droit d'y toucher, ou fais-le à la main **avant** de lancer les sessions. C'est là que naîtront les conflits.

## Exercice 3 : trois sessions <Etiquette type="claude" />

Ouvre trois terminaux, un par worktree, et lance `claude` dans chacun. Donne à chaque session sa tâche **et** sa liste de fichiers permis.

Pièges pratiques :

- **Ports** : un seul serveur peut écouter sur 8080. Si deux sessions lancent le serveur, la deuxième échoue. Donne un port à chaque worktree (`--server.port=8081`).
- **Émulateur** : un seul, partagé. Les deux apps ont des `applicationId` différents, elles cohabitent.
- **Android Studio** : ouvre un worktree à la fois, ou une fenêtre par worktree.
- **Quotas** : trois sessions consomment trois fois plus vite ton forfait Claude Pro.

Pendant qu'elles travaillent : ton rôle est de **lire** ce qu'elles proposent, pas d'attendre.

## Exercice 4 : fusionner <Etiquette type="main" />

1. Pousse chaque branche et ouvre une PR par branche (`gh pr create`).
2. Fusionne-les une à une dans `main`. Résous **à la main** les conflits (souvent dans `:commun`).
3. Après chaque fusion : le serveur et les deux apps compilent-ils? La collection Bruno passe-t-elle?
4. Supprime les worktrees et les branches fusionnées.

Dans `docs/PARALLELE.md`, réponds :

- Combien de temps auraient pris les trois tâches à la suite? En parallèle?
- Où ont eu lieu les conflits, et comment ton découpage aurait-il pu les éviter?
- Trois diffs à relire en même temps : est-ce que tu as **vraiment** tout relu?

La suite : faire travailler Claude **hors de ta machine**, même après la déconnexion → [séance 17, le cloud](claude-cloud).
