---
title: Révision
description: Premier cours, révisions écrans, navigation, i18n
hide_table_of_contents: true
---

# Premier cours, révisions écrans


<Row>

<Column>

:::tip Avant la séance

Relire le code produit par Claude à la [séance 1](choc-claude).

Révisions:
- La base : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/cours/6.1-intro-android)
- Liste : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/liste1) et [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/liste2)
- Multilingue : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/multilingue)
- État et recomposition : joue les scénarios de [tuto-remember](https://jorisdeguet.github.io/tuto-remember/) (quand une fonction `@Composable` est réexécutée, comment `remember` retrouve la valeur précédente).

:::

</Column>

<Column>

:::info Séance

- **20 min** : examen diagnostic.
- **10 min** : correction par les pairs.
- **5 min** : objectifs du cours.
- **5 min** : présentation du plan de cours: examens papier, examens ordi, TP.
- **5 min** : présentation du [TP1](../tp/tp1). 
- reste de la séance : exercices de révision

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Bilan du choc <Etiquette type="main" />

Retourne dans `ex02-tp-3M5/` (le code produit par Claude à la [séance 1](choc-claude)). Sans Claude, dans `ex02-tp-3M5/BILAN.md` :

- choisis **3 fichiers** et explique en 3 lignes ce que fait chacun;
- trouve une fonction que tu ne comprends pas et explique-la après l'avoir tracée;
- modifie une fonctionnalité à la main (ex. : ajouter un champ) et vérifie qu'elle fonctionne.

## Exercice IA web ou IA avec le code

Même modèle (Claude), même demande, une seule différence : **l'accès au projet**. Le professeur fournit le projet `NotesDepart` (une petite app de notes, avec ses conventions).

La demande, à donner **telle quelle** aux deux IA :

> Dans mon app Android Compose de notes, ajoute un écran de demande de révision : un champ « motif » (au moins 10 caractères), un bouton « Envoyer » qui enregistre la demande puis revient à l'écran précédent, et un bouton « Annuler ». On y accède avec le bouton « Demander une révision » de l'écran de détail d'une note.

### Étape 1 : l'IA en conversation web <Etiquette type="claude" />

1. Crée une branche : `git switch -c ia-web`.
2. Dans claude.ai (conversation web), colle la demande. Tu peux répondre à ses questions, mais **sans lui coller de fichiers du projet**.
3. Copie-colle ce qu'elle propose dans le projet, et fais compiler. Note le temps passé et chaque correction que tu as dû faire.
4. Commite tel quel, même si c'est imparfait.

### Étape 2 : Claude Code dans le projet <Etiquette type="claude" />

1. Repars de `main` : `git switch main && git switch -c ia-cli`.
2. Lance `claude` à la racine du projet et donne **la même demande**, rien de plus.
3. Fais compiler, teste, commite. Note le temps.

### Étape 3 : comparer <Etiquette type="main" />

Sans IA, compare les deux branches (`git diff main ia-web`, `git diff main ia-cli`) et remplis `COMPARAISON.md` :

| Critère | IA web | Claude Code |
|---|---|---|
| Compile du premier coup? | | |
| Nombre de fichiers modifiés (une solution complète en touche 6) | | |
| Noms, classes ou fonctions **inventés** (qui n'existent pas dans le projet) | | |
| Écran avec des paramètres lambda (`onEnvoyer: () -> Unit`…) alors que `CONVENTIONS.md` les interdit pour un écran utilisé une fois | | |
| `Button` au lieu de `BoutonPrincipal` / `BoutonSecondaire` | | |
| Textes en dur au lieu de `strings.xml` (français **et** anglais) | | |
| Couleurs ou `dp` en dur au lieu du thème et de `Espacement` | | |
| `ViewModel` ajouté alors que le projet passe par `Depot` | | |
| Route ajoutée dans `Routes`, bouton du détail branché | | |
| Temps total, corrections comprises | | |

Puis, en une phrase chacune :

- Quelles **hypothèses** l'IA web a-t-elle faites sur ton projet? Lesquelles étaient fausses?
- L'IA web n'est pas moins intelligente : il lui manquait le contexte. Si tu lui colles des fichiers, qui choisit lesquels?
- Pourquoi un copier-coller qui « marche » peut-il quand même faire du tort à un projet?

## Exercice Trace Simple <Etiquette type="main" />
Créez un fichier **trace-simple.md** pour y mettre la réponse à cet exercice.  
En suivant les instructions de la recette sur les [traces d'exécution](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/aa-produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code**.
```kotlin showLineNumbers
fun main() {
    val a = 5 + 2 * 2
    for (i in 1..2) {
        for (j in 4..6) {
            println((i + j).toString() + (i.toString() + " " + a))
        }
    }
}
```
En créant un projet et en exécutant en débogage, validez votre trace.  
Si vous avez des surprises, demandez des explications à votre prof.

## Exercice Tiroir <Etiquette type="main" />

Créez un projet appelé **Tiroir** contenant 3 écrans. Ajoutez un tiroir de navigation permettant d'accéder à chacun des écrans depuis chacun des écrans.

## Exercice Navigation <Etiquette type="main" />

Créez un projet appelé **Navigation** contenant 2 écrans.

- Le premier écran contient un champ texte et un bouton
- Quand on appuie sur le bouton, le texte du champ est envoyé vers le 2ème écran
- Le 2ème écran affiche le texte reçu

:::
