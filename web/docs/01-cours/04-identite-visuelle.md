---
title: Identité visuelle
description: Un logo, deux palettes (vert enseignant, orange étudiant), un thème Compose partagé
hide_table_of_contents: true
---

# Identité visuelle

<Row>

<Column>

:::tip Avant la séance

- Choisis l'**établissement** (fictif) de ton projet fil rouge et décris-le en 3 lignes.
- Regarde comment Material 3 construit un thème à partir de couleurs : [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/).

:::

</Column>

<Column>

:::info Séance

- Une identité commune, deux apps qui se distinguent : **même logo**, palette **verte** pour l'app enseignant, palette **orange** pour l'app étudiant. Un enseignant qui ouvre la mauvaise app le voit tout de suite.
- Où vit la couleur dans une app Compose : `Color.kt`, `Theme.kt`, `MaterialTheme.colorScheme`.
- Pourquoi on ne met **jamais** une couleur codée en dur dans un écran.
- Ensuite : [orientation et taille](orientation-taille).

:::

</Column>

</Row>

## Exercice 1 : la charte <Etiquette type="main" />

Dans `docs/charte.md` du projet fil rouge :

- le logo de l'établissement (SVG ou PNG 512×512), lisible en petit, sur fond clair et foncé;
- app enseignant : une primaire et une secondaire dans les **verts** (code hex);
- app étudiant : une primaire et une secondaire dans les **oranges** (code hex);
- le contraste texte/fond de chaque couleur (vise au moins 4.5:1, vérifie avec un outil de contraste). Attention : le texte blanc sur orange clair échoue souvent.

## Exercice 2 : un thème, deux palettes <Etiquette type="main" />

À la main, dans le module `:commun` :

1. Déclare les 4 couleurs dans `ui/theme/Color.kt`.
2. Écris **une** fonction de thème paramétrée :

```kotlin
@Composable
fun NotesTheme(palette: Palette, darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
```

où `Palette` vaut `ENSEIGNANT` ou `ETUDIANT`, et construit le `lightColorScheme` / `darkColorScheme` correspondant.
3. Désactive les couleurs dynamiques : on veut **ta** charte, pas celle du fond d'écran.
4. Dans `:enseignant`, appelle `NotesTheme(Palette.ENSEIGNANT)`; dans `:etudiant`, `NotesTheme(Palette.ETUDIANT)`.
5. Même logo comme icône adaptative des deux apps, avec un fond vert pour l'une et orange pour l'autre.

## Exercice 3 : décliner partout <Etiquette type="claude" />

Demande à Claude de faire passer **tous** les écrans des deux apps par `MaterialTheme.colorScheme` et `MaterialTheme.typography`, sans aucune couleur en dur.

Vérifie ensuite **à la main** :

```bash
grep -rn "Color(0x" android/
```

Il ne doit rester que `Color.kt` de `:commun`. Note dans `IA.md` ce que Claude a oublié.
