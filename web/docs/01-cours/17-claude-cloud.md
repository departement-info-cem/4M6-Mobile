---
title: Claude hors du cours, le cloud
description: Faire continuer Claude après la séance, sur une machine qui ne s'éteint pas quand tu te déconnectes
hide_table_of_contents: true
---

# Claude hors du cours : le cloud

<Row>

<Column>

:::tip Avant la séance

- Ton repo du fil rouge est sur GitHub et `main` est poussé.
- Installe l'app **Claude** sur ton téléphone et connecte-toi avec ton compte Claude Pro.
- Relis la page [worktrees](worktrees) : une tâche = une branche. C'est la même idée aujourd'hui, mais la branche se fait **ailleurs que sur ta machine**.

:::

</Column>

<Column>

:::info Séance

Les postes du labo s'effacent quand tu te déconnectes. Une session `claude` lancée en classe meurt avec eux, au milieu de son travail.

Une **session cloud** de Claude Code tourne dans une machine virtuelle d'Anthropic : elle clone ton repo GitHub, travaille, lance les tests, pousse une **branche**. Rien à garder allumé. Tu la suis depuis [claude.ai/code](https://claude.ai/code) ou l'app mobile (onglet **Code**). C'est inclus dans Claude Pro, et ça compte dans **le même quota** que le reste.

Au labo, la connexion Claude est perdue à chaque déconnexion : `claude auth login` en début de séance ([recette](../03-recettes/01-claude-cli.mdx)).

:::

</Column>

</Row>

Ce qui **survit** à la déconnexion :

| Où | Quoi | Survit? |
|---|---|---|
| Poste du labo | dossier de travail, session `claude`, connexion Claude | non |
| GitHub | ton code, tes branches, tes PR | oui |
| Ton compte Claude | les sessions **cloud** et leur historique | oui |

## Exercice 1 : brancher GitHub <Etiquette type="main" />

1. Va sur [claude.ai/code](https://claude.ai/code) et installe l'**app GitHub de Claude**.
2. Quand GitHub te demande à quels repos donner accès, choisis **Only select repositories** et coche **seulement** le repo du fil rouge.
3. Garde l'environnement **Default** (réseau *Trusted*).

Questions :

- Pourquoi ne pas donner accès à **tous** tes repos? Pense au cours de cybersec : quel est le principe?
- Que peut faire l'app GitHub dans ton repo une fois installée? Regarde la liste des permissions demandées.

## Exercice 2 : le serveur dans le cloud <Etiquette type="claude" />

Depuis claude.ai/code, choisis ton repo et demande une **route de `API.md` que ton serveur n'a pas encore**, avec ses tests JUnit. Demande explicitement à Claude de lancer les tests avant de terminer.

Java 21 et Gradle sont déjà installés dans la machine du cloud, et le réseau par défaut permet Maven Central et Spring : ton serveur compile tel quel.

Quand Claude a fini :

1. Ouvre la **PR** à partir de la branche poussée. ✍️ Lis tout le diff.
2. ✍️ Sur ton poste : `git fetch`, place-toi sur la branche, relance `./gradlew test` et la collection Bruno.
3. Les tests passent-ils chez toi aussi? Si non, qu'est-ce qui diffère entre ta machine et celle du cloud?

## Exercice 3 : Android dans le cloud <Etiquette type="main" />

Essaie la même chose pour l'app étudiant : avec l'environnement Default, la compilation **échoue**. Lis l'erreur : il manque le SDK Android.

1. Crée un nouvel environnement **Custom** :
   - réseau : ajoute `dl.google.com` et coche **Also include default list**;
   - variable d'environnement : `ANDROID_HOME=/opt/android-sdk`;
   - script de setup : celui fourni par le prof (il installe `cmdline-tools`, `platforms;android-36`, `build-tools;35.0.0`).
2. Relance la tâche dans ce nouvel environnement. `./gradlew assembleDebug` et les tests unitaires passent.
3. Demande à Claude de vérifier que l'écran de détail affiche bien la note. Que répond-il?

Il n'y a **pas d'émulateur** dans le cloud. Claude peut compiler et lancer les tests unitaires, mais il ne **voit** pas ton app. Rappelle-toi la [séance de débogage](debogage) : sans retour (logs, tests), Claude devine.

Questions :

- Quelles tâches du fil rouge peux-tu confier au cloud sans risque? Lesquelles exigent ton émulateur?
- Pourquoi le réseau n'est-il pas ouvert à tout Internet par défaut? Et pourquoi ne jamais mettre un mot de passe ou un jeton dans les variables d'environnement?

## Exercice 4 : le relais du soir <Etiquette type="claude" />

À **10 minutes de la fin** de la séance :

1. Pousse ta branche courante.
2. Depuis le terminal, envoie une tâche au cloud :

   ```bash
   claude --cloud "Ajoute l'écran de demande de révision dans l'app étudiant, selon API.md et CONVENTIONS.md, avec tests unitaires"
   ```

3. Déconnecte-toi. Le soir, ouvre l'app mobile : la session continue-t-elle? Réponds à Claude s'il pose une question.
4. À la séance suivante, récupère le travail :

   ```bash
   claude --teleport
   ```

   ou simplement `git fetch` et la branche poussée par Claude. `--teleport` exige un état git propre et le même compte Claude.

Note dans `docs/IA.md` : ce que Claude a fait seul, ce que tu as dû corriger.

:::note Et si j'ai un ordi à la maison?

- **Remote Control** : tu lances `claude` chez toi et tu le pilotes depuis le téléphone ou le web. L'ordi doit **rester allumé** et `claude` ouvert. Avantage : ton SDK et ton émulateur.
- **SSH + tmux** : même idée, sur une machine que tu contrôles.

Dans les deux cas, Claude tourne **sur ton ordi, avec tes droits**, sans que tu regardes. Donne-lui un compte ou un conteneur à part : [Une machine pour Claude](../03-recettes/03-machine-pour-claude.mdx). La session cloud n'a pas ce problème : sa VM est jetable et ne voit que ton repo.

Pour aller plus loin : l'action GitHub de Claude répond à `@claude` dans une issue ou une PR (jeton créé avec `claude setup-token`, dans **ton** repo seulement).

:::

:::caution Quota

Une session cloud consomme le même forfait Claude Pro que le terminal. Trois sessions en parallèle le vident trois fois plus vite. Une tâche du soir bien découpée vaut mieux que trois vagues.

:::

## Références

- [Claude Code sur le web](https://code.claude.com/docs/en/claude-code-on-the-web)
- [Environnements cloud : réseau, script de setup](https://code.claude.com/docs/en/cloud-environments)
- [Claude Code sur mobile](https://code.claude.com/docs/en/mobile)
- [Remote Control](https://code.claude.com/docs/en/remote-control)
- [GitHub Actions](https://code.claude.com/docs/en/github-actions)
