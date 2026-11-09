---
title: Compatibilité et API 
description: Compatibilité - API 21 à 30 et tests sur émulateurs
hide_table_of_contents: true
---

# Compatibilité et API

<Row>

<Column>

:::tip Avant la séance (2h)

Le but de cette séance est de voir si votre application fonctionne sur différentes versions d'API Android.

- Est-ce que vous implémentez de nouvelles fonctionnalités sans offrir une alternative pour les vieilles versions ?
- Est-ce que la version minimale requise est trop restrictive ?

:::

</Column>

<Column>

:::info Séance

On discutera de quelle version d'API utiliser pour le déploiement sur le Play Store.

On discutera des [changements aux APIs](https://abi-laboratory.pro/?view=timeline&lang=java&l=android) dans les dernières années et de l'impact des versions sur la rétro-compatibilité.

On regardera comment utiliser la [documentation](https://developer.android.com/about/dashboards/index.html) pour obtenir de l'information sur les releases

:::

</Column>

</Row>

:::note Exercices

### Exercice A <Etiquette type="main" />

Ouvrez 5 de vos exercices de la session et essayez de les rouler sur des versions antérieures de l'API. Pour chaque exercice, reculez de 5 versions (ex : API 30, 25, 20, 21 ...).

:::tip Émulateur qui gèle au démarrage
Sur certains postes (Mac Apple Silicon notamment), les images API 30 et moins restent figées au démarrage avec l'accélération graphique. Force le rendu logiciel :

```bash
emulator -avd API_30 -no-snapshot -gpu swiftshader_indirect
```
:::

:::
