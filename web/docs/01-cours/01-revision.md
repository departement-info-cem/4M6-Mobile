---
title: Retour, rôles et l'interface avec Claude
description: Séance 2 - retour sur le choc, qui fait quoi, Claude produit le gros de l'interface du TP1 après une planification
hide_table_of_contents: true
---

# Retour sur le choc, rôles, et l'interface avec Claude

<Row>

<Column>

:::tip Avant la séance

- Relis ton `ex02-tp-3M5/IA.md` et ton `ex03-validation/validation.md` de la [séance 1](choc-claude).
- Lis l'énoncé du [TP1](../tp/tp1) : domaine, deux apps, structure en 3 modules.
- Capsule : [les lambdas](../capsules/lambda) (on en écrit à chaque écran Compose).
- Joue les scénarios de [tuto-remember](https://jorisdeguet.github.io/tuto-remember/) (quand une fonction `@Composable` est réexécutée, comment `remember` retrouve la valeur précédente).

:::

</Column>

<Column>

:::info Séance

1. **Retour sur le choc** (20 min) : ce qui vous a impressionnés, ce qui était faux, ce que vous avez trouvé en validant à la main.
2. **Qui fait quoi** (15 min) : le tableau ci-dessous.
3. **Exercice principal** : Claude produit le gros de l'interface des deux apps du TP1, après une phase de planification, et tu surveilles les dérives.

:::

</Column>

</Row>

## Ce qu'on retient du choc

Claude écrit vite du code qui compile. Ça ne veut pas dire que le code fait ce qui est demandé, ni que tu sais l'expliquer. Deux habitudes en découlent pour toute la session :

- **Planifier avant de générer.** Le mode plan force Claude à dire ce qu'il va faire avant de le faire. Un plan se corrige en une phrase; 40 fichiers générés dans la mauvaise direction, non.
- **Boucler sur le fonctionnement.** Après chaque étape : compiler, lancer, essayer à la main, comparer à l'énoncé. On ne passe pas à l'étape suivante sur la parole de Claude.

## Qui fait quoi

| | Claude | Toi | Le prof |
|---|---|---|---|
| **Avant** | propose un plan quand tu le demandes | écris la demande, lis le plan, le corriges, l'acceptes ou le refuses | fournit l'énoncé, le serveur Léo2000 et son API |
| **Pendant** | écrit le code, compile, lance des commandes | surveilles les dérives, arrêtes Claude quand il part ailleurs | répond aux questions sur le **domaine** et la **théorie** |
| **Après** | dit que c'est fini (pas toujours vrai) | valides à la main exigence par exigence, commites, notes dans `IA.md` ce qui a été corrigé | évalue ce que **tu** sais expliquer et modifier sans IA (défenses, tests papier, examens) |
| **Responsable de la note** | rien | tout ce qui est dans ton repo | la grille |

Le prof ne débogue pas le code produit par Claude à ta place : apporte une question précise (« pourquoi ce `remember` perd-il la valeur à la rotation? »), pas « ça marche pas ».

## Exercice principal : l'interface du TP1 avec Claude <Etiquette type="claude" /> <Etiquette type="main" />

Le but : sortir de la séance avec les **écrans des deux apps** du TP1 (données factices pour l'instant : le réseau arrive aux séances 3 à 5), en ayant gardé le contrôle.

### 1. Planifier <Etiquette type="main" />

Sans Claude, dans `docs/PLAN-UI.md` de ton repo de TP1 :

- la liste des écrans de chaque app (reprends la liste du [TP1](../tp/tp1)) et la navigation entre eux (un croquis papier photographié suffit);
- ce qui va dans `:commun` (thème paramétré, composants partagés, DTO) et ce qui reste dans chaque app;
- tes deux palettes : 2 verts pour l'app enseignant, 2 oranges pour l'app étudiant (hex).

### 2. Écrire les règles anti-dérive <Etiquette type="main" />

Crée `CLAUDE.md` à la racine du repo. Claude le lit à chaque session. Mets-y au moins :

- aucune couleur ni `dp` en dur dans un écran : tout passe par le thème de `:commun`;
- aucun texte en dur : `strings.xml`, français **et** anglais;
- pas de nouvelle dépendance Gradle sans me le demander;
- pas d'écran ni de fonctionnalité qui ne sont pas dans `docs/PLAN-UI.md`;
- chaque écran fonctionne en portrait **et** en paysage;
- une étape à la fois : compiler et me laisser tester avant la suivante.

### 3. Générer avec un plan <Etiquette type="claude" />

1. Lance `claude` à la racine, passe en **mode plan** (`Shift+Tab`).
2. Demande un plan pour créer le projet à 3 modules (`:commun`, `:enseignant`, `:etudiant`) et les écrans de `docs/PLAN-UI.md`, avec des données factices.
3. **Lis le plan.** Refuse-le s'il ajoute des écrans, des bibliothèques ou une architecture que tu n'as pas demandés. Fais-le découper en étapes (ex. : modules et thème, puis app enseignant, puis app étudiant).
4. Accepte, une étape à la fois.

### 4. Surveiller et boucler <Etiquette type="main" />

Après **chaque** étape, avant de dire « continue » :

| Dérive | Comment la voir vite |
|---|---|
| couleur ou `dp` en dur | `git diff` : chercher `Color(0x` et `.dp` hors de `:commun` |
| texte en dur | `git diff` : chercher `Text("` |
| dépendance ajoutée | `git diff` sur les `build.gradle.kts` et `libs.versions.toml` |
| écran ou bouton non demandé | lancer l'app, comparer à `PLAN-UI.md` |
| paysage cassé | tourner l'émulateur sur chaque écran |
| le thème vert/orange ne s'applique pas | lancer les **deux** apps côte à côte |

Chaque dérive trouvée va dans `IA.md` : ce que Claude a fait, comment tu l'as vue, ce que tu as demandé. C'est une des questions de la défense du TP1.

### 5. Comprendre ce qui a été généré <Etiquette type="main" />

Sans Claude, choisis un écran et réponds dans `IA.md` : d'où viennent ses données, quelles lambdas il reçoit, quand il se recompose. Ajoute un champ à la main dans cet écran.

## Exercice IA web ou IA avec le code (si le temps le permet)

Même modèle (Claude), même demande, une seule différence : **l'accès au projet**. Le professeur fournit le projet `NotesDepart` (une petite app de notes, avec ses conventions).

La demande, à donner **telle quelle** aux deux IA :

> Dans mon app Android Compose de notes, ajoute un écran de demande de révision : un champ « motif » (au moins 10 caractères), un bouton « Envoyer » qui enregistre la demande puis revient à l'écran précédent, et un bouton « Annuler ». On y accède avec le bouton « Demander une révision » de l'écran de détail d'une note.

1. <Etiquette type="claude" /> Branche `ia-web` : la demande dans claude.ai (conversation web), **sans lui coller de fichiers**; copie-colle sa réponse, fais compiler, note le temps et chaque correction, commite.
2. <Etiquette type="claude" /> Branche `ia-cli` depuis `main` : la même demande à `claude` lancé à la racine du projet. Compile, teste, commite, note le temps.
3. <Etiquette type="main" /> Compare les deux branches (`git diff main ia-web`, `git diff main ia-cli`) dans `COMPARAISON.md` : compile du premier coup? fichiers modifiés? noms inventés? textes ou couleurs en dur? conventions de `CONVENTIONS.md` respectées? temps total?

Puis, en une phrase : l'IA web n'est pas moins intelligente, il lui manquait le contexte. Si tu lui colles des fichiers, qui choisit lesquels?

## Pour référence

Les pages de révision de la mise en page, des listes et de l'identité visuelle restent disponibles si une notion te manque en lisant le code de Claude : [mise en page](mise-en-page), [listes et navigation](listes), [identité visuelle](identite-visuelle), [orientation et taille](orientation-taille).
